#include <DIS/RemoveEntityReliablePdu.h> 

using namespace DIS;


RemoveEntityReliablePdu::RemoveEntityReliablePdu() : SimulationManagementWithReliabilityFamilyPdu(),
   _requiredReliabilityService(0), 
   _pad1(0), 
   _pad2(0), 
   _requestID(0)
{
    setPduType( 52 );
}

RemoveEntityReliablePdu::~RemoveEntityReliablePdu()
{
}

unsigned char RemoveEntityReliablePdu::getRequiredReliabilityService() const
{
    return _requiredReliabilityService;
}

void RemoveEntityReliablePdu::setRequiredReliabilityService(unsigned char pX)
{
    _requiredReliabilityService = pX;
}

unsigned short RemoveEntityReliablePdu::getPad1() const
{
    return _pad1;
}

void RemoveEntityReliablePdu::setPad1(unsigned short pX)
{
    _pad1 = pX;
}

unsigned char RemoveEntityReliablePdu::getPad2() const
{
    return _pad2;
}

void RemoveEntityReliablePdu::setPad2(unsigned char pX)
{
    _pad2 = pX;
}

unsigned int RemoveEntityReliablePdu::getRequestID() const
{
    return _requestID;
}

void RemoveEntityReliablePdu::setRequestID(unsigned int pX)
{
    _requestID = pX;
}

void RemoveEntityReliablePdu::marshal(DataStream& dataStream) const
{
    SimulationManagementWithReliabilityFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    dataStream << _requiredReliabilityService;
    dataStream << _pad1;
    dataStream << _pad2;
    dataStream << _requestID;
}

void RemoveEntityReliablePdu::unmarshal(DataStream& dataStream)
{
    SimulationManagementWithReliabilityFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    dataStream >> _requiredReliabilityService;
    dataStream >> _pad1;
    dataStream >> _pad2;
    dataStream >> _requestID;
}


bool RemoveEntityReliablePdu::operator ==(const RemoveEntityReliablePdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = SimulationManagementWithReliabilityFamilyPdu::operator==(rhs);

     if( ! (_requiredReliabilityService == rhs._requiredReliabilityService) ) ivarsEqual = false;
     if( ! (_pad1 == rhs._pad1) ) ivarsEqual = false;
     if( ! (_pad2 == rhs._pad2) ) ivarsEqual = false;
     if( ! (_requestID == rhs._requestID) ) ivarsEqual = false;

    return ivarsEqual;
 }

int RemoveEntityReliablePdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = SimulationManagementWithReliabilityFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + 1;  // _requiredReliabilityService
   marshalSize = marshalSize + 2;  // _pad1
   marshalSize = marshalSize + 1;  // _pad2
   marshalSize = marshalSize + 4;  // _requestID
    return marshalSize;
}

