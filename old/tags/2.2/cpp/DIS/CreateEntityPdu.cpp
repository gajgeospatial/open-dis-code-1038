#include <DIS/CreateEntityPdu.h> 

using namespace DIS;


CreateEntityPdu::CreateEntityPdu() : SimulationManagementFamilyPdu(),
   _requestID(0)
{
    setPduType( 11 );
}

CreateEntityPdu::~CreateEntityPdu()
{
}

unsigned int CreateEntityPdu::getRequestID() const
{
    return _requestID;
}

void CreateEntityPdu::setRequestID(unsigned int pX)
{
    _requestID = pX;
}

void CreateEntityPdu::marshal(DataStream& dataStream) const
{
    SimulationManagementFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    dataStream << _requestID;
}

void CreateEntityPdu::unmarshal(DataStream& dataStream)
{
    SimulationManagementFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    dataStream >> _requestID;
}


bool CreateEntityPdu::operator ==(const CreateEntityPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = SimulationManagementFamilyPdu::operator==(rhs);

     if( ! (_requestID == rhs._requestID) ) ivarsEqual = false;

    return ivarsEqual;
 }

int CreateEntityPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = SimulationManagementFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + 4;  // _requestID
    return marshalSize;
}

