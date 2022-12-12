#include <DIS/IntercomCommunicationsParameters.h> 

using namespace DIS;


IntercomCommunicationsParameters::IntercomCommunicationsParameters():
   _recordType(0), 
   _recordLength(0), 
   _recordSpecificField(0)
{
}

IntercomCommunicationsParameters::~IntercomCommunicationsParameters()
{
}

unsigned short IntercomCommunicationsParameters::getRecordType() const
{
    return _recordType;
}

void IntercomCommunicationsParameters::setRecordType(unsigned short pX)
{
    _recordType = pX;
}

unsigned short IntercomCommunicationsParameters::getRecordLength() const
{
    return _recordLength;
}

void IntercomCommunicationsParameters::setRecordLength(unsigned short pX)
{
    _recordLength = pX;
}

unsigned int IntercomCommunicationsParameters::getRecordSpecificField() const
{
    return _recordSpecificField;
}

void IntercomCommunicationsParameters::setRecordSpecificField(unsigned int pX)
{
    _recordSpecificField = pX;
}

void IntercomCommunicationsParameters::marshal(DataStream& dataStream) const
{
    dataStream << _recordType;
    dataStream << _recordLength;
    dataStream << _recordSpecificField;
}

void IntercomCommunicationsParameters::unmarshal(DataStream& dataStream)
{
    dataStream >> _recordType;
    dataStream >> _recordLength;
    dataStream >> _recordSpecificField;
}


bool IntercomCommunicationsParameters::operator ==(const IntercomCommunicationsParameters& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_recordType == rhs._recordType) ) ivarsEqual = false;
     if( ! (_recordLength == rhs._recordLength) ) ivarsEqual = false;
     if( ! (_recordSpecificField == rhs._recordSpecificField) ) ivarsEqual = false;

    return ivarsEqual;
 }

int IntercomCommunicationsParameters::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 2;  // _recordType
   marshalSize = marshalSize + 2;  // _recordLength
   marshalSize = marshalSize + 4;  // _recordSpecificField
    return marshalSize;
}

