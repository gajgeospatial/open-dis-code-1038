#include <DIS/StartResumePdu.h> 

using namespace DIS;


StartResumePdu::StartResumePdu() : SimulationManagementFamilyPdu(),
   _realWorldTime(), 
   _simulationTime(), 
   _requestID(0)
{
    setPduType( 13 );
}

StartResumePdu::~StartResumePdu()
{
}

ClockTime& StartResumePdu::getRealWorldTime() 
{
    return _realWorldTime;
}

const ClockTime& StartResumePdu::getRealWorldTime() const
{
    return _realWorldTime;
}

void StartResumePdu::setRealWorldTime(const ClockTime &pX)
{
    _realWorldTime = pX;
}

ClockTime& StartResumePdu::getSimulationTime() 
{
    return _simulationTime;
}

const ClockTime& StartResumePdu::getSimulationTime() const
{
    return _simulationTime;
}

void StartResumePdu::setSimulationTime(const ClockTime &pX)
{
    _simulationTime = pX;
}

unsigned int StartResumePdu::getRequestID() const
{
    return _requestID;
}

void StartResumePdu::setRequestID(unsigned int pX)
{
    _requestID = pX;
}

void StartResumePdu::marshal(DataStream& dataStream) const
{
    SimulationManagementFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _realWorldTime.marshal(dataStream);
    _simulationTime.marshal(dataStream);
    dataStream << _requestID;
}

void StartResumePdu::unmarshal(DataStream& dataStream)
{
    SimulationManagementFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _realWorldTime.unmarshal(dataStream);
    _simulationTime.unmarshal(dataStream);
    dataStream >> _requestID;
}


bool StartResumePdu::operator ==(const StartResumePdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = SimulationManagementFamilyPdu::operator==(rhs);

     if( ! (_realWorldTime == rhs._realWorldTime) ) ivarsEqual = false;
     if( ! (_simulationTime == rhs._simulationTime) ) ivarsEqual = false;
     if( ! (_requestID == rhs._requestID) ) ivarsEqual = false;

    return ivarsEqual;
 }

int StartResumePdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = SimulationManagementFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _realWorldTime.getMarshalledSize();  // _realWorldTime
   marshalSize = marshalSize + _simulationTime.getMarshalledSize();  // _simulationTime
   marshalSize = marshalSize + 4;  // _requestID
    return marshalSize;
}

