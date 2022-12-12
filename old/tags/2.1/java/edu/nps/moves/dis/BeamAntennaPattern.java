package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.2.4.2. Used when the antenna pattern type field has a value of 1. Specifies           the direction, patter, and polarization of radiation from an antenna.
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class BeamAntennaPattern extends Object implements Serializable
{
   /** The rotation that transformst he reference coordinate sytem     into the beam coordinate system. Either world coordinates or entity coordinates may be used as the     reference coordinate system, as specified by teh reference system field of the antenna pattern record. */
   protected Orientation  beamDirection = new Orientation(); 

   protected float  azimuthBeamwidth = 0;

   protected float  referenceSystem = 0;

   protected short  padding1 = 0;

   protected byte  padding2 = 0;

   /** Magnigute of the z-component in beam coordinates at some arbitrary      single point in the mainbeam      and in the far field of the antenna. */
   protected float  ez;

   /** Magnigute of the x-component in beam coordinates at some arbitrary      single point in the mainbeam      and in the far field of the antenna. */
   protected float  ex;

   /** THe phase angle between Ez and Ex in radians. */
   protected float  phase;


/** Constructor */
 public BeamAntennaPattern()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public BeamAntennaPattern(edu.nps.moves.jaxb.dis.BeamAntennaPattern x)
 {

     edu.nps.moves.dis.Orientation foo_0;
     if(x.getBeamDirection() == null)
        foo_0 = new edu.nps.moves.dis.Orientation();
      else
        foo_0 = new edu.nps.moves.dis.Orientation(x.getBeamDirection() );
     this.setBeamDirection(foo_0);

     this.azimuthBeamwidth = x.getAzimuthBeamwidth();
     this.referenceSystem = x.getReferenceSystem();
     this.padding1 = x.getPadding1();
     this.padding2 = x.getPadding2();
     this.ez = x.getEz();
     this.ex = x.getEx();
     this.phase = x.getPhase();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.BeamAntennaPattern initializeJaxbObject(edu.nps.moves.jaxb.dis.BeamAntennaPattern x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setBeamDirection( this.getBeamDirection().initializeJaxbObject(factory.createOrientation()) );
     x.setAzimuthBeamwidth( this.getAzimuthBeamwidth() );
     x.setReferenceSystem( this.getReferenceSystem() );
     x.setPadding1( this.getPadding1() );
     x.setPadding2( this.getPadding2() );
     x.setEz( this.getEz() );
     x.setEx( this.getEx() );
     x.setPhase( this.getPhase() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + beamDirection.getMarshalledSize();  // beamDirection
   marshalSize = marshalSize + 4;  // azimuthBeamwidth
   marshalSize = marshalSize + 4;  // referenceSystem
   marshalSize = marshalSize + 2;  // padding1
   marshalSize = marshalSize + 1;  // padding2
   marshalSize = marshalSize + 4;  // ez
   marshalSize = marshalSize + 4;  // ex
   marshalSize = marshalSize + 4;  // phase

   return marshalSize;
}


public void setBeamDirection(Orientation pBeamDirection)
{ beamDirection = pBeamDirection;
}

public Orientation getBeamDirection()
{ return beamDirection; }

public void setAzimuthBeamwidth(float pAzimuthBeamwidth)
{ azimuthBeamwidth = pAzimuthBeamwidth;
}

public float getAzimuthBeamwidth()
{ return azimuthBeamwidth; 
}

public void setReferenceSystem(float pReferenceSystem)
{ referenceSystem = pReferenceSystem;
}

public float getReferenceSystem()
{ return referenceSystem; 
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

public void setEz(float pEz)
{ ez = pEz;
}

public float getEz()
{ return ez; 
}

public void setEx(float pEx)
{ ex = pEx;
}

public float getEx()
{ return ex; 
}

public void setPhase(float pPhase)
{ phase = pPhase;
}

public float getPhase()
{ return phase; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       beamDirection.marshal(dos);
       dos.writeFloat( (float)azimuthBeamwidth);
       dos.writeFloat( (float)referenceSystem);
       dos.writeShort( (short)padding1);
       dos.writeByte( (byte)padding2);
       dos.writeFloat( (float)ez);
       dos.writeFloat( (float)ex);
       dos.writeFloat( (float)phase);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       beamDirection.unmarshal(dis);
       azimuthBeamwidth = dis.readFloat();
       referenceSystem = dis.readFloat();
       padding1 = dis.readShort();
       padding2 = dis.readByte();
       ez = dis.readFloat();
       ex = dis.readFloat();
       phase = dis.readFloat();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(BeamAntennaPattern rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (beamDirection.equals( rhs.beamDirection) )) ivarsEqual = false;
     if( ! (azimuthBeamwidth == rhs.azimuthBeamwidth)) ivarsEqual = false;
     if( ! (referenceSystem == rhs.referenceSystem)) ivarsEqual = false;
     if( ! (padding1 == rhs.padding1)) ivarsEqual = false;
     if( ! (padding2 == rhs.padding2)) ivarsEqual = false;
     if( ! (ez == rhs.ez)) ivarsEqual = false;
     if( ! (ex == rhs.ex)) ivarsEqual = false;
     if( ! (phase == rhs.phase)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
