#include <DIS/RecordQueryReliablePdu.h> 

using namespace DIS;


RecordQueryReliablePdu::RecordQueryReliablePdu() : SimulationManagementWithReliabilityFamilyPdu(),
   _requestID(0), 
   _requiredReliabilityService(0), 
   _pad1(0), 
   _pad2(0), 
   _eventType(0), 
   _time(0), 
   _numberOfRecords(0)
{
    setPduType( 63 );
}

RecordQueryReliablePdu::~RecordQueryReliablePdu()
{
    _recordIDs.clear();
}

unsigned int RecordQueryReliablePdu::getRequestID() const
{
    return _requestID;
}

void RecordQueryReliablePdu::setRequestID(unsigned int pX)
{
    _requestID = pX;
}

unsigned char RecordQueryReliablePdu::getRequiredReliabilityService() const
{
    return _requiredReliabilityService;
}

void RecordQueryReliablePdu::setRequiredReliabilityService(unsigned char pX)
{
    _requiredReliabilityService = pX;
}

unsigned short RecordQueryReliablePdu::getPad1() const
{
    return _pad1;
}

void RecordQueryReliablePdu::setPad1(unsigned short pX)
{
    _pad1 = pX;
}

unsigned char RecordQueryReliablePdu::getPad2() const
{
    return _pad2;
}

void RecordQueryReliablePdu::setPad2(unsigned char pX)
{
    _pad2 = pX;
}

unsigned short RecordQueryReliablePdu::getEventType() const
{
    return _eventType;
}

void RecordQueryReliablePdu::setEventType(unsigned short pX)
{
    _eventType = pX;
}

unsigned int RecordQueryReliablePdu::getTime() const
{
    return _time;
}

void RecordQueryReliablePdu::setTime(unsigned int pX)
{
    _time = pX;
}

unsigned int RecordQueryReliablePdu::getNumberOfRecords() const
{
   return _recordIDs.size();
}

std::vector<FourByteChunk>& RecordQueryReliablePdu::getRecordIDs() 
{
    return _recordIDs;
}

const std::vector<FourByteChunk>& RecordQueryReliablePdu::getRecordIDs() const
{
    return _recordIDs;
}

void RecordQueryReliablePdu::setRecordIDs(const std::vector<FourByteChunk>& pX)
{
     _recordIDs = pX;
}

void RecordQueryReliablePdu::marshal(DataStream& dataStream) const
{
    SimulationManagementWithReliabilityFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    dataStream << _requestID;
    dataStream << _requiredReliabilityService;
    dataStream << _pad1;
    dataStream << _pad2;
    dataStream << _eventType;
    dataStream << _time;
    dataStream << ( unsigned int )_recordIDs.size();

     for(size_t idx = 0; idx < _recordIDs.size(); idx++)
     {
        FourByteChunk x = _recordIDs[idx];
        x.marshal(dataStream);
     }

}

void RecordQueryReliablePdu::unmarshal(DataStream& dataStream)
{
    SimulationManagementWithReliabilityFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    dataStream >> _requestID;
    dataStream >> _requiredReliabilityService;
    dataStream >> _pad1;
    dataStream >> _pad2;
    dataStream >> _eventType;
    dataStream >> _time;
    dataStream >> _numberOfRecords;

     _recordIDs.clear();
     for(size_t idx = 0; idx < _numberOfRecords; idx++)
     {
        FourByteChunk x;
        x.unmarshal(dataStream);
        _recordIDs.push_back(x);
     }
}


bool RecordQueryReliablePdu::operator ==(const RecordQueryReliablePdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = SimulationManagementWithReliabilityFamilyPdu::operator==(rhs);

     if( ! (_requestID == rhs._requestID) ) ivarsEqual = false;
     if( ! (_requiredReliabilityService == rhs._requiredReliabilityService) ) ivarsEqual = false;
     if( ! (_pad1 == rhs._pad1) ) ivarsEqual = false;
     if( ! (_pad2 == rhs._pad2) ) ivarsEqual = false;
     if( ! (_eventType == rhs._eventType) ) ivarsEqual = false;
     if( ! (_time == rhs._time) ) ivarsEqual = false;

     for(size_t idx = 0; idx < _recordIDs.size(); idx++)
     {
        if( ! ( _recordIDs[idx] == rhs._recordIDs[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int RecordQueryReliablePdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = SimulationManagementWithReliabilityFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + 4;  // _requestID
   marshalSize = marshalSize + 1;  // _requiredReliabilityService
   marshalSize = marshalSize + 2;  // _pad1
   marshalSize = marshalSize + 1;  // _pad2
   marshalSize = marshalSize + 2;  // _eventType
   marshalSize = marshalSize + 4;  // _time
   marshalSize = marshalSize + 4;  // _numberOfRecords

   for(int idx=0; idx < _recordIDs.size(); idx++)
   {
        FourByteChunk listElement = _recordIDs[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

