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
 * Used in UA PDU
 *
 * Copyright (c) 2008-2010, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
@Entity  // Hibernate
@Inheritance(strategy=InheritanceType.JOINED)  // Hibernate
public class ApaData extends Object implements Serializable
{
   /** Primary key for hibernate, not part of the DIS standard */
   private long pk_ApaData;

   /** Index of APA parameter */
   protected int  parameterIndex;

   /** Index of APA parameter */
   protected short  parameterValue;


/** Constructor */
 public ApaData()
 {
 }

@Transient  // Marked as transient to prevent hibernate from thinking this is a persistent property
public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 2;  // parameterIndex
   marshalSize = marshalSize + 2;  // parameterValue

   return marshalSize;
}


/** Primary key for hibernate, not part of the DIS standard */
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
public long getPk_ApaData()
{
   return pk_ApaData;
}

/** Hibernate primary key, not part of the DIS standard */
public void setPk_ApaData(long pKeyName)
{
   this.pk_ApaData = pKeyName;
}

public void setParameterIndex(int pParameterIndex)
{ parameterIndex = pParameterIndex;
}

@XmlAttribute // Jaxb
@Basic       // Hibernate
public int getParameterIndex()
{ return parameterIndex; 
}

public void setParameterValue(short pParameterValue)
{ parameterValue = pParameterValue;
}

@XmlAttribute // Jaxb
@Basic       // Hibernate
public short getParameterValue()
{ return parameterValue; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeShort( (short)parameterIndex);
       dos.writeShort( (short)parameterValue);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       parameterIndex = (int)dis.readUnsignedShort();
       parameterValue = dis.readShort();
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
       buff.putShort( (short)parameterIndex);
       buff.putShort( (short)parameterValue);
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
       parameterIndex = (int)(buff.getShort() & 0xFFFF);
       parameterValue = buff.getShort();
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
 public boolean equals(ApaData rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (parameterIndex == rhs.parameterIndex)) ivarsEqual = false;
     if( ! (parameterValue == rhs.parameterValue)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
