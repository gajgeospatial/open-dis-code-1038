#include <DIS/RemoveEntityPdu.h> 

using namespace DIS;


RemoveEntityPdu::RemoveEntityPdu() : SimulationManagementFamilyPdu(),
   _requestID(0)
{
    setPduType( 12 );
}

RemoveEntityPdu::~RemoveEntityPdu()
{
}

unsigned int RemoveEntityPdu::getRequestID() const
{
    return _requestID;
}

void RemoveEntityPdu::setRequestID(unsigned int pX)
{
    _requestID = pX;
}

void RemoveEntityPdu::marshal(DataStream& dataStream) const
{
    SimulationManagementFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    dataStream << _requestID;
}

void RemoveEntityPdu::unmarshal(DataStream& dataStream)
{
    SimulationManagementFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    dataStream >> _requestID;
}


bool RemoveEntityPdu::operator ==(const RemoveEntityPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = SimulationManagementFamilyPdu::operator==(rhs);

     if( ! (_requestID == rhs._requestID) ) ivarsEqual = false;

    return ivarsEqual;
 }

int RemoveEntityPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = SimulationManagementFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + 4;  // _requestID
    return marshalSize;
}

