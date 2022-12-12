package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.2.30. A supply, and the amount of that supply. Similar to an entity kind but with the addition of a quantity.
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class SupplyQuantity extends Object implements Serializable
{
   /** Type of supply */
   protected EntityID  supplyType = new EntityID(); 

   /** quantity to be supplied */
   protected short  quantity;


/** Constructor */
 public SupplyQuantity()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public SupplyQuantity(edu.nps.moves.jaxb.dis.SupplyQuantity x)
 {

     edu.nps.moves.dis.EntityID foo_0;
     if(x.getSupplyType() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getSupplyType() );
     this.setSupplyType(foo_0);

     this.quantity = x.getQuantity();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.SupplyQuantity initializeJaxbObject(edu.nps.moves.jaxb.dis.SupplyQuantity x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setSupplyType( this.getSupplyType().initializeJaxbObject(factory.createEntityID()) );
     x.setQuantity( this.getQuantity() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + supplyType.getMarshalledSize();  // supplyType
   marshalSize = marshalSize + 1;  // quantity

   return marshalSize;
}


public void setSupplyType(EntityID pSupplyType)
{ supplyType = pSupplyType;
}

public EntityID getSupplyType()
{ return supplyType; }

public void setQuantity(short pQuantity)
{ quantity = pQuantity;
}

public short getQuantity()
{ return quantity; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       supplyType.marshal(dos);
       dos.writeByte( (byte)quantity);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       supplyType.unmarshal(dis);
       quantity = dis.readByte();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(SupplyQuantity rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (supplyType.equals( rhs.supplyType) )) ivarsEqual = false;
     if( ! (quantity == rhs.quantity)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
