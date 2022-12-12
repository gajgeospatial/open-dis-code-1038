#include <DIS/AggregateID.h> 

using namespace DIS;


AggregateID::AggregateID():
   _site(0), 
   _application(0), 
   _aggregateID(0)
{
}

AggregateID::~AggregateID()
{
}

unsigned short AggregateID::getSite() const
{
    return _site;
}

void AggregateID::setSite(unsigned short pX)
{
    _site = pX;
}

unsigned short AggregateID::getApplication() const
{
    return _application;
}

void AggregateID::setApplication(unsigned short pX)
{
    _application = pX;
}

unsigned short AggregateID::getAggregateID() const
{
    return _aggregateID;
}

void AggregateID::setAggregateID(unsigned short pX)
{
    _aggregateID = pX;
}

void AggregateID::marshal(DataStream& dataStream) const
{
    dataStream << _site;
    dataStream << _application;
    dataStream << _aggregateID;
}

void AggregateID::unmarshal(DataStream& dataStream)
{
    dataStream >> _site;
    dataStream >> _application;
    dataStream >> _aggregateID;
}


bool AggregateID::operator ==(const AggregateID& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_site == rhs._site) ) ivarsEqual = false;
     if( ! (_application == rhs._application) ) ivarsEqual = false;
     if( ! (_aggregateID == rhs._aggregateID) ) ivarsEqual = false;

    return ivarsEqual;
 }

int AggregateID::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 2;  // _site
   marshalSize = marshalSize + 2;  // _application
   marshalSize = marshalSize + 2;  // _aggregateID
    return marshalSize;
}

