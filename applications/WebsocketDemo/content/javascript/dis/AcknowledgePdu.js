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
