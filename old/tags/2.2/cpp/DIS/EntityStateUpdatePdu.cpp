#include <DIS/EntityStateUpdatePdu.h> 

using namespace DIS;


EntityStateUpdatePdu::EntityStateUpdatePdu() : EntityInformationFamilyPdu(),
   _entityID(), 
   _numberOfArticulationParameters(0), 
   _entityLinearVelocity(), 
   _entityLocation(), 
   _entityOrientation(), 
   _entityAppearance(0)
{
    setPduType( 67 );
    setProtocolFamily( 1 );
}

EntityStateUpdatePdu::~EntityStateUpdatePdu()
{
    _articulationParameters.clear();
}

EntityID& EntityStateUpdatePdu::getEntityID() 
{
    return _entityID;
}

const EntityID& EntityStateUpdatePdu::getEntityID() const
{
    return _entityID;
}

void EntityStateUpdatePdu::setEntityID(const EntityID &pX)
{
    _entityID = pX;
}

char EntityStateUpdatePdu::getNumberOfArticulationParameters() const
{
   return _articulationParameters.size();
}

Vector3Float& EntityStateUpdatePdu::getEntityLinearVelocity() 
{
    return _entityLinearVelocity;
}

const Vector3Float& EntityStateUpdatePdu::getEntityLinearVelocity() const
{
    return _entityLinearVelocity;
}

void EntityStateUpdatePdu::setEntityLinearVelocity(const Vector3Float &pX)
{
    _entityLinearVelocity = pX;
}

Vector3Double& EntityStateUpdatePdu::getEntityLocation() 
{
    return _entityLocation;
}

const Vector3Double& EntityStateUpdatePdu::getEntityLocation() const
{
    return _entityLocation;
}

void EntityStateUpdatePdu::setEntityLocation(const Vector3Double &pX)
{
    _entityLocation = pX;
}

Orientation& EntityStateUpdatePdu::getEntityOrientation() 
{
    return _entityOrientation;
}

const Orientation& EntityStateUpdatePdu::getEntityOrientation() const
{
    return _entityOrientation;
}

void EntityStateUpdatePdu::setEntityOrientation(const Orientation &pX)
{
    _entityOrientation = pX;
}

int EntityStateUpdatePdu::getEntityAppearance() const
{
    return _entityAppearance;
}

void EntityStateUpdatePdu::setEntityAppearance(int pX)
{
    _entityAppearance = pX;
}

std::vector<ArticulationParameter>& EntityStateUpdatePdu::getArticulationParameters() 
{
    return _articulationParameters;
}

const std::vector<ArticulationParameter>& EntityStateUpdatePdu::getArticulationParameters() const
{
    return _articulationParameters;
}

void EntityStateUpdatePdu::setArticulationParameters(const std::vector<ArticulationParameter>& pX)
{
     _articulationParameters = pX;
}

void EntityStateUpdatePdu::marshal(DataStream& dataStream) const
{
    EntityInformationFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _entityID.marshal(dataStream);
    dataStream << ( char )_articulationParameters.size();
    _entityLinearVelocity.marshal(dataStream);
    _entityLocation.marshal(dataStream);
    _entityOrientation.marshal(dataStream);
    dataStream << _entityAppearance;

     for(size_t idx = 0; idx < _articulationParameters.size(); idx++)
     {
        ArticulationParameter x = _articulationParameters[idx];
        x.marshal(dataStream);
     }

}

void EntityStateUpdatePdu::unmarshal(DataStream& dataStream)
{
    EntityInformationFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _entityID.unmarshal(dataStream);
    dataStream >> _numberOfArticulationParameters;
    _entityLinearVelocity.unmarshal(dataStream);
    _entityLocation.unmarshal(dataStream);
    _entityOrientation.unmarshal(dataStream);
    dataStream >> _entityAppearance;

     _articulationParameters.clear();
     for(size_t idx = 0; idx < _numberOfArticulationParameters; idx++)
     {
        ArticulationParameter x;
        x.unmarshal(dataStream);
        _articulationParameters.push_back(x);
     }
}


bool EntityStateUpdatePdu::operator ==(const EntityStateUpdatePdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = EntityInformationFamilyPdu::operator==(rhs);

     if( ! (_entityID == rhs._entityID) ) ivarsEqual = false;
     if( ! (_entityLinearVelocity == rhs._entityLinearVelocity) ) ivarsEqual = false;
     if( ! (_entityLocation == rhs._entityLocation) ) ivarsEqual = false;
     if( ! (_entityOrientation == rhs._entityOrientation) ) ivarsEqual = false;
     if( ! (_entityAppearance == rhs._entityAppearance) ) ivarsEqual = false;

     for(size_t idx = 0; idx < _articulationParameters.size(); idx++)
     {
        if( ! ( _articulationParameters[idx] == rhs._articulationParameters[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int EntityStateUpdatePdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = EntityInformationFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _entityID.getMarshalledSize();  // _entityID
   marshalSize = marshalSize + 1;  // _numberOfArticulationParameters
   marshalSize = marshalSize + _entityLinearVelocity.getMarshalledSize();  // _entityLinearVelocity
   marshalSize = marshalSize + _entityLocation.getMarshalledSize();  // _entityLocation
   marshalSize = marshalSize + _entityOrientation.getMarshalledSize();  // _entityOrientation
   marshalSize = marshalSize + 4;  // _entityAppearance

   for(int idx=0; idx < _articulationParameters.size(); idx++)
   {
        ArticulationParameter listElement = _articulationParameters[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

