#include <DIS/BeamData.h> 

using namespace DIS;


BeamData::BeamData():
   _beamAzimuthCenter(0.0), 
   _beamAzimuthSweep(0.0), 
   _beamElevationCenter(0.0), 
   _beamElevationSweep(0.0), 
   _beamSweepSync(0.0)
{
}

BeamData::~BeamData()
{
}

float BeamData::getBeamAzimuthCenter() const
{
    return _beamAzimuthCenter;
}

void BeamData::setBeamAzimuthCenter(float pX)
{
    _beamAzimuthCenter = pX;
}

float BeamData::getBeamAzimuthSweep() const
{
    return _beamAzimuthSweep;
}

void BeamData::setBeamAzimuthSweep(float pX)
{
    _beamAzimuthSweep = pX;
}

float BeamData::getBeamElevationCenter() const
{
    return _beamElevationCenter;
}

void BeamData::setBeamElevationCenter(float pX)
{
    _beamElevationCenter = pX;
}

float BeamData::getBeamElevationSweep() const
{
    return _beamElevationSweep;
}

void BeamData::setBeamElevationSweep(float pX)
{
    _beamElevationSweep = pX;
}

float BeamData::getBeamSweepSync() const
{
    return _beamSweepSync;
}

void BeamData::setBeamSweepSync(float pX)
{
    _beamSweepSync = pX;
}

void BeamData::marshal(DataStream& dataStream) const
{
    dataStream << _beamAzimuthCenter;
    dataStream << _beamAzimuthSweep;
    dataStream << _beamElevationCenter;
    dataStream << _beamElevationSweep;
    dataStream << _beamSweepSync;
}

void BeamData::unmarshal(DataStream& dataStream)
{
    dataStream >> _beamAzimuthCenter;
    dataStream >> _beamAzimuthSweep;
    dataStream >> _beamElevationCenter;
    dataStream >> _beamElevationSweep;
    dataStream >> _beamSweepSync;
}


bool BeamData::operator ==(const BeamData& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_beamAzimuthCenter == rhs._beamAzimuthCenter) ) ivarsEqual = false;
     if( ! (_beamAzimuthSweep == rhs._beamAzimuthSweep) ) ivarsEqual = false;
     if( ! (_beamElevationCenter == rhs._beamElevationCenter) ) ivarsEqual = false;
     if( ! (_beamElevationSweep == rhs._beamElevationSweep) ) ivarsEqual = false;
     if( ! (_beamSweepSync == rhs._beamSweepSync) ) ivarsEqual = false;

    return ivarsEqual;
 }

int BeamData::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 4;  // _beamAzimuthCenter
   marshalSize = marshalSize + 4;  // _beamAzimuthSweep
   marshalSize = marshalSize + 4;  // _beamElevationCenter
   marshalSize = marshalSize + 4;  // _beamElevationSweep
   marshalSize = marshalSize + 4;  // _beamSweepSync
    return marshalSize;
}

