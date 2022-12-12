package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Record sets, used in transfer control request PDU
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class RecordSet extends Object implements Serializable
{
   /** record ID */
   protected long  recordID;

   /** record set serial number */
   protected long  recordSetSerialNumber;

   /** record length */
   protected int  recordLength;

   /** record count */
   protected int  recordCount;

   /** @@@This is wrong--variable sized data records */
   protected int  recordValues;

   /** @@@This is wrong--variable sized padding */
   protected short  pad4;


/** Constructor */
 public RecordSet()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public RecordSet(edu.nps.moves.jaxb.dis.RecordSet x)
 {
     this.recordID = x.getRecordID();
     this.recordSetSerialNumber = x.getRecordSetSerialNumber();
     this.recordLength = x.getRecordLength();
     this.recordCount = x.getRecordCount();
     this.recordValues = x.getRecordValues();
     this.pad4 = x.getPad4();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.RecordSet initializeJaxbObject(edu.nps.moves.jaxb.dis.RecordSet x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setRecordID( this.getRecordID() );
     x.setRecordSetSerialNumber( this.getRecordSetSerialNumber() );
     x.setRecordLength( this.getRecordLength() );
     x.setRecordCount( this.getRecordCount() );
     x.setRecordValues( this.getRecordValues() );
     x.setPad4( this.getPad4() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 4;  // recordID
   marshalSize = marshalSize + 4;  // recordSetSerialNumber
   marshalSize = marshalSize + 2;  // recordLength
   marshalSize = marshalSize + 2;  // recordCount
   marshalSize = marshalSize + 2;  // recordValues
   marshalSize = marshalSize + 1;  // pad4

   return marshalSize;
}


public void setRecordID(long pRecordID)
{ recordID = pRecordID;
}

public long getRecordID()
{ return recordID; 
}

public void setRecordSetSerialNumber(long pRecordSetSerialNumber)
{ recordSetSerialNumber = pRecordSetSerialNumber;
}

public long getRecordSetSerialNumber()
{ return recordSetSerialNumber; 
}

public void setRecordLength(int pRecordLength)
{ recordLength = pRecordLength;
}

public int getRecordLength()
{ return recordLength; 
}

public void setRecordCount(int pRecordCount)
{ recordCount = pRecordCount;
}

public int getRecordCount()
{ return recordCount; 
}

public void setRecordValues(int pRecordValues)
{ recordValues = pRecordValues;
}

public int getRecordValues()
{ return recordValues; 
}

public void setPad4(short pPad4)
{ pad4 = pPad4;
}

public short getPad4()
{ return pad4; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeInt( (int)recordID);
       dos.writeInt( (int)recordSetSerialNumber);
       dos.writeShort( (short)recordLength);
       dos.writeShort( (short)recordCount);
       dos.writeShort( (short)recordValues);
       dos.writeByte( (byte)pad4);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       recordID = dis.readInt();
       recordSetSerialNumber = dis.readInt();
       recordLength = dis.readShort();
       recordCount = dis.readShort();
       recordValues = dis.readShort();
       pad4 = dis.readByte();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(RecordSet rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (recordID == rhs.recordID)) ivarsEqual = false;
     if( ! (recordSetSerialNumber == rhs.recordSetSerialNumber)) ivarsEqual = false;
     if( ! (recordLength == rhs.recordLength)) ivarsEqual = false;
     if( ! (recordCount == rhs.recordCount)) ivarsEqual = false;
     if( ! (recordValues == rhs.recordValues)) ivarsEqual = false;
     if( ! (pad4 == rhs.pad4)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
