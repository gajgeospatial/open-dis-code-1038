package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * 5.2.46.  Intercom communcations parameters
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class IntercomCommunicationsParameters extends Object implements Serializable
{
   /** Type of intercom parameters record */
   protected int  recordType;

   /** length of record */
   protected int  recordLength;

   /** Jerks. Looks like the committee is forcing a lookup of the record type parameter to find out how long the field is. This is a placeholder. */
   protected long  recordSpecificField;


/** Constructor */
 public IntercomCommunicationsParameters()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public IntercomCommunicationsParameters(edu.nps.moves.jaxb.dis.IntercomCommunicationsParameters x)
 {
     this.recordType = x.getRecordType();
     this.recordLength = x.getRecordLength();
     this.recordSpecificField = x.getRecordSpecificField();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.IntercomCommunicationsParameters initializeJaxbObject(edu.nps.moves.jaxb.dis.IntercomCommunicationsParameters x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setRecordType( this.getRecordType() );
     x.setRecordLength( this.getRecordLength() );
     x.setRecordSpecificField( this.getRecordSpecificField() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 2;  // recordType
   marshalSize = marshalSize + 2;  // recordLength
   marshalSize = marshalSize + 4;  // recordSpecificField

   return marshalSize;
}


public void setRecordType(int pRecordType)
{ recordType = pRecordType;
}

public int getRecordType()
{ return recordType; 
}

public void setRecordLength(int pRecordLength)
{ recordLength = pRecordLength;
}

public int getRecordLength()
{ return recordLength; 
}

public void setRecordSpecificField(long pRecordSpecificField)
{ recordSpecificField = pRecordSpecificField;
}

public long getRecordSpecificField()
{ return recordSpecificField; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeShort( (short)recordType);
       dos.writeShort( (short)recordLength);
       dos.writeInt( (int)recordSpecificField);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       recordType = dis.readShort();
       recordLength = dis.readShort();
       recordSpecificField = dis.readInt();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(IntercomCommunicationsParameters rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (recordType == rhs.recordType)) ivarsEqual = false;
     if( ! (recordLength == rhs.recordLength)) ivarsEqual = false;
     if( ! (recordSpecificField == rhs.recordSpecificField)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
