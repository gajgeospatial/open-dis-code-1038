#include <DIS/CreateEntityReliablePdu.h> 

using namespace DIS;


CreateEntityReliablePdu::CreateEntityReliablePdu() : SimulationManagementWithReliabilityFamilyPdu(),
   _requiredReliabilityService(0), 
   _pad1(0), 
   _pad2(0), 
   _requestID(0)
{
    setPduType( 51 );
}

CreateEntityReliablePdu::~CreateEntityReliablePdu()
{
}

unsigned char CreateEntityReliablePdu::getRequiredReliabilityService() const
{
    return _requiredReliabilityService;
}

void CreateEntityReliablePdu::setRequiredReliabilityService(unsigned char pX)
{
    _requiredReliabilityService = pX;
}

unsigned short CreateEntityReliablePdu::getPad1() const
{
    return _pad1;
}

void CreateEntityReliablePdu::setPad1(unsigned short pX)
{
    _pad1 = pX;
}

unsigned char CreateEntityReliablePdu::getPad2() const
{
    return _pad2;
}

void CreateEntityReliablePdu::setPad2(unsigned char pX)
{
    _pad2 = pX;
}

unsigned int CreateEntityReliablePdu::getRequestID() const
{
    return _requestID;
}

void CreateEntityReliablePdu::setRequestID(unsigned int pX)
{
    _requestID = pX;
}

void CreateEntityReliablePdu::marshal(DataStream& dataStream) const
{
    SimulationManagementWithReliabilityFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    dataStream << _requiredReliabilityService;
    dataStream << _pad1;
    dataStream << _pad2;
    dataStream << _requestID;
}

void CreateEntityReliablePdu::unmarshal(DataStream& dataStream)
{
    SimulationManagementWithReliabilityFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    dataStream >> _requiredReliabilityService;
    dataStream >> _pad1;
    dataStream >> _pad2;
    dataStream >> _requestID;
}


bool CreateEntityReliablePdu::operator ==(const CreateEntityReliablePdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = SimulationManagementWithReliabilityFamilyPdu::operator==(rhs);

     if( ! (_requiredReliabilityService == rhs._requiredReliabilityService) ) ivarsEqual = false;
     if( ! (_pad1 == rhs._pad1) ) ivarsEqual = false;
     if( ! (_pad2 == rhs._pad2) ) ivarsEqual = false;
     if( ! (_requestID == rhs._requestID) ) ivarsEqual = false;

    return ivarsEqual;
 }

int CreateEntityReliablePdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = SimulationManagementWithReliabilityFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + 1;  // _requiredReliabilityService
   marshalSize = marshalSize + 2;  // _pad1
   marshalSize = marshalSize + 1;  // _pad2
   marshalSize = marshalSize + 4;  // _requestID
    return marshalSize;
}

