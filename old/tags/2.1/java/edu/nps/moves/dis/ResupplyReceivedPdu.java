package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.5.3. Receipt of supplies is communiated. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class ResupplyReceivedPdu extends LogisticsFamilyPdu implements Serializable
{
   /** Entity that is receiving service */
   protected EntityID  receivingEntityID = new EntityID(); 

   /** Entity that is supplying */
   protected EntityID  supplyingEntityID = new EntityID(); 

   /** how many supplies are being offered */
   protected short  numberOfSupplyTypes;

   /** padding */
   protected short  padding1 = 0;

   /** padding */
   protected byte  padding2 = 0;

   protected List supplies = new ArrayList(); 

/** Constructor */
 public ResupplyReceivedPdu()
 {
    setPduType( (short)7 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public ResupplyReceivedPdu(edu.nps.moves.jaxb.dis.ResupplyReceivedPdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.EntityID foo_0;
     if(x.getReceivingEntityID() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getReceivingEntityID() );
     this.setReceivingEntityID(foo_0);


     edu.nps.moves.dis.EntityID foo_1;
     if(x.getSupplyingEntityID() == null)
        foo_1 = new edu.nps.moves.dis.EntityID();
      else
        foo_1 = new edu.nps.moves.dis.EntityID(x.getSupplyingEntityID() );
     this.setSupplyingEntityID(foo_1);

     this.numberOfSupplyTypes = x.getNumberOfSupplyTypes();
     this.padding1 = x.getPadding1();
     this.padding2 = x.getPadding2();
     this.supplies = new ArrayList();
     for(int idx = 0; idx < x.getSupplies().size(); idx++)
     {
        this.supplies.add( new edu.nps.moves.dis.SupplyQuantity((edu.nps.moves.jaxb.dis.SupplyQuantity) x.getSupplies().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.ResupplyReceivedPdu initializeJaxbObject(edu.nps.moves.jaxb.dis.ResupplyReceivedPdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setReceivingEntityID( this.getReceivingEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setSupplyingEntityID( this.getSupplyingEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setNumberOfSupplyTypes( this.getNumberOfSupplyTypes() );
     x.setPadding1( this.getPadding1() );
     x.setPadding2( this.getPadding2() );

     List supplies_1 = x.getSupplies();
     for(int idx = 0; idx < supplies.size(); idx++)
     {
         SupplyQuantity a = (edu.nps.moves.dis.SupplyQuantity)supplies.get(idx);
         supplies_1.add(a.initializeJaxbObject(factory.createSupplyQuantity()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + receivingEntityID.getMarshalledSize();  // receivingEntityID
   marshalSize = marshalSize + supplyingEntityID.getMarshalledSize();  // supplyingEntityID
   marshalSize = marshalSize + 1;  // numberOfSupplyTypes
   marshalSize = marshalSize + 2;  // padding1
   marshalSize = marshalSize + 1;  // padding2
   for(int idx=0; idx < supplies.size(); idx++)
   {
        SupplyQuantity listElement = (SupplyQuantity)supplies.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setReceivingEntityID(EntityID pReceivingEntityID)
{ receivingEntityID = pReceivingEntityID;
}

public EntityID getReceivingEntityID()
{ return receivingEntityID; }

public void setSupplyingEntityID(EntityID pSupplyingEntityID)
{ supplyingEntityID = pSupplyingEntityID;
}

public EntityID getSupplyingEntityID()
{ return supplyingEntityID; }

public short getNumberOfSupplyTypes()
{ return (short)supplies.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfSupplyTypes method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfSupplyTypes(short pNumberOfSupplyTypes)
{ numberOfSupplyTypes = pNumberOfSupplyTypes;
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

public void setSupplies(List pSupplies)
{ supplies = pSupplies;
}

public List getSupplies()
{ return supplies; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       receivingEntityID.marshal(dos);
       supplyingEntityID.marshal(dos);
       dos.writeByte( (byte)supplies.size());
       dos.writeShort( (short)padding1);
       dos.writeByte( (byte)padding2);

       for(int idx = 0; idx < supplies.size(); idx++)
       {
            SupplyQuantity aSupplyQuantity = (SupplyQuantity)supplies.get(idx);
            aSupplyQuantity.marshal(dos);
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
       receivingEntityID.unmarshal(dis);
       supplyingEntityID.unmarshal(dis);
       numberOfSupplyTypes = dis.readByte();
       padding1 = dis.readShort();
       padding2 = dis.readByte();
        for(int idx = 0; idx < numberOfSupplyTypes; idx++)
        {
           SupplyQuantity anX = new SupplyQuantity();
            anX.unmarshal(dis);
            supplies.add(anX);
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
 public boolean equals(ResupplyReceivedPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (receivingEntityID.equals( rhs.receivingEntityID) )) ivarsEqual = false;
     if( ! (supplyingEntityID.equals( rhs.supplyingEntityID) )) ivarsEqual = false;
     if( ! (numberOfSupplyTypes == rhs.numberOfSupplyTypes)) ivarsEqual = false;
     if( ! (padding1 == rhs.padding1)) ivarsEqual = false;
     if( ! (padding2 == rhs.padding2)) ivarsEqual = false;

     for(int idx = 0; idx < supplies.size(); idx++)
     {
        SupplyQuantity x = (SupplyQuantity)supplies.get(idx);
        if( ! ( supplies.get(idx).equals(rhs.supplies.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
