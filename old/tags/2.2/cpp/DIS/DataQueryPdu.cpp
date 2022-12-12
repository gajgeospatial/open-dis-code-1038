#include <DIS/DataQueryPdu.h> 

using namespace DIS;


DataQueryPdu::DataQueryPdu() : SimulationManagementFamilyPdu(),
   _requestID(0), 
   _timeInterval(0), 
   _numberOfFixedDatumRecords(0), 
   _numberOfVariableDatumRecords(0)
{
    setPduType( 18 );
}

DataQueryPdu::~DataQueryPdu()
{
    _fixedDatums.clear();
    _variableDatums.clear();
}

unsigned int DataQueryPdu::getRequestID() const
{
    return _requestID;
}

void DataQueryPdu::setRequestID(unsigned int pX)
{
    _requestID = pX;
}

unsigned int DataQueryPdu::getTimeInterval() const
{
    return _timeInterval;
}

void DataQueryPdu::setTimeInterval(unsigned int pX)
{
    _timeInterval = pX;
}

unsigned int DataQueryPdu::getNumberOfFixedDatumRecords() const
{
   return _fixedDatums.size();
}

unsigned int DataQueryPdu::getNumberOfVariableDatumRecords() const
{
   return _variableDatums.size();
}

std::vector<FixedDatum>& DataQueryPdu::getFixedDatums() 
{
    return _fixedDatums;
}

const std::vector<FixedDatum>& DataQueryPdu::getFixedDatums() const
{
    return _fixedDatums;
}

void DataQueryPdu::setFixedDatums(const std::vector<FixedDatum>& pX)
{
     _fixedDatums = pX;
}

std::vector<VariableDatum>& DataQueryPdu::getVariableDatums() 
{
    return _variableDatums;
}

const std::vector<VariableDatum>& DataQueryPdu::getVariableDatums() const
{
    return _variableDatums;
}

void DataQueryPdu::setVariableDatums(const std::vector<VariableDatum>& pX)
{
     _variableDatums = pX;
}

void DataQueryPdu::marshal(DataStream& dataStream) const
{
    SimulationManagementFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    dataStream << _requestID;
    dataStream << _timeInterval;
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

void DataQueryPdu::unmarshal(DataStream& dataStream)
{
    SimulationManagementFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    dataStream >> _requestID;
    dataStream >> _timeInterval;
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


bool DataQueryPdu::operator ==(const DataQueryPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = SimulationManagementFamilyPdu::operator==(rhs);

     if( ! (_requestID == rhs._requestID) ) ivarsEqual = false;
     if( ! (_timeInterval == rhs._timeInterval) ) ivarsEqual = false;

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

int DataQueryPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = SimulationManagementFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + 4;  // _requestID
   marshalSize = marshalSize + 4;  // _timeInterval
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

