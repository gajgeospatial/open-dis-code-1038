#include <DIS/MinfieldFamilyPdu.h> 

using namespace DIS;


MinfieldFamilyPdu::MinfieldFamilyPdu() : Pdu()

{
    setProtocolFamily( 8 );
}

MinfieldFamilyPdu::~MinfieldFamilyPdu()
{
}

void MinfieldFamilyPdu::marshal(DataStream& dataStream) const
{
    Pdu::marshal(dataStream); // Marshal information in superclass first
}

void MinfieldFamilyPdu::unmarshal(DataStream& dataStream)
{
    Pdu::unmarshal(dataStream); // unmarshal information in superclass first
}


bool MinfieldFamilyPdu::operator ==(const MinfieldFamilyPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = Pdu::operator==(rhs);


    return ivarsEqual;
 }

int MinfieldFamilyPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = Pdu::getMarshalledSize();
    return marshalSize;
}

