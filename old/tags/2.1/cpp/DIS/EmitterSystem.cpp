#include <DIS/EmitterSystem.h> 

using namespace DIS;


EmitterSystem::EmitterSystem():
   _emitterName(0), 
   _function(0), 
   _emitterIdNumber(0)
{
}

EmitterSystem::~EmitterSystem()
{
}

unsigned short EmitterSystem::getEmitterName() const
{
    return _emitterName;
}

void EmitterSystem::setEmitterName(unsigned short pX)
{
    _emitterName = pX;
}

unsigned char EmitterSystem::getFunction() const
{
    return _function;
}

void EmitterSystem::setFunction(unsigned char pX)
{
    _function = pX;
}

unsigned char EmitterSystem::getEmitterIdNumber() const
{
    return _emitterIdNumber;
}

void EmitterSystem::setEmitterIdNumber(unsigned char pX)
{
    _emitterIdNumber = pX;
}

void EmitterSystem::marshal(DataStream& dataStream) const
{
    dataStream << _emitterName;
    dataStream << _function;
    dataStream << _emitterIdNumber;
}

void EmitterSystem::unmarshal(DataStream& dataStream)
{
    dataStream >> _emitterName;
    dataStream >> _function;
    dataStream >> _emitterIdNumber;
}


bool EmitterSystem::operator ==(const EmitterSystem& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_emitterName == rhs._emitterName) ) ivarsEqual = false;
     if( ! (_function == rhs._function) ) ivarsEqual = false;
     if( ! (_emitterIdNumber == rhs._emitterIdNumber) ) ivarsEqual = false;

    return ivarsEqual;
 }

int EmitterSystem::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 2;  // _emitterName
   marshalSize = marshalSize + 1;  // _function
   marshalSize = marshalSize + 1;  // _emitterIdNumber
    return marshalSize;
}

