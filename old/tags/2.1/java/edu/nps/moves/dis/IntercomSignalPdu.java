package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.8.4. Actual transmission of intercome voice data. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class IntercomSignalPdu extends RadioCommunicationsFamilyPdu implements Serializable
{
   /** entity ID */
   protected EntityID  entityID = new EntityID(); 

   /** ID of communications device */
   protected int  communicationsDeviceID;

   /** encoding scheme */
   protected int  encodingScheme;

   /** tactical data link type */
   protected int  TdlType;

   /** sample rate */
   protected long  sampleRate;

   /** data length */
   protected int  dataLength;

   /** samples */
   protected int  samples;

   /** data bytes */
   protected List data = new ArrayList(); 

/** Constructor */
 public IntercomSignalPdu()
 {
    setPduType( (short)31 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public IntercomSignalPdu(edu.nps.moves.jaxb.dis.IntercomSignalPdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.EntityID foo_0;
     if(x.getEntityID() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getEntityID() );
     this.setEntityID(foo_0);

     this.communicationsDeviceID = x.getCommunicationsDeviceID();
     this.encodingScheme = x.getEncodingScheme();
     this.TdlType = x.getTdlType();
     this.sampleRate = x.getSampleRate();
     this.dataLength = x.getDataLength();
     this.samples = x.getSamples();
     this.data = new ArrayList();
     for(int idx = 0; idx < x.getData().size(); idx++)
     {
        this.data.add( new edu.nps.moves.dis.OneByteChunk((edu.nps.moves.jaxb.dis.OneByteChunk) x.getData().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.IntercomSignalPdu initializeJaxbObject(edu.nps.moves.jaxb.dis.IntercomSignalPdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setEntityID( this.getEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setCommunicationsDeviceID( this.getCommunicationsDeviceID() );
     x.setEncodingScheme( this.getEncodingScheme() );
     x.setTdlType( this.getTdlType() );
     x.setSampleRate( this.getSampleRate() );
     x.setDataLength( this.getDataLength() );
     x.setSamples( this.getSamples() );

     List data_1 = x.getData();
     for(int idx = 0; idx < data.size(); idx++)
     {
         OneByteChunk a = (edu.nps.moves.dis.OneByteChunk)data.get(idx);
         data_1.add(a.initializeJaxbObject(factory.createOneByteChunk()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + entityID.getMarshalledSize();  // entityID
   marshalSize = marshalSize + 2;  // communicationsDeviceID
   marshalSize = marshalSize + 2;  // encodingScheme
   marshalSize = marshalSize + 2;  // TdlType
   marshalSize = marshalSize + 4;  // sampleRate
   marshalSize = marshalSize + 2;  // dataLength
   marshalSize = marshalSize + 2;  // samples
   for(int idx=0; idx < data.size(); idx++)
   {
        OneByteChunk listElement = (OneByteChunk)data.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setEntityID(EntityID pEntityID)
{ entityID = pEntityID;
}

public EntityID getEntityID()
{ return entityID; }

public void setCommunicationsDeviceID(int pCommunicationsDeviceID)
{ communicationsDeviceID = pCommunicationsDeviceID;
}

public int getCommunicationsDeviceID()
{ return communicationsDeviceID; 
}

public void setEncodingScheme(int pEncodingScheme)
{ encodingScheme = pEncodingScheme;
}

public int getEncodingScheme()
{ return encodingScheme; 
}

public void setTdlType(int pTdlType)
{ TdlType = pTdlType;
}

public int getTdlType()
{ return TdlType; 
}

public void setSampleRate(long pSampleRate)
{ sampleRate = pSampleRate;
}

public long getSampleRate()
{ return sampleRate; 
}

public int getDataLength()
{ return (int)data.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getdataLength method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setDataLength(int pDataLength)
{ dataLength = pDataLength;
}

public void setSamples(int pSamples)
{ samples = pSamples;
}

public int getSamples()
{ return samples; 
}

public void setData(List pData)
{ data = pData;
}

public List getData()
{ return data; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       entityID.marshal(dos);
       dos.writeShort( (short)communicationsDeviceID);
       dos.writeShort( (short)encodingScheme);
       dos.writeShort( (short)TdlType);
       dos.writeInt( (int)sampleRate);
       dos.writeShort( (short)data.size());
       dos.writeShort( (short)samples);

       for(int idx = 0; idx < data.size(); idx++)
       {
            OneByteChunk aOneByteChunk = (OneByteChunk)data.get(idx);
            aOneByteChunk.marshal(dos);
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
       entityID.unmarshal(dis);
       communicationsDeviceID = dis.readShort();
       encodingScheme = dis.readShort();
       TdlType = dis.readShort();
       sampleRate = dis.readInt();
       dataLength = dis.readShort();
       samples = dis.readShort();
        for(int idx = 0; idx < dataLength; idx++)
        {
           OneByteChunk anX = new OneByteChunk();
            anX.unmarshal(dis);
            data.add(anX);
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
 public boolean equals(IntercomSignalPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (entityID.equals( rhs.entityID) )) ivarsEqual = false;
     if( ! (communicationsDeviceID == rhs.communicationsDeviceID)) ivarsEqual = false;
     if( ! (encodingScheme == rhs.encodingScheme)) ivarsEqual = false;
     if( ! (TdlType == rhs.TdlType)) ivarsEqual = false;
     if( ! (sampleRate == rhs.sampleRate)) ivarsEqual = false;
     if( ! (dataLength == rhs.dataLength)) ivarsEqual = false;
     if( ! (samples == rhs.samples)) ivarsEqual = false;

     for(int idx = 0; idx < data.size(); idx++)
     {
        OneByteChunk x = (OneByteChunk)data.get(idx);
        if( ! ( data.get(idx).equals(rhs.data.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
