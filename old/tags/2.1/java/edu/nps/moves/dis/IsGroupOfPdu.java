package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.9.2 Information about a particular group of entities grouped together for the purposes of netowrk bandwidth         reduction or aggregation. Needs manual cleanup. The GED size requires a database lookup. UNFINISHED
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class IsGroupOfPdu extends EntityManagementFamilyPdu implements Serializable
{
   /** ID of aggregated entities */
   protected EntityID  groupEntityID = new EntityID(); 

   /** type of entities constituting the group */
   protected short  groupedEntityCategory;

   /** Number of individual entities constituting the group */
   protected short  numberOfGroupedEntities;

   /** padding */
   protected long  pad2;

   /** latitude */
   protected double  latitude;

   /** longitude */
   protected double  longitude;

   /** GED records about each individual entity in the group. @@@this is wrong--need a database lookup to find the actual size of the list elements */
   protected List groupedEntityDescriptions = new ArrayList(); 

/** Constructor */
 public IsGroupOfPdu()
 {
    setPduType( (short)34 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public IsGroupOfPdu(edu.nps.moves.jaxb.dis.IsGroupOfPdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.EntityID foo_0;
     if(x.getGroupEntityID() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getGroupEntityID() );
     this.setGroupEntityID(foo_0);

     this.groupedEntityCategory = x.getGroupedEntityCategory();
     this.numberOfGroupedEntities = x.getNumberOfGroupedEntities();
     this.pad2 = x.getPad2();
     this.latitude = x.getLatitude();
     this.longitude = x.getLongitude();
     this.groupedEntityDescriptions = new ArrayList();
     for(int idx = 0; idx < x.getGroupedEntityDescriptions().size(); idx++)
     {
        this.groupedEntityDescriptions.add( new edu.nps.moves.dis.VariableDatum((edu.nps.moves.jaxb.dis.VariableDatum) x.getGroupedEntityDescriptions().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.IsGroupOfPdu initializeJaxbObject(edu.nps.moves.jaxb.dis.IsGroupOfPdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setGroupEntityID( this.getGroupEntityID().initializeJaxbObject(factory.createEntityID()) );
     x.setGroupedEntityCategory( this.getGroupedEntityCategory() );
     x.setNumberOfGroupedEntities( this.getNumberOfGroupedEntities() );
     x.setPad2( this.getPad2() );
     x.setLatitude( this.getLatitude() );
     x.setLongitude( this.getLongitude() );

     List groupedEntityDescriptions_1 = x.getGroupedEntityDescriptions();
     for(int idx = 0; idx < groupedEntityDescriptions.size(); idx++)
     {
         VariableDatum a = (edu.nps.moves.dis.VariableDatum)groupedEntityDescriptions.get(idx);
         groupedEntityDescriptions_1.add(a.initializeJaxbObject(factory.createVariableDatum()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + groupEntityID.getMarshalledSize();  // groupEntityID
   marshalSize = marshalSize + 1;  // groupedEntityCategory
   marshalSize = marshalSize + 1;  // numberOfGroupedEntities
   marshalSize = marshalSize + 4;  // pad2
   marshalSize = marshalSize + 8;  // latitude
   marshalSize = marshalSize + 8;  // longitude
   for(int idx=0; idx < groupedEntityDescriptions.size(); idx++)
   {
        VariableDatum listElement = (VariableDatum)groupedEntityDescriptions.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setGroupEntityID(EntityID pGroupEntityID)
{ groupEntityID = pGroupEntityID;
}

public EntityID getGroupEntityID()
{ return groupEntityID; }

public void setGroupedEntityCategory(short pGroupedEntityCategory)
{ groupedEntityCategory = pGroupedEntityCategory;
}

public short getGroupedEntityCategory()
{ return groupedEntityCategory; 
}

public short getNumberOfGroupedEntities()
{ return (short)groupedEntityDescriptions.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfGroupedEntities method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfGroupedEntities(short pNumberOfGroupedEntities)
{ numberOfGroupedEntities = pNumberOfGroupedEntities;
}

public void setPad2(long pPad2)
{ pad2 = pPad2;
}

public long getPad2()
{ return pad2; 
}

public void setLatitude(double pLatitude)
{ latitude = pLatitude;
}

public double getLatitude()
{ return latitude; 
}

public void setLongitude(double pLongitude)
{ longitude = pLongitude;
}

public double getLongitude()
{ return longitude; 
}

public void setGroupedEntityDescriptions(List pGroupedEntityDescriptions)
{ groupedEntityDescriptions = pGroupedEntityDescriptions;
}

public List getGroupedEntityDescriptions()
{ return groupedEntityDescriptions; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       groupEntityID.marshal(dos);
       dos.writeByte( (byte)groupedEntityCategory);
       dos.writeByte( (byte)groupedEntityDescriptions.size());
       dos.writeInt( (int)pad2);
       dos.writeDouble( (double)latitude);
       dos.writeDouble( (double)longitude);

       for(int idx = 0; idx < groupedEntityDescriptions.size(); idx++)
       {
            VariableDatum aVariableDatum = (VariableDatum)groupedEntityDescriptions.get(idx);
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
       groupEntityID.unmarshal(dis);
       groupedEntityCategory = dis.readByte();
       numberOfGroupedEntities = dis.readByte();
       pad2 = dis.readInt();
       latitude = dis.readDouble();
       longitude = dis.readDouble();
        for(int idx = 0; idx < numberOfGroupedEntities; idx++)
        {
           VariableDatum anX = new VariableDatum();
            anX.unmarshal(dis);
            groupedEntityDescriptions.add(anX);
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
 public boolean equals(IsGroupOfPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (groupEntityID.equals( rhs.groupEntityID) )) ivarsEqual = false;
     if( ! (groupedEntityCategory == rhs.groupedEntityCategory)) ivarsEqual = false;
     if( ! (numberOfGroupedEntities == rhs.numberOfGroupedEntities)) ivarsEqual = false;
     if( ! (pad2 == rhs.pad2)) ivarsEqual = false;
     if( ! (latitude == rhs.latitude)) ivarsEqual = false;
     if( ! (longitude == rhs.longitude)) ivarsEqual = false;

     for(int idx = 0; idx < groupedEntityDescriptions.size(); idx++)
     {
        VariableDatum x = (VariableDatum)groupedEntityDescriptions.get(idx);
        if( ! ( groupedEntityDescriptions.get(idx).equals(rhs.groupedEntityDescriptions.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
