#include <DIS/AcousticEmitter.h> 

using namespace DIS;


AcousticEmitter::AcousticEmitter():
   _acousticName(0), 
   _function(0), 
   _acousticIdNumber(0)
{
}

AcousticEmitter::~AcousticEmitter()
{
}

unsigned short AcousticEmitter::getAcousticName() const
{
    return _acousticName;
}

void AcousticEmitter::setAcousticName(unsigned short pX)
{
    _acousticName = pX;
}

unsigned char AcousticEmitter::getFunction() const
{
    return _function;
}

void AcousticEmitter::setFunction(unsigned char pX)
{
    _function = pX;
}

unsigned char AcousticEmitter::getAcousticIdNumber() const
{
    return _acousticIdNumber;
}

void AcousticEmitter::setAcousticIdNumber(unsigned char pX)
{
    _acousticIdNumber = pX;
}

void AcousticEmitter::marshal(DataStream& dataStream) const
{
    dataStream << _acousticName;
    dataStream << _function;
    dataStream << _acousticIdNumber;
}

void AcousticEmitter::unmarshal(DataStream& dataStream)
{
    dataStream >> _acousticName;
    dataStream >> _function;
    dataStream >> _acousticIdNumber;
}


bool AcousticEmitter::operator ==(const AcousticEmitter& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_acousticName == rhs._acousticName) ) ivarsEqual = false;
     if( ! (_function == rhs._function) ) ivarsEqual = false;
     if( ! (_acousticIdNumber == rhs._acousticIdNumber) ) ivarsEqual = false;

    return ivarsEqual;
 }

int AcousticEmitter::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 2;  // _acousticName
   marshalSize = marshalSize + 1;  // _function
   marshalSize = marshalSize + 1;  // _acousticIdNumber
    return marshalSize;
}

