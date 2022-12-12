#include <DIS/FundamentalParameterDataIff.h> 

using namespace DIS;


FundamentalParameterDataIff::FundamentalParameterDataIff():
   _erp(0.0), 
   _frequency(0.0), 
   _pgrf(0.0), 
   _pulseWidth(0.0), 
   _burstLength(0), 
   _applicableModes(0), 
   _pad2(0), 
   _pad3(0)
{
}

FundamentalParameterDataIff::~FundamentalParameterDataIff()
{
}

float FundamentalParameterDataIff::getErp() const
{
    return _erp;
}

void FundamentalParameterDataIff::setErp(float pX)
{
    _erp = pX;
}

float FundamentalParameterDataIff::getFrequency() const
{
    return _frequency;
}

void FundamentalParameterDataIff::setFrequency(float pX)
{
    _frequency = pX;
}

float FundamentalParameterDataIff::getPgrf() const
{
    return _pgrf;
}

void FundamentalParameterDataIff::setPgrf(float pX)
{
    _pgrf = pX;
}

float FundamentalParameterDataIff::getPulseWidth() const
{
    return _pulseWidth;
}

void FundamentalParameterDataIff::setPulseWidth(float pX)
{
    _pulseWidth = pX;
}

unsigned int FundamentalParameterDataIff::getBurstLength() const
{
    return _burstLength;
}

void FundamentalParameterDataIff::setBurstLength(unsigned int pX)
{
    _burstLength = pX;
}

unsigned char FundamentalParameterDataIff::getApplicableModes() const
{
    return _applicableModes;
}

void FundamentalParameterDataIff::setApplicableModes(unsigned char pX)
{
    _applicableModes = pX;
}

unsigned short FundamentalParameterDataIff::getPad2() const
{
    return _pad2;
}

void FundamentalParameterDataIff::setPad2(unsigned short pX)
{
    _pad2 = pX;
}

unsigned char FundamentalParameterDataIff::getPad3() const
{
    return _pad3;
}

void FundamentalParameterDataIff::setPad3(unsigned char pX)
{
    _pad3 = pX;
}

void FundamentalParameterDataIff::marshal(DataStream& dataStream) const
{
    dataStream << _erp;
    dataStream << _frequency;
    dataStream << _pgrf;
    dataStream << _pulseWidth;
    dataStream << _burstLength;
    dataStream << _applicableModes;
    dataStream << _pad2;
    dataStream << _pad3;
}

void FundamentalParameterDataIff::unmarshal(DataStream& dataStream)
{
    dataStream >> _erp;
    dataStream >> _frequency;
    dataStream >> _pgrf;
    dataStream >> _pulseWidth;
    dataStream >> _burstLength;
    dataStream >> _applicableModes;
    dataStream >> _pad2;
    dataStream >> _pad3;
}


bool FundamentalParameterDataIff::operator ==(const FundamentalParameterDataIff& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_erp == rhs._erp) ) ivarsEqual = false;
     if( ! (_frequency == rhs._frequency) ) ivarsEqual = false;
     if( ! (_pgrf == rhs._pgrf) ) ivarsEqual = false;
     if( ! (_pulseWidth == rhs._pulseWidth) ) ivarsEqual = false;
     if( ! (_burstLength == rhs._burstLength) ) ivarsEqual = false;
     if( ! (_applicableModes == rhs._applicableModes) ) ivarsEqual = false;
     if( ! (_pad2 == rhs._pad2) ) ivarsEqual = false;
     if( ! (_pad3 == rhs._pad3) ) ivarsEqual = false;

    return ivarsEqual;
 }

int FundamentalParameterDataIff::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 4;  // _erp
   marshalSize = marshalSize + 4;  // _frequency
   marshalSize = marshalSize + 4;  // _pgrf
   marshalSize = marshalSize + 4;  // _pulseWidth
   marshalSize = marshalSize + 4;  // _burstLength
   marshalSize = marshalSize + 1;  // _applicableModes
   marshalSize = marshalSize + 2;  // _pad2
   marshalSize = marshalSize + 1;  // _pad3
    return marshalSize;
}

