package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.disenum.*;
import edu.nps.moves.disutil.*;

// Jaxb and Hibernate annotations generally won't work on mobile devices. XML serialization uses jaxb, and
// javax.persistence uses the JPA JSR, aka hibernate. See the Hibernate site for details.
// To generate Java code without these, and without the annotations scattered through the
// see the XMLPG java code generator, and set the boolean useHibernateAnnotations and useJaxbAnnotions 
// to false, and then regenerate the code

import javax.xml.bind.*;            // Used for JAXB XML serialization
import javax.xml.bind.annotation.*; // Used for XML serialization annotations (the @ stuff)
import javax.persistence.*;         // Used for JPA/Hibernate SQL persistence

/**
 * Section 5.2.3.4. Stop or freeze an exercise. COMPLETE
 *
 * Copyright (c) 2008-2010, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
@Entity  // Hibernate
@Inheritance(strategy=InheritanceType.JOINED)  // Hibernate
public class StopFreezePdu extends SimulationManagementFamilyPdu implements Serializable
{
   /** UTC time at which the simulation shall stop or freeze */
   protected ClockTime  realWorldTime = new ClockTime(); 

   /** Reason the simulation was stopped or frozen */
   protected short  reason;

   /** Internal behavior of the simulation and its appearance while frozento the other participants */
   protected short  frozenBehavior;

   /** padding */
   protected short  padding1 = (short)0;

   /** Request ID that is unique */
   protected long  requestID;


/** Constructor */
 public StopFreezePdu()
 {
    setPduType( (short)14 );
 }

@Transient  // Marked as transient to prevent hibernate from thinking this is a persistent property
public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + realWorldTime.getMarshalledSize();  // realWorldTime
   marshalSize = marshalSize + 1;  // reason
   marshalSize = marshalSize + 1;  // frozenBehavior
   marshalSize = marshalSize + 2;  // padding1
   marshalSize = marshalSize + 4;  // requestID

   return marshalSize;
}


public void setRealWorldTime(ClockTime pRealWorldTime)
{ realWorldTime = pRealWorldTime;
}

// HIBERNATE: this ivar is a foreign key, linked to the below class table. 
// It is not a DIS-standard variable and is not marshalled to IEEE-1278.1
public long fk_realWorldTime;

@XmlElement
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name="fk_realWorldTime")
public ClockTime getRealWorldTime()
{ return realWorldTime; 
}

public void setReason(short pReason)
{ reason = pReason;
}

@XmlAttribute // Jaxb
@Basic       // Hibernate
public short getReason()
{ return reason; 
}

public void setFrozenBehavior(short pFrozenBehavior)
{ frozenBehavior = pFrozenBehavior;
}

@XmlAttribute // Jaxb
@Basic       // Hibernate
public short getFrozenBehavior()
{ return frozenBehavior; 
}

public void setPadding1(short pPadding1)
{ padding1 = pPadding1;
}

@XmlAttribute // Jaxb
@Basic       // Hibernate
public short getPadding1()
{ return padding1; 
}

public void setRequestID(long pRequestID)
{ requestID = pRequestID;
}

@XmlAttribute // Jaxb
@Basic       // Hibernate
public long getRequestID()
{ return requestID; 
}


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       realWorldTime.marshal(dos);
       dos.writeByte( (byte)reason);
       dos.writeByte( (byte)frozenBehavior);
       dos.writeShort( (short)padding1);
       dos.writeInt( (int)requestID);
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
       realWorldTime.unmarshal(dis);
       reason = (short)dis.readUnsignedByte();
       frozenBehavior = (short)dis.readUnsignedByte();
       padding1 = dis.readShort();
       requestID = dis.readInt();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


/**
 * Packs a Pdu into the ByteBuffer.
 * @throws java.nio.BufferOverflowException if buff is too small
 * @throws java.nio.ReadOnlyBufferException if buff is read only
 * @see java.nio.ByteBuffer
 * @param buff The ByteBuffer at the position to begin writing
 * @since ??
 */
public void marshal(java.nio.ByteBuffer buff)
{
       super.marshal(buff);
       realWorldTime.marshal(buff);
       buff.put( (byte)reason);
       buff.put( (byte)frozenBehavior);
       buff.putShort( (short)padding1);
       buff.putInt( (int)requestID);
    } // end of marshal method

/**
 * Unpacks a Pdu from the underlying data.
 * @throws java.nio.BufferUnderflowException if buff is too small
 * @see java.nio.ByteBuffer
 * @param buff The ByteBuffer at the position to begin reading
 * @since ??
 */
public void unmarshal(java.nio.ByteBuffer buff)
{
       super.unmarshal(buff);

       realWorldTime.unmarshal(buff);
       reason = (short)(buff.get() & 0xFF);
       frozenBehavior = (short)(buff.get() & 0xFF);
       padding1 = buff.getShort();
       requestID = buff.getInt();
 } // end of unmarshal method 


 /*
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
@Override
 public boolean equals(Object obj)
 {

    if(this == obj){
      return true;
    }

    if(obj == null){
       return false;
    }

    if(getClass() != obj.getClass())
        return false;

    return equalsImpl(obj);
 }

@Override
 public boolean equalsImpl(Object obj)
 {
     boolean ivarsEqual = true;

    if(!(obj instanceof StopFreezePdu))
        return false;

     final StopFreezePdu rhs = (StopFreezePdu)obj;

     if( ! (realWorldTime.equals( rhs.realWorldTime) )) ivarsEqual = false;
     if( ! (reason == rhs.reason)) ivarsEqual = false;
     if( ! (frozenBehavior == rhs.frozenBehavior)) ivarsEqual = false;
     if( ! (padding1 == rhs.padding1)) ivarsEqual = false;
     if( ! (requestID == rhs.requestID)) ivarsEqual = false;

    return ivarsEqual && super.equalsImpl(rhs);
 }
} // end of class
