#include <DIS/FixedDatum.h> 

using namespace DIS;


FixedDatum::FixedDatum():
   _fixedDatumID(0), 
   _fixedDatumValue(0)
{
}

FixedDatum::~FixedDatum()
{
}

unsigned int FixedDatum::getFixedDatumID() const
{
    return _fixedDatumID;
}

void FixedDatum::setFixedDatumID(unsigned int pX)
{
    _fixedDatumID = pX;
}

unsigned int FixedDatum::getFixedDatumValue() const
{
    return _fixedDatumValue;
}

void FixedDatum::setFixedDatumValue(unsigned int pX)
{
    _fixedDatumValue = pX;
}

void FixedDatum::marshal(DataStream& dataStream) const
{
    dataStream << _fixedDatumID;
    dataStream << _fixedDatumValue;
}

void FixedDatum::unmarshal(DataStream& dataStream)
{
    dataStream >> _fixedDatumID;
    dataStream >> _fixedDatumValue;
}


bool FixedDatum::operator ==(const FixedDatum& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_fixedDatumID == rhs._fixedDatumID) ) ivarsEqual = false;
     if( ! (_fixedDatumValue == rhs._fixedDatumValue) ) ivarsEqual = false;

    return ivarsEqual;
 }

int FixedDatum::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 4;  // _fixedDatumID
   marshalSize = marshalSize + 4;  // _fixedDatumValue
    return marshalSize;
}

