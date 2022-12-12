#include <DIS/Orientation.h> 

using namespace DIS;


Orientation::Orientation():
   _psi(0.0), 
   _theta(0.0), 
   _phi(0.0)
{
}

Orientation::~Orientation()
{
}

float Orientation::getPsi() const
{
    return _psi;
}

void Orientation::setPsi(float pX)
{
    _psi = pX;
}

float Orientation::getTheta() const
{
    return _theta;
}

void Orientation::setTheta(float pX)
{
    _theta = pX;
}

float Orientation::getPhi() const
{
    return _phi;
}

void Orientation::setPhi(float pX)
{
    _phi = pX;
}

void Orientation::marshal(DataStream& dataStream) const
{
    dataStream << _psi;
    dataStream << _theta;
    dataStream << _phi;
}

void Orientation::unmarshal(DataStream& dataStream)
{
    dataStream >> _psi;
    dataStream >> _theta;
    dataStream >> _phi;
}


bool Orientation::operator ==(const Orientation& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_psi == rhs._psi) ) ivarsEqual = false;
     if( ! (_theta == rhs._theta) ) ivarsEqual = false;
     if( ! (_phi == rhs._phi) ) ivarsEqual = false;

    return ivarsEqual;
 }

int Orientation::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 4;  // _psi
   marshalSize = marshalSize + 4;  // _theta
   marshalSize = marshalSize + 4;  // _phi
    return marshalSize;
}

