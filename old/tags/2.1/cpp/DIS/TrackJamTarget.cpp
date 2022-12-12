#include <DIS/TrackJamTarget.h> 

using namespace DIS;


TrackJamTarget::TrackJamTarget():
   _trackJam(), 
   _emitterID(0), 
   _beamID(0)
{
}

TrackJamTarget::~TrackJamTarget()
{
}

EntityID& TrackJamTarget::getTrackJam() 
{
    return _trackJam;
}

const EntityID& TrackJamTarget::getTrackJam() const
{
    return _trackJam;
}

void TrackJamTarget::setTrackJam(const EntityID &pX)
{
    _trackJam = pX;
}

unsigned char TrackJamTarget::getEmitterID() const
{
    return _emitterID;
}

void TrackJamTarget::setEmitterID(unsigned char pX)
{
    _emitterID = pX;
}

unsigned char TrackJamTarget::getBeamID() const
{
    return _beamID;
}

void TrackJamTarget::setBeamID(unsigned char pX)
{
    _beamID = pX;
}

void TrackJamTarget::marshal(DataStream& dataStream) const
{
    _trackJam.marshal(dataStream);
    dataStream << _emitterID;
    dataStream << _beamID;
}

void TrackJamTarget::unmarshal(DataStream& dataStream)
{
    _trackJam.unmarshal(dataStream);
    dataStream >> _emitterID;
    dataStream >> _beamID;
}


bool TrackJamTarget::operator ==(const TrackJamTarget& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_trackJam == rhs._trackJam) ) ivarsEqual = false;
     if( ! (_emitterID == rhs._emitterID) ) ivarsEqual = false;
     if( ! (_beamID == rhs._beamID) ) ivarsEqual = false;

    return ivarsEqual;
 }

int TrackJamTarget::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + _trackJam.getMarshalledSize();  // _trackJam
   marshalSize = marshalSize + 1;  // _emitterID
   marshalSize = marshalSize + 1;  // _beamID
    return marshalSize;
}

