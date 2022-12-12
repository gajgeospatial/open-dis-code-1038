#include <DIS/Vector3Float.h> 

using namespace DIS;


Vector3Float::Vector3Float():
   _x(0.0), 
   _y(0.0), 
   _z(0.0)
{
}

Vector3Float::~Vector3Float()
{
}

float Vector3Float::getX() const
{
    return _x;
}

void Vector3Float::setX(float pX)
{
    _x = pX;
}

float Vector3Float::getY() const
{
    return _y;
}

void Vector3Float::setY(float pX)
{
    _y = pX;
}

float Vector3Float::getZ() const
{
    return _z;
}

void Vector3Float::setZ(float pX)
{
    _z = pX;
}

void Vector3Float::marshal(DataStream& dataStream) const
{
    dataStream << _x;
    dataStream << _y;
    dataStream << _z;
}

void Vector3Float::unmarshal(DataStream& dataStream)
{
    dataStream >> _x;
    dataStream >> _y;
    dataStream >> _z;
}


bool Vector3Float::operator ==(const Vector3Float& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_x == rhs._x) ) ivarsEqual = false;
     if( ! (_y == rhs._y) ) ivarsEqual = false;
     if( ! (_z == rhs._z) ) ivarsEqual = false;

    return ivarsEqual;
 }

int Vector3Float::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 4;  // _x
   marshalSize = marshalSize + 4;  // _y
   marshalSize = marshalSize + 4;  // _z
    return marshalSize;
}

