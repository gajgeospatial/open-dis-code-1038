#include <DIS/MinefieldResponseNackPdu.h> 

using namespace DIS;


MinefieldResponseNackPdu::MinefieldResponseNackPdu() : MinfieldFamilyPdu(),
   _minefieldID(), 
   _requestingEntityID(), 
   _requestID(0), 
   _numberOfMissingPdus(0)
{
    setPduType( 40 );
}

MinefieldResponseNackPdu::~MinefieldResponseNackPdu()
{
    _missingPduSequenceNumbers.clear();
}

EntityID& MinefieldResponseNackPdu::getMinefieldID() 
{
    return _minefieldID;
}

const EntityID& MinefieldResponseNackPdu::getMinefieldID() const
{
    return _minefieldID;
}

void MinefieldResponseNackPdu::setMinefieldID(const EntityID &pX)
{
    _minefieldID = pX;
}

EntityID& MinefieldResponseNackPdu::getRequestingEntityID() 
{
    return _requestingEntityID;
}

const EntityID& MinefieldResponseNackPdu::getRequestingEntityID() const
{
    return _requestingEntityID;
}

void MinefieldResponseNackPdu::setRequestingEntityID(const EntityID &pX)
{
    _requestingEntityID = pX;
}

unsigned char MinefieldResponseNackPdu::getRequestID() const
{
    return _requestID;
}

void MinefieldResponseNackPdu::setRequestID(unsigned char pX)
{
    _requestID = pX;
}

unsigned char MinefieldResponseNackPdu::getNumberOfMissingPdus() const
{
   return _missingPduSequenceNumbers.size();
}

std::vector<EightByteChunk>& MinefieldResponseNackPdu::getMissingPduSequenceNumbers() 
{
    return _missingPduSequenceNumbers;
}

const std::vector<EightByteChunk>& MinefieldResponseNackPdu::getMissingPduSequenceNumbers() const
{
    return _missingPduSequenceNumbers;
}

void MinefieldResponseNackPdu::setMissingPduSequenceNumbers(const std::vector<EightByteChunk>& pX)
{
     _missingPduSequenceNumbers = pX;
}

void MinefieldResponseNackPdu::marshal(DataStream& dataStream) const
{
    MinfieldFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _minefieldID.marshal(dataStream);
    _requestingEntityID.marshal(dataStream);
    dataStream << _requestID;
    dataStream << ( unsigned char )_missingPduSequenceNumbers.size();

     for(size_t idx = 0; idx < _missingPduSequenceNumbers.size(); idx++)
     {
        EightByteChunk x = _missingPduSequenceNumbers[idx];
        x.marshal(dataStream);
     }

}

void MinefieldResponseNackPdu::unmarshal(DataStream& dataStream)
{
    MinfieldFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _minefieldID.unmarshal(dataStream);
    _requestingEntityID.unmarshal(dataStream);
    dataStream >> _requestID;
    dataStream >> _numberOfMissingPdus;

     _missingPduSequenceNumbers.clear();
     for(size_t idx = 0; idx < _numberOfMissingPdus; idx++)
     {
        EightByteChunk x;
        x.unmarshal(dataStream);
        _missingPduSequenceNumbers.push_back(x);
     }
}


bool MinefieldResponseNackPdu::operator ==(const MinefieldResponseNackPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = MinfieldFamilyPdu::operator==(rhs);

     if( ! (_minefieldID == rhs._minefieldID) ) ivarsEqual = false;
     if( ! (_requestingEntityID == rhs._requestingEntityID) ) ivarsEqual = false;
     if( ! (_requestID == rhs._requestID) ) ivarsEqual = false;

     for(size_t idx = 0; idx < _missingPduSequenceNumbers.size(); idx++)
     {
        if( ! ( _missingPduSequenceNumbers[idx] == rhs._missingPduSequenceNumbers[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int MinefieldResponseNackPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = MinfieldFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _minefieldID.getMarshalledSize();  // _minefieldID
   marshalSize = marshalSize + _requestingEntityID.getMarshalledSize();  // _requestingEntityID
   marshalSize = marshalSize + 1;  // _requestID
   marshalSize = marshalSize + 1;  // _numberOfMissingPdus

   for(int idx=0; idx < _missingPduSequenceNumbers.size(); idx++)
   {
        EightByteChunk listElement = _missingPduSequenceNumbers[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

