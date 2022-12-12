package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.2.5.5. Repair is complete. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class RepairCompletePdu extends LogisticsFamilyPdu implements Serializable
{
   /** Entity that is receiving service */
   protected EntityID  receivingEntityID = new EntityID(); 

   /** Entity that is supplying */
   protected EntityID  repairingEntityID = new EntityID(); 

   /** Enumeration for type of repair */
   protected int  repair;

   /** padding */
   protected short  padding = 0;


/** Constructor */
 public RepairCompletePdu()
 {
    setPduType( (short)9 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public RepairCompletePdu(edu.nps.moves.jaxb.dis.RepairCompletePdu x)
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

     this.repair = x.getRepair();
     this.padding = x.getPadding();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.RepairCompletePdu initializeJaxbObject(edu.nps.moves.jaxb.dis.RepairCompletePdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setReceivingEntityID( this.getReceivingEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setRepairingEntityID( this.getRepairingEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setRepair( this.getRepair() );
     x.setPadding( this.getPadding() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + receivingEntityID.getMarshalledSize();  // receivingEntityID
   marshalSize = marshalSize + repairingEntityID.getMarshalledSize();  // repairingEntityID
   marshalSize = marshalSize + 2;  // repair
   marshalSize = marshalSize + 2;  // padding

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

public void setRepair(int pRepair)
{ repair = pRepair;
}

public int getRepair()
{ return repair; 
}

public void setPadding(short pPadding)
{ padding = pPadding;
}

public short getPadding()
{ return padding; 
}


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       receivingEntityID.marshal(dos);
       repairingEntityID.marshal(dos);
       dos.writeShort( (short)repair);
       dos.writeShort( (short)padding);
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
       repair = dis.readShort();
       padding = dis.readShort();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(RepairCompletePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (receivingEntityID.equals( rhs.receivingEntityID) )) ivarsEqual = false;
     if( ! (repairingEntityID.equals( rhs.repairingEntityID) )) ivarsEqual = false;
     if( ! (repair == rhs.repair)) ivarsEqual = false;
     if( ! (padding == rhs.padding)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
