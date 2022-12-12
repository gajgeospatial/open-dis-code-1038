#include <DIS/AngularVelocityVector.h> 

using namespace DIS;


AngularVelocityVector::AngularVelocityVector():
   _x(0), 
   _y(0), 
   _z(0)
{
}

AngularVelocityVector::~AngularVelocityVector()
{
}

float AngularVelocityVector::getX() const
{
    return _x;
}

void AngularVelocityVector::setX(float pX)
{
    _x = pX;
}

float AngularVelocityVector::getY() const
{
    return _y;
}

void AngularVelocityVector::setY(float pX)
{
    _y = pX;
}

float AngularVelocityVector::getZ() const
{
    return _z;
}

void AngularVelocityVector::setZ(float pX)
{
    _z = pX;
}

void AngularVelocityVector::marshal(DataStream& dataStream) const
{
    dataStream << _x;
    dataStream << _y;
    dataStream << _z;
}

void AngularVelocityVector::unmarshal(DataStream& dataStream)
{
    dataStream >> _x;
    dataStream >> _y;
    dataStream >> _z;
}


bool AngularVelocityVector::operator ==(const AngularVelocityVector& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_x == rhs._x) ) ivarsEqual = false;
     if( ! (_y == rhs._y) ) ivarsEqual = false;
     if( ! (_z == rhs._z) ) ivarsEqual = false;

    return ivarsEqual;
 }

int AngularVelocityVector::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 4;  // _x
   marshalSize = marshalSize + 4;  // _y
   marshalSize = marshalSize + 4;  // _z
    return marshalSize;
}

