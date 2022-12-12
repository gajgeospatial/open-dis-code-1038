package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * 5.2.42. Basic operational data ofr IFF ATC NAVAIDS
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class IffFundamentalData extends Object implements Serializable
{
   /** system status */
   protected short  systemStatus;

   /** Alternate parameter 4 */
   protected short  alternateParameter4;

   /** eight boolean fields */
   protected short  informationLayers;

   /** enumeration */
   protected short  modifier;

   /** parameter, enumeration */
   protected int  parameter1;

   /** parameter, enumeration */
   protected int  parameter2;

   /** parameter, enumeration */
   protected int  parameter3;

   /** parameter, enumeration */
   protected int  parameter4;

   /** parameter, enumeration */
   protected int  parameter5;

   /** parameter, enumeration */
   protected int  parameter6;


/** Constructor */
 public IffFundamentalData()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public IffFundamentalData(edu.nps.moves.jaxb.dis.IffFundamentalData x)
 {
     this.systemStatus = x.getSystemStatus();
     this.alternateParameter4 = x.getAlternateParameter4();
     this.informationLayers = x.getInformationLayers();
     this.modifier = x.getModifier();
     this.parameter1 = x.getParameter1();
     this.parameter2 = x.getParameter2();
     this.parameter3 = x.getParameter3();
     this.parameter4 = x.getParameter4();
     this.parameter5 = x.getParameter5();
     this.parameter6 = x.getParameter6();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.IffFundamentalData initializeJaxbObject(edu.nps.moves.jaxb.dis.IffFundamentalData x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setSystemStatus( this.getSystemStatus() );
     x.setAlternateParameter4( this.getAlternateParameter4() );
     x.setInformationLayers( this.getInformationLayers() );
     x.setModifier( this.getModifier() );
     x.setParameter1( this.getParameter1() );
     x.setParameter2( this.getParameter2() );
     x.setParameter3( this.getParameter3() );
     x.setParameter4( this.getParameter4() );
     x.setParameter5( this.getParameter5() );
     x.setParameter6( this.getParameter6() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 1;  // systemStatus
   marshalSize = marshalSize + 1;  // alternateParameter4
   marshalSize = marshalSize + 1;  // informationLayers
   marshalSize = marshalSize + 1;  // modifier
   marshalSize = marshalSize + 2;  // parameter1
   marshalSize = marshalSize + 2;  // parameter2
   marshalSize = marshalSize + 2;  // parameter3
   marshalSize = marshalSize + 2;  // parameter4
   marshalSize = marshalSize + 2;  // parameter5
   marshalSize = marshalSize + 2;  // parameter6

   return marshalSize;
}


public void setSystemStatus(short pSystemStatus)
{ systemStatus = pSystemStatus;
}

public short getSystemStatus()
{ return systemStatus; 
}

public void setAlternateParameter4(short pAlternateParameter4)
{ alternateParameter4 = pAlternateParameter4;
}

public short getAlternateParameter4()
{ return alternateParameter4; 
}

public void setInformationLayers(short pInformationLayers)
{ informationLayers = pInformationLayers;
}

public short getInformationLayers()
{ return informationLayers; 
}

public void setModifier(short pModifier)
{ modifier = pModifier;
}

public short getModifier()
{ return modifier; 
}

public void setParameter1(int pParameter1)
{ parameter1 = pParameter1;
}

public int getParameter1()
{ return parameter1; 
}

public void setParameter2(int pParameter2)
{ parameter2 = pParameter2;
}

public int getParameter2()
{ return parameter2; 
}

public void setParameter3(int pParameter3)
{ parameter3 = pParameter3;
}

public int getParameter3()
{ return parameter3; 
}

public void setParameter4(int pParameter4)
{ parameter4 = pParameter4;
}

public int getParameter4()
{ return parameter4; 
}

public void setParameter5(int pParameter5)
{ parameter5 = pParameter5;
}

public int getParameter5()
{ return parameter5; 
}

public void setParameter6(int pParameter6)
{ parameter6 = pParameter6;
}

public int getParameter6()
{ return parameter6; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeByte( (byte)systemStatus);
       dos.writeByte( (byte)alternateParameter4);
       dos.writeByte( (byte)informationLayers);
       dos.writeByte( (byte)modifier);
       dos.writeShort( (short)parameter1);
       dos.writeShort( (short)parameter2);
       dos.writeShort( (short)parameter3);
       dos.writeShort( (short)parameter4);
       dos.writeShort( (short)parameter5);
       dos.writeShort( (short)parameter6);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       systemStatus = dis.readByte();
       alternateParameter4 = dis.readByte();
       informationLayers = dis.readByte();
       modifier = dis.readByte();
       parameter1 = dis.readShort();
       parameter2 = dis.readShort();
       parameter3 = dis.readShort();
       parameter4 = dis.readShort();
       parameter5 = dis.readShort();
       parameter6 = dis.readShort();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(IffFundamentalData rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (systemStatus == rhs.systemStatus)) ivarsEqual = false;
     if( ! (alternateParameter4 == rhs.alternateParameter4)) ivarsEqual = false;
     if( ! (informationLayers == rhs.informationLayers)) ivarsEqual = false;
     if( ! (modifier == rhs.modifier)) ivarsEqual = false;
     if( ! (parameter1 == rhs.parameter1)) ivarsEqual = false;
     if( ! (parameter2 == rhs.parameter2)) ivarsEqual = false;
     if( ! (parameter3 == rhs.parameter3)) ivarsEqual = false;
     if( ! (parameter4 == rhs.parameter4)) ivarsEqual = false;
     if( ! (parameter5 == rhs.parameter5)) ivarsEqual = false;
     if( ! (parameter6 == rhs.parameter6)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
