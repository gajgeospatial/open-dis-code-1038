package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.2.22. Contains electromagnetic emmision regineratin parameters that are        variable throughout a scenario dependent on the actions of the participants in the simulation. Also provides basic parametric data that may be used to support low-fidelity simulations.
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class FundamentalParameterData extends Object implements Serializable
{
   /** center frequency of the emission in hertz. */
   protected float  frequency;

   /** Bandwidth of the frequencies corresponding to the fequency field. */
   protected float  frequencyRange;

   /** Effective radiated power for the emission in DdBm. For a      radar noise jammer, indicates the peak of the transmitted power. */
   protected float  effectiveRadiatedPower;

   /** Average repetition frequency of the emission in hertz. */
   protected float  pulseRepetitionFrequency;

   /** Average pulse width  of the emission in microseconds. */
   protected float  pulseWidth;

   /** Specifies the beam azimuth an elevation centers and corresponding half-angles     to describe the scan volume */
   protected float  beamAzimuthCenter;

   /** Specifies the beam azimuth sweep to determine scan volume */
   protected float  beamAzimuthSweep;

   /** Specifies the beam elevation center to determine scan volume */
   protected float  beamElevationCenter;

   /** Specifies the beam elevation sweep to determine scan volume */
   protected float  beamElevationSweep;

   /** allows receiver to synchronize its regenerated scan pattern to     that of the emmitter. Specifies the percentage of time a scan is through its pattern from its origion. */
   protected float  beamSweepSync;


/** Constructor */
 public FundamentalParameterData()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public FundamentalParameterData(edu.nps.moves.jaxb.dis.FundamentalParameterData x)
 {
     this.frequency = x.getFrequency();
     this.frequencyRange = x.getFrequencyRange();
     this.effectiveRadiatedPower = x.getEffectiveRadiatedPower();
     this.pulseRepetitionFrequency = x.getPulseRepetitionFrequency();
     this.pulseWidth = x.getPulseWidth();
     this.beamAzimuthCenter = x.getBeamAzimuthCenter();
     this.beamAzimuthSweep = x.getBeamAzimuthSweep();
     this.beamElevationCenter = x.getBeamElevationCenter();
     this.beamElevationSweep = x.getBeamElevationSweep();
     this.beamSweepSync = x.getBeamSweepSync();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.FundamentalParameterData initializeJaxbObject(edu.nps.moves.jaxb.dis.FundamentalParameterData x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setFrequency( this.getFrequency() );
     x.setFrequencyRange( this.getFrequencyRange() );
     x.setEffectiveRadiatedPower( this.getEffectiveRadiatedPower() );
     x.setPulseRepetitionFrequency( this.getPulseRepetitionFrequency() );
     x.setPulseWidth( this.getPulseWidth() );
     x.setBeamAzimuthCenter( this.getBeamAzimuthCenter() );
     x.setBeamAzimuthSweep( this.getBeamAzimuthSweep() );
     x.setBeamElevationCenter( this.getBeamElevationCenter() );
     x.setBeamElevationSweep( this.getBeamElevationSweep() );
     x.setBeamSweepSync( this.getBeamSweepSync() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 4;  // frequency
   marshalSize = marshalSize + 4;  // frequencyRange
   marshalSize = marshalSize + 4;  // effectiveRadiatedPower
   marshalSize = marshalSize + 4;  // pulseRepetitionFrequency
   marshalSize = marshalSize + 4;  // pulseWidth
   marshalSize = marshalSize + 4;  // beamAzimuthCenter
   marshalSize = marshalSize + 4;  // beamAzimuthSweep
   marshalSize = marshalSize + 4;  // beamElevationCenter
   marshalSize = marshalSize + 4;  // beamElevationSweep
   marshalSize = marshalSize + 4;  // beamSweepSync

   return marshalSize;
}


public void setFrequency(float pFrequency)
{ frequency = pFrequency;
}

public float getFrequency()
{ return frequency; 
}

public void setFrequencyRange(float pFrequencyRange)
{ frequencyRange = pFrequencyRange;
}

public float getFrequencyRange()
{ return frequencyRange; 
}

public void setEffectiveRadiatedPower(float pEffectiveRadiatedPower)
{ effectiveRadiatedPower = pEffectiveRadiatedPower;
}

public float getEffectiveRadiatedPower()
{ return effectiveRadiatedPower; 
}

public void setPulseRepetitionFrequency(float pPulseRepetitionFrequency)
{ pulseRepetitionFrequency = pPulseRepetitionFrequency;
}

public float getPulseRepetitionFrequency()
{ return pulseRepetitionFrequency; 
}

public void setPulseWidth(float pPulseWidth)
{ pulseWidth = pPulseWidth;
}

public float getPulseWidth()
{ return pulseWidth; 
}

public void setBeamAzimuthCenter(float pBeamAzimuthCenter)
{ beamAzimuthCenter = pBeamAzimuthCenter;
}

public float getBeamAzimuthCenter()
{ return beamAzimuthCenter; 
}

public void setBeamAzimuthSweep(float pBeamAzimuthSweep)
{ beamAzimuthSweep = pBeamAzimuthSweep;
}

public float getBeamAzimuthSweep()
{ return beamAzimuthSweep; 
}

public void setBeamElevationCenter(float pBeamElevationCenter)
{ beamElevationCenter = pBeamElevationCenter;
}

public float getBeamElevationCenter()
{ return beamElevationCenter; 
}

public void setBeamElevationSweep(float pBeamElevationSweep)
{ beamElevationSweep = pBeamElevationSweep;
}

public float getBeamElevationSweep()
{ return beamElevationSweep; 
}

public void setBeamSweepSync(float pBeamSweepSync)
{ beamSweepSync = pBeamSweepSync;
}

public float getBeamSweepSync()
{ return beamSweepSync; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeFloat( (float)frequency);
       dos.writeFloat( (float)frequencyRange);
       dos.writeFloat( (float)effectiveRadiatedPower);
       dos.writeFloat( (float)pulseRepetitionFrequency);
       dos.writeFloat( (float)pulseWidth);
       dos.writeFloat( (float)beamAzimuthCenter);
       dos.writeFloat( (float)beamAzimuthSweep);
       dos.writeFloat( (float)beamElevationCenter);
       dos.writeFloat( (float)beamElevationSweep);
       dos.writeFloat( (float)beamSweepSync);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       frequency = dis.readFloat();
       frequencyRange = dis.readFloat();
       effectiveRadiatedPower = dis.readFloat();
       pulseRepetitionFrequency = dis.readFloat();
       pulseWidth = dis.readFloat();
       beamAzimuthCenter = dis.readFloat();
       beamAzimuthSweep = dis.readFloat();
       beamElevationCenter = dis.readFloat();
       beamElevationSweep = dis.readFloat();
       beamSweepSync = dis.readFloat();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(FundamentalParameterData rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (frequency == rhs.frequency)) ivarsEqual = false;
     if( ! (frequencyRange == rhs.frequencyRange)) ivarsEqual = false;
     if( ! (effectiveRadiatedPower == rhs.effectiveRadiatedPower)) ivarsEqual = false;
     if( ! (pulseRepetitionFrequency == rhs.pulseRepetitionFrequency)) ivarsEqual = false;
     if( ! (pulseWidth == rhs.pulseWidth)) ivarsEqual = false;
     if( ! (beamAzimuthCenter == rhs.beamAzimuthCenter)) ivarsEqual = false;
     if( ! (beamAzimuthSweep == rhs.beamAzimuthSweep)) ivarsEqual = false;
     if( ! (beamElevationCenter == rhs.beamElevationCenter)) ivarsEqual = false;
     if( ! (beamElevationSweep == rhs.beamElevationSweep)) ivarsEqual = false;
     if( ! (beamSweepSync == rhs.beamSweepSync)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
