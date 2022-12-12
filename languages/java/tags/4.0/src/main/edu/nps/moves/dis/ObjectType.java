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
 * Identifies type of object. This is a shorter version of EntityType that omits the specific and extra fields.
 *
 * Copyright (c) 2008-2010, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
@Entity  // Hibernate
@Inheritance(strategy=InheritanceType.JOINED)  // Hibernate
public class ObjectType extends Object implements Serializable
{
   /** Primary key for hibernate, not part of the DIS standard */
   private long pk_ObjectType;

   /** Kind of entity */
   protected short  entityKind;

   /** Domain of entity (air, surface, subsurface, space, etc) */
   protected short  domain;

   /** country to which the design of the entity is attributed */
   protected int  country;

   /** category of entity */
   protected short  category;

   /** subcategory of entity */
   protected short  subcategory;


/** Constructor */
 public ObjectType()
 {
 }

@Transient  // Marked as transient to prevent hibernate from thinking this is a persistent property
public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 1;  // entityKind
   marshalSize = marshalSize + 1;  // domain
   marshalSize = marshalSize + 2;  // country
   marshalSize = marshalSize + 1;  // category
   marshalSize = marshalSize + 1;  // subcategory

   return marshalSize;
}


/** Primary key for hibernate, not part of the DIS standard */
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
public long getPk_ObjectType()
{
   return pk_ObjectType;
}

/** Hibernate primary key, not part of the DIS standard */
public void setPk_ObjectType(long pKeyName)
{
   this.pk_ObjectType = pKeyName;
}

public void setEntityKind(short pEntityKind)
{ entityKind = pEntityKind;
}

@XmlAttribute // Jaxb
@Basic       // Hibernate
public short getEntityKind()
{ return entityKind; 
}

public void setDomain(short pDomain)
{ domain = pDomain;
}

@XmlAttribute // Jaxb
@Basic       // Hibernate
public short getDomain()
{ return domain; 
}

public void setCountry(int pCountry)
{ country = pCountry;
}

@XmlAttribute // Jaxb
@Basic       // Hibernate
public int getCountry()
{ return country; 
}

public void setCategory(short pCategory)
{ category = pCategory;
}

@XmlAttribute // Jaxb
@Basic       // Hibernate
public short getCategory()
{ return category; 
}

public void setSubcategory(short pSubcategory)
{ subcategory = pSubcategory;
}

@XmlAttribute // Jaxb
@Basic       // Hibernate
public short getSubcategory()
{ return subcategory; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeByte( (byte)entityKind);
       dos.writeByte( (byte)domain);
       dos.writeShort( (short)country);
       dos.writeByte( (byte)category);
       dos.writeByte( (byte)subcategory);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       entityKind = (short)dis.readUnsignedByte();
       domain = (short)dis.readUnsignedByte();
       country = (int)dis.readUnsignedShort();
       category = (short)dis.readUnsignedByte();
       subcategory = (short)dis.readUnsignedByte();
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
       buff.put( (byte)entityKind);
       buff.put( (byte)domain);
       buff.putShort( (short)country);
       buff.put( (byte)category);
       buff.put( (byte)subcategory);
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
       entityKind = (short)(buff.get() & 0xFF);
       domain = (short)(buff.get() & 0xFF);
       country = (int)(buff.getShort() & 0xFFFF);
       category = (short)(buff.get() & 0xFF);
       subcategory = (short)(buff.get() & 0xFF);
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
 public boolean equals(ObjectType rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (entityKind == rhs.entityKind)) ivarsEqual = false;
     if( ! (domain == rhs.domain)) ivarsEqual = false;
     if( ! (country == rhs.country)) ivarsEqual = false;
     if( ! (category == rhs.category)) ivarsEqual = false;
     if( ! (subcategory == rhs.subcategory)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
