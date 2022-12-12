#include <DIS/StopFreezePdu.h> 

using namespace DIS;


StopFreezePdu::StopFreezePdu() : SimulationManagementFamilyPdu(),
   _realWorldTime(), 
   _reason(0), 
   _frozenBehavior(0), 
   _padding1(0), 
   _requestID(0)
{
    setPduType( 14 );
}

StopFreezePdu::~StopFreezePdu()
{
}

ClockTime& StopFreezePdu::getRealWorldTime() 
{
    return _realWorldTime;
}

const ClockTime& StopFreezePdu::getRealWorldTime() const
{
    return _realWorldTime;
}

void StopFreezePdu::setRealWorldTime(const ClockTime &pX)
{
    _realWorldTime = pX;
}

unsigned char StopFreezePdu::getReason() const
{
    return _reason;
}

void StopFreezePdu::setReason(unsigned char pX)
{
    _reason = pX;
}

unsigned char StopFreezePdu::getFrozenBehavior() const
{
    return _frozenBehavior;
}

void StopFreezePdu::setFrozenBehavior(unsigned char pX)
{
    _frozenBehavior = pX;
}

short StopFreezePdu::getPadding1() const
{
    return _padding1;
}

void StopFreezePdu::setPadding1(short pX)
{
    _padding1 = pX;
}

unsigned int StopFreezePdu::getRequestID() const
{
    return _requestID;
}

void StopFreezePdu::setRequestID(unsigned int pX)
{
    _requestID = pX;
}

void StopFreezePdu::marshal(DataStream& dataStream) const
{
    SimulationManagementFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _realWorldTime.marshal(dataStream);
    dataStream << _reason;
    dataStream << _frozenBehavior;
    dataStream << _padding1;
    dataStream << _requestID;
}

void StopFreezePdu::unmarshal(DataStream& dataStream)
{
    SimulationManagementFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _realWorldTime.unmarshal(dataStream);
    dataStream >> _reason;
    dataStream >> _frozenBehavior;
    dataStream >> _padding1;
    dataStream >> _requestID;
}


bool StopFreezePdu::operator ==(const StopFreezePdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = SimulationManagementFamilyPdu::operator==(rhs);

     if( ! (_realWorldTime == rhs._realWorldTime) ) ivarsEqual = false;
     if( ! (_reason == rhs._reason) ) ivarsEqual = false;
     if( ! (_frozenBehavior == rhs._frozenBehavior) ) ivarsEqual = false;
     if( ! (_padding1 == rhs._padding1) ) ivarsEqual = false;
     if( ! (_requestID == rhs._requestID) ) ivarsEqual = false;

    return ivarsEqual;
 }

int StopFreezePdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = SimulationManagementFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _realWorldTime.getMarshalledSize();  // _realWorldTime
   marshalSize = marshalSize + 1;  // _reason
   marshalSize = marshalSize + 1;  // _frozenBehavior
   marshalSize = marshalSize + 2;  // _padding1
   marshalSize = marshalSize + 4;  // _requestID
    return marshalSize;
}

