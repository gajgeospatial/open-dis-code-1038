package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.3.1. Represents the postion and state of one entity in the world. This is identical in function to entity state pdu, but generates less garbage to collect in the Java world. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class FastEntityStatePdu extends EntityInformationFamilyPdu implements Serializable
{
   /** The site ID */
   protected int  site;

   /** The application ID */
   protected int  application;

   /** the entity ID */
   protected int  entity;

   /** what force this entity is affiliated with, eg red, blue, neutral, etc */
   protected short  forceId;

   /** How many articulation parameters are in the variable length list */
   protected byte  numberOfArticulationParameters;

   /** Kind of entity */
   protected short  entityKind;

   /** Domain of entity (air, surface, subsurface, space, etc) */
   protected short  domain;

   /** country to which the design of the entity is attributed */
   protected int  country;

   /** category of entity */
   protected short  category;

   /** subcategory of entity */
   protected short  subcategory;

   /** specific info based on subcategory field */
   protected short  specific;

   protected short  extra;

   /** Kind of entity */
   protected short  altEntityKind;

   /** Domain of entity (air, surface, subsurface, space, etc) */
   protected short  altDomain;

   /** country to which the design of the entity is attributed */
   protected int  altCountry;

   /** category of entity */
   protected short  altCategory;

   /** subcategory of entity */
   protected short  altSubcategory;

   /** specific info based on subcategory field */
   protected short  altSpecific;

   protected short  altExtra;

   /** X velo */
   protected float  xVelocity;

   /** y Value */
   protected float  yVelocity;

   /** Z value */
   protected float  zVelocity;

   /** X value */
   protected float  xLocation;

   /** y Value */
   protected float  yLocation;

   /** Z value */
   protected float  zLocation;

   protected float  psi;

   protected float  theta;

   protected float  phi;

   /** a series of bit flags that are used to help draw the entity, such as smoking, on fire, etc. */
   protected int  entityAppearance;

   /** enumeration of what dead reckoning algorighm to use */
   protected short  deadReckoningAlgorithm;

   /** other parameters to use in the dead reckoning algorithm */
   protected byte[]  otherParameters = new byte[15]; 

   /** X value */
   protected float  xAcceleration;

   /** y Value */
   protected float  yAcceleration;

   /** Z value */
   protected float  zAcceleration;

   /** X value */
   protected float  xAngularVelocity;

   /** y Value */
   protected float  yAngularVelocity;

   /** Z value */
   protected float  zAngularVelocity;

   /** characters that can be used for debugging, or to draw unique strings on the side of entities in the world */
   protected byte[]  marking = new byte[12]; 

   /** a series of bit flags */
   protected int  capabilities;

   /** variable length list of articulation parameters */
   protected List articulationParameters = new ArrayList(); 

/** Constructor */
 public FastEntityStatePdu()
 {
    setPduType( (short)1 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public FastEntityStatePdu(edu.nps.moves.jaxb.dis.FastEntityStatePdu x)
 {
     super(x); // Call superclass constructor

     this.site = x.getSite();
     this.application = x.getApplication();
     this.entity = x.getEntity();
     this.forceId = x.getForceId();
     this.numberOfArticulationParameters = x.getNumberOfArticulationParameters();
     this.entityKind = x.getEntityKind();
     this.domain = x.getDomain();
     this.country = x.getCountry();
     this.category = x.getCategory();
     this.subcategory = x.getSubcategory();
     this.specific = x.getSpecific();
     this.extra = x.getExtra();
     this.altEntityKind = x.getAltEntityKind();
     this.altDomain = x.getAltDomain();
     this.altCountry = x.getAltCountry();
     this.altCategory = x.getAltCategory();
     this.altSubcategory = x.getAltSubcategory();
     this.altSpecific = x.getAltSpecific();
     this.altExtra = x.getAltExtra();
     this.xVelocity = x.getXVelocity();
     this.yVelocity = x.getYVelocity();
     this.zVelocity = x.getZVelocity();
     this.xLocation = x.getXLocation();
     this.yLocation = x.getYLocation();
     this.zLocation = x.getZLocation();
     this.psi = x.getPsi();
     this.theta = x.getTheta();
     this.phi = x.getPhi();
     this.entityAppearance = x.getEntityAppearance();
     this.deadReckoningAlgorithm = x.getDeadReckoningAlgorithm();
     this.otherParameters = new byte[15];
     for(int idx = 0; idx < 15; idx++)
     {
         byte[] y = x.getOtherParameters();
         this.otherParameters[idx] = y[idx];
     }
     this.xAcceleration = x.getXAcceleration();
     this.yAcceleration = x.getYAcceleration();
     this.zAcceleration = x.getZAcceleration();
     this.xAngularVelocity = x.getXAngularVelocity();
     this.yAngularVelocity = x.getYAngularVelocity();
     this.zAngularVelocity = x.getZAngularVelocity();
     this.marking = new byte[12];
     for(int idx = 0; idx < 12; idx++)
     {
         byte[] y = x.getMarking();
         this.marking[idx] = y[idx];
     }
     this.capabilities = x.getCapabilities();
     this.articulationParameters = new ArrayList();
     for(int idx = 0; idx < x.getArticulationParameters().size(); idx++)
     {
        this.articulationParameters.add( new edu.nps.moves.dis.ArticulationParameter((edu.nps.moves.jaxb.dis.ArticulationParameter) x.getArticulationParameters().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.FastEntityStatePdu initializeJaxbObject(edu.nps.moves.jaxb.dis.FastEntityStatePdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setSite( this.getSite() );
     x.setApplication( this.getApplication() );
     x.setEntity( this.getEntity() );
     x.setForceId( this.getForceId() );
     x.setNumberOfArticulationParameters( this.getNumberOfArticulationParameters() );
     x.setEntityKind( this.getEntityKind() );
     x.setDomain( this.getDomain() );
     x.setCountry( this.getCountry() );
     x.setCategory( this.getCategory() );
     x.setSubcategory( this.getSubcategory() );
     x.setSpecific( this.getSpecific() );
     x.setExtra( this.getExtra() );
     x.setAltEntityKind( this.getAltEntityKind() );
     x.setAltDomain( this.getAltDomain() );
     x.setAltCountry( this.getAltCountry() );
     x.setAltCategory( this.getAltCategory() );
     x.setAltSubcategory( this.getAltSubcategory() );
     x.setAltSpecific( this.getAltSpecific() );
     x.setAltExtra( this.getAltExtra() );
     x.setXVelocity( this.getXVelocity() );
     x.setYVelocity( this.getYVelocity() );
     x.setZVelocity( this.getZVelocity() );
     x.setXLocation( this.getXLocation() );
     x.setYLocation( this.getYLocation() );
     x.setZLocation( this.getZLocation() );
     x.setPsi( this.getPsi() );
     x.setTheta( this.getTheta() );
     x.setPhi( this.getPhi() );
     x.setEntityAppearance( this.getEntityAppearance() );
     x.setDeadReckoningAlgorithm( this.getDeadReckoningAlgorithm() );
     x.setOtherParameters( new byte[15]);
     for(int idx = 0; idx < 15; idx++)
     {
         x.getOtherParameters()[idx] = this.otherParameters[idx];
     }
     x.setXAcceleration( this.getXAcceleration() );
     x.setYAcceleration( this.getYAcceleration() );
     x.setZAcceleration( this.getZAcceleration() );
     x.setXAngularVelocity( this.getXAngularVelocity() );
     x.setYAngularVelocity( this.getYAngularVelocity() );
     x.setZAngularVelocity( this.getZAngularVelocity() );
     x.setMarking( new byte[12]);
     for(int idx = 0; idx < 12; idx++)
     {
         x.getMarking()[idx] = this.marking[idx];
     }
     x.setCapabilities( this.getCapabilities() );

     List articulationParameters_1 = x.getArticulationParameters();
     for(int idx = 0; idx < articulationParameters.size(); idx++)
     {
         ArticulationParameter a = (edu.nps.moves.dis.ArticulationParameter)articulationParameters.get(idx);
         articulationParameters_1.add(a.initializeJaxbObject(factory.createArticulationParameter()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + 2;  // site
   marshalSize = marshalSize + 2;  // application
   marshalSize = marshalSize + 2;  // entity
   marshalSize = marshalSize + 1;  // forceId
   marshalSize = marshalSize + 1;  // numberOfArticulationParameters
   marshalSize = marshalSize + 1;  // entityKind
   marshalSize = marshalSize + 1;  // domain
   marshalSize = marshalSize + 2;  // country
   marshalSize = marshalSize + 1;  // category
   marshalSize = marshalSize + 1;  // subcategory
   marshalSize = marshalSize + 1;  // specific
   marshalSize = marshalSize + 1;  // extra
   marshalSize = marshalSize + 1;  // altEntityKind
   marshalSize = marshalSize + 1;  // altDomain
   marshalSize = marshalSize + 2;  // altCountry
   marshalSize = marshalSize + 1;  // altCategory
   marshalSize = marshalSize + 1;  // altSubcategory
   marshalSize = marshalSize + 1;  // altSpecific
   marshalSize = marshalSize + 1;  // altExtra
   marshalSize = marshalSize + 4;  // xVelocity
   marshalSize = marshalSize + 4;  // yVelocity
   marshalSize = marshalSize + 4;  // zVelocity
   marshalSize = marshalSize + 4;  // xLocation
   marshalSize = marshalSize + 4;  // yLocation
   marshalSize = marshalSize + 4;  // zLocation
   marshalSize = marshalSize + 4;  // psi
   marshalSize = marshalSize + 4;  // theta
   marshalSize = marshalSize + 4;  // phi
   marshalSize = marshalSize + 4;  // entityAppearance
   marshalSize = marshalSize + 1;  // deadReckoningAlgorithm
   marshalSize = marshalSize + 15 * 1;  // otherParameters
   marshalSize = marshalSize + 4;  // xAcceleration
   marshalSize = marshalSize + 4;  // yAcceleration
   marshalSize = marshalSize + 4;  // zAcceleration
   marshalSize = marshalSize + 4;  // xAngularVelocity
   marshalSize = marshalSize + 4;  // yAngularVelocity
   marshalSize = marshalSize + 4;  // zAngularVelocity
   marshalSize = marshalSize + 12 * 1;  // marking
   marshalSize = marshalSize + 4;  // capabilities
   for(int idx=0; idx < articulationParameters.size(); idx++)
   {
        ArticulationParameter listElement = (ArticulationParameter)articulationParameters.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setSite(int pSite)
{ site = pSite;
}

public int getSite()
{ return site; 
}

public void setApplication(int pApplication)
{ application = pApplication;
}

public int getApplication()
{ return application; 
}

public void setEntity(int pEntity)
{ entity = pEntity;
}

public int getEntity()
{ return entity; 
}

public void setForceId(short pForceId)
{ forceId = pForceId;
}

public short getForceId()
{ return forceId; 
}

public byte getNumberOfArticulationParameters()
{ return (byte)articulationParameters.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfArticulationParameters method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfArticulationParameters(byte pNumberOfArticulationParameters)
{ numberOfArticulationParameters = pNumberOfArticulationParameters;
}

public void setEntityKind(short pEntityKind)
{ entityKind = pEntityKind;
}

public short getEntityKind()
{ return entityKind; 
}

public void setDomain(short pDomain)
{ domain = pDomain;
}

public short getDomain()
{ return domain; 
}

public void setCountry(int pCountry)
{ country = pCountry;
}

public int getCountry()
{ return country; 
}

public void setCategory(short pCategory)
{ category = pCategory;
}

public short getCategory()
{ return category; 
}

public void setSubcategory(short pSubcategory)
{ subcategory = pSubcategory;
}

public short getSubcategory()
{ return subcategory; 
}

public void setSpecific(short pSpecific)
{ specific = pSpecific;
}

public short getSpecific()
{ return specific; 
}

public void setExtra(short pExtra)
{ extra = pExtra;
}

public short getExtra()
{ return extra; 
}

public void setAltEntityKind(short pAltEntityKind)
{ altEntityKind = pAltEntityKind;
}

public short getAltEntityKind()
{ return altEntityKind; 
}

public void setAltDomain(short pAltDomain)
{ altDomain = pAltDomain;
}

public short getAltDomain()
{ return altDomain; 
}

public void setAltCountry(int pAltCountry)
{ altCountry = pAltCountry;
}

public int getAltCountry()
{ return altCountry; 
}

public void setAltCategory(short pAltCategory)
{ altCategory = pAltCategory;
}

public short getAltCategory()
{ return altCategory; 
}

public void setAltSubcategory(short pAltSubcategory)
{ altSubcategory = pAltSubcategory;
}

public short getAltSubcategory()
{ return altSubcategory; 
}

public void setAltSpecific(short pAltSpecific)
{ altSpecific = pAltSpecific;
}

public short getAltSpecific()
{ return altSpecific; 
}

public void setAltExtra(short pAltExtra)
{ altExtra = pAltExtra;
}

public short getAltExtra()
{ return altExtra; 
}

public void setXVelocity(float pXVelocity)
{ xVelocity = pXVelocity;
}

public float getXVelocity()
{ return xVelocity; 
}

public void setYVelocity(float pYVelocity)
{ yVelocity = pYVelocity;
}

public float getYVelocity()
{ return yVelocity; 
}

public void setZVelocity(float pZVelocity)
{ zVelocity = pZVelocity;
}

public float getZVelocity()
{ return zVelocity; 
}

public void setXLocation(float pXLocation)
{ xLocation = pXLocation;
}

public float getXLocation()
{ return xLocation; 
}

public void setYLocation(float pYLocation)
{ yLocation = pYLocation;
}

public float getYLocation()
{ return yLocation; 
}

public void setZLocation(float pZLocation)
{ zLocation = pZLocation;
}

public float getZLocation()
{ return zLocation; 
}

public void setPsi(float pPsi)
{ psi = pPsi;
}

public float getPsi()
{ return psi; 
}

public void setTheta(float pTheta)
{ theta = pTheta;
}

public float getTheta()
{ return theta; 
}

public void setPhi(float pPhi)
{ phi = pPhi;
}

public float getPhi()
{ return phi; 
}

public void setEntityAppearance(int pEntityAppearance)
{ entityAppearance = pEntityAppearance;
}

public int getEntityAppearance()
{ return entityAppearance; 
}

public void setDeadReckoningAlgorithm(short pDeadReckoningAlgorithm)
{ deadReckoningAlgorithm = pDeadReckoningAlgorithm;
}

public short getDeadReckoningAlgorithm()
{ return deadReckoningAlgorithm; 
}

public void setOtherParameters(byte[] pOtherParameters)
{ otherParameters = pOtherParameters;
}

public byte[] getOtherParameters()
{ return otherParameters; }

public void setXAcceleration(float pXAcceleration)
{ xAcceleration = pXAcceleration;
}

public float getXAcceleration()
{ return xAcceleration; 
}

public void setYAcceleration(float pYAcceleration)
{ yAcceleration = pYAcceleration;
}

public float getYAcceleration()
{ return yAcceleration; 
}

public void setZAcceleration(float pZAcceleration)
{ zAcceleration = pZAcceleration;
}

public float getZAcceleration()
{ return zAcceleration; 
}

public void setXAngularVelocity(float pXAngularVelocity)
{ xAngularVelocity = pXAngularVelocity;
}

public float getXAngularVelocity()
{ return xAngularVelocity; 
}

public void setYAngularVelocity(float pYAngularVelocity)
{ yAngularVelocity = pYAngularVelocity;
}

public float getYAngularVelocity()
{ return yAngularVelocity; 
}

public void setZAngularVelocity(float pZAngularVelocity)
{ zAngularVelocity = pZAngularVelocity;
}

public float getZAngularVelocity()
{ return zAngularVelocity; 
}

public void setMarking(byte[] pMarking)
{ marking = pMarking;
}

public byte[] getMarking()
{ return marking; }

public void setCapabilities(int pCapabilities)
{ capabilities = pCapabilities;
}

public int getCapabilities()
{ return capabilities; 
}

public void setArticulationParameters(List pArticulationParameters)
{ articulationParameters = pArticulationParameters;
}

public List getArticulationParameters()
{ return articulationParameters; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       dos.writeShort( (short)site);
       dos.writeShort( (short)application);
       dos.writeShort( (short)entity);
       dos.writeByte( (byte)forceId);
       dos.writeByte( (byte)articulationParameters.size());
       dos.writeByte( (byte)entityKind);
       dos.writeByte( (byte)domain);
       dos.writeShort( (short)country);
       dos.writeByte( (byte)category);
       dos.writeByte( (byte)subcategory);
       dos.writeByte( (byte)specific);
       dos.writeByte( (byte)extra);
       dos.writeByte( (byte)altEntityKind);
       dos.writeByte( (byte)altDomain);
       dos.writeShort( (short)altCountry);
       dos.writeByte( (byte)altCategory);
       dos.writeByte( (byte)altSubcategory);
       dos.writeByte( (byte)altSpecific);
       dos.writeByte( (byte)altExtra);
       dos.writeFloat( (float)xVelocity);
       dos.writeFloat( (float)yVelocity);
       dos.writeFloat( (float)zVelocity);
       dos.writeFloat( (float)xLocation);
       dos.writeFloat( (float)yLocation);
       dos.writeFloat( (float)zLocation);
       dos.writeFloat( (float)psi);
       dos.writeFloat( (float)theta);
       dos.writeFloat( (float)phi);
       dos.writeInt( (int)entityAppearance);
       dos.writeByte( (byte)deadReckoningAlgorithm);

       for(int idx = 0; idx < otherParameters.length; idx++)
       {
           dos.writeByte(otherParameters[idx]);
       } // end of array marshaling

       dos.writeFloat( (float)xAcceleration);
       dos.writeFloat( (float)yAcceleration);
       dos.writeFloat( (float)zAcceleration);
       dos.writeFloat( (float)xAngularVelocity);
       dos.writeFloat( (float)yAngularVelocity);
       dos.writeFloat( (float)zAngularVelocity);

       for(int idx = 0; idx < marking.length; idx++)
       {
           dos.writeByte(marking[idx]);
       } // end of array marshaling

       dos.writeInt( (int)capabilities);

       for(int idx = 0; idx < articulationParameters.size(); idx++)
       {
            ArticulationParameter aArticulationParameter = (ArticulationParameter)articulationParameters.get(idx);
            aArticulationParameter.marshal(dos);
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
       site = dis.readShort();
       application = dis.readShort();
       entity = dis.readShort();
       forceId = dis.readByte();
       numberOfArticulationParameters = dis.readByte();
       entityKind = dis.readByte();
       domain = dis.readByte();
       country = dis.readShort();
       category = dis.readByte();
       subcategory = dis.readByte();
       specific = dis.readByte();
       extra = dis.readByte();
       altEntityKind = dis.readByte();
       altDomain = dis.readByte();
       altCountry = dis.readShort();
       altCategory = dis.readByte();
       altSubcategory = dis.readByte();
       altSpecific = dis.readByte();
       altExtra = dis.readByte();
       xVelocity = dis.readFloat();
       yVelocity = dis.readFloat();
       zVelocity = dis.readFloat();
       xLocation = dis.readFloat();
       yLocation = dis.readFloat();
       zLocation = dis.readFloat();
       psi = dis.readFloat();
       theta = dis.readFloat();
       phi = dis.readFloat();
       entityAppearance = dis.readInt();
       deadReckoningAlgorithm = dis.readByte();
       for(int idx = 0; idx < otherParameters.length; idx++)
       {
                otherParameters[idx] = dis.readByte();
       } // end of array unmarshaling
       xAcceleration = dis.readFloat();
       yAcceleration = dis.readFloat();
       zAcceleration = dis.readFloat();
       xAngularVelocity = dis.readFloat();
       yAngularVelocity = dis.readFloat();
       zAngularVelocity = dis.readFloat();
       for(int idx = 0; idx < marking.length; idx++)
       {
                marking[idx] = dis.readByte();
       } // end of array unmarshaling
       capabilities = dis.readInt();
        for(int idx = 0; idx < numberOfArticulationParameters; idx++)
        {
           ArticulationParameter anX = new ArticulationParameter();
            anX.unmarshal(dis);
            articulationParameters.add(anX);
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
 public boolean equals(FastEntityStatePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (site == rhs.site)) ivarsEqual = false;
     if( ! (application == rhs.application)) ivarsEqual = false;
     if( ! (entity == rhs.entity)) ivarsEqual = false;
     if( ! (forceId == rhs.forceId)) ivarsEqual = false;
     if( ! (numberOfArticulationParameters == rhs.numberOfArticulationParameters)) ivarsEqual = false;
     if( ! (entityKind == rhs.entityKind)) ivarsEqual = false;
     if( ! (domain == rhs.domain)) ivarsEqual = false;
     if( ! (country == rhs.country)) ivarsEqual = false;
     if( ! (category == rhs.category)) ivarsEqual = false;
     if( ! (subcategory == rhs.subcategory)) ivarsEqual = false;
     if( ! (specific == rhs.specific)) ivarsEqual = false;
     if( ! (extra == rhs.extra)) ivarsEqual = false;
     if( ! (altEntityKind == rhs.altEntityKind)) ivarsEqual = false;
     if( ! (altDomain == rhs.altDomain)) ivarsEqual = false;
     if( ! (altCountry == rhs.altCountry)) ivarsEqual = false;
     if( ! (altCategory == rhs.altCategory)) ivarsEqual = false;
     if( ! (altSubcategory == rhs.altSubcategory)) ivarsEqual = false;
     if( ! (altSpecific == rhs.altSpecific)) ivarsEqual = false;
     if( ! (altExtra == rhs.altExtra)) ivarsEqual = false;
     if( ! (xVelocity == rhs.xVelocity)) ivarsEqual = false;
     if( ! (yVelocity == rhs.yVelocity)) ivarsEqual = false;
     if( ! (zVelocity == rhs.zVelocity)) ivarsEqual = false;
     if( ! (xLocation == rhs.xLocation)) ivarsEqual = false;
     if( ! (yLocation == rhs.yLocation)) ivarsEqual = false;
     if( ! (zLocation == rhs.zLocation)) ivarsEqual = false;
     if( ! (psi == rhs.psi)) ivarsEqual = false;
     if( ! (theta == rhs.theta)) ivarsEqual = false;
     if( ! (phi == rhs.phi)) ivarsEqual = false;
     if( ! (entityAppearance == rhs.entityAppearance)) ivarsEqual = false;
     if( ! (deadReckoningAlgorithm == rhs.deadReckoningAlgorithm)) ivarsEqual = false;

     for(int idx = 0; idx < 15; idx++)
     {
          if(!(otherParameters[idx] == rhs.otherParameters[idx])) ivarsEqual = false;
     }

     if( ! (xAcceleration == rhs.xAcceleration)) ivarsEqual = false;
     if( ! (yAcceleration == rhs.yAcceleration)) ivarsEqual = false;
     if( ! (zAcceleration == rhs.zAcceleration)) ivarsEqual = false;
     if( ! (xAngularVelocity == rhs.xAngularVelocity)) ivarsEqual = false;
     if( ! (yAngularVelocity == rhs.yAngularVelocity)) ivarsEqual = false;
     if( ! (zAngularVelocity == rhs.zAngularVelocity)) ivarsEqual = false;

     for(int idx = 0; idx < 12; idx++)
     {
          if(!(marking[idx] == rhs.marking[idx])) ivarsEqual = false;
     }

     if( ! (capabilities == rhs.capabilities)) ivarsEqual = false;

     for(int idx = 0; idx < articulationParameters.size(); idx++)
     {
        ArticulationParameter x = (ArticulationParameter)articulationParameters.get(idx);
        if( ! ( articulationParameters.get(idx).equals(rhs.articulationParameters.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
