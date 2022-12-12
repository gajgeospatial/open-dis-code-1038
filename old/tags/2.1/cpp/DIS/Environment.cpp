#include <DIS/Environment.h> 

using namespace DIS;


Environment::Environment():
   _environmentType(0), 
   _length(0), 
   _index(0), 
   _padding1(0), 
   _geometry(0), 
   _padding2(0)
{
}

Environment::~Environment()
{
}

unsigned int Environment::getEnvironmentType() const
{
    return _environmentType;
}

void Environment::setEnvironmentType(unsigned int pX)
{
    _environmentType = pX;
}

unsigned char Environment::getLength() const
{
    return _length;
}

void Environment::setLength(unsigned char pX)
{
    _length = pX;
}

unsigned char Environment::getIndex() const
{
    return _index;
}

void Environment::setIndex(unsigned char pX)
{
    _index = pX;
}

unsigned char Environment::getPadding1() const
{
    return _padding1;
}

void Environment::setPadding1(unsigned char pX)
{
    _padding1 = pX;
}

unsigned char Environment::getGeometry() const
{
    return _geometry;
}

void Environment::setGeometry(unsigned char pX)
{
    _geometry = pX;
}

unsigned char Environment::getPadding2() const
{
    return _padding2;
}

void Environment::setPadding2(unsigned char pX)
{
    _padding2 = pX;
}

void Environment::marshal(DataStream& dataStream) const
{
    dataStream << _environmentType;
    dataStream << _length;
    dataStream << _index;
    dataStream << _padding1;
    dataStream << _geometry;
    dataStream << _padding2;
}

void Environment::unmarshal(DataStream& dataStream)
{
    dataStream >> _environmentType;
    dataStream >> _length;
    dataStream >> _index;
    dataStream >> _padding1;
    dataStream >> _geometry;
    dataStream >> _padding2;
}


bool Environment::operator ==(const Environment& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_environmentType == rhs._environmentType) ) ivarsEqual = false;
     if( ! (_length == rhs._length) ) ivarsEqual = false;
     if( ! (_index == rhs._index) ) ivarsEqual = false;
     if( ! (_padding1 == rhs._padding1) ) ivarsEqual = false;
     if( ! (_geometry == rhs._geometry) ) ivarsEqual = false;
     if( ! (_padding2 == rhs._padding2) ) ivarsEqual = false;

    return ivarsEqual;
 }

int Environment::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 4;  // _environmentType
   marshalSize = marshalSize + 1;  // _length
   marshalSize = marshalSize + 1;  // _index
   marshalSize = marshalSize + 1;  // _padding1
   marshalSize = marshalSize + 1;  // _geometry
   marshalSize = marshalSize + 1;  // _padding2
    return marshalSize;
}

