#include <DIS/ElectronicEmissionsPdu.h> 

using namespace DIS;


ElectronicEmissionsPdu::ElectronicEmissionsPdu() : DistributedEmissionsFamilyPdu(),
   _emittingEntityID(), 
   _eventID(), 
   _stateUpdateIndicator(0), 
   _numberOfSystems(0)
{
    setPduType( 23 );
}

ElectronicEmissionsPdu::~ElectronicEmissionsPdu()
{
    _systems.clear();
}

EntityID& ElectronicEmissionsPdu::getEmittingEntityID() 
{
    return _emittingEntityID;
}

const EntityID& ElectronicEmissionsPdu::getEmittingEntityID() const
{
    return _emittingEntityID;
}

void ElectronicEmissionsPdu::setEmittingEntityID(const EntityID &pX)
{
    _emittingEntityID = pX;
}

EventID& ElectronicEmissionsPdu::getEventID() 
{
    return _eventID;
}

const EventID& ElectronicEmissionsPdu::getEventID() const
{
    return _eventID;
}

void ElectronicEmissionsPdu::setEventID(const EventID &pX)
{
    _eventID = pX;
}

unsigned char ElectronicEmissionsPdu::getStateUpdateIndicator() const
{
    return _stateUpdateIndicator;
}

void ElectronicEmissionsPdu::setStateUpdateIndicator(unsigned char pX)
{
    _stateUpdateIndicator = pX;
}

unsigned char ElectronicEmissionsPdu::getNumberOfSystems() const
{
   return _systems.size();
}

std::vector<ElectronicEmissionSystemData>& ElectronicEmissionsPdu::getSystems() 
{
    return _systems;
}

const std::vector<ElectronicEmissionSystemData>& ElectronicEmissionsPdu::getSystems() const
{
    return _systems;
}

void ElectronicEmissionsPdu::setSystems(const std::vector<ElectronicEmissionSystemData>& pX)
{
     _systems = pX;
}

void ElectronicEmissionsPdu::marshal(DataStream& dataStream) const
{
    DistributedEmissionsFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _emittingEntityID.marshal(dataStream);
    _eventID.marshal(dataStream);
    dataStream << _stateUpdateIndicator;
    dataStream << ( unsigned char )_systems.size();

     for(size_t idx = 0; idx < _systems.size(); idx++)
     {
        ElectronicEmissionSystemData x = _systems[idx];
        x.marshal(dataStream);
     }

}

void ElectronicEmissionsPdu::unmarshal(DataStream& dataStream)
{
    DistributedEmissionsFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _emittingEntityID.unmarshal(dataStream);
    _eventID.unmarshal(dataStream);
    dataStream >> _stateUpdateIndicator;
    dataStream >> _numberOfSystems;

     _systems.clear();
     for(size_t idx = 0; idx < _numberOfSystems; idx++)
     {
        ElectronicEmissionSystemData x;
        x.unmarshal(dataStream);
        _systems.push_back(x);
     }
}


bool ElectronicEmissionsPdu::operator ==(const ElectronicEmissionsPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = DistributedEmissionsFamilyPdu::operator==(rhs);

     if( ! (_emittingEntityID == rhs._emittingEntityID) ) ivarsEqual = false;
     if( ! (_eventID == rhs._eventID) ) ivarsEqual = false;
     if( ! (_stateUpdateIndicator == rhs._stateUpdateIndicator) ) ivarsEqual = false;

     for(size_t idx = 0; idx < _systems.size(); idx++)
     {
        if( ! ( _systems[idx] == rhs._systems[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int ElectronicEmissionsPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = DistributedEmissionsFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _emittingEntityID.getMarshalledSize();  // _emittingEntityID
   marshalSize = marshalSize + _eventID.getMarshalledSize();  // _eventID
   marshalSize = marshalSize + 1;  // _stateUpdateIndicator
   marshalSize = marshalSize + 1;  // _numberOfSystems

   for(int idx=0; idx < _systems.size(); idx++)
   {
        ElectronicEmissionSystemData listElement = _systems[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

