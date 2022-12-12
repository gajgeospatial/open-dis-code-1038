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
 * represents values used in dead reckoning algorithms
 *
 * Copyright (c) 2008-2010, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
@Entity  // Hibernate
@Inheritance(strategy=InheritanceType.JOINED)  // Hibernate
public class DeadReckoningParameter extends Object implements Serializable
{
   /** Primary key for hibernate, not part of the DIS standard */
   private long pk_DeadReckoningParameter;

   /** enumeration of what dead reckoning algorighm to use */
   protected short  deadReckoningAlgorithm;

   /** other parameters to use in the dead reckoning algorithm */
   protected byte[]  otherParameters = new byte[15]; 

   /** Linear acceleration of the entity */
   protected Vector3Float  entityLinearAcceleration = new Vector3Float(); 

   /** angular velocity of the entity */
   protected Vector3Float  entityAngularVelocity = new Vector3Float(); 


/** Constructor */
 public DeadReckoningParameter()
 {
 }

@Transient  // Marked as transient to prevent hibernate from thinking this is a persistent property
public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 1;  // deadReckoningAlgorithm
   marshalSize = marshalSize + 15 * 1;  // otherParameters
   marshalSize = marshalSize + entityLinearAcceleration.getMarshalledSize();  // entityLinearAcceleration
   marshalSize = marshalSize + entityAngularVelocity.getMarshalledSize();  // entityAngularVelocity

   return marshalSize;
}


/** Primary key for hibernate, not part of the DIS standard */
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
public long getPk_DeadReckoningParameter()
{
   return pk_DeadReckoningParameter;
}

/** Hibernate primary key, not part of the DIS standard */
public void setPk_DeadReckoningParameter(long pKeyName)
{
   this.pk_DeadReckoningParameter = pKeyName;
}

public void setDeadReckoningAlgorithm(short pDeadReckoningAlgorithm)
{ deadReckoningAlgorithm = pDeadReckoningAlgorithm;
}

@XmlAttribute // Jaxb
@Basic       // Hibernate
public short getDeadReckoningAlgorithm()
{ return deadReckoningAlgorithm; 
}

public void setOtherParameters(byte[] pOtherParameters)
{ otherParameters = pOtherParameters;
}

@XmlElement(name="otherParameters" )
@Basic
public byte[] getOtherParameters()
{ return otherParameters; }

public void setEntityLinearAcceleration(Vector3Float pEntityLinearAcceleration)
{ entityLinearAcceleration = pEntityLinearAcceleration;
}

// HIBERNATE: this ivar is a foreign key, linked to the below class table. 
// It is not a DIS-standard variable and is not marshalled to IEEE-1278.1
public long fk_entityLinearAcceleration;

@XmlElement
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name="fk_entityLinearAcceleration")
public Vector3Float getEntityLinearAcceleration()
{ return entityLinearAcceleration; 
}

public void setEntityAngularVelocity(Vector3Float pEntityAngularVelocity)
{ entityAngularVelocity = pEntityAngularVelocity;
}

// HIBERNATE: this ivar is a foreign key, linked to the below class table. 
// It is not a DIS-standard variable and is not marshalled to IEEE-1278.1
public long fk_entityAngularVelocity;

@XmlElement
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name="fk_entityAngularVelocity")
public Vector3Float getEntityAngularVelocity()
{ return entityAngularVelocity; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeByte( (byte)deadReckoningAlgorithm);

       for(int idx = 0; idx < otherParameters.length; idx++)
       {
           dos.writeByte(otherParameters[idx]);
       } // end of array marshaling

       entityLinearAcceleration.marshal(dos);
       entityAngularVelocity.marshal(dos);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       deadReckoningAlgorithm = (short)dis.readUnsignedByte();
       for(int idx = 0; idx < otherParameters.length; idx++)
       {
                otherParameters[idx] = dis.readByte();
       } // end of array unmarshaling
       entityLinearAcceleration.unmarshal(dis);
       entityAngularVelocity.unmarshal(dis);
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
       buff.put( (byte)deadReckoningAlgorithm);

       for(int idx = 0; idx < otherParameters.length; idx++)
       {
           buff.put(otherParameters[idx]);
       } // end of array marshaling

       entityLinearAcceleration.marshal(buff);
       entityAngularVelocity.marshal(buff);
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
       deadReckoningAlgorithm = (short)(buff.get() & 0xFF);
       for(int idx = 0; idx < otherParameters.length; idx++)
       {
                otherParameters[idx] = buff.get();
       } // end of array unmarshaling
       entityLinearAcceleration.unmarshal(buff);
       entityAngularVelocity.unmarshal(buff);
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
 public boolean equals(DeadReckoningParameter rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (deadReckoningAlgorithm == rhs.deadReckoningAlgorithm)) ivarsEqual = false;

     for(int idx = 0; idx < 15; idx++)
     {
          if(!(otherParameters[idx] == rhs.otherParameters[idx])) ivarsEqual = false;
     }

     if( ! (entityLinearAcceleration.equals( rhs.entityLinearAcceleration) )) ivarsEqual = false;
     if( ! (entityAngularVelocity.equals( rhs.entityAngularVelocity) )) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
