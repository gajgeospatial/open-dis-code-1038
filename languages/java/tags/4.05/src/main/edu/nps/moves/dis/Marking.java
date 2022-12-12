package edu.nps.moves.dis;

import java.util.*;
import java.io.*;

// Jaxb and Hibernate annotations generally won't work on mobile devices. XML serialization uses jaxb, and
// javax.persistence uses the JPA JSR, aka hibernate. See the Hibernate site for details.
// To generate Java code without these, and without the annotations scattered through the
// see the XMLPG java code generator, and set the boolean useHibernateAnnotations and useJaxbAnnotions 
// to false, and then regenerate the code

import javax.xml.bind.*;            // Used for JAXB XML serialization
import javax.xml.bind.annotation.*; // Used for XML serialization annotations (the @ stuff)
import javax.persistence.*;         // Used for JPA/Hibernate SQL persistence

/**
 * Section 5.2.15. Specifies the character set used inthe first byte, followed by 11 characters of text data.
 *
 * Copyright (c) 2008-2010, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
@Entity  // Hibernate
@Inheritance(strategy=InheritanceType.JOINED)  // Hibernate
public class Marking extends Object implements Serializable
{
   /** Primary key for hibernate, not part of the DIS standard */
   private long pk_Marking;

   /** The character set */
   protected short  characterSet;

   /** The characters */
   protected byte[]  characters = new byte[11]; 


/** Constructor */
 public Marking()
 {
 }

@Transient  // Marked as transient to prevent hibernate from thinking this is a persistent property
public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 1;  // characterSet
   marshalSize = marshalSize + 11 * 1;  // characters

   return marshalSize;
}


/** Primary key for hibernate, not part of the DIS standard */
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
public long getPk_Marking()
{
   return pk_Marking;
}

/** Hibernate primary key, not part of the DIS standard */
public void setPk_Marking(long pKeyName)
{
   this.pk_Marking = pKeyName;
}

public void setCharacterSet(short pCharacterSet)
{ characterSet = pCharacterSet;
}

@XmlAttribute // Jaxb
@Basic       // Hibernate
public short getCharacterSet()
{ return characterSet; 
}

@XmlElement(name="characters" )
@Basic
public byte[] getCharacters()
{ return characters; }

    /**
     * Ensure what is set does not go over 11 characters -- post-processing patch
     * @param pCharacters an array of characters to set
     */
    public void setCharacters(byte[] pCharacters) 
    {
        if (pCharacters.length >= characters.length) 
        {
            System.arraycopy(pCharacters, 0, characters, 0, characters.length);
        } 
        else 
        {
            int pCharactersLength = pCharacters.length;
            System.arraycopy(pCharacters, 0, characters, 0, pCharactersLength);
            for (int ix = pCharactersLength; ix < characters.length; ix++) 
            {
                // Ensure all zeros in unfilled fields
                characters[ix] = 0;
            }
        }
    }
    
    /**
     * An added convieniece method (added by patch): accepts a string, and either
     * truncates or zero-fills it to fit into the 11-byte character marking field.
     * @param marking the marking string, converted internally into a character array that
     * is exactly 11 bytes long
     */
    public void setCharacters(String marking)
    {
        byte[] buff = marking.getBytes();
        this.setCharacters(buff);
    }

public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeByte( (byte)characterSet);

       for(int idx = 0; idx < characters.length; idx++)
       {
           dos.writeByte(characters[idx]);
       } // end of array marshaling

    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       characterSet = (short)dis.readUnsignedByte();
       for(int idx = 0; idx < characters.length; idx++)
       {
                characters[idx] = dis.readByte();
       } // end of array unmarshaling
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
       buff.put( (byte)characterSet);

       for(int idx = 0; idx < characters.length; idx++)
       {
           buff.put((byte)characters[idx]);
       } // end of array marshaling

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
       characterSet = (short)(buff.get() & 0xFF);
       for(int idx = 0; idx < characters.length; idx++)
       {
                characters[idx] = buff.get();
       } // end of array unmarshaling
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

 /**
  * Compare all fields that contribute to the state, ignoring
 transient and static fields, for <code>this</code> and the supplied object
  * @param obj the object to compare to
  * @return true if the objects are equal, false otherwise.
  */
 public boolean equalsImpl(Object obj)
 {
     boolean ivarsEqual = true;

    if(!(obj instanceof Marking))
        return false;

     final Marking rhs = (Marking)obj;

     if( ! (characterSet == rhs.characterSet)) ivarsEqual = false;

     for(int idx = 0; idx < 11; idx++)
     {
          if(!(characters[idx] == rhs.characters[idx])) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
