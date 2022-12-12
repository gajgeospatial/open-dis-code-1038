package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.5.1. Information about a request for supplies. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class ServiceRequestPdu extends LogisticsFamilyPdu implements Serializable
{
   /** Entity that is requesting service */
   protected EntityID  requestingEntityID = new EntityID(); 

   /** Entity that is providing the service */
   protected EntityID  servicingEntityID = new EntityID(); 

   /** type of service requested */
   protected short  serviceTypeRequested;

   /** How many requested */
   protected short  numberOfSupplyTypes;

   /** padding */
   protected short  serviceRequestPadding = 0;

   protected List supplies = new ArrayList(); 

/** Constructor */
 public ServiceRequestPdu()
 {
    setPduType( (short)5 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public ServiceRequestPdu(edu.nps.moves.jaxb.dis.ServiceRequestPdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.EntityID foo_0;
     if(x.getRequestingEntityID() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getRequestingEntityID() );
     this.setRequestingEntityID(foo_0);


     edu.nps.moves.dis.EntityID foo_1;
     if(x.getServicingEntityID() == null)
        foo_1 = new edu.nps.moves.dis.EntityID();
      else
        foo_1 = new edu.nps.moves.dis.EntityID(x.getServicingEntityID() );
     this.setServicingEntityID(foo_1);

     this.serviceTypeRequested = x.getServiceTypeRequested();
     this.numberOfSupplyTypes = x.getNumberOfSupplyTypes();
     this.serviceRequestPadding = x.getServiceRequestPadding();
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
 public edu.nps.moves.jaxb.dis.ServiceRequestPdu initializeJaxbObject(edu.nps.moves.jaxb.dis.ServiceRequestPdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setRequestingEntityID( this.getRequestingEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setServicingEntityID( this.getServicingEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setServiceTypeRequested( this.getServiceTypeRequested() );
     x.setNumberOfSupplyTypes( this.getNumberOfSupplyTypes() );
     x.setServiceRequestPadding( this.getServiceRequestPadding() );

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
   marshalSize = marshalSize + requestingEntityID.getMarshalledSize();  // requestingEntityID
   marshalSize = marshalSize + servicingEntityID.getMarshalledSize();  // servicingEntityID
   marshalSize = marshalSize + 1;  // serviceTypeRequested
   marshalSize = marshalSize + 1;  // numberOfSupplyTypes
   marshalSize = marshalSize + 2;  // serviceRequestPadding
   for(int idx=0; idx < supplies.size(); idx++)
   {
        SupplyQuantity listElement = (SupplyQuantity)supplies.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setRequestingEntityID(EntityID pRequestingEntityID)
{ requestingEntityID = pRequestingEntityID;
}

public EntityID getRequestingEntityID()
{ return requestingEntityID; }

public void setServicingEntityID(EntityID pServicingEntityID)
{ servicingEntityID = pServicingEntityID;
}

public EntityID getServicingEntityID()
{ return servicingEntityID; }

public void setServiceTypeRequested(short pServiceTypeRequested)
{ serviceTypeRequested = pServiceTypeRequested;
}

public short getServiceTypeRequested()
{ return serviceTypeRequested; 
}

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

public void setServiceRequestPadding(short pServiceRequestPadding)
{ serviceRequestPadding = pServiceRequestPadding;
}

public short getServiceRequestPadding()
{ return serviceRequestPadding; 
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
       requestingEntityID.marshal(dos);
       servicingEntityID.marshal(dos);
       dos.writeByte( (byte)serviceTypeRequested);
       dos.writeByte( (byte)supplies.size());
       dos.writeShort( (short)serviceRequestPadding);

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
       requestingEntityID.unmarshal(dis);
       servicingEntityID.unmarshal(dis);
       serviceTypeRequested = dis.readByte();
       numberOfSupplyTypes = dis.readByte();
       serviceRequestPadding = dis.readShort();
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
 public boolean equals(ServiceRequestPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (requestingEntityID.equals( rhs.requestingEntityID) )) ivarsEqual = false;
     if( ! (servicingEntityID.equals( rhs.servicingEntityID) )) ivarsEqual = false;
     if( ! (serviceTypeRequested == rhs.serviceTypeRequested)) ivarsEqual = false;
     if( ! (numberOfSupplyTypes == rhs.numberOfSupplyTypes)) ivarsEqual = false;
     if( ! (serviceRequestPadding == rhs.serviceRequestPadding)) ivarsEqual = false;

     for(int idx = 0; idx < supplies.size(); idx++)
     {
        SupplyQuantity x = (SupplyQuantity)supplies.get(idx);
        if( ! ( supplies.get(idx).equals(rhs.supplies.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
