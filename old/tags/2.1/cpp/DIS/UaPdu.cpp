#include <DIS/UaPdu.h> 

using namespace DIS;


UaPdu::UaPdu() : DistributedEmissionsFamilyPdu(),
   _emittingEntityID(), 
   _eventID(), 
   _stateChangeIndicator(0), 
   _pad(0), 
   _passiveParameterIndex(0), 
   _propulsionPlantConfiguration(0), 
   _numberOfShafts(0), 
   _numberOfAPAs(0), 
   _numberOfUAEmitterSystems(0)
{
    setPduType( 29 );
}

UaPdu::~UaPdu()
{
    _shaftRPMs.clear();
    _apaData.clear();
    _emitterSystems.clear();
}

EntityID& UaPdu::getEmittingEntityID() 
{
    return _emittingEntityID;
}

const EntityID& UaPdu::getEmittingEntityID() const
{
    return _emittingEntityID;
}

void UaPdu::setEmittingEntityID(const EntityID &pX)
{
    _emittingEntityID = pX;
}

EventID& UaPdu::getEventID() 
{
    return _eventID;
}

const EventID& UaPdu::getEventID() const
{
    return _eventID;
}

void UaPdu::setEventID(const EventID &pX)
{
    _eventID = pX;
}

char UaPdu::getStateChangeIndicator() const
{
    return _stateChangeIndicator;
}

void UaPdu::setStateChangeIndicator(char pX)
{
    _stateChangeIndicator = pX;
}

char UaPdu::getPad() const
{
    return _pad;
}

void UaPdu::setPad(char pX)
{
    _pad = pX;
}

unsigned short UaPdu::getPassiveParameterIndex() const
{
    return _passiveParameterIndex;
}

void UaPdu::setPassiveParameterIndex(unsigned short pX)
{
    _passiveParameterIndex = pX;
}

unsigned char UaPdu::getPropulsionPlantConfiguration() const
{
    return _propulsionPlantConfiguration;
}

void UaPdu::setPropulsionPlantConfiguration(unsigned char pX)
{
    _propulsionPlantConfiguration = pX;
}

unsigned char UaPdu::getNumberOfShafts() const
{
   return _shaftRPMs.size();
}

unsigned char UaPdu::getNumberOfAPAs() const
{
   return _apaData.size();
}

unsigned char UaPdu::getNumberOfUAEmitterSystems() const
{
   return _emitterSystems.size();
}

std::vector<ShaftRPMs>& UaPdu::getShaftRPMs() 
{
    return _shaftRPMs;
}

const std::vector<ShaftRPMs>& UaPdu::getShaftRPMs() const
{
    return _shaftRPMs;
}

void UaPdu::setShaftRPMs(const std::vector<ShaftRPMs>& pX)
{
     _shaftRPMs = pX;
}

std::vector<ApaData>& UaPdu::getApaData() 
{
    return _apaData;
}

const std::vector<ApaData>& UaPdu::getApaData() const
{
    return _apaData;
}

void UaPdu::setApaData(const std::vector<ApaData>& pX)
{
     _apaData = pX;
}

std::vector<AcousticEmitterSystemData>& UaPdu::getEmitterSystems() 
{
    return _emitterSystems;
}

const std::vector<AcousticEmitterSystemData>& UaPdu::getEmitterSystems() const
{
    return _emitterSystems;
}

void UaPdu::setEmitterSystems(const std::vector<AcousticEmitterSystemData>& pX)
{
     _emitterSystems = pX;
}

void UaPdu::marshal(DataStream& dataStream) const
{
    DistributedEmissionsFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _emittingEntityID.marshal(dataStream);
    _eventID.marshal(dataStream);
    dataStream << _stateChangeIndicator;
    dataStream << _pad;
    dataStream << _passiveParameterIndex;
    dataStream << _propulsionPlantConfiguration;
    dataStream << ( unsigned char )_shaftRPMs.size();
    dataStream << ( unsigned char )_apaData.size();
    dataStream << ( unsigned char )_emitterSystems.size();

     for(size_t idx = 0; idx < _shaftRPMs.size(); idx++)
     {
        ShaftRPMs x = _shaftRPMs[idx];
        x.marshal(dataStream);
     }


     for(size_t idx = 0; idx < _apaData.size(); idx++)
     {
        ApaData x = _apaData[idx];
        x.marshal(dataStream);
     }


     for(size_t idx = 0; idx < _emitterSystems.size(); idx++)
     {
        AcousticEmitterSystemData x = _emitterSystems[idx];
        x.marshal(dataStream);
     }

}

