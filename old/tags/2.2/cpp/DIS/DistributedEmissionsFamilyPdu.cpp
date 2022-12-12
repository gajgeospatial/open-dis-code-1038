#include <DIS/DistributedEmissionsFamilyPdu.h> 

using namespace DIS;


DistributedEmissionsFamilyPdu::DistributedEmissionsFamilyPdu() : Pdu()

{
    setProtocolFamily( 6 );
}

DistributedEmissionsFamilyPdu::~DistributedEmissionsFamilyPdu()
{
}

void DistributedEmissionsFamilyPdu::marshal(DataStream& dataStream) const
{
    Pdu::marshal(dataStream); // Marshal information in superclass first
}

void DistributedEmissionsFamilyPdu::unmarshal(DataStream& dataStream)
{
    Pdu::unmarshal(dataStream); // unmarshal information in superclass first
}


bool DistributedEmissionsFamilyPdu::operator ==(const DistributedEmissionsFamilyPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = Pdu::operator==(rhs);


    return ivarsEqual;
 }

int DistributedEmissionsFamilyPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = Pdu::getMarshalledSize();
    return marshalSize;
}

