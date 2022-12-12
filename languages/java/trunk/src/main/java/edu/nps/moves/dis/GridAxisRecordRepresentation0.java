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
 * 5.2.44: Grid data record, representation 0
 *
 * Copyright (c) 2008-2014, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
@Entity  // Hibernate
@Inheritance(strategy=InheritanceType.JOINED)  // Hibernate
public class GridAxisRecordRepresentation0 extends GridAxisRecord implements Serializable
{
   /** number of bytes of environmental state data */
   protected int  numberOfBytes;

   /** variable length list of data parameters ^^^this is wrong--need padding as well */
   protected List< OneByteChunk > dataValues = new ArrayList< OneByteChunk >(); 

/** Constructor */
 public GridAxisRecordRepresentation0()
 {
 }

@Transient  // Marked as transient to prevent hibernate from thinking this is a persistent property
public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + 2;  // numberOfBytes
   for(int idx=0; idx < dataValues.size(); idx++)
   {
        OneByteChunk listElement = dataValues.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


@XmlAttribute
@Basic
public int getNumberOfBytes()
{ return (int)dataValues.size();
}

/** Note that setting this value will not change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfBytes method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfBytes(int pNumberOfBytes)
{ numberOfBytes = pNumberOfBytes;
}

public void setDataValues(List<OneByteChunk> pDataValues)
{ dataValues = pDataValues;
}

@XmlElementWrapper(name="dataValuesList" ) //  Jaxb
@OneToMany    // Hibernate
public List<OneByteChunk> getDataValues()
{ return dataValues; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       dos.writeShort( (short)dataValues.size());

       for(int idx = 0; idx < dataValues.size(); idx++)
       {
            OneByteChunk aOneByteChunk = dataValues.get(idx);
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
       numberOfBytes = (int)dis.readUnsignedShort();
       for(int idx = 0; idx < numberOfBytes; idx++)
       {
           OneByteChunk anX = new OneByteChunk();
           anX.unmarshal(dis);
           dataValues.add(anX);
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
       buff.putShort( (short)dataValues.size());

       for(int idx = 0; idx < dataValues.size(); idx++)
       {
            OneByteChunk aOneByteChunk = (OneByteChunk)dataValues.get(idx);
            aOneByteChunk.marshal(buff);
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

       numberOfBytes = (int)(buff.getShort() & 0xFFFF);
       for(int idx = 0; idx < numberOfBytes; idx++)
       {
            OneByteChunk anX = new OneByteChunk();
            anX.unmarshal(buff);
            dataValues.add(anX);
       }

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

    if(!(obj instanceof GridAxisRecordRepresentation0))
        return false;

     final GridAxisRecordRepresentation0 rhs = (GridAxisRecordRepresentation0)obj;

     if( ! (numberOfBytes == rhs.numberOfBytes)) ivarsEqual = false;

     for(int idx = 0; idx < dataValues.size(); idx++)
     {
        if( ! ( dataValues.get(idx).equals(rhs.dataValues.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual && super.equalsImpl(rhs);
 }
} // end of class
