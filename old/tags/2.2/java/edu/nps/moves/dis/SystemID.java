package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * 5.2.58. Used in IFF ATC PDU
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class SystemID extends Object implements Serializable
{
   /** System Type */
   protected int  systemType;

   /** System name, an enumeration */
   protected int  systemName;

   /** System mode */
   protected short  systemMode;

   /** Change Options */
   protected short  changeOptions;


/** Constructor */
 public SystemID()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public SystemID(edu.nps.moves.jaxb.dis.SystemID x)
 {
     this.systemType = x.getSystemType();
     this.systemName = x.getSystemName();
     this.systemMode = x.getSystemMode();
     this.changeOptions = x.getChangeOptions();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.SystemID initializeJaxbObject(edu.nps.moves.jaxb.dis.SystemID x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setSystemType( this.getSystemType() );
     x.setSystemName( this.getSystemName() );
     x.setSystemMode( this.getSystemMode() );
     x.setChangeOptions( this.getChangeOptions() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 2;  // systemType
   marshalSize = marshalSize + 2;  // systemName
   marshalSize = marshalSize + 1;  // systemMode
   marshalSize = marshalSize + 1;  // changeOptions

   return marshalSize;
}


public void setSystemType(int pSystemType)
{ systemType = pSystemType;
}

public int getSystemType()
{ return systemType; 
}

public void setSystemName(int pSystemName)
{ systemName = pSystemName;
}

public int getSystemName()
{ return systemName; 
}

public void setSystemMode(short pSystemMode)
{ systemMode = pSystemMode;
}

public short getSystemMode()
{ return systemMode; 
}

public void setChangeOptions(short pChangeOptions)
{ changeOptions = pChangeOptions;
}

public short getChangeOptions()
{ return changeOptions; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeShort( (short)systemType);
       dos.writeShort( (short)systemName);
       dos.writeByte( (byte)systemMode);
       dos.writeByte( (byte)changeOptions);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       systemType = dis.readShort();
       systemName = dis.readShort();
       systemMode = dis.readByte();
       changeOptions = dis.readByte();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(SystemID rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (systemType == rhs.systemType)) ivarsEqual = false;
     if( ! (systemName == rhs.systemName)) ivarsEqual = false;
     if( ! (systemMode == rhs.systemMode)) ivarsEqual = false;
     if( ! (changeOptions == rhs.changeOptions)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
