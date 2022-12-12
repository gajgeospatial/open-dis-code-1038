package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * 5.2.56. Purpose for joinging two entities
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class Relationship extends Object implements Serializable
{
   /** Nature of join */
   protected int  nature;

   /** position of join */
   protected int  position;


/** Constructor */
 public Relationship()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public Relationship(edu.nps.moves.jaxb.dis.Relationship x)
 {
     this.nature = x.getNature();
     this.position = x.getPosition();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.Relationship initializeJaxbObject(edu.nps.moves.jaxb.dis.Relationship x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setNature( this.getNature() );
     x.setPosition( this.getPosition() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 2;  // nature
   marshalSize = marshalSize + 2;  // position

   return marshalSize;
}


public void setNature(int pNature)
{ nature = pNature;
}

public int getNature()
{ return nature; 
}

public void setPosition(int pPosition)
{ position = pPosition;
}

public int getPosition()
{ return position; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeShort( (short)nature);
       dos.writeShort( (short)position);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       nature = dis.readShort();
       position = dis.readShort();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(Relationship rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (nature == rhs.nature)) ivarsEqual = false;
     if( ! (position == rhs.position)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
