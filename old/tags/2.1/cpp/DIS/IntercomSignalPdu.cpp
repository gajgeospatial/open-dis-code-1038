#include <DIS/IntercomSignalPdu.h> 

using namespace DIS;


IntercomSignalPdu::IntercomSignalPdu() : RadioCommunicationsFamilyPdu(),
   _entityID(), 
   _communicationsDeviceID(0), 
   _encodingScheme(0), 
   _TdlType(0), 
   _sampleRate(0), 
   _dataLength(0), 
   _samples(0)
{
    setPduType( 31 );
}

IntercomSignalPdu::~IntercomSignalPdu()
{
    _data.clear();
}

EntityID& IntercomSignalPdu::getEntityID() 
{
    return _entityID;
}

const EntityID& IntercomSignalPdu::getEntityID() const
{
    return _entityID;
}

void IntercomSignalPdu::setEntityID(const EntityID &pX)
{
    _entityID = pX;
}

unsigned short IntercomSignalPdu::getCommunicationsDeviceID() const
{
    return _communicationsDeviceID;
}

void IntercomSignalPdu::setCommunicationsDeviceID(unsigned short pX)
{
    _communicationsDeviceID = pX;
}

unsigned short IntercomSignalPdu::getEncodingScheme() const
{
    return _encodingScheme;
}

void IntercomSignalPdu::setEncodingScheme(unsigned short pX)
{
    _encodingScheme = pX;
}

unsigned short IntercomSignalPdu::getTdlType() const
{
    return _TdlType;
}

void IntercomSignalPdu::setTdlType(unsigned short pX)
{
    _TdlType = pX;
}

unsigned int IntercomSignalPdu::getSampleRate() const
{
    return _sampleRate;
}

void IntercomSignalPdu::setSampleRate(unsigned int pX)
{
    _sampleRate = pX;
}

unsigned short IntercomSignalPdu::getDataLength() const
{
   return _data.size();
}

unsigned short IntercomSignalPdu::getSamples() const
{
    return _samples;
}

void IntercomSignalPdu::setSamples(unsigned short pX)
{
    _samples = pX;
}

std::vector<OneByteChunk>& IntercomSignalPdu::getData() 
{
    return _data;
}

const std::vector<OneByteChunk>& IntercomSignalPdu::getData() const
{
    return _data;
}

void IntercomSignalPdu::setData(const std::vector<OneByteChunk>& pX)
{
     _data = pX;
}

void IntercomSignalPdu::marshal(DataStream& dataStream) const
{
    RadioCommunicationsFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    _entityID.marshal(dataStream);
    dataStream << _communicationsDeviceID;
    dataStream << _encodingScheme;
    dataStream << _TdlType;
    dataStream << _sampleRate;
    dataStream << ( unsigned short )_data.size();
    dataStream << _samples;

     for(size_t idx = 0; idx < _data.size(); idx++)
     {
        OneByteChunk x = _data[idx];
        x.marshal(dataStream);
     }

}

void IntercomSignalPdu::unmarshal(DataStream& dataStream)
{
    RadioCommunicationsFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    _entityID.unmarshal(dataStream);
    dataStream >> _communicationsDeviceID;
    dataStream >> _encodingScheme;
    dataStream >> _TdlType;
    dataStream >> _sampleRate;
    dataStream >> _dataLength;
    dataStream >> _samples;

     _data.clear();
     for(size_t idx = 0; idx < _dataLength; idx++)
     {
        OneByteChunk x;
        x.unmarshal(dataStream);
        _data.push_back(x);
     }
}


bool IntercomSignalPdu::operator ==(const IntercomSignalPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = RadioCommunicationsFamilyPdu::operator==(rhs);

     if( ! (_entityID == rhs._entityID) ) ivarsEqual = false;
     if( ! (_communicationsDeviceID == rhs._communicationsDeviceID) ) ivarsEqual = false;
     if( ! (_encodingScheme == rhs._encodingScheme) ) ivarsEqual = false;
     if( ! (_TdlType == rhs._TdlType) ) ivarsEqual = false;
     if( ! (_sampleRate == rhs._sampleRate) ) ivarsEqual = false;
     if( ! (_samples == rhs._samples) ) ivarsEqual = false;

     for(size_t idx = 0; idx < _data.size(); idx++)
     {
        if( ! ( _data[idx] == rhs._data[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int IntercomSignalPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = RadioCommunicationsFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + _entityID.getMarshalledSize();  // _entityID
   marshalSize = marshalSize + 2;  // _communicationsDeviceID
   marshalSize = marshalSize + 2;  // _encodingScheme
   marshalSize = marshalSize + 2;  // _TdlType
   marshalSize = marshalSize + 4;  // _sampleRate
   marshalSize = marshalSize + 2;  // _dataLength
   marshalSize = marshalSize + 2;  // _samples

   for(int idx=0; idx < _data.size(); idx++)
   {
        OneByteChunk listElement = _data[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

