package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.12.10: issued in response to a data query R or set dataR pdu. Needs manual intervention      to fix padding on variable datums. UNFINSIHED
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class DataReliablePdu extends SimulationManagementWithReliabilityFamilyPdu implements Serializable
{
   /** Request ID */
   protected long  requestID;

   /** level of reliability service used for this transaction */
   protected short  requiredReliabilityService;

   /** padding */
   protected int  pad1;

   /** padding */
   protected short  pad2;

   /** Fixed datum record count */
   protected long  numberOfFixedDatumRecords;

   /** variable datum record count */
   protected long  numberOfVariableDatumRecords;

   /** Fixed datum records */
   protected List fixedDatumRecords = new ArrayList(); 
   /** Variable datum records */
   protected List variableDatumRecords = new ArrayList(); 

/** Constructor */
 public DataReliablePdu()
 {
    setPduType( (short)60 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public DataReliablePdu(edu.nps.moves.jaxb.dis.DataReliablePdu x)
 {
     super(x); // Call superclass constructor

     this.requestID = x.getRequestID();
     this.requiredReliabilityService = x.getRequiredReliabilityService();
     this.pad1 = x.getPad1();
     this.pad2 = x.getPad2();
     this.numberOfFixedDatumRecords = x.getNumberOfFixedDatumRecords();
     this.numberOfVariableDatumRecords = x.getNumberOfVariableDatumRecords();
     this.fixedDatumRecords = new ArrayList();
     for(int idx = 0; idx < x.getFixedDatumRecords().size(); idx++)
     {
        this.fixedDatumRecords.add( new edu.nps.moves.dis.FixedDatum((edu.nps.moves.jaxb.dis.FixedDatum) x.getFixedDatumRecords().get(idx)));
     }
     this.variableDatumRecords = new ArrayList();
     for(int idx = 0; idx < x.getVariableDatumRecords().size(); idx++)
     {
        this.variableDatumRecords.add( new edu.nps.moves.dis.VariableDatum((edu.nps.moves.jaxb.dis.VariableDatum) x.getVariableDatumRecords().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.DataReliablePdu initializeJaxbObject(edu.nps.moves.jaxb.dis.DataReliablePdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setRequestID( this.getRequestID() );
     x.setRequiredReliabilityService( this.getRequiredReliabilityService() );
     x.setPad1( this.getPad1() );
     x.setPad2( this.getPad2() );
     x.setNumberOfFixedDatumRecords( this.getNumberOfFixedDatumRecords() );
     x.setNumberOfVariableDatumRecords( this.getNumberOfVariableDatumRecords() );

     List fixedDatumRecords_1 = x.getFixedDatumRecords();
     for(int idx = 0; idx < fixedDatumRecords.size(); idx++)
     {
         FixedDatum a = (edu.nps.moves.dis.FixedDatum)fixedDatumRecords.get(idx);
         fixedDatumRecords_1.add(a.initializeJaxbObject(factory.createFixedDatum()));
     }

     List variableDatumRecords_1 = x.getVariableDatumRecords();
     for(int idx = 0; idx < variableDatumRecords.size(); idx++)
     {
         VariableDatum a = (edu.nps.moves.dis.VariableDatum)variableDatumRecords.get(idx);
         variableDatumRecords_1.add(a.initializeJaxbObject(factory.createVariableDatum()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + 4;  // requestID
   marshalSize = marshalSize + 1;  // requiredReliabilityService
   marshalSize = marshalSize + 2;  // pad1
   marshalSize = marshalSize + 1;  // pad2
   marshalSize = marshalSize + 4;  // numberOfFixedDatumRecords
   marshalSize = marshalSize + 4;  // numberOfVariableDatumRecords
   for(int idx=0; idx < fixedDatumRecords.size(); idx++)
   {
        FixedDatum listElement = (FixedDatum)fixedDatumRecords.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }
   for(int idx=0; idx < variableDatumRecords.size(); idx++)
   {
        VariableDatum listElement = (VariableDatum)variableDatumRecords.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setRequestID(long pRequestID)
{ requestID = pRequestID;
}

public long getRequestID()
{ return requestID; 
}

public void setRequiredReliabilityService(short pRequiredReliabilityService)
{ requiredReliabilityService = pRequiredReliabilityService;
}

public short getRequiredReliabilityService()
{ return requiredReliabilityService; 
}

public void setPad1(int pPad1)
{ pad1 = pPad1;
}

public int getPad1()
{ return pad1; 
}

public void setPad2(short pPad2)
{ pad2 = pPad2;
}

public short getPad2()
{ return pad2; 
}

public long getNumberOfFixedDatumRecords()
{ return (long)fixedDatumRecords.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfFixedDatumRecords method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfFixedDatumRecords(long pNumberOfFixedDatumRecords)
{ numberOfFixedDatumRecords = pNumberOfFixedDatumRecords;
}

public long getNumberOfVariableDatumRecords()
{ return (long)variableDatumRecords.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfVariableDatumRecords method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfVariableDatumRecords(long pNumberOfVariableDatumRecords)
{ numberOfVariableDatumRecords = pNumberOfVariableDatumRecords;
}

public void setFixedDatumRecords(List pFixedDatumRecords)
{ fixedDatumRecords = pFixedDatumRecords;
}

public List getFixedDatumRecords()
{ return fixedDatumRecords; }

public void setVariableDatumRecords(List pVariableDatumRecords)
{ variableDatumRecords = pVariableDatumRecords;
}

public List getVariableDatumRecords()
{ return variableDatumRecords; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       dos.writeInt( (int)requestID);
       dos.writeByte( (byte)requiredReliabilityService);
       dos.writeShort( (short)pad1);
       dos.writeByte( (byte)pad2);
       dos.writeInt( (int)fixedDatumRecords.size());
       dos.writeInt( (int)variableDatumRecords.size());

       for(int idx = 0; idx < fixedDatumRecords.size(); idx++)
       {
            FixedDatum aFixedDatum = (FixedDatum)fixedDatumRecords.get(idx);
            aFixedDatum.marshal(dos);
       } // end of list marshalling


       for(int idx = 0; idx < variableDatumRecords.size(); idx++)
       {
            VariableDatum aVariableDatum = (VariableDatum)variableDatumRecords.get(idx);
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
       requestID = dis.readInt();
       requiredReliabilityService = dis.readByte();
       pad1 = dis.readShort();
       pad2 = dis.readByte();
       numberOfFixedDatumRecords = dis.readInt();
       numberOfVariableDatumRecords = dis.readInt();
        for(int idx = 0; idx < numberOfFixedDatumRecords; idx++)
        {
           FixedDatum anX = new FixedDatum();
            anX.unmarshal(dis);
            fixedDatumRecords.add(anX);
        };

        for(int idx = 0; idx < numberOfVariableDatumRecords; idx++)
        {
           VariableDatum anX = new VariableDatum();
            anX.unmarshal(dis);
            variableDatumRecords.add(anX);
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
 public boolean equals(DataReliablePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (requestID == rhs.requestID)) ivarsEqual = false;
     if( ! (requiredReliabilityService == rhs.requiredReliabilityService)) ivarsEqual = false;
     if( ! (pad1 == rhs.pad1)) ivarsEqual = false;
     if( ! (pad2 == rhs.pad2)) ivarsEqual = false;
     if( ! (numberOfFixedDatumRecords == rhs.numberOfFixedDatumRecords)) ivarsEqual = false;
     if( ! (numberOfVariableDatumRecords == rhs.numberOfVariableDatumRecords)) ivarsEqual = false;

     for(int idx = 0; idx < fixedDatumRecords.size(); idx++)
     {
        FixedDatum x = (FixedDatum)fixedDatumRecords.get(idx);
        if( ! ( fixedDatumRecords.get(idx).equals(rhs.fixedDatumRecords.get(idx)))) ivarsEqual = false;
     }


     for(int idx = 0; idx < variableDatumRecords.size(); idx++)
     {
        VariableDatum x = (VariableDatum)variableDatumRecords.get(idx);
        if( ! ( variableDatumRecords.get(idx).equals(rhs.variableDatumRecords.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
