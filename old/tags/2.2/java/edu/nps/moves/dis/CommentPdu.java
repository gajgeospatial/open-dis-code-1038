package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.6.12. Arbitrary messages can be entered into the data stream via use of this PDU. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class CommentPdu extends SimulationManagementFamilyPdu implements Serializable
{
   /** Number of fixed datum records */
   protected long  numberOfFixedDatumRecords;

   /** Number of variable datum records */
   protected long  numberOfVariableDatumRecords;

   /** variable length list of fixed datums */
   protected List fixedDatums = new ArrayList(); 
   /** variable length list of variable length datums */
   protected List variableDatums = new ArrayList(); 

/** Constructor */
 public CommentPdu()
 {
    setPduType( (short)22 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public CommentPdu(edu.nps.moves.jaxb.dis.CommentPdu x)
 {
     super(x); // Call superclass constructor

     this.numberOfFixedDatumRecords = x.getNumberOfFixedDatumRecords();
     this.numberOfVariableDatumRecords = x.getNumberOfVariableDatumRecords();
     this.fixedDatums = new ArrayList();
     for(int idx = 0; idx < x.getFixedDatums().size(); idx++)
     {
        this.fixedDatums.add( new edu.nps.moves.dis.FixedDatum((edu.nps.moves.jaxb.dis.FixedDatum) x.getFixedDatums().get(idx)));
     }
     this.variableDatums = new ArrayList();
     for(int idx = 0; idx < x.getVariableDatums().size(); idx++)
     {
        this.variableDatums.add( new edu.nps.moves.dis.VariableDatum((edu.nps.moves.jaxb.dis.VariableDatum) x.getVariableDatums().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.CommentPdu initializeJaxbObject(edu.nps.moves.jaxb.dis.CommentPdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setNumberOfFixedDatumRecords( this.getNumberOfFixedDatumRecords() );
     x.setNumberOfVariableDatumRecords( this.getNumberOfVariableDatumRecords() );

     List fixedDatums_1 = x.getFixedDatums();
     for(int idx = 0; idx < fixedDatums.size(); idx++)
     {
         FixedDatum a = (edu.nps.moves.dis.FixedDatum)fixedDatums.get(idx);
         fixedDatums_1.add(a.initializeJaxbObject(factory.createFixedDatum()));
     }

     List variableDatums_1 = x.getVariableDatums();
     for(int idx = 0; idx < variableDatums.size(); idx++)
     {
         VariableDatum a = (edu.nps.moves.dis.VariableDatum)variableDatums.get(idx);
         variableDatums_1.add(a.initializeJaxbObject(factory.createVariableDatum()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + 4;  // numberOfFixedDatumRecords
   marshalSize = marshalSize + 4;  // numberOfVariableDatumRecords
   for(int idx=0; idx < fixedDatums.size(); idx++)
   {
        FixedDatum listElement = (FixedDatum)fixedDatums.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }
   for(int idx=0; idx < variableDatums.size(); idx++)
   {
        VariableDatum listElement = (VariableDatum)variableDatums.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public long getNumberOfFixedDatumRecords()
{ return (long)fixedDatums.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfFixedDatumRecords method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfFixedDatumRecords(long pNumberOfFixedDatumRecords)
{ numberOfFixedDatumRecords = pNumberOfFixedDatumRecords;
}

public long getNumberOfVariableDatumRecords()
{ return (long)variableDatums.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfVariableDatumRecords method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfVariableDatumRecords(long pNumberOfVariableDatumRecords)
{ numberOfVariableDatumRecords = pNumberOfVariableDatumRecords;
}

public void setFixedDatums(List pFixedDatums)
{ fixedDatums = pFixedDatums;
}

public List getFixedDatums()
{ return fixedDatums; }

public void setVariableDatums(List pVariableDatums)
{ variableDatums = pVariableDatums;
}

public List getVariableDatums()
{ return variableDatums; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       dos.writeInt( (int)fixedDatums.size());
       dos.writeInt( (int)variableDatums.size());

       for(int idx = 0; idx < fixedDatums.size(); idx++)
       {
            FixedDatum aFixedDatum = (FixedDatum)fixedDatums.get(idx);
            aFixedDatum.marshal(dos);
       } // end of list marshalling


       for(int idx = 0; idx < variableDatums.size(); idx++)
       {
            VariableDatum aVariableDatum = (VariableDatum)variableDatums.get(idx);
            aVariableDatum.marshal(dos);
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
       numberOfFixedDatumRecords = dis.readInt();
       numberOfVariableDatumRecords = dis.readInt();
        for(int idx = 0; idx < numberOfFixedDatumRecords; idx++)
        {
           FixedDatum anX = new FixedDatum();
            anX.unmarshal(dis);
            fixedDatums.add(anX);
        };

        for(int idx = 0; idx < numberOfVariableDatumRecords; idx++)
        {
           VariableDatum anX = new VariableDatum();
            anX.unmarshal(dis);
            variableDatums.add(anX);
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
 public boolean equals(CommentPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (numberOfFixedDatumRecords == rhs.numberOfFixedDatumRecords)) ivarsEqual = false;
     if( ! (numberOfVariableDatumRecords == rhs.numberOfVariableDatumRecords)) ivarsEqual = false;

     for(int idx = 0; idx < fixedDatums.size(); idx++)
     {
        FixedDatum x = (FixedDatum)fixedDatums.get(idx);
        if( ! ( fixedDatums.get(idx).equals(rhs.fixedDatums.get(idx)))) ivarsEqual = false;
     }


     for(int idx = 0; idx < variableDatums.size(); idx++)
     {
        VariableDatum x = (VariableDatum)variableDatums.get(idx);
        if( ! ( variableDatums.get(idx).equals(rhs.variableDatums.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
