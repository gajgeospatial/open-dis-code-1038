#include <DIS/ElectronicEmissionSystemData.h> 

using namespace DIS;


ElectronicEmissionSystemData::ElectronicEmissionSystemData():
   _systemDataLength(0), 
   _numberOfBeams(0), 
   _emissionsPadding2(0), 
   _emitterSystem(), 
   _location()
{
}

ElectronicEmissionSystemData::~ElectronicEmissionSystemData()
{
    _beamDataRecords.clear();
}

unsigned char ElectronicEmissionSystemData::getSystemDataLength() const
{
    return _systemDataLength;
}

void ElectronicEmissionSystemData::setSystemDataLength(unsigned char pX)
{
    _systemDataLength = pX;
}

unsigned char ElectronicEmissionSystemData::getNumberOfBeams() const
{
   return _beamDataRecords.size();
}

unsigned short ElectronicEmissionSystemData::getEmissionsPadding2() const
{
    return _emissionsPadding2;
}

void ElectronicEmissionSystemData::setEmissionsPadding2(unsigned short pX)
{
    _emissionsPadding2 = pX;
}

EmitterSystem& ElectronicEmissionSystemData::getEmitterSystem() 
{
    return _emitterSystem;
}

const EmitterSystem& ElectronicEmissionSystemData::getEmitterSystem() const
{
    return _emitterSystem;
}

void ElectronicEmissionSystemData::setEmitterSystem(const EmitterSystem &pX)
{
    _emitterSystem = pX;
}

Vector3Float& ElectronicEmissionSystemData::getLocation() 
{
    return _location;
}

const Vector3Float& ElectronicEmissionSystemData::getLocation() const
{
    return _location;
}

void ElectronicEmissionSystemData::setLocation(const Vector3Float &pX)
{
    _location = pX;
}

std::vector<ElectronicEmissionBeamData>& ElectronicEmissionSystemData::getBeamDataRecords() 
{
    return _beamDataRecords;
}

const std::vector<ElectronicEmissionBeamData>& ElectronicEmissionSystemData::getBeamDataRecords() const
{
    return _beamDataRecords;
}

void ElectronicEmissionSystemData::setBeamDataRecords(const std::vector<ElectronicEmissionBeamData>& pX)
{
     _beamDataRecords = pX;
}

void ElectronicEmissionSystemData::marshal(DataStream& dataStream) const
{
    dataStream << _systemDataLength;
    dataStream << ( unsigned char )_beamDataRecords.size();
    dataStream << _emissionsPadding2;
    _emitterSystem.marshal(dataStream);
    _location.marshal(dataStream);

     for(size_t idx = 0; idx < _beamDataRecords.size(); idx++)
     {
        ElectronicEmissionBeamData x = _beamDataRecords[idx];
        x.marshal(dataStream);
     }

}

void ElectronicEmissionSystemData::unmarshal(DataStream& dataStream)
{
    dataStream >> _systemDataLength;
    dataStream >> _numberOfBeams;
    dataStream >> _emissionsPadding2;
    _emitterSystem.unmarshal(dataStream);
    _location.unmarshal(dataStream);

     _beamDataRecords.clear();
     for(size_t idx = 0; idx < _numberOfBeams; idx++)
     {
        ElectronicEmissionBeamData x;
        x.unmarshal(dataStream);
        _beamDataRecords.push_back(x);
     }
}


bool ElectronicEmissionSystemData::operator ==(const ElectronicEmissionSystemData& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_systemDataLength == rhs._systemDataLength) ) ivarsEqual = false;
     if( ! (_emissionsPadding2 == rhs._emissionsPadding2) ) ivarsEqual = false;
     if( ! (_emitterSystem == rhs._emitterSystem) ) ivarsEqual = false;
     if( ! (_location == rhs._location) ) ivarsEqual = false;

     for(size_t idx = 0; idx < _beamDataRecords.size(); idx++)
     {
        if( ! ( _beamDataRecords[idx] == rhs._beamDataRecords[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int ElectronicEmissionSystemData::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 1;  // _systemDataLength
   marshalSize = marshalSize + 1;  // _numberOfBeams
   marshalSize = marshalSize + 2;  // _emissionsPadding2
   marshalSize = marshalSize + _emitterSystem.getMarshalledSize();  // _emitterSystem
   marshalSize = marshalSize + _location.getMarshalledSize();  // _location

   for(int idx=0; idx < _beamDataRecords.size(); idx++)
   {
        ElectronicEmissionBeamData listElement = _beamDataRecords[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

