package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.2.38. Identifies the type of aggregate including kind of entity, domain (surface, subsurface, air, etc) country, category, etc.
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class AggregateType extends Object implements Serializable
{
   /** Kind of entity */
   protected short  aggregateKind;

   /** Domain of entity (air, surface, subsurface, space, etc) */
   protected short  domain;

   /** country to which the design of the entity is attributed */
   protected int  country;

   /** category of entity */
   protected short  category;

   /** subcategory of entity */
   protected short  subcategory;

   /** specific info based on subcategory field */
   protected short  specific;

   protected short  extra;


/** Constructor */
 public AggregateType()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public AggregateType(edu.nps.moves.jaxb.dis.AggregateType x)
 {
     this.aggregateKind = x.getAggregateKind();
     this.domain = x.getDomain();
     this.country = x.getCountry();
     this.category = x.getCategory();
     this.subcategory = x.getSubcategory();
     this.specific = x.getSpecific();
     this.extra = x.getExtra();
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.AggregateType initializeJaxbObject(edu.nps.moves.jaxb.dis.AggregateType x)
 {
     ObjectFactory factory = new ObjectFactory();

     x.setAggregateKind( this.getAggregateKind() );
     x.setDomain( this.getDomain() );
     x.setCountry( this.getCountry() );
     x.setCategory( this.getCategory() );
     x.setSubcategory( this.getSubcategory() );
     x.setSpecific( this.getSpecific() );
     x.setExtra( this.getExtra() );
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = marshalSize + 1;  // aggregateKind
   marshalSize = marshalSize + 1;  // domain
   marshalSize = marshalSize + 2;  // country
   marshalSize = marshalSize + 1;  // category
   marshalSize = marshalSize + 1;  // subcategory
   marshalSize = marshalSize + 1;  // specific
   marshalSize = marshalSize + 1;  // extra

   return marshalSize;
}


public void setAggregateKind(short pAggregateKind)
{ aggregateKind = pAggregateKind;
}

public short getAggregateKind()
{ return aggregateKind; 
}

public void setDomain(short pDomain)
{ domain = pDomain;
}

public short getDomain()
{ return domain; 
}

public void setCountry(int pCountry)
{ country = pCountry;
}

public int getCountry()
{ return country; 
}

public void setCategory(short pCategory)
{ category = pCategory;
}

public short getCategory()
{ return category; 
}

public void setSubcategory(short pSubcategory)
{ subcategory = pSubcategory;
}

public short getSubcategory()
{ return subcategory; 
}

public void setSpecific(short pSpecific)
{ specific = pSpecific;
}

public short getSpecific()
{ return specific; 
}

public void setExtra(short pExtra)
{ extra = pExtra;
}

public short getExtra()
{ return extra; 
}


public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeByte( (byte)aggregateKind);
       dos.writeByte( (byte)domain);
       dos.writeShort( (short)country);
       dos.writeByte( (byte)category);
       dos.writeByte( (byte)subcategory);
       dos.writeByte( (byte)specific);
       dos.writeByte( (byte)extra);
    } // end try 
    catch(Exception e)
    { 
      System.out.println(e);}
    } // end of marshal method

public void unmarshal(DataInputStream dis)
{
    try 
    {
       aggregateKind = dis.readByte();
       domain = dis.readByte();
       country = dis.readShort();
       category = dis.readByte();
       subcategory = dis.readByte();
       specific = dis.readByte();
       extra = dis.readByte();
    } // end try 
   catch(Exception e)
    { 
      System.out.println(e); 
    }
 } // end of unmarshal method 


 /**
  * The equals method doesn't always work--mostly on on classes that consist only of primitives. Be careful.
  */
 public boolean equals(AggregateType rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (aggregateKind == rhs.aggregateKind)) ivarsEqual = false;
     if( ! (domain == rhs.domain)) ivarsEqual = false;
     if( ! (country == rhs.country)) ivarsEqual = false;
     if( ! (category == rhs.category)) ivarsEqual = false;
     if( ! (subcategory == rhs.subcategory)) ivarsEqual = false;
     if( ! (specific == rhs.specific)) ivarsEqual = false;
     if( ! (extra == rhs.extra)) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
