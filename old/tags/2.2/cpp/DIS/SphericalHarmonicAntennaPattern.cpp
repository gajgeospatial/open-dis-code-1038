#include <DIS/SphericalHarmonicAntennaPattern.h> 

using namespace DIS;


SphericalHarmonicAntennaPattern::SphericalHarmonicAntennaPattern():
   _order(0)
{
}

SphericalHarmonicAntennaPattern::~SphericalHarmonicAntennaPattern()
{
}

char SphericalHarmonicAntennaPattern::getOrder() const
{
    return _order;
}

void SphericalHarmonicAntennaPattern::setOrder(char pX)
{
    _order = pX;
}

void SphericalHarmonicAntennaPattern::marshal(DataStream& dataStream) const
{
    dataStream << _order;
}

void SphericalHarmonicAntennaPattern::unmarshal(DataStream& dataStream)
{
    dataStream >> _order;
}


bool SphericalHarmonicAntennaPattern::operator ==(const SphericalHarmonicAntennaPattern& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_order == rhs._order) ) ivarsEqual = false;

    return ivarsEqual;
 }

int SphericalHarmonicAntennaPattern::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 1;  // _order
    return marshalSize;
}

