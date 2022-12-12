package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * 5.2.45. Fundamental IFF atc data
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class FundamentalParameterDataIff extends Object implements Serializable
{
   /** ERP */
   protected float  erp;

   /** frequency */
   protected float  frequency;

   /** pgrf */
   protected float  pgrf;

   /** Pulse width */
   protected float  pulseWidth;

   /** Burst length */
   protected long  burstLength;

   /** Applicable modes enumeration */
   protected short  applicableModes;

   /** padding */
   protected int  pad2;

   /** padding */
   protected short  pad3;


/** Constructor */
 public FundamentalParameterDataIff()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public FundamentalParameterDataIff(edu.nps.moves.jaxb.dis.FundamentalParameterDataIff x)
 {
     this.erp = x.getErp();
     this.frequency = x.getFrequency();
     this.pgrf = x.getPgrf();
     this.pulseWidth = x.getPulseWidth();
     this.burstLength = x.getBurstLength();
     this.applicableModes = x.getApplicableModes();
     this.pad2 = x.getPad2();
     this.pad3 = x.getPad3();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.FundamentalParameterDataIff initializeJaxbObject(edu.nps.moves.jaxb.dis.FundamentalParameterDataIff x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setErp( this.getErp() );
     x.setFrequency( this.getFrequency() );
     x.setPgrf( this.getPgrf() );
     x.setPulseWidth( this.getPulseWidth() );
     x.setBurstLength( this.getBurstLength() );
     x.setApplicableModes( this.getApplicableModes() );
     x.setPad2( this.getPad2() );
     x.setPad3( this.getPad3() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 4;  // erp
   marshalSize = marshalSize + 4;  // frequency
   marshalSize = marshalSize + 4;  // pgrf
   marshalSize = marshalSize + 4;  // pulseWidth
   marshalSize = marshalSize + 4;  // burstLength
   marshalSize = marshalSize + 1;  // applicableModes
   marshalSize = marshalSize + 2;  // pad2
   marshalSize = marshalSize + 1;  // pad3

   return marshalSize;
}


public void setErp(float pErp)
{ erp = pErp;
}

public float getErp()
{ return erp; 
}

public void setFrequency(float pFrequency)
{ frequency = pFrequency;
}

public float getFrequency()
{ return frequency; 
}

public void setPgrf(float pPgrf)
{ pgrf = pPgrf;
}

public float getPgrf()
{ return pgrf; 
}

public void setPulseWidth(float pPulseWidth)
{ pulseWidth = pPulseWidth;
}

public float getPulseWidth()
{ return pulseWidth; 
}

public void setBurstLength(long pBurstLength)
{ burstLength = pBurstLength;
}

public long getBurstLength()
{ return burstLength; 
}

public void setApplicableModes(short pApplicableModes)
{ applicableModes = pApplicableModes;
}

public short getApplicableModes()
{ return applicableModes; 
}

public void setPad2(int pPad2)
{ pad2 = pPad2;
}

public int getPad2()
{ return pad2; 
}

public void setPad3(short pPad3)
{ pad3 = pPad3;
}

public short getPad3()
{ return pad3; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeFloat( (float)erp);
       dos.writeFloat( (float)frequency);
       dos.writeFloat( (float)pgrf);
       dos.writeFloat( (float)pulseWidth);
       dos.writeInt( (int)burstLength);
       dos.writeByte( (byte)applicableModes);
       dos.writeShort( (short)pad2);
       dos.writeByte( (byte)pad3);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       erp = dis.readFloat();
       frequency = dis.readFloat();
       pgrf = dis.readFloat();
       pulseWidth = dis.readFloat();
       burstLength = dis.readInt();
       applicableModes = dis.readByte();
       pad2 = dis.readShort();
       pad3 = dis.readByte();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(FundamentalParameterDataIff rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (erp == rhs.erp)) ivarsEqual = false;
     if( ! (frequency == rhs.frequency)) ivarsEqual = false;
     if( ! (pgrf == rhs.pgrf)) ivarsEqual = false;
     if( ! (pulseWidth == rhs.pulseWidth)) ivarsEqual = false;
     if( ! (burstLength == rhs.burstLength)) ivarsEqual = false;
     if( ! (applicableModes == rhs.applicableModes)) ivarsEqual = false;
     if( ! (pad2 == rhs.pad2)) ivarsEqual = false;
     if( ! (pad3 == rhs.pad3)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
