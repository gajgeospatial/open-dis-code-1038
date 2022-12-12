#include <DIS/DesignatorPdu.h> 

using namespace DIS;


DesignatorPdu::DesignatorPdu() : DistributedEmissionsFamilyPdu(),
   _designatingEntityID(), 
   _codeName(0), 
   _designatedEntityID(), 
   _designatorCode(0), 
   _designatorPower(0.0), 
   _designatorWavelength(0.0), 
   _designatorSpotWrtDesignated(), 
   _designatorSpotLocation(), 
   _deadReckoningAlgorithm(0), 
   _padding1(0), 
   _padding2(0), 
   _entityLinearAcceleration()
{
    setPduType( 24 );
}

DesignatorPdu::~DesignatorPdu()
{
}

EntityID& DesignatorPdu::getDesignatingEntityID() 
{
    return _designatingEntityID;
}

const EntityID& DesignatorPdu::getDesignatingEntityID() const
{
    return _designatingEntityID;
}

void DesignatorPdu::setDesignatingEntityID(const EntityID &pX)
{
    _designatingEntityID = pX;
}

unsigned short DesignatorPdu::getCodeName() const
{
    return _codeName;
}

void DesignatorPdu::setCodeName(unsigned short pX)
{
    _codeName = pX;
}

EntityID& DesignatorPdu::getDesignatedEntityID() 
{
    return _designatedEntityID;
}

const EntityID& DesignatorPdu::getDesignatedEntityID() const
{
    return _designatedEntityID;
}

void DesignatorPdu::setDesignatedEntityID(const EntityID &pX)
{
    _designatedEntityID = pX;
}

unsigned short DesignatorPdu::getDesignatorCode() const
{
    return _designatorCode;
}

void DesignatorPdu::setDesignatorCode(unsigned short pX)
{
    _designatorCode = pX;
}

float DesignatorPdu::getDesignatorPower() const
{
    return _designatorPower;
}

void DesignatorPdu::setDesignatorPower(float pX)
{
    _designatorPower = pX;
}

float DesignatorPdu::getDesignatorWavelength() const
{
    return _designatorWavelength;
}

void DesignatorPdu::setDesignatorWavelength(float pX)
{
    _designatorWavelength = pX;
}

Vector3Float& DesignatorPdu::getDesignatorSpotWrtDesignated() 
{
    return _designatorSpotWrtDesignated;
}

const Vector3Float& DesignatorPdu::getDesignatorSpotWrtDesignated() const
{
    return _designatorSpotWrtDesignated;
}

void DesignatorPdu::setDesignatorSpotWrtDesignated(const Vector3Float &pX)
{
    _designatorSpotWrtDesignated = pX;
}

Vector3Double& DesignatorPdu::getDesignatorSpotLocation() 
{
    return _designatorSpotLocation;
}

const Vector3Double& DesignatorPdu::getDesignatorSpotLocation() const
{
    return _designatorSpotLocation;
}

void DesignatorPdu::setDesignatorSpotLocation(const Vector3Double &pX)
{
    _designatorSpotLocation = pX;
}

char DesignatorPdu::getDeadReckoningAlgorithm() const
{
    return _deadReckoningAlgorithm;
}

void DesignatorPdu::setDeadReckoningAlgorithm(char pX)
{
    _deadReckoningAlgorithm = pX;
}

unsigned short DesignatorPdu::getPadding1() const
{
    return _padding1;
}

void DesignatorPdu::setPadding1(unsigned short pX)
{
    _padding1 = pX;
}

char DesignatorPdu::getPadding2() const
{
    return _padding2;
}

void DesignatorPdu::setPadding2(char pX)
{
    _padding2 = pX;
}

Vector3Float& DesignatorPdu::getEntityLinearAcceleration() 
{
    return _entityLinearAcceleration;
}

const Vector3Float& DesignatorPdu::getEntityLinearAcceleration() const
{
    return _entityLinearAcceleration;
}

