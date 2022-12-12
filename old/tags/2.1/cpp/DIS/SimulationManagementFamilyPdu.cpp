#include <DIS/SimulationManagementFamilyPdu.h> 

using namespace DIS;


SimulationManagementFamilyPdu::SimulationManagementFamilyPdu() : Pdu(),
   _originatingEntityID(), 
   _receivingEntityID()
{
    setProtocolFamily( 5 );
}

SimulationManagementFamilyPdu::~SimulationManagementFamilyPdu()
{
}

EntityID& SimulationManagementFamilyPdu::getOriginatingEntityID() 
{
    return _originatingEntityID;
}

const EntityID& SimulationManagementFamilyPdu::getOriginatingEntityID() const
{
    return _originatingEntityID;
}

void SimulationManagementFamilyPdu::setOriginatingEntityID(const EntityID &pX)
{
    _originatingEntityID = pX;
}

EntityID& SimulationManagementFamilyPdu::getReceivingEntityID() 
{
    return _receivingEntityID;
}

const EntityID& SimulationManagementFamilyPdu::getReceivingEntityID() const
{
    return _receivingEntityID;
}

void SimulationManagementFamilyPdu::setReceivingEntityID(const EntityID &pX)
{
    _receivingEntityID = pX;
}

void SimulationManagementFamilyPdu::marshal(DataStream& dataStream) const
{
    Pdu::marshal(dataStream); // Marshal information in superclass first
    _originatingEntityID.marshal(dataStream);
    _receivingEntityID.marshal(dataStream);
}

void SimulationManagementFamilyPdu::unmarshal(DataStream& dataStream)
{
    Pdu::unmarshal(dataStream); // unmarshal information in superclass first
    _originatingEntityID.unmarshal(dataStream);
    _receivingEntityID.unmarshal(dataStream);
}


bool SimulationManagementFamilyPdu::operator ==(const SimulationManagementFamilyPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = Pdu::operator==(rhs);

     if( ! (_originatingEntityID == rhs._originatingEntityID) ) ivarsEqual = false;
     if( ! (_receivingEntityID == rhs._receivingEntityID) ) ivarsEqual = false;

    return ivarsEqual;
 }

int SimulationManagementFamilyPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = Pdu::getMarshalledSize();
   marshalSize = marshalSize + _originatingEntityID.getMarshalledSize();  // _originatingEntityID
   marshalSize = marshalSize + _receivingEntityID.getMarshalledSize();  // _receivingEntityID
    return marshalSize;
}

