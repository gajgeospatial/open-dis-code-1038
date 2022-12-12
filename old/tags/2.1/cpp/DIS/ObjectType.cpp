#include <DIS/ObjectType.h> 

using namespace DIS;


ObjectType::ObjectType():
   _entityKind(0), 
   _domain(0), 
   _country(0), 
   _category(0), 
   _subcategory(0)
{
}

ObjectType::~ObjectType()
{
}

unsigned char ObjectType::getEntityKind() const
{
    return _entityKind;
}

void ObjectType::setEntityKind(unsigned char pX)
{
    _entityKind = pX;
}

unsigned char ObjectType::getDomain() const
{
    return _domain;
}

void ObjectType::setDomain(unsigned char pX)
{
    _domain = pX;
}

unsigned short ObjectType::getCountry() const
{
    return _country;
}

void ObjectType::setCountry(unsigned short pX)
{
    _country = pX;
}

unsigned char ObjectType::getCategory() const
{
    return _category;
}

void ObjectType::setCategory(unsigned char pX)
{
    _category = pX;
}

unsigned char ObjectType::getSubcategory() const
{
    return _subcategory;
}

void ObjectType::setSubcategory(unsigned char pX)
{
    _subcategory = pX;
}

void ObjectType::marshal(DataStream& dataStream) const
{
    dataStream << _entityKind;
    dataStream << _domain;
    dataStream << _country;
    dataStream << _category;
    dataStream << _subcategory;
}

void ObjectType::unmarshal(DataStream& dataStream)
{
    dataStream >> _entityKind;
    dataStream >> _domain;
    dataStream >> _country;
    dataStream >> _category;
    dataStream >> _subcategory;
}


bool ObjectType::operator ==(const ObjectType& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_entityKind == rhs._entityKind) ) ivarsEqual = false;
     if( ! (_domain == rhs._domain) ) ivarsEqual = false;
     if( ! (_country == rhs._country) ) ivarsEqual = false;
     if( ! (_category == rhs._category) ) ivarsEqual = false;
     if( ! (_subcategory == rhs._subcategory) ) ivarsEqual = false;

    return ivarsEqual;
 }

int ObjectType::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 1;  // _entityKind
   marshalSize = marshalSize + 1;  // _domain
   marshalSize = marshalSize + 2;  // _country
   marshalSize = marshalSize + 1;  // _category
   marshalSize = marshalSize + 1;  // _subcategory
    return marshalSize;
}

