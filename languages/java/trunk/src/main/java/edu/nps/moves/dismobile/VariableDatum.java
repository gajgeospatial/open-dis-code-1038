package edu.nps.moves.dismobile;

import java.util.*;
import java.io.*;
import edu.nps.moves.disenum.*;
import edu.nps.moves.disutil.*;


/**
 * Section 5.2.32. Variable Datum Record
 *
 * Copyright (c) 2008-2014, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class VariableDatum extends Object implements Serializable
{
   /** ID of the variable datum */
   protected long  variableDatumID;

   /** length of the variable datums, in bits. Note that this is not programmatically tied to the size of the variableData. The variable data field may be 64 bits long but only 16 bits of it could actually be used. */
   protected long  variableDatumLength;

   /** data can be any length, but must increase in 8 byte quanta. This requires some postprocessing patches. Note that setting the data allocates a new internal array to account for the possibly increased size. The default initial size is 64 bits. */
   protected byte[]  variableData = new byte[8]; 


/** Constructor */
 public VariableDatum()
 {
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 4;  // variableDatumID
   marshalSize = marshalSize + 4;  // variableDatumLength
   marshalSize = marshalSize + 8 * 1;  // variableData

   return marshalSize;
}


public void setVariableDatumID(long pVariableDatumID)
{ variableDatumID = pVariableDatumID;
}

public long getVariableDatumID()
{ return variableDatumID; 
}

public void setVariableDatumLength(long pVariableDatumLength)
{ variableDatumLength = pVariableDatumLength;
}

public long getVariableDatumLength()
{ return variableDatumLength; 
}

public void setVariableData(byte[] pVariableData)
{ variableData = pVariableData;
}

public byte[] getVariableData()
{ return variableData; }


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeInt( (int)variableDatumID);
       dos.writeInt( (int)variableDatumLength);

       for(int idx = 0; idx < variableData.length; idx++)
       {
           dos.writeByte(variableData[idx]);
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
       variableDatumID = dis.readInt();
       variableDatumLength = dis.readInt();
       for(int idx = 0; idx < variableData.length; idx++)
       {
                variableData[idx] = dis.readByte();
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
       buff.putInt( (int)variableDatumID);
       buff.putInt( (int)variableDatumLength);

       for(int idx = 0; idx < variableData.length; idx++)
       {
           buff.put((byte)variableData[idx]);
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
       variableDatumID = buff.getInt();
       variableDatumLength = buff.getInt();
       for(int idx = 0; idx < variableData.length; idx++)
       {
                variableData[idx] = buff.get();
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

    if(!(obj instanceof VariableDatum))
        return false;

     final VariableDatum rhs = (VariableDatum)obj;

     if( ! (variableDatumID == rhs.variableDatumID)) ivarsEqual = false;
     if( ! (variableDatumLength == rhs.variableDatumLength)) ivarsEqual = false;

     for(int idx = 0; idx < 8; idx++)
     {
          if(!(variableData[idx] == rhs.variableData[idx])) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
