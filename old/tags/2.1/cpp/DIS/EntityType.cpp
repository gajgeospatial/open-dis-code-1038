#include <DIS/EntityType.h> 

using namespace DIS;


EntityType::EntityType():
   _entityKind(0), 
   _domain(0), 
   _country(0), 
   _category(0), 
   _subcategory(0), 
   _specific(0), 
   _extra(0)
{
}

EntityType::~EntityType()
{
}

unsigned char EntityType::getEntityKind() const
{
    return _entityKind;
}

void EntityType::setEntityKind(unsigned char pX)
{
    _entityKind = pX;
}

unsigned char EntityType::getDomain() const
{
    return _domain;
}

void EntityType::setDomain(unsigned char pX)
{
    _domain = pX;
}

unsigned short EntityType::getCountry() const
{
    return _country;
}

void EntityType::setCountry(unsigned short pX)
{
    _country = pX;
}

unsigned char EntityType::getCategory() const
{
    return _category;
}

void EntityType::setCategory(unsigned char pX)
{
    _category = pX;
}

unsigned char EntityType::getSubcategory() const
{
    return _subcategory;
}

void EntityType::setSubcategory(unsigned char pX)
{
    _subcategory = pX;
}

unsigned char EntityType::getSpecific() const
{
    return _specific;
}

void EntityType::setSpecific(unsigned char pX)
{
    _specific = pX;
}

unsigned char EntityType::getExtra() const
{
    return _extra;
}

void EntityType::setExtra(unsigned char pX)
{
    _extra = pX;
}

void EntityType::marshal(DataStream& dataStream) const
{
    dataStream << _entityKind;
    dataStream << _domain;
    dataStream << _country;
    dataStream << _category;
    dataStream << _subcategory;
    dataStream << _specific;
    dataStream << _extra;
}

void EntityType::unmarshal(DataStream& dataStream)
{
    dataStream >> _entityKind;
    dataStream >> _domain;
    dataStream >> _country;
    dataStream >> _category;
    dataStream >> _subcategory;
    dataStream >> _specific;
    dataStream >> _extra;
}


bool EntityType::operator ==(const EntityType& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_entityKind == rhs._entityKind) ) ivarsEqual = false;
     if( ! (_domain == rhs._domain) ) ivarsEqual = false;
     if( ! (_country == rhs._country) ) ivarsEqual = false;
     if( ! (_category == rhs._category) ) ivarsEqual = false;
     if( ! (_subcategory == rhs._subcategory) ) ivarsEqual = false;
     if( ! (_specific == rhs._specific) ) ivarsEqual = false;
     if( ! (_extra == rhs._extra) ) ivarsEqual = false;

    return ivarsEqual;
 }

int EntityType::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 1;  // _entityKind
   marshalSize = marshalSize + 1;  // _domain
   marshalSize = marshalSize + 2;  // _country
   marshalSize = marshalSize + 1;  // _category
   marshalSize = marshalSize + 1;  // _subcategory
   marshalSize = marshalSize + 1;  // _specific
   marshalSize = marshalSize + 1;  // _extra
    return marshalSize;
}

