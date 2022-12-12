#include <DIS/SimulationManagementWithReliabilityFamilyPdu.h> 

using namespace DIS;


SimulationManagementWithReliabilityFamilyPdu::SimulationManagementWithReliabilityFamilyPdu() : Pdu(),
   _originatingEntityID(), 
   _receivingEntityID()
{
    setProtocolFamily( 10 );
}

SimulationManagementWithReliabilityFamilyPdu::~SimulationManagementWithReliabilityFamilyPdu()
{
}

EntityID& SimulationManagementWithReliabilityFamilyPdu::getOriginatingEntityID() 
{
    return _originatingEntityID;
}

const EntityID& SimulationManagementWithReliabilityFamilyPdu::getOriginatingEntityID() const
{
    return _originatingEntityID;
}

void SimulationManagementWithReliabilityFamilyPdu::setOriginatingEntityID(const EntityID &pX)
{
    _originatingEntityID = pX;
}

EntityID& SimulationManagementWithReliabilityFamilyPdu::getReceivingEntityID() 
{
    return _receivingEntityID;
}

const EntityID& SimulationManagementWithReliabilityFamilyPdu::getReceivingEntityID() const
{
    return _receivingEntityID;
}

void SimulationManagementWithReliabilityFamilyPdu::setReceivingEntityID(const EntityID &pX)
{
    _receivingEntityID = pX;
}

void SimulationManagementWithReliabilityFamilyPdu::marshal(DataStream& dataStream) const
{
    Pdu::marshal(dataStream); // Marshal information in superclass first
    _originatingEntityID.marshal(dataStream);
    _receivingEntityID.marshal(dataStream);
}

void SimulationManagementWithReliabilityFamilyPdu::unmarshal(DataStream& dataStream)
{
    Pdu::unmarshal(dataStream); // unmarshal information in superclass first
    _originatingEntityID.unmarshal(dataStream);
    _receivingEntityID.unmarshal(dataStream);
}


bool SimulationManagementWithReliabilityFamilyPdu::operator ==(const SimulationManagementWithReliabilityFamilyPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = Pdu::operator==(rhs);

     if( ! (_originatingEntityID == rhs._originatingEntityID) ) ivarsEqual = false;
     if( ! (_receivingEntityID == rhs._receivingEntityID) ) ivarsEqual = false;

    return ivarsEqual;
 }

int SimulationManagementWithReliabilityFamilyPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = Pdu::getMarshalledSize();
   marshalSize = marshalSize + _originatingEntityID.getMarshalledSize();  // _originatingEntityID
   marshalSize = marshalSize + _receivingEntityID.getMarshalledSize();  // _receivingEntityID
    return marshalSize;
}

