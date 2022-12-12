#include <DIS/AcknowledgeReliablePdu.h> 

using namespace DIS;


AcknowledgeReliablePdu::AcknowledgeReliablePdu() : SimulationManagementWithReliabilityFamilyPdu(),
   _acknowledgeFlag(0), 
   _responseFlag(0), 
   _requestID(0)
{
    setPduType( 55 );
}

AcknowledgeReliablePdu::~AcknowledgeReliablePdu()
{
}

unsigned short AcknowledgeReliablePdu::getAcknowledgeFlag() const
{
    return _acknowledgeFlag;
}

void AcknowledgeReliablePdu::setAcknowledgeFlag(unsigned short pX)
{
    _acknowledgeFlag = pX;
}

unsigned short AcknowledgeReliablePdu::getResponseFlag() const
{
    return _responseFlag;
}

void AcknowledgeReliablePdu::setResponseFlag(unsigned short pX)
{
    _responseFlag = pX;
}

unsigned int AcknowledgeReliablePdu::getRequestID() const
{
    return _requestID;
}

void AcknowledgeReliablePdu::setRequestID(unsigned int pX)
{
    _requestID = pX;
}

void AcknowledgeReliablePdu::marshal(DataStream& dataStream) const
{
    SimulationManagementWithReliabilityFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    dataStream << _acknowledgeFlag;
    dataStream << _responseFlag;
    dataStream << _requestID;
}

void AcknowledgeReliablePdu::unmarshal(DataStream& dataStream)
{
    SimulationManagementWithReliabilityFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    dataStream >> _acknowledgeFlag;
    dataStream >> _responseFlag;
    dataStream >> _requestID;
}


bool AcknowledgeReliablePdu::operator ==(const AcknowledgeReliablePdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = SimulationManagementWithReliabilityFamilyPdu::operator==(rhs);

     if( ! (_acknowledgeFlag == rhs._acknowledgeFlag) ) ivarsEqual = false;
     if( ! (_responseFlag == rhs._responseFlag) ) ivarsEqual = false;
     if( ! (_requestID == rhs._requestID) ) ivarsEqual = false;

    return ivarsEqual;
 }

int AcknowledgeReliablePdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = SimulationManagementWithReliabilityFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + 2;  // _acknowledgeFlag
   marshalSize = marshalSize + 2;  // _responseFlag
   marshalSize = marshalSize + 4;  // _requestID
    return marshalSize;
}

