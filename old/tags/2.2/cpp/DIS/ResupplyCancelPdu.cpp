#include <DIS/ResupplyCancelPdu.h> 

using namespace DIS;


ResupplyCancelPdu::ResupplyCancelPdu() : LogisticsFamilyPdu(),
   _receivingEntityID(), 
   _supplyingEntityID()
{
    setPduType( 8 );
}

ResupplyCancelPdu::~ResupplyCancelPdu()
{
}

EntityID& ResupplyCancelPdu::getReceivingEntityID() 
{
    return _receivingEntityID;
}

const EntityID& ResupplyCancelPdu::getReceivingEntityID() const
{
    return _receivingEntityID;
}

void ResupplyCancelPdu::setReceivingEntityID(const EntityID &pX)
{
    _receivingEntityID = pX;
}

EntityID& ResupplyCancelPdu::getSupplyingEntityID() 
{
    return _supplyingEntityID;
}

const EntityID& ResupplyCancelPdu::getSupplyingEntityID() const
{
    return _supplyingEntityID;
}

void ResupplyCancelPdu::setSupplyingEntityID(const EntityID &pX)
{
    _supplyingEntityID = pX;
}

void ResupplyCancelPdu::marshal(DataStream& dataStream) const
{
    LogisticsFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _receivingEntityID.marshal(dataStream);
    _supplyingEntityID.marshal(dataStream);
}

void ResupplyCancelPdu::unmarshal(DataStream& dataStream)
{
    LogisticsFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _receivingEntityID.unmarshal(dataStream);
    _supplyingEntityID.unmarshal(dataStream);
}


bool ResupplyCancelPdu::operator ==(const ResupplyCancelPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = LogisticsFamilyPdu::operator==(rhs);

     if( ! (_receivingEntityID == rhs._receivingEntityID) ) ivarsEqual = false;
     if( ! (_supplyingEntityID == rhs._supplyingEntityID) ) ivarsEqual = false;

    return ivarsEqual;
 }

int ResupplyCancelPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = LogisticsFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _receivingEntityID.getMarshalledSize();  // _receivingEntityID
   marshalSize = marshalSize + _supplyingEntityID.getMarshalledSize();  // _supplyingEntityID
    return marshalSize;
}

