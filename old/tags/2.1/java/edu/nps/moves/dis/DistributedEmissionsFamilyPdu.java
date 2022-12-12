package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.7. Electronic Emissions. Abstract superclass for distirubted emissions PDU
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class DistributedEmissionsFamilyPdu extends Pdu implements Serializable
{

/** Constructor */
 public DistributedEmissionsFamilyPdu()
 {
    setProtocolFamily( (short)6 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public DistributedEmissionsFamilyPdu(edu.nps.moves.jaxb.dis.DistributedEmissionsFamilyPdu x)
 {
     super(x); // Call superclass constructor

 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.DistributedEmissionsFamilyPdu initializeJaxbObject(edu.nps.moves.jaxb.dis.DistributedEmissionsFamilyPdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();

   return marshalSize;
}



public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
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
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(DistributedEmissionsFamilyPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;


    return ivarsEqual;
 }
} // end of class
