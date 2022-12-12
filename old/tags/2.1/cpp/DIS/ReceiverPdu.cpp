#include <DIS/ReceiverPdu.h> 

using namespace DIS;


ReceiverPdu::ReceiverPdu() : RadioCommunicationsFamilyPdu(),
   _receiverState(0), 
   _padding1(0), 
   _receivedPoser(0.0), 
   _transmitterEntityId(), 
   _transmitterRadioId(0)
{
    setPduType( 27 );
}

ReceiverPdu::~ReceiverPdu()
{
}

unsigned short ReceiverPdu::getReceiverState() const
{
    return _receiverState;
}

void ReceiverPdu::setReceiverState(unsigned short pX)
{
    _receiverState = pX;
}

unsigned short ReceiverPdu::getPadding1() const
{
    return _padding1;
}

void ReceiverPdu::setPadding1(unsigned short pX)
{
    _padding1 = pX;
}

float ReceiverPdu::getReceivedPoser() const
{
    return _receivedPoser;
}

void ReceiverPdu::setReceivedPoser(float pX)
{
    _receivedPoser = pX;
}

EntityID& ReceiverPdu::getTransmitterEntityId() 
{
    return _transmitterEntityId;
}

const EntityID& ReceiverPdu::getTransmitterEntityId() const
{
    return _transmitterEntityId;
}

void ReceiverPdu::setTransmitterEntityId(const EntityID &pX)
{
    _transmitterEntityId = pX;
}

unsigned short ReceiverPdu::getTransmitterRadioId() const
{
    return _transmitterRadioId;
}

void ReceiverPdu::setTransmitterRadioId(unsigned short pX)
{
    _transmitterRadioId = pX;
}

void ReceiverPdu::marshal(DataStream& dataStream) const
{
    RadioCommunicationsFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    dataStream << _receiverState;
    dataStream << _padding1;
    dataStream << _receivedPoser;
    _transmitterEntityId.marshal(dataStream);
    dataStream << _transmitterRadioId;
}

void ReceiverPdu::unmarshal(DataStream& dataStream)
{
    RadioCommunicationsFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    dataStream >> _receiverState;
    dataStream >> _padding1;
    dataStream >> _receivedPoser;
    _transmitterEntityId.unmarshal(dataStream);
    dataStream >> _transmitterRadioId;
}


bool ReceiverPdu::operator ==(const ReceiverPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = RadioCommunicationsFamilyPdu::operator==(rhs);

     if( ! (_receiverState == rhs._receiverState) ) ivarsEqual = false;
     if( ! (_padding1 == rhs._padding1) ) ivarsEqual = false;
     if( ! (_receivedPoser == rhs._receivedPoser) ) ivarsEqual = false;
     if( ! (_transmitterEntityId == rhs._transmitterEntityId) ) ivarsEqual = false;
     if( ! (_transmitterRadioId == rhs._transmitterRadioId) ) ivarsEqual = false;

    return ivarsEqual;
 }

int ReceiverPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = RadioCommunicationsFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + 2;  // _receiverState
   marshalSize = marshalSize + 2;  // _padding1
   marshalSize = marshalSize + 4;  // _receivedPoser
   marshalSize = marshalSize + _transmitterEntityId.getMarshalledSize();  // _transmitterEntityId
   marshalSize = marshalSize + 2;  // _transmitterRadioId
    return marshalSize;
}

