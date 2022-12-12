#include <DIS/VectoringNozzleSystemData.h> 

using namespace DIS;


VectoringNozzleSystemData::VectoringNozzleSystemData():
   _horizontalDeflectionAngle(0.0), 
   _verticalDeflectionAngle(0.0)
{
}

VectoringNozzleSystemData::~VectoringNozzleSystemData()
{
}

float VectoringNozzleSystemData::getHorizontalDeflectionAngle() const
{
    return _horizontalDeflectionAngle;
}

void VectoringNozzleSystemData::setHorizontalDeflectionAngle(float pX)
{
    _horizontalDeflectionAngle = pX;
}

float VectoringNozzleSystemData::getVerticalDeflectionAngle() const
{
    return _verticalDeflectionAngle;
}

void VectoringNozzleSystemData::setVerticalDeflectionAngle(float pX)
{
    _verticalDeflectionAngle = pX;
}

void VectoringNozzleSystemData::marshal(DataStream& dataStream) const
{
    dataStream << _horizontalDeflectionAngle;
    dataStream << _verticalDeflectionAngle;
}

void VectoringNozzleSystemData::unmarshal(DataStream& dataStream)
{
    dataStream >> _horizontalDeflectionAngle;
    dataStream >> _verticalDeflectionAngle;
}


bool VectoringNozzleSystemData::operator ==(const VectoringNozzleSystemData& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_horizontalDeflectionAngle == rhs._horizontalDeflectionAngle) ) ivarsEqual = false;
     if( ! (_verticalDeflectionAngle == rhs._verticalDeflectionAngle) ) ivarsEqual = false;

    return ivarsEqual;
 }

int VectoringNozzleSystemData::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 4;  // _horizontalDeflectionAngle
   marshalSize = marshalSize + 4;  // _verticalDeflectionAngle
    return marshalSize;
}

