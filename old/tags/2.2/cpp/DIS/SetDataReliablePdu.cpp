#include <DIS/SetDataReliablePdu.h> 

using namespace DIS;


SetDataReliablePdu::SetDataReliablePdu() : SimulationManagementWithReliabilityFamilyPdu(),
   _requiredReliabilityService(0), 
   _pad1(0), 
   _pad2(0), 
   _requestID(0), 
   _numberOfFixedDatumRecords(0), 
   _numberOfVariableDatumRecords(0)
{
    setPduType( 59 );
}

SetDataReliablePdu::~SetDataReliablePdu()
{
    _fixedDatumRecords.clear();
    _variableDatumRecords.clear();
}

unsigned char SetDataReliablePdu::getRequiredReliabilityService() const
{
    return _requiredReliabilityService;
}

void SetDataReliablePdu::setRequiredReliabilityService(unsigned char pX)
{
    _requiredReliabilityService = pX;
}

unsigned short SetDataReliablePdu::getPad1() const
{
    return _pad1;
}

void SetDataReliablePdu::setPad1(unsigned short pX)
{
    _pad1 = pX;
}

unsigned char SetDataReliablePdu::getPad2() const
{
    return _pad2;
}

void SetDataReliablePdu::setPad2(unsigned char pX)
{
    _pad2 = pX;
}

unsigned int SetDataReliablePdu::getRequestID() const
{
    return _requestID;
}

void SetDataReliablePdu::setRequestID(unsigned int pX)
{
    _requestID = pX;
}

unsigned int SetDataReliablePdu::getNumberOfFixedDatumRecords() const
{
   return _fixedDatumRecords.size();
}

unsigned int SetDataReliablePdu::getNumberOfVariableDatumRecords() const
{
   return _variableDatumRecords.size();
}

std::vector<FixedDatum>& SetDataReliablePdu::getFixedDatumRecords() 
{
    return _fixedDatumRecords;
}

const std::vector<FixedDatum>& SetDataReliablePdu::getFixedDatumRecords() const
{
    return _fixedDatumRecords;
}

void SetDataReliablePdu::setFixedDatumRecords(const std::vector<FixedDatum>& pX)
{
     _fixedDatumRecords = pX;
}

std::vector<VariableDatum>& SetDataReliablePdu::getVariableDatumRecords() 
{
    return _variableDatumRecords;
}

const std::vector<VariableDatum>& SetDataReliablePdu::getVariableDatumRecords() const
{
    return _variableDatumRecords;
}

void SetDataReliablePdu::setVariableDatumRecords(const std::vector<VariableDatum>& pX)
{
     _variableDatumRecords = pX;
}

void SetDataReliablePdu::marshal(DataStream& dataStream) const
{
    SimulationManagementWithReliabilityFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    dataStream << _requiredReliabilityService;
    dataStream << _pad1;
    dataStream << _pad2;
    dataStream << _requestID;
    dataStream << ( unsigned int )_fixedDatumRecords.size();
    dataStream << ( unsigned int )_variableDatumRecords.size();

     for(size_t idx = 0; idx < _fixedDatumRecords.size(); idx++)
     {
        FixedDatum x = _fixedDatumRecords[idx];
        x.marshal(dataStream);
     }


     for(size_t idx = 0; idx < _variableDatumRecords.size(); idx++)
     {
        VariableDatum x = _variableDatumRecords[idx];
        x.marshal(dataStream);
     }

}

void SetDataReliablePdu::unmarshal(DataStream& dataStream)
{
    SimulationManagementWithReliabilityFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    dataStream >> _requiredReliabilityService;
    dataStream >> _pad1;
    dataStream >> _pad2;
    dataStream >> _requestID;
    dataStream >> _numberOfFixedDatumRecords;
    dataStream >> _numberOfVariableDatumRecords;

     _fixedDatumRecords.clear();
     for(size_t idx = 0; idx < _numberOfFixedDatumRecords; idx++)
     {
        FixedDatum x;
        x.unmarshal(dataStream);
        _fixedDatumRecords.push_back(x);
     }

     _variableDatumRecords.clear();
     for(size_t idx = 0; idx < _numberOfVariableDatumRecords; idx++)
     {
        VariableDatum x;
        x.unmarshal(dataStream);
        _variableDatumRecords.push_back(x);
     }
}


bool SetDataReliablePdu::operator ==(const SetDataReliablePdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = SimulationManagementWithReliabilityFamilyPdu::operator==(rhs);

     if( ! (_requiredReliabilityService == rhs._requiredReliabilityService) ) ivarsEqual = false;
     if( ! (_pad1 == rhs._pad1) ) ivarsEqual = false;
     if( ! (_pad2 == rhs._pad2) ) ivarsEqual = false;
     if( ! (_requestID == rhs._requestID) ) ivarsEqual = false;

     for(size_t idx = 0; idx < _fixedDatumRecords.size(); idx++)
     {
        if( ! ( _fixedDatumRecords[idx] == rhs._fixedDatumRecords[idx]) ) ivarsEqual = false;
     }


     for(size_t idx = 0; idx < _variableDatumRecords.size(); idx++)
     {
        if( ! ( _variableDatumRecords[idx] == rhs._variableDatumRecords[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int SetDataReliablePdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = SimulationManagementWithReliabilityFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + 1;  // _requiredReliabilityService
   marshalSize = marshalSize + 2;  // _pad1
   marshalSize = marshalSize + 1;  // _pad2
   marshalSize = marshalSize + 4;  // _requestID
   marshalSize = marshalSize + 4;  // _numberOfFixedDatumRecords
   marshalSize = marshalSize + 4;  // _numberOfVariableDatumRecords

   for(int idx=0; idx < _fixedDatumRecords.size(); idx++)
   {
        FixedDatum listElement = _fixedDatumRecords[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }


   for(int idx=0; idx < _variableDatumRecords.size(); idx++)
   {
        VariableDatum listElement = _variableDatumRecords[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

