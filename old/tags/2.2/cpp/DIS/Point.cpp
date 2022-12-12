#include <DIS/Point.h> 

using namespace DIS;


Point::Point():
   _x(0.0), 
   _y(0.0)
{
}

Point::~Point()
{
}

float Point::getX() const
{
    return _x;
}

void Point::setX(float pX)
{
    _x = pX;
}

float Point::getY() const
{
    return _y;
}

void Point::setY(float pX)
{
    _y = pX;
}

void Point::marshal(DataStream& dataStream) const
{
    dataStream << _x;
    dataStream << _y;
}

void Point::unmarshal(DataStream& dataStream)
{
    dataStream >> _x;
    dataStream >> _y;
}


bool Point::operator ==(const Point& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_x == rhs._x) ) ivarsEqual = false;
     if( ! (_y == rhs._y) ) ivarsEqual = false;

    return ivarsEqual;
 }

int Point::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 4;  // _x
   marshalSize = marshalSize + 4;  // _y
    return marshalSize;
}

