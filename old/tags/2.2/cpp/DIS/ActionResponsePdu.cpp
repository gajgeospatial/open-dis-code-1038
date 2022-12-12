#include <DIS/ActionResponsePdu.h> 

using namespace DIS;


ActionResponsePdu::ActionResponsePdu() : SimulationManagementFamilyPdu(),
   _requestID(0), 
   _requestStatus(0), 
   _numberOfFixedDatumRecords(0), 
   _numberOfVariableDatumRecords(0)
{
    setPduType( 17 );
}

ActionResponsePdu::~ActionResponsePdu()
{
    _fixedDatums.clear();
    _variableDatums.clear();
}

unsigned int ActionResponsePdu::getRequestID() const
{
    return _requestID;
}

void ActionResponsePdu::setRequestID(unsigned int pX)
{
    _requestID = pX;
}

unsigned int ActionResponsePdu::getRequestStatus() const
{
    return _requestStatus;
}

void ActionResponsePdu::setRequestStatus(unsigned int pX)
{
    _requestStatus = pX;
}

unsigned int ActionResponsePdu::getNumberOfFixedDatumRecords() const
{
   return _fixedDatums.size();
}

unsigned int ActionResponsePdu::getNumberOfVariableDatumRecords() const
{
   return _variableDatums.size();
}

std::vector<FixedDatum>& ActionResponsePdu::getFixedDatums() 
{
    return _fixedDatums;
}

const std::vector<FixedDatum>& ActionResponsePdu::getFixedDatums() const
{
    return _fixedDatums;
}

void ActionResponsePdu::setFixedDatums(const std::vector<FixedDatum>& pX)
{
     _fixedDatums = pX;
}

std::vector<VariableDatum>& ActionResponsePdu::getVariableDatums() 
{
    return _variableDatums;
}

const std::vector<VariableDatum>& ActionResponsePdu::getVariableDatums() const
{
    return _variableDatums;
}

void ActionResponsePdu::setVariableDatums(const std::vector<VariableDatum>& pX)
{
     _variableDatums = pX;
}

void ActionResponsePdu::marshal(DataStream& dataStream) const
{
    SimulationManagementFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    dataStream << _requestID;
    dataStream << _requestStatus;
    dataStream << ( unsigned int )_fixedDatums.size();
    dataStream << ( unsigned int )_variableDatums.size();

     for(size_t idx = 0; idx < _fixedDatums.size(); idx++)
     {
        FixedDatum x = _fixedDatums[idx];
        x.marshal(dataStream);
     }


     for(size_t idx = 0; idx < _variableDatums.size(); idx++)
     {
        VariableDatum x = _variableDatums[idx];
        x.marshal(dataStream);
     }

}

void ActionResponsePdu::unmarshal(DataStream& dataStream)
{
    SimulationManagementFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    dataStream >> _requestID;
    dataStream >> _requestStatus;
    dataStream >> _numberOfFixedDatumRecords;
    dataStream >> _numberOfVariableDatumRecords;

     _fixedDatums.clear();
     for(size_t idx = 0; idx < _numberOfFixedDatumRecords; idx++)
     {
        FixedDatum x;
        x.unmarshal(dataStream);
        _fixedDatums.push_back(x);
     }

     _variableDatums.clear();
     for(size_t idx = 0; idx < _numberOfVariableDatumRecords; idx++)
     {
        VariableDatum x;
        x.unmarshal(dataStream);
        _variableDatums.push_back(x);
     }
}


bool ActionResponsePdu::operator ==(const ActionResponsePdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = SimulationManagementFamilyPdu::operator==(rhs);

     if( ! (_requestID == rhs._requestID) ) ivarsEqual = false;
     if( ! (_requestStatus == rhs._requestStatus) ) ivarsEqual = false;

     for(size_t idx = 0; idx < _fixedDatums.size(); idx++)
     {
        if( ! ( _fixedDatums[idx] == rhs._fixedDatums[idx]) ) ivarsEqual = false;
     }


     for(size_t idx = 0; idx < _variableDatums.size(); idx++)
     {
        if( ! ( _variableDatums[idx] == rhs._variableDatums[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int ActionResponsePdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = SimulationManagementFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + 4;  // _requestID
   marshalSize = marshalSize + 4;  // _requestStatus
   marshalSize = marshalSize + 4;  // _numberOfFixedDatumRecords
   marshalSize = marshalSize + 4;  // _numberOfVariableDatumRecords

   for(int idx=0; idx < _fixedDatums.size(); idx++)
   {
        FixedDatum listElement = _fixedDatums[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }


   for(int idx=0; idx < _variableDatums.size(); idx++)
   {
        VariableDatum listElement = _variableDatums[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

