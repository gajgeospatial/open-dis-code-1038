#include <DIS/SyntheticEnvironmentFamilyPdu.h> 

using namespace DIS;


SyntheticEnvironmentFamilyPdu::SyntheticEnvironmentFamilyPdu() : Pdu()

{
    setProtocolFamily( 9 );
}

SyntheticEnvironmentFamilyPdu::~SyntheticEnvironmentFamilyPdu()
{
}

void SyntheticEnvironmentFamilyPdu::marshal(DataStream& dataStream) const
{
    Pdu::marshal(dataStream); // Marshal information in superclass first
}

void SyntheticEnvironmentFamilyPdu::unmarshal(DataStream& dataStream)
{
    Pdu::unmarshal(dataStream); // unmarshal information in superclass first
}


bool SyntheticEnvironmentFamilyPdu::operator ==(const SyntheticEnvironmentFamilyPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = Pdu::operator==(rhs);


    return ivarsEqual;
 }

int SyntheticEnvironmentFamilyPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = Pdu::getMarshalledSize();
    return marshalSize;
}