void UaPdu::unmarshal(DataStream& dataStream)
{
    DistributedEmissionsFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _emittingEntityID.unmarshal(dataStream);
    _eventID.unmarshal(dataStream);
    dataStream >> _stateChangeIndicator;
    dataStream >> _pad;
    dataStream >> _passiveParameterIndex;
    dataStream >> _propulsionPlantConfiguration;
    dataStream >> _numberOfShafts;
    dataStream >> _numberOfAPAs;
    dataStream >> _numberOfUAEmitterSystems;

     _shaftRPMs.clear();
     for(size_t idx = 0; idx < _numberOfShafts; idx++)
     {
        ShaftRPMs x;
        x.unmarshal(dataStream);
        _shaftRPMs.push_back(x);
     }

     _apaData.clear();
     for(size_t idx = 0; idx < _numberOfAPAs; idx++)
     {
        ApaData x;
        x.unmarshal(dataStream);
        _apaData.push_back(x);
     }

     _emitterSystems.clear();
     for(size_t idx = 0; idx < _numberOfUAEmitterSystems; idx++)
     {
        AcousticEmitterSystemData x;
        x.unmarshal(dataStream);
        _emitterSystems.push_back(x);
     }
}


bool UaPdu::operator ==(const UaPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = DistributedEmissionsFamilyPdu::operator==(rhs);

     if( ! (_emittingEntityID == rhs._emittingEntityID) ) ivarsEqual = false;
     if( ! (_eventID == rhs._eventID) ) ivarsEqual = false;
     if( ! (_stateChangeIndicator == rhs._stateChangeIndicator) ) ivarsEqual = false;
     if( ! (_pad == rhs._pad) ) ivarsEqual = false;
     if( ! (_passiveParameterIndex == rhs._passiveParameterIndex) ) ivarsEqual = false;
     if( ! (_propulsionPlantConfiguration == rhs._propulsionPlantConfiguration) ) ivarsEqual = false;

     for(size_t idx = 0; idx < _shaftRPMs.size(); idx++)
     {
        if( ! ( _shaftRPMs[idx] == rhs._shaftRPMs[idx]) ) ivarsEqual = false;
     }


     for(size_t idx = 0; idx < _apaData.size(); idx++)
     {
        if( ! ( _apaData[idx] == rhs._apaData[idx]) ) ivarsEqual = false;
     }


     for(size_t idx = 0; idx < _emitterSystems.size(); idx++)
     {
        if( ! ( _emitterSystems[idx] == rhs._emitterSystems[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int UaPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = DistributedEmissionsFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _emittingEntityID.getMarshalledSize();  // _emittingEntityID
   marshalSize = marshalSize + _eventID.getMarshalledSize();  // _eventID
   marshalSize = marshalSize + 1;  // _stateChangeIndicator
   marshalSize = marshalSize + 1;  // _pad
   marshalSize = marshalSize + 2;  // _passiveParameterIndex
   marshalSize = marshalSize + 1;  // _propulsionPlantConfiguration
   marshalSize = marshalSize + 1;  // _numberOfShafts
   marshalSize = marshalSize + 1;  // _numberOfAPAs
   marshalSize = marshalSize + 1;  // _numberOfUAEmitterSystems

   for(int idx=0; idx < _shaftRPMs.size(); idx++)
   {
        ShaftRPMs listElement = _shaftRPMs[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }


   for(int idx=0; idx < _apaData.size(); idx++)
   {
        ApaData listElement = _apaData[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }


   for(int idx=0; idx < _emitterSystems.size(); idx++)
   {
        AcousticEmitterSystemData listElement = _emitterSystems[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

