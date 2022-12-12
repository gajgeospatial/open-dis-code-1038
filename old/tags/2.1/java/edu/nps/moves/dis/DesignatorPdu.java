package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.7.2. Handles designating operations. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class DesignatorPdu extends DistributedEmissionsFamilyPdu implements Serializable
{
   /** ID of the entity designating */
   protected EntityID  designatingEntityID = new EntityID(); 

   /** This field shall specify a unique emitter database number assigned to  differentiate between otherwise similar or identical emitter beams within an emitter system. */
   protected int  codeName;

   /** ID of the entity being designated */
   protected EntityID  designatedEntityID = new EntityID(); 

   /** This field shall identify the designator code being used by the designating entity  */
   protected int  designatorCode;

   /** This field shall identify the designator output power in watts */
   protected float  designatorPower;

   /** This field shall identify the designator wavelength in units of microns */
   protected float  designatorWavelength;

   /** designtor spot wrt the designated entity */
   protected Vector3Float  designatorSpotWrtDesignated = new Vector3Float(); 

   /** designtor spot wrt the designated entity */
   protected Vector3Double  designatorSpotLocation = new Vector3Double(); 

   /** Dead reckoning algorithm */
   protected byte  deadReckoningAlgorithm;

   /** padding */
   protected int  padding1;

   /** padding */
   protected byte  padding2;

   /** linear accelleration of entity */
   protected Vector3Float  entityLinearAcceleration = new Vector3Float(); 


/** Constructor */
 public DesignatorPdu()
 {
    setPduType( (short)24 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public DesignatorPdu(edu.nps.moves.jaxb.dis.DesignatorPdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.EntityID foo_0;
     if(x.getDesignatingEntityID() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getDesignatingEntityID() );
     this.setDesignatingEntityID(foo_0);

     this.codeName = x.getCodeName();

     edu.nps.moves.dis.EntityID foo_2;
     if(x.getDesignatedEntityID() == null)
        foo_2 = new edu.nps.moves.dis.EntityID();
      else
        foo_2 = new edu.nps.moves.dis.EntityID(x.getDesignatedEntityID() );
     this.setDesignatedEntityID(foo_2);

     this.designatorCode = x.getDesignatorCode();
     this.designatorPower = x.getDesignatorPower();
     this.designatorWavelength = x.getDesignatorWavelength();

     edu.nps.moves.dis.Vector3Float foo_6;
     if(x.getDesignatorSpotWrtDesignated() == null)
        foo_6 = new edu.nps.moves.dis.Vector3Float();
      else
        foo_6 = new edu.nps.moves.dis.Vector3Float(x.getDesignatorSpotWrtDesignated() );
     this.setDesignatorSpotWrtDesignated(foo_6);


     edu.nps.moves.dis.Vector3Double foo_7;
     if(x.getDesignatorSpotLocation() == null)
        foo_7 = new edu.nps.moves.dis.Vector3Double();
      else
        foo_7 = new edu.nps.moves.dis.Vector3Double(x.getDesignatorSpotLocation() );
     this.setDesignatorSpotLocation(foo_7);

     this.deadReckoningAlgorithm = x.getDeadReckoningAlgorithm();
     this.padding1 = x.getPadding1();
     this.padding2 = x.getPadding2();

     edu.nps.moves.dis.Vector3Float foo_11;
     if(x.getEntityLinearAcceleration() == null)
        foo_11 = new edu.nps.moves.dis.Vector3Float();
      else
        foo_11 = new edu.nps.moves.dis.Vector3Float(x.getEntityLinearAcceleration() );
     this.setEntityLinearAcceleration(foo_11);

 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.DesignatorPdu initializeJaxbObject(edu.nps.moves.jaxb.dis.DesignatorPdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setDesignatingEntityID( this.getDesignatingEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setCodeName( this.getCodeName() );
     x.setDesignatedEntityID( this.getDesignatedEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setDesignatorCode( this.getDesignatorCode() );
     x.setDesignatorPower( this.getDesignatorPower() );
     x.setDesignatorWavelength( this.getDesignatorWavelength() );
     x.setDesignatorSpotWrtDesignated( this.getDesignatorSpotWrtDesignated().initializeJaxbObject(factory.createVector3Float()) );
     x.setDesignatorSpotLocation( this.getDesignatorSpotLocation().initializeJaxbObject(factory.createVector3Double()) );
     x.setDeadReckoningAlgorithm( this.getDeadReckoningAlgorithm() );
     x.setPadding1( this.getPadding1() );
     x.setPadding2( this.getPadding2() );
     x.setEntityLinearAcceleration( this.getEntityLinearAcceleration().initializeJaxbObject(factory.createVector3Float()) );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + designatingEntityID.getMarshalledSize();  // designatingEntityID
   marshalSize = marshalSize + 2;  // codeName
   marshalSize = marshalSize + designatedEntityID.getMarshalledSize();  // designatedEntityID
   marshalSize = marshalSize + 2;  // designatorCode
   marshalSize = marshalSize + 4;  // designatorPower
   marshalSize = marshalSize + 4;  // designatorWavelength
   marshalSize = marshalSize + designatorSpotWrtDesignated.getMarshalledSize();  // designatorSpotWrtDesignated
   marshalSize = marshalSize + designatorSpotLocation.getMarshalledSize();  // designatorSpotLocation
   marshalSize = marshalSize + 1;  // deadReckoningAlgorithm
   marshalSize = marshalSize + 2;  // padding1
   marshalSize = marshalSize + 1;  // padding2
   marshalSize = marshalSize + entityLinearAcceleration.getMarshalledSize();  // entityLinearAcceleration

   return marshalSize;
}


public void setDesignatingEntityID(EntityID pDesignatingEntityID)
{ designatingEntityID = pDesignatingEntityID;
}

public EntityID getDesignatingEntityID()
{ return designatingEntityID; }

public void setCodeName(int pCodeName)
{ codeName = pCodeName;
}

public int getCodeName()
{ return codeName; 
}

public void setDesignatedEntityID(EntityID pDesignatedEntityID)
{ designatedEntityID = pDesignatedEntityID;
}

public EntityID getDesignatedEntityID()
{ return designatedEntityID; }

public void setDesignatorCode(int pDesignatorCode)
{ designatorCode = pDesignatorCode;
}

public int getDesignatorCode()
{ return designatorCode; 
}

public void setDesignatorPower(float pDesignatorPower)
{ designatorPower = pDesignatorPower;
}

public float getDesignatorPower()
{ return designatorPower; 
}

public void setDesignatorWavelength(float pDesignatorWavelength)
{ designatorWavelength = pDesignatorWavelength;
}

public float getDesignatorWavelength()
{ return designatorWavelength; 
}

public void setDesignatorSpotWrtDesignated(Vector3Float pDesignatorSpotWrtDesignated)
{ designatorSpotWrtDesignated = pDesignatorSpotWrtDesignated;
}

public Vector3Float getDesignatorSpotWrtDesignated()
{ return designatorSpotWrtDesignated; }

public void setDesignatorSpotLocation(Vector3Double pDesignatorSpotLocation)
{ designatorSpotLocation = pDesignatorSpotLocation;
}

public Vector3Double getDesignatorSpotLocation()
{ return designatorSpotLocation; }

public void setDeadReckoningAlgorithm(byte pDeadReckoningAlgorithm)
{ deadReckoningAlgorithm = pDeadReckoningAlgorithm;
}

public byte getDeadReckoningAlgorithm()
{ return deadReckoningAlgorithm; 
}

public void setPadding1(int pPadding1)
{ padding1 = pPadding1;
}

public int getPadding1()
{ return padding1; 
}

public void setPadding2(byte pPadding2)
{ padding2 = pPadding2;
}

public byte getPadding2()
{ return padding2; 
}

public void setEntityLinearAcceleration(Vector3Float pEntityLinearAcceleration)
{ entityLinearAcceleration = pEntityLinearAcceleration;
}

public Vector3Float getEntityLinearAcceleration()
{ return entityLinearAcceleration; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       designatingEntityID.marshal(dos);
       dos.writeShort( (short)codeName);
       designatedEntityID.marshal(dos);
       dos.writeShort( (short)designatorCode);
       dos.writeFloat( (float)designatorPower);
       dos.writeFloat( (float)designatorWavelength);
       designatorSpotWrtDesignated.marshal(dos);
       designatorSpotLocation.marshal(dos);
       dos.writeByte( (byte)deadReckoningAlgorithm);
       dos.writeShort( (short)padding1);
       dos.writeByte( (byte)padding2);
       entityLinearAcceleration.marshal(dos);
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
       designatingEntityID.unmarshal(dis);
       codeName = dis.readShort();
       designatedEntityID.unmarshal(dis);
       designatorCode = dis.readShort();
       designatorPower = dis.readFloat();
       designatorWavelength = dis.readFloat();
       designatorSpotWrtDesignated.unmarshal(dis);
       designatorSpotLocation.unmarshal(dis);
       deadReckoningAlgorithm = dis.readByte();
       padding1 = dis.readShort();
       padding2 = dis.readByte();
       entityLinearAcceleration.unmarshal(dis);
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(DesignatorPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (designatingEntityID.equals( rhs.designatingEntityID) )) ivarsEqual = false;
     if( ! (codeName == rhs.codeName)) ivarsEqual = false;
     if( ! (designatedEntityID.equals( rhs.designatedEntityID) )) ivarsEqual = false;
     if( ! (designatorCode == rhs.designatorCode)) ivarsEqual = false;
     if( ! (designatorPower == rhs.designatorPower)) ivarsEqual = false;
     if( ! (designatorWavelength == rhs.designatorWavelength)) ivarsEqual = false;
     if( ! (designatorSpotWrtDesignated.equals( rhs.designatorSpotWrtDesignated) )) ivarsEqual = false;
     if( ! (designatorSpotLocation.equals( rhs.designatorSpotLocation) )) ivarsEqual = false;
     if( ! (deadReckoningAlgorithm == rhs.deadReckoningAlgorithm)) ivarsEqual = false;
     if( ! (padding1 == rhs.padding1)) ivarsEqual = false;
     if( ! (padding2 == rhs.padding2)) ivarsEqual = false;
     if( ! (entityLinearAcceleration.equals( rhs.entityLinearAcceleration) )) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
