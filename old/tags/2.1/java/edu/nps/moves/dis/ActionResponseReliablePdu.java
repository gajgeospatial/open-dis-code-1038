package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.12.7: Response from an entity to an action request PDU. COMPLETE
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class ActionResponseReliablePdu extends SimulationManagementWithReliabilityFamilyPdu implements Serializable
{
   /** request ID */
   protected long  requestID;

   /** status of response */
   protected long  responseStatus;

   /** Fixed datum record count */
   protected long  numberOfFixedDatumRecords;

   /** variable datum record count */
   protected long  numberOfVariableDatumRecords;

   /** Fixed datum records */
   protected List fixedDatumRecords = new ArrayList(); 
   /** Variable datum records */
   protected List variableDatumRecords = new ArrayList(); 

/** Constructor */
 public ActionResponseReliablePdu()
 {
    setPduType( (short)57 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public ActionResponseReliablePdu(edu.nps.moves.jaxb.dis.ActionResponseReliablePdu x)
 {
     super(x); // Call superclass constructor

     this.requestID = x.getRequestID();
     this.responseStatus = x.getResponseStatus();
     this.numberOfFixedDatumRecords = x.getNumberOfFixedDatumRecords();
     this.numberOfVariableDatumRecords = x.getNumberOfVariableDatumRecords();
     this.fixedDatumRecords = new ArrayList();
     for(int idx = 0; idx < x.getFixedDatumRecords().size(); idx++)
     {
        this.fixedDatumRecords.add( new edu.nps.moves.dis.FixedDatum((edu.nps.moves.jaxb.dis.FixedDatum) x.getFixedDatumRecords().get(idx)));
     }
     this.variableDatumRecords = new ArrayList();
     for(int idx = 0; idx < x.getVariableDatumRecords().size(); idx++)
     {
        this.variableDatumRecords.add( new edu.nps.moves.dis.VariableDatum((edu.nps.moves.jaxb.dis.VariableDatum) x.getVariableDatumRecords().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.ActionResponseReliablePdu initializeJaxbObject(edu.nps.moves.jaxb.dis.ActionResponseReliablePdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setRequestID( this.getRequestID() );
     x.setResponseStatus( this.getResponseStatus() );
     x.setNumberOfFixedDatumRecords( this.getNumberOfFixedDatumRecords() );
     x.setNumberOfVariableDatumRecords( this.getNumberOfVariableDatumRecords() );

     List fixedDatumRecords_1 = x.getFixedDatumRecords();
     for(int idx = 0; idx < fixedDatumRecords.size(); idx++)
     {
         FixedDatum a = (edu.nps.moves.dis.FixedDatum)fixedDatumRecords.get(idx);
         fixedDatumRecords_1.add(a.initializeJaxbObject(factory.createFixedDatum()));
     }

     List variableDatumRecords_1 = x.getVariableDatumRecords();
     for(int idx = 0; idx < variableDatumRecords.size(); idx++)
     {
         VariableDatum a = (edu.nps.moves.dis.VariableDatum)variableDatumRecords.get(idx);
         variableDatumRecords_1.add(a.initializeJaxbObject(factory.createVariableDatum()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + 4;  // requestID
   marshalSize = marshalSize + 4;  // responseStatus
   marshalSize = marshalSize + 4;  // numberOfFixedDatumRecords
   marshalSize = marshalSize + 4;  // numberOfVariableDatumRecords
   for(int idx=0; idx < fixedDatumRecords.size(); idx++)
   {
        FixedDatum listElement = (FixedDatum)fixedDatumRecords.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }
   for(int idx=0; idx < variableDatumRecords.size(); idx++)
   {
        VariableDatum listElement = (VariableDatum)variableDatumRecords.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setRequestID(long pRequestID)
{ requestID = pRequestID;
}

public long getRequestID()
{ return requestID; 
}

public void setResponseStatus(long pResponseStatus)
{ responseStatus = pResponseStatus;
}

public long getResponseStatus()
{ return responseStatus; 
}

public long getNumberOfFixedDatumRecords()
{ return (long)fixedDatumRecords.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfFixedDatumRecords method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfFixedDatumRecords(long pNumberOfFixedDatumRecords)
{ numberOfFixedDatumRecords = pNumberOfFixedDatumRecords;
}

public long getNumberOfVariableDatumRecords()
{ return (long)variableDatumRecords.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfVariableDatumRecords method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfVariableDatumRecords(long pNumberOfVariableDatumRecords)
{ numberOfVariableDatumRecords = pNumberOfVariableDatumRecords;
}

public void setFixedDatumRecords(List pFixedDatumRecords)
{ fixedDatumRecords = pFixedDatumRecords;
}

public List getFixedDatumRecords()
{ return fixedDatumRecords; }

public void setVariableDatumRecords(List pVariableDatumRecords)
{ variableDatumRecords = pVariableDatumRecords;
}

public List getVariableDatumRecords()
{ return variableDatumRecords; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       dos.writeInt( (int)requestID);
       dos.writeInt( (int)responseStatus);
       dos.writeInt( (int)fixedDatumRecords.size());
       dos.writeInt( (int)variableDatumRecords.size());

       for(int idx = 0; idx < fixedDatumRecords.size(); idx++)
       {
            FixedDatum aFixedDatum = (FixedDatum)fixedDatumRecords.get(idx);
            aFixedDatum.marshal(dos);
       } // end of list marshalling


       for(int idx = 0; idx < variableDatumRecords.size(); idx++)
       {
            VariableDatum aVariableDatum = (VariableDatum)variableDatumRecords.get(idx);
            aVariableDatum.marshal(dos);
       } // end of list marshalling

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
       requestID = dis.readInt();
       responseStatus = dis.readInt();
       numberOfFixedDatumRecords = dis.readInt();
       numberOfVariableDatumRecords = dis.readInt();
        for(int idx = 0; idx < numberOfFixedDatumRecords; idx++)
        {
           FixedDatum anX = new FixedDatum();
            anX.unmarshal(dis);
            fixedDatumRecords.add(anX);
        };

        for(int idx = 0; idx < numberOfVariableDatumRecords; idx++)
        {
           VariableDatum anX = new VariableDatum();
            anX.unmarshal(dis);
            variableDatumRecords.add(anX);
        };

    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(ActionResponseReliablePdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (requestID == rhs.requestID)) ivarsEqual = false;
     if( ! (responseStatus == rhs.responseStatus)) ivarsEqual = false;
     if( ! (numberOfFixedDatumRecords == rhs.numberOfFixedDatumRecords)) ivarsEqual = false;
     if( ! (numberOfVariableDatumRecords == rhs.numberOfVariableDatumRecords)) ivarsEqual = false;

     for(int idx = 0; idx < fixedDatumRecords.size(); idx++)
     {
        FixedDatum x = (FixedDatum)fixedDatumRecords.get(idx);
        if( ! ( fixedDatumRecords.get(idx).equals(rhs.fixedDatumRecords.get(idx)))) ivarsEqual = false;
     }


     for(int idx = 0; idx < variableDatumRecords.size(); idx++)
     {
        VariableDatum x = (VariableDatum)variableDatumRecords.get(idx);
        if( ! ( variableDatumRecords.get(idx).equals(rhs.variableDatumRecords.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