void DesignatorPdu::setEntityLinearAcceleration(const Vector3Float &pX)
{
    _entityLinearAcceleration = pX;
}

void DesignatorPdu::marshal(DataStream& dataStream) const
{
    DistributedEmissionsFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _designatingEntityID.marshal(dataStream);
    dataStream << _codeName;
    _designatedEntityID.marshal(dataStream);
    dataStream << _designatorCode;
    dataStream << _designatorPower;
    dataStream << _designatorWavelength;
    _designatorSpotWrtDesignated.marshal(dataStream);
    _designatorSpotLocation.marshal(dataStream);
    dataStream << _deadReckoningAlgorithm;
    dataStream << _padding1;
    dataStream << _padding2;
    _entityLinearAcceleration.marshal(dataStream);
}

void DesignatorPdu::unmarshal(DataStream& dataStream)
{
    DistributedEmissionsFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _designatingEntityID.unmarshal(dataStream);
    dataStream >> _codeName;
    _designatedEntityID.unmarshal(dataStream);
    dataStream >> _designatorCode;
    dataStream >> _designatorPower;
    dataStream >> _designatorWavelength;
    _designatorSpotWrtDesignated.unmarshal(dataStream);
    _designatorSpotLocation.unmarshal(dataStream);
    dataStream >> _deadReckoningAlgorithm;
    dataStream >> _padding1;
    dataStream >> _padding2;
    _entityLinearAcceleration.unmarshal(dataStream);
}


bool DesignatorPdu::operator ==(const DesignatorPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = DistributedEmissionsFamilyPdu::operator==(rhs);

     if( ! (_designatingEntityID == rhs._designatingEntityID) ) ivarsEqual = false;
     if( ! (_codeName == rhs._codeName) ) ivarsEqual = false;
     if( ! (_designatedEntityID == rhs._designatedEntityID) ) ivarsEqual = false;
     if( ! (_designatorCode == rhs._designatorCode) ) ivarsEqual = false;
     if( ! (_designatorPower == rhs._designatorPower) ) ivarsEqual = false;
     if( ! (_designatorWavelength == rhs._designatorWavelength) ) ivarsEqual = false;
     if( ! (_designatorSpotWrtDesignated == rhs._designatorSpotWrtDesignated) ) ivarsEqual = false;
     if( ! (_designatorSpotLocation == rhs._designatorSpotLocation) ) ivarsEqual = false;
     if( ! (_deadReckoningAlgorithm == rhs._deadReckoningAlgorithm) ) ivarsEqual = false;
     if( ! (_padding1 == rhs._padding1) ) ivarsEqual = false;
     if( ! (_padding2 == rhs._padding2) ) ivarsEqual = false;
     if( ! (_entityLinearAcceleration == rhs._entityLinearAcceleration) ) ivarsEqual = false;

    return ivarsEqual;
 }

int DesignatorPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = DistributedEmissionsFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _designatingEntityID.getMarshalledSize();  // _designatingEntityID
   marshalSize = marshalSize + 2;  // _codeName
   marshalSize = marshalSize + _designatedEntityID.getMarshalledSize();  // _designatedEntityID
   marshalSize = marshalSize + 2;  // _designatorCode
   marshalSize = marshalSize + 4;  // _designatorPower
   marshalSize = marshalSize + 4;  // _designatorWavelength
   marshalSize = marshalSize + _designatorSpotWrtDesignated.getMarshalledSize();  // _designatorSpotWrtDesignated
   marshalSize = marshalSize + _designatorSpotLocation.getMarshalledSize();  // _designatorSpotLocation
   marshalSize = marshalSize + 1;  // _deadReckoningAlgorithm
   marshalSize = marshalSize + 2;  // _padding1
   marshalSize = marshalSize + 1;  // _padding2
   marshalSize = marshalSize + _entityLinearAcceleration.getMarshalledSize();  // _entityLinearAcceleration
    return marshalSize;
}

