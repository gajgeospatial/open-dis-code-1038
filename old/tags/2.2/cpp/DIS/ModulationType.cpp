#include <DIS/ModulationType.h> 

using namespace DIS;


ModulationType::ModulationType():
   _spreadSpectrum(0), 
   _major(0), 
   _detail(0), 
   _system(0)
{
}

ModulationType::~ModulationType()
{
}

unsigned short ModulationType::getSpreadSpectrum() const
{
    return _spreadSpectrum;
}

void ModulationType::setSpreadSpectrum(unsigned short pX)
{
    _spreadSpectrum = pX;
}

unsigned short ModulationType::getMajor() const
{
    return _major;
}

void ModulationType::setMajor(unsigned short pX)
{
    _major = pX;
}

unsigned short ModulationType::getDetail() const
{
    return _detail;
}

void ModulationType::setDetail(unsigned short pX)
{
    _detail = pX;
}

unsigned short ModulationType::getSystem() const
{
    return _system;
}

void ModulationType::setSystem(unsigned short pX)
{
    _system = pX;
}

void ModulationType::marshal(DataStream& dataStream) const
{
    dataStream << _spreadSpectrum;
    dataStream << _major;
    dataStream << _detail;
    dataStream << _system;
}

void ModulationType::unmarshal(DataStream& dataStream)
{
    dataStream >> _spreadSpectrum;
    dataStream >> _major;
    dataStream >> _detail;
    dataStream >> _system;
}


bool ModulationType::operator ==(const ModulationType& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_spreadSpectrum == rhs._spreadSpectrum) ) ivarsEqual = false;
     if( ! (_major == rhs._major) ) ivarsEqual = false;
     if( ! (_detail == rhs._detail) ) ivarsEqual = false;
     if( ! (_system == rhs._system) ) ivarsEqual = false;

    return ivarsEqual;
 }

int ModulationType::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 2;  // _spreadSpectrum
   marshalSize = marshalSize + 2;  // _major
   marshalSize = marshalSize + 2;  // _detail
   marshalSize = marshalSize + 2;  // _system
    return marshalSize;
}

