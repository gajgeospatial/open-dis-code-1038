package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.2.18. Identifies a unique event in a simulation via the combination of three values
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class EventID extends Object implements Serializable
{
   /** The application ID */
   protected int  application;

   /** The site ID */
   protected int  site;

   /** the number of the event */
   protected int  eventNumber;


/** Constructor */
 public EventID()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public EventID(edu.nps.moves.jaxb.dis.EventID x)
 {
     this.application = x.getApplication();
     this.site = x.getSite();
     this.eventNumber = x.getEventNumber();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.EventID initializeJaxbObject(edu.nps.moves.jaxb.dis.EventID x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setApplication( this.getApplication() );
     x.setSite( this.getSite() );
     x.setEventNumber( this.getEventNumber() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 2;  // application
   marshalSize = marshalSize + 2;  // site
   marshalSize = marshalSize + 2;  // eventNumber

   return marshalSize;
}


public void setApplication(int pApplication)
{ application = pApplication;
}

public int getApplication()
{ return application; 
}

public void setSite(int pSite)
{ site = pSite;
}

public int getSite()
{ return site; 
}

public void setEventNumber(int pEventNumber)
{ eventNumber = pEventNumber;
}

public int getEventNumber()
{ return eventNumber; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeShort( (short)application);
       dos.writeShort( (short)site);
       dos.writeShort( (short)eventNumber);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       application = dis.readShort();
       site = dis.readShort();
       eventNumber = dis.readShort();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(EventID rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (application == rhs.application)) ivarsEqual = false;
     if( ! (site == rhs.site)) ivarsEqual = false;
     if( ! (eventNumber == rhs.eventNumber)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
