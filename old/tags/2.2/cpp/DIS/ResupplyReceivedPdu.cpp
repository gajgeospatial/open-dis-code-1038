#include <DIS/ResupplyReceivedPdu.h> 

using namespace DIS;


ResupplyReceivedPdu::ResupplyReceivedPdu() : LogisticsFamilyPdu(),
   _receivingEntityID(), 
   _supplyingEntityID(), 
   _numberOfSupplyTypes(0), 
   _padding1(0), 
   _padding2(0)
{
    setPduType( 7 );
}

ResupplyReceivedPdu::~ResupplyReceivedPdu()
{
    _supplies.clear();
}

EntityID& ResupplyReceivedPdu::getReceivingEntityID() 
{
    return _receivingEntityID;
}

const EntityID& ResupplyReceivedPdu::getReceivingEntityID() const
{
    return _receivingEntityID;
}

void ResupplyReceivedPdu::setReceivingEntityID(const EntityID &pX)
{
    _receivingEntityID = pX;
}

EntityID& ResupplyReceivedPdu::getSupplyingEntityID() 
{
    return _supplyingEntityID;
}

const EntityID& ResupplyReceivedPdu::getSupplyingEntityID() const
{
    return _supplyingEntityID;
}

void ResupplyReceivedPdu::setSupplyingEntityID(const EntityID &pX)
{
    _supplyingEntityID = pX;
}

unsigned char ResupplyReceivedPdu::getNumberOfSupplyTypes() const
{
   return _supplies.size();
}

short ResupplyReceivedPdu::getPadding1() const
{
    return _padding1;
}

void ResupplyReceivedPdu::setPadding1(short pX)
{
    _padding1 = pX;
}

char ResupplyReceivedPdu::getPadding2() const
{
    return _padding2;
}

void ResupplyReceivedPdu::setPadding2(char pX)
{
    _padding2 = pX;
}

std::vector<SupplyQuantity>& ResupplyReceivedPdu::getSupplies() 
{
    return _supplies;
}

const std::vector<SupplyQuantity>& ResupplyReceivedPdu::getSupplies() const
{
    return _supplies;
}

void ResupplyReceivedPdu::setSupplies(const std::vector<SupplyQuantity>& pX)
{
     _supplies = pX;
}

void ResupplyReceivedPdu::marshal(DataStream& dataStream) const
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

void ResupplyReceivedPdu::unmarshal(DataStream& dataStream)
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


bool ResupplyReceivedPdu::operator ==(const ResupplyReceivedPdu& rhs) const
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

int ResupplyReceivedPdu::getMarshalledSize() const
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

