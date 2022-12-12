#include <DIS/ResupplyOfferPdu.h> 

using namespace DIS;


ResupplyOfferPdu::ResupplyOfferPdu() : LogisticsFamilyPdu(),
   _receivingEntityID(), 
   _supplyingEntityID(), 
   _numberOfSupplyTypes(0), 
   _padding1(0), 
   _padding2(0)
{
    setPduType( 6 );
}

ResupplyOfferPdu::~ResupplyOfferPdu()
{
    _supplies.clear();
}

EntityID& ResupplyOfferPdu::getReceivingEntityID() 
{
    return _receivingEntityID;
}

const EntityID& ResupplyOfferPdu::getReceivingEntityID() const
{
    return _receivingEntityID;
}

void ResupplyOfferPdu::setReceivingEntityID(const EntityID &pX)
{
    _receivingEntityID = pX;
}

EntityID& ResupplyOfferPdu::getSupplyingEntityID() 
{
    return _supplyingEntityID;
}

const EntityID& ResupplyOfferPdu::getSupplyingEntityID() const
{
    return _supplyingEntityID;
}

void ResupplyOfferPdu::setSupplyingEntityID(const EntityID &pX)
{
    _supplyingEntityID = pX;
}

unsigned char ResupplyOfferPdu::getNumberOfSupplyTypes() const
{
   return _supplies.size();
}

short ResupplyOfferPdu::getPadding1() const
{
    return _padding1;
}

void ResupplyOfferPdu::setPadding1(short pX)
{
    _padding1 = pX;
}

char ResupplyOfferPdu::getPadding2() const
{
    return _padding2;
}

void ResupplyOfferPdu::setPadding2(char pX)
{
    _padding2 = pX;
}

std::vector<SupplyQuantity>& ResupplyOfferPdu::getSupplies() 
{
    return _supplies;
}

const std::vector<SupplyQuantity>& ResupplyOfferPdu::getSupplies() const
{
    return _supplies;
}

void ResupplyOfferPdu::setSupplies(const std::vector<SupplyQuantity>& pX)
{
     _supplies = pX;
}

void ResupplyOfferPdu::marshal(DataStream& dataStream) const
{
    LogisticsFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _receivingEntityID.marshal(dataStream);
    _supplyingEntityID.marshal(dataStream);
    dataStream << ( unsigned char )_supplies.size();
    dataStream << _padding1;
    dataStream << _padding2;

     for(size_t idx = 0; idx < _supplies.size(); idx++)
     {
        SupplyQuantity x = _supplies[idx];
        x.marshal(dataStream);
     }

}

void ResupplyOfferPdu::unmarshal(DataStream& dataStream)
{
    LogisticsFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _receivingEntityID.unmarshal(dataStream);
    _supplyingEntityID.unmarshal(dataStream);
    dataStream >> _numberOfSupplyTypes;
    dataStream >> _padding1;
    dataStream >> _padding2;

     _supplies.clear();
     for(size_t idx = 0; idx < _numberOfSupplyTypes; idx++)
     {
        SupplyQuantity x;
        x.unmarshal(dataStream);
        _supplies.push_back(x);
     }
}


bool ResupplyOfferPdu::operator ==(const ResupplyOfferPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = LogisticsFamilyPdu::operator==(rhs);

     if( ! (_receivingEntityID == rhs._receivingEntityID) ) ivarsEqual = false;
     if( ! (_supplyingEntityID == rhs._supplyingEntityID) ) ivarsEqual = false;
     if( ! (_padding1 == rhs._padding1) ) ivarsEqual = false;
     if( ! (_padding2 == rhs._padding2) ) ivarsEqual = false;

     for(size_t idx = 0; idx < _supplies.size(); idx++)
     {
        if( ! ( _supplies[idx] == rhs._supplies[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int ResupplyOfferPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = LogisticsFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _receivingEntityID.getMarshalledSize();  // _receivingEntityID
   marshalSize = marshalSize + _supplyingEntityID.getMarshalledSize();  // _supplyingEntityID
   marshalSize = marshalSize + 1;  // _numberOfSupplyTypes
   marshalSize = marshalSize + 2;  // _padding1
   marshalSize = marshalSize + 1;  // _padding2

   for(int idx=0; idx < _supplies.size(); idx++)
   {
        SupplyQuantity listElement = _supplies[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

