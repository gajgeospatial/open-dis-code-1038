package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * One track/jam target
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class TrackJamTarget extends Object implements Serializable
{
   /** track/jam target */
   protected EntityID  trackJam = new EntityID(); 

   /** Emitter ID */
   protected short  emitterID;

   /** beam ID */
   protected short  beamID;


/** Constructor */
 public TrackJamTarget()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public TrackJamTarget(edu.nps.moves.jaxb.dis.TrackJamTarget x)
 {

     edu.nps.moves.dis.EntityID foo_0;
     if(x.getTrackJam() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getTrackJam() );
     this.setTrackJam(foo_0);

     this.emitterID = x.getEmitterID();
     this.beamID = x.getBeamID();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.TrackJamTarget initializeJaxbObject(edu.nps.moves.jaxb.dis.TrackJamTarget x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setTrackJam( this.getTrackJam().initializeJaxbObject(factory.createEntityID()) );
     x.setEmitterID( this.getEmitterID() );
     x.setBeamID( this.getBeamID() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + trackJam.getMarshalledSize();  // trackJam
   marshalSize = marshalSize + 1;  // emitterID
   marshalSize = marshalSize + 1;  // beamID

   return marshalSize;
}


public void setTrackJam(EntityID pTrackJam)
{ trackJam = pTrackJam;
}

public EntityID getTrackJam()
{ return trackJam; }

public void setEmitterID(short pEmitterID)
{ emitterID = pEmitterID;
}

public short getEmitterID()
{ return emitterID; 
}

public void setBeamID(short pBeamID)
{ beamID = pBeamID;
}

public short getBeamID()
{ return beamID; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       trackJam.marshal(dos);
       dos.writeByte( (byte)emitterID);
       dos.writeByte( (byte)beamID);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       trackJam.unmarshal(dis);
       emitterID = dis.readByte();
       beamID = dis.readByte();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(TrackJamTarget rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (trackJam.equals( rhs.trackJam) )) ivarsEqual = false;
     if( ! (emitterID == rhs.emitterID)) ivarsEqual = false;
     if( ! (beamID == rhs.beamID)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
