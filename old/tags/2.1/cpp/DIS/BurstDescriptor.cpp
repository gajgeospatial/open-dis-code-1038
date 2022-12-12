#include <DIS/BurstDescriptor.h> 

using namespace DIS;


BurstDescriptor::BurstDescriptor():
   _munition(), 
   _warhead(0), 
   _fuse(0), 
   _quantity(0), 
   _rate(0)
{
}

BurstDescriptor::~BurstDescriptor()
{
}

EntityType& BurstDescriptor::getMunition() 
{
    return _munition;
}

const EntityType& BurstDescriptor::getMunition() const
{
    return _munition;
}

void BurstDescriptor::setMunition(const EntityType &pX)
{
    _munition = pX;
}

unsigned short BurstDescriptor::getWarhead() const
{
    return _warhead;
}

void BurstDescriptor::setWarhead(unsigned short pX)
{
    _warhead = pX;
}

unsigned short BurstDescriptor::getFuse() const
{
    return _fuse;
}

void BurstDescriptor::setFuse(unsigned short pX)
{
    _fuse = pX;
}

unsigned short BurstDescriptor::getQuantity() const
{
    return _quantity;
}

void BurstDescriptor::setQuantity(unsigned short pX)
{
    _quantity = pX;
}

unsigned short BurstDescriptor::getRate() const
{
    return _rate;
}

void BurstDescriptor::setRate(unsigned short pX)
{
    _rate = pX;
}

void BurstDescriptor::marshal(DataStream& dataStream) const
{
    _munition.marshal(dataStream);
    dataStream << _warhead;
    dataStream << _fuse;
    dataStream << _quantity;
    dataStream << _rate;
}

void BurstDescriptor::unmarshal(DataStream& dataStream)
{
    _munition.unmarshal(dataStream);
    dataStream >> _warhead;
    dataStream >> _fuse;
    dataStream >> _quantity;
    dataStream >> _rate;
}


bool BurstDescriptor::operator ==(const BurstDescriptor& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_munition == rhs._munition) ) ivarsEqual = false;
     if( ! (_warhead == rhs._warhead) ) ivarsEqual = false;
     if( ! (_fuse == rhs._fuse) ) ivarsEqual = false;
     if( ! (_quantity == rhs._quantity) ) ivarsEqual = false;
     if( ! (_rate == rhs._rate) ) ivarsEqual = false;

    return ivarsEqual;
 }

int BurstDescriptor::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + _munition.getMarshalledSize();  // _munition
   marshalSize = marshalSize + 2;  // _warhead
   marshalSize = marshalSize + 2;  // _fuse
   marshalSize = marshalSize + 2;  // _quantity
   marshalSize = marshalSize + 2;  // _rate
    return marshalSize;
}

