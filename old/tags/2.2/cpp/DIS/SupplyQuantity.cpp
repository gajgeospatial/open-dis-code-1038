#include <DIS/SupplyQuantity.h> 

using namespace DIS;


SupplyQuantity::SupplyQuantity():
   _supplyType(), 
   _quantity(0)
{
}

SupplyQuantity::~SupplyQuantity()
{
}

EntityID& SupplyQuantity::getSupplyType() 
{
    return _supplyType;
}

const EntityID& SupplyQuantity::getSupplyType() const
{
    return _supplyType;
}

void SupplyQuantity::setSupplyType(const EntityID &pX)
{
    _supplyType = pX;
}

unsigned char SupplyQuantity::getQuantity() const
{
    return _quantity;
}

void SupplyQuantity::setQuantity(unsigned char pX)
{
    _quantity = pX;
}

void SupplyQuantity::marshal(DataStream& dataStream) const
{
    _supplyType.marshal(dataStream);
    dataStream << _quantity;
}

void SupplyQuantity::unmarshal(DataStream& dataStream)
{
    _supplyType.unmarshal(dataStream);
    dataStream >> _quantity;
}


bool SupplyQuantity::operator ==(const SupplyQuantity& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_supplyType == rhs._supplyType) ) ivarsEqual = false;
     if( ! (_quantity == rhs._quantity) ) ivarsEqual = false;

    return ivarsEqual;
 }

int SupplyQuantity::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + _supplyType.getMarshalledSize();  // _supplyType
   marshalSize = marshalSize + 1;  // _quantity
    return marshalSize;
}

