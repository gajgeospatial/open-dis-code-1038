package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Description of one electronic emission beam
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class ElectronicEmissionBeamData extends Object implements Serializable
{
   /** This field shall specify the length of this beams data in 32 bit words */
   protected short  beamDataLength;

   /** This field shall specify a unique emitter database number assigned to differentiate between otherwise similar or identical emitter beams within an emitter system. */
   protected short  beamIDNumber;

   /** This field shall specify a Beam Parameter Index number that shall be used by receiving entities in conjunction with the Emitter Name field to provide a pointer to the stored database parameters required to regenerate the beam.  */
   protected int  beamParameterIndex;

   /** Fundamental parameter data such as frequency range, beam sweep, etc. */
   protected FundamentalParameterData  fundamentalParameterData = new FundamentalParameterData(); 

   /** beam function of a particular beam */
   protected short  beamFunction;

   /** Number of track/jam targets */
   protected short  numberOfTrackJamTargets;

   /** wheher or not the receiving simulation apps can assume all the targets in the scan pattern are being tracked/jammed */
   protected short  highDensityTrackJam;

   /** padding */
   protected short  pad4 = 0;

   /** identify jamming techniques used */
   protected long  jammingModeSequence;

   /** variable length list of track/jam targets */
   protected List trackJamTargets = new ArrayList(); 

/** Constructor */
 public ElectronicEmissionBeamData()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public ElectronicEmissionBeamData(edu.nps.moves.jaxb.dis.ElectronicEmissionBeamData x)
 {
     this.beamDataLength = x.getBeamDataLength();
     this.beamIDNumber = x.getBeamIDNumber();
     this.beamParameterIndex = x.getBeamParameterIndex();

     edu.nps.moves.dis.FundamentalParameterData foo_3;
     if(x.getFundamentalParameterData() == null)
        foo_3 = new edu.nps.moves.dis.FundamentalParameterData();
      else
        foo_3 = new edu.nps.moves.dis.FundamentalParameterData(x.getFundamentalParameterData() );
     this.setFundamentalParameterData(foo_3);

     this.beamFunction = x.getBeamFunction();
     this.numberOfTrackJamTargets = x.getNumberOfTrackJamTargets();
     this.highDensityTrackJam = x.getHighDensityTrackJam();
     this.pad4 = x.getPad4();
     this.jammingModeSequence = x.getJammingModeSequence();
     this.trackJamTargets = new ArrayList();
     for(int idx = 0; idx < x.getTrackJamTargets().size(); idx++)
     {
        this.trackJamTargets.add( new edu.nps.moves.dis.TrackJamTarget((edu.nps.moves.jaxb.dis.TrackJamTarget) x.getTrackJamTargets().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.ElectronicEmissionBeamData initializeJaxbObject(edu.nps.moves.jaxb.dis.ElectronicEmissionBeamData x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setBeamDataLength( this.getBeamDataLength() );
     x.setBeamIDNumber( this.getBeamIDNumber() );
     x.setBeamParameterIndex( this.getBeamParameterIndex() );
     x.setFundamentalParameterData( this.getFundamentalParameterData().initializeJaxbObject(factory.createFundamentalParameterData()) );
     x.setBeamFunction( this.getBeamFunction() );
     x.setNumberOfTrackJamTargets( this.getNumberOfTrackJamTargets() );
     x.setHighDensityTrackJam( this.getHighDensityTrackJam() );
     x.setPad4( this.getPad4() );
     x.setJammingModeSequence( this.getJammingModeSequence() );

     List trackJamTargets_1 = x.getTrackJamTargets();
     for(int idx = 0; idx < trackJamTargets.size(); idx++)
     {
         TrackJamTarget a = (edu.nps.moves.dis.TrackJamTarget)trackJamTargets.get(idx);
         trackJamTargets_1.add(a.initializeJaxbObject(factory.createTrackJamTarget()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 1;  // beamDataLength
   marshalSize = marshalSize + 1;  // beamIDNumber
   marshalSize = marshalSize + 2;  // beamParameterIndex
   marshalSize = marshalSize + fundamentalParameterData.getMarshalledSize();  // fundamentalParameterData
   marshalSize = marshalSize + 1;  // beamFunction
   marshalSize = marshalSize + 1;  // numberOfTrackJamTargets
   marshalSize = marshalSize + 1;  // highDensityTrackJam
   marshalSize = marshalSize + 1;  // pad4
   marshalSize = marshalSize + 4;  // jammingModeSequence
   for(int idx=0; idx < trackJamTargets.size(); idx++)
   {
        TrackJamTarget listElement = (TrackJamTarget)trackJamTargets.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setBeamDataLength(short pBeamDataLength)
{ beamDataLength = pBeamDataLength;
}

public short getBeamDataLength()
{ return beamDataLength; 
}

public void setBeamIDNumber(short pBeamIDNumber)
{ beamIDNumber = pBeamIDNumber;
}

public short getBeamIDNumber()
{ return beamIDNumber; 
}

public void setBeamParameterIndex(int pBeamParameterIndex)
{ beamParameterIndex = pBeamParameterIndex;
}

public int getBeamParameterIndex()
{ return beamParameterIndex; 
}

public void setFundamentalParameterData(FundamentalParameterData pFundamentalParameterData)
{ fundamentalParameterData = pFundamentalParameterData;
}

public FundamentalParameterData getFundamentalParameterData()
{ return fundamentalParameterData; }

public void setBeamFunction(short pBeamFunction)
{ beamFunction = pBeamFunction;
}

public short getBeamFunction()
{ return beamFunction; 
}

public short getNumberOfTrackJamTargets()
{ return (short)trackJamTargets.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfTrackJamTargets method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfTrackJamTargets(short pNumberOfTrackJamTargets)
{ numberOfTrackJamTargets = pNumberOfTrackJamTargets;
}

public void setHighDensityTrackJam(short pHighDensityTrackJam)
{ highDensityTrackJam = pHighDensityTrackJam;
}

public short getHighDensityTrackJam()
{ return highDensityTrackJam; 
}

public void setPad4(short pPad4)
{ pad4 = pPad4;
}

public short getPad4()
{ return pad4; 
}

public void setJammingModeSequence(long pJammingModeSequence)
{ jammingModeSequence = pJammingModeSequence;
}

public long getJammingModeSequence()
{ return jammingModeSequence; 
}

public void setTrackJamTargets(List pTrackJamTargets)
{ trackJamTargets = pTrackJamTargets;
}

public List getTrackJamTargets()
{ return trackJamTargets; }


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeByte( (byte)beamDataLength);
       dos.writeByte( (byte)beamIDNumber);
       dos.writeShort( (short)beamParameterIndex);
       fundamentalParameterData.marshal(dos);
       dos.writeByte( (byte)beamFunction);
       dos.writeByte( (byte)trackJamTargets.size());
       dos.writeByte( (byte)highDensityTrackJam);
       dos.writeByte( (byte)pad4);
       dos.writeInt( (int)jammingModeSequence);

       for(int idx = 0; idx < trackJamTargets.size(); idx++)
       {
            TrackJamTarget aTrackJamTarget = (TrackJamTarget)trackJamTargets.get(idx);
            aTrackJamTarget.marshal(dos);
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
       beamDataLength = dis.readByte();
       beamIDNumber = dis.readByte();
       beamParameterIndex = dis.readShort();
       fundamentalParameterData.unmarshal(dis);
       beamFunction = dis.readByte();
       numberOfTrackJamTargets = dis.readByte();
       highDensityTrackJam = dis.readByte();
       pad4 = dis.readByte();
       jammingModeSequence = dis.readInt();
        for(int idx = 0; idx < numberOfTrackJamTargets; idx++)
        {
           TrackJamTarget anX = new TrackJamTarget();
            anX.unmarshal(dis);
            trackJamTargets.add(anX);
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
 public boolean equals(ElectronicEmissionBeamData rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (beamDataLength == rhs.beamDataLength)) ivarsEqual = false;
     if( ! (beamIDNumber == rhs.beamIDNumber)) ivarsEqual = false;
     if( ! (beamParameterIndex == rhs.beamParameterIndex)) ivarsEqual = false;
     if( ! (fundamentalParameterData.equals( rhs.fundamentalParameterData) )) ivarsEqual = false;
     if( ! (beamFunction == rhs.beamFunction)) ivarsEqual = false;
     if( ! (numberOfTrackJamTargets == rhs.numberOfTrackJamTargets)) ivarsEqual = false;
     if( ! (highDensityTrackJam == rhs.highDensityTrackJam)) ivarsEqual = false;
     if( ! (pad4 == rhs.pad4)) ivarsEqual = false;
     if( ! (jammingModeSequence == rhs.jammingModeSequence)) ivarsEqual = false;

     for(int idx = 0; idx < trackJamTargets.size(); idx++)
     {
        TrackJamTarget x = (TrackJamTarget)trackJamTargets.get(idx);
        if( ! ( trackJamTargets.get(idx).equals(rhs.trackJamTargets.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
