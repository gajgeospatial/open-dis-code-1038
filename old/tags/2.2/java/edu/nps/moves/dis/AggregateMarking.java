package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.2.37. Specifies the character set used inthe first byte, followed by up to 31 characters of text data.
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class AggregateMarking extends Object implements Serializable
{
   /** The character set */
   protected short  characterSet;

   /** The characters */
   protected byte[]  characters = new byte[31]; 


/** Constructor */
 public AggregateMarking()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public AggregateMarking(edu.nps.moves.jaxb.dis.AggregateMarking x)
 {
     this.characterSet = x.getCharacterSet();
     this.characters = new byte[31];
     for(int idx = 0; idx < 31; idx++)
     {
         byte[] y = x.getCharacters();
         this.characters[idx] = y[idx];
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.AggregateMarking initializeJaxbObject(edu.nps.moves.jaxb.dis.AggregateMarking x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setCharacterSet( this.getCharacterSet() );
     x.setCharacters( new byte[31]);
     for(int idx = 0; idx < 31; idx++)
     {
         x.getCharacters()[idx] = this.characters[idx];
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 1;  // characterSet
   marshalSize = marshalSize + 31 * 1;  // characters

   return marshalSize;
}


public void setCharacterSet(short pCharacterSet)
{ characterSet = pCharacterSet;
}

public short getCharacterSet()
{ return characterSet; 
}

public void setCharacters(byte[] pCharacters)
{ characters = pCharacters;
}

public byte[] getCharacters()
{ return characters; }


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeByte( (byte)characterSet);

       for(int idx = 0; idx < characters.length; idx++)
       {
           dos.writeByte(characters[idx]);
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
       characterSet = dis.readByte();
       for(int idx = 0; idx < characters.length; idx++)
       {
                characters[idx] = dis.readByte();
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
 public boolean equals(AggregateMarking rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (characterSet == rhs.characterSet)) ivarsEqual = false;

     for(int idx = 0; idx < 31; idx++)
     {
          if(!(characters[idx] == rhs.characters[idx])) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
