package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.2.14.1. A Simulation Address  record shall consist of the Site Identification number and the Application Identification number.
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class SimulationAddress extends Object implements Serializable
{
   /** The site ID */
   protected int  site;

   /** The application ID */
   protected int  application;


/** Constructor */
 public SimulationAddress()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public SimulationAddress(edu.nps.moves.jaxb.dis.SimulationAddress x)
 {
     this.site = x.getSite();
     this.application = x.getApplication();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.SimulationAddress initializeJaxbObject(edu.nps.moves.jaxb.dis.SimulationAddress x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setSite( this.getSite() );
     x.setApplication( this.getApplication() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 2;  // site
   marshalSize = marshalSize + 2;  // application

   return marshalSize;
}


public void setSite(int pSite)
{ site = pSite;
}

public int getSite()
{ return site; 
}

public void setApplication(int pApplication)
{ application = pApplication;
}

public int getApplication()
{ return application; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeShort( (short)site);
       dos.writeShort( (short)application);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       site = dis.readShort();
       application = dis.readShort();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(SimulationAddress rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (site == rhs.site)) ivarsEqual = false;
     if( ! (application == rhs.application)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
