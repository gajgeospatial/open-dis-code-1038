package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.11.2: Information about globat, spatially varying enviornmental effects. This requires manual cleanup; the grid axis        records are variable sized. UNFINISHED
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class GriddedDataPdu extends SyntheticEnvironmentFamilyPdu implements Serializable
{
   /** environmental simulation application ID */
   protected EntityID  environmentalSimulationApplicationID = new EntityID(); 

   /** unique identifier for each piece of enviornmental data */
   protected int  fieldNumber;

   /** sequence number for the total set of PDUS used to transmit the data */
   protected int  pduNumber;

   /** Total number of PDUS used to transmit the data */
   protected int  pduTotal;

   /** coordinate system of the grid */
   protected int  coordinateSystem;

   /** number of grid axes for the environmental data */
   protected short  numberOfGridAxes;

   /** are domain grid axes identidal to those of the priveious domain update? */
   protected short  constantGrid;

   /** type of environment */
   protected EntityType  environmentType = new EntityType(); 

   /** orientation of the data grid */
   protected Orientation  orientation = new Orientation(); 

   /** valid time of the enviormental data sample, 64 bit unsigned int */
   protected long  sampleTime;

   /** total number of all data values for all pdus for an environmental sample */
   protected long  totalValues;

   /** total number of data values at each grid point. */
   protected short  vectorDimension;

   /** padding */
   protected int  padding1;

   /** padding */
   protected short  padding2;

   /** Grid data @@@This is wrong */
   protected List gridDataList = new ArrayList(); 

/** Constructor */
 public GriddedDataPdu()
 {
    setPduType( (short)42 );
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public GriddedDataPdu(edu.nps.moves.jaxb.dis.GriddedDataPdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.EntityID foo_0;
     if(x.getEnvironmentalSimulationApplicationID() == null)
        foo_0 = new edu.nps.moves.dis.EntityID();
      else
        foo_0 = new edu.nps.moves.dis.EntityID(x.getEnvironmentalSimulationApplicationID() );
     this.setEnvironmentalSimulationApplicationID(foo_0);

     this.fieldNumber = x.getFieldNumber();
     this.pduNumber = x.getPduNumber();
     this.pduTotal = x.getPduTotal();
     this.coordinateSystem = x.getCoordinateSystem();
     this.numberOfGridAxes = x.getNumberOfGridAxes();
     this.constantGrid = x.getConstantGrid();

     edu.nps.moves.dis.EntityType foo_7;
     if(x.getEnvironmentType() == null)
        foo_7 = new edu.nps.moves.dis.EntityType();
      else
        foo_7 = new edu.nps.moves.dis.EntityType(x.getEnvironmentType() );
     this.setEnvironmentType(foo_7);


     edu.nps.moves.dis.Orientation foo_8;
     if(x.getOrientation() == null)
        foo_8 = new edu.nps.moves.dis.Orientation();
      else
        foo_8 = new edu.nps.moves.dis.Orientation(x.getOrientation() );
     this.setOrientation(foo_8);

     this.sampleTime = x.getSampleTime();
     this.totalValues = x.getTotalValues();
     this.vectorDimension = x.getVectorDimension();
     this.padding1 = x.getPadding1();
     this.padding2 = x.getPadding2();
     this.gridDataList = new ArrayList();
     for(int idx = 0; idx < x.getGridDataList().size(); idx++)
     {
        this.gridDataList.add( new edu.nps.moves.dis.GridAxisRecord((edu.nps.moves.jaxb.dis.GridAxisRecord) x.getGridDataList().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.GriddedDataPdu initializeJaxbObject(edu.nps.moves.jaxb.dis.GriddedDataPdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setEnvironmentalSimulationApplicationID( this.getEnvironmentalSimulationApplicationID().initializeJaxbObject(factory.createEntityID()) );
     x.setFieldNumber( this.getFieldNumber() );
     x.setPduNumber( this.getPduNumber() );
     x.setPduTotal( this.getPduTotal() );
     x.setCoordinateSystem( this.getCoordinateSystem() );
     x.setNumberOfGridAxes( this.getNumberOfGridAxes() );
     x.setConstantGrid( this.getConstantGrid() );
     x.setEnvironmentType( this.getEnvironmentType().initializeJaxbObject(factory.createEntityType()) );
     x.setOrientation( this.getOrientation().initializeJaxbObject(factory.createOrientation()) );
     x.setSampleTime( this.getSampleTime() );
     x.setTotalValues( this.getTotalValues() );
     x.setVectorDimension( this.getVectorDimension() );
     x.setPadding1( this.getPadding1() );
     x.setPadding2( this.getPadding2() );

     List gridDataList_1 = x.getGridDataList();
     for(int idx = 0; idx < gridDataList.size(); idx++)
     {
         GridAxisRecord a = (edu.nps.moves.dis.GridAxisRecord)gridDataList.get(idx);
         gridDataList_1.add(a.initializeJaxbObject(factory.createGridAxisRecord()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + environmentalSimulationApplicationID.getMarshalledSize();  // environmentalSimulationApplicationID
   marshalSize = marshalSize + 2;  // fieldNumber
   marshalSize = marshalSize + 2;  // pduNumber
   marshalSize = marshalSize + 2;  // pduTotal
   marshalSize = marshalSize + 2;  // coordinateSystem
   marshalSize = marshalSize + 1;  // numberOfGridAxes
   marshalSize = marshalSize + 1;  // constantGrid
   marshalSize = marshalSize + environmentType.getMarshalledSize();  // environmentType
   marshalSize = marshalSize + orientation.getMarshalledSize();  // orientation
   marshalSize = marshalSize + 8;  // sampleTime
   marshalSize = marshalSize + 4;  // totalValues
   marshalSize = marshalSize + 1;  // vectorDimension
   marshalSize = marshalSize + 2;  // padding1
   marshalSize = marshalSize + 1;  // padding2
   for(int idx=0; idx < gridDataList.size(); idx++)
   {
        GridAxisRecord listElement = (GridAxisRecord)gridDataList.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setEnvironmentalSimulationApplicationID(EntityID pEnvironmentalSimulationApplicationID)
{ environmentalSimulationApplicationID = pEnvironmentalSimulationApplicationID;
}

public EntityID getEnvironmentalSimulationApplicationID()
{ return environmentalSimulationApplicationID; }

public void setFieldNumber(int pFieldNumber)
{ fieldNumber = pFieldNumber;
}

public int getFieldNumber()
{ return fieldNumber; 
}

public void setPduNumber(int pPduNumber)
{ pduNumber = pPduNumber;
}

public int getPduNumber()
{ return pduNumber; 
}

public void setPduTotal(int pPduTotal)
{ pduTotal = pPduTotal;
}

public int getPduTotal()
{ return pduTotal; 
}

public void setCoordinateSystem(int pCoordinateSystem)
{ coordinateSystem = pCoordinateSystem;
}

public int getCoordinateSystem()
{ return coordinateSystem; 
}

public short getNumberOfGridAxes()
{ return (short)gridDataList.size();
}

/** Note that setting this value will ot change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfGridAxes method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public void setNumberOfGridAxes(short pNumberOfGridAxes)
{ numberOfGridAxes = pNumberOfGridAxes;
}

public void setConstantGrid(short pConstantGrid)
{ constantGrid = pConstantGrid;
}

public short getConstantGrid()
{ return constantGrid; 
}

public void setEnvironmentType(EntityType pEnvironmentType)
{ environmentType = pEnvironmentType;
}

public EntityType getEnvironmentType()
{ return environmentType; }

public void setOrientation(Orientation pOrientation)
{ orientation = pOrientation;
}

public Orientation getOrientation()
{ return orientation; }

public void setSampleTime(long pSampleTime)
{ sampleTime = pSampleTime;
}

public long getSampleTime()
{ return sampleTime; 
}

public void setTotalValues(long pTotalValues)
{ totalValues = pTotalValues;
}

public long getTotalValues()
{ return totalValues; 
}

public void setVectorDimension(short pVectorDimension)
{ vectorDimension = pVectorDimension;
}

public short getVectorDimension()
{ return vectorDimension; 
}

public void setPadding1(int pPadding1)
{ padding1 = pPadding1;
}

public int getPadding1()
{ return padding1; 
}

public void setPadding2(short pPadding2)
{ padding2 = pPadding2;
}

public short getPadding2()
{ return padding2; 
}

public void setGridDataList(List pGridDataList)
{ gridDataList = pGridDataList;
}

public List getGridDataList()
{ return gridDataList; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       environmentalSimulationApplicationID.marshal(dos);
       dos.writeShort( (short)fieldNumber);
       dos.writeShort( (short)pduNumber);
       dos.writeShort( (short)pduTotal);
       dos.writeShort( (short)coordinateSystem);
       dos.writeByte( (byte)gridDataList.size());
       dos.writeByte( (byte)constantGrid);
       environmentType.marshal(dos);
       orientation.marshal(dos);
       dos.writeLong( (long)sampleTime);
       dos.writeInt( (int)totalValues);
       dos.writeByte( (byte)vectorDimension);
       dos.writeShort( (short)padding1);
       dos.writeByte( (byte)padding2);

       for(int idx = 0; idx < gridDataList.size(); idx++)
       {
            GridAxisRecord aGridAxisRecord = (GridAxisRecord)gridDataList.get(idx);
            aGridAxisRecord.marshal(dos);
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
       environmentalSimulationApplicationID.unmarshal(dis);
       fieldNumber = dis.readShort();
       pduNumber = dis.readShort();
       pduTotal = dis.readShort();
       coordinateSystem = dis.readShort();
       numberOfGridAxes = dis.readByte();
       constantGrid = dis.readByte();
       environmentType.unmarshal(dis);
       orientation.unmarshal(dis);
       sampleTime = dis.readLong();
       totalValues = dis.readInt();
       vectorDimension = dis.readByte();
       padding1 = dis.readShort();
       padding2 = dis.readByte();
        for(int idx = 0; idx < numberOfGridAxes; idx++)
        {
           GridAxisRecord anX = new GridAxisRecord();
            anX.unmarshal(dis);
            gridDataList.add(anX);
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
 public boolean equals(GriddedDataPdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (environmentalSimulationApplicationID.equals( rhs.environmentalSimulationApplicationID) )) ivarsEqual = false;
     if( ! (fieldNumber == rhs.fieldNumber)) ivarsEqual = false;
     if( ! (pduNumber == rhs.pduNumber)) ivarsEqual = false;
     if( ! (pduTotal == rhs.pduTotal)) ivarsEqual = false;
     if( ! (coordinateSystem == rhs.coordinateSystem)) ivarsEqual = false;
     if( ! (numberOfGridAxes == rhs.numberOfGridAxes)) ivarsEqual = false;
     if( ! (constantGrid == rhs.constantGrid)) ivarsEqual = false;
     if( ! (environmentType.equals( rhs.environmentType) )) ivarsEqual = false;
     if( ! (orientation.equals( rhs.orientation) )) ivarsEqual = false;
     if( ! (sampleTime == rhs.sampleTime)) ivarsEqual = false;
     if( ! (totalValues == rhs.totalValues)) ivarsEqual = false;
     if( ! (vectorDimension == rhs.vectorDimension)) ivarsEqual = false;
     if( ! (padding1 == rhs.padding1)) ivarsEqual = false;
     if( ! (padding2 == rhs.padding2)) ivarsEqual = false;

     for(int idx = 0; idx < gridDataList.size(); idx++)
     {
        GridAxisRecord x = (GridAxisRecord)gridDataList.get(idx);
        if( ! ( gridDataList.get(idx).equals(rhs.gridDataList.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
