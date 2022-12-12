package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.2.11. This field shall specify information about a particular emitter system
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class EmitterSystem extends Object implements Serializable
{
   /** Name of the emitter, 16 bit enumeration */
   protected int  emitterName;

   /** function of the emitter, 8 bit enumeration */
   protected short  function;

   /** emitter ID, 8 bit enumeration */
   protected short  emitterIdNumber;


/** Constructor */
 public EmitterSystem()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public EmitterSystem(edu.nps.moves.jaxb.dis.EmitterSystem x)
 {
     this.emitterName = x.getEmitterName();
     this.function = x.getFunction();
     this.emitterIdNumber = x.getEmitterIdNumber();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.EmitterSystem initializeJaxbObject(edu.nps.moves.jaxb.dis.EmitterSystem x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setEmitterName( this.getEmitterName() );
     x.setFunction( this.getFunction() );
     x.setEmitterIdNumber( this.getEmitterIdNumber() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 2;  // emitterName
   marshalSize = marshalSize + 1;  // function
   marshalSize = marshalSize + 1;  // emitterIdNumber

   return marshalSize;
}


public void setEmitterName(int pEmitterName)
{ emitterName = pEmitterName;
}

public int getEmitterName()
{ return emitterName; 
}

public void setFunction(short pFunction)
{ function = pFunction;
}

public short getFunction()
{ return function; 
}

public void setEmitterIdNumber(short pEmitterIdNumber)
{ emitterIdNumber = pEmitterIdNumber;
}

public short getEmitterIdNumber()
{ return emitterIdNumber; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeShort( (short)emitterName);
       dos.writeByte( (byte)function);
       dos.writeByte( (byte)emitterIdNumber);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       emitterName = dis.readShort();
       function = dis.readByte();
       emitterIdNumber = dis.readByte();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(EmitterSystem rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (emitterName == rhs.emitterName)) ivarsEqual = false;
     if( ! (function == rhs.function)) ivarsEqual = false;
     if( ! (emitterIdNumber == rhs.emitterIdNumber)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
