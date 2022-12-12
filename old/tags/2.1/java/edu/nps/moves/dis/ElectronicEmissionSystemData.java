package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Data about one electronic system
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class ElectronicEmissionSystemData extends Object implements Serializable
{
   /** This field shall specify the length of this emitter system?s data (including beam data and its track/jam information) in 32-bit words. The length shall include the System Data Length field.  */
   protected short  systemDataLength;

   /** This field shall specify the number of beams being described in the current PDU for the system being described.  */
   protected short  numberOfBeams;

   /** padding. */
   protected int  emissionsPadding2 = 0;

   /** This field shall specify information about a particular emitter system */
   protected EmitterSystem  emitterSystem = new EmitterSystem(); 

   /** Location with respect to the entity */
   protected Vector3Float  location = new Vector3Float(); 

   /** variable length list of beam data records */
   protected List beamDataRecords = new ArrayList(); 

/** Constructor */
 public ElectronicEmissionSystemData()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public ElectronicEmissionSystemData(edu.nps.moves.jaxb.dis.ElectronicEmissionSystemData x)
 {
     this.systemDataLength = x.getSystemDataLength();
     this.numberOfBeams = x.getNumberOfBeams();
     this.emissionsPadding2 = x.getEmissionsPadding2();

     edu.nps.moves.dis.EmitterSystem foo_3;
     if(x.getEmitterSystem() == null)
        foo_3 = new edu.nps.moves.dis.EmitterSystem();
      else
        foo_3 = new edu.nps.moves.dis.EmitterSystem(x.getEmitterSystem() );
     this.setEmitterSystem(foo_3);


     edu.nps.moves.dis.Vector3Float foo_4;
     if(x.getLocation() == null)
        foo_4 = new edu.nps.moves.dis.Vector3Float();
      else
        foo_4 = new edu.nps.moves.dis.Vector3Float(x.getLocation() );
     this.setLocation(foo_4);

     this.beamDataRecords = new ArrayList();
     for(int idx = 0; idx < x.getBeamDataRecords().size(); idx++)
     {
        this.beamDataRecords.add( new edu.nps.moves.dis.ElectronicEmissionBeamData((edu.nps.moves.jaxb.dis.ElectronicEmissionBeamData) x.getBeamDataRecords().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.ElectronicEmissionSystemData initializeJaxbObject(edu.nps.moves.jaxb.dis.ElectronicEmissionSystemData x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setSystemDataLength( this.getSystemDataLength() );
     x.setNumberOfBeams( this.getNumberOfBeams() );
     x.setEmissionsPadding2( this.getEmissionsPadding2() );
     x.setEmitterSystem( this.getEmitterSystem().initializeJaxbObject(factory.createEmitterSystem()) );
     x.setLocation( this.getLocation().initializeJaxbObject(factory.createVector3Float()) );

     List beamDataRecords_1 = x.getBeamDataRecords();
     for(int idx = 0; idx < beamDataRecords.size(); idx++)
     {
         ElectronicEmissionBeamData a = (edu.nps.moves.dis.ElectronicEmissionBeamData)beamDataRecords.get(idx);
         beamDataRecords_1.add(a.initializeJaxbObject(factory.createElectronicEmissionBeamData()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 1;  // systemDataLength
   marshalSize = marshalSize + 1;  // numberOfBeams
   marshalSize = marshalSize + 2;  // emissionsPadding2
   marshalSize = marshalSize + emitterSystem.getMarshalledSize();  // emitterSystem
   marshalSize = marshalSize + location.getMarshalledSize();  // location
   for(int idx=0; idx < beamDataRecords.size(); idx++)
   {
        ElectronicEmissionBeamData listElement = (ElectronicEmissionBeamData)beamDataRecords.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setSystemDataLength(short pSystemDataLength)
{ systemDataLength = pSystemDataLength;
}

public short getSystemDataLength()
{ return systemDataLength; 
}

public short getNumberOfBeams()
{ return (short)beamDataRecords.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfBeams method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfBeams(short pNumberOfBeams)
{ numberOfBeams = pNumberOfBeams;
}

public void setEmissionsPadding2(int pEmissionsPadding2)
{ emissionsPadding2 = pEmissionsPadding2;
}

public int getEmissionsPadding2()
{ return emissionsPadding2; 
}

public void setEmitterSystem(EmitterSystem pEmitterSystem)
{ emitterSystem = pEmitterSystem;
}

public EmitterSystem getEmitterSystem()
{ return emitterSystem; }

public void setLocation(Vector3Float pLocation)
{ location = pLocation;
}

public Vector3Float getLocation()
{ return location; }

public void setBeamDataRecords(List pBeamDataRecords)
{ beamDataRecords = pBeamDataRecords;
}

public List getBeamDataRecords()
{ return beamDataRecords; }


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeByte( (byte)systemDataLength);
       dos.writeByte( (byte)beamDataRecords.size());
       dos.writeShort( (short)emissionsPadding2);
       emitterSystem.marshal(dos);
       location.marshal(dos);

       for(int idx = 0; idx < beamDataRecords.size(); idx++)
       {
            ElectronicEmissionBeamData aElectronicEmissionBeamData = (ElectronicEmissionBeamData)beamDataRecords.get(idx);
            aElectronicEmissionBeamData.marshal(dos);
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
       systemDataLength = dis.readByte();
       numberOfBeams = dis.readByte();
       emissionsPadding2 = dis.readShort();
       emitterSystem.unmarshal(dis);
       location.unmarshal(dis);
        for(int idx = 0; idx < numberOfBeams; idx++)
        {
           ElectronicEmissionBeamData anX = new ElectronicEmissionBeamData();
            anX.unmarshal(dis);
            beamDataRecords.add(anX);
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
 public boolean equals(ElectronicEmissionSystemData rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (systemDataLength == rhs.systemDataLength)) ivarsEqual = false;
     if( ! (numberOfBeams == rhs.numberOfBeams)) ivarsEqual = false;
     if( ! (emissionsPadding2 == rhs.emissionsPadding2)) ivarsEqual = false;
     if( ! (emitterSystem.equals( rhs.emitterSystem) )) ivarsEqual = false;
     if( ! (location.equals( rhs.location) )) ivarsEqual = false;

     for(int idx = 0; idx < beamDataRecords.size(); idx++)
     {
        ElectronicEmissionBeamData x = (ElectronicEmissionBeamData)beamDataRecords.get(idx);
        if( ! ( beamDataRecords.get(idx).equals(rhs.beamDataRecords.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
