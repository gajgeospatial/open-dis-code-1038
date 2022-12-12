#include <DIS/ArealObjectStatePdu.h> 

using namespace DIS;


ArealObjectStatePdu::ArealObjectStatePdu() : SyntheticEnvironmentFamilyPdu(),
   _objectID(), 
   _referencedObjectID(), 
   _updateNumber(0), 
   _forceID(0), 
   _modifications(0), 
   _objectType(), 
   _objectAppearance(), 
   _numberOfPoints(0), 
   _requesterID(), 
   _receivingID()
{
    setPduType( 45 );
}

ArealObjectStatePdu::~ArealObjectStatePdu()
{
    _objectLocation.clear();
}

EntityID& ArealObjectStatePdu::getObjectID() 
{
    return _objectID;
}

const EntityID& ArealObjectStatePdu::getObjectID() const
{
    return _objectID;
}

void ArealObjectStatePdu::setObjectID(const EntityID &pX)
{
    _objectID = pX;
}

EntityID& ArealObjectStatePdu::getReferencedObjectID() 
{
    return _referencedObjectID;
}

const EntityID& ArealObjectStatePdu::getReferencedObjectID() const
{
    return _referencedObjectID;
}

void ArealObjectStatePdu::setReferencedObjectID(const EntityID &pX)
{
    _referencedObjectID = pX;
}

unsigned short ArealObjectStatePdu::getUpdateNumber() const
{
    return _updateNumber;
}

void ArealObjectStatePdu::setUpdateNumber(unsigned short pX)
{
    _updateNumber = pX;
}

unsigned char ArealObjectStatePdu::getForceID() const
{
    return _forceID;
}

void ArealObjectStatePdu::setForceID(unsigned char pX)
{
    _forceID = pX;
}

unsigned char ArealObjectStatePdu::getModifications() const
{
    return _modifications;
}

void ArealObjectStatePdu::setModifications(unsigned char pX)
{
    _modifications = pX;
}

EntityType& ArealObjectStatePdu::getObjectType() 
{
    return _objectType;
}

const EntityType& ArealObjectStatePdu::getObjectType() const
{
    return _objectType;
}

void ArealObjectStatePdu::setObjectType(const EntityType &pX)
{
    _objectType = pX;
}

SixByteChunk& ArealObjectStatePdu::getObjectAppearance() 
{
    return _objectAppearance;
}

const SixByteChunk& ArealObjectStatePdu::getObjectAppearance() const
{
    return _objectAppearance;
}

void ArealObjectStatePdu::setObjectAppearance(const SixByteChunk &pX)
{
    _objectAppearance = pX;
}

unsigned short ArealObjectStatePdu::getNumberOfPoints() const
{
   return _objectLocation.size();
}

SimulationAddress& ArealObjectStatePdu::getRequesterID() 
{
    return _requesterID;
}

const SimulationAddress& ArealObjectStatePdu::getRequesterID() const
{
    return _requesterID;
}

void ArealObjectStatePdu::setRequesterID(const SimulationAddress &pX)
{
    _requesterID = pX;
}

SimulationAddress& ArealObjectStatePdu::getReceivingID() 
{
    return _receivingID;
}

const SimulationAddress& ArealObjectStatePdu::getReceivingID() const
{
    return _receivingID;
}

void ArealObjectStatePdu::setReceivingID(const SimulationAddress &pX)
{
    _receivingID = pX;
}

std::vector<Vector3Double>& ArealObjectStatePdu::getObjectLocation() 
{
    return _objectLocation;
}

const std::vector<Vector3Double>& ArealObjectStatePdu::getObjectLocation() const
{
    return _objectLocation;
}

void ArealObjectStatePdu::setObjectLocation(const std::vector<Vector3Double>& pX)
{
     _objectLocation = pX;
}

void ArealObjectStatePdu::marshal(DataStream& dataStream) const
{
    SyntheticEnvironmentFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _objectID.marshal(dataStream);
    _referencedObjectID.marshal(dataStream);
    dataStream << _updateNumber;
    dataStream << _forceID;
    dataStream << _modifications;
    _objectType.marshal(dataStream);
    _objectAppearance.marshal(dataStream);
    dataStream << ( unsigned short )_objectLocation.size();
    _requesterID.marshal(dataStream);
    _receivingID.marshal(dataStream);

     for(size_t idx = 0; idx < _objectLocation.size(); idx++)
     {
        Vector3Double x = _objectLocation[idx];
        x.marshal(dataStream);
     }

}

void ArealObjectStatePdu::unmarshal(DataStream& dataStream)
{
    SyntheticEnvironmentFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _objectID.unmarshal(dataStream);
    _referencedObjectID.unmarshal(dataStream);
    dataStream >> _updateNumber;
    dataStream >> _forceID;
    dataStream >> _modifications;
    _objectType.unmarshal(dataStream);
    _objectAppearance.unmarshal(dataStream);
    dataStream >> _numberOfPoints;
    _requesterID.unmarshal(dataStream);
    _receivingID.unmarshal(dataStream);

     _objectLocation.clear();
     for(size_t idx = 0; idx < _numberOfPoints; idx++)
     {
        Vector3Double x;
        x.unmarshal(dataStream);
        _objectLocation.push_back(x);
     }
}


bool ArealObjectStatePdu::operator ==(const ArealObjectStatePdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = SyntheticEnvironmentFamilyPdu::operator==(rhs);

     if( ! (_objectID == rhs._objectID) ) ivarsEqual = false;
     if( ! (_referencedObjectID == rhs._referencedObjectID) ) ivarsEqual = false;
     if( ! (_updateNumber == rhs._updateNumber) ) ivarsEqual = false;
     if( ! (_forceID == rhs._forceID) ) ivarsEqual = false;
     if( ! (_modifications == rhs._modifications) ) ivarsEqual = false;
     if( ! (_objectType == rhs._objectType) ) ivarsEqual = false;
     if( ! (_objectAppearance == rhs._objectAppearance) ) ivarsEqual = false;
     if( ! (_requesterID == rhs._requesterID) ) ivarsEqual = false;
     if( ! (_receivingID == rhs._receivingID) ) ivarsEqual = false;

     for(size_t idx = 0; idx < _objectLocation.size(); idx++)
     {
        if( ! ( _objectLocation[idx] == rhs._objectLocation[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int ArealObjectStatePdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = SyntheticEnvironmentFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _objectID.getMarshalledSize();  // _objectID
   marshalSize = marshalSize + _referencedObjectID.getMarshalledSize();  // _referencedObjectID
   marshalSize = marshalSize + 2;  // _updateNumber
   marshalSize = marshalSize + 1;  // _forceID
   marshalSize = marshalSize + 1;  // _modifications
   marshalSize = marshalSize + _objectType.getMarshalledSize();  // _objectType
   marshalSize = marshalSize + _objectAppearance.getMarshalledSize();  // _objectAppearance
   marshalSize = marshalSize + 2;  // _numberOfPoints
   marshalSize = marshalSize + _requesterID.getMarshalledSize();  // _requesterID
   marshalSize = marshalSize + _receivingID.getMarshalledSize();  // _receivingID

   for(int idx=0; idx < _objectLocation.size(); idx++)
   {
        Vector3Double listElement = _objectLocation[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

