#include <DIS/EntityID.h> 

using namespace DIS;


EntityID::EntityID():
   _site(0), 
   _application(0), 
   _entity(0)
{
}

EntityID::~EntityID()
{
}

unsigned short EntityID::getSite() const
{
    return _site;
}

void EntityID::setSite(unsigned short pX)
{
    _site = pX;
}

unsigned short EntityID::getApplication() const
{
    return _application;
}

void EntityID::setApplication(unsigned short pX)
{
    _application = pX;
}

unsigned short EntityID::getEntity() const
{
    return _entity;
}

void EntityID::setEntity(unsigned short pX)
{
    _entity = pX;
}

void EntityID::marshal(DataStream& dataStream) const
{
    dataStream << _site;
    dataStream << _application;
    dataStream << _entity;
}

void EntityID::unmarshal(DataStream& dataStream)
{
    dataStream >> _site;
    dataStream >> _application;
    dataStream >> _entity;
}


bool EntityID::operator ==(const EntityID& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_site == rhs._site) ) ivarsEqual = false;
     if( ! (_application == rhs._application) ) ivarsEqual = false;
     if( ! (_entity == rhs._entity) ) ivarsEqual = false;

    return ivarsEqual;
 }

int EntityID::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 2;  // _site
   marshalSize = marshalSize + 2;  // _application
   marshalSize = marshalSize + 2;  // _entity
    return marshalSize;
}

