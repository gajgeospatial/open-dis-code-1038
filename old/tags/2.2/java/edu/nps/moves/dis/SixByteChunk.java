package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * 48 bit piece of data
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class SixByteChunk extends Object implements Serializable
{
   /** six bytes of arbitrary data */
   protected byte[]  otherParameters = new byte[6]; 


/** Constructor */
 public SixByteChunk()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public SixByteChunk(edu.nps.moves.jaxb.dis.SixByteChunk x)
 {
     this.otherParameters = new byte[6];
     for(int idx = 0; idx < 6; idx++)
     {
         byte[] y = x.getOtherParameters();
         this.otherParameters[idx] = y[idx];
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.SixByteChunk initializeJaxbObject(edu.nps.moves.jaxb.dis.SixByteChunk x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setOtherParameters( new byte[6]);
     for(int idx = 0; idx < 6; idx++)
     {
         x.getOtherParameters()[idx] = this.otherParameters[idx];
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 6 * 1;  // otherParameters

   return marshalSize;
}


public void setOtherParameters(byte[] pOtherParameters)
{ otherParameters = pOtherParameters;
}

public byte[] getOtherParameters()
{ return otherParameters; }


public void marshal(DataOutputStream dos)
{
    try 
    {

       for(int idx = 0; idx < otherParameters.length; idx++)
       {
           dos.writeByte(otherParameters[idx]);
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
       for(int idx = 0; idx < otherParameters.length; idx++)
       {
                otherParameters[idx] = dis.readByte();
       } // end of array unmarshaling
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(SixByteChunk rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;


     for(int idx = 0; idx < 6; idx++)
     {
          if(!(otherParameters[idx] == rhs.otherParameters[idx])) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
