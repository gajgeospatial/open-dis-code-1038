package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.2.5. Articulation parameters for  movable parts and attached parts of an entity. Specifes wether or not a change has occured,  the part identifcation of the articulated part to which it is attached, and the type and value of each parameter.
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class ArticulationParameter extends Object implements Serializable
{
   protected short  parameterTypeDesignator;

   protected short  changeIndicator;

   protected int  partAttachedTo;

   protected int  parameterType;

   protected double  parameterValue;


/** Constructor */
 public ArticulationParameter()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public ArticulationParameter(edu.nps.moves.jaxb.dis.ArticulationParameter x)
 {
     this.parameterTypeDesignator = x.getParameterTypeDesignator();
     this.changeIndicator = x.getChangeIndicator();
     this.partAttachedTo = x.getPartAttachedTo();
     this.parameterType = x.getParameterType();
     this.parameterValue = x.getParameterValue();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.ArticulationParameter initializeJaxbObject(edu.nps.moves.jaxb.dis.ArticulationParameter x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setParameterTypeDesignator( this.getParameterTypeDesignator() );
     x.setChangeIndicator( this.getChangeIndicator() );
     x.setPartAttachedTo( this.getPartAttachedTo() );
     x.setParameterType( this.getParameterType() );
     x.setParameterValue( this.getParameterValue() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 1;  // parameterTypeDesignator
   marshalSize = marshalSize + 1;  // changeIndicator
   marshalSize = marshalSize + 2;  // partAttachedTo
   marshalSize = marshalSize + 4;  // parameterType
   marshalSize = marshalSize + 8;  // parameterValue

   return marshalSize;
}


public void setParameterTypeDesignator(short pParameterTypeDesignator)
{ parameterTypeDesignator = pParameterTypeDesignator;
}

public short getParameterTypeDesignator()
{ return parameterTypeDesignator; 
}

public void setChangeIndicator(short pChangeIndicator)
{ changeIndicator = pChangeIndicator;
}

public short getChangeIndicator()
{ return changeIndicator; 
}

public void setPartAttachedTo(int pPartAttachedTo)
{ partAttachedTo = pPartAttachedTo;
}

public int getPartAttachedTo()
{ return partAttachedTo; 
}

public void setParameterType(int pParameterType)
{ parameterType = pParameterType;
}

public int getParameterType()
{ return parameterType; 
}

public void setParameterValue(double pParameterValue)
{ parameterValue = pParameterValue;
}

public double getParameterValue()
{ return parameterValue; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeByte( (byte)parameterTypeDesignator);
       dos.writeByte( (byte)changeIndicator);
       dos.writeShort( (short)partAttachedTo);
       dos.writeInt( (int)parameterType);
       dos.writeDouble( (double)parameterValue);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       parameterTypeDesignator = dis.readByte();
       changeIndicator = dis.readByte();
       partAttachedTo = dis.readShort();
       parameterType = dis.readInt();
       parameterValue = dis.readDouble();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(ArticulationParameter rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (parameterTypeDesignator == rhs.parameterTypeDesignator)) ivarsEqual = false;
     if( ! (changeIndicator == rhs.changeIndicator)) ivarsEqual = false;
     if( ! (partAttachedTo == rhs.partAttachedTo)) ivarsEqual = false;
     if( ! (parameterType == rhs.parameterType)) ivarsEqual = false;
     if( ! (parameterValue == rhs.parameterValue)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
