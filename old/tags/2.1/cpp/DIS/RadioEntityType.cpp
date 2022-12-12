#include <DIS/RadioEntityType.h> 

using namespace DIS;


RadioEntityType::RadioEntityType():
   _entityKind(0), 
   _domain(0), 
   _country(0), 
   _category(0), 
   _subcategory(0), 
   _nomenclatureVersion(0), 
   _nomenclature(0)
{
}

RadioEntityType::~RadioEntityType()
{
}

unsigned char RadioEntityType::getEntityKind() const
{
    return _entityKind;
}

void RadioEntityType::setEntityKind(unsigned char pX)
{
    _entityKind = pX;
}

unsigned char RadioEntityType::getDomain() const
{
    return _domain;
}

void RadioEntityType::setDomain(unsigned char pX)
{
    _domain = pX;
}

unsigned short RadioEntityType::getCountry() const
{
    return _country;
}

void RadioEntityType::setCountry(unsigned short pX)
{
    _country = pX;
}

unsigned char RadioEntityType::getCategory() const
{
    return _category;
}

void RadioEntityType::setCategory(unsigned char pX)
{
    _category = pX;
}

unsigned char RadioEntityType::getSubcategory() const
{
    return _subcategory;
}

void RadioEntityType::setSubcategory(unsigned char pX)
{
    _subcategory = pX;
}

unsigned char RadioEntityType::getNomenclatureVersion() const
{
    return _nomenclatureVersion;
}

void RadioEntityType::setNomenclatureVersion(unsigned char pX)
{
    _nomenclatureVersion = pX;
}

unsigned short RadioEntityType::getNomenclature() const
{
    return _nomenclature;
}

void RadioEntityType::setNomenclature(unsigned short pX)
{
    _nomenclature = pX;
}

void RadioEntityType::marshal(DataStream& dataStream) const
{
    dataStream << _entityKind;
    dataStream << _domain;
    dataStream << _country;
    dataStream << _category;
    dataStream << _subcategory;
    dataStream << _nomenclatureVersion;
    dataStream << _nomenclature;
}

void RadioEntityType::unmarshal(DataStream& dataStream)
{
    dataStream >> _entityKind;
    dataStream >> _domain;
    dataStream >> _country;
    dataStream >> _category;
    dataStream >> _subcategory;
    dataStream >> _nomenclatureVersion;
    dataStream >> _nomenclature;
}


bool RadioEntityType::operator ==(const RadioEntityType& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_entityKind == rhs._entityKind) ) ivarsEqual = false;
     if( ! (_domain == rhs._domain) ) ivarsEqual = false;
     if( ! (_country == rhs._country) ) ivarsEqual = false;
     if( ! (_category == rhs._category) ) ivarsEqual = false;
     if( ! (_subcategory == rhs._subcategory) ) ivarsEqual = false;
     if( ! (_nomenclatureVersion == rhs._nomenclatureVersion) ) ivarsEqual = false;
     if( ! (_nomenclature == rhs._nomenclature) ) ivarsEqual = false;

    return ivarsEqual;
 }

int RadioEntityType::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 1;  // _entityKind
   marshalSize = marshalSize + 1;  // _domain
   marshalSize = marshalSize + 2;  // _country
   marshalSize = marshalSize + 1;  // _category
   marshalSize = marshalSize + 1;  // _subcategory
   marshalSize = marshalSize + 1;  // _nomenclatureVersion
   marshalSize = marshalSize + 2;  // _nomenclature
    return marshalSize;
}

