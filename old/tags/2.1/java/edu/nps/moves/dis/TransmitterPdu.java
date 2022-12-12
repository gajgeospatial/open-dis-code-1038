package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.8.1. Detailed information about a radio transmitter. This PDU requires manually         written code to complete, since the modulation parameters are of variable length. UNFINISHED
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class TransmitterPdu extends RadioCommunicationsFamilyPdu implements Serializable
{
   /** linear accelleration of entity */
   protected RadioEntityType  radioEntityType = new RadioEntityType(); 

   /** transmit state */
   protected short  transmitState;

   /** input source */
   protected short  inputSource;

   /** padding */
   protected int  padding1;

   /** Location of antenna */
   protected Vector3Double  antennaLocation = new Vector3Double(); 

   /** relative location of antenna */
   protected Vector3Double  relativeAntennaLocation = new Vector3Double(); 

   /** antenna pattern type */
   protected int  antennaPatternType;

   /** atenna pattern length */
   protected int  antennaPatternCount;

   /** frequency */
   protected double  frequency;

   /** transmit frequency Bandwidth */
   protected float  transmitFrequencyBandwidth;

   /** transmission power */
   protected float  power;

   /** modulation */
   protected ModulationType  modulationType = new ModulationType(); 

   /** crypto system enumeration */
   protected int  cryptoSystem;

   /** crypto system key identifer */
   protected int  cryptoKeyId;

   /** how many modulation parameters we have */
   protected short  modulationParameterCount;

   /** padding2 */
   protected int  padding2 = 0;

   /** padding3 */
   protected short  padding3 = 0;

   /** variable length list of modulation parameters */
   protected List modulationParametersList = new ArrayList(); 
   /** variable length list of antenna pattern records */
   protected List antennaPatternList = new ArrayList(); 

/** Constructor */
 public TransmitterPdu()
 {
    setPduType( (short)25 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public TransmitterPdu(edu.nps.moves.jaxb.dis.TransmitterPdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.RadioEntityType foo_0;
     if(x.getRadioEntityType() == null)
        foo_0 = new edu.nps.moves.dis.RadioEntityType();
      else
        foo_0 = new edu.nps.moves.dis.RadioEntityType(x.getRadioEntityType() );
     this.setRadioEntityType(foo_0);

     this.transmitState = x.getTransmitState();
     this.inputSource = x.getInputSource();
     this.padding1 = x.getPadding1();

     edu.nps.moves.dis.Vector3Double foo_4;
     if(x.getAntennaLocation() == null)
        foo_4 = new edu.nps.moves.dis.Vector3Double();
      else
        foo_4 = new edu.nps.moves.dis.Vector3Double(x.getAntennaLocation() );
     this.setAntennaLocation(foo_4);


     edu.nps.moves.dis.Vector3Double foo_5;
     if(x.getRelativeAntennaLocation() == null)
        foo_5 = new edu.nps.moves.dis.Vector3Double();
      else
        foo_5 = new edu.nps.moves.dis.Vector3Double(x.getRelativeAntennaLocation() );
     this.setRelativeAntennaLocation(foo_5);

     this.antennaPatternType = x.getAntennaPatternType();
     this.antennaPatternCount = x.getAntennaPatternCount();
     this.frequency = x.getFrequency();
     this.transmitFrequencyBandwidth = x.getTransmitFrequencyBandwidth();
     this.power = x.getPower();

     edu.nps.moves.dis.ModulationType foo_11;
     if(x.getModulationType() == null)
        foo_11 = new edu.nps.moves.dis.ModulationType();
      else
        foo_11 = new edu.nps.moves.dis.ModulationType(x.getModulationType() );
     this.setModulationType(foo_11);

     this.cryptoSystem = x.getCryptoSystem();
     this.cryptoKeyId = x.getCryptoKeyId();
     this.modulationParameterCount = x.getModulationParameterCount();
     this.padding2 = x.getPadding2();
     this.padding3 = x.getPadding3();
     this.modulationParametersList = new ArrayList();
     for(int idx = 0; idx < x.getModulationParametersList().size(); idx++)
     {
        this.modulationParametersList.add( new edu.nps.moves.dis.Vector3Float((edu.nps.moves.jaxb.dis.Vector3Float) x.getModulationParametersList().get(idx)));
     }
     this.antennaPatternList = new ArrayList();
     for(int idx = 0; idx < x.getAntennaPatternList().size(); idx++)
     {
        this.antennaPatternList.add( new edu.nps.moves.dis.Vector3Float((edu.nps.moves.jaxb.dis.Vector3Float) x.getAntennaPatternList().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.TransmitterPdu initializeJaxbObject(edu.nps.moves.jaxb.dis.TransmitterPdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setRadioEntityType( this.getRadioEntityType().initializeJaxbObject(factory.createRadioEntityType()) );
     x.setTransmitState( this.getTransmitState() );
     x.setInputSource( this.getInputSource() );
     x.setPadding1( this.getPadding1() );
     x.setAntennaLocation( this.getAntennaLocation().initializeJaxbObject(factory.createVector3Double()) );
     x.setRelativeAntennaLocation( this.getRelativeAntennaLocation().initializeJaxbObject(factory.createVector3Double()) );
     x.setAntennaPatternType( this.getAntennaPatternType() );
     x.setAntennaPatternCount( this.getAntennaPatternCount() );
     x.setFrequency( this.getFrequency() );
     x.setTransmitFrequencyBandwidth( this.getTransmitFrequencyBandwidth() );
     x.setPower( this.getPower() );
     x.setModulationType( this.getModulationType().initializeJaxbObject(factory.createModulationType()) );
     x.setCryptoSystem( this.getCryptoSystem() );
     x.setCryptoKeyId( this.getCryptoKeyId() );
     x.setModulationParameterCount( this.getModulationParameterCount() );
     x.setPadding2( this.getPadding2() );
     x.setPadding3( this.getPadding3() );

     List modulationParametersList_1 = x.getModulationParametersList();
     for(int idx = 0; idx < modulationParametersList.size(); idx++)
     {
         Vector3Float a = (edu.nps.moves.dis.Vector3Float)modulationParametersList.get(idx);
         modulationParametersList_1.add(a.initializeJaxbObject(factory.createVector3Float()));
     }

     List antennaPatternList_1 = x.getAntennaPatternList();
     for(int idx = 0; idx < antennaPatternList.size(); idx++)
     {
         Vector3Float a = (edu.nps.moves.dis.Vector3Float)antennaPatternList.get(idx);
         antennaPatternList_1.add(a.initializeJaxbObject(factory.createVector3Float()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + radioEntityType.getMarshalledSize();  // radioEntityType
   marshalSize = marshalSize + 1;  // transmitState
   marshalSize = marshalSize + 1;  // inputSource
   marshalSize = marshalSize + 2;  // padding1
   marshalSize = marshalSize + antennaLocation.getMarshalledSize();  // antennaLocation
   marshalSize = marshalSize + relativeAntennaLocation.getMarshalledSize();  // relativeAntennaLocation
   marshalSize = marshalSize + 2;  // antennaPatternType
   marshalSize = marshalSize + 2;  // antennaPatternCount
   marshalSize = marshalSize + 8;  // frequency
   marshalSize = marshalSize + 4;  // transmitFrequencyBandwidth
   marshalSize = marshalSize + 4;  // power
   marshalSize = marshalSize + modulationType.getMarshalledSize();  // modulationType
   marshalSize = marshalSize + 2;  // cryptoSystem
   marshalSize = marshalSize + 2;  // cryptoKeyId
   marshalSize = marshalSize + 1;  // modulationParameterCount
   marshalSize = marshalSize + 2;  // padding2
   marshalSize = marshalSize + 1;  // padding3
   for(int idx=0; idx < modulationParametersList.size(); idx++)
   {
        Vector3Float listElement = (Vector3Float)modulationParametersList.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }
   for(int idx=0; idx < antennaPatternList.size(); idx++)
   {
        Vector3Float listElement = (Vector3Float)antennaPatternList.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setRadioEntityType(RadioEntityType pRadioEntityType)
{ radioEntityType = pRadioEntityType;
}

public RadioEntityType getRadioEntityType()
{ return radioEntityType; }

public void setTransmitState(short pTransmitState)
{ transmitState = pTransmitState;
}

public short getTransmitState()
{ return transmitState; 
}

public void setInputSource(short pInputSource)
{ inputSource = pInputSource;
}

public short getInputSource()
{ return inputSource; 
}

public void setPadding1(int pPadding1)
{ padding1 = pPadding1;
}

public int getPadding1()
{ return padding1; 
}

public void setAntennaLocation(Vector3Double pAntennaLocation)
{ antennaLocation = pAntennaLocation;
}

public Vector3Double getAntennaLocation()
{ return antennaLocation; }

public void setRelativeAntennaLocation(Vector3Double pRelativeAntennaLocation)
{ relativeAntennaLocation = pRelativeAntennaLocation;
}

public Vector3Double getRelativeAntennaLocation()
{ return relativeAntennaLocation; }

public void setAntennaPatternType(int pAntennaPatternType)
{ antennaPatternType = pAntennaPatternType;
}

public int getAntennaPatternType()
{ return antennaPatternType; 
}

public int getAntennaPatternCount()
{ return (int)antennaPatternList.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getantennaPatternCount method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setAntennaPatternCount(int pAntennaPatternCount)
{ antennaPatternCount = pAntennaPatternCount;
}

public void setFrequency(double pFrequency)
{ frequency = pFrequency;
}

public double getFrequency()
{ return frequency; 
}

public void setTransmitFrequencyBandwidth(float pTransmitFrequencyBandwidth)
{ transmitFrequencyBandwidth = pTransmitFrequencyBandwidth;
}

public float getTransmitFrequencyBandwidth()
{ return transmitFrequencyBandwidth; 
}

public void setPower(float pPower)
{ power = pPower;
}

public float getPower()
{ return power; 
}

public void setModulationType(ModulationType pModulationType)
{ modulationType = pModulationType;
}

public ModulationType getModulationType()
{ return modulationType; }

public void setCryptoSystem(int pCryptoSystem)
{ cryptoSystem = pCryptoSystem;
}

public int getCryptoSystem()
{ return cryptoSystem; 
}

public void setCryptoKeyId(int pCryptoKeyId)
{ cryptoKeyId = pCryptoKeyId;
}

public int getCryptoKeyId()
{ return cryptoKeyId; 
}

public short getModulationParameterCount()
{ return (short)modulationParametersList.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getmodulationParameterCount method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setModulationParameterCount(short pModulationParameterCount)
{ modulationParameterCount = pModulationParameterCount;
}

public void setPadding2(int pPadding2)
{ padding2 = pPadding2;
}

public int getPadding2()
{ return padding2; 
}

public void setPadding3(short pPadding3)
{ padding3 = pPadding3;
}

public short getPadding3()
{ return padding3; 
}

public void setModulationParametersList(List pModulationParametersList)
{ modulationParametersList = pModulationParametersList;
}

public List getModulationParametersList()
{ return modulationParametersList; }

public void setAntennaPatternList(List pAntennaPatternList)
{ antennaPatternList = pAntennaPatternList;
}

public List getAntennaPatternList()
{ return antennaPatternList; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       radioEntityType.marshal(dos);
       dos.writeByte( (byte)transmitState);
       dos.writeByte( (byte)inputSource);
       dos.writeShort( (short)padding1);
       antennaLocation.marshal(dos);
       relativeAntennaLocation.marshal(dos);
       dos.writeShort( (short)antennaPatternType);
       dos.writeShort( (short)antennaPatternList.size());
       dos.writeDouble( (double)frequency);
       dos.writeFloat( (float)transmitFrequencyBandwidth);
       dos.writeFloat( (float)power);
       modulationType.marshal(dos);
       dos.writeShort( (short)cryptoSystem);
       dos.writeShort( (short)cryptoKeyId);
       dos.writeByte( (byte)modulationParametersList.size());
       dos.writeShort( (short)padding2);
       dos.writeByte( (byte)padding3);

       for(int idx = 0; idx < modulationParametersList.size(); idx++)
       {
            Vector3Float aVector3Float = (Vector3Float)modulationParametersList.get(idx);
            aVector3Float.marshal(dos);
       } // end of list marshalling


       for(int idx = 0; idx < antennaPatternList.size(); idx++)
       {
            Vector3Float aVector3Float = (Vector3Float)antennaPatternList.get(idx);
            aVector3Float.marshal(dos);
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
       radioEntityType.unmarshal(dis);
       transmitState = dis.readByte();
       inputSource = dis.readByte();
       padding1 = dis.readShort();
       antennaLocation.unmarshal(dis);
       relativeAntennaLocation.unmarshal(dis);
       antennaPatternType = dis.readShort();
       antennaPatternCount = dis.readShort();
       frequency = dis.readDouble();
       transmitFrequencyBandwidth = dis.readFloat();
       power = dis.readFloat();
       modulationType.unmarshal(dis);
       cryptoSystem = dis.readShort();
       cryptoKeyId = dis.readShort();
       modulationParameterCount = dis.readByte();
       padding2 = dis.readShort();
       padding3 = dis.readByte();
        for(int idx = 0; idx < modulationParameterCount; idx++)
        {
           Vector3Float anX = new Vector3Float();
            anX.unmarshal(dis);
            modulationParametersList.add(anX);
        };

        for(int idx = 0; idx < antennaPatternCount; idx++)
        {
           Vector3Float anX = new Vector3Float();
            anX.unmarshal(dis);
            antennaPatternList.add(anX);
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
 public boolean equals(TransmitterPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (radioEntityType.equals( rhs.radioEntityType) )) ivarsEqual = false;
     if( ! (transmitState == rhs.transmitState)) ivarsEqual = false;
     if( ! (inputSource == rhs.inputSource)) ivarsEqual = false;
     if( ! (padding1 == rhs.padding1)) ivarsEqual = false;
     if( ! (antennaLocation.equals( rhs.antennaLocation) )) ivarsEqual = false;
     if( ! (relativeAntennaLocation.equals( rhs.relativeAntennaLocation) )) ivarsEqual = false;
     if( ! (antennaPatternType == rhs.antennaPatternType)) ivarsEqual = false;
     if( ! (antennaPatternCount == rhs.antennaPatternCount)) ivarsEqual = false;
     if( ! (frequency == rhs.frequency)) ivarsEqual = false;
     if( ! (transmitFrequencyBandwidth == rhs.transmitFrequencyBandwidth)) ivarsEqual = false;
     if( ! (power == rhs.power)) ivarsEqual = false;
     if( ! (modulationType.equals( rhs.modulationType) )) ivarsEqual = false;
     if( ! (cryptoSystem == rhs.cryptoSystem)) ivarsEqual = false;
     if( ! (cryptoKeyId == rhs.cryptoKeyId)) ivarsEqual = false;
     if( ! (modulationParameterCount == rhs.modulationParameterCount)) ivarsEqual = false;
     if( ! (padding2 == rhs.padding2)) ivarsEqual = false;
     if( ! (padding3 == rhs.padding3)) ivarsEqual = false;

     for(int idx = 0; idx < modulationParametersList.size(); idx++)
     {
        Vector3Float x = (Vector3Float)modulationParametersList.get(idx);
        if( ! ( modulationParametersList.get(idx).equals(rhs.modulationParametersList.get(idx)))) ivarsEqual = false;
     }


     for(int idx = 0; idx < antennaPatternList.size(); idx++)
     {
        Vector3Float x = (Vector3Float)antennaPatternList.get(idx);
        if( ! ( antennaPatternList.get(idx).equals(rhs.antennaPatternList.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
