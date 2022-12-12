#include <DIS/AggregateType.h> 

using namespace DIS;


AggregateType::AggregateType():
   _aggregateKind(0), 
   _domain(0), 
   _country(0), 
   _category(0), 
   _subcategory(0), 
   _specific(0), 
   _extra(0)
{
}

AggregateType::~AggregateType()
{
}

unsigned char AggregateType::getAggregateKind() const
{
    return _aggregateKind;
}

void AggregateType::setAggregateKind(unsigned char pX)
{
    _aggregateKind = pX;
}

unsigned char AggregateType::getDomain() const
{
    return _domain;
}

void AggregateType::setDomain(unsigned char pX)
{
    _domain = pX;
}

unsigned short AggregateType::getCountry() const
{
    return _country;
}

void AggregateType::setCountry(unsigned short pX)
{
    _country = pX;
}

unsigned char AggregateType::getCategory() const
{
    return _category;
}

void AggregateType::setCategory(unsigned char pX)
{
    _category = pX;
}

unsigned char AggregateType::getSubcategory() const
{
    return _subcategory;
}

void AggregateType::setSubcategory(unsigned char pX)
{
    _subcategory = pX;
}

unsigned char AggregateType::getSpecific() const
{
    return _specific;
}

void AggregateType::setSpecific(unsigned char pX)
{
    _specific = pX;
}

unsigned char AggregateType::getExtra() const
{
    return _extra;
}

void AggregateType::setExtra(unsigned char pX)
{
    _extra = pX;
}

void AggregateType::marshal(DataStream& dataStream) const
{
    dataStream << _aggregateKind;
    dataStream << _domain;
    dataStream << _country;
    dataStream << _category;
    dataStream << _subcategory;
    dataStream << _specific;
    dataStream << _extra;
}

void AggregateType::unmarshal(DataStream& dataStream)
{
    dataStream >> _aggregateKind;
    dataStream >> _domain;
    dataStream >> _country;
    dataStream >> _category;
    dataStream >> _subcategory;
    dataStream >> _specific;
    dataStream >> _extra;
}


bool AggregateType::operator ==(const AggregateType& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_aggregateKind == rhs._aggregateKind) ) ivarsEqual = false;
     if( ! (_domain == rhs._domain) ) ivarsEqual = false;
     if( ! (_country == rhs._country) ) ivarsEqual = false;
     if( ! (_category == rhs._category) ) ivarsEqual = false;
     if( ! (_subcategory == rhs._subcategory) ) ivarsEqual = false;
     if( ! (_specific == rhs._specific) ) ivarsEqual = false;
     if( ! (_extra == rhs._extra) ) ivarsEqual = false;

    return ivarsEqual;
 }

int AggregateType::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 1;  // _aggregateKind
   marshalSize = marshalSize + 1;  // _domain
   marshalSize = marshalSize + 2;  // _country
   marshalSize = marshalSize + 1;  // _category
   marshalSize = marshalSize + 1;  // _subcategory
   marshalSize = marshalSize + 1;  // _specific
   marshalSize = marshalSize + 1;  // _extra
    return marshalSize;
}

