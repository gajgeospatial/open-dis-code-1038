#include <DIS/EnvironmentalProcessPdu.h> 

using namespace DIS;


EnvironmentalProcessPdu::EnvironmentalProcessPdu() : SyntheticEnvironmentFamilyPdu(),
   _environementalProcessID(), 
   _environmentType(), 
   _modelType(0), 
   _environmentStatus(0), 
   _numberOfEnvironmentRecords(0), 
   _sequenceNumber(0)
{
    setPduType( 41 );
}

EnvironmentalProcessPdu::~EnvironmentalProcessPdu()
{
    _environmentRecords.clear();
}

EntityID& EnvironmentalProcessPdu::getEnvironementalProcessID() 
{
    return _environementalProcessID;
}

const EntityID& EnvironmentalProcessPdu::getEnvironementalProcessID() const
{
    return _environementalProcessID;
}

void EnvironmentalProcessPdu::setEnvironementalProcessID(const EntityID &pX)
{
    _environementalProcessID = pX;
}

EntityType& EnvironmentalProcessPdu::getEnvironmentType() 
{
    return _environmentType;
}

const EntityType& EnvironmentalProcessPdu::getEnvironmentType() const
{
    return _environmentType;
}

void EnvironmentalProcessPdu::setEnvironmentType(const EntityType &pX)
{
    _environmentType = pX;
}

unsigned char EnvironmentalProcessPdu::getModelType() const
{
    return _modelType;
}

void EnvironmentalProcessPdu::setModelType(unsigned char pX)
{
    _modelType = pX;
}

unsigned char EnvironmentalProcessPdu::getEnvironmentStatus() const
{
    return _environmentStatus;
}

void EnvironmentalProcessPdu::setEnvironmentStatus(unsigned char pX)
{
    _environmentStatus = pX;
}

unsigned char EnvironmentalProcessPdu::getNumberOfEnvironmentRecords() const
{
   return _environmentRecords.size();
}

unsigned short EnvironmentalProcessPdu::getSequenceNumber() const
{
    return _sequenceNumber;
}

void EnvironmentalProcessPdu::setSequenceNumber(unsigned short pX)
{
    _sequenceNumber = pX;
}

std::vector<Environment>& EnvironmentalProcessPdu::getEnvironmentRecords() 
{
    return _environmentRecords;
}

const std::vector<Environment>& EnvironmentalProcessPdu::getEnvironmentRecords() const
{
    return _environmentRecords;
}

void EnvironmentalProcessPdu::setEnvironmentRecords(const std::vector<Environment>& pX)
{
     _environmentRecords = pX;
}

void EnvironmentalProcessPdu::marshal(DataStream& dataStream) const
{
    SyntheticEnvironmentFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _environementalProcessID.marshal(dataStream);
    _environmentType.marshal(dataStream);
    dataStream << _modelType;
    dataStream << _environmentStatus;
    dataStream << ( unsigned char )_environmentRecords.size();
    dataStream << _sequenceNumber;

     for(size_t idx = 0; idx < _environmentRecords.size(); idx++)
     {
        Environment x = _environmentRecords[idx];
        x.marshal(dataStream);
     }

}

void EnvironmentalProcessPdu::unmarshal(DataStream& dataStream)
{
    SyntheticEnvironmentFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _environementalProcessID.unmarshal(dataStream);
    _environmentType.unmarshal(dataStream);
    dataStream >> _modelType;
    dataStream >> _environmentStatus;
    dataStream >> _numberOfEnvironmentRecords;
    dataStream >> _sequenceNumber;

     _environmentRecords.clear();
     for(size_t idx = 0; idx < _numberOfEnvironmentRecords; idx++)
     {
        Environment x;
        x.unmarshal(dataStream);
        _environmentRecords.push_back(x);
     }
}


bool EnvironmentalProcessPdu::operator ==(const EnvironmentalProcessPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = SyntheticEnvironmentFamilyPdu::operator==(rhs);

     if( ! (_environementalProcessID == rhs._environementalProcessID) ) ivarsEqual = false;
     if( ! (_environmentType == rhs._environmentType) ) ivarsEqual = false;
     if( ! (_modelType == rhs._modelType) ) ivarsEqual = false;
     if( ! (_environmentStatus == rhs._environmentStatus) ) ivarsEqual = false;
     if( ! (_sequenceNumber == rhs._sequenceNumber) ) ivarsEqual = false;

     for(size_t idx = 0; idx < _environmentRecords.size(); idx++)
     {
        if( ! ( _environmentRecords[idx] == rhs._environmentRecords[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int EnvironmentalProcessPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = SyntheticEnvironmentFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _environementalProcessID.getMarshalledSize();  // _environementalProcessID
   marshalSize = marshalSize + _environmentType.getMarshalledSize();  // _environmentType
   marshalSize = marshalSize + 1;  // _modelType
   marshalSize = marshalSize + 1;  // _environmentStatus
   marshalSize = marshalSize + 1;  // _numberOfEnvironmentRecords
   marshalSize = marshalSize + 2;  // _sequenceNumber

   for(int idx=0; idx < _environmentRecords.size(); idx++)
   {
        Environment listElement = _environmentRecords[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

