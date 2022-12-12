#include <DIS/RepairCompletePdu.h> 

using namespace DIS;


RepairCompletePdu::RepairCompletePdu() : LogisticsFamilyPdu(),
   _receivingEntityID(), 
   _repairingEntityID(), 
   _repair(0), 
   _padding(0)
{
    setPduType( 9 );
}

RepairCompletePdu::~RepairCompletePdu()
{
}

EntityID& RepairCompletePdu::getReceivingEntityID() 
{
    return _receivingEntityID;
}

const EntityID& RepairCompletePdu::getReceivingEntityID() const
{
    return _receivingEntityID;
}

void RepairCompletePdu::setReceivingEntityID(const EntityID &pX)
{
    _receivingEntityID = pX;
}

EntityID& RepairCompletePdu::getRepairingEntityID() 
{
    return _repairingEntityID;
}

const EntityID& RepairCompletePdu::getRepairingEntityID() const
{
    return _repairingEntityID;
}

void RepairCompletePdu::setRepairingEntityID(const EntityID &pX)
{
    _repairingEntityID = pX;
}

unsigned short RepairCompletePdu::getRepair() const
{
    return _repair;
}

void RepairCompletePdu::setRepair(unsigned short pX)
{
    _repair = pX;
}

short RepairCompletePdu::getPadding() const
{
    return _padding;
}

void RepairCompletePdu::setPadding(short pX)
{
    _padding = pX;
}

void RepairCompletePdu::marshal(DataStream& dataStream) const
{
    LogisticsFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _receivingEntityID.marshal(dataStream);
    _repairingEntityID.marshal(dataStream);
    dataStream << _repair;
    dataStream << _padding;
}

void RepairCompletePdu::unmarshal(DataStream& dataStream)
{
    LogisticsFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _receivingEntityID.unmarshal(dataStream);
    _repairingEntityID.unmarshal(dataStream);
    dataStream >> _repair;
    dataStream >> _padding;
}


bool RepairCompletePdu::operator ==(const RepairCompletePdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = LogisticsFamilyPdu::operator==(rhs);

     if( ! (_receivingEntityID == rhs._receivingEntityID) ) ivarsEqual = false;
     if( ! (_repairingEntityID == rhs._repairingEntityID) ) ivarsEqual = false;
     if( ! (_repair == rhs._repair) ) ivarsEqual = false;
     if( ! (_padding == rhs._padding) ) ivarsEqual = false;

    return ivarsEqual;
 }

int RepairCompletePdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = LogisticsFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _receivingEntityID.getMarshalledSize();  // _receivingEntityID
   marshalSize = marshalSize + _repairingEntityID.getMarshalledSize();  // _repairingEntityID
   marshalSize = marshalSize + 2;  // _repair
   marshalSize = marshalSize + 2;  // _padding
    return marshalSize;
}

