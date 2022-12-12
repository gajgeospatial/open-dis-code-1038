#include <DIS/LogisticsFamilyPdu.h> 

using namespace DIS;


LogisticsFamilyPdu::LogisticsFamilyPdu() : Pdu()

{
    setProtocolFamily( 3 );
}

LogisticsFamilyPdu::~LogisticsFamilyPdu()
{
}

void LogisticsFamilyPdu::marshal(DataStream& dataStream) const
{
    Pdu::marshal(dataStream); // Marshal information in superclass first
}

void LogisticsFamilyPdu::unmarshal(DataStream& dataStream)
{
    Pdu::unmarshal(dataStream); // unmarshal information in superclass first
}


bool LogisticsFamilyPdu::operator ==(const LogisticsFamilyPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = Pdu::operator==(rhs);


    return ivarsEqual;
 }

int LogisticsFamilyPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = Pdu::getMarshalledSize();
    return marshalSize;
}

