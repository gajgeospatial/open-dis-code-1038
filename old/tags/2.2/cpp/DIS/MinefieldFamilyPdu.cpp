#include <DIS/MinefieldFamilyPdu.h> 

using namespace DIS;


MinefieldFamilyPdu::MinefieldFamilyPdu() : Pdu()

{
    setProtocolFamily( 8 );
}

MinefieldFamilyPdu::~MinefieldFamilyPdu()
{
}

void MinefieldFamilyPdu::marshal(DataStream& dataStream) const
{
    Pdu::marshal(dataStream); // Marshal information in superclass first
}

void MinefieldFamilyPdu::unmarshal(DataStream& dataStream)
{
    Pdu::unmarshal(dataStream); // unmarshal information in superclass first
}


bool MinefieldFamilyPdu::operator ==(const MinefieldFamilyPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = Pdu::operator==(rhs);


    return ivarsEqual;
 }

int MinefieldFamilyPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = Pdu::getMarshalledSize();
    return marshalSize;
}

