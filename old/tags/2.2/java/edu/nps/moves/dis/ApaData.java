package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Used in UA PDU
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class ApaData extends Object implements Serializable
{
   /** Index of APA parameter */
   protected int  parameterIndex;

   /** Index of APA parameter */
   protected short  parameterValue;


/** Constructor */
 public ApaData()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public ApaData(edu.nps.moves.jaxb.dis.ApaData x)
 {
     this.parameterIndex = x.getParameterIndex();
     this.parameterValue = x.getParameterValue();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.ApaData initializeJaxbObject(edu.nps.moves.jaxb.dis.ApaData x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setParameterIndex( this.getParameterIndex() );
     x.setParameterValue( this.getParameterValue() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 2;  // parameterIndex
   marshalSize = marshalSize + 2;  // parameterValue

   return marshalSize;
}


public void setParameterIndex(int pParameterIndex)
{ parameterIndex = pParameterIndex;
}

public int getParameterIndex()
{ return parameterIndex; 
}

public void setParameterValue(short pParameterValue)
{ parameterValue = pParameterValue;
}

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
       parameterIndex = dis.readShort();
       parameterValue = dis.readShort();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
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
