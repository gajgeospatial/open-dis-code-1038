package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.2.5.6. Sent after repair complete PDU. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class RepairResponsePdu extends LogisticsFamilyPdu implements Serializable
{
   /** Entity that is receiving service */
   protected EntityID  receivingEntityID = new EntityID(); 

   /** Entity that is supplying */
   protected EntityID  repairingEntityID = new EntityID(); 

   /** Result of repair operation */
   protected short  repairResult;

   /** padding */
   protected short  padding1 = 0;

   /** padding */
   protected byte  padding2 = 0;


/** Constructor */
 public RepairResponsePdu()
 {
    setPduType( (short)10 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public RepairResponsePdu(edu.nps.moves.jaxb.dis.RepairResponsePdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.EntityID foo_0;
     if(x.getReceivingEntityID() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getReceivingEntityID() );
     this.setReceivingEntityID(foo_0);


     edu.nps.moves.dis.EntityID foo_1;
     if(x.getRepairingEntityID() == null)
        foo_1 = new edu.nps.moves.dis.EntityID();
      else
        foo_1 = new edu.nps.moves.dis.EntityID(x.getRepairingEntityID() );
     this.setRepairingEntityID(foo_1);

     this.repairResult = x.getRepairResult();
     this.padding1 = x.getPadding1();
     this.padding2 = x.getPadding2();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.RepairResponsePdu initializeJaxbObject(edu.nps.moves.jaxb.dis.RepairResponsePdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setReceivingEntityID( this.getReceivingEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setRepairingEntityID( this.getRepairingEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setRepairResult( this.getRepairResult() );
     x.setPadding1( this.getPadding1() );
     x.setPadding2( this.getPadding2() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + receivingEntityID.getMarshalledSize();  // receivingEntityID
   marshalSize = marshalSize + repairingEntityID.getMarshalledSize();  // repairingEntityID
   marshalSize = marshalSize + 1;  // repairResult
   marshalSize = marshalSize + 2;  // padding1
   marshalSize = marshalSize + 1;  // padding2

   return marshalSize;
}


public void setReceivingEntityID(EntityID pReceivingEntityID)
{ receivingEntityID = pReceivingEntityID;
}

public EntityID getReceivingEntityID()
{ return receivingEntityID; }

public void setRepairingEntityID(EntityID pRepairingEntityID)
{ repairingEntityID = pRepairingEntityID;
}

public EntityID getRepairingEntityID()
{ return repairingEntityID; }

public void setRepairResult(short pRepairResult)
{ repairResult = pRepairResult;
}

public short getRepairResult()
{ return repairResult; 
}

public void setPadding1(short pPadding1)
{ padding1 = pPadding1;
}

public short getPadding1()
{ return padding1; 
}

public void setPadding2(byte pPadding2)
{ padding2 = pPadding2;
}

public byte getPadding2()
{ return padding2; 
}


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       receivingEntityID.marshal(dos);
       repairingEntityID.marshal(dos);
       dos.writeByte( (byte)repairResult);
       dos.writeShort( (short)padding1);
       dos.writeByte( (byte)padding2);
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
       receivingEntityID.unmarshal(dis);
       repairingEntityID.unmarshal(dis);
       repairResult = dis.readByte();
       padding1 = dis.readShort();
       padding2 = dis.readByte();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(RepairResponsePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (receivingEntityID.equals( rhs.receivingEntityID) )) ivarsEqual = false;
     if( ! (repairingEntityID.equals( rhs.repairingEntityID) )) ivarsEqual = false;
     if( ! (repairResult == rhs.repairResult)) ivarsEqual = false;
     if( ! (padding1 == rhs.padding1)) ivarsEqual = false;
     if( ! (padding2 == rhs.padding2)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
