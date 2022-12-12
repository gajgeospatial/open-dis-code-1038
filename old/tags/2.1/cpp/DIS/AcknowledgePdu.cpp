#include <DIS/AcknowledgePdu.h> 

using namespace DIS;


AcknowledgePdu::AcknowledgePdu() : SimulationManagementFamilyPdu(),
   _acknowledgeFlag(0), 
   _responseFlag(0), 
   _requestID(0)
{
    setPduType( 15 );
}

AcknowledgePdu::~AcknowledgePdu()
{
}

unsigned short AcknowledgePdu::getAcknowledgeFlag() const
{
    return _acknowledgeFlag;
}

void AcknowledgePdu::setAcknowledgeFlag(unsigned short pX)
{
    _acknowledgeFlag = pX;
}

unsigned short AcknowledgePdu::getResponseFlag() const
{
    return _responseFlag;
}

void AcknowledgePdu::setResponseFlag(unsigned short pX)
{
    _responseFlag = pX;
}

unsigned int AcknowledgePdu::getRequestID() const
{
    return _requestID;
}

void AcknowledgePdu::setRequestID(unsigned int pX)
{
    _requestID = pX;
}

void AcknowledgePdu::marshal(DataStream& dataStream) const
{
    SimulationManagementFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    dataStream << _acknowledgeFlag;
    dataStream << _responseFlag;
    dataStream << _requestID;
}

void AcknowledgePdu::unmarshal(DataStream& dataStream)
{
    SimulationManagementFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    dataStream >> _acknowledgeFlag;
    dataStream >> _responseFlag;
    dataStream >> _requestID;
}


bool AcknowledgePdu::operator ==(const AcknowledgePdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = SimulationManagementFamilyPdu::operator==(rhs);

     if( ! (_acknowledgeFlag == rhs._acknowledgeFlag) ) ivarsEqual = false;
     if( ! (_responseFlag == rhs._responseFlag) ) ivarsEqual = false;
     if( ! (_requestID == rhs._requestID) ) ivarsEqual = false;

    return ivarsEqual;
 }

int AcknowledgePdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = SimulationManagementFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + 2;  // _acknowledgeFlag
   marshalSize = marshalSize + 2;  // _responseFlag
   marshalSize = marshalSize + 4;  // _requestID
    return marshalSize;
}

