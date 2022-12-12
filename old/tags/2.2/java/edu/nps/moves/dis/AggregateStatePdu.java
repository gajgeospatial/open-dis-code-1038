package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.9.1 informationa bout aggregating entities anc communicating information about the aggregated entities.        requires manual intervention to fix the padding between entityID lists and silent aggregate sysem lists--this padding        is dependent on how many entityIDs there are, and needs to be on a 32 bit word boundary. UNFINISHED
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class AggregateStatePdu extends EntityManagementFamilyPdu implements Serializable
{
   /** ID of aggregated entities */
   protected EntityID  aggregateID = new EntityID(); 

   /** force ID */
   protected short  forceID;

   /** state of aggregate */
   protected short  aggregateState;

   /** entity type of the aggregated entities */
   protected EntityType  aggregateType = new EntityType(); 

   /** formation of aggregated entities */
   protected long  formation;

   /** marking for aggregate; first char is charset type, rest is char data */
   protected AggregateMarking  aggregateMarking = new AggregateMarking(); 

   /** dimensions of bounding box for the aggregated entities, origin at the center of mass */
   protected Vector3Float  dimensions = new Vector3Float(); 

   /** orientation of the bounding box */
   protected Orientation  orientation = new Orientation(); 

   /** center of mass of the aggregation */
   protected Vector3Double  centerOfMass = new Vector3Double(); 

   /** velocity of aggregation */
   protected Vector3Float  velocity = new Vector3Float(); 

   /** number of aggregates */
   protected int  numberOfDisAggregates;

   /** number of entities */
   protected int  numberOfDisEntities;

   /** number of silent aggregate types */
   protected int  numberOfSilentAggregateTypes;

   /** number of silent entity types */
   protected int  numberOfSilentEntityTypes;

   /** aggregates  list */
   protected List aggregateIDList = new ArrayList(); 
   /** entity ID list */
   protected List entityIDList = new ArrayList(); 
   /** @@@padding to put the start of the next list on a 32 bit boundary. This needs to be fixed */
   protected short  pad2;

   /** silent entity types */
   protected List silentAggregateSystemList = new ArrayList(); 
   /** silent entity types */
   protected List silentEntitySystemList = new ArrayList(); 
   /** number of variable datum records */
   protected long  numberOfVariableDatumRecords;

   /** variableDatums */
   protected List variableDatumList = new ArrayList(); 

/** Constructor */
 public AggregateStatePdu()
 {
    setPduType( (short)33 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public AggregateStatePdu(edu.nps.moves.jaxb.dis.AggregateStatePdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.EntityID foo_0;
     if(x.getAggregateID() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getAggregateID() );
     this.setAggregateID(foo_0);

     this.forceID = x.getForceID();
     this.aggregateState = x.getAggregateState();

     edu.nps.moves.dis.EntityType foo_3;
     if(x.getAggregateType() == null)
        foo_3 = new edu.nps.moves.dis.EntityType();
      else
        foo_3 = new edu.nps.moves.dis.EntityType(x.getAggregateType() );
     this.setAggregateType(foo_3);

     this.formation = x.getFormation();

     edu.nps.moves.dis.AggregateMarking foo_5;
     if(x.getAggregateMarking() == null)
        foo_5 = new edu.nps.moves.dis.AggregateMarking();
      else
        foo_5 = new edu.nps.moves.dis.AggregateMarking(x.getAggregateMarking() );
     this.setAggregateMarking(foo_5);


     edu.nps.moves.dis.Vector3Float foo_6;
     if(x.getDimensions() == null)
        foo_6 = new edu.nps.moves.dis.Vector3Float();
      else
        foo_6 = new edu.nps.moves.dis.Vector3Float(x.getDimensions() );
     this.setDimensions(foo_6);


     edu.nps.moves.dis.Orientation foo_7;
     if(x.getOrientation() == null)
        foo_7 = new edu.nps.moves.dis.Orientation();
      else
        foo_7 = new edu.nps.moves.dis.Orientation(x.getOrientation() );
     this.setOrientation(foo_7);


     edu.nps.moves.dis.Vector3Double foo_8;
     if(x.getCenterOfMass() == null)
        foo_8 = new edu.nps.moves.dis.Vector3Double();
      else
        foo_8 = new edu.nps.moves.dis.Vector3Double(x.getCenterOfMass() );
     this.setCenterOfMass(foo_8);


     edu.nps.moves.dis.Vector3Float foo_9;
     if(x.getVelocity() == null)
        foo_9 = new edu.nps.moves.dis.Vector3Float();
      else
        foo_9 = new edu.nps.moves.dis.Vector3Float(x.getVelocity() );
     this.setVelocity(foo_9);

     this.numberOfDisAggregates = x.getNumberOfDisAggregates();
     this.numberOfDisEntities = x.getNumberOfDisEntities();
     this.numberOfSilentAggregateTypes = x.getNumberOfSilentAggregateTypes();
     this.numberOfSilentEntityTypes = x.getNumberOfSilentEntityTypes();
     this.aggregateIDList = new ArrayList();
     for(int idx = 0; idx < x.getAggregateIDList().size(); idx++)
     {
        this.aggregateIDList.add( new edu.nps.moves.dis.AggregateID((edu.nps.moves.jaxb.dis.AggregateID) x.getAggregateIDList().get(idx)));
     }
     this.entityIDList = new ArrayList();
     for(int idx = 0; idx < x.getEntityIDList().size(); idx++)
     {
        this.entityIDList.add( new edu.nps.moves.dis.EntityID((edu.nps.moves.jaxb.dis.EntityID) x.getEntityIDList().get(idx)));
     }
     this.pad2 = x.getPad2();
     this.silentAggregateSystemList = new ArrayList();
     for(int idx = 0; idx < x.getSilentAggregateSystemList().size(); idx++)
     {
        this.silentAggregateSystemList.add( new edu.nps.moves.dis.EntityType((edu.nps.moves.jaxb.dis.EntityType) x.getSilentAggregateSystemList().get(idx)));
     }
     this.silentEntitySystemList = new ArrayList();
     for(int idx = 0; idx < x.getSilentEntitySystemList().size(); idx++)
     {
        this.silentEntitySystemList.add( new edu.nps.moves.dis.EntityType((edu.nps.moves.jaxb.dis.EntityType) x.getSilentEntitySystemList().get(idx)));
     }
     this.numberOfVariableDatumRecords = x.getNumberOfVariableDatumRecords();
     this.variableDatumList = new ArrayList();
     for(int idx = 0; idx < x.getVariableDatumList().size(); idx++)
     {
        this.variableDatumList.add( new edu.nps.moves.dis.VariableDatum((edu.nps.moves.jaxb.dis.VariableDatum) x.getVariableDatumList().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.AggregateStatePdu initializeJaxbObject(edu.nps.moves.jaxb.dis.AggregateStatePdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setAggregateID( this.getAggregateID().initializeJaxbObject(factory.createEntityID()) );
     x.setForceID( this.getForceID() );
     x.setAggregateState( this.getAggregateState() );
     x.setAggregateType( this.getAggregateType().initializeJaxbObject(factory.createEntityType()) );
     x.setFormation( this.getFormation() );
     x.setAggregateMarking( this.getAggregateMarking().initializeJaxbObject(factory.createAggregateMarking()) );
     x.setDimensions( this.getDimensions().initializeJaxbObject(factory.createVector3Float()) );
     x.setOrientation( this.getOrientation().initializeJaxbObject(factory.createOrientation()) );
     x.setCenterOfMass( this.getCenterOfMass().initializeJaxbObject(factory.createVector3Double()) );
     x.setVelocity( this.getVelocity().initializeJaxbObject(factory.createVector3Float()) );
     x.setNumberOfDisAggregates( this.getNumberOfDisAggregates() );
     x.setNumberOfDisEntities( this.getNumberOfDisEntities() );
     x.setNumberOfSilentAggregateTypes( this.getNumberOfSilentAggregateTypes() );
     x.setNumberOfSilentEntityTypes( this.getNumberOfSilentEntityTypes() );

     List aggregateIDList_1 = x.getAggregateIDList();
     for(int idx = 0; idx < aggregateIDList.size(); idx++)
     {
         AggregateID a = (edu.nps.moves.dis.AggregateID)aggregateIDList.get(idx);
         aggregateIDList_1.add(a.initializeJaxbObject(factory.createAggregateID()));
     }

     List entityIDList_1 = x.getEntityIDList();
     for(int idx = 0; idx < entityIDList.size(); idx++)
     {
         EntityID a = (edu.nps.moves.dis.EntityID)entityIDList.get(idx);
         entityIDList_1.add(a.initializeJaxbObject(factory.createEntityID()));
     }
     x.setPad2( this.getPad2() );

     List silentAggregateSystemList_1 = x.getSilentAggregateSystemList();
     for(int idx = 0; idx < silentAggregateSystemList.size(); idx++)
     {
         EntityType a = (edu.nps.moves.dis.EntityType)silentAggregateSystemList.get(idx);
         silentAggregateSystemList_1.add(a.initializeJaxbObject(factory.createEntityType()));
     }

     List silentEntitySystemList_1 = x.getSilentEntitySystemList();
     for(int idx = 0; idx < silentEntitySystemList.size(); idx++)
     {
         EntityType a = (edu.nps.moves.dis.EntityType)silentEntitySystemList.get(idx);
         silentEntitySystemList_1.add(a.initializeJaxbObject(factory.createEntityType()));
     }
     x.setNumberOfVariableDatumRecords( this.getNumberOfVariableDatumRecords() );

     List variableDatumList_1 = x.getVariableDatumList();
     for(int idx = 0; idx < variableDatumList.size(); idx++)
     {
         VariableDatum a = (edu.nps.moves.dis.VariableDatum)variableDatumList.get(idx);
         variableDatumList_1.add(a.initializeJaxbObject(factory.createVariableDatum()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + aggregateID.getMarshalledSize();  // aggregateID
   marshalSize = marshalSize + 1;  // forceID
   marshalSize = marshalSize + 1;  // aggregateState
   marshalSize = marshalSize + aggregateType.getMarshalledSize();  // aggregateType
   marshalSize = marshalSize + 4;  // formation
   marshalSize = marshalSize + aggregateMarking.getMarshalledSize();  // aggregateMarking
   marshalSize = marshalSize + dimensions.getMarshalledSize();  // dimensions
   marshalSize = marshalSize + orientation.getMarshalledSize();  // orientation
   marshalSize = marshalSize + centerOfMass.getMarshalledSize();  // centerOfMass
   marshalSize = marshalSize + velocity.getMarshalledSize();  // velocity
   marshalSize = marshalSize + 2;  // numberOfDisAggregates
   marshalSize = marshalSize + 2;  // numberOfDisEntities
   marshalSize = marshalSize + 2;  // numberOfSilentAggregateTypes
   marshalSize = marshalSize + 2;  // numberOfSilentEntityTypes
   for(int idx=0; idx < aggregateIDList.size(); idx++)
   {
        AggregateID listElement = (AggregateID)aggregateIDList.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }
   for(int idx=0; idx < entityIDList.size(); idx++)
   {
        EntityID listElement = (EntityID)entityIDList.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }
   marshalSize = marshalSize + 1;  // pad2
   for(int idx=0; idx < silentAggregateSystemList.size(); idx++)
   {
        EntityType listElement = (EntityType)silentAggregateSystemList.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }
   for(int idx=0; idx < silentEntitySystemList.size(); idx++)
   {
        EntityType listElement = (EntityType)silentEntitySystemList.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }
   marshalSize = marshalSize + 4;  // numberOfVariableDatumRecords
   for(int idx=0; idx < variableDatumList.size(); idx++)
   {
        VariableDatum listElement = (VariableDatum)variableDatumList.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setAggregateID(EntityID pAggregateID)
{ aggregateID = pAggregateID;
}

public EntityID getAggregateID()
{ return aggregateID; }

public void setForceID(short pForceID)
{ forceID = pForceID;
}

public short getForceID()
{ return forceID; 
}

public void setAggregateState(short pAggregateState)
{ aggregateState = pAggregateState;
}

public short getAggregateState()
{ return aggregateState; 
}

public void setAggregateType(EntityType pAggregateType)
{ aggregateType = pAggregateType;
}

public EntityType getAggregateType()
{ return aggregateType; }

public void setFormation(long pFormation)
{ formation = pFormation;
}

public long getFormation()
{ return formation; 
}

public void setAggregateMarking(AggregateMarking pAggregateMarking)
{ aggregateMarking = pAggregateMarking;
}

public AggregateMarking getAggregateMarking()
{ return aggregateMarking; }

public void setDimensions(Vector3Float pDimensions)
{ dimensions = pDimensions;
}

public Vector3Float getDimensions()
{ return dimensions; }

public void setOrientation(Orientation pOrientation)
{ orientation = pOrientation;
}

public Orientation getOrientation()
{ return orientation; }

public void setCenterOfMass(Vector3Double pCenterOfMass)
{ centerOfMass = pCenterOfMass;
}

public Vector3Double getCenterOfMass()
{ return centerOfMass; }

public void setVelocity(Vector3Float pVelocity)
{ velocity = pVelocity;
}

public Vector3Float getVelocity()
{ return velocity; }

public int getNumberOfDisAggregates()
{ return (int)aggregateIDList.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfDisAggregates method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfDisAggregates(int pNumberOfDisAggregates)
{ numberOfDisAggregates = pNumberOfDisAggregates;
}

public int getNumberOfDisEntities()
{ return (int)entityIDList.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfDisEntities method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfDisEntities(int pNumberOfDisEntities)
{ numberOfDisEntities = pNumberOfDisEntities;
}

public int getNumberOfSilentAggregateTypes()
{ return (int)silentAggregateSystemList.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfSilentAggregateTypes method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfSilentAggregateTypes(int pNumberOfSilentAggregateTypes)
{ numberOfSilentAggregateTypes = pNumberOfSilentAggregateTypes;
}

public int getNumberOfSilentEntityTypes()
{ return (int)silentEntitySystemList.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfSilentEntityTypes method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfSilentEntityTypes(int pNumberOfSilentEntityTypes)
{ numberOfSilentEntityTypes = pNumberOfSilentEntityTypes;
}

public void setAggregateIDList(List pAggregateIDList)
{ aggregateIDList = pAggregateIDList;
}

public List getAggregateIDList()
{ return aggregateIDList; }

public void setEntityIDList(List pEntityIDList)
{ entityIDList = pEntityIDList;
}

public List getEntityIDList()
{ return entityIDList; }

public void setPad2(short pPad2)
{ pad2 = pPad2;
}

public short getPad2()
{ return pad2; 
}

public void setSilentAggregateSystemList(List pSilentAggregateSystemList)
{ silentAggregateSystemList = pSilentAggregateSystemList;
}

public List getSilentAggregateSystemList()
{ return silentAggregateSystemList; }

public void setSilentEntitySystemList(List pSilentEntitySystemList)
{ silentEntitySystemList = pSilentEntitySystemList;
}

public List getSilentEntitySystemList()
{ return silentEntitySystemList; }

public long getNumberOfVariableDatumRecords()
{ return (long)variableDatumList.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfVariableDatumRecords method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfVariableDatumRecords(long pNumberOfVariableDatumRecords)
{ numberOfVariableDatumRecords = pNumberOfVariableDatumRecords;
}

public void setVariableDatumList(List pVariableDatumList)
{ variableDatumList = pVariableDatumList;
}

public List getVariableDatumList()
{ return variableDatumList; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       aggregateID.marshal(dos);
       dos.writeByte( (byte)forceID);
       dos.writeByte( (byte)aggregateState);
       aggregateType.marshal(dos);
       dos.writeInt( (int)formation);
       aggregateMarking.marshal(dos);
       dimensions.marshal(dos);
       orientation.marshal(dos);
       centerOfMass.marshal(dos);
       velocity.marshal(dos);
       dos.writeShort( (short)aggregateIDList.size());
       dos.writeShort( (short)entityIDList.size());
       dos.writeShort( (short)silentAggregateSystemList.size());
       dos.writeShort( (short)silentEntitySystemList.size());

       for(int idx = 0; idx < aggregateIDList.size(); idx++)
       {
            AggregateID aAggregateID = (AggregateID)aggregateIDList.get(idx);
            aAggregateID.marshal(dos);
       } // end of list marshalling


       for(int idx = 0; idx < entityIDList.size(); idx++)
       {
            EntityID aEntityID = (EntityID)entityIDList.get(idx);
            aEntityID.marshal(dos);
       } // end of list marshalling

       dos.writeByte( (byte)pad2);

       for(int idx = 0; idx < silentAggregateSystemList.size(); idx++)
       {
            EntityType aEntityType = (EntityType)silentAggregateSystemList.get(idx);
            aEntityType.marshal(dos);
       } // end of list marshalling


       for(int idx = 0; idx < silentEntitySystemList.size(); idx++)
       {
            EntityType aEntityType = (EntityType)silentEntitySystemList.get(idx);
            aEntityType.marshal(dos);
       } // end of list marshalling

       dos.writeInt( (int)variableDatumList.size());

       for(int idx = 0; idx < variableDatumList.size(); idx++)
       {
            VariableDatum aVariableDatum = (VariableDatum)variableDatumList.get(idx);
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
       aggregateID.unmarshal(dis);
       forceID = dis.readByte();
       aggregateState = dis.readByte();
       aggregateType.unmarshal(dis);
       formation = dis.readInt();
       aggregateMarking.unmarshal(dis);
       dimensions.unmarshal(dis);
       orientation.unmarshal(dis);
       centerOfMass.unmarshal(dis);
       velocity.unmarshal(dis);
       numberOfDisAggregates = dis.readShort();
       numberOfDisEntities = dis.readShort();
       numberOfSilentAggregateTypes = dis.readShort();
       numberOfSilentEntityTypes = dis.readShort();
        for(int idx = 0; idx < numberOfDisAggregates; idx++)
        {
           AggregateID anX = new AggregateID();
            anX.unmarshal(dis);
            aggregateIDList.add(anX);
        };

        for(int idx = 0; idx < numberOfDisEntities; idx++)
        {
           EntityID anX = new EntityID();
            anX.unmarshal(dis);
            entityIDList.add(anX);
        };

       pad2 = dis.readByte();
        for(int idx = 0; idx < numberOfSilentAggregateTypes; idx++)
        {
           EntityType anX = new EntityType();
            anX.unmarshal(dis);
            silentAggregateSystemList.add(anX);
        };

        for(int idx = 0; idx < numberOfSilentEntityTypes; idx++)
        {
           EntityType anX = new EntityType();
            anX.unmarshal(dis);
            silentEntitySystemList.add(anX);
        };

       numberOfVariableDatumRecords = dis.readInt();
        for(int idx = 0; idx < numberOfVariableDatumRecords; idx++)
        {
           VariableDatum anX = new VariableDatum();
            anX.unmarshal(dis);
            variableDatumList.add(anX);
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
 public boolean equals(AggregateStatePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (aggregateID.equals( rhs.aggregateID) )) ivarsEqual = false;
     if( ! (forceID == rhs.forceID)) ivarsEqual = false;
     if( ! (aggregateState == rhs.aggregateState)) ivarsEqual = false;
     if( ! (aggregateType.equals( rhs.aggregateType) )) ivarsEqual = false;
     if( ! (formation == rhs.formation)) ivarsEqual = false;
     if( ! (aggregateMarking.equals( rhs.aggregateMarking) )) ivarsEqual = false;
     if( ! (dimensions.equals( rhs.dimensions) )) ivarsEqual = false;
     if( ! (orientation.equals( rhs.orientation) )) ivarsEqual = false;
     if( ! (centerOfMass.equals( rhs.centerOfMass) )) ivarsEqual = false;
     if( ! (velocity.equals( rhs.velocity) )) ivarsEqual = false;
     if( ! (numberOfDisAggregates == rhs.numberOfDisAggregates)) ivarsEqual = false;
     if( ! (numberOfDisEntities == rhs.numberOfDisEntities)) ivarsEqual = false;
     if( ! (numberOfSilentAggregateTypes == rhs.numberOfSilentAggregateTypes)) ivarsEqual = false;
     if( ! (numberOfSilentEntityTypes == rhs.numberOfSilentEntityTypes)) ivarsEqual = false;

     for(int idx = 0; idx < aggregateIDList.size(); idx++)
     {
        AggregateID x = (AggregateID)aggregateIDList.get(idx);
        if( ! ( aggregateIDList.get(idx).equals(rhs.aggregateIDList.get(idx)))) ivarsEqual = false;
     }


     for(int idx = 0; idx < entityIDList.size(); idx++)
     {
        EntityID x = (EntityID)entityIDList.get(idx);
        if( ! ( entityIDList.get(idx).equals(rhs.entityIDList.get(idx)))) ivarsEqual = false;
     }

     if( ! (pad2 == rhs.pad2)) ivarsEqual = false;

     for(int idx = 0; idx < silentAggregateSystemList.size(); idx++)
     {
        EntityType x = (EntityType)silentAggregateSystemList.get(idx);
        if( ! ( silentAggregateSystemList.get(idx).equals(rhs.silentAggregateSystemList.get(idx)))) ivarsEqual = false;
     }


     for(int idx = 0; idx < silentEntitySystemList.size(); idx++)
     {
        EntityType x = (EntityType)silentEntitySystemList.get(idx);
        if( ! ( silentEntitySystemList.get(idx).equals(rhs.silentEntitySystemList.get(idx)))) ivarsEqual = false;
     }

     if( ! (numberOfVariableDatumRecords == rhs.numberOfVariableDatumRecords)) ivarsEqual = false;

     for(int idx = 0; idx < variableDatumList.size(); idx++)
     {
        VariableDatum x = (VariableDatum)variableDatumList.get(idx);
        if( ! ( variableDatumList.get(idx).equals(rhs.variableDatumList.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
