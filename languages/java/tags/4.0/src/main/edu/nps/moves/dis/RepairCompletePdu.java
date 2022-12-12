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
 * Section 5.2.5.5. Repair is complete. COMPLETE
 *
 * Copyright (c) 2008-2010, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
@Entity  // Hibernate
@Inheritance(strategy=InheritanceType.JOINED)  // Hibernate
public class RepairCompletePdu extends LogisticsFamilyPdu implements Serializable
{
   /** Entity that is receiving service */
   protected EntityID  receivingEntityID = new EntityID(); 

   /** Entity that is supplying */
   protected EntityID  repairingEntityID = new EntityID(); 

   /** Enumeration for type of repair */
   protected int  repair;

   /** padding, number prevents conflict with superclass ivar name */
   protected short  padding2 = 0;


/** Constructor */
 public RepairCompletePdu()
 {
    setPduType( (short)9 );
 }

@Transient  // Marked as transient to prevent hibernate from thinking this is a persistent property
public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + receivingEntityID.getMarshalledSize();  // receivingEntityID
   marshalSize = marshalSize + repairingEntityID.getMarshalledSize();  // repairingEntityID
   marshalSize = marshalSize + 2;  // repair
   marshalSize = marshalSize + 2;  // padding2

   return marshalSize;
}


public void setReceivingEntityID(EntityID pReceivingEntityID)
{ receivingEntityID = pReceivingEntityID;
}

// HIBERNATE: this ivar is a foreign key, linked to the below class table. 
// It is not a DIS-standard variable and is not marshalled to IEEE-1278.1
public long fk_receivingEntityID;

@XmlElement
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name="fk_receivingEntityID")
public EntityID getReceivingEntityID()
{ return receivingEntityID; 
}

public void setRepairingEntityID(EntityID pRepairingEntityID)
{ repairingEntityID = pRepairingEntityID;
}

// HIBERNATE: this ivar is a foreign key, linked to the below class table. 
// It is not a DIS-standard variable and is not marshalled to IEEE-1278.1
public long fk_repairingEntityID;

@XmlElement
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name="fk_repairingEntityID")
public EntityID getRepairingEntityID()
{ return repairingEntityID; 
}

public void setRepair(int pRepair)
{ repair = pRepair;
}

@XmlAttribute // Jaxb
@Basic       // Hibernate
public int getRepair()
{ return repair; 
}

public void setPadding2(short pPadding2)
{ padding2 = pPadding2;
}

@XmlAttribute // Jaxb
@Basic       // Hibernate
public short getPadding2()
{ return padding2; 
}


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       receivingEntityID.marshal(dos);
       repairingEntityID.marshal(dos);
       dos.writeShort( (short)repair);
       dos.writeShort( (short)padding2);
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
       receivingEntityID.unmarshal(dis);
       repairingEntityID.unmarshal(dis);
       repair = (int)dis.readUnsignedShort();
       padding2 = dis.readShort();
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
       receivingEntityID.marshal(buff);
       repairingEntityID.marshal(buff);
       buff.putShort( (short)repair);
       buff.putShort( (short)padding2);
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

       receivingEntityID.unmarshal(buff);
       repairingEntityID.unmarshal(buff);
       repair = (int)(buff.getShort() & 0xFFFF);
       padding2 = buff.getShort();
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
 public boolean equals(RepairCompletePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (receivingEntityID.equals( rhs.receivingEntityID) )) ivarsEqual = false;
     if( ! (repairingEntityID.equals( rhs.repairingEntityID) )) ivarsEqual = false;
     if( ! (repair == rhs.repair)) ivarsEqual = false;
     if( ! (padding2 == rhs.padding2)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
