#include <DIS/ClockTime.h> 

using namespace DIS;


ClockTime::ClockTime():
   _hour(0), 
   _timePastHour(0)
{
}

ClockTime::~ClockTime()
{
}

int ClockTime::getHour() const
{
    return _hour;
}

void ClockTime::setHour(int pX)
{
    _hour = pX;
}

unsigned int ClockTime::getTimePastHour() const
{
    return _timePastHour;
}

void ClockTime::setTimePastHour(unsigned int pX)
{
    _timePastHour = pX;
}

void ClockTime::marshal(DataStream& dataStream) const
{
    dataStream << _hour;
    dataStream << _timePastHour;
}

void ClockTime::unmarshal(DataStream& dataStream)
{
    dataStream >> _hour;
    dataStream >> _timePastHour;
}


bool ClockTime::operator ==(const ClockTime& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_hour == rhs._hour) ) ivarsEqual = false;
     if( ! (_timePastHour == rhs._timePastHour) ) ivarsEqual = false;

    return ivarsEqual;
 }

int ClockTime::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 4;  // _hour
   marshalSize = marshalSize + 4;  // _timePastHour
    return marshalSize;
}

