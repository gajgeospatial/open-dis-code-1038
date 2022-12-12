/**
 * Section 5.3.6.5. Acknowledge the receipt of a start/resume, stop/freeze, or RemoveEntityPDU. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.AcknowledgePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 15;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 5;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Entity that is sending message */
   this.originatingEntityID = new dis.EntityID(); 

   /** Entity that is intended to receive message */
   this.receivingEntityID = new dis.EntityID(); 

   /** type of message being acknowledged */
  this.acknowledgeFlag = 0;

   /** Whether or not the receiving entity was able to comply with the request */
  this.responseFlag = 0;

   /** Request ID that is unique */
  this.requestID = 0;

} // end of class
/**
 * Section 5.3.12.5: Ack receipt of a start-resume, stop-freeze, create-entity or remove enitty (reliable) pdus. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.AcknowledgeReliablePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 55;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 10;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Object originatig the request */
   this.originatingEntityID = new dis.EntityID(); 

   /** Object with which this point object is associated */
   this.receivingEntityID = new dis.EntityID(); 

   /** ack flags */
  this.acknowledgeFlag = 0;

   /** response flags */
  this.responseFlag = 0;

   /** Request ID */
  this.requestID = 0;

} // end of class
/**
 * Used in UA PDU
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.AcousticBeamData = function()
{
   /** beam data length */
  this.beamDataLength = 0;

   /** beamIDNumber */
  this.beamIDNumber = 0;

   /** padding */
  this.pad2 = 0;

   /** fundamental data parameters */
   this.fundamentalDataParameters = new dis.AcousticBeamFundamentalParameter(); 

} // end of class
/**
 * Used in UaPdu
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.AcousticBeamFundamentalParameter = function()
{
   /** parameter index */
  this.activeEmissionParameterIndex = 0;

   /** scan pattern */
  this.scanPattern = 0;

   /** beam center azimuth */
  this.beamCenterAzimuth = 0;

   /** azimuthal beamwidth */
  this.azimuthalBeamwidth = 0;

   /** beam center */
  this.beamCenterDE = 0;

   /** DE beamwidth (vertical beamwidth) */
  this.deBeamwidth = 0;

} // end of class
/**
 * Section 5.2.35. information about a specific UA emmtter
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.AcousticEmitter = function()
{
   /** the system for a particular UA emitter, and an enumeration */
  this.acousticName = 0;

   /** The function of the acoustic system */
  this.function = 0;

   /** The UA emitter identification number relative to a specific system */
  this.acousticIdNumber = 0;

} // end of class
/**
 * 5.3.35: Information about a particular UA emitter shall be represented using an Acoustic Emitter System record. This record shall consist of three fields: Acoustic Name, Function, and Acoustic ID Number
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.AcousticEmitterSystem = function()
{
   /** This field shall specify the system for a particular UA emitter. */
  this.acousticName = 0;

   /** This field shall describe the function of the acoustic system.  */
  this.acousticFunction = 0;

   /** This field shall specify the UA emitter identification number relative to a specific system. This field shall be represented by an 8-bit unsigned integer. This field allows the differentiation of multiple systems on an entity, even if in some instances two or more of the systems may be identical UA emitter types. Numbering of systems shall begin with the value 1.  */
  this.acousticID = 0;

} // end of class
/**
 * Used in the UA pdu; ties together an emmitter and a location. This requires manual cleanup; the beam data should not be attached to each emitter system.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.AcousticEmitterSystemData = function()
{
   /** Length of emitter system data */
  this.emitterSystemDataLength = 0;

   /** Number of beams */
  this.numberOfBeams = 0;

   /** padding */
  this.pad2 = 0;

   /** This field shall specify the system for a particular UA emitter. */
   this.acousticEmitterSystem = new dis.AcousticEmitterSystem(); 

   /** Represents the location wrt the entity */
   this.emitterLocation = new dis.Vector3Float(); 

   /** For each beam in numberOfBeams, an emitter system. This is not right--the beam records need to be at the end of the PDU, rather than attached to each system. */
    beamRecords = new Array();
 
} // end of class
/**
 * Section 5.3.6.6. Request from simulation manager to an entity. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.ActionRequestPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 16;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 5;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Entity that is sending message */
   this.originatingEntityID = new dis.EntityID(); 

   /** Entity that is intended to receive message */
   this.receivingEntityID = new dis.EntityID(); 

   /** Request ID that is unique */
  this.requestID = 0;

   /** identifies the action being requested */
  this.actionID = 0;

   /** Number of fixed datum records */
  this.numberOfFixedDatumRecords = 0;

   /** Number of variable datum records */
  this.numberOfVariableDatumRecords = 0;

   /** variable length list of fixed datums */
    fixedDatums = new Array();
 
   /** variable length list of variable length datums */
    variableDatums = new Array();
 
} // end of class
/**
 * Section 5.3.12.6: request from a simulation manager to a managed entity to perform a specified action. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.ActionRequestReliablePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 56;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 10;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Object originatig the request */
   this.originatingEntityID = new dis.EntityID(); 

   /** Object with which this point object is associated */
   this.receivingEntityID = new dis.EntityID(); 

   /** level of reliability service used for this transaction */
  this.requiredReliabilityService = 0;

   /** padding */
  this.pad1 = 0;

   /** padding */
  this.pad2 = 0;

   /** request ID */
  this.requestID = 0;

   /** request ID */
  this.actionID = 0;

   /** Fixed datum record count */
  this.numberOfFixedDatumRecords = 0;

   /** variable datum record count */
  this.numberOfVariableDatumRecords = 0;

   /** Fixed datum records */
    fixedDatumRecords = new Array();
 
   /** Variable datum records */
    variableDatumRecords = new Array();
 
} // end of class
/**
 * Section 5.3.6.7. response to an action request PDU. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.ActionResponsePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 17;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 5;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Entity that is sending message */
   this.originatingEntityID = new dis.EntityID(); 

   /** Entity that is intended to receive message */
   this.receivingEntityID = new dis.EntityID(); 

   /** Request ID that is unique */
  this.requestID = 0;

   /** Status of response */
  this.requestStatus = 0;

   /** Number of fixed datum records */
  this.numberOfFixedDatumRecords = 0;

   /** Number of variable datum records */
  this.numberOfVariableDatumRecords = 0;

   /** variable length list of fixed datums */
    fixedDatums = new Array();
 
   /** variable length list of variable length datums */
    variableDatums = new Array();
 
} // end of class
/**
 * Section 5.3.12.7: Response from an entity to an action request PDU. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.ActionResponseReliablePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 57;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 10;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Object originatig the request */
   this.originatingEntityID = new dis.EntityID(); 

   /** Object with which this point object is associated */
   this.receivingEntityID = new dis.EntityID(); 

   /** request ID */
  this.requestID = 0;

   /** status of response */
  this.responseStatus = 0;

   /** Fixed datum record count */
  this.numberOfFixedDatumRecords = 0;

   /** variable datum record count */
  this.numberOfVariableDatumRecords = 0;

   /** Fixed datum records */
    fixedDatumRecords = new Array();
 
   /** Variable datum records */
    variableDatumRecords = new Array();
 
} // end of class
/**
 * Section 5.2.36. Each agregate in a given simulation app is given an aggregate identifier number unique for all other aggregates in that app and in that exercise. The id is valid for the duration of the the exercise.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.AggregateID = function()
{
   /** The site ID */
  this.site = 0;

   /** The application ID */
  this.application = 0;

   /** the aggregate ID */
  this.aggregateID = 0;

} // end of class
/**
 * Section 5.2.37. Specifies the character set used inthe first byte, followed by up to 31 characters of text data.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.AggregateMarking = function()
{
   /** The character set */
  this.characterSet = 0;

   /** The characters */
} // end of class
/**
 * Section 5.3.9.1 informationa bout aggregating entities anc communicating information about the aggregated entities.        requires manual intervention to fix the padding between entityID lists and silent aggregate sysem lists--this padding        is dependent on how many entityIDs there are, and needs to be on a 32 bit word boundary. UNFINISHED
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.AggregateStatePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 33;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 7;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** ID of aggregated entities */
   this.aggregateID = new dis.EntityID(); 

   /** force ID */
  this.forceID = 0;

   /** state of aggregate */
  this.aggregateState = 0;

   /** entity type of the aggregated entities */
   this.aggregateType = new dis.EntityType(); 

   /** formation of aggregated entities */
  this.formation = 0;

   /** marking for aggregate; first char is charset type, rest is char data */
   this.aggregateMarking = new dis.AggregateMarking(); 

   /** dimensions of bounding box for the aggregated entities, origin at the center of mass */
   this.dimensions = new dis.Vector3Float(); 

   /** orientation of the bounding box */
   this.orientation = new dis.Orientation(); 

   /** center of mass of the aggregation */
   this.centerOfMass = new dis.Vector3Double(); 

   /** velocity of aggregation */
   this.velocity = new dis.Vector3Float(); 

   /** number of aggregates */
  this.numberOfDisAggregates = 0;

   /** number of entities */
  this.numberOfDisEntities = 0;

   /** number of silent aggregate types */
  this.numberOfSilentAggregateTypes = 0;

   /** number of silent entity types */
  this.numberOfSilentEntityTypes = 0;

   /** aggregates  list */
    aggregateIDList = new Array();
 
   /** entity ID list */
    entityIDList = new Array();
 
   /** ^^^padding to put the start of the next list on a 32 bit boundary. This needs to be fixed */
  this.pad2 = 0;

   /** silent entity types */
    silentAggregateSystemList = new Array();
 
   /** silent entity types */
    silentEntitySystemList = new Array();
 
   /** number of variable datum records */
  this.numberOfVariableDatumRecords = 0;

   /** variableDatums */
    variableDatumList = new Array();
 
} // end of class
/**
 * Section 5.2.38. Identifies the type of aggregate including kind of entity, domain (surface, subsurface, air, etc) country, category, etc.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.AggregateType = function()
{
   /** Kind of entity */
  this.aggregateKind = 0;

   /** Domain of entity (air, surface, subsurface, space, etc) */
  this.domain = 0;

   /** country to which the design of the entity is attributed */
  this.country = 0;

   /** category of entity */
  this.category = 0;

   /** subcategory of entity */
  this.subcategory = 0;

   /** specific info based on subcategory field, sql has a reserved word for specific */
  this.specificInfo = 0;

  this.extra = 0;

} // end of class
/**
 * 5.2.2: angular velocity measured in radians per second out each of the entity's own coordinate axes.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.AngularVelocityVector = function()
{
   /** velocity about the x axis */
  this.x = 0;

   /** velocity about the y axis */
  this.y = 0;

   /** velocity about the zaxis */
  this.z = 0;

} // end of class
/**
 * 5.2.3: location of the radiating portion of the antenna, specified in world coordinates and         entity coordinates.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.AntennaLocation = function()
{
   /** Location of the radiating portion of the antenna in world    coordinates */
   this.antennaLocation = new dis.Vector3Double(); 

   /** Location of the radiating portion of the antenna     in entity coordinates */
   this.relativeAntennaLocation = new dis.Vector3Float(); 

} // end of class
/**
 * Used in UA PDU
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.ApaData = function()
{
   /** Index of APA parameter */
  this.parameterIndex = 0;

   /** Index of APA parameter */
  this.parameterValue = 0;

} // end of class
/**
 * Section 5.3.11.5: Information about the addition/modification of an oobject that is geometrically      achored to the terrain with a set of three or more points that come to a closure. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.ArealObjectStatePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 45;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 9;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Object in synthetic environment */
   this.objectID = new dis.EntityID(); 

   /** Object with which this point object is associated */
   this.referencedObjectID = new dis.EntityID(); 

   /** unique update number of each state transition of an object */
  this.updateNumber = 0;

   /** force ID */
  this.forceID = 0;

   /** modifications enumeration */
  this.modifications = 0;

   /** Object type */
   this.objectType = new dis.EntityType(); 

   /** Object appearance */
   this.objectAppearance = new dis.SixByteChunk(); 

   /** Number of points */
  this.numberOfPoints = 0;

   /** requesterID */
   this.requesterID = new dis.SimulationAddress(); 

   /** receiver ID */
   this.receivingID = new dis.SimulationAddress(); 

   /** location of object */
    objectLocation = new Array();
 
} // end of class
/**
 * Section 5.2.5. Articulation parameters for  movable parts and attached parts of an entity. Specifes wether or not a change has occured,  the part identifcation of the articulated part to which it is attached, and the type and value of each parameter.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.ArticulationParameter = function()
{
  this.parameterTypeDesignator = 0;

  this.changeIndicator = 0;

  this.partAttachedTo = 0;

  this.parameterType = 0;

  this.parameterValue = 0;

} // end of class
/**
 * Section 5.2.4.2. Used when the antenna pattern type field has a value of 1. Specifies           the direction, patter, and polarization of radiation from an antenna.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.BeamAntennaPattern = function()
{
   /** The rotation that transformst he reference coordinate sytem     into the beam coordinate system. Either world coordinates or entity coordinates may be used as the     reference coordinate system, as specified by teh reference system field of the antenna pattern record. */
   this.beamDirection = new dis.Orientation(); 

  this.azimuthBeamwidth = 0;

  this.elevationBeamwidth = 0;

  this.referenceSystem = 0;

  this.padding1 = 0;

  this.padding2 = 0;

   /** Magnigute of the z-component in beam coordinates at some arbitrary      single point in the mainbeam      and in the far field of the antenna. */
  this.ez = 0;

   /** Magnigute of the x-component in beam coordinates at some arbitrary      single point in the mainbeam      and in the far field of the antenna. */
  this.ex = 0;

   /** THe phase angle between Ez and Ex in radians. */
  this.phase = 0;

} // end of class
/**
 * Section 5.2.39. Specification of the data necessary to  describe the scan volume of an emitter.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.BeamData = function()
{
   /** Specifies the beam azimuth an elevation centers and corresponding half-angles     to describe the scan volume */
  this.beamAzimuthCenter = 0;

   /** Specifies the beam azimuth sweep to determine scan volume */
  this.beamAzimuthSweep = 0;

   /** Specifies the beam elevation center to determine scan volume */
  this.beamElevationCenter = 0;

   /** Specifies the beam elevation sweep to determine scan volume */
  this.beamElevationSweep = 0;

   /** allows receiver to synchronize its regenerated scan pattern to     that of the emmitter. Specifies the percentage of time a scan is through its pattern from its origion. */
  this.beamSweepSync = 0;

} // end of class
/**
 * Section 5.2.7. Specifies the type of muntion fired, the type of warhead, the         type of fuse, the number of rounds fired, and the rate at which the roudns are fired in         rounds per minute.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.BurstDescriptor = function()
{
   /** What munition was used in the burst */
   this.munition = new dis.EntityType(); 

   /** type of warhead */
  this.warhead = 0;

   /** type of fuse used */
  this.fuse = 0;

   /** how many of the munition were fired */
  this.quantity = 0;

   /** rate at which the munition was fired */
  this.rate = 0;

} // end of class
/**
 * Section 5.2.8. Time measurements that exceed one hour. Hours is the number of           hours since January 1, 1970, UTC
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.ClockTime = function()
{
   /** Hours in UTC */
  this.hour = 0;

   /** Time past the hour */
  this.timePastHour = 0;

} // end of class
/**
 * 5.3.3.3. Information about elastic collisions in a DIS exercise shall be communicated using a Collision-Elastic PDU. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.CollisionElasticPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 66;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 1;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** ID of the entity that issued the collision PDU */
   this.issuingEntityID = new dis.EntityID(); 

   /** ID of entity that has collided with the issuing entity ID */
   this.collidingEntityID = new dis.EntityID(); 

   /** ID of event */
   this.collisionEventID = new dis.EventID(); 

   /** some padding */
  this.pad = 0;

   /** velocity at collision */
   this.contactVelocity = new dis.Vector3Float(); 

   /** mass of issuing entity */
  this.mass = 0;

   /** Location with respect to entity the issuing entity collided with */
   this.location = new dis.Vector3Float(); 

   /** tensor values */
  this.collisionResultXX = 0;

   /** tensor values */
  this.collisionResultXY = 0;

   /** tensor values */
  this.collisionResultXZ = 0;

   /** tensor values */
  this.collisionResultYY = 0;

   /** tensor values */
  this.collisionResultYZ = 0;

   /** tensor values */
  this.collisionResultZZ = 0;

   /** This record shall represent the normal vector to the surface at the point of collision detection. The surface normal shall be represented in world coordinates. */
   this.unitSurfaceNormal = new dis.Vector3Float(); 

   /** This field shall represent the degree to which energy is conserved in a collision */
  this.coefficientOfRestitution = 0;

} // end of class
/**
 * Section 5.3.3.2. Information about a collision. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.CollisionPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 4;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 1;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** ID of the entity that issued the collision PDU */
   this.issuingEntityID = new dis.EntityID(); 

   /** ID of entity that has collided with the issuing entity ID */
   this.collidingEntityID = new dis.EntityID(); 

   /** ID of event */
   this.eventID = new dis.EventID(); 

   /** ID of event */
  this.collisionType = 0;

   /** some padding */
  this.pad = 0;

   /** velocity at collision */
   this.velocity = new dis.Vector3Float(); 

   /** mass of issuing entity */
  this.mass = 0;

   /** Location with respect to entity the issuing entity collided with */
   this.location = new dis.Vector3Float(); 

} // end of class
/**
 * Section 5.3.6.12. Arbitrary messages can be entered into the data stream via use of this PDU. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.CommentPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 22;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 5;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Entity that is sending message */
   this.originatingEntityID = new dis.EntityID(); 

   /** Entity that is intended to receive message */
   this.receivingEntityID = new dis.EntityID(); 

   /** Number of fixed datum records */
  this.numberOfFixedDatumRecords = 0;

   /** Number of variable datum records */
  this.numberOfVariableDatumRecords = 0;

   /** variable length list of fixed datums */
    fixedDatums = new Array();
 
   /** variable length list of variable length datums */
    variableDatums = new Array();
 
} // end of class
/**
 * Section 5.3.12.12: Arbitrary messages. Only reliable this time. Neds manual intervention     to fix padding in variable datums. UNFINISHED
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.CommentReliablePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 62;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 10;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Object originatig the request */
   this.originatingEntityID = new dis.EntityID(); 

   /** Object with which this point object is associated */
   this.receivingEntityID = new dis.EntityID(); 

   /** Fixed datum record count */
  this.numberOfFixedDatumRecords = 0;

   /** variable datum record count */
  this.numberOfVariableDatumRecords = 0;

   /** Fixed datum records */
    fixedDatumRecords = new Array();
 
   /** Variable datum records */
    variableDatumRecords = new Array();
 
} // end of class
/**
 * Section 5.3.6.1. Create a new entity. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.CreateEntityPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 11;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 5;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Entity that is sending message */
   this.originatingEntityID = new dis.EntityID(); 

   /** Entity that is intended to receive message */
   this.receivingEntityID = new dis.EntityID(); 

   /** Identifier for the request */
  this.requestID = 0;

} // end of class
/**
 * Section 5.3.12.1: creation of an entity , reliable. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.CreateEntityReliablePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 51;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 10;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Object originatig the request */
   this.originatingEntityID = new dis.EntityID(); 

   /** Object with which this point object is associated */
   this.receivingEntityID = new dis.EntityID(); 

   /** level of reliability service used for this transaction */
  this.requiredReliabilityService = 0;

   /** padding */
  this.pad1 = 0;

   /** padding */
  this.pad2 = 0;

   /** Request ID */
  this.requestID = 0;

} // end of class
/**
 * Section 5.3.6.10. Information issued in response to a data query pdu or a set data pdu is communicated using a data pdu. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.DataPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 20;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 5;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Entity that is sending message */
   this.originatingEntityID = new dis.EntityID(); 

   /** Entity that is intended to receive message */
   this.receivingEntityID = new dis.EntityID(); 

   /** ID of request */
  this.requestID = 0;

   /** padding */
  this.padding1 = 0;

   /** Number of fixed datum records */
  this.numberOfFixedDatumRecords = 0;

   /** Number of variable datum records */
  this.numberOfVariableDatumRecords = 0;

   /** variable length list of fixed datums */
    fixedDatums = new Array();
 
   /** variable length list of variable length datums */
    variableDatums = new Array();
 
} // end of class
/**
 * Section 5.3.6.8. Request for data from an entity. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.DataQueryPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 18;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 5;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Entity that is sending message */
   this.originatingEntityID = new dis.EntityID(); 

   /** Entity that is intended to receive message */
   this.receivingEntityID = new dis.EntityID(); 

   /** ID of request */
  this.requestID = 0;

   /** time issues between issues of Data PDUs. Zero means send once only. */
  this.timeInterval = 0;

   /** Number of fixed datum records */
  this.numberOfFixedDatumRecords = 0;

   /** Number of variable datum records */
  this.numberOfVariableDatumRecords = 0;

   /** variable length list of fixed datums */
    fixedDatums = new Array();
 
   /** variable length list of variable length datums */
    variableDatums = new Array();
 
} // end of class
/**
 * Section 5.3.12.8: request for data from an entity. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.DataQueryReliablePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 58;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 10;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Object originatig the request */
   this.originatingEntityID = new dis.EntityID(); 

   /** Object with which this point object is associated */
   this.receivingEntityID = new dis.EntityID(); 

   /** level of reliability service used for this transaction */
  this.requiredReliabilityService = 0;

   /** padding */
  this.pad1 = 0;

   /** padding */
  this.pad2 = 0;

   /** request ID */
  this.requestID = 0;

   /** time interval between issuing data query PDUs */
  this.timeInterval = 0;

   /** Fixed datum record count */
  this.numberOfFixedDatumRecords = 0;

   /** variable datum record count */
  this.numberOfVariableDatumRecords = 0;

   /** Fixed datum records */
    fixedDatumRecords = new Array();
 
   /** Variable datum records */
    variableDatumRecords = new Array();
 
} // end of class
/**
 * Section 5.3.12.10: issued in response to a data query R or set dataR pdu. Needs manual intervention      to fix padding on variable datums. UNFINSIHED
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.DataReliablePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 60;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 10;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Object originatig the request */
   this.originatingEntityID = new dis.EntityID(); 

   /** Object with which this point object is associated */
   this.receivingEntityID = new dis.EntityID(); 

   /** Request ID */
  this.requestID = 0;

   /** level of reliability service used for this transaction */
  this.requiredReliabilityService = 0;

   /** padding */
  this.pad1 = 0;

   /** padding */
  this.pad2 = 0;

   /** Fixed datum record count */
  this.numberOfFixedDatumRecords = 0;

   /** variable datum record count */
  this.numberOfVariableDatumRecords = 0;

   /** Fixed datum records */
    fixedDatumRecords = new Array();
 
   /** Variable datum records */
    variableDatumRecords = new Array();
 
} // end of class
/**
 * represents values used in dead reckoning algorithms
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.DeadReckoningParameter = function()
{
   /** enumeration of what dead reckoning algorighm to use */
  this.deadReckoningAlgorithm = 0;

   /** other parameters to use in the dead reckoning algorithm */
   /** Linear acceleration of the entity */
   this.entityLinearAcceleration = new dis.Vector3Float(); 

   /** angular velocity of the entity */
   this.entityAngularVelocity = new dis.Vector3Float(); 

} // end of class
/**
 * Section 5.3.7.2. Handles designating operations. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.DesignatorPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 24;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 6;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** ID of the entity designating */
   this.designatingEntityID = new dis.EntityID(); 

   /** This field shall specify a unique emitter database number assigned to  differentiate between otherwise similar or identical emitter beams within an emitter system. */
  this.codeName = 0;

   /** ID of the entity being designated */
   this.designatedEntityID = new dis.EntityID(); 

   /** This field shall identify the designator code being used by the designating entity  */
  this.designatorCode = 0;

   /** This field shall identify the designator output power in watts */
  this.designatorPower = 0;

   /** This field shall identify the designator wavelength in units of microns */
  this.designatorWavelength = 0;

   /** designtor spot wrt the designated entity */
   this.designatorSpotWrtDesignated = new dis.Vector3Float(); 

   /** designtor spot wrt the designated entity */
   this.designatorSpotLocation = new dis.Vector3Double(); 

   /** Dead reckoning algorithm */
  this.deadReckoningAlgorithm = 0;

   /** padding */
  this.padding1 = 0;

   /** padding */
  this.padding2 = 0;

   /** linear accelleration of entity */
   this.entityLinearAcceleration = new dis.Vector3Float(); 

} // end of class
/**
 * Section 5.3.4.2. Information about stuff exploding. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.DetonationPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 3;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 2;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** ID of the entity that shot */
   this.firingEntityID = new dis.EntityID(); 

   /** ID of the entity that is being shot at */
   this.targetEntityID = new dis.EntityID(); 

   /** ID of muntion that was fired */
   this.munitionID = new dis.EntityID(); 

   /** ID firing event */
   this.eventID = new dis.EventID(); 

   /** ID firing event */
   this.velocity = new dis.Vector3Float(); 

   /** where the detonation is, in world coordinates */
   this.locationInWorldCoordinates = new dis.Vector3Double(); 

   /** Describes munition used */
   this.burstDescriptor = new dis.BurstDescriptor(); 

   /** location of the detonation or impact in the target entity's coordinate system. This information should be used for damage assessment. */
   this.locationInEntityCoordinates = new dis.Vector3Float(); 

   /** result of the explosion */
  this.detonationResult = 0;

   /** How many articulation parameters we have */
  this.numberOfArticulationParameters = 0;

   /** padding */
  this.pad = 0;

    articulationParameters = new Array();
 
} // end of class
/**
 * Section 5.3.7. Electronic Emissions. Abstract superclass for distirubted emissions PDU
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.DistributedEmissionsFamilyPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 0;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 6;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

} // end of class
/**
 * 64 bit piece of data
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.EightByteChunk = function()
{
   /** Eight bytes of arbitrary data */
} // end of class
/**
 * Description of one electronic emission beam
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.ElectronicEmissionBeamData = function()
{
   /** This field shall specify the length of this beams data in 32 bit words */
  this.beamDataLength = 0;

   /** This field shall specify a unique emitter database number assigned to differentiate between otherwise similar or identical emitter beams within an emitter system. */
  this.beamIDNumber = 0;

   /** This field shall specify a Beam Parameter Index number that shall be used by receiving entities in conjunction with the Emitter Name field to provide a pointer to the stored database parameters required to regenerate the beam.  */
  this.beamParameterIndex = 0;

   /** Fundamental parameter data such as frequency range, beam sweep, etc. */
   this.fundamentalParameterData = new dis.FundamentalParameterData(); 

   /** beam function of a particular beam */
  this.beamFunction = 0;

   /** Number of track/jam targets */
  this.numberOfTrackJamTargets = 0;

   /** wheher or not the receiving simulation apps can assume all the targets in the scan pattern are being tracked/jammed */
  this.highDensityTrackJam = 0;

   /** padding */
  this.pad4 = 0;

   /** identify jamming techniques used */
  this.jammingModeSequence = 0;

   /** variable length list of track/jam targets */
    trackJamTargets = new Array();
 
} // end of class
/**
 * Data about one electronic system
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.ElectronicEmissionSystemData = function()
{
   /** This field shall specify the length of this emitter system�s data (including beam data and its track/jam information) in 32-bit words. The length shall include the System Data Length field.  */
  this.systemDataLength = 0;

   /** This field shall specify the number of beams being described in the current PDU for the system being described.  */
  this.numberOfBeams = 0;

   /** padding. */
  this.emissionsPadding2 = 0;

   /** This field shall specify information about a particular emitter system */
   this.emitterSystem = new dis.EmitterSystem(); 

   /** Location with respect to the entity */
   this.location = new dis.Vector3Float(); 

   /** variable length list of beam data records */
    beamDataRecords = new Array();
 
} // end of class
/**
 * Section 5.3.7.1. Information about active electronic warfare (EW) emissions and active EW countermeasures shall be communicated using an Electromagnetic Emission PDU. COMPLETE (I think)
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.ElectronicEmissionsPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 23;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 6;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** ID of the entity emitting */
   this.emittingEntityID = new dis.EntityID(); 

   /** ID of event */
   this.eventID = new dis.EventID(); 

   /** This field shall be used to indicate if the data in the PDU represents a state update or just data that has changed since issuance of the last Electromagnetic Emission PDU [relative to the identified entity and emission system(s)]. */
  this.stateUpdateIndicator = 0;

   /** This field shall specify the number of emission systems being described in the current PDU. */
  this.numberOfSystems = 0;

   /** padding */
  this.paddingForEmissionsPdu = 0;

   /** Electronic emmissions systems */
    systems = new Array();
 
} // end of class
/**
 * Section 5.2.11. This field shall specify information about a particular emitter system
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.EmitterSystem = function()
{
   /** Name of the emitter, 16 bit enumeration */
  this.emitterName = 0;

   /** function of the emitter, 8 bit enumeration */
  this.function = 0;

   /** emitter ID, 8 bit enumeration */
  this.emitterIdNumber = 0;

} // end of class
/**
 * Each entity in a given DIS simulation application shall be given an entity identifier number unique to all  other entities in that application. This identifier number is valid for the duration of the exercise; however,  entity identifier numbers may be reused when all possible numbers have been exhausted. No entity shall  have an entity identifier number of NO_ENTITY, ALL_ENTITIES, or RQST_ASSIGN_ID. The entity iden-  tifier number need not be registered or retained for future exercises. The entity identifier number shall be  specified by a 16-bit unsigned integer.  An entity identifier number equal to zero with valid site and application identification shall address a  simulation application. An entity identifier number equal to ALL_ENTITIES shall mean all entities within  the specified site and application. An entity identifier number equal to RQST_ASSIGN_ID allows the  receiver of the create entity to define the entity identifier number of the new entity.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.EntityID = function()
{
   /** The site ID */
  this.site = 0;

   /** The application ID */
  this.application = 0;

   /** the entity ID */
  this.entity = 0;

} // end of class
/**
 * Section 5.3.3. Common superclass for EntityState, Collision, collision-elastic, and entity state update PDUs. This should be abstract. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.EntityInformationFamilyPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 0;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 1;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

} // end of class
/**
 * Section 5.3.9. Common superclass for EntityManagment PDUs, including aggregate state, isGroupOf, TransferControLRequest, and isPartOf
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.EntityManagementFamilyPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 0;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 7;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

} // end of class
/**
 * Section 5.3.3.1. Represents the postion and state of one entity in the world. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.EntityStatePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 1;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 1;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Unique ID for an entity that is tied to this state information */
   this.entityID = new dis.EntityID(); 

   /** What force this entity is affiliated with, eg red, blue, neutral, etc */
  this.forceId = 0;

   /** How many articulation parameters are in the variable length list */
  this.numberOfArticulationParameters = 0;

   /** Describes the type of entity in the world */
   this.entityType = new dis.EntityType(); 

   this.alternativeEntityType = new dis.EntityType(); 

   /** Describes the speed of the entity in the world */
   this.entityLinearVelocity = new dis.Vector3Float(); 

   /** describes the location of the entity in the world */
   this.entityLocation = new dis.Vector3Double(); 

   /** describes the orientation of the entity, in euler angles */
   this.entityOrientation = new dis.Orientation(); 

   /** a series of bit flags that are used to help draw the entity, such as smoking, on fire, etc. */
  this.entityAppearance = 0;

   /** parameters used for dead reckoning */
   this.deadReckoningParameters = new dis.DeadReckoningParameter(); 

   /** characters that can be used for debugging, or to draw unique strings on the side of entities in the world */
   this.marking = new dis.Marking(); 

   /** a series of bit flags */
  this.capabilities = 0;

   /** variable length list of articulation parameters */
    articulationParameters = new Array();
 
} // end of class
/**
 * 5.3.3.4. Nonstatic information about a particular entity may be communicated by issuing an Entity State Update PDU. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.EntityStateUpdatePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 67;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 1;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** This field shall identify the entity issuing the PDU */
   this.entityID = new dis.EntityID(); 

   /** Padding */
  this.padding1 = 0;

   /** How many articulation parameters are in the variable length list */
  this.numberOfArticulationParameters = 0;

   /** Describes the speed of the entity in the world */
   this.entityLinearVelocity = new dis.Vector3Float(); 

   /** describes the location of the entity in the world */
   this.entityLocation = new dis.Vector3Double(); 

   /** describes the orientation of the entity, in euler angles */
   this.entityOrientation = new dis.Orientation(); 

   /** a series of bit flags that are used to help draw the entity, such as smoking, on fire, etc. */
  this.entityAppearance = 0;

    articulationParameters = new Array();
 
} // end of class
/**
 * Section 5.2.16. Identifies the type of entity, including kind of entity, domain (surface, subsurface, air, etc) country, category, etc.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.EntityType = function()
{
   /** Kind of entity */
  this.entityKind = 0;

   /** Domain of entity (air, surface, subsurface, space, etc) */
  this.domain = 0;

   /** country to which the design of the entity is attributed */
  this.country = 0;

   /** category of entity */
  this.category = 0;

   /** subcategory of entity */
  this.subcategory = 0;

   /** specific info based on subcategory field. Renamed from specific because that is a reserved word in SQL */
  this.spec = 0;

  this.extra = 0;

} // end of class
/**
 * Section 5.2.40. Information about a geometry, a state associated with a geometry, a bounding volume, or an associated entity ID. NOTE: this class requires hand coding.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.Environment = function()
{
   /** Record type */
  this.environmentType = 0;

   /** length, in bits */
  this.length = 0;

   /** Identify the sequentially numbered record index */
  this.recordIndex = 0;

   /** padding */
  this.padding1 = 0;

   /** Geometry or state record */
  this.geometry = 0;

   /** padding to bring the total size up to a 64 bit boundry */
  this.padding2 = 0;

} // end of class
/**
 * Section 5.3.11.1: Information about environmental effects and processes. This requires manual cleanup. the environmental        record is variable, as is the padding. UNFINISHED
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.EnvironmentalProcessPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 41;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 9;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Environmental process ID */
   this.environementalProcessID = new dis.EntityID(); 

   /** Environment type */
   this.environmentType = new dis.EntityType(); 

   /** model type */
  this.modelType = 0;

   /** Environment status */
  this.environmentStatus = 0;

   /** number of environment records  */
  this.numberOfEnvironmentRecords = 0;

   /** PDU sequence number for the environmentla process if pdu sequencing required */
  this.sequenceNumber = 0;

   /** environemt records */
    environmentRecords = new Array();
 
} // end of class
/**
 * Section 5.2.18. Identifies a unique event in a simulation via the combination of three values
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.EventID = function()
{
   /** The site ID */
  this.site = 0;

   /** The application ID */
  this.application = 0;

   /** the number of the event */
  this.eventNumber = 0;

} // end of class
/**
 * Section 5.3.6.11. Reports occurance of a significant event to the simulation manager. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.EventReportPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 21;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 5;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Entity that is sending message */
   this.originatingEntityID = new dis.EntityID(); 

   /** Entity that is intended to receive message */
   this.receivingEntityID = new dis.EntityID(); 

   /** Type of event */
  this.eventType = 0;

   /** padding */
  this.padding1 = 0;

   /** Number of fixed datum records */
  this.numberOfFixedDatumRecords = 0;

   /** Number of variable datum records */
  this.numberOfVariableDatumRecords = 0;

   /** variable length list of fixed datums */
    fixedDatums = new Array();
 
   /** variable length list of variable length datums */
    variableDatums = new Array();
 
} // end of class
/**
 * Section 5.3.12.11: reports the occurance of a significatnt event to the simulation manager. Needs manual     intervention to fix padding in variable datums. UNFINISHED.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.EventReportReliablePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 61;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 10;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Object originatig the request */
   this.originatingEntityID = new dis.EntityID(); 

   /** Object with which this point object is associated */
   this.receivingEntityID = new dis.EntityID(); 

   /** Event type */
  this.eventType = 0;

   /** padding */
  this.pad1 = 0;

   /** Fixed datum record count */
  this.numberOfFixedDatumRecords = 0;

   /** variable datum record count */
  this.numberOfVariableDatumRecords = 0;

   /** Fixed datum records */
    fixedDatumRecords = new Array();
 
   /** Variable datum records */
    variableDatumRecords = new Array();
 
} // end of class
/**
 * Section 5.3.3.1. Represents the postion and state of one entity in the world. This is identical in function to entity state pdu, but generates less garbage to collect in the Java world. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.FastEntityStatePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 1;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 1;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** The site ID */
  this.site = 0;

   /** The application ID */
  this.application = 0;

   /** the entity ID */
  this.entity = 0;

   /** what force this entity is affiliated with, eg red, blue, neutral, etc */
  this.forceId = 0;

   /** How many articulation parameters are in the variable length list */
  this.numberOfArticulationParameters = 0;

   /** Kind of entity */
  this.entityKind = 0;

   /** Domain of entity (air, surface, subsurface, space, etc) */
  this.domain = 0;

   /** country to which the design of the entity is attributed */
  this.country = 0;

   /** category of entity */
  this.category = 0;

   /** subcategory of entity */
  this.subcategory = 0;

   /** specific info based on subcategory field. Name changed from specific because that is a reserved word in SQL. */
  this.specif = 0;

  this.extra = 0;

   /** Kind of entity */
  this.altEntityKind = 0;

   /** Domain of entity (air, surface, subsurface, space, etc) */
  this.altDomain = 0;

   /** country to which the design of the entity is attributed */
  this.altCountry = 0;

   /** category of entity */
  this.altCategory = 0;

   /** subcategory of entity */
  this.altSubcategory = 0;

   /** specific info based on subcategory field */
  this.altSpecific = 0;

  this.altExtra = 0;

   /** X velo */
  this.xVelocity = 0;

   /** y Value */
  this.yVelocity = 0;

   /** Z value */
  this.zVelocity = 0;

   /** X value */
  this.xLocation = 0;

   /** y Value */
  this.yLocation = 0;

   /** Z value */
  this.zLocation = 0;

  this.psi = 0;

  this.theta = 0;

  this.phi = 0;

   /** a series of bit flags that are used to help draw the entity, such as smoking, on fire, etc. */
  this.entityAppearance = 0;

   /** enumeration of what dead reckoning algorighm to use */
  this.deadReckoningAlgorithm = 0;

   /** other parameters to use in the dead reckoning algorithm */
   /** X value */
  this.xAcceleration = 0;

   /** y Value */
  this.yAcceleration = 0;

   /** Z value */
  this.zAcceleration = 0;

   /** X value */
  this.xAngularVelocity = 0;

   /** y Value */
  this.yAngularVelocity = 0;

   /** Z value */
  this.zAngularVelocity = 0;

   /** characters that can be used for debugging, or to draw unique strings on the side of entities in the world */
   /** a series of bit flags */
  this.capabilities = 0;

   /** variable length list of articulation parameters */
    articulationParameters = new Array();
 
} // end of class
/**
 * Sectioin 5.3.4.1. Information about someone firing something. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.FirePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 2;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 2;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** ID of the entity that shot */
   this.firingEntityID = new dis.EntityID(); 

   /** ID of the entity that is being shot at */
   this.targetEntityID = new dis.EntityID(); 

   /** ID of the munition that is being shot */
   this.munitionID = new dis.EntityID(); 

   /** ID of event */
   this.eventID = new dis.EventID(); 

  this.fireMissionIndex = 0;

   /** location of the firing event */
   this.locationInWorldCoordinates = new dis.Vector3Double(); 

   /** Describes munitions used in the firing event */
   this.burstDescriptor = new dis.BurstDescriptor(); 

   /** Velocity of the ammunition */
   this.velocity = new dis.Vector3Float(); 

   /** range to the target. Note the word range is a SQL reserved word. */
  this.rangeToTarget = 0;

} // end of class
/**
 * Section 5.2.18. Fixed Datum Record
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.FixedDatum = function()
{
   /** ID of the fixed datum */
  this.fixedDatumID = 0;

   /** Value for the fixed datum */
  this.fixedDatumValue = 0;

} // end of class
/**
 * 32 bit piece of data
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.FourByteChunk = function()
{
   /** four bytes of arbitrary data */
} // end of class
/**
 * Section 5.2.22. Contains electromagnetic emmision regineratin parameters that are        variable throughout a scenario dependent on the actions of the participants in the simulation. Also provides basic parametric data that may be used to support low-fidelity simulations.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.FundamentalParameterData = function()
{
   /** center frequency of the emission in hertz. */
  this.frequency = 0;

   /** Bandwidth of the frequencies corresponding to the fequency field. */
  this.frequencyRange = 0;

   /** Effective radiated power for the emission in DdBm. For a      radar noise jammer, indicates the peak of the transmitted power. */
  this.effectiveRadiatedPower = 0;

   /** Average repetition frequency of the emission in hertz. */
  this.pulseRepetitionFrequency = 0;

   /** Average pulse width  of the emission in microseconds. */
  this.pulseWidth = 0;

   /** Specifies the beam azimuth an elevation centers and corresponding half-angles     to describe the scan volume */
  this.beamAzimuthCenter = 0;

   /** Specifies the beam azimuth sweep to determine scan volume */
  this.beamAzimuthSweep = 0;

   /** Specifies the beam elevation center to determine scan volume */
  this.beamElevationCenter = 0;

   /** Specifies the beam elevation sweep to determine scan volume */
  this.beamElevationSweep = 0;

   /** allows receiver to synchronize its regenerated scan pattern to     that of the emmitter. Specifies the percentage of time a scan is through its pattern from its origion. */
  this.beamSweepSync = 0;

} // end of class
/**
 * 5.2.45. Fundamental IFF atc data
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.FundamentalParameterDataIff = function()
{
   /** ERP */
  this.erp = 0;

   /** frequency */
  this.frequency = 0;

   /** pgrf */
  this.pgrf = 0;

   /** Pulse width */
  this.pulseWidth = 0;

   /** Burst length */
  this.burstLength = 0;

   /** Applicable modes enumeration */
  this.applicableModes = 0;

   /** padding */
  this.pad2 = 0;

   /** padding */
  this.pad3 = 0;

} // end of class
/**
 * 5.2.44: Grid data record, a common abstract superclass for several subtypes 
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.GridAxisRecord = function()
{
   /** type of environmental sample */
  this.sampleType = 0;

   /** value that describes data representation */
  this.dataRepresentation = 0;

} // end of class
/**
 * 5.2.44: Grid data record, representation 0
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.GridAxisRecordRepresentation0 = function()
{
   /** type of environmental sample */
  this.sampleType = 0;

   /** value that describes data representation */
  this.dataRepresentation = 0;

   /** number of bytes of environmental state data */
  this.numberOfBytes = 0;

   /** variable length list of data parameters ^^^this is wrong--need padding as well */
    dataValues = new Array();
 
} // end of class
/**
 * 5.2.44: Grid data record, representation 1
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.GridAxisRecordRepresentation1 = function()
{
   /** type of environmental sample */
  this.sampleType = 0;

   /** value that describes data representation */
  this.dataRepresentation = 0;

   /** constant scale factor */
  this.fieldScale = 0;

   /** constant offset used to scale grid data */
  this.fieldOffset = 0;

   /** Number of data values */
  this.numberOfValues = 0;

   /** variable length list of data parameters ^^^this is wrong--need padding as well */
    dataValues = new Array();
 
} // end of class
/**
 * 5.2.44: Grid data record, representation 1
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.GridAxisRecordRepresentation2 = function()
{
   /** type of environmental sample */
  this.sampleType = 0;

   /** value that describes data representation */
  this.dataRepresentation = 0;

   /** number of values */
  this.numberOfValues = 0;

   /** variable length list of data parameters ^^^this is wrong--need padding as well */
    dataValues = new Array();
 
} // end of class
/**
 * Section 5.3.11.2: Information about globat, spatially varying enviornmental effects. This requires manual cleanup; the grid axis        records are variable sized. UNFINISHED
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.GriddedDataPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 42;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 9;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** environmental simulation application ID */
   this.environmentalSimulationApplicationID = new dis.EntityID(); 

   /** unique identifier for each piece of enviornmental data */
  this.fieldNumber = 0;

   /** sequence number for the total set of PDUS used to transmit the data */
  this.pduNumber = 0;

   /** Total number of PDUS used to transmit the data */
  this.pduTotal = 0;

   /** coordinate system of the grid */
  this.coordinateSystem = 0;

   /** number of grid axes for the environmental data */
  this.numberOfGridAxes = 0;

   /** are domain grid axes identidal to those of the priveious domain update? */
  this.constantGrid = 0;

   /** type of environment */
   this.environmentType = new dis.EntityType(); 

   /** orientation of the data grid */
   this.orientation = new dis.Orientation(); 

   /** valid time of the enviormental data sample, 64 bit unsigned int */
  this.sampleTime = 0;

   /** total number of all data values for all pdus for an environmental sample */
  this.totalValues = 0;

   /** total number of data values at each grid point. */
  this.vectorDimension = 0;

   /** padding */
  this.padding1 = 0;

   /** padding */
  this.padding2 = 0;

   /** Grid data ^^^This is wrong */
    gridDataList = new Array();
 
} // end of class
/**
 * 5.3.7.4.1: Navigational and IFF PDU. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.IffAtcNavAidsLayer1Pdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 28;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 6;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** ID of the entity that is the source of the emissions */
   this.emittingEntityId = new dis.EntityID(); 

   /** Number generated by the issuing simulation to associate realted events. */
   this.eventID = new dis.EventID(); 

   /** Location wrt entity. There is some ambugiuity in the standard here, but this is the order it is listed in the table. */
   this.location = new dis.Vector3Float(); 

   /** System ID information */
   this.systemID = new dis.SystemID(); 

   /** padding */
  this.pad2 = 0;

   /** fundamental parameters */
   this.fundamentalParameters = new dis.IffFundamentalData(); 

} // end of class
/**
 * Section 5.3.7.4.2 When present, layer 2 should follow layer 1 and have the following fields. This requires manual cleanup.        the beamData attribute semantics are used in multiple ways. UNFINSISHED
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.IffAtcNavAidsLayer2Pdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 28;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 6;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** ID of the entity that is the source of the emissions */
   this.emittingEntityId = new dis.EntityID(); 

   /** Number generated by the issuing simulation to associate realted events. */
   this.eventID = new dis.EventID(); 

   /** Location wrt entity. There is some ambugiuity in the standard here, but this is the order it is listed in the table. */
   this.location = new dis.Vector3Float(); 

   /** System ID information */
   this.systemID = new dis.SystemID(); 

   /** padding */
  this.pad2 = 0;

   /** fundamental parameters */
   this.fundamentalParameters = new dis.IffFundamentalData(); 

   /** layer header */
   this.layerHeader = new dis.LayerHeader(); 

   /** beam data */
   this.beamData = new dis.BeamData(); 

   /** Secondary operational data, 5.2.57 */
   this.secondaryOperationalData = new dis.BeamData(); 

   /** variable length list of fundamental parameters. ^^^This is wrong */
    fundamentalIffParameters = new Array();
 
} // end of class
/**
 * 5.2.42. Basic operational data ofr IFF ATC NAVAIDS
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.IffFundamentalData = function()
{
   /** system status */
  this.systemStatus = 0;

   /** Alternate parameter 4 */
  this.alternateParameter4 = 0;

   /** eight boolean fields */
  this.informationLayers = 0;

   /** enumeration */
  this.modifier = 0;

   /** parameter, enumeration */
  this.parameter1 = 0;

   /** parameter, enumeration */
  this.parameter2 = 0;

   /** parameter, enumeration */
  this.parameter3 = 0;

   /** parameter, enumeration */
  this.parameter4 = 0;

   /** parameter, enumeration */
  this.parameter5 = 0;

   /** parameter, enumeration */
  this.parameter6 = 0;

} // end of class
/**
 * 5.2.46.  Intercom communcations parameters
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.IntercomCommunicationsParameters = function()
{
   /** Type of intercom parameters record */
  this.recordType = 0;

   /** length of record */
  this.recordLength = 0;

   /** Jerks. Looks like the committee is forcing a lookup of the record type parameter to find out how long the field is. This is a placeholder. */
  this.recordSpecificField = 0;

} // end of class
/**
 * Section 5.3.8.5. Detailed inofrmation about the state of an intercom device and the actions it is requestion         of another intercom device, or the response to a requested action. Required manual intervention to fix the intercom parameters,        which can be of varialbe length. UNFINSISHED
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.IntercomControlPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 32;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 4;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** ID of the entitythat is the source of the communication */
   this.entityId = new dis.EntityID(); 

   /** particular radio within an entity */
  this.radioId = 0;

   /** control type */
  this.controlType = 0;

   /** control type */
  this.communicationsChannelType = 0;

   /** Source entity ID */
   this.sourceEntityID = new dis.EntityID(); 

   /** The specific intercom device being simulated within an entity. */
  this.sourceCommunicationsDeviceID = 0;

   /** Line number to which the intercom control refers */
  this.sourceLineID = 0;

   /** priority of this message relative to transmissons from other intercom devices */
  this.transmitPriority = 0;

   /** current transmit state of the line */
  this.transmitLineState = 0;

   /** detailed type requested. */
  this.command = 0;

   /** eid of the entity that has created this intercom channel. */
   this.masterEntityID = new dis.EntityID(); 

   /** specific intercom device that has created this intercom channel */
  this.masterCommunicationsDeviceID = 0;

   /** number of intercom parameters */
  this.intercomParametersLength = 0;

   /** ^^^This is wrong--the length of the data field is variable. Using a long for now. */
    intercomParameters = new Array();
 
} // end of class
/**
 * Section 5.3.8.4. Actual transmission of intercome voice data. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.IntercomSignalPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 31;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 4;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** ID of the entitythat is the source of the communication */
   this.entityId = new dis.EntityID(); 

   /** particular radio within an entity */
  this.radioId = 0;

   /** entity ID */
   this.entityID = new dis.EntityID(); 

   /** ID of communications device */
  this.communicationsDeviceID = 0;

   /** encoding scheme */
  this.encodingScheme = 0;

   /** tactical data link type */
  this.tdlType = 0;

   /** sample rate */
  this.sampleRate = 0;

   /** data length */
  this.dataLength = 0;

   /** samples */
  this.samples = 0;

   /** data bytes */
    data = new Array();
 
} // end of class
/**
 * Section 5.3.9.2 Information about a particular group of entities grouped together for the purposes of netowrk bandwidth         reduction or aggregation. Needs manual cleanup. The GED size requires a database lookup. UNFINISHED
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.IsGroupOfPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 34;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 7;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** ID of aggregated entities */
   this.groupEntityID = new dis.EntityID(); 

   /** type of entities constituting the group */
  this.groupedEntityCategory = 0;

   /** Number of individual entities constituting the group */
  this.numberOfGroupedEntities = 0;

   /** padding */
  this.pad2 = 0;

   /** latitude */
  this.latitude = 0;

   /** longitude */
  this.longitude = 0;

   /** GED records about each individual entity in the group. ^^^this is wrong--need a database lookup to find the actual size of the list elements */
    groupedEntityDescriptions = new Array();
 
} // end of class
/**
 * Section 5.3.9.4 The joining of two or more simulation entities is communicated by this PDU. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.IsPartOfPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 36;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 7;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** ID of entity originating PDU */
   this.orginatingEntityID = new dis.EntityID(); 

   /** ID of entity receiving PDU */
   this.receivingEntityID = new dis.EntityID(); 

   /** relationship of joined parts */
   this.relationship = new dis.Relationship(); 

   /** location of part; centroid of part in host's coordinate system. x=range, y=bearing, z=0 */
   this.partLocation = new dis.Vector3Float(); 

   /** named location */
   this.namedLocationID = new dis.NamedLocation(); 

   /** entity type */
   this.partEntityType = new dis.EntityType(); 

} // end of class
/**
 * 5.2.47.  Layer header.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.LayerHeader = function()
{
   /** Layer number */
  this.layerNumber = 0;

   /** Layer speccific information enumeration */
  this.layerSpecificInformaiton = 0;

   /** information length */
  this.length = 0;

} // end of class
/**
 * Section 5.3.11.4: Information abut the addition or modification of a synthecic enviroment object that      is anchored to the terrain with a single point and has size or orientation. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.LinearObjectStatePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 44;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 9;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Object in synthetic environment */
   this.objectID = new dis.EntityID(); 

   /** Object with which this point object is associated */
   this.referencedObjectID = new dis.EntityID(); 

   /** unique update number of each state transition of an object */
  this.updateNumber = 0;

   /** force ID */
  this.forceID = 0;

   /** number of linear segment parameters */
  this.numberOfSegments = 0;

   /** requesterID */
   this.requesterID = new dis.SimulationAddress(); 

   /** receiver ID */
   this.receivingID = new dis.SimulationAddress(); 

   /** Object type */
   this.objectType = new dis.ObjectType(); 

   /** Linear segment parameters */
    linearSegmentParameters = new Array();
 
} // end of class
/**
 * 5.2.48: Linear segment parameters
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.LinearSegmentParameter = function()
{
   /** number of segments */
  this.segmentNumber = 0;

   /** segment appearance */
   this.segmentAppearance = new dis.SixByteChunk(); 

   /** location */
   this.location = new dis.Vector3Double(); 

   /** orientation */
   this.orientation = new dis.Orientation(); 

   /** segmentLength */
  this.segmentLength = 0;

   /** segmentWidth */
  this.segmentWidth = 0;

   /** segmentHeight */
  this.segmentHeight = 0;

   /** segment Depth */
  this.segmentDepth = 0;

   /** segment Depth */
  this.pad1 = 0;

} // end of class
/**
 * Section 5.3.5. Abstract superclass for logistics PDUs. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.LogisticsFamilyPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 0;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 3;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

} // end of class
/**
 * Section 5.2.15. Specifies the character set used inthe first byte, followed by 11 characters of text data.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.Marking = function()
{
   /** The character set */
  this.characterSet = 0;

   /** The characters */
} // end of class
/**
 * Section 5.3.10.3 Information about individual mines within a minefield. This is very, very wrong. UNFINISHED
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.MinefieldDataPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 39;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 8;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Minefield ID */
   this.minefieldID = new dis.EntityID(); 

   /** ID of entity making request */
   this.requestingEntityID = new dis.EntityID(); 

   /** Minefield sequence number */
  this.minefieldSequenceNumbeer = 0;

   /** request ID */
  this.requestID = 0;

   /** pdu sequence number */
  this.pduSequenceNumber = 0;

   /** number of pdus in response */
  this.numberOfPdus = 0;

   /** how many mines are in this PDU */
  this.numberOfMinesInThisPdu = 0;

   /** how many sensor type are in this PDU */
  this.numberOfSensorTypes = 0;

   /** padding */
  this.pad2 = 0;

   /** 32 boolean fields */
  this.dataFilter = 0;

   /** Mine type */
   this.mineType = new dis.EntityType(); 

   /** Sensor types, each 16 bits long */
    sensorTypes = new Array();
 
   /** Padding to get things 32-bit aligned. ^^^this is wrong--dyanmically sized padding needed */
  this.pad3 = 0;

   /** Mine locations */
    mineLocation = new Array();
 
} // end of class
/**
 * Section 5.3.10.1 Abstract superclass for PDUs relating to minefields
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.MinefieldFamilyPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 0;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 8;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

} // end of class
/**
 * Section 5.3.10.2 Query a minefield for information about individual mines. Requires manual clean up to get the padding right. UNFINISHED
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.MinefieldQueryPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 38;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 8;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Minefield ID */
   this.minefieldID = new dis.EntityID(); 

   /** EID of entity making the request */
   this.requestingEntityID = new dis.EntityID(); 

   /** request ID */
  this.requestID = 0;

   /** Number of perimeter points for the minefield */
  this.numberOfPerimeterPoints = 0;

   /** Padding */
  this.pad2 = 0;

   /** Number of sensor types */
  this.numberOfSensorTypes = 0;

   /** data filter, 32 boolean fields */
  this.dataFilter = 0;

   /** Entity type of mine being requested */
   this.requestedMineType = new dis.EntityType(); 

   /** perimeter points of request */
    requestedPerimeterPoints = new Array();
 
   /** Sensor types, each 16 bits long */
    sensorTypes = new Array();
 
} // end of class
/**
 * Section 5.3.10.4 proivde the means to request a retransmit of a minefield data pdu. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.MinefieldResponseNackPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 40;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 8;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Minefield ID */
   this.minefieldID = new dis.EntityID(); 

   /** entity ID making the request */
   this.requestingEntityID = new dis.EntityID(); 

   /** request ID */
  this.requestID = 0;

   /** how many pdus were missing */
  this.numberOfMissingPdus = 0;

   /** PDU sequence numbers that were missing */
    missingPduSequenceNumbers = new Array();
 
} // end of class
/**
 * Section 5.3.10.1 Abstract superclass for PDUs relating to minefields. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.MinefieldStatePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 37;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 8;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Minefield ID */
   this.minefieldID = new dis.EntityID(); 

   /** Minefield sequence */
  this.minefieldSequence = 0;

   /** force ID */
  this.forceID = 0;

   /** Number of permieter points */
  this.numberOfPerimeterPoints = 0;

   /** type of minefield */
   this.minefieldType = new dis.EntityType(); 

   /** how many mine types */
  this.numberOfMineTypes = 0;

   /** location of minefield in world coords */
   this.minefieldLocation = new dis.Vector3Double(); 

   /** orientation of minefield */
   this.minefieldOrientation = new dis.Orientation(); 

   /** appearance bitflags */
  this.appearance = 0;

   /** protocolMode */
  this.protocolMode = 0;

   /** perimeter points for the minefield */
    perimeterPoints = new Array();
 
   /** Type of mines */
    mineType = new Array();
 
} // end of class
/**
 * Radio modulation
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.ModulationType = function()
{
   /** spread spectrum, 16 bit boolean array */
  this.spreadSpectrum = 0;

   /** major */
  this.major = 0;

   /** detail */
  this.detail = 0;

   /** system */
  this.system = 0;

} // end of class
/**
 * discrete ostional relationsihip 
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.NamedLocation = function()
{
   /** station name enumeration */
  this.stationName = 0;

   /** station number */
  this.stationNumber = 0;

} // end of class
/**
 * Identifies type of object. This is a shorter version of EntityType that omits the specific and extra fields.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.ObjectType = function()
{
   /** Kind of entity */
  this.entityKind = 0;

   /** Domain of entity (air, surface, subsurface, space, etc) */
  this.domain = 0;

   /** country to which the design of the entity is attributed */
  this.country = 0;

   /** category of entity */
  this.category = 0;

   /** subcategory of entity */
  this.subcategory = 0;

} // end of class
/**
 * 8 bit piece of data
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.OneByteChunk = function()
{
   /** one byte of arbitrary data */
} // end of class
/**
 * Section 5.2.17. Three floating point values representing an orientation, psi, theta, and phi, aka the euler angles, in radians
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.Orientation = function()
{
  this.psi = 0;

  this.theta = 0;

  this.phi = 0;

} // end of class
/**
 * The superclass for all PDUs. This incorporates the PduHeader record, section 5.2.29.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.Pdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 0;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 0;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

} // end of class
/**
 * Used for XML compatability. A container that holds PDUs
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.PduContainer = function()
{
   /** Number of PDUs in the container list */
  this.numberOfPdus = 0;

   /** record sets */
    pdus = new Array();
 
} // end of class
/**
 * Non-DIS class, used on SQL databases
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.PduStream = function()
{
   /** Longish description of this PDU stream */
   /** short description of this PDU stream */
   /** Start time of recording, in Unix time */
  this.startTime = 0;

   /** stop time of recording, in Unix time */
  this.stopTime = 0;

} // end of class
/**
 * x,y point
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.Point = function()
{
   /** x */
  this.x = 0;

   /** y */
  this.y = 0;

} // end of class
/**
 * Section 5.3.11.3: Inormation abut the addition or modification of a synthecic enviroment object that is anchored      to the terrain with a single point. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.PointObjectStatePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 43;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 9;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Object in synthetic environment */
   this.objectID = new dis.EntityID(); 

   /** Object with which this point object is associated */
   this.referencedObjectID = new dis.EntityID(); 

   /** unique update number of each state transition of an object */
  this.updateNumber = 0;

   /** force ID */
  this.forceID = 0;

   /** modifications */
  this.modifications = 0;

   /** Object type */
   this.objectType = new dis.ObjectType(); 

   /** Object location */
   this.objectLocation = new dis.Vector3Double(); 

   /** Object orientation */
   this.objectOrientation = new dis.Orientation(); 

   /** Object apperance */
  this.objectAppearance = 0;

   /** requesterID */
   this.requesterID = new dis.SimulationAddress(); 

   /** receiver ID */
   this.receivingID = new dis.SimulationAddress(); 

   /** padding */
  this.pad2 = 0;

} // end of class
/**
 * Data about a propulsion system
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.PropulsionSystemData = function()
{
   /** powerSetting */
  this.powerSetting = 0;

   /** engine RPMs */
  this.engineRpm = 0;

} // end of class
/**
 * Section 5.3.8. Abstract superclass for radio communications PDUs.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.RadioCommunicationsFamilyPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 0;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 4;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** ID of the entitythat is the source of the communication */
   this.entityId = new dis.EntityID(); 

   /** particular radio within an entity */
  this.radioId = 0;

} // end of class
/**
 * Section 5.2.25. Identifies the type of radio
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.RadioEntityType = function()
{
   /** Kind of entity */
  this.entityKind = 0;

   /** Domain of entity (air, surface, subsurface, space, etc) */
  this.domain = 0;

   /** country to which the design of the entity is attributed */
  this.country = 0;

   /** category of entity */
  this.category = 0;

   /** specific info based on subcategory field */
  this.nomenclatureVersion = 0;

  this.nomenclature = 0;

} // end of class
/**
 * Section 5.3.8.3. Communication of a receiver state. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.ReceiverPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 27;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 4;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** ID of the entitythat is the source of the communication */
   this.entityId = new dis.EntityID(); 

   /** particular radio within an entity */
  this.radioId = 0;

   /** encoding scheme used, and enumeration */
  this.receiverState = 0;

   /** padding */
  this.padding1 = 0;

   /** received power */
  this.receivedPoser = 0;

   /** ID of transmitter */
   this.transmitterEntityId = new dis.EntityID(); 

   /** ID of transmitting radio */
  this.transmitterRadioId = 0;

} // end of class
/**
 * Section 5.3.12.13: A request for one or more records of data from an entity. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.RecordQueryReliablePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 65;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 10;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Object originatig the request */
   this.originatingEntityID = new dis.EntityID(); 

   /** Object with which this point object is associated */
   this.receivingEntityID = new dis.EntityID(); 

   /** request ID */
  this.requestID = 0;

   /** level of reliability service used for this transaction */
  this.requiredReliabilityService = 0;

   /** padding. The spec is unclear and contradictory here. */
  this.pad1 = 0;

   /** padding */
  this.pad2 = 0;

   /** event type */
  this.eventType = 0;

   /** time */
  this.time = 0;

   /** numberOfRecords */
  this.numberOfRecords = 0;

   /** record IDs */
    recordIDs = new Array();
 
} // end of class
/**
 * Record sets, used in transfer control request PDU
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.RecordSet = function()
{
   /** record ID */
  this.recordID = 0;

   /** record set serial number */
  this.recordSetSerialNumber = 0;

   /** record length */
  this.recordLength = 0;

   /** record count */
  this.recordCount = 0;

   /** ^^^This is wrong--variable sized data records */
  this.recordValues = 0;

   /** ^^^This is wrong--variable sized padding */
  this.pad4 = 0;

} // end of class
/**
 * 5.2.56. Purpose for joinging two entities
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.Relationship = function()
{
   /** Nature of join */
  this.nature = 0;

   /** position of join */
  this.position = 0;

} // end of class
/**
 * Section 5.3.6.2. Remove an entity. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.RemoveEntityPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 12;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 5;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Entity that is sending message */
   this.originatingEntityID = new dis.EntityID(); 

   /** Entity that is intended to receive message */
   this.receivingEntityID = new dis.EntityID(); 

   /** Identifier for the request */
  this.requestID = 0;

} // end of class
/**
 * Section 5.3.12.2: Removal of an entity , reliable. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.RemoveEntityReliablePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 52;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 10;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Object originatig the request */
   this.originatingEntityID = new dis.EntityID(); 

   /** Object with which this point object is associated */
   this.receivingEntityID = new dis.EntityID(); 

   /** level of reliability service used for this transaction */
  this.requiredReliabilityService = 0;

   /** padding */
  this.pad1 = 0;

   /** padding */
  this.pad2 = 0;

   /** Request ID */
  this.requestID = 0;

} // end of class
/**
 * Section 5.2.5.5. Repair is complete. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.RepairCompletePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 9;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 3;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Entity that is receiving service */
   this.receivingEntityID = new dis.EntityID(); 

   /** Entity that is supplying */
   this.repairingEntityID = new dis.EntityID(); 

   /** Enumeration for type of repair */
  this.repair = 0;

   /** padding, number prevents conflict with superclass ivar name */
  this.padding2 = 0;

} // end of class
/**
 * Section 5.2.5.6. Sent after repair complete PDU. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.RepairResponsePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 10;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 3;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Entity that is receiving service */
   this.receivingEntityID = new dis.EntityID(); 

   /** Entity that is supplying */
   this.repairingEntityID = new dis.EntityID(); 

   /** Result of repair operation */
  this.repairResult = 0;

   /** padding */
  this.padding1 = 0;

   /** padding */
  this.padding2 = 0;

} // end of class
/**
 * Section 5.2.5.4. Cancel of resupply by either the receiving or supplying entity. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.ResupplyCancelPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 8;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 3;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Entity that is receiving service */
   this.receivingEntityID = new dis.EntityID(); 

   /** Entity that is supplying */
   this.supplyingEntityID = new dis.EntityID(); 

} // end of class
/**
 * Section 5.3.5.2. Information about a request for supplies. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.ResupplyOfferPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 6;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 3;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Entity that is receiving service */
   this.receivingEntityID = new dis.EntityID(); 

   /** Entity that is supplying */
   this.supplyingEntityID = new dis.EntityID(); 

   /** how many supplies are being offered */
  this.numberOfSupplyTypes = 0;

   /** padding */
  this.padding1 = 0;

   /** padding */
  this.padding2 = 0;

    supplies = new Array();
 
} // end of class
/**
 * Section 5.3.5.3. Receipt of supplies is communiated. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.ResupplyReceivedPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 7;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 3;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Entity that is receiving service */
   this.receivingEntityID = new dis.EntityID(); 

   /** Entity that is supplying */
   this.supplyingEntityID = new dis.EntityID(); 

   /** how many supplies are being offered */
  this.numberOfSupplyTypes = 0;

   /** padding */
  this.padding1 = 0;

   /** padding */
  this.padding2 = 0;

    supplies = new Array();
 
} // end of class
/**
 * Section 5.3.7.5. SEES PDU, supplemental emissions entity state information. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.SeesPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 30;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 6;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Originating entity ID */
   this.orginatingEntityID = new dis.EntityID(); 

   /** IR Signature representation index */
  this.infraredSignatureRepresentationIndex = 0;

   /** acoustic Signature representation index */
  this.acousticSignatureRepresentationIndex = 0;

   /** radar cross section representation index */
  this.radarCrossSectionSignatureRepresentationIndex = 0;

   /** how many propulsion systems */
  this.numberOfPropulsionSystems = 0;

   /** how many vectoring nozzle systems */
  this.numberOfVectoringNozzleSystems = 0;

   /** variable length list of propulsion system data */
    propulsionSystemData = new Array();
 
   /** variable length list of vectoring system data */
    vectoringSystemData = new Array();
 
} // end of class
/**
 * Section 5.3.5.1. Information about a request for supplies. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.ServiceRequestPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 5;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 3;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Entity that is requesting service */
   this.requestingEntityID = new dis.EntityID(); 

   /** Entity that is providing the service */
   this.servicingEntityID = new dis.EntityID(); 

   /** type of service requested */
  this.serviceTypeRequested = 0;

   /** How many requested */
  this.numberOfSupplyTypes = 0;

   /** padding */
  this.serviceRequestPadding = 0;

    supplies = new Array();
 
} // end of class
/**
 * Section 5.3.6.9. Change state information with the data contained in this. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.SetDataPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 19;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 5;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Entity that is sending message */
   this.originatingEntityID = new dis.EntityID(); 

   /** Entity that is intended to receive message */
   this.receivingEntityID = new dis.EntityID(); 

   /** ID of request */
  this.requestID = 0;

   /** padding */
  this.padding1 = 0;

   /** Number of fixed datum records */
  this.numberOfFixedDatumRecords = 0;

   /** Number of variable datum records */
  this.numberOfVariableDatumRecords = 0;

   /** variable length list of fixed datums */
    fixedDatums = new Array();
 
   /** variable length list of variable length datums */
    variableDatums = new Array();
 
} // end of class
/**
 * Section 5.3.12.9: initializing or chaning internal state information, reliable. Needs manual intervention to fix     padding on variable datums. UNFINISHED
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.SetDataReliablePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 59;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 10;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Object originatig the request */
   this.originatingEntityID = new dis.EntityID(); 

   /** Object with which this point object is associated */
   this.receivingEntityID = new dis.EntityID(); 

   /** level of reliability service used for this transaction */
  this.requiredReliabilityService = 0;

   /** padding */
  this.pad1 = 0;

   /** padding */
  this.pad2 = 0;

   /** Request ID */
  this.requestID = 0;

   /** Fixed datum record count */
  this.numberOfFixedDatumRecords = 0;

   /** variable datum record count */
  this.numberOfVariableDatumRecords = 0;

   /** Fixed datum records */
    fixedDatumRecords = new Array();
 
   /** Variable datum records */
    variableDatumRecords = new Array();
 
} // end of class
/**
 * Section 5.3.12.14: Initializing or changing internal parameter info. Needs manual intervention     to fix padding in recrod set PDUs. UNFINISHED
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.SetRecordReliablePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 64;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 10;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Object originatig the request */
   this.originatingEntityID = new dis.EntityID(); 

   /** Object with which this point object is associated */
   this.receivingEntityID = new dis.EntityID(); 

   /** request ID */
  this.requestID = 0;

   /** level of reliability service used for this transaction */
  this.requiredReliabilityService = 0;

   /** padding. The spec is unclear and contradictory here. */
  this.pad1 = 0;

   /** padding */
  this.pad2 = 0;

   /** Number of record sets in list */
  this.numberOfRecordSets = 0;

   /** record sets */
    recordSets = new Array();
 
} // end of class
/**
 * Shaft RPMs, used in underwater acoustic clacluations.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.ShaftRPMs = function()
{
   /** Current shaft RPMs */
  this.currentShaftRPMs = 0;

   /** ordered shaft rpms */
  this.orderedShaftRPMs = 0;

   /** rate of change of shaft RPMs */
  this.shaftRPMRateOfChange = 0;

} // end of class
/**
 * Section 5.3.8.2. Detailed information about a radio transmitter. This PDU requires        manually written code to complete. The encodingScheme field can be used in multiple        ways, which requires hand-written code to finish. UNFINISHED
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.SignalPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 26;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 4;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** ID of the entitythat is the source of the communication */
   this.entityId = new dis.EntityID(); 

   /** particular radio within an entity */
  this.radioId = 0;

   /** encoding scheme used, and enumeration */
  this.encodingScheme = 0;

   /** tdl type */
  this.tdlType = 0;

   /** sample rate */
  this.sampleRate = 0;

   /** length od data */
  this.dataLength = 0;

   /** number of samples */
  this.samples = 0;

   /** list of eight bit values */
    data = new Array();
 
} // end of class
/**
 * Section 5.2.14.1. A Simulation Address  record shall consist of the Site Identification number and the Application Identification number.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.SimulationAddress = function()
{
   /** The site ID */
  this.site = 0;

   /** The application ID */
  this.application = 0;

} // end of class
/**
 * Section 5.3.6. Abstract superclass for PDUs relating to the simulation itself. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.SimulationManagementFamilyPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 0;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 5;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Entity that is sending message */
   this.originatingEntityID = new dis.EntityID(); 

   /** Entity that is intended to receive message */
   this.receivingEntityID = new dis.EntityID(); 

} // end of class
/**
 * Section 5.3.12: Abstract superclass for reliable simulation management PDUs
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.SimulationManagementWithReliabilityFamilyPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 0;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 10;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Object originatig the request */
   this.originatingEntityID = new dis.EntityID(); 

   /** Object with which this point object is associated */
   this.receivingEntityID = new dis.EntityID(); 

} // end of class
/**
 * 48 bit piece of data
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.SixByteChunk = function()
{
   /** six bytes of arbitrary data */
} // end of class
/**
 * Section 5.2.4.3. Used when the antenna pattern type in the transmitter pdu is of value 2.         Specified the direction and radiation pattern from a radio transmitter's antenna.        NOTE: this class must be hand-coded to clean up some implementation details.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.SphericalHarmonicAntennaPattern = function()
{
  this.harmonicOrder = 0;

} // end of class
/**
 * Section 5.2.6.3. Start or resume an exercise. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.StartResumePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 13;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 5;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Entity that is sending message */
   this.originatingEntityID = new dis.EntityID(); 

   /** Entity that is intended to receive message */
   this.receivingEntityID = new dis.EntityID(); 

   /** UTC time at which the simulation shall start or resume */
   this.realWorldTime = new dis.ClockTime(); 

   /** Simulation clock time at which the simulation shall start or resume */
   this.simulationTime = new dis.ClockTime(); 

   /** Identifier for the request */
  this.requestID = 0;

} // end of class
/**
 * Section 5.3.12.3: Start resume simulation, relaible. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.StartResumeReliablePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 53;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 10;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Object originatig the request */
   this.originatingEntityID = new dis.EntityID(); 

   /** Object with which this point object is associated */
   this.receivingEntityID = new dis.EntityID(); 

   /** time in real world for this operation to happen */
   this.realWorldTime = new dis.ClockTime(); 

   /** time in simulation for the simulation to resume */
   this.simulationTime = new dis.ClockTime(); 

   /** level of reliability service used for this transaction */
  this.requiredReliabilityService = 0;

   /** padding */
  this.pad1 = 0;

   /** padding */
  this.pad2 = 0;

   /** Request ID */
  this.requestID = 0;

} // end of class
/**
 * Section 5.2.3.4. Stop or freeze an exercise. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.StopFreezePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 14;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 5;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Entity that is sending message */
   this.originatingEntityID = new dis.EntityID(); 

   /** Entity that is intended to receive message */
   this.receivingEntityID = new dis.EntityID(); 

   /** UTC time at which the simulation shall stop or freeze */
   this.realWorldTime = new dis.ClockTime(); 

   /** Reason the simulation was stopped or frozen */
  this.reason = 0;

   /** Internal behavior of the simulation and its appearance while frozento the other participants */
  this.frozenBehavior = 0;

   /** padding */
  this.padding1 = 0;

   /** Request ID that is unique */
  this.requestID = 0;

} // end of class
/**
 * Section 5.3.12.4: Stop freeze simulation, relaible. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.StopFreezeReliablePdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 54;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 10;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** Object originatig the request */
   this.originatingEntityID = new dis.EntityID(); 

   /** Object with which this point object is associated */
   this.receivingEntityID = new dis.EntityID(); 

   /** time in real world for this operation to happen */
   this.realWorldTime = new dis.ClockTime(); 

   /** Reason for stopping/freezing simulation */
  this.reason = 0;

   /** internal behvior of the simulation while frozen */
  this.frozenBehavior = 0;

   /** reliablity level */
  this.requiredReliablityService = 0;

   /** padding */
  this.pad1 = 0;

   /** Request ID */
  this.requestID = 0;

} // end of class
/**
 * Section 5.2.30. A supply, and the amount of that supply. Similar to an entity kind but with the addition of a quantity.
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.SupplyQuantity = function()
{
   /** Type of supply */
   this.supplyType = new dis.EntityType(); 

   /** quantity to be supplied */
  this.quantity = 0;

} // end of class
/**
 * Section 5.3.11: Abstract superclass for synthetic environment PDUs
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.SyntheticEnvironmentFamilyPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 0;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 9;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

} // end of class
/**
 * 5.2.58. Used in IFF ATC PDU
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.SystemID = function()
{
   /** System Type */
  this.systemType = 0;

   /** System name, an enumeration */
  this.systemName = 0;

   /** System mode */
  this.systemMode = 0;

   /** Change Options */
  this.changeOptions = 0;

} // end of class
/**
 * One track/jam target
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.TrackJamTarget = function()
{
   /** track/jam target */
   this.trackJam = new dis.EntityID(); 

   /** Emitter ID */
  this.emitterID = 0;

   /** beam ID */
  this.beamID = 0;

} // end of class
/**
 * Section 5.3.9.3 Information initiating the dyanic allocation and control of simulation entities         between two simulation applications. Requires manual cleanup. The padding between record sets is variable. UNFINISHED
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.TransferControlRequestPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 35;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 7;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** ID of entity originating request */
   this.orginatingEntityID = new dis.EntityID(); 

   /** ID of entity receiving request */
   this.recevingEntityID = new dis.EntityID(); 

   /** ID ofrequest */
  this.requestID = 0;

   /** required level of reliabliity service. */
  this.requiredReliabilityService = 0;

   /** type of transfer desired */
  this.tranferType = 0;

   /** The entity for which control is being requested to transfer */
   this.transferEntityID = new dis.EntityID(); 

   /** number of record sets to transfer */
  this.numberOfRecordSets = 0;

   /** ^^^This is wrong--the RecordSet class needs more work */
    recordSets = new Array();
 
} // end of class
/**
 * Section 5.3.8.1. Detailed information about a radio transmitter. This PDU requires manually         written code to complete, since the modulation parameters are of variable length. UNFINISHED
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.TransmitterPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 25;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 4;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** ID of the entitythat is the source of the communication */
   this.entityId = new dis.EntityID(); 

   /** particular radio within an entity */
  this.radioId = 0;

   /** linear accelleration of entity */
   this.radioEntityType = new dis.RadioEntityType(); 

   /** transmit state */
  this.transmitState = 0;

   /** input source */
  this.inputSource = 0;

   /** padding */
  this.padding1 = 0;

   /** Location of antenna */
   this.antennaLocation = new dis.Vector3Double(); 

   /** relative location of antenna */
   this.relativeAntennaLocation = new dis.Vector3Float(); 

   /** antenna pattern type */
  this.antennaPatternType = 0;

   /** atenna pattern length */
  this.antennaPatternCount = 0;

   /** frequency */
  this.frequency = 0;

   /** transmit frequency Bandwidth */
  this.transmitFrequencyBandwidth = 0;

   /** transmission power */
  this.power = 0;

   /** modulation */
   this.modulationType = new dis.ModulationType(); 

   /** crypto system enumeration */
  this.cryptoSystem = 0;

   /** crypto system key identifer */
  this.cryptoKeyId = 0;

   /** how many modulation parameters we have */
  this.modulationParameterCount = 0;

   /** padding2 */
  this.padding2 = 0;

   /** padding3 */
  this.padding3 = 0;

   /** variable length list of modulation parameters */
    modulationParametersList = new Array();
 
   /** variable length list of antenna pattern records */
    antennaPatternList = new Array();
 
} // end of class
/**
 * 16 bit piece of data
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.TwoByteChunk = function()
{
   /** two bytes of arbitrary data */
} // end of class
/**
 * Section 5.3.7.3. Information about underwater acoustic emmissions. This requires manual cleanup.  The beam data records should ALL be a the finish, rather than attached to each emitter system. UNFINISHED
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.UaPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 29;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 6;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** ID of the entity that is the source of the emission */
   this.emittingEntityID = new dis.EntityID(); 

   /** ID of event */
   this.eventID = new dis.EventID(); 

   /** This field shall be used to indicate whether the data in the UA PDU represent a state update or data that have changed since issuance of the last UA PDU */
  this.stateChangeIndicator = 0;

   /** padding */
  this.pad = 0;

   /** This field indicates which database record (or file) shall be used in the definition of passive signature (unintentional) emissions of the entity. The indicated database record (or  file) shall define all noise generated as a function of propulsion plant configurations and associated  auxiliaries. */
  this.passiveParameterIndex = 0;

   /** This field shall specify the entity propulsion plant configuration. This field is used to determine the passive signature characteristics of an entity. */
  this.propulsionPlantConfiguration = 0;

   /**  This field shall represent the number of shafts on a platform */
  this.numberOfShafts = 0;

   /** This field shall indicate the number of APAs described in the current UA PDU */
  this.numberOfAPAs = 0;

   /** This field shall specify the number of UA emitter systems being described in the current UA PDU */
  this.numberOfUAEmitterSystems = 0;

   /** shaft RPM values */
    shaftRPMs = new Array();
 
   /** apaData */
    apaData = new Array();
 
    emitterSystems = new Array();
 
} // end of class
/**
 * Section 5.2.32. Variable Datum Record
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.VariableDatum = function()
{
   /** ID of the variable datum */
  this.variableDatumID = 0;

   /** length of the variable datums */
  this.variableDatumLength = 0;

   /** variable length list of 64-bit datums */
    variableDatums = new Array();
 
} // end of class
/**
 * Section 5.3.34. Three double precision floating point values, x, y, and z
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.Vector3Double = function()
{
   /** X value */
  this.x = 0;

   /** Y value */
  this.y = 0;

   /** Z value */
  this.z = 0;

} // end of class
/**
 * Section 5.2.33. Three floating point values, x, y, and z
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.Vector3Float = function()
{
   /** X value */
  this.x = 0;

   /** y Value */
  this.y = 0;

   /** Z value */
  this.z = 0;

} // end of class
/**
 * Data about a vectoring nozzle system
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.VectoringNozzleSystemData = function()
{
   /** horizontal deflection angle */
  this.horizontalDeflectionAngle = 0;

   /** vertical deflection angle */
  this.verticalDeflectionAngle = 0;

} // end of class
/**
 * Section 5.3.4. abstract superclass for fire and detonation pdus that have shared information. COMPLETE
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.WarfareFamilyPdu = function()
{
   /** The version of the protocol. 5=DIS-1995, 6=DIS-1998. */
  this.protocolVersion = 6;

   /** Exercise ID */
  this.exerciseID = 0;

   /** Type of pdu, unique for each PDU class */
  this.pduType = 0;

   /** value that refers to the protocol family, eg SimulationManagement, et */
  this.protocolFamily = 2;

   /** Timestamp value */
  this.timestamp = 0;

   /** Length, in bytes, of the PDU. Changed name from length to avoid use of Hibernate QL reserved word */
  this.pduLength = 0;

   /** zero-filled array of padding */
  this.padding = 0;

   /** ID of the entity that shot */
   this.firingEntityID = new dis.EntityID(); 

   /** ID of the entity that is being shot at */
   this.targetEntityID = new dis.EntityID(); 

} // end of class
