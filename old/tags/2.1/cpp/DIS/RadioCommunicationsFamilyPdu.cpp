#include <DIS/RadioCommunicationsFamilyPdu.h> 

using namespace DIS;


RadioCommunicationsFamilyPdu::RadioCommunicationsFamilyPdu() : Pdu(),
   _entityId(), 
   _radioId(0)
{
    setProtocolFamily( 4 );
}

RadioCommunicationsFamilyPdu::~RadioCommunicationsFamilyPdu()
{
}

EntityID& RadioCommunicationsFamilyPdu::getEntityId() 
{
    return _entityId;
}

const EntityID& RadioCommunicationsFamilyPdu::getEntityId() const
{
    return _entityId;
}

void RadioCommunicationsFamilyPdu::setEntityId(const EntityID &pX)
{
    _entityId = pX;
}

unsigned short RadioCommunicationsFamilyPdu::getRadioId() const
{
    return _radioId;
}

void RadioCommunicationsFamilyPdu::setRadioId(unsigned short pX)
{
    _radioId = pX;
}

void RadioCommunicationsFamilyPdu::marshal(DataStream& dataStream) const
{
    Pdu::marshal(dataStream); // Marshal information in superclass first
    _entityId.marshal(dataStream);
    dataStream << _radioId;
}

void RadioCommunicationsFamilyPdu::unmarshal(DataStream& dataStream)
{
    Pdu::unmarshal(dataStream); // unmarshal information in superclass first
    _entityId.unmarshal(dataStream);
    dataStream >> _radioId;
}


bool RadioCommunicationsFamilyPdu::operator ==(const RadioCommunicationsFamilyPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = Pdu::operator==(rhs);

     if( ! (_entityId == rhs._entityId) ) ivarsEqual = false;
     if( ! (_radioId == rhs._radioId) ) ivarsEqual = false;

    return ivarsEqual;
 }

int RadioCommunicationsFamilyPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = Pdu::getMarshalledSize();
   marshalSize = marshalSize + _entityId.getMarshalledSize();  // _entityId
   marshalSize = marshalSize + 2;  // _radioId
    return marshalSize;
}

