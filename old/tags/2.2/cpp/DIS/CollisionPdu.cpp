#include <DIS/CollisionPdu.h> 

using namespace DIS;


CollisionPdu::CollisionPdu() : EntityInformationFamilyPdu(),
   _issuingEntityID(), 
   _collidingEntityID(), 
   _eventID(), 
   _collisionType(0), 
   _pad(0), 
   _velocity(), 
   _mass(0.0), 
   _location()
{
    setPduType( 4 );
    setProtocolFamily( 1 );
}

CollisionPdu::~CollisionPdu()
{
}

EntityID& CollisionPdu::getIssuingEntityID() 
{
    return _issuingEntityID;
}

const EntityID& CollisionPdu::getIssuingEntityID() const
{
    return _issuingEntityID;
}

void CollisionPdu::setIssuingEntityID(const EntityID &pX)
{
    _issuingEntityID = pX;
}

EntityID& CollisionPdu::getCollidingEntityID() 
{
    return _collidingEntityID;
}

const EntityID& CollisionPdu::getCollidingEntityID() const
{
    return _collidingEntityID;
}

void CollisionPdu::setCollidingEntityID(const EntityID &pX)
{
    _collidingEntityID = pX;
}

EventID& CollisionPdu::getEventID() 
{
    return _eventID;
}

const EventID& CollisionPdu::getEventID() const
{
    return _eventID;
}

void CollisionPdu::setEventID(const EventID &pX)
{
    _eventID = pX;
}

unsigned char CollisionPdu::getCollisionType() const
{
    return _collisionType;
}

void CollisionPdu::setCollisionType(unsigned char pX)
{
    _collisionType = pX;
}

char CollisionPdu::getPad() const
{
    return _pad;
}

void CollisionPdu::setPad(char pX)
{
    _pad = pX;
}

Vector3Float& CollisionPdu::getVelocity() 
{
    return _velocity;
}

const Vector3Float& CollisionPdu::getVelocity() const
{
    return _velocity;
}

void CollisionPdu::setVelocity(const Vector3Float &pX)
{
    _velocity = pX;
}

float CollisionPdu::getMass() const
{
    return _mass;
}

void CollisionPdu::setMass(float pX)
{
    _mass = pX;
}

Vector3Float& CollisionPdu::getLocation() 
{
    return _location;
}

const Vector3Float& CollisionPdu::getLocation() const
{
    return _location;
}

void CollisionPdu::setLocation(const Vector3Float &pX)
{
    _location = pX;
}

void CollisionPdu::marshal(DataStream& dataStream) const
{
    EntityInformationFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _issuingEntityID.marshal(dataStream);
    _collidingEntityID.marshal(dataStream);
    _eventID.marshal(dataStream);
    dataStream << _collisionType;
    dataStream << _pad;
    _velocity.marshal(dataStream);
    dataStream << _mass;
    _location.marshal(dataStream);
}

void CollisionPdu::unmarshal(DataStream& dataStream)
{
    EntityInformationFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _issuingEntityID.unmarshal(dataStream);
    _collidingEntityID.unmarshal(dataStream);
    _eventID.unmarshal(dataStream);
    dataStream >> _collisionType;
    dataStream >> _pad;
    _velocity.unmarshal(dataStream);
    dataStream >> _mass;
    _location.unmarshal(dataStream);
}


bool CollisionPdu::operator ==(const CollisionPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = EntityInformationFamilyPdu::operator==(rhs);

     if( ! (_issuingEntityID == rhs._issuingEntityID) ) ivarsEqual = false;
     if( ! (_collidingEntityID == rhs._collidingEntityID) ) ivarsEqual = false;
     if( ! (_eventID == rhs._eventID) ) ivarsEqual = false;
     if( ! (_collisionType == rhs._collisionType) ) ivarsEqual = false;
     if( ! (_pad == rhs._pad) ) ivarsEqual = false;
     if( ! (_velocity == rhs._velocity) ) ivarsEqual = false;
     if( ! (_mass == rhs._mass) ) ivarsEqual = false;
     if( ! (_location == rhs._location) ) ivarsEqual = false;

    return ivarsEqual;
 }

int CollisionPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = EntityInformationFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _issuingEntityID.getMarshalledSize();  // _issuingEntityID
   marshalSize = marshalSize + _collidingEntityID.getMarshalledSize();  // _collidingEntityID
   marshalSize = marshalSize + _eventID.getMarshalledSize();  // _eventID
   marshalSize = marshalSize + 1;  // _collisionType
   marshalSize = marshalSize + 1;  // _pad
   marshalSize = marshalSize + _velocity.getMarshalledSize();  // _velocity
   marshalSize = marshalSize + 4;  // _mass
   marshalSize = marshalSize + _location.getMarshalledSize();  // _location
    return marshalSize;
}

