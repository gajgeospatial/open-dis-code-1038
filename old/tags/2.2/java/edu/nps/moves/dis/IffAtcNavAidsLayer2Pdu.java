package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.jaxb.dis.*;

/**
 * Section 5.3.7.4.2 When present, layer 2 should follow layer 1 and have the following fields. This requires manual cleanup.        the beamData attribute semantics are used in multiple ways. UNFINSISHED
 *
 * Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class IffAtcNavAidsLayer2Pdu extends IffAtcNavAidsLayer1Pdu implements Serializable
{
   /** layer header */
   protected LayerHeader  layerHeader = new LayerHeader(); 

   /** beam data */
   protected BeamData  beamData = new BeamData(); 

   /** Secondary operational data, 5.2.57 */
   protected BeamData  secondaryOperationalData = new BeamData(); 

   /** variable length list of fundamental parameters. @@@This is wrong */
   protected List fundamentalIffParameters = new ArrayList(); 

/** Constructor */
 public IffAtcNavAidsLayer2Pdu()
 {
 }

/** 
 * Constructor--takes a parallel jaxb object and returns an open-dis object 
 * 1.4_sed_bait_start */
 public IffAtcNavAidsLayer2Pdu(edu.nps.moves.jaxb.dis.IffAtcNavAidsLayer2Pdu x)
 {
     super(x); // Call superclass constructor


     edu.nps.moves.dis.LayerHeader foo_0;
     if(x.getLayerHeader() == null)
        foo_0 = new edu.nps.moves.dis.LayerHeader();
      else
        foo_0 = new edu.nps.moves.dis.LayerHeader(x.getLayerHeader() );
     this.setLayerHeader(foo_0);


     edu.nps.moves.dis.BeamData foo_1;
     if(x.getBeamData() == null)
        foo_1 = new edu.nps.moves.dis.BeamData();
      else
        foo_1 = new edu.nps.moves.dis.BeamData(x.getBeamData() );
     this.setBeamData(foo_1);


     edu.nps.moves.dis.BeamData foo_2;
     if(x.getSecondaryOperationalData() == null)
        foo_2 = new edu.nps.moves.dis.BeamData();
      else
        foo_2 = new edu.nps.moves.dis.BeamData(x.getSecondaryOperationalData() );
     this.setSecondaryOperationalData(foo_2);

     this.fundamentalIffParameters = new ArrayList();
     for(int idx = 0; idx < x.getFundamentalIffParameters().size(); idx++)
     {
        this.fundamentalIffParameters.add( new edu.nps.moves.dis.FundamentalParameterDataIff((edu.nps.moves.jaxb.dis.FundamentalParameterDataIff) x.getFundamentalIffParameters().get(idx)));
     }
 }
/* 1.4_sed_bait_end */


/**
 * returns a jaxb object intialized from this object, given an empty jaxb object
 * 1.4_sed_bait_start **/
 public edu.nps.moves.jaxb.dis.IffAtcNavAidsLayer2Pdu initializeJaxbObject(edu.nps.moves.jaxb.dis.IffAtcNavAidsLayer2Pdu x)
 {
     super.initializeJaxbObject(x); // Call superclass initializer

     ObjectFactory factory = new ObjectFactory();

     x.setLayerHeader( this.getLayerHeader().initializeJaxbObject(factory.createLayerHeader()) );
     x.setBeamData( this.getBeamData().initializeJaxbObject(factory.createBeamData()) );
     x.setSecondaryOperationalData( this.getSecondaryOperationalData().initializeJaxbObject(factory.createBeamData()) );

     List fundamentalIffParameters_1 = x.getFundamentalIffParameters();
     for(int idx = 0; idx < fundamentalIffParameters.size(); idx++)
     {
         FundamentalParameterDataIff a = (edu.nps.moves.dis.FundamentalParameterDataIff)fundamentalIffParameters.get(idx);
         fundamentalIffParameters_1.add(a.initializeJaxbObject(factory.createFundamentalParameterDataIff()));
     }
   return x;
 }
/* 1.4_sed_bait_end */


public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize = marshalSize + layerHeader.getMarshalledSize();  // layerHeader
   marshalSize = marshalSize + beamData.getMarshalledSize();  // beamData
   marshalSize = marshalSize + secondaryOperationalData.getMarshalledSize();  // secondaryOperationalData
   for(int idx=0; idx < fundamentalIffParameters.size(); idx++)
   {
        FundamentalParameterDataIff listElement = (FundamentalParameterDataIff)fundamentalIffParameters.get(idx);
        marshalSize = marshalSize + listElement.getMarshalledSize();
   }

   return marshalSize;
}


public void setLayerHeader(LayerHeader pLayerHeader)
{ layerHeader = pLayerHeader;
}

public LayerHeader getLayerHeader()
{ return layerHeader; }

public void setBeamData(BeamData pBeamData)
{ beamData = pBeamData;
}

public BeamData getBeamData()
{ return beamData; }

public void setSecondaryOperationalData(BeamData pSecondaryOperationalData)
{ secondaryOperationalData = pSecondaryOperationalData;
}

public BeamData getSecondaryOperationalData()
{ return secondaryOperationalData; }

public void setFundamentalIffParameters(List pFundamentalIffParameters)
{ fundamentalIffParameters = pFundamentalIffParameters;
}

public List getFundamentalIffParameters()
{ return fundamentalIffParameters; }


public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       layerHeader.marshal(dos);
       beamData.marshal(dos);
       secondaryOperationalData.marshal(dos);

       for(int idx = 0; idx < fundamentalIffParameters.size(); idx++)
       {
            FundamentalParameterDataIff aFundamentalParameterDataIff = (FundamentalParameterDataIff)fundamentalIffParameters.get(idx);
            aFundamentalParameterDataIff.marshal(dos);
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
       layerHeader.unmarshal(dis);
       beamData.unmarshal(dis);
       secondaryOperationalData.unmarshal(dis);
        for(int idx = 0; idx < pad2; idx++)
        {
           FundamentalParameterDataIff anX = new FundamentalParameterDataIff();
            anX.unmarshal(dis);
            fundamentalIffParameters.add(anX);
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
 public boolean equals(IffAtcNavAidsLayer2Pdu rhs)
 {
     boolean ivarsEqual = true;

    if(rhs.getClass() != this.getClass())
        return false;

     if( ! (layerHeader.equals( rhs.layerHeader) )) ivarsEqual = false;
     if( ! (beamData.equals( rhs.beamData) )) ivarsEqual = false;
     if( ! (secondaryOperationalData.equals( rhs.secondaryOperationalData) )) ivarsEqual = false;

     for(int idx = 0; idx < fundamentalIffParameters.size(); idx++)
     {
        FundamentalParameterDataIff x = (FundamentalParameterDataIff)fundamentalIffParameters.get(idx);
        if( ! ( fundamentalIffParameters.get(idx).equals(rhs.fundamentalIffParameters.get(idx)))) ivarsEqual = false;
     }


    return ivarsEqual;
 }
} // end of class
