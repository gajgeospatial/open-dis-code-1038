#include <DIS/EntityInformationFamilyPdu.h> 

using namespace DIS;


EntityInformationFamilyPdu::EntityInformationFamilyPdu() : Pdu()

{
    setProtocolFamily( 1 );
}

EntityInformationFamilyPdu::~EntityInformationFamilyPdu()
{
}

void EntityInformationFamilyPdu::marshal(DataStream& dataStream) const
{
    Pdu::marshal(dataStream); // Marshal information in superclass first
}

void EntityInformationFamilyPdu::unmarshal(DataStream& dataStream)
{
    Pdu::unmarshal(dataStream); // unmarshal information in superclass first
}


bool EntityInformationFamilyPdu::operator ==(const EntityInformationFamilyPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = Pdu::operator==(rhs);


    return ivarsEqual;
 }

int EntityInformationFamilyPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = Pdu::getMarshalledSize();
    return marshalSize;
}

