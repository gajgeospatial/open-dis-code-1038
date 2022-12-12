package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.7.5. SEES PDU, supplemental emissions entity state information. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class SeesPdu extends DistributedEmissionsFamilyPdu implements Serializable
{
   /** Originating entity ID */
   protected EntityID  orginatingEntityID = new EntityID(); 

   /** IR Signature representation index */
   protected int  infraredSignatureRepresentationIndex;

   /** acoustic Signature representation index */
   protected int  acousticSignatureRepresentationIndex;

   /** radar cross section representation index */
   protected int  radarCrossSectionSignatureRepresentationIndex;

   /** how many propulsion systems */
   protected int  numberOfPropulsionSystems;

   /** how many vectoring nozzle systems */
   protected int  numberOfVectoringNozzleSystems;

   /** variable length list of propulsion system data */
   protected List propulsionSystemData = new ArrayList(); 
   /** variable length list of vectoring system data */
   protected List vectoringSystemData = new ArrayList(); 

/** Constructor */
 public SeesPdu()
 {
    setPduType( (short)30 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public SeesPdu(edu.nps.moves.jaxb.dis.SeesPdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.EntityID foo_0;
     if(x.getOrginatingEntityID() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getOrginatingEntityID() );
     this.setOrginatingEntityID(foo_0);

     this.infraredSignatureRepresentationIndex = x.getInfraredSignatureRepresentationIndex();
     this.acousticSignatureRepresentationIndex = x.getAcousticSignatureRepresentationIndex();
     this.radarCrossSectionSignatureRepresentationIndex = x.getRadarCrossSectionSignatureRepresentationIndex();
     this.numberOfPropulsionSystems = x.getNumberOfPropulsionSystems();
     this.numberOfVectoringNozzleSystems = x.getNumberOfVectoringNozzleSystems();
     this.propulsionSystemData = new ArrayList();
     for(int idx = 0; idx < x.getPropulsionSystemData().size(); idx++)
     {
        this.propulsionSystemData.add( new edu.nps.moves.dis.PropulsionSystemData((edu.nps.moves.jaxb.dis.PropulsionSystemData) x.getPropulsionSystemData().get(idx)));
     }
     this.vectoringSystemData = new ArrayList();
     for(int idx = 0; idx < x.getVectoringSystemData().size(); idx++)
     {
        this.vectoringSystemData.add( new edu.nps.moves.dis.VectoringNozzleSystemData((edu.nps.moves.jaxb.dis.VectoringNozzleSystemData) x.getVectoringSystemData().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.SeesPdu initializeJaxbObject(edu.nps.moves.jaxb.dis.SeesPdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setOrginatingEntityID( this.getOrginatingEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setInfraredSignatureRepresentationIndex( this.getInfraredSignatureRepresentationIndex() );
     x.setAcousticSignatureRepresentationIndex( this.getAcousticSignatureRepresentationIndex() );
     x.setRadarCrossSectionSignatureRepresentationIndex( this.getRadarCrossSectionSignatureRepresentationIndex() );
     x.setNumberOfPropulsionSystems( this.getNumberOfPropulsionSystems() );
     x.setNumberOfVectoringNozzleSystems( this.getNumberOfVectoringNozzleSystems() );

     List propulsionSystemData_1 = x.getPropulsionSystemData();
     for(int idx = 0; idx < propulsionSystemData.size(); idx++)
     {
         PropulsionSystemData a = (edu.nps.moves.dis.PropulsionSystemData)propulsionSystemData.get(idx);
         propulsionSystemData_1.add(a.initializeJaxbObject(factory.createPropulsionSystemData()));
     }

     List vectoringSystemData_1 = x.getVectoringSystemData();
     for(int idx = 0; idx < vectoringSystemData.size(); idx++)
     {
         VectoringNozzleSystemData a = (edu.nps.moves.dis.VectoringNozzleSystemData)vectoringSystemData.get(idx);
         vectoringSystemData_1.add(a.initializeJaxbObject(factory.createVectoringNozzleSystemData()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + orginatingEntityID.getMarshalledSize();  // orginatingEntityID
   marshalSize = marshalSize + 2;  // infraredSignatureRepresentationIndex
   marshalSize = marshalSize + 2;  // acousticSignatureRepresentationIndex
   marshalSize = marshalSize + 2;  // radarCrossSectionSignatureRepresentationIndex
   marshalSize = marshalSize + 2;  // numberOfPropulsionSystems
   marshalSize = marshalSize + 2;  // numberOfVectoringNozzleSystems
   for(int idx=0; idx < propulsionSystemData.size(); idx++)
   {
        PropulsionSystemData listElement = (PropulsionSystemData)propulsionSystemData.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }
   for(int idx=0; idx < vectoringSystemData.size(); idx++)
   {
        VectoringNozzleSystemData listElement = (VectoringNozzleSystemData)vectoringSystemData.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setOrginatingEntityID(EntityID pOrginatingEntityID)
{ orginatingEntityID = pOrginatingEntityID;
}

public EntityID getOrginatingEntityID()
{ return orginatingEntityID; }

public void setInfraredSignatureRepresentationIndex(int pInfraredSignatureRepresentationIndex)
{ infraredSignatureRepresentationIndex = pInfraredSignatureRepresentationIndex;
}

public int getInfraredSignatureRepresentationIndex()
{ return infraredSignatureRepresentationIndex; 
}

public void setAcousticSignatureRepresentationIndex(int pAcousticSignatureRepresentationIndex)
{ acousticSignatureRepresentationIndex = pAcousticSignatureRepresentationIndex;
}

public int getAcousticSignatureRepresentationIndex()
{ return acousticSignatureRepresentationIndex; 
}

public void setRadarCrossSectionSignatureRepresentationIndex(int pRadarCrossSectionSignatureRepresentationIndex)
{ radarCrossSectionSignatureRepresentationIndex = pRadarCrossSectionSignatureRepresentationIndex;
}

public int getRadarCrossSectionSignatureRepresentationIndex()
{ return radarCrossSectionSignatureRepresentationIndex; 
}

public int getNumberOfPropulsionSystems()
{ return (int)propulsionSystemData.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfPropulsionSystems method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfPropulsionSystems(int pNumberOfPropulsionSystems)
{ numberOfPropulsionSystems = pNumberOfPropulsionSystems;
}

public int getNumberOfVectoringNozzleSystems()
{ return (int)vectoringSystemData.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfVectoringNozzleSystems method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfVectoringNozzleSystems(int pNumberOfVectoringNozzleSystems)
{ numberOfVectoringNozzleSystems = pNumberOfVectoringNozzleSystems;
}

public void setPropulsionSystemData(List pPropulsionSystemData)
{ propulsionSystemData = pPropulsionSystemData;
}

public List getPropulsionSystemData()
{ return propulsionSystemData; }

public void setVectoringSystemData(List pVectoringSystemData)
{ vectoringSystemData = pVectoringSystemData;
}

public List getVectoringSystemData()
{ return vectoringSystemData; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       orginatingEntityID.marshal(dos);
       dos.writeShort( (short)infraredSignatureRepresentationIndex);
       dos.writeShort( (short)acousticSignatureRepresentationIndex);
       dos.writeShort( (short)radarCrossSectionSignatureRepresentationIndex);
       dos.writeShort( (short)propulsionSystemData.size());
       dos.writeShort( (short)vectoringSystemData.size());

       for(int idx = 0; idx < propulsionSystemData.size(); idx++)
       {
            PropulsionSystemData aPropulsionSystemData = (PropulsionSystemData)propulsionSystemData.get(idx);
            aPropulsionSystemData.marshal(dos);
       } // end of list marshalling


       for(int idx = 0; idx < vectoringSystemData.size(); idx++)
       {
            VectoringNozzleSystemData aVectoringNozzleSystemData = (VectoringNozzleSystemData)vectoringSystemData.get(idx);
            aVectoringNozzleSystemData.marshal(dos);
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
       orginatingEntityID.unmarshal(dis);
       infraredSignatureRepresentationIndex = dis.readShort();
       acousticSignatureRepresentationIndex = dis.readShort();
       radarCrossSectionSignatureRepresentationIndex = dis.readShort();
       numberOfPropulsionSystems = dis.readShort();
       numberOfVectoringNozzleSystems = dis.readShort();
        for(int idx = 0; idx < numberOfPropulsionSystems; idx++)
        {
           PropulsionSystemData anX = new PropulsionSystemData();
            anX.unmarshal(dis);
            propulsionSystemData.add(anX);
        };

        for(int idx = 0; idx < numberOfVectoringNozzleSystems; idx++)
        {
           VectoringNozzleSystemData anX = new VectoringNozzleSystemData();
            anX.unmarshal(dis);
            vectoringSystemData.add(anX);
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
 public boolean equals(SeesPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (orginatingEntityID.equals( rhs.orginatingEntityID) )) ivarsEqual = false;
     if( ! (infraredSignatureRepresentationIndex == rhs.infraredSignatureRepresentationIndex)) ivarsEqual = false;
     if( ! (acousticSignatureRepresentationIndex == rhs.acousticSignatureRepresentationIndex)) ivarsEqual = false;
     if( ! (radarCrossSectionSignatureRepresentationIndex == rhs.radarCrossSectionSignatureRepresentationIndex)) ivarsEqual = false;
     if( ! (numberOfPropulsionSystems == rhs.numberOfPropulsionSystems)) ivarsEqual = false;
     if( ! (numberOfVectoringNozzleSystems == rhs.numberOfVectoringNozzleSystems)) ivarsEqual = false;

     for(int idx = 0; idx < propulsionSystemData.size(); idx++)
     {
        PropulsionSystemData x = (PropulsionSystemData)propulsionSystemData.get(idx);
        if( ! ( propulsionSystemData.get(idx).equals(rhs.propulsionSystemData.get(idx)))) ivarsEqual = false;
     }


     for(int idx = 0; idx < vectoringSystemData.size(); idx++)
     {
        VectoringNozzleSystemData x = (VectoringNozzleSystemData)vectoringSystemData.get(idx);
        if( ! ( vectoringSystemData.get(idx).equals(rhs.vectoringSystemData.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
