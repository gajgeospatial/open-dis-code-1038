package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * 5.2.44: Grid data record, representation 0
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class GridAxisRecordRepresentation0 extends GridAxisRecord implements Serializable
{
   /** number of bytes of environmental state data */
   protected int  numberOfBytes;

   /** variable length list of data parameters @@@this is wrong--need padding as well */
   protected List dataValues = new ArrayList(); 

/** Constructor */
 public GridAxisRecordRepresentation0()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public GridAxisRecordRepresentation0(edu.nps.moves.jaxb.dis.GridAxisRecordRepresentation0 x)
 {
     super(x); // Call superclass constructor

     this.numberOfBytes = x.getNumberOfBytes();
     this.dataValues = new ArrayList();
     for(int idx = 0; idx < x.getDataValues().size(); idx++)
     {
        this.dataValues.add( new edu.nps.moves.dis.OneByteChunk((edu.nps.moves.jaxb.dis.OneByteChunk) x.getDataValues().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.GridAxisRecordRepresentation0 initializeJaxbObject(edu.nps.moves.jaxb.dis.GridAxisRecordRepresentation0 x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setNumberOfBytes( this.getNumberOfBytes() );

     List dataValues_1 = x.getDataValues();
     for(int idx = 0; idx < dataValues.size(); idx++)
     {
         OneByteChunk a = (edu.nps.moves.dis.OneByteChunk)dataValues.get(idx);
         dataValues_1.add(a.initializeJaxbObject(factory.createOneByteChunk()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + 2;  // numberOfBytes
   for(int idx=0; idx < dataValues.size(); idx++)
   {
        OneByteChunk listElement = (OneByteChunk)dataValues.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public int getNumberOfBytes()
{ return (int)dataValues.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfBytes method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfBytes(int pNumberOfBytes)
{ numberOfBytes = pNumberOfBytes;
}

public void setDataValues(List pDataValues)
{ dataValues = pDataValues;
}

public List getDataValues()
{ return dataValues; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       dos.writeShort( (short)dataValues.size());

       for(int idx = 0; idx < dataValues.size(); idx++)
       {
            OneByteChunk aOneByteChunk = (OneByteChunk)dataValues.get(idx);
            aOneByteChunk.marshal(dos);
       } // end of list marshalling

    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    super.unmarshal(dis);

    try 
    {
       numberOfBytes = dis.readShort();
        for(int idx = 0; idx < numberOfBytes; idx++)
        {
           OneByteChunk anX = new OneByteChunk();
            anX.unmarshal(dis);
            dataValues.add(anX);
        };

    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(GridAxisRecordRepresentation0 rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (numberOfBytes == rhs.numberOfBytes)) ivarsEqual = false;

     for(int idx = 0; idx < dataValues.size(); idx++)
     {
        OneByteChunk x = (OneByteChunk)dataValues.get(idx);
        if( ! ( dataValues.get(idx).equals(rhs.dataValues.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
