#include <DIS/RepairResponsePdu.h> 

using namespace DIS;


RepairResponsePdu::RepairResponsePdu() : LogisticsFamilyPdu(),
   _receivingEntityID(), 
   _repairingEntityID(), 
   _repairResult(0), 
   _padding1(0), 
   _padding2(0)
{
    setPduType( 10 );
}

RepairResponsePdu::~RepairResponsePdu()
{
}

EntityID& RepairResponsePdu::getReceivingEntityID() 
{
    return _receivingEntityID;
}

const EntityID& RepairResponsePdu::getReceivingEntityID() const
{
    return _receivingEntityID;
}

void RepairResponsePdu::setReceivingEntityID(const EntityID &pX)
{
    _receivingEntityID = pX;
}

EntityID& RepairResponsePdu::getRepairingEntityID() 
{
    return _repairingEntityID;
}

const EntityID& RepairResponsePdu::getRepairingEntityID() const
{
    return _repairingEntityID;
}

void RepairResponsePdu::setRepairingEntityID(const EntityID &pX)
{
    _repairingEntityID = pX;
}

unsigned char RepairResponsePdu::getRepairResult() const
{
    return _repairResult;
}

void RepairResponsePdu::setRepairResult(unsigned char pX)
{
    _repairResult = pX;
}

short RepairResponsePdu::getPadding1() const
{
    return _padding1;
}

void RepairResponsePdu::setPadding1(short pX)
{
    _padding1 = pX;
}

char RepairResponsePdu::getPadding2() const
{
    return _padding2;
}

void RepairResponsePdu::setPadding2(char pX)
{
    _padding2 = pX;
}

void RepairResponsePdu::marshal(DataStream& dataStream) const
{
    LogisticsFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _receivingEntityID.marshal(dataStream);
    _repairingEntityID.marshal(dataStream);
    dataStream << _repairResult;
    dataStream << _padding1;
    dataStream << _padding2;
}

void RepairResponsePdu::unmarshal(DataStream& dataStream)
{
    LogisticsFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _receivingEntityID.unmarshal(dataStream);
    _repairingEntityID.unmarshal(dataStream);
    dataStream >> _repairResult;
    dataStream >> _padding1;
    dataStream >> _padding2;
}


bool RepairResponsePdu::operator ==(const RepairResponsePdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = LogisticsFamilyPdu::operator==(rhs);

     if( ! (_receivingEntityID == rhs._receivingEntityID) ) ivarsEqual = false;
     if( ! (_repairingEntityID == rhs._repairingEntityID) ) ivarsEqual = false;
     if( ! (_repairResult == rhs._repairResult) ) ivarsEqual = false;
     if( ! (_padding1 == rhs._padding1) ) ivarsEqual = false;
     if( ! (_padding2 == rhs._padding2) ) ivarsEqual = false;

    return ivarsEqual;
 }

int RepairResponsePdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = LogisticsFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _receivingEntityID.getMarshalledSize();  // _receivingEntityID
   marshalSize = marshalSize + _repairingEntityID.getMarshalledSize();  // _repairingEntityID
   marshalSize = marshalSize + 1;  // _repairResult
   marshalSize = marshalSize + 2;  // _padding1
   marshalSize = marshalSize + 1;  // _padding2
    return marshalSize;
}

