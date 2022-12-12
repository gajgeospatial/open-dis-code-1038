#include <DIS/NamedLocation.h> 

using namespace DIS;


NamedLocation::NamedLocation():
   _stationName(0), 
   _stationNumber(0)
{
}

NamedLocation::~NamedLocation()
{
}

unsigned short NamedLocation::getStationName() const
{
    return _stationName;
}

void NamedLocation::setStationName(unsigned short pX)
{
    _stationName = pX;
}

unsigned short NamedLocation::getStationNumber() const
{
    return _stationNumber;
}

void NamedLocation::setStationNumber(unsigned short pX)
{
    _stationNumber = pX;
}

void NamedLocation::marshal(DataStream& dataStream) const
{
    dataStream << _stationName;
    dataStream << _stationNumber;
}

void NamedLocation::unmarshal(DataStream& dataStream)
{
    dataStream >> _stationName;
    dataStream >> _stationNumber;
}


bool NamedLocation::operator ==(const NamedLocation& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_stationName == rhs._stationName) ) ivarsEqual = false;
     if( ! (_stationNumber == rhs._stationNumber) ) ivarsEqual = false;

    return ivarsEqual;
 }

int NamedLocation::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 2;  // _stationName
   marshalSize = marshalSize + 2;  // _stationNumber
    return marshalSize;
}

