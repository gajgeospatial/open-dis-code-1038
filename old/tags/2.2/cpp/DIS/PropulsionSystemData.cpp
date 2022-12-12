#include <DIS/PropulsionSystemData.h> 

using namespace DIS;


PropulsionSystemData::PropulsionSystemData():
   _powerSetting(0.0), 
   _engineRpm(0.0)
{
}

PropulsionSystemData::~PropulsionSystemData()
{
}

float PropulsionSystemData::getPowerSetting() const
{
    return _powerSetting;
}

void PropulsionSystemData::setPowerSetting(float pX)
{
    _powerSetting = pX;
}

float PropulsionSystemData::getEngineRpm() const
{
    return _engineRpm;
}

void PropulsionSystemData::setEngineRpm(float pX)
{
    _engineRpm = pX;
}

void PropulsionSystemData::marshal(DataStream& dataStream) const
{
    dataStream << _powerSetting;
    dataStream << _engineRpm;
}

void PropulsionSystemData::unmarshal(DataStream& dataStream)
{
    dataStream >> _powerSetting;
    dataStream >> _engineRpm;
}


bool PropulsionSystemData::operator ==(const PropulsionSystemData& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_powerSetting == rhs._powerSetting) ) ivarsEqual = false;
     if( ! (_engineRpm == rhs._engineRpm) ) ivarsEqual = false;

    return ivarsEqual;
 }

int PropulsionSystemData::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 4;  // _powerSetting
   marshalSize = marshalSize + 4;  // _engineRpm
    return marshalSize;
}

