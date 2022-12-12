#include <DIS/AcousticEmitterSystem.h> 

using namespace DIS;


AcousticEmitterSystem::AcousticEmitterSystem():
   _acousticName(0), 
   _acousticFunction(0), 
   _acousticID(0)
{
}

AcousticEmitterSystem::~AcousticEmitterSystem()
{
}

unsigned short AcousticEmitterSystem::getAcousticName() const
{
    return _acousticName;
}

void AcousticEmitterSystem::setAcousticName(unsigned short pX)
{
    _acousticName = pX;
}

unsigned char AcousticEmitterSystem::getAcousticFunction() const
{
    return _acousticFunction;
}

void AcousticEmitterSystem::setAcousticFunction(unsigned char pX)
{
    _acousticFunction = pX;
}

unsigned char AcousticEmitterSystem::getAcousticID() const
{
    return _acousticID;
}

void AcousticEmitterSystem::setAcousticID(unsigned char pX)
{
    _acousticID = pX;
}

void AcousticEmitterSystem::marshal(DataStream& dataStream) const
{
    dataStream << _acousticName;
    dataStream << _acousticFunction;
    dataStream << _acousticID;
}

void AcousticEmitterSystem::unmarshal(DataStream& dataStream)
{
    dataStream >> _acousticName;
    dataStream >> _acousticFunction;
    dataStream >> _acousticID;
}


bool AcousticEmitterSystem::operator ==(const AcousticEmitterSystem& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_acousticName == rhs._acousticName) ) ivarsEqual = false;
     if( ! (_acousticFunction == rhs._acousticFunction) ) ivarsEqual = false;
     if( ! (_acousticID == rhs._acousticID) ) ivarsEqual = false;

    return ivarsEqual;
 }

int AcousticEmitterSystem::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 2;  // _acousticName
   marshalSize = marshalSize + 1;  // _acousticFunction
   marshalSize = marshalSize + 1;  // _acousticID
    return marshalSize;
}

