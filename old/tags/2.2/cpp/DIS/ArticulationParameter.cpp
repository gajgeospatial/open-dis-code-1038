#include <DIS/ArticulationParameter.h> 

using namespace DIS;


ArticulationParameter::ArticulationParameter():
   _parameterTypeDesignator(0), 
   _changeIndicator(0), 
   _partAttachedTo(0), 
   _parameterType(0), 
   _parameterValue(0.0)
{
}

ArticulationParameter::~ArticulationParameter()
{
}

unsigned char ArticulationParameter::getParameterTypeDesignator() const
{
    return _parameterTypeDesignator;
}

void ArticulationParameter::setParameterTypeDesignator(unsigned char pX)
{
    _parameterTypeDesignator = pX;
}

unsigned char ArticulationParameter::getChangeIndicator() const
{
    return _changeIndicator;
}

void ArticulationParameter::setChangeIndicator(unsigned char pX)
{
    _changeIndicator = pX;
}

unsigned short ArticulationParameter::getPartAttachedTo() const
{
    return _partAttachedTo;
}

void ArticulationParameter::setPartAttachedTo(unsigned short pX)
{
    _partAttachedTo = pX;
}

int ArticulationParameter::getParameterType() const
{
    return _parameterType;
}

void ArticulationParameter::setParameterType(int pX)
{
    _parameterType = pX;
}

double ArticulationParameter::getParameterValue() const
{
    return _parameterValue;
}

void ArticulationParameter::setParameterValue(double pX)
{
    _parameterValue = pX;
}

void ArticulationParameter::marshal(DataStream& dataStream) const
{
    dataStream << _parameterTypeDesignator;
    dataStream << _changeIndicator;
    dataStream << _partAttachedTo;
    dataStream << _parameterType;
    dataStream << _parameterValue;
}

void ArticulationParameter::unmarshal(DataStream& dataStream)
{
    dataStream >> _parameterTypeDesignator;
    dataStream >> _changeIndicator;
    dataStream >> _partAttachedTo;
    dataStream >> _parameterType;
    dataStream >> _parameterValue;
}


bool ArticulationParameter::operator ==(const ArticulationParameter& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_parameterTypeDesignator == rhs._parameterTypeDesignator) ) ivarsEqual = false;
     if( ! (_changeIndicator == rhs._changeIndicator) ) ivarsEqual = false;
     if( ! (_partAttachedTo == rhs._partAttachedTo) ) ivarsEqual = false;
     if( ! (_parameterType == rhs._parameterType) ) ivarsEqual = false;
     if( ! (_parameterValue == rhs._parameterValue) ) ivarsEqual = false;

    return ivarsEqual;
 }

int ArticulationParameter::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 1;  // _parameterTypeDesignator
   marshalSize = marshalSize + 1;  // _changeIndicator
   marshalSize = marshalSize + 2;  // _partAttachedTo
   marshalSize = marshalSize + 4;  // _parameterType
   marshalSize = marshalSize + 8;  // _parameterValue
    return marshalSize;
}

