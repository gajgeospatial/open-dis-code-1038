package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.12.5: Ack receipt of a start-resume, stop-freeze, create-entity or remove enitty (reliable) pdus. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class AcknowledgeReliablePdu extends SimulationManagementWithReliabilityFamilyPdu implements Serializable
{
   /** ack flags */
   protected int  acknowledgeFlag;

   /** response flags */
   protected int  responseFlag;

   /** Request ID */
   protected long  requestID;


/** Constructor */
 public AcknowledgeReliablePdu()
 {
    setPduType( (short)55 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public AcknowledgeReliablePdu(edu.nps.moves.jaxb.dis.AcknowledgeReliablePdu x)
 {
     super(x); // Call superclass constructor

     this.acknowledgeFlag = x.getAcknowledgeFlag();
     this.responseFlag = x.getResponseFlag();
     this.requestID = x.getRequestID();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.AcknowledgeReliablePdu initializeJaxbObject(edu.nps.moves.jaxb.dis.AcknowledgeReliablePdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setAcknowledgeFlag( this.getAcknowledgeFlag() );
     x.setResponseFlag( this.getResponseFlag() );
     x.setRequestID( this.getRequestID() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + 2;  // acknowledgeFlag
   marshalSize = marshalSize + 2;  // responseFlag
   marshalSize = marshalSize + 4;  // requestID

   return marshalSize;
}


public void setAcknowledgeFlag(int pAcknowledgeFlag)
{ acknowledgeFlag = pAcknowledgeFlag;
}

public int getAcknowledgeFlag()
{ return acknowledgeFlag; 
}

public void setResponseFlag(int pResponseFlag)
{ responseFlag = pResponseFlag;
}

public int getResponseFlag()
{ return responseFlag; 
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
       dos.writeShort( (short)acknowledgeFlag);
       dos.writeShort( (short)responseFlag);
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
       acknowledgeFlag = dis.readShort();
       responseFlag = dis.readShort();
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
 public boolean equals(AcknowledgeReliablePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (acknowledgeFlag == rhs.acknowledgeFlag)) ivarsEqual = false;
     if( ! (responseFlag == rhs.responseFlag)) ivarsEqual = false;
     if( ! (requestID == rhs.requestID)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
