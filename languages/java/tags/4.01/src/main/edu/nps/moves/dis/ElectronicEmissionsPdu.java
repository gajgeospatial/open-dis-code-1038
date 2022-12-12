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
 * Section 5.3.7.1. Information about active electronic warfare (EW) emissions and active EW countermeasures shall be communicated using an Electromagnetic Emission PDU. COMPLETE (I think)
 *
 * Copyright (c) 2008-2010, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
@Entity  // Hibernate
@Inheritance(strategy=InheritanceType.JOINED)  // Hibernate
public class ElectronicEmissionsPdu extends DistributedEmissionsFamilyPdu implements Serializable
{
   /** ID of the entity emitting */
   protected EntityID  emittingEntityID = new EntityID(); 

   /** ID of event */
   protected EventID  eventID = new EventID(); 

   /** This field shall be used to indicate if the data in the PDU represents a state update or just data that has changed since issuance of the last Electromagnetic Emission PDU [relative to the identified entity and emission system(s)]. */
   protected short  stateUpdateIndicator;

   /** This field shall specify the number of emission systems being described in the current PDU. */
   protected short  numberOfSystems;

   /** padding */
   protected int  paddingForEmissionsPdu;

   /** Electronic emmissions systems */
   protected List< ElectronicEmissionSystemData> systems = new ArrayList<ElectronicEmissionSystemData>(); 

/** Constructor */
 public ElectronicEmissionsPdu()
 {
    setPduType( (short)23 );
    setPaddingForEmissionsPdu( (int)0 );
 }

@Transient  // Marked as transient to prevent hibernate from thinking this is a persistent property
public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + emittingEntityID.getMarshalledSize();  // emittingEntityID
   marshalSize = marshalSize + eventID.getMarshalledSize();  // eventID
   marshalSize = marshalSize + 1;  // stateUpdateIndicator
   marshalSize = marshalSize + 1;  // numberOfSystems
   marshalSize = marshalSize + 2;  // paddingForEmissionsPdu
   for(int idx=0; idx < systems.size(); idx++)
   {
        ElectronicEmissionSystemData listElement = systems.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setEmittingEntityID(EntityID pEmittingEntityID)
{ emittingEntityID = pEmittingEntityID;
}

// HIBERNATE: this ivar is a foreign key, linked to the below class table. 
// It is not a DIS-standard variable and is not marshalled to IEEE-1278.1
public long fk_emittingEntityID;

@XmlElement
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name="fk_emittingEntityID")
public EntityID getEmittingEntityID()
{ return emittingEntityID; 
}

public void setEventID(EventID pEventID)
{ eventID = pEventID;
}

// HIBERNATE: this ivar is a foreign key, linked to the below class table. 
// It is not a DIS-standard variable and is not marshalled to IEEE-1278.1
public long fk_eventID;

@XmlElement
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name="fk_eventID")
public EventID getEventID()
{ return eventID; 
}

public void setStateUpdateIndicator(short pStateUpdateIndicator)
{ stateUpdateIndicator = pStateUpdateIndicator;
}

@XmlAttribute // Jaxb
@Basic       // Hibernate
public short getStateUpdateIndicator()
{ return stateUpdateIndicator; 
}

@XmlAttribute
@Basic
public short getNumberOfSystems()
{ return (short)systems.size();
}

/** Note that setting this value will not change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfSystems method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfSystems(short pNumberOfSystems)
{ numberOfSystems = pNumberOfSystems;
}

public void setPaddingForEmissionsPdu(int pPaddingForEmissionsPdu)
{ paddingForEmissionsPdu = pPaddingForEmissionsPdu;
}

@XmlAttribute // Jaxb
@Basic       // Hibernate
public int getPaddingForEmissionsPdu()
{ return paddingForEmissionsPdu; 
}

public void setSystems(List<ElectronicEmissionSystemData> pSystems)
{ systems = pSystems;
}

@XmlElementWrapper(name="systemsList" ) //  Jaxb
@OneToMany    // Hibernate
public List<ElectronicEmissionSystemData> getSystems()
{ return systems; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       emittingEntityID.marshal(dos);
       eventID.marshal(dos);
       dos.writeByte( (byte)stateUpdateIndicator);
       dos.writeByte( (byte)systems.size());
       dos.writeShort( (short)paddingForEmissionsPdu);

       for(int idx = 0; idx < systems.size(); idx++)
       {
            ElectronicEmissionSystemData aElectronicEmissionSystemData = systems.get(idx);
            aElectronicEmissionSystemData.marshal(dos);
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
       emittingEntityID.unmarshal(dis);
       eventID.unmarshal(dis);
       stateUpdateIndicator = (short)dis.readUnsignedByte();
       numberOfSystems = (short)dis.readUnsignedByte();
       paddingForEmissionsPdu = (int)dis.readUnsignedShort();
       for(int idx = 0; idx < numberOfSystems; idx++)
       {
           ElectronicEmissionSystemData anX = new ElectronicEmissionSystemData();
           anX.unmarshal(dis);
           systems.add(anX);
       }

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
       emittingEntityID.marshal(buff);
       eventID.marshal(buff);
       buff.put( (byte)stateUpdateIndicator);
       buff.put( (byte)systems.size());
       buff.putShort( (short)paddingForEmissionsPdu);

       for(int idx = 0; idx < systems.size(); idx++)
       {
            ElectronicEmissionSystemData aElectronicEmissionSystemData = (ElectronicEmissionSystemData)systems.get(idx);
            aElectronicEmissionSystemData.marshal(buff);
       } // end of list marshalling

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

       emittingEntityID.unmarshal(buff);
       eventID.unmarshal(buff);
       stateUpdateIndicator = (short)(buff.get() & 0xFF);
       numberOfSystems = (short)(buff.get() & 0xFF);
       paddingForEmissionsPdu = (int)(buff.getShort() & 0xFFFF);
       for(int idx = 0; idx < numberOfSystems; idx++)
       {
            ElectronicEmissionSystemData anX = new ElectronicEmissionSystemData();
            anX.unmarshal(buff);
            systems.add(anX);
       }

 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
 public boolean equals(ElectronicEmissionsPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (emittingEntityID.equals( rhs.emittingEntityID) )) ivarsEqual = false;
     if( ! (eventID.equals( rhs.eventID) )) ivarsEqual = false;
     if( ! (stateUpdateIndicator == rhs.stateUpdateIndicator)) ivarsEqual = false;
     if( ! (numberOfSystems == rhs.numberOfSystems)) ivarsEqual = false;
     if( ! (paddingForEmissionsPdu == rhs.paddingForEmissionsPdu)) ivarsEqual = false;

     for(int idx = 0; idx < systems.size(); idx++)
     {
        if( ! ( systems.get(idx).equals(rhs.systems.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
