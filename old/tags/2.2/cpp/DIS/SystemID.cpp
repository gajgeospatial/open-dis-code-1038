#include <DIS/SystemID.h> 

using namespace DIS;


SystemID::SystemID():
   _systemType(0), 
   _systemName(0), 
   _systemMode(0), 
   _changeOptions(0)
{
}

SystemID::~SystemID()
{
}

unsigned short SystemID::getSystemType() const
{
    return _systemType;
}

void SystemID::setSystemType(unsigned short pX)
{
    _systemType = pX;
}

unsigned short SystemID::getSystemName() const
{
    return _systemName;
}

void SystemID::setSystemName(unsigned short pX)
{
    _systemName = pX;
}

unsigned char SystemID::getSystemMode() const
{
    return _systemMode;
}

void SystemID::setSystemMode(unsigned char pX)
{
    _systemMode = pX;
}

unsigned char SystemID::getChangeOptions() const
{
    return _changeOptions;
}

void SystemID::setChangeOptions(unsigned char pX)
{
    _changeOptions = pX;
}

void SystemID::marshal(DataStream& dataStream) const
{
    dataStream << _systemType;
    dataStream << _systemName;
    dataStream << _systemMode;
    dataStream << _changeOptions;
}

void SystemID::unmarshal(DataStream& dataStream)
{
    dataStream >> _systemType;
    dataStream >> _systemName;
    dataStream >> _systemMode;
    dataStream >> _changeOptions;
}


bool SystemID::operator ==(const SystemID& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_systemType == rhs._systemType) ) ivarsEqual = false;
     if( ! (_systemName == rhs._systemName) ) ivarsEqual = false;
     if( ! (_systemMode == rhs._systemMode) ) ivarsEqual = false;
     if( ! (_changeOptions == rhs._changeOptions) ) ivarsEqual = false;

    return ivarsEqual;
 }

int SystemID::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 2;  // _systemType
   marshalSize = marshalSize + 2;  // _systemName
   marshalSize = marshalSize + 1;  // _systemMode
   marshalSize = marshalSize + 1;  // _changeOptions
    return marshalSize;
}

