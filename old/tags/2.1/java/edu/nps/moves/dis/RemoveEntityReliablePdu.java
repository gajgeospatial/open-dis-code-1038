package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.12.2: Removal of an entity , reliable. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class RemoveEntityReliablePdu extends SimulationManagementWithReliabilityFamilyPdu implements Serializable
{
   /** level of reliability service used for this transaction */
   protected short  requiredReliabilityService;

   /** padding */
   protected int  pad1;

   /** padding */
   protected short  pad2;

   /** Request ID */
   protected long  requestID;


/** Constructor */
 public RemoveEntityReliablePdu()
 {
    setPduType( (short)52 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public RemoveEntityReliablePdu(edu.nps.moves.jaxb.dis.RemoveEntityReliablePdu x)
 {
     super(x); // Call superclass constructor

     this.requiredReliabilityService = x.getRequiredReliabilityService();
     this.pad1 = x.getPad1();
     this.pad2 = x.getPad2();
     this.requestID = x.getRequestID();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.RemoveEntityReliablePdu initializeJaxbObject(edu.nps.moves.jaxb.dis.RemoveEntityReliablePdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setRequiredReliabilityService( this.getRequiredReliabilityService() );
     x.setPad1( this.getPad1() );
     x.setPad2( this.getPad2() );
     x.setRequestID( this.getRequestID() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + 1;  // requiredReliabilityService
   marshalSize = marshalSize + 2;  // pad1
   marshalSize = marshalSize + 1;  // pad2
   marshalSize = marshalSize + 4;  // requestID

   return marshalSize;
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

public void setRequestID(long pRequestID)
{ requestID = pRequestID;
}

public long getRequestID()
{ return requestID; 
}


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       dos.writeByte( (byte)requiredReliabilityService);
       dos.writeShort( (short)pad1);
       dos.writeByte( (byte)pad2);
       dos.writeInt( (int)requestID);
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
       requiredReliabilityService = dis.readByte();
       pad1 = dis.readShort();
       pad2 = dis.readByte();
       requestID = dis.readInt();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(RemoveEntityReliablePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (requiredReliabilityService == rhs.requiredReliabilityService)) ivarsEqual = false;
     if( ! (pad1 == rhs.pad1)) ivarsEqual = false;
     if( ! (pad2 == rhs.pad2)) ivarsEqual = false;
     if( ! (requestID == rhs.requestID)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
