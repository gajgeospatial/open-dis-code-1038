package edu.nps.moves.dis7mobile;

import java.util.*;
import java.io.*;
import edu.nps.moves.disenum.*;
import edu.nps.moves.disutil.*;


/**
 * The unique designation of a minefield Section 6.2.56 
 *
 * Copyright (c) 2008-2014, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class MinefieldIdentifier extends Object implements Serializable
{
   /**  */
   protected SimulationAddress  simulationAddress = new SimulationAddress(); 

   /**  */
   protected int  minefieldNumber;


/** Constructor */
 public MinefieldIdentifier()
 {
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + simulationAddress.getMarshalledSize();  // simulationAddress
   marshalSize = marshalSize + 2;  // minefieldNumber

   return marshalSize;
}


public void setSimulationAddress(SimulationAddress pSimulationAddress)
{ simulationAddress = pSimulationAddress;
}

public SimulationAddress getSimulationAddress()
{ return simulationAddress; 
}

public void setMinefieldNumber(int pMinefieldNumber)
{ minefieldNumber = pMinefieldNumber;
}

public int getMinefieldNumber()
{ return minefieldNumber; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       simulationAddress.marshal(dos);
       dos.writeShort( (short)minefieldNumber);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       simulationAddress.unmarshal(dis);
       minefieldNumber = (int)dis.readUnsignedShort();
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
       simulationAddress.marshal(buff);
       buff.putShort( (short)minefieldNumber);
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
       simulationAddress.unmarshal(buff);
       minefieldNumber = (int)(buff.getShort() & 0xFFFF);
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

    if(!(obj instanceof MinefieldIdentifier))
        return false;

     final MinefieldIdentifier rhs = (MinefieldIdentifier)obj;

     if( ! (simulationAddress.equals( rhs.simulationAddress) )) ivarsEqual = false;
     if( ! (minefieldNumber == rhs.minefieldNumber)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
