#include <DIS/ShaftRPMs.h> 

using namespace DIS;


ShaftRPMs::ShaftRPMs():
   _currentShaftRPMs(0), 
   _orderedShaftRPMs(0), 
   _shaftRPMRateOfChange(0.0)
{
}

ShaftRPMs::~ShaftRPMs()
{
}

short ShaftRPMs::getCurrentShaftRPMs() const
{
    return _currentShaftRPMs;
}

void ShaftRPMs::setCurrentShaftRPMs(short pX)
{
    _currentShaftRPMs = pX;
}

short ShaftRPMs::getOrderedShaftRPMs() const
{
    return _orderedShaftRPMs;
}

void ShaftRPMs::setOrderedShaftRPMs(short pX)
{
    _orderedShaftRPMs = pX;
}

float ShaftRPMs::getShaftRPMRateOfChange() const
{
    return _shaftRPMRateOfChange;
}

void ShaftRPMs::setShaftRPMRateOfChange(float pX)
{
    _shaftRPMRateOfChange = pX;
}

void ShaftRPMs::marshal(DataStream& dataStream) const
{
    dataStream << _currentShaftRPMs;
    dataStream << _orderedShaftRPMs;
    dataStream << _shaftRPMRateOfChange;
}

void ShaftRPMs::unmarshal(DataStream& dataStream)
{
    dataStream >> _currentShaftRPMs;
    dataStream >> _orderedShaftRPMs;
    dataStream >> _shaftRPMRateOfChange;
}


bool ShaftRPMs::operator ==(const ShaftRPMs& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_currentShaftRPMs == rhs._currentShaftRPMs) ) ivarsEqual = false;
     if( ! (_orderedShaftRPMs == rhs._orderedShaftRPMs) ) ivarsEqual = false;
     if( ! (_shaftRPMRateOfChange == rhs._shaftRPMRateOfChange) ) ivarsEqual = false;

    return ivarsEqual;
 }

int ShaftRPMs::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 2;  // _currentShaftRPMs
   marshalSize = marshalSize + 2;  // _orderedShaftRPMs
   marshalSize = marshalSize + 4;  // _shaftRPMRateOfChange
    return marshalSize;
}

