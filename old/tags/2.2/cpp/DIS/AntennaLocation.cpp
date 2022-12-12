#include <DIS/AntennaLocation.h> 

using namespace DIS;


AntennaLocation::AntennaLocation():
   _antennaLocation(), 
   _relativeAntennaLocation()
{
}

AntennaLocation::~AntennaLocation()
{
}

Vector3Double& AntennaLocation::getAntennaLocation() 
{
    return _antennaLocation;
}

const Vector3Double& AntennaLocation::getAntennaLocation() const
{
    return _antennaLocation;
}

void AntennaLocation::setAntennaLocation(const Vector3Double &pX)
{
    _antennaLocation = pX;
}

Vector3Float& AntennaLocation::getRelativeAntennaLocation() 
{
    return _relativeAntennaLocation;
}

const Vector3Float& AntennaLocation::getRelativeAntennaLocation() const
{
    return _relativeAntennaLocation;
}

void AntennaLocation::setRelativeAntennaLocation(const Vector3Float &pX)
{
    _relativeAntennaLocation = pX;
}

void AntennaLocation::marshal(DataStream& dataStream) const
{
    _antennaLocation.marshal(dataStream);
    _relativeAntennaLocation.marshal(dataStream);
}

void AntennaLocation::unmarshal(DataStream& dataStream)
{
    _antennaLocation.unmarshal(dataStream);
    _relativeAntennaLocation.unmarshal(dataStream);
}


bool AntennaLocation::operator ==(const AntennaLocation& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_antennaLocation == rhs._antennaLocation) ) ivarsEqual = false;
     if( ! (_relativeAntennaLocation == rhs._relativeAntennaLocation) ) ivarsEqual = false;

    return ivarsEqual;
 }

int AntennaLocation::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + _antennaLocation.getMarshalledSize();  // _antennaLocation
   marshalSize = marshalSize + _relativeAntennaLocation.getMarshalledSize();  // _relativeAntennaLocation
    return marshalSize;
}

