package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.2.40. Information about a geometry, a state associated with a geometry, a bounding volume, or an associated entity ID. NOTE: this class requires hand coding.
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class Environment extends Object implements Serializable
{
   /** Record type */
   protected long  environmentType;

   /** length, in bits */
   protected short  length;

   /** Identify the sequentially numbered record index */
   protected short  index;

   /** padding */
   protected short  padding1;

   /** Geometry or state record */
   protected short  geometry;

   /** padding to bring the total size up to a 64 bit boundry */
   protected short  padding2;


/** Constructor */
 public Environment()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public Environment(edu.nps.moves.jaxb.dis.Environment x)
 {
     this.environmentType = x.getEnvironmentType();
     this.length = x.getLength();
     this.index = x.getIndex();
     this.padding1 = x.getPadding1();
     this.geometry = x.getGeometry();
     this.padding2 = x.getPadding2();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.Environment initializeJaxbObject(edu.nps.moves.jaxb.dis.Environment x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setEnvironmentType( this.getEnvironmentType() );
     x.setLength( this.getLength() );
     x.setIndex( this.getIndex() );
     x.setPadding1( this.getPadding1() );
     x.setGeometry( this.getGeometry() );
     x.setPadding2( this.getPadding2() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 4;  // environmentType
   marshalSize = marshalSize + 1;  // length
   marshalSize = marshalSize + 1;  // index
   marshalSize = marshalSize + 1;  // padding1
   marshalSize = marshalSize + 1;  // geometry
   marshalSize = marshalSize + 1;  // padding2

   return marshalSize;
}


public void setEnvironmentType(long pEnvironmentType)
{ environmentType = pEnvironmentType;
}

public long getEnvironmentType()
{ return environmentType; 
}

public void setLength(short pLength)
{ length = pLength;
}

public short getLength()
{ return length; 
}

public void setIndex(short pIndex)
{ index = pIndex;
}

public short getIndex()
{ return index; 
}

public void setPadding1(short pPadding1)
{ padding1 = pPadding1;
}

public short getPadding1()
{ return padding1; 
}

public void setGeometry(short pGeometry)
{ geometry = pGeometry;
}

public short getGeometry()
{ return geometry; 
}

public void setPadding2(short pPadding2)
{ padding2 = pPadding2;
}

public short getPadding2()
{ return padding2; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeInt( (int)environmentType);
       dos.writeByte( (byte)length);
       dos.writeByte( (byte)index);
       dos.writeByte( (byte)padding1);
       dos.writeByte( (byte)geometry);
       dos.writeByte( (byte)padding2);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       environmentType = dis.readInt();
       length = dis.readByte();
       index = dis.readByte();
       padding1 = dis.readByte();
       geometry = dis.readByte();
       padding2 = dis.readByte();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(Environment rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (environmentType == rhs.environmentType)) ivarsEqual = false;
     if( ! (length == rhs.length)) ivarsEqual = false;
     if( ! (index == rhs.index)) ivarsEqual = false;
     if( ! (padding1 == rhs.padding1)) ivarsEqual = false;
     if( ! (geometry == rhs.geometry)) ivarsEqual = false;
     if( ! (padding2 == rhs.padding2)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
