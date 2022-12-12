#include <DIS/SeesPdu.h> 

using namespace DIS;


SeesPdu::SeesPdu() : DistributedEmissionsFamilyPdu(),
   _orginatingEntityID(), 
   _infraredSignatureRepresentationIndex(0), 
   _acousticSignatureRepresentationIndex(0), 
   _radarCrossSectionSignatureRepresentationIndex(0), 
   _numberOfPropulsionSystems(0), 
   _numberOfVectoringNozzleSystems(0)
{
    setPduType( 30 );
}

SeesPdu::~SeesPdu()
{
    _propulsionSystemData.clear();
    _vectoringSystemData.clear();
}

EntityID& SeesPdu::getOrginatingEntityID() 
{
    return _orginatingEntityID;
}

const EntityID& SeesPdu::getOrginatingEntityID() const
{
    return _orginatingEntityID;
}

void SeesPdu::setOrginatingEntityID(const EntityID &pX)
{
    _orginatingEntityID = pX;
}

unsigned short SeesPdu::getInfraredSignatureRepresentationIndex() const
{
    return _infraredSignatureRepresentationIndex;
}

void SeesPdu::setInfraredSignatureRepresentationIndex(unsigned short pX)
{
    _infraredSignatureRepresentationIndex = pX;
}

unsigned short SeesPdu::getAcousticSignatureRepresentationIndex() const
{
    return _acousticSignatureRepresentationIndex;
}

void SeesPdu::setAcousticSignatureRepresentationIndex(unsigned short pX)
{
    _acousticSignatureRepresentationIndex = pX;
}

unsigned short SeesPdu::getRadarCrossSectionSignatureRepresentationIndex() const
{
    return _radarCrossSectionSignatureRepresentationIndex;
}

void SeesPdu::setRadarCrossSectionSignatureRepresentationIndex(unsigned short pX)
{
    _radarCrossSectionSignatureRepresentationIndex = pX;
}

unsigned short SeesPdu::getNumberOfPropulsionSystems() const
{
   return _propulsionSystemData.size();
}

unsigned short SeesPdu::getNumberOfVectoringNozzleSystems() const
{
   return _vectoringSystemData.size();
}

std::vector<PropulsionSystemData>& SeesPdu::getPropulsionSystemData() 
{
    return _propulsionSystemData;
}

const std::vector<PropulsionSystemData>& SeesPdu::getPropulsionSystemData() const
{
    return _propulsionSystemData;
}

void SeesPdu::setPropulsionSystemData(const std::vector<PropulsionSystemData>& pX)
{
     _propulsionSystemData = pX;
}

std::vector<VectoringNozzleSystemData>& SeesPdu::getVectoringSystemData() 
{
    return _vectoringSystemData;
}

const std::vector<VectoringNozzleSystemData>& SeesPdu::getVectoringSystemData() const
{
    return _vectoringSystemData;
}

void SeesPdu::setVectoringSystemData(const std::vector<VectoringNozzleSystemData>& pX)
{
     _vectoringSystemData = pX;
}

void SeesPdu::marshal(DataStream& dataStream) const
{
    DistributedEmissionsFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _orginatingEntityID.marshal(dataStream);
    dataStream << _infraredSignatureRepresentationIndex;
    dataStream << _acousticSignatureRepresentationIndex;
    dataStream << _radarCrossSectionSignatureRepresentationIndex;
    dataStream << ( unsigned short )_propulsionSystemData.size();
    dataStream << ( unsigned short )_vectoringSystemData.size();

     for(size_t idx = 0; idx < _propulsionSystemData.size(); idx++)
     {
        PropulsionSystemData x = _propulsionSystemData[idx];
        x.marshal(dataStream);
     }


     for(size_t idx = 0; idx < _vectoringSystemData.size(); idx++)
     {
        VectoringNozzleSystemData x = _vectoringSystemData[idx];
        x.marshal(dataStream);
     }

}

void SeesPdu::unmarshal(DataStream& dataStream)
{
    DistributedEmissionsFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _orginatingEntityID.unmarshal(dataStream);
    dataStream >> _infraredSignatureRepresentationIndex;
    dataStream >> _acousticSignatureRepresentationIndex;
    dataStream >> _radarCrossSectionSignatureRepresentationIndex;
    dataStream >> _numberOfPropulsionSystems;
    dataStream >> _numberOfVectoringNozzleSystems;

     _propulsionSystemData.clear();
     for(size_t idx = 0; idx < _numberOfPropulsionSystems; idx++)
     {
        PropulsionSystemData x;
        x.unmarshal(dataStream);
        _propulsionSystemData.push_back(x);
     }

     _vectoringSystemData.clear();
     for(size_t idx = 0; idx < _numberOfVectoringNozzleSystems; idx++)
     {
        VectoringNozzleSystemData x;
        x.unmarshal(dataStream);
        _vectoringSystemData.push_back(x);
     }
}


bool SeesPdu::operator ==(const SeesPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = DistributedEmissionsFamilyPdu::operator==(rhs);

     if( ! (_orginatingEntityID == rhs._orginatingEntityID) ) ivarsEqual = false;
     if( ! (_infraredSignatureRepresentationIndex == rhs._infraredSignatureRepresentationIndex) ) ivarsEqual = false;
     if( ! (_acousticSignatureRepresentationIndex == rhs._acousticSignatureRepresentationIndex) ) ivarsEqual = false;
     if( ! (_radarCrossSectionSignatureRepresentationIndex == rhs._radarCrossSectionSignatureRepresentationIndex) ) ivarsEqual = false;

     for(size_t idx = 0; idx < _propulsionSystemData.size(); idx++)
     {
        if( ! ( _propulsionSystemData[idx] == rhs._propulsionSystemData[idx]) ) ivarsEqual = false;
     }


     for(size_t idx = 0; idx < _vectoringSystemData.size(); idx++)
     {
        if( ! ( _vectoringSystemData[idx] == rhs._vectoringSystemData[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int SeesPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = DistributedEmissionsFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _orginatingEntityID.getMarshalledSize();  // _orginatingEntityID
   marshalSize = marshalSize + 2;  // _infraredSignatureRepresentationIndex
   marshalSize = marshalSize + 2;  // _acousticSignatureRepresentationIndex
   marshalSize = marshalSize + 2;  // _radarCrossSectionSignatureRepresentationIndex
   marshalSize = marshalSize + 2;  // _numberOfPropulsionSystems
   marshalSize = marshalSize + 2;  // _numberOfVectoringNozzleSystems

   for(int idx=0; idx < _propulsionSystemData.size(); idx++)
   {
        PropulsionSystemData listElement = _propulsionSystemData[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }


   for(int idx=0; idx < _vectoringSystemData.size(); idx++)
   {
        VectoringNozzleSystemData listElement = _vectoringSystemData[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

