#include <DIS/Vector3Double.h> 

using namespace DIS;


Vector3Double::Vector3Double():
   _x(0.0), 
   _y(0.0), 
   _z(0.0)
{
}

Vector3Double::~Vector3Double()
{
}

double Vector3Double::getX() const
{
    return _x;
}

void Vector3Double::setX(double pX)
{
    _x = pX;
}

double Vector3Double::getY() const
{
    return _y;
}

void Vector3Double::setY(double pX)
{
    _y = pX;
}

double Vector3Double::getZ() const
{
    return _z;
}

void Vector3Double::setZ(double pX)
{
    _z = pX;
}

void Vector3Double::marshal(DataStream& dataStream) const
{
    dataStream << _x;
    dataStream << _y;
    dataStream << _z;
}

void Vector3Double::unmarshal(DataStream& dataStream)
{
    dataStream >> _x;
    dataStream >> _y;
    dataStream >> _z;
}


bool Vector3Double::operator ==(const Vector3Double& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_x == rhs._x) ) ivarsEqual = false;
     if( ! (_y == rhs._y) ) ivarsEqual = false;
     if( ! (_z == rhs._z) ) ivarsEqual = false;

    return ivarsEqual;
 }

int Vector3Double::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 8;  // _x
   marshalSize = marshalSize + 8;  // _y
   marshalSize = marshalSize + 8;  // _z
    return marshalSize;
}

