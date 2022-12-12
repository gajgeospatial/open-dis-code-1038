package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.8.3. Communication of a receiver state. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class ReceiverPdu extends RadioCommunicationsFamilyPdu implements Serializable
{
   /** encoding scheme used, and enumeration */
   protected int  receiverState;

   /** padding */
   protected int  padding1;

   /** received power */
   protected float  receivedPoser;

   /** ID of transmitter */
   protected EntityID  transmitterEntityId = new EntityID(); 

   /** ID of transmitting radio */
   protected int  transmitterRadioId;


/** Constructor */
 public ReceiverPdu()
 {
    setPduType( (short)27 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public ReceiverPdu(edu.nps.moves.jaxb.dis.ReceiverPdu x)
 {
     super(x); // Call superclass constructor

     this.receiverState = x.getReceiverState();
     this.padding1 = x.getPadding1();
     this.receivedPoser = x.getReceivedPoser();

     edu.nps.moves.dis.EntityID foo_3;
     if(x.getTransmitterEntityId() == null)
        foo_3 = new edu.nps.moves.dis.EntityID();
      else
        foo_3 = new edu.nps.moves.dis.EntityID(x.getTransmitterEntityId() );
     this.setTransmitterEntityId(foo_3);

     this.transmitterRadioId = x.getTransmitterRadioId();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.ReceiverPdu initializeJaxbObject(edu.nps.moves.jaxb.dis.ReceiverPdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setReceiverState( this.getReceiverState() );
     x.setPadding1( this.getPadding1() );
     x.setReceivedPoser( this.getReceivedPoser() );
     x.setTransmitterEntityId( this.getTransmitterEntityId().initializeJaxbObject(factory.createEntityID()) );
     x.setTransmitterRadioId( this.getTransmitterRadioId() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + 2;  // receiverState
   marshalSize = marshalSize + 2;  // padding1
   marshalSize = marshalSize + 4;  // receivedPoser
   marshalSize = marshalSize + transmitterEntityId.getMarshalledSize();  // transmitterEntityId
   marshalSize = marshalSize + 2;  // transmitterRadioId

   return marshalSize;
}


public void setReceiverState(int pReceiverState)
{ receiverState = pReceiverState;
}

public int getReceiverState()
{ return receiverState; 
}

public void setPadding1(int pPadding1)
{ padding1 = pPadding1;
}

public int getPadding1()
{ return padding1; 
}

public void setReceivedPoser(float pReceivedPoser)
{ receivedPoser = pReceivedPoser;
}

public float getReceivedPoser()
{ return receivedPoser; 
}

public void setTransmitterEntityId(EntityID pTransmitterEntityId)
{ transmitterEntityId = pTransmitterEntityId;
}

public EntityID getTransmitterEntityId()
{ return transmitterEntityId; }

public void setTransmitterRadioId(int pTransmitterRadioId)
{ transmitterRadioId = pTransmitterRadioId;
}

public int getTransmitterRadioId()
{ return transmitterRadioId; 
}


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       dos.writeShort( (short)receiverState);
       dos.writeShort( (short)padding1);
       dos.writeFloat( (float)receivedPoser);
       transmitterEntityId.marshal(dos);
       dos.writeShort( (short)transmitterRadioId);
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
       receiverState = dis.readShort();
       padding1 = dis.readShort();
       receivedPoser = dis.readFloat();
       transmitterEntityId.unmarshal(dis);
       transmitterRadioId = dis.readShort();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(ReceiverPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (receiverState == rhs.receiverState)) ivarsEqual = false;
     if( ! (padding1 == rhs.padding1)) ivarsEqual = false;
     if( ! (receivedPoser == rhs.receivedPoser)) ivarsEqual = false;
     if( ! (transmitterEntityId.equals( rhs.transmitterEntityId) )) ivarsEqual = false;
     if( ! (transmitterRadioId == rhs.transmitterRadioId)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
