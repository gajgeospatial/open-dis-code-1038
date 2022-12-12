package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * 5.2.44: Grid data record, representation 1
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class GridAxisRecordRepresentation1 extends GridAxisRecord implements Serializable
{
   /** constant scale factor */
   protected float  fieldScale;

   /** constant offset used to scale grid data */
   protected float  fieldOffset;

   /** Number of data values */
   protected int  numberOfValues;

   /** variable length list of data parameters @@@this is wrong--need padding as well */
   protected List dataValues = new ArrayList(); 

/** Constructor */
 public GridAxisRecordRepresentation1()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public GridAxisRecordRepresentation1(edu.nps.moves.jaxb.dis.GridAxisRecordRepresentation1 x)
 {
     super(x); // Call superclass constructor

     this.fieldScale = x.getFieldScale();
     this.fieldOffset = x.getFieldOffset();
     this.numberOfValues = x.getNumberOfValues();
     this.dataValues = new ArrayList();
     for(int idx = 0; idx < x.getDataValues().size(); idx++)
     {
        this.dataValues.add( new edu.nps.moves.dis.TwoByteChunk((edu.nps.moves.jaxb.dis.TwoByteChunk) x.getDataValues().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.GridAxisRecordRepresentation1 initializeJaxbObject(edu.nps.moves.jaxb.dis.GridAxisRecordRepresentation1 x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setFieldScale( this.getFieldScale() );
     x.setFieldOffset( this.getFieldOffset() );
     x.setNumberOfValues( this.getNumberOfValues() );

     List dataValues_1 = x.getDataValues();
     for(int idx = 0; idx < dataValues.size(); idx++)
     {
         TwoByteChunk a = (edu.nps.moves.dis.TwoByteChunk)dataValues.get(idx);
         dataValues_1.add(a.initializeJaxbObject(factory.createTwoByteChunk()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + 4;  // fieldScale
   marshalSize = marshalSize + 4;  // fieldOffset
   marshalSize = marshalSize + 2;  // numberOfValues
   for(int idx=0; idx < dataValues.size(); idx++)
   {
        TwoByteChunk listElement = (TwoByteChunk)dataValues.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setFieldScale(float pFieldScale)
{ fieldScale = pFieldScale;
}

public float getFieldScale()
{ return fieldScale; 
}

public void setFieldOffset(float pFieldOffset)
{ fieldOffset = pFieldOffset;
}

public float getFieldOffset()
{ return fieldOffset; 
}

public int getNumberOfValues()
{ return (int)dataValues.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfValues method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfValues(int pNumberOfValues)
{ numberOfValues = pNumberOfValues;
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
       dos.writeFloat( (float)fieldScale);
       dos.writeFloat( (float)fieldOffset);
       dos.writeShort( (short)dataValues.size());

       for(int idx = 0; idx < dataValues.size(); idx++)
       {
            TwoByteChunk aTwoByteChunk = (TwoByteChunk)dataValues.get(idx);
            aTwoByteChunk.marshal(dos);
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
       fieldScale = dis.readFloat();
       fieldOffset = dis.readFloat();
       numberOfValues = dis.readShort();
        for(int idx = 0; idx < numberOfValues; idx++)
        {
           TwoByteChunk anX = new TwoByteChunk();
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
 public boolean equals(GridAxisRecordRepresentation1 rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (fieldScale == rhs.fieldScale)) ivarsEqual = false;
     if( ! (fieldOffset == rhs.fieldOffset)) ivarsEqual = false;
     if( ! (numberOfValues == rhs.numberOfValues)) ivarsEqual = false;

     for(int idx = 0; idx < dataValues.size(); idx++)
     {
        TwoByteChunk x = (TwoByteChunk)dataValues.get(idx);
        if( ! ( dataValues.get(idx).equals(rhs.dataValues.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
