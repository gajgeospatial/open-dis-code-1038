#include <DIS/StartResumeReliablePdu.h> 

using namespace DIS;


StartResumeReliablePdu::StartResumeReliablePdu() : SimulationManagementWithReliabilityFamilyPdu(),
   _realWorldTime(), 
   _simulationTime(), 
   _requiredReliabilityService(0), 
   _pad1(0), 
   _pad2(0), 
   _requestID(0)
{
    setPduType( 53 );
}

StartResumeReliablePdu::~StartResumeReliablePdu()
{
}

ClockTime& StartResumeReliablePdu::getRealWorldTime() 
{
    return _realWorldTime;
}

const ClockTime& StartResumeReliablePdu::getRealWorldTime() const
{
    return _realWorldTime;
}

void StartResumeReliablePdu::setRealWorldTime(const ClockTime &pX)
{
    _realWorldTime = pX;
}

ClockTime& StartResumeReliablePdu::getSimulationTime() 
{
    return _simulationTime;
}

const ClockTime& StartResumeReliablePdu::getSimulationTime() const
{
    return _simulationTime;
}

void StartResumeReliablePdu::setSimulationTime(const ClockTime &pX)
{
    _simulationTime = pX;
}

unsigned char StartResumeReliablePdu::getRequiredReliabilityService() const
{
    return _requiredReliabilityService;
}

void StartResumeReliablePdu::setRequiredReliabilityService(unsigned char pX)
{
    _requiredReliabilityService = pX;
}

unsigned short StartResumeReliablePdu::getPad1() const
{
    return _pad1;
}

void StartResumeReliablePdu::setPad1(unsigned short pX)
{
    _pad1 = pX;
}

unsigned char StartResumeReliablePdu::getPad2() const
{
    return _pad2;
}

void StartResumeReliablePdu::setPad2(unsigned char pX)
{
    _pad2 = pX;
}

unsigned int StartResumeReliablePdu::getRequestID() const
{
    return _requestID;
}

void StartResumeReliablePdu::setRequestID(unsigned int pX)
{
    _requestID = pX;
}

void StartResumeReliablePdu::marshal(DataStream& dataStream) const
{
    SimulationManagementWithReliabilityFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _realWorldTime.marshal(dataStream);
    _simulationTime.marshal(dataStream);
    dataStream << _requiredReliabilityService;
    dataStream << _pad1;
    dataStream << _pad2;
    dataStream << _requestID;
}

void StartResumeReliablePdu::unmarshal(DataStream& dataStream)
{
    SimulationManagementWithReliabilityFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _realWorldTime.unmarshal(dataStream);
    _simulationTime.unmarshal(dataStream);
    dataStream >> _requiredReliabilityService;
    dataStream >> _pad1;
    dataStream >> _pad2;
    dataStream >> _requestID;
}


bool StartResumeReliablePdu::operator ==(const StartResumeReliablePdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = SimulationManagementWithReliabilityFamilyPdu::operator==(rhs);

     if( ! (_realWorldTime == rhs._realWorldTime) ) ivarsEqual = false;
     if( ! (_simulationTime == rhs._simulationTime) ) ivarsEqual = false;
     if( ! (_requiredReliabilityService == rhs._requiredReliabilityService) ) ivarsEqual = false;
     if( ! (_pad1 == rhs._pad1) ) ivarsEqual = false;
     if( ! (_pad2 == rhs._pad2) ) ivarsEqual = false;
     if( ! (_requestID == rhs._requestID) ) ivarsEqual = false;

    return ivarsEqual;
 }

int StartResumeReliablePdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = SimulationManagementWithReliabilityFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _realWorldTime.getMarshalledSize();  // _realWorldTime
   marshalSize = marshalSize + _simulationTime.getMarshalledSize();  // _simulationTime
   marshalSize = marshalSize + 1;  // _requiredReliabilityService
   marshalSize = marshalSize + 2;  // _pad1
   marshalSize = marshalSize + 1;  // _pad2
   marshalSize = marshalSize + 4;  // _requestID
    return marshalSize;
}

