#include <DIS/IsPartOfPdu.h> 

using namespace DIS;


IsPartOfPdu::IsPartOfPdu() : EntityManagementFamilyPdu(),
   _orginatingEntityID(), 
   _receivingEntityID(), 
   _relationship(), 
   _partLocation(), 
   _namedLocationID(), 
   _partEntityType()
{
    setPduType( 36 );
}

IsPartOfPdu::~IsPartOfPdu()
{
}

EntityID& IsPartOfPdu::getOrginatingEntityID() 
{
    return _orginatingEntityID;
}

const EntityID& IsPartOfPdu::getOrginatingEntityID() const
{
    return _orginatingEntityID;
}

void IsPartOfPdu::setOrginatingEntityID(const EntityID &pX)
{
    _orginatingEntityID = pX;
}

EntityID& IsPartOfPdu::getReceivingEntityID() 
{
    return _receivingEntityID;
}

const EntityID& IsPartOfPdu::getReceivingEntityID() const
{
    return _receivingEntityID;
}

void IsPartOfPdu::setReceivingEntityID(const EntityID &pX)
{
    _receivingEntityID = pX;
}

Relationship& IsPartOfPdu::getRelationship() 
{
    return _relationship;
}

const Relationship& IsPartOfPdu::getRelationship() const
{
    return _relationship;
}

void IsPartOfPdu::setRelationship(const Relationship &pX)
{
    _relationship = pX;
}

Vector3Float& IsPartOfPdu::getPartLocation() 
{
    return _partLocation;
}

const Vector3Float& IsPartOfPdu::getPartLocation() const
{
    return _partLocation;
}

void IsPartOfPdu::setPartLocation(const Vector3Float &pX)
{
    _partLocation = pX;
}

NamedLocation& IsPartOfPdu::getNamedLocationID() 
{
    return _namedLocationID;
}

const NamedLocation& IsPartOfPdu::getNamedLocationID() const
{
    return _namedLocationID;
}

void IsPartOfPdu::setNamedLocationID(const NamedLocation &pX)
{
    _namedLocationID = pX;
}

EntityType& IsPartOfPdu::getPartEntityType() 
{
    return _partEntityType;
}

const EntityType& IsPartOfPdu::getPartEntityType() const
{
    return _partEntityType;
}

void IsPartOfPdu::setPartEntityType(const EntityType &pX)
{
    _partEntityType = pX;
}

void IsPartOfPdu::marshal(DataStream& dataStream) const
{
    EntityManagementFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _orginatingEntityID.marshal(dataStream);
    _receivingEntityID.marshal(dataStream);
    _relationship.marshal(dataStream);
    _partLocation.marshal(dataStream);
    _namedLocationID.marshal(dataStream);
    _partEntityType.marshal(dataStream);
}

void IsPartOfPdu::unmarshal(DataStream& dataStream)
{
    EntityManagementFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _orginatingEntityID.unmarshal(dataStream);
    _receivingEntityID.unmarshal(dataStream);
    _relationship.unmarshal(dataStream);
    _partLocation.unmarshal(dataStream);
    _namedLocationID.unmarshal(dataStream);
    _partEntityType.unmarshal(dataStream);
}


bool IsPartOfPdu::operator ==(const IsPartOfPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = EntityManagementFamilyPdu::operator==(rhs);

     if( ! (_orginatingEntityID == rhs._orginatingEntityID) ) ivarsEqual = false;
     if( ! (_receivingEntityID == rhs._receivingEntityID) ) ivarsEqual = false;
     if( ! (_relationship == rhs._relationship) ) ivarsEqual = false;
     if( ! (_partLocation == rhs._partLocation) ) ivarsEqual = false;
     if( ! (_namedLocationID == rhs._namedLocationID) ) ivarsEqual = false;
     if( ! (_partEntityType == rhs._partEntityType) ) ivarsEqual = false;

    return ivarsEqual;
 }

int IsPartOfPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = EntityManagementFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _orginatingEntityID.getMarshalledSize();  // _orginatingEntityID
   marshalSize = marshalSize + _receivingEntityID.getMarshalledSize();  // _receivingEntityID
   marshalSize = marshalSize + _relationship.getMarshalledSize();  // _relationship
   marshalSize = marshalSize + _partLocation.getMarshalledSize();  // _partLocation
   marshalSize = marshalSize + _namedLocationID.getMarshalledSize();  // _namedLocationID
   marshalSize = marshalSize + _partEntityType.getMarshalledSize();  // _partEntityType
    return marshalSize;
}

