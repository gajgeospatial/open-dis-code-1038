#include <DIS/EventID.h> 

using namespace DIS;


EventID::EventID():
   _application(0), 
   _site(0), 
   _eventNumber(0)
{
}

EventID::~EventID()
{
}

unsigned short EventID::getApplication() const
{
    return _application;
}

void EventID::setApplication(unsigned short pX)
{
    _application = pX;
}

unsigned short EventID::getSite() const
{
    return _site;
}

void EventID::setSite(unsigned short pX)
{
    _site = pX;
}

unsigned short EventID::getEventNumber() const
{
    return _eventNumber;
}

void EventID::setEventNumber(unsigned short pX)
{
    _eventNumber = pX;
}

void EventID::marshal(DataStream& dataStream) const
{
    dataStream << _application;
    dataStream << _site;
    dataStream << _eventNumber;
}

void EventID::unmarshal(DataStream& dataStream)
{
    dataStream >> _application;
    dataStream >> _site;
    dataStream >> _eventNumber;
}


bool EventID::operator ==(const EventID& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_application == rhs._application) ) ivarsEqual = false;
     if( ! (_site == rhs._site) ) ivarsEqual = false;
     if( ! (_eventNumber == rhs._eventNumber) ) ivarsEqual = false;

    return ivarsEqual;
 }

int EventID::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 2;  // _application
   marshalSize = marshalSize + 2;  // _site
   marshalSize = marshalSize + 2;  // _eventNumber
    return marshalSize;
}

