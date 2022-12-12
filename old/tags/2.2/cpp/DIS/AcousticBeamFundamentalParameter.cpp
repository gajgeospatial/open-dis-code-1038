#include <DIS/AcousticBeamFundamentalParameter.h> 

using namespace DIS;


AcousticBeamFundamentalParameter::AcousticBeamFundamentalParameter():
   _activeEmissionParameterIndex(0), 
   _scanPattern(0), 
   _beamCenterAzimuth(0.0), 
   _azimuthalBeamwidth(0.0), 
   _beamCenterDE(0.0), 
   _deBeamwidth(0.0)
{
}

AcousticBeamFundamentalParameter::~AcousticBeamFundamentalParameter()
{
}

unsigned short AcousticBeamFundamentalParameter::getActiveEmissionParameterIndex() const
{
    return _activeEmissionParameterIndex;
}

void AcousticBeamFundamentalParameter::setActiveEmissionParameterIndex(unsigned short pX)
{
    _activeEmissionParameterIndex = pX;
}

unsigned short AcousticBeamFundamentalParameter::getScanPattern() const
{
    return _scanPattern;
}

void AcousticBeamFundamentalParameter::setScanPattern(unsigned short pX)
{
    _scanPattern = pX;
}

float AcousticBeamFundamentalParameter::getBeamCenterAzimuth() const
{
    return _beamCenterAzimuth;
}

void AcousticBeamFundamentalParameter::setBeamCenterAzimuth(float pX)
{
    _beamCenterAzimuth = pX;
}

float AcousticBeamFundamentalParameter::getAzimuthalBeamwidth() const
{
    return _azimuthalBeamwidth;
}

void AcousticBeamFundamentalParameter::setAzimuthalBeamwidth(float pX)
{
    _azimuthalBeamwidth = pX;
}

float AcousticBeamFundamentalParameter::getBeamCenterDE() const
{
    return _beamCenterDE;
}

void AcousticBeamFundamentalParameter::setBeamCenterDE(float pX)
{
    _beamCenterDE = pX;
}

float AcousticBeamFundamentalParameter::getDeBeamwidth() const
{
    return _deBeamwidth;
}

void AcousticBeamFundamentalParameter::setDeBeamwidth(float pX)
{
    _deBeamwidth = pX;
}

void AcousticBeamFundamentalParameter::marshal(DataStream& dataStream) const
{
    dataStream << _activeEmissionParameterIndex;
    dataStream << _scanPattern;
    dataStream << _beamCenterAzimuth;
    dataStream << _azimuthalBeamwidth;
    dataStream << _beamCenterDE;
    dataStream << _deBeamwidth;
}

void AcousticBeamFundamentalParameter::unmarshal(DataStream& dataStream)
{
    dataStream >> _activeEmissionParameterIndex;
    dataStream >> _scanPattern;
    dataStream >> _beamCenterAzimuth;
    dataStream >> _azimuthalBeamwidth;
    dataStream >> _beamCenterDE;
    dataStream >> _deBeamwidth;
}


bool AcousticBeamFundamentalParameter::operator ==(const AcousticBeamFundamentalParameter& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_activeEmissionParameterIndex == rhs._activeEmissionParameterIndex) ) ivarsEqual = false;
     if( ! (_scanPattern == rhs._scanPattern) ) ivarsEqual = false;
     if( ! (_beamCenterAzimuth == rhs._beamCenterAzimuth) ) ivarsEqual = false;
     if( ! (_azimuthalBeamwidth == rhs._azimuthalBeamwidth) ) ivarsEqual = false;
     if( ! (_beamCenterDE == rhs._beamCenterDE) ) ivarsEqual = false;
     if( ! (_deBeamwidth == rhs._deBeamwidth) ) ivarsEqual = false;

    return ivarsEqual;
 }

int AcousticBeamFundamentalParameter::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 2;  // _activeEmissionParameterIndex
   marshalSize = marshalSize + 2;  // _scanPattern
   marshalSize = marshalSize + 4;  // _beamCenterAzimuth
   marshalSize = marshalSize + 4;  // _azimuthalBeamwidth
   marshalSize = marshalSize + 4;  // _beamCenterDE
   marshalSize = marshalSize + 4;  // _deBeamwidth
    return marshalSize;
}

