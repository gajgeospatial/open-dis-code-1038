#include <DIS/ApaData.h> 

using namespace DIS;


ApaData::ApaData():
   _parameterIndex(0), 
   _parameterValue(0)
{
}

ApaData::~ApaData()
{
}

unsigned short ApaData::getParameterIndex() const
{
    return _parameterIndex;
}

void ApaData::setParameterIndex(unsigned short pX)
{
    _parameterIndex = pX;
}

short ApaData::getParameterValue() const
{
    return _parameterValue;
}

void ApaData::setParameterValue(short pX)
{
    _parameterValue = pX;
}

void ApaData::marshal(DataStream& dataStream) const
{
    dataStream << _parameterIndex;
    dataStream << _parameterValue;
}

void ApaData::unmarshal(DataStream& dataStream)
{
    dataStream >> _parameterIndex;
    dataStream >> _parameterValue;
}


bool ApaData::operator ==(const ApaData& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_parameterIndex == rhs._parameterIndex) ) ivarsEqual = false;
     if( ! (_parameterValue == rhs._parameterValue) ) ivarsEqual = false;

    return ivarsEqual;
 }

int ApaData::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 2;  // _parameterIndex
   marshalSize = marshalSize + 2;  // _parameterValue
    return marshalSize;
}

