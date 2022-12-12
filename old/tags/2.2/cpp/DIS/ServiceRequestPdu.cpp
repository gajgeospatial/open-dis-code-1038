#include <DIS/ServiceRequestPdu.h> 

using namespace DIS;


ServiceRequestPdu::ServiceRequestPdu() : LogisticsFamilyPdu(),
   _requestingEntityID(), 
   _servicingEntityID(), 
   _serviceTypeRequested(0), 
   _numberOfSupplyTypes(0), 
   _serviceRequestPadding(0)
{
    setPduType( 5 );
}

ServiceRequestPdu::~ServiceRequestPdu()
{
    _supplies.clear();
}

EntityID& ServiceRequestPdu::getRequestingEntityID() 
{
    return _requestingEntityID;
}

const EntityID& ServiceRequestPdu::getRequestingEntityID() const
{
    return _requestingEntityID;
}

void ServiceRequestPdu::setRequestingEntityID(const EntityID &pX)
{
    _requestingEntityID = pX;
}

EntityID& ServiceRequestPdu::getServicingEntityID() 
{
    return _servicingEntityID;
}

const EntityID& ServiceRequestPdu::getServicingEntityID() const
{
    return _servicingEntityID;
}

void ServiceRequestPdu::setServicingEntityID(const EntityID &pX)
{
    _servicingEntityID = pX;
}

unsigned char ServiceRequestPdu::getServiceTypeRequested() const
{
    return _serviceTypeRequested;
}

void ServiceRequestPdu::setServiceTypeRequested(unsigned char pX)
{
    _serviceTypeRequested = pX;
}

unsigned char ServiceRequestPdu::getNumberOfSupplyTypes() const
{
   return _supplies.size();
}

short ServiceRequestPdu::getServiceRequestPadding() const
{
    return _serviceRequestPadding;
}

void ServiceRequestPdu::setServiceRequestPadding(short pX)
{
    _serviceRequestPadding = pX;
}

std::vector<SupplyQuantity>& ServiceRequestPdu::getSupplies() 
{
    return _supplies;
}

const std::vector<SupplyQuantity>& ServiceRequestPdu::getSupplies() const
{
    return _supplies;
}

void ServiceRequestPdu::setSupplies(const std::vector<SupplyQuantity>& pX)
{
     _supplies = pX;
}

void ServiceRequestPdu::marshal(DataStream& dataStream) const
{
    LogisticsFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _requestingEntityID.marshal(dataStream);
    _servicingEntityID.marshal(dataStream);
    dataStream << _serviceTypeRequested;
    dataStream << ( unsigned char )_supplies.size();
    dataStream << _serviceRequestPadding;

     for(size_t idx = 0; idx < _supplies.size(); idx++)
     {
        SupplyQuantity x = _supplies[idx];
        x.marshal(dataStream);
     }

}

void ServiceRequestPdu::unmarshal(DataStream& dataStream)
{
    LogisticsFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _requestingEntityID.unmarshal(dataStream);
    _servicingEntityID.unmarshal(dataStream);
    dataStream >> _serviceTypeRequested;
    dataStream >> _numberOfSupplyTypes;
    dataStream >> _serviceRequestPadding;

     _supplies.clear();
     for(size_t idx = 0; idx < _numberOfSupplyTypes; idx++)
     {
        SupplyQuantity x;
        x.unmarshal(dataStream);
        _supplies.push_back(x);
     }
}


bool ServiceRequestPdu::operator ==(const ServiceRequestPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = LogisticsFamilyPdu::operator==(rhs);

     if( ! (_requestingEntityID == rhs._requestingEntityID) ) ivarsEqual = false;
     if( ! (_servicingEntityID == rhs._servicingEntityID) ) ivarsEqual = false;
     if( ! (_serviceTypeRequested == rhs._serviceTypeRequested) ) ivarsEqual = false;
     if( ! (_serviceRequestPadding == rhs._serviceRequestPadding) ) ivarsEqual = false;

     for(size_t idx = 0; idx < _supplies.size(); idx++)
     {
        if( ! ( _supplies[idx] == rhs._supplies[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int ServiceRequestPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = LogisticsFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _requestingEntityID.getMarshalledSize();  // _requestingEntityID
   marshalSize = marshalSize + _servicingEntityID.getMarshalledSize();  // _servicingEntityID
   marshalSize = marshalSize + 1;  // _serviceTypeRequested
   marshalSize = marshalSize + 1;  // _numberOfSupplyTypes
   marshalSize = marshalSize + 2;  // _serviceRequestPadding

   for(int idx=0; idx < _supplies.size(); idx++)
   {
        SupplyQuantity listElement = _supplies[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

