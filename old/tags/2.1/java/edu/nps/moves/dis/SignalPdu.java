package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.8.2. Detailed information about a radio transmitter. This PDU requires        manually written code to complete. The encodingScheme field can be used in multiple        ways, which requires hand-written code to finish. UNFINISHED
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class SignalPdu extends RadioCommunicationsFamilyPdu implements Serializable
{
   /** encoding scheme used, and enumeration */
   protected int  encodingScheme;

   /** tdl type */
   protected int  tdlType;

   /** sample rate */
   protected int  sampleRate;

   /** length od data */
   protected short  dataLength;

   /** number of samples */
   protected short  samples;


/** Constructor */
 public SignalPdu()
 {
    setPduType( (short)26 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public SignalPdu(edu.nps.moves.jaxb.dis.SignalPdu x)
 {
     super(x); // Call superclass constructor

     this.encodingScheme = x.getEncodingScheme();
     this.tdlType = x.getTdlType();
     this.sampleRate = x.getSampleRate();
     this.dataLength = x.getDataLength();
     this.samples = x.getSamples();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.SignalPdu initializeJaxbObject(edu.nps.moves.jaxb.dis.SignalPdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setEncodingScheme( this.getEncodingScheme() );
     x.setTdlType( this.getTdlType() );
     x.setSampleRate( this.getSampleRate() );
     x.setDataLength( this.getDataLength() );
     x.setSamples( this.getSamples() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + 2;  // encodingScheme
   marshalSize = marshalSize + 2;  // tdlType
   marshalSize = marshalSize + 4;  // sampleRate
   marshalSize = marshalSize + 2;  // dataLength
   marshalSize = marshalSize + 2;  // samples

   return marshalSize;
}


public void setEncodingScheme(int pEncodingScheme)
{ encodingScheme = pEncodingScheme;
}

public int getEncodingScheme()
{ return encodingScheme; 
}

public void setTdlType(int pTdlType)
{ tdlType = pTdlType;
}

public int getTdlType()
{ return tdlType; 
}

public void setSampleRate(int pSampleRate)
{ sampleRate = pSampleRate;
}

public int getSampleRate()
{ return sampleRate; 
}

public void setDataLength(short pDataLength)
{ dataLength = pDataLength;
}

public short getDataLength()
{ return dataLength; 
}

public void setSamples(short pSamples)
{ samples = pSamples;
}

public short getSamples()
{ return samples; 
}


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       dos.writeShort( (short)encodingScheme);
       dos.writeShort( (short)tdlType);
       dos.writeInt( (int)sampleRate);
       dos.writeShort( (short)dataLength);
       dos.writeShort( (short)samples);
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
       encodingScheme = dis.readShort();
       tdlType = dis.readShort();
       sampleRate = dis.readInt();
       dataLength = dis.readShort();
       samples = dis.readShort();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(SignalPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (encodingScheme == rhs.encodingScheme)) ivarsEqual = false;
     if( ! (tdlType == rhs.tdlType)) ivarsEqual = false;
     if( ! (sampleRate == rhs.sampleRate)) ivarsEqual = false;
     if( ! (dataLength == rhs.dataLength)) ivarsEqual = false;
     if( ! (samples == rhs.samples)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
