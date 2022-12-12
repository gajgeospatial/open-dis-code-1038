#include <DIS/EntityManagementFamilyPdu.h> 

using namespace DIS;


EntityManagementFamilyPdu::EntityManagementFamilyPdu() : Pdu()

{
    setProtocolFamily( 7 );
}

EntityManagementFamilyPdu::~EntityManagementFamilyPdu()
{
}

void EntityManagementFamilyPdu::marshal(DataStream& dataStream) const
{
    Pdu::marshal(dataStream); // Marshal information in superclass first
}

void EntityManagementFamilyPdu::unmarshal(DataStream& dataStream)
{
    Pdu::unmarshal(dataStream); // unmarshal information in superclass first
}


bool EntityManagementFamilyPdu::operator ==(const EntityManagementFamilyPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = Pdu::operator==(rhs);


    return ivarsEqual;
 }

int EntityManagementFamilyPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = Pdu::getMarshalledSize();
    return marshalSize;
}

