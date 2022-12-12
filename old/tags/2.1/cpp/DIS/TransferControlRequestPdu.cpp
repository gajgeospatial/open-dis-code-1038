#include <DIS/TransferControlRequestPdu.h> 

using namespace DIS;


TransferControlRequestPdu::TransferControlRequestPdu() : EntityManagementFamilyPdu(),
   _orginatingEntityID(), 
   _recevingEntityID(), 
   _requestID(0), 
   _requiredReliabilityService(0), 
   _tranferType(0), 
   _transferEntityID(), 
   _numberOfRecordSets(0)
{
    setPduType( 35 );
}

TransferControlRequestPdu::~TransferControlRequestPdu()
{
    _recordSets.clear();
}

EntityID& TransferControlRequestPdu::getOrginatingEntityID() 
{
    return _orginatingEntityID;
}

const EntityID& TransferControlRequestPdu::getOrginatingEntityID() const
{
    return _orginatingEntityID;
}

void TransferControlRequestPdu::setOrginatingEntityID(const EntityID &pX)
{
    _orginatingEntityID = pX;
}

EntityID& TransferControlRequestPdu::getRecevingEntityID() 
{
    return _recevingEntityID;
}

const EntityID& TransferControlRequestPdu::getRecevingEntityID() const
{
    return _recevingEntityID;
}

void TransferControlRequestPdu::setRecevingEntityID(const EntityID &pX)
{
    _recevingEntityID = pX;
}

unsigned int TransferControlRequestPdu::getRequestID() const
{
    return _requestID;
}

void TransferControlRequestPdu::setRequestID(unsigned int pX)
{
    _requestID = pX;
}

unsigned char TransferControlRequestPdu::getRequiredReliabilityService() const
{
    return _requiredReliabilityService;
}

void TransferControlRequestPdu::setRequiredReliabilityService(unsigned char pX)
{
    _requiredReliabilityService = pX;
}

unsigned char TransferControlRequestPdu::getTranferType() const
{
    return _tranferType;
}

void TransferControlRequestPdu::setTranferType(unsigned char pX)
{
    _tranferType = pX;
}

EntityID& TransferControlRequestPdu::getTransferEntityID() 
{
    return _transferEntityID;
}

const EntityID& TransferControlRequestPdu::getTransferEntityID() const
{
    return _transferEntityID;
}

void TransferControlRequestPdu::setTransferEntityID(const EntityID &pX)
{
    _transferEntityID = pX;
}

unsigned char TransferControlRequestPdu::getNumberOfRecordSets() const
{
   return _recordSets.size();
}

std::vector<RecordSet>& TransferControlRequestPdu::getRecordSets() 
{
    return _recordSets;
}

const std::vector<RecordSet>& TransferControlRequestPdu::getRecordSets() const
{
    return _recordSets;
}

void TransferControlRequestPdu::setRecordSets(const std::vector<RecordSet>& pX)
{
     _recordSets = pX;
}

void TransferControlRequestPdu::marshal(DataStream& dataStream) const
{
    EntityManagementFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _orginatingEntityID.marshal(dataStream);
    _recevingEntityID.marshal(dataStream);
    dataStream << _requestID;
    dataStream << _requiredReliabilityService;
    dataStream << _tranferType;
    _transferEntityID.marshal(dataStream);
    dataStream << ( unsigned char )_recordSets.size();

     for(size_t idx = 0; idx < _recordSets.size(); idx++)
     {
        RecordSet x = _recordSets[idx];
        x.marshal(dataStream);
     }

}

void TransferControlRequestPdu::unmarshal(DataStream& dataStream)
{
    EntityManagementFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _orginatingEntityID.unmarshal(dataStream);
    _recevingEntityID.unmarshal(dataStream);
    dataStream >> _requestID;
    dataStream >> _requiredReliabilityService;
    dataStream >> _tranferType;
    _transferEntityID.unmarshal(dataStream);
    dataStream >> _numberOfRecordSets;

     _recordSets.clear();
     for(size_t idx = 0; idx < _numberOfRecordSets; idx++)
     {
        RecordSet x;
        x.unmarshal(dataStream);
        _recordSets.push_back(x);
     }
}


bool TransferControlRequestPdu::operator ==(const TransferControlRequestPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = EntityManagementFamilyPdu::operator==(rhs);

     if( ! (_orginatingEntityID == rhs._orginatingEntityID) ) ivarsEqual = false;
     if( ! (_recevingEntityID == rhs._recevingEntityID) ) ivarsEqual = false;
     if( ! (_requestID == rhs._requestID) ) ivarsEqual = false;
     if( ! (_requiredReliabilityService == rhs._requiredReliabilityService) ) ivarsEqual = false;
     if( ! (_tranferType == rhs._tranferType) ) ivarsEqual = false;
     if( ! (_transferEntityID == rhs._transferEntityID) ) ivarsEqual = false;

     for(size_t idx = 0; idx < _recordSets.size(); idx++)
     {
        if( ! ( _recordSets[idx] == rhs._recordSets[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int TransferControlRequestPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = EntityManagementFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _orginatingEntityID.getMarshalledSize();  // _orginatingEntityID
   marshalSize = marshalSize + _recevingEntityID.getMarshalledSize();  // _recevingEntityID
   marshalSize = marshalSize + 4;  // _requestID
   marshalSize = marshalSize + 1;  // _requiredReliabilityService
   marshalSize = marshalSize + 1;  // _tranferType
   marshalSize = marshalSize + _transferEntityID.getMarshalledSize();  // _transferEntityID
   marshalSize = marshalSize + 1;  // _numberOfRecordSets

   for(int idx=0; idx < _recordSets.size(); idx++)
   {
        RecordSet listElement = _recordSets[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

