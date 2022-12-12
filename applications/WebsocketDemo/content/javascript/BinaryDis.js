
/**
 * Prototype attempt at decoding binary DIS data. Uses DataView,
 * an object present in most modern browsers. DataView is a binary
 * representation, and what's more expects the data to be in network
 * byte order, the same as DIS uses.
 * 
 * Currently this only partially decodes an ESPDU.
 * 
 * DMcG
 */

/**
 * Convert binary data to an entity state pdu in javascript.
 * 
 * @param {type} binaryData
 * @returns {undefined}
 */
BinaryDis = function(binaryData)
{
  this.dataView = new DataView(binaryData, 0);

  // I won't swear to any of these offsets being correct. Pretty much
  // untested. Use at your own risk. If happy fun ball begins to smoke,
  // seek shelter.
  
  // header data  
  this.espdu = new dis.EntityStatePdu();
  this.espdu.protocolVersion = this.dataView.getUint8(0);
  this.espdu.exerciseId = this.dataView.getUint8(1);
  this.espdu.pduType = this.dataView.getUint8(2);
  this.espdu.protocolFamily = this.dataView.getUint8(3);
  this.espdu.timestamp = this.dataView.getUint32(4);
  this.espdu.pduLength = this.dataView.getUint16(8);
  this.pduStatus;
  this.padding;
 
  // entity ID 
  this.espdu.entityID.site = this.dataView.getUint16(12);
  this.espdu.entityID.application = this.dataView.getUint16(14);
  this.espdu.entityID.entity = this.dataView.getUint16(16);
  
  this.espdu.forceId = this.dataView.getUint8(18);
  this.espdu.numberOfArticulationParameters = this.dataView.getUint8(19);
 
  // Entity type 
  this.espdu.entityType.entityKind = this.dataView.getUint8(20);
  this.espdu.entityType.domain = this.dataView.getUint8(21);
  this.espdu.entityType.country = this.dataView.getUint16(22);
  this.espdu.entityType.category = this.dataView.getUint8(24);
  this.espdu.entityType.subcategory = this.dataView.getUint8(25);
  this.espdu.entityType.specific = this.dataView.getUint8(26);
  this.espdu.entityType.extra = this.dataView.getUint8(27);
  
  // Alt entity type 
  this.espdu.alternativeEntityType.entityKind = this.dataView.getUint8(28);
  this.espdu.alternativeEntityType.domain = this.dataView.getUint8(29);
  this.espdu.alternativeEntityType.country = this.dataView.getUint16(30);
  this.espdu.alternativeEntityType.category = this.dataView.getUint8(32);
  this.espdu.alternativeEntityType.subcategory = this.dataView.getUint8(33);
  this.espdu.alternativeEntityType.specific = this.dataView.getUint8(34);
  this.espdu.alternativeEntityType.extra = this.dataView.getUint8(35);

  // Velocity 
  this.espdu.entityLinearVelocity.x = this.dataView.getFloat32(36);
  this.espdu.entityLinearVelocity.x = this.dataView.getFloat32(40);
  this.espdu.entityLinearVelocity.x = this.dataView.getFloat32(44);
 
  // Location 
  this.espdu.entityLocation.x = this.dataView.getFloat64(48);
  this.espdu.entityLocation.x = this.dataView.getFloat64(56);
  this.espdu.entityLocation.x = this.dataView.getFloat64(64);
 
  // Orientation 
  this.espdu.entityOrientation.psi = this.dataView.getFloat32(72);
  this.espdu.entityOrientation.theta = this.dataView.getFloat32(76);
  this.espdu.entityOrientation.phi = this.dataView.getFloat32(80);
 
  // appearance 
  this.espdu.entityAppearance = this.dataView.getUint32(84);
  
  return this.espdu;
  
  
};

