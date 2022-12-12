#include <DIS/FundamentalParameterData.h> 

using namespace DIS;


FundamentalParameterData::FundamentalParameterData():
   _frequency(0.0), 
   _frequencyRange(0.0), 
   _effectiveRadiatedPower(0.0), 
   _pulseRepetitionFrequency(0.0), 
   _pulseWidth(0.0), 
   _beamAzimuthCenter(0.0), 
   _beamAzimuthSweep(0.0), 
   _beamElevationCenter(0.0), 
   _beamElevationSweep(0.0), 
   _beamSweepSync(0.0)
{
}

FundamentalParameterData::~FundamentalParameterData()
{
}

float FundamentalParameterData::getFrequency() const
{
    return _frequency;
}

void FundamentalParameterData::setFrequency(float pX)
{
    _frequency = pX;
}

float FundamentalParameterData::getFrequencyRange() const
{
    return _frequencyRange;
}

void FundamentalParameterData::setFrequencyRange(float pX)
{
    _frequencyRange = pX;
}

float FundamentalParameterData::getEffectiveRadiatedPower() const
{
    return _effectiveRadiatedPower;
}

void FundamentalParameterData::setEffectiveRadiatedPower(float pX)
{
    _effectiveRadiatedPower = pX;
}

float FundamentalParameterData::getPulseRepetitionFrequency() const
{
    return _pulseRepetitionFrequency;
}

void FundamentalParameterData::setPulseRepetitionFrequency(float pX)
{
    _pulseRepetitionFrequency = pX;
}

float FundamentalParameterData::getPulseWidth() const
{
    return _pulseWidth;
}

void FundamentalParameterData::setPulseWidth(float pX)
{
    _pulseWidth = pX;
}

float FundamentalParameterData::getBeamAzimuthCenter() const
{
    return _beamAzimuthCenter;
}

void FundamentalParameterData::setBeamAzimuthCenter(float pX)
{
    _beamAzimuthCenter = pX;
}

float FundamentalParameterData::getBeamAzimuthSweep() const
{
    return _beamAzimuthSweep;
}

void FundamentalParameterData::setBeamAzimuthSweep(float pX)
{
    _beamAzimuthSweep = pX;
}

float FundamentalParameterData::getBeamElevationCenter() const
{
    return _beamElevationCenter;
}

void FundamentalParameterData::setBeamElevationCenter(float pX)
{
    _beamElevationCenter = pX;
}

float FundamentalParameterData::getBeamElevationSweep() const
{
    return _beamElevationSweep;
}

void FundamentalParameterData::setBeamElevationSweep(float pX)
{
    _beamElevationSweep = pX;
}

float FundamentalParameterData::getBeamSweepSync() const
{
    return _beamSweepSync;
}

void FundamentalParameterData::setBeamSweepSync(float pX)
{
    _beamSweepSync = pX;
}

void FundamentalParameterData::marshal(DataStream& dataStream) const
{
    dataStream << _frequency;
    dataStream << _frequencyRange;
    dataStream << _effectiveRadiatedPower;
    dataStream << _pulseRepetitionFrequency;
    dataStream << _pulseWidth;
    dataStream << _beamAzimuthCenter;
    dataStream << _beamAzimuthSweep;
    dataStream << _beamElevationCenter;
    dataStream << _beamElevationSweep;
    dataStream << _beamSweepSync;
}

void FundamentalParameterData::unmarshal(DataStream& dataStream)
{
    dataStream >> _frequency;
    dataStream >> _frequencyRange;
    dataStream >> _effectiveRadiatedPower;
    dataStream >> _pulseRepetitionFrequency;
    dataStream >> _pulseWidth;
    dataStream >> _beamAzimuthCenter;
    dataStream >> _beamAzimuthSweep;
    dataStream >> _beamElevationCenter;
    dataStream >> _beamElevationSweep;
    dataStream >> _beamSweepSync;
}


bool FundamentalParameterData::operator ==(const FundamentalParameterData& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_frequency == rhs._frequency) ) ivarsEqual = false;
     if( ! (_frequencyRange == rhs._frequencyRange) ) ivarsEqual = false;
     if( ! (_effectiveRadiatedPower == rhs._effectiveRadiatedPower) ) ivarsEqual = false;
     if( ! (_pulseRepetitionFrequency == rhs._pulseRepetitionFrequency) ) ivarsEqual = false;
     if( ! (_pulseWidth == rhs._pulseWidth) ) ivarsEqual = false;
     if( ! (_beamAzimuthCenter == rhs._beamAzimuthCenter) ) ivarsEqual = false;
     if( ! (_beamAzimuthSweep == rhs._beamAzimuthSweep) ) ivarsEqual = false;
     if( ! (_beamElevationCenter == rhs._beamElevationCenter) ) ivarsEqual = false;
     if( ! (_beamElevationSweep == rhs._beamElevationSweep) ) ivarsEqual = false;
     if( ! (_beamSweepSync == rhs._beamSweepSync) ) ivarsEqual = false;

    return ivarsEqual;
 }

int FundamentalParameterData::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 4;  // _frequency
   marshalSize = marshalSize + 4;  // _frequencyRange
   marshalSize = marshalSize + 4;  // _effectiveRadiatedPower
   marshalSize = marshalSize + 4;  // _pulseRepetitionFrequency
   marshalSize = marshalSize + 4;  // _pulseWidth
   marshalSize = marshalSize + 4;  // _beamAzimuthCenter
   marshalSize = marshalSize + 4;  // _beamAzimuthSweep
   marshalSize = marshalSize + 4;  // _beamElevationCenter
   marshalSize = marshalSize + 4;  // _beamElevationSweep
   marshalSize = marshalSize + 4;  // _beamSweepSync
    return marshalSize;
}

