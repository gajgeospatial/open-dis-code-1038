#include <DIS/ActionRequestReliablePdu.h> 

using namespace DIS;


ActionRequestReliablePdu::ActionRequestReliablePdu() : SimulationManagementWithReliabilityFamilyPdu(),
   _requiredReliabilityService(0), 
   _pad1(0), 
   _pad2(0), 
   _requestID(0), 
   _actionID(0), 
   _numberOfFixedDatumRecords(0), 
   _numberOfVariableDatumRecords(0)
{
    setPduType( 56 );
}

ActionRequestReliablePdu::~ActionRequestReliablePdu()
{
    _fixedDatumRecords.clear();
    _variableDatumRecords.clear();
}

unsigned char ActionRequestReliablePdu::getRequiredReliabilityService() const
{
    return _requiredReliabilityService;
}

void ActionRequestReliablePdu::setRequiredReliabilityService(unsigned char pX)
{
    _requiredReliabilityService = pX;
}

unsigned short ActionRequestReliablePdu::getPad1() const
{
    return _pad1;
}

void ActionRequestReliablePdu::setPad1(unsigned short pX)
{
    _pad1 = pX;
}

unsigned char ActionRequestReliablePdu::getPad2() const
{
    return _pad2;
}

void ActionRequestReliablePdu::setPad2(unsigned char pX)
{
    _pad2 = pX;
}

unsigned int ActionRequestReliablePdu::getRequestID() const
{
    return _requestID;
}

void ActionRequestReliablePdu::setRequestID(unsigned int pX)
{
    _requestID = pX;
}

unsigned int ActionRequestReliablePdu::getActionID() const
{
    return _actionID;
}

void ActionRequestReliablePdu::setActionID(unsigned int pX)
{
    _actionID = pX;
}

unsigned int ActionRequestReliablePdu::getNumberOfFixedDatumRecords() const
{
   return _fixedDatumRecords.size();
}

unsigned int ActionRequestReliablePdu::getNumberOfVariableDatumRecords() const
{
   return _variableDatumRecords.size();
}

std::vector<FixedDatum>& ActionRequestReliablePdu::getFixedDatumRecords() 
{
    return _fixedDatumRecords;
}

const std::vector<FixedDatum>& ActionRequestReliablePdu::getFixedDatumRecords() const
{
    return _fixedDatumRecords;
}

void ActionRequestReliablePdu::setFixedDatumRecords(const std::vector<FixedDatum>& pX)
{
     _fixedDatumRecords = pX;
}

std::vector<VariableDatum>& ActionRequestReliablePdu::getVariableDatumRecords() 
{
    return _variableDatumRecords;
}

const std::vector<VariableDatum>& ActionRequestReliablePdu::getVariableDatumRecords() const
{
    return _variableDatumRecords;
}

void ActionRequestReliablePdu::setVariableDatumRecords(const std::vector<VariableDatum>& pX)
{
     _variableDatumRecords = pX;
}

void ActionRequestReliablePdu::marshal(DataStream& dataStream) const
{
    SimulationManagementWithReliabilityFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    dataStream << _requiredReliabilityService;
    dataStream << _pad1;
    dataStream << _pad2;
    dataStream << _requestID;
    dataStream << _actionID;
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

void ActionRequestReliablePdu::unmarshal(DataStream& dataStream)
{
    SimulationManagementWithReliabilityFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    dataStream >> _requiredReliabilityService;
    dataStream >> _pad1;
    dataStream >> _pad2;
    dataStream >> _requestID;
    dataStream >> _actionID;
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


bool ActionRequestReliablePdu::operator ==(const ActionRequestReliablePdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = SimulationManagementWithReliabilityFamilyPdu::operator==(rhs);

     if( ! (_requiredReliabilityService == rhs._requiredReliabilityService) ) ivarsEqual = false;
     if( ! (_pad1 == rhs._pad1) ) ivarsEqual = false;
     if( ! (_pad2 == rhs._pad2) ) ivarsEqual = false;
     if( ! (_requestID == rhs._requestID) ) ivarsEqual = false;
     if( ! (_actionID == rhs._actionID) ) ivarsEqual = false;

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

int ActionRequestReliablePdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = SimulationManagementWithReliabilityFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + 1;  // _requiredReliabilityService
   marshalSize = marshalSize + 2;  // _pad1
   marshalSize = marshalSize + 1;  // _pad2
   marshalSize = marshalSize + 4;  // _requestID
   marshalSize = marshalSize + 4;  // _actionID
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

