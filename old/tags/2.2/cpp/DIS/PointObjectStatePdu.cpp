#include <DIS/PointObjectStatePdu.h> 

using namespace DIS;


PointObjectStatePdu::PointObjectStatePdu() : SyntheticEnvironmentFamilyPdu(),
   _objectID(), 
   _referencedObjectID(), 
   _updateNumber(0), 
   _forceID(0), 
   _modifications(0), 
   _objectType(), 
   _objectLocation(), 
   _objectOrientation(), 
   _objectAppearance(0.0), 
   _requesterID(), 
   _receivingID(), 
   _pad2(0)
{
    setPduType( 43 );
}

PointObjectStatePdu::~PointObjectStatePdu()
{
}

EntityID& PointObjectStatePdu::getObjectID() 
{
    return _objectID;
}

const EntityID& PointObjectStatePdu::getObjectID() const
{
    return _objectID;
}

void PointObjectStatePdu::setObjectID(const EntityID &pX)
{
    _objectID = pX;
}

EntityID& PointObjectStatePdu::getReferencedObjectID() 
{
    return _referencedObjectID;
}

const EntityID& PointObjectStatePdu::getReferencedObjectID() const
{
    return _referencedObjectID;
}

void PointObjectStatePdu::setReferencedObjectID(const EntityID &pX)
{
    _referencedObjectID = pX;
}

unsigned short PointObjectStatePdu::getUpdateNumber() const
{
    return _updateNumber;
}

void PointObjectStatePdu::setUpdateNumber(unsigned short pX)
{
    _updateNumber = pX;
}

unsigned char PointObjectStatePdu::getForceID() const
{
    return _forceID;
}

void PointObjectStatePdu::setForceID(unsigned char pX)
{
    _forceID = pX;
}

unsigned char PointObjectStatePdu::getModifications() const
{
    return _modifications;
}

void PointObjectStatePdu::setModifications(unsigned char pX)
{
    _modifications = pX;
}

ObjectType& PointObjectStatePdu::getObjectType() 
{
    return _objectType;
}

const ObjectType& PointObjectStatePdu::getObjectType() const
{
    return _objectType;
}

void PointObjectStatePdu::setObjectType(const ObjectType &pX)
{
    _objectType = pX;
}

Vector3Double& PointObjectStatePdu::getObjectLocation() 
{
    return _objectLocation;
}

const Vector3Double& PointObjectStatePdu::getObjectLocation() const
{
    return _objectLocation;
}

void PointObjectStatePdu::setObjectLocation(const Vector3Double &pX)
{
    _objectLocation = pX;
}

Orientation& PointObjectStatePdu::getObjectOrientation() 
{
    return _objectOrientation;
}

const Orientation& PointObjectStatePdu::getObjectOrientation() const
{
    return _objectOrientation;
}

void PointObjectStatePdu::setObjectOrientation(const Orientation &pX)
{
    _objectOrientation = pX;
}

double PointObjectStatePdu::getObjectAppearance() const
{
    return _objectAppearance;
}

void PointObjectStatePdu::setObjectAppearance(double pX)
{
    _objectAppearance = pX;
}

SimulationAddress& PointObjectStatePdu::getRequesterID() 
{
    return _requesterID;
}

const SimulationAddress& PointObjectStatePdu::getRequesterID() const
{
    return _requesterID;
}

void PointObjectStatePdu::setRequesterID(const SimulationAddress &pX)
{
    _requesterID = pX;
}

SimulationAddress& PointObjectStatePdu::getReceivingID() 
{
    return _receivingID;
}

const SimulationAddress& PointObjectStatePdu::getReceivingID() const
{
    return _receivingID;
}

void PointObjectStatePdu::setReceivingID(const SimulationAddress &pX)
{
    _receivingID = pX;
}

unsigned int PointObjectStatePdu::getPad2() const
{
    return _pad2;
}

void PointObjectStatePdu::setPad2(unsigned int pX)
{
    _pad2 = pX;
}

void PointObjectStatePdu::marshal(DataStream& dataStream) const
{
    SyntheticEnvironmentFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _objectID.marshal(dataStream);
    _referencedObjectID.marshal(dataStream);
    dataStream << _updateNumber;
    dataStream << _forceID;
    dataStream << _modifications;
    _objectType.marshal(dataStream);
    _objectLocation.marshal(dataStream);
    _objectOrientation.marshal(dataStream);
    dataStream << _objectAppearance;
    _requesterID.marshal(dataStream);
    _receivingID.marshal(dataStream);
    dataStream << _pad2;
}

void PointObjectStatePdu::unmarshal(DataStream& dataStream)
{
    SyntheticEnvironmentFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _objectID.unmarshal(dataStream);
    _referencedObjectID.unmarshal(dataStream);
    dataStream >> _updateNumber;
    dataStream >> _forceID;
    dataStream >> _modifications;
    _objectType.unmarshal(dataStream);
    _objectLocation.unmarshal(dataStream);
    _objectOrientation.unmarshal(dataStream);
    dataStream >> _objectAppearance;
    _requesterID.unmarshal(dataStream);
    _receivingID.unmarshal(dataStream);
    dataStream >> _pad2;
}


bool PointObjectStatePdu::operator ==(const PointObjectStatePdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = SyntheticEnvironmentFamilyPdu::operator==(rhs);

     if( ! (_objectID == rhs._objectID) ) ivarsEqual = false;
     if( ! (_referencedObjectID == rhs._referencedObjectID) ) ivarsEqual = false;
     if( ! (_updateNumber == rhs._updateNumber) ) ivarsEqual = false;
     if( ! (_forceID == rhs._forceID) ) ivarsEqual = false;
     if( ! (_modifications == rhs._modifications) ) ivarsEqual = false;
     if( ! (_objectType == rhs._objectType) ) ivarsEqual = false;
     if( ! (_objectLocation == rhs._objectLocation) ) ivarsEqual = false;
     if( ! (_objectOrientation == rhs._objectOrientation) ) ivarsEqual = false;
     if( ! (_objectAppearance == rhs._objectAppearance) ) ivarsEqual = false;
     if( ! (_requesterID == rhs._requesterID) ) ivarsEqual = false;
     if( ! (_receivingID == rhs._receivingID) ) ivarsEqual = false;
     if( ! (_pad2 == rhs._pad2) ) ivarsEqual = false;

    return ivarsEqual;
 }

int PointObjectStatePdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = SyntheticEnvironmentFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _objectID.getMarshalledSize();  // _objectID
   marshalSize = marshalSize + _referencedObjectID.getMarshalledSize();  // _referencedObjectID
   marshalSize = marshalSize + 2;  // _updateNumber
   marshalSize = marshalSize + 1;  // _forceID
   marshalSize = marshalSize + 1;  // _modifications
   marshalSize = marshalSize + _objectType.getMarshalledSize();  // _objectType
   marshalSize = marshalSize + _objectLocation.getMarshalledSize();  // _objectLocation
   marshalSize = marshalSize + _objectOrientation.getMarshalledSize();  // _objectOrientation
   marshalSize = marshalSize + 8;  // _objectAppearance
   marshalSize = marshalSize + _requesterID.getMarshalledSize();  // _requesterID
   marshalSize = marshalSize + _receivingID.getMarshalledSize();  // _receivingID
   marshalSize = marshalSize + 4;  // _pad2
    return marshalSize;
}

