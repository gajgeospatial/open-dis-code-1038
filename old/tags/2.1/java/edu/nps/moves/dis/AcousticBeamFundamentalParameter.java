package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Used in UaPdu
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class AcousticBeamFundamentalParameter extends Object implements Serializable
{
   /** parameter index */
   protected int  activeEmissionParameterIndex;

   /** scan pattern */
   protected int  scanPattern;

   /** beam center azimuth */
   protected float  beamCenterAzimuth;

   /** azimuthal beamwidth */
   protected float  azimuthalBeamwidth;

   /** beam center */
   protected float  beamCenterDE;

   /** DE beamwidth (vertical beamwidth) */
   protected float  deBeamwidth;


/** Constructor */
 public AcousticBeamFundamentalParameter()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public AcousticBeamFundamentalParameter(edu.nps.moves.jaxb.dis.AcousticBeamFundamentalParameter x)
 {
     this.activeEmissionParameterIndex = x.getActiveEmissionParameterIndex();
     this.scanPattern = x.getScanPattern();
     this.beamCenterAzimuth = x.getBeamCenterAzimuth();
     this.azimuthalBeamwidth = x.getAzimuthalBeamwidth();
     this.beamCenterDE = x.getBeamCenterDE();
     this.deBeamwidth = x.getDeBeamwidth();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.AcousticBeamFundamentalParameter initializeJaxbObject(edu.nps.moves.jaxb.dis.AcousticBeamFundamentalParameter x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setActiveEmissionParameterIndex( this.getActiveEmissionParameterIndex() );
     x.setScanPattern( this.getScanPattern() );
     x.setBeamCenterAzimuth( this.getBeamCenterAzimuth() );
     x.setAzimuthalBeamwidth( this.getAzimuthalBeamwidth() );
     x.setBeamCenterDE( this.getBeamCenterDE() );
     x.setDeBeamwidth( this.getDeBeamwidth() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 2;  // activeEmissionParameterIndex
   marshalSize = marshalSize + 2;  // scanPattern
   marshalSize = marshalSize + 4;  // beamCenterAzimuth
   marshalSize = marshalSize + 4;  // azimuthalBeamwidth
   marshalSize = marshalSize + 4;  // beamCenterDE
   marshalSize = marshalSize + 4;  // deBeamwidth

   return marshalSize;
}


public void setActiveEmissionParameterIndex(int pActiveEmissionParameterIndex)
{ activeEmissionParameterIndex = pActiveEmissionParameterIndex;
}

public int getActiveEmissionParameterIndex()
{ return activeEmissionParameterIndex; 
}

public void setScanPattern(int pScanPattern)
{ scanPattern = pScanPattern;
}

public int getScanPattern()
{ return scanPattern; 
}

public void setBeamCenterAzimuth(float pBeamCenterAzimuth)
{ beamCenterAzimuth = pBeamCenterAzimuth;
}

public float getBeamCenterAzimuth()
{ return beamCenterAzimuth; 
}

public void setAzimuthalBeamwidth(float pAzimuthalBeamwidth)
{ azimuthalBeamwidth = pAzimuthalBeamwidth;
}

public float getAzimuthalBeamwidth()
{ return azimuthalBeamwidth; 
}

public void setBeamCenterDE(float pBeamCenterDE)
{ beamCenterDE = pBeamCenterDE;
}

public float getBeamCenterDE()
{ return beamCenterDE; 
}

public void setDeBeamwidth(float pDeBeamwidth)
{ deBeamwidth = pDeBeamwidth;
}

public float getDeBeamwidth()
{ return deBeamwidth; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeShort( (short)activeEmissionParameterIndex);
       dos.writeShort( (short)scanPattern);
       dos.writeFloat( (float)beamCenterAzimuth);
       dos.writeFloat( (float)azimuthalBeamwidth);
       dos.writeFloat( (float)beamCenterDE);
       dos.writeFloat( (float)deBeamwidth);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       activeEmissionParameterIndex = dis.readShort();
       scanPattern = dis.readShort();
       beamCenterAzimuth = dis.readFloat();
       azimuthalBeamwidth = dis.readFloat();
       beamCenterDE = dis.readFloat();
       deBeamwidth = dis.readFloat();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(AcousticBeamFundamentalParameter rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (activeEmissionParameterIndex == rhs.activeEmissionParameterIndex)) ivarsEqual = false;
     if( ! (scanPattern == rhs.scanPattern)) ivarsEqual = false;
     if( ! (beamCenterAzimuth == rhs.beamCenterAzimuth)) ivarsEqual = false;
     if( ! (azimuthalBeamwidth == rhs.azimuthalBeamwidth)) ivarsEqual = false;
     if( ! (beamCenterDE == rhs.beamCenterDE)) ivarsEqual = false;
     if( ! (deBeamwidth == rhs.deBeamwidth)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
