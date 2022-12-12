#include <DIS/BeamAntennaPattern.h> 

using namespace DIS;


BeamAntennaPattern::BeamAntennaPattern():
   _beamDirection(), 
   _azimuthBeamwidth(0), 
   _referenceSystem(0), 
   _padding1(0), 
   _padding2(0), 
   _ez(0.0), 
   _ex(0.0), 
   _phase(0.0)
{
}

BeamAntennaPattern::~BeamAntennaPattern()
{
}

Orientation& BeamAntennaPattern::getBeamDirection() 
{
    return _beamDirection;
}

const Orientation& BeamAntennaPattern::getBeamDirection() const
{
    return _beamDirection;
}

void BeamAntennaPattern::setBeamDirection(const Orientation &pX)
{
    _beamDirection = pX;
}

float BeamAntennaPattern::getAzimuthBeamwidth() const
{
    return _azimuthBeamwidth;
}

void BeamAntennaPattern::setAzimuthBeamwidth(float pX)
{
    _azimuthBeamwidth = pX;
}

float BeamAntennaPattern::getReferenceSystem() const
{
    return _referenceSystem;
}

void BeamAntennaPattern::setReferenceSystem(float pX)
{
    _referenceSystem = pX;
}

short BeamAntennaPattern::getPadding1() const
{
    return _padding1;
}

void BeamAntennaPattern::setPadding1(short pX)
{
    _padding1 = pX;
}

char BeamAntennaPattern::getPadding2() const
{
    return _padding2;
}

void BeamAntennaPattern::setPadding2(char pX)
{
    _padding2 = pX;
}

float BeamAntennaPattern::getEz() const
{
    return _ez;
}

void BeamAntennaPattern::setEz(float pX)
{
    _ez = pX;
}

float BeamAntennaPattern::getEx() const
{
    return _ex;
}

void BeamAntennaPattern::setEx(float pX)
{
    _ex = pX;
}

float BeamAntennaPattern::getPhase() const
{
    return _phase;
}

void BeamAntennaPattern::setPhase(float pX)
{
    _phase = pX;
}

void BeamAntennaPattern::marshal(DataStream& dataStream) const
{
    _beamDirection.marshal(dataStream);
    dataStream << _azimuthBeamwidth;
    dataStream << _referenceSystem;
    dataStream << _padding1;
    dataStream << _padding2;
    dataStream << _ez;
    dataStream << _ex;
    dataStream << _phase;
}

void BeamAntennaPattern::unmarshal(DataStream& dataStream)
{
    _beamDirection.unmarshal(dataStream);
    dataStream >> _azimuthBeamwidth;
    dataStream >> _referenceSystem;
    dataStream >> _padding1;
    dataStream >> _padding2;
    dataStream >> _ez;
    dataStream >> _ex;
    dataStream >> _phase;
}


bool BeamAntennaPattern::operator ==(const BeamAntennaPattern& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_beamDirection == rhs._beamDirection) ) ivarsEqual = false;
     if( ! (_azimuthBeamwidth == rhs._azimuthBeamwidth) ) ivarsEqual = false;
     if( ! (_referenceSystem == rhs._referenceSystem) ) ivarsEqual = false;
     if( ! (_padding1 == rhs._padding1) ) ivarsEqual = false;
     if( ! (_padding2 == rhs._padding2) ) ivarsEqual = false;
     if( ! (_ez == rhs._ez) ) ivarsEqual = false;
     if( ! (_ex == rhs._ex) ) ivarsEqual = false;
     if( ! (_phase == rhs._phase) ) ivarsEqual = false;

    return ivarsEqual;
 }

int BeamAntennaPattern::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + _beamDirection.getMarshalledSize();  // _beamDirection
   marshalSize = marshalSize + 4;  // _azimuthBeamwidth
   marshalSize = marshalSize + 4;  // _referenceSystem
   marshalSize = marshalSize + 2;  // _padding1
   marshalSize = marshalSize + 1;  // _padding2
   marshalSize = marshalSize + 4;  // _ez
   marshalSize = marshalSize + 4;  // _ex
   marshalSize = marshalSize + 4;  // _phase
    return marshalSize;
}

