#include <DIS/Relationship.h> 

using namespace DIS;


Relationship::Relationship():
   _nature(0), 
   _position(0)
{
}

Relationship::~Relationship()
{
}

unsigned short Relationship::getNature() const
{
    return _nature;
}

void Relationship::setNature(unsigned short pX)
{
    _nature = pX;
}

unsigned short Relationship::getPosition() const
{
    return _position;
}

void Relationship::setPosition(unsigned short pX)
{
    _position = pX;
}

void Relationship::marshal(DataStream& dataStream) const
{
    dataStream << _nature;
    dataStream << _position;
}

void Relationship::unmarshal(DataStream& dataStream)
{
    dataStream >> _nature;
    dataStream >> _position;
}


bool Relationship::operator ==(const Relationship& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_nature == rhs._nature) ) ivarsEqual = false;
     if( ! (_position == rhs._position) ) ivarsEqual = false;

    return ivarsEqual;
 }

int Relationship::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 2;  // _nature
   marshalSize = marshalSize + 2;  // _position
    return marshalSize;
}

