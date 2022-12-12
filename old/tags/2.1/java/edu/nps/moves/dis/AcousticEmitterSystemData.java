package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Used in the UA pdu; ties together an emmitter and a location. This requires manual cleanup; the beam data should not be attached to each emitter system.
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class AcousticEmitterSystemData extends Object implements Serializable
{
   /** Length of emitter system data */
   protected short  emitterSystemDataLength;

   /** Number of beams */
   protected short  numberOfBeams;

   /** padding */
   protected int  pad2;

   /** This field shall specify the system for a particular UA emitter. */
   protected AcousticEmitterSystem  acousticEmitterSystem = new AcousticEmitterSystem(); 

   /** Represents the location wrt the entity */
   protected Vector3Float  emitterLocation = new Vector3Float(); 

   /** For each beam in numberOfBeams, an emitter system. This is not right--the beam records need to be at the end of the PDU, rather than attached to each system. */
   protected List beamRecords = new ArrayList(); 

/** Constructor */
 public AcousticEmitterSystemData()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public AcousticEmitterSystemData(edu.nps.moves.jaxb.dis.AcousticEmitterSystemData x)
 {
     this.emitterSystemDataLength = x.getEmitterSystemDataLength();
     this.numberOfBeams = x.getNumberOfBeams();
     this.pad2 = x.getPad2();

     edu.nps.moves.dis.AcousticEmitterSystem foo_3;
     if(x.getAcousticEmitterSystem() == null)
        foo_3 = new edu.nps.moves.dis.AcousticEmitterSystem();
      else
        foo_3 = new edu.nps.moves.dis.AcousticEmitterSystem(x.getAcousticEmitterSystem() );
     this.setAcousticEmitterSystem(foo_3);


     edu.nps.moves.dis.Vector3Float foo_4;
     if(x.getEmitterLocation() == null)
        foo_4 = new edu.nps.moves.dis.Vector3Float();
      else
        foo_4 = new edu.nps.moves.dis.Vector3Float(x.getEmitterLocation() );
     this.setEmitterLocation(foo_4);

     this.beamRecords = new ArrayList();
     for(int idx = 0; idx < x.getBeamRecords().size(); idx++)
     {
        this.beamRecords.add( new edu.nps.moves.dis.AcousticBeamData((edu.nps.moves.jaxb.dis.AcousticBeamData) x.getBeamRecords().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.AcousticEmitterSystemData initializeJaxbObject(edu.nps.moves.jaxb.dis.AcousticEmitterSystemData x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setEmitterSystemDataLength( this.getEmitterSystemDataLength() );
     x.setNumberOfBeams( this.getNumberOfBeams() );
     x.setPad2( this.getPad2() );
     x.setAcousticEmitterSystem( this.getAcousticEmitterSystem().initializeJaxbObject(factory.createAcousticEmitterSystem()) );
     x.setEmitterLocation( this.getEmitterLocation().initializeJaxbObject(factory.createVector3Float()) );

     List beamRecords_1 = x.getBeamRecords();
     for(int idx = 0; idx < beamRecords.size(); idx++)
     {
         AcousticBeamData a = (edu.nps.moves.dis.AcousticBeamData)beamRecords.get(idx);
         beamRecords_1.add(a.initializeJaxbObject(factory.createAcousticBeamData()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 1;  // emitterSystemDataLength
   marshalSize = marshalSize + 1;  // numberOfBeams
   marshalSize = marshalSize + 2;  // pad2
   marshalSize = marshalSize + acousticEmitterSystem.getMarshalledSize();  // acousticEmitterSystem
   marshalSize = marshalSize + emitterLocation.getMarshalledSize();  // emitterLocation
   for(int idx=0; idx < beamRecords.size(); idx++)
   {
        AcousticBeamData listElement = (AcousticBeamData)beamRecords.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setEmitterSystemDataLength(short pEmitterSystemDataLength)
{ emitterSystemDataLength = pEmitterSystemDataLength;
}

public short getEmitterSystemDataLength()
{ return emitterSystemDataLength; 
}

public short getNumberOfBeams()
{ return (short)beamRecords.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfBeams method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfBeams(short pNumberOfBeams)
{ numberOfBeams = pNumberOfBeams;
}

public void setPad2(int pPad2)
{ pad2 = pPad2;
}

public int getPad2()
{ return pad2; 
}

public void setAcousticEmitterSystem(AcousticEmitterSystem pAcousticEmitterSystem)
{ acousticEmitterSystem = pAcousticEmitterSystem;
}

public AcousticEmitterSystem getAcousticEmitterSystem()
{ return acousticEmitterSystem; }

public void setEmitterLocation(Vector3Float pEmitterLocation)
{ emitterLocation = pEmitterLocation;
}

public Vector3Float getEmitterLocation()
{ return emitterLocation; }

public void setBeamRecords(List pBeamRecords)
{ beamRecords = pBeamRecords;
}

public List getBeamRecords()
{ return beamRecords; }


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeByte( (byte)emitterSystemDataLength);
       dos.writeByte( (byte)beamRecords.size());
       dos.writeShort( (short)pad2);
       acousticEmitterSystem.marshal(dos);
       emitterLocation.marshal(dos);

       for(int idx = 0; idx < beamRecords.size(); idx++)
       {
            AcousticBeamData aAcousticBeamData = (AcousticBeamData)beamRecords.get(idx);
            aAcousticBeamData.marshal(dos);
       } // end of list marshalling

    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       emitterSystemDataLength = dis.readByte();
       numberOfBeams = dis.readByte();
       pad2 = dis.readShort();
       acousticEmitterSystem.unmarshal(dis);
       emitterLocation.unmarshal(dis);
        for(int idx = 0; idx < numberOfBeams; idx++)
        {
           AcousticBeamData anX = new AcousticBeamData();
            anX.unmarshal(dis);
            beamRecords.add(anX);
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
 public boolean equals(AcousticEmitterSystemData rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (emitterSystemDataLength == rhs.emitterSystemDataLength)) ivarsEqual = false;
     if( ! (numberOfBeams == rhs.numberOfBeams)) ivarsEqual = false;
     if( ! (pad2 == rhs.pad2)) ivarsEqual = false;
     if( ! (acousticEmitterSystem.equals( rhs.acousticEmitterSystem) )) ivarsEqual = false;
     if( ! (emitterLocation.equals( rhs.emitterLocation) )) ivarsEqual = false;

     for(int idx = 0; idx < beamRecords.size(); idx++)
     {
        AcousticBeamData x = (AcousticBeamData)beamRecords.get(idx);
        if( ! ( beamRecords.get(idx).equals(rhs.beamRecords.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
