#include <DIS/SignalPdu.h> 

using namespace DIS;


SignalPdu::SignalPdu() : RadioCommunicationsFamilyPdu(),
   _encodingScheme(0), 
   _tdlType(0), 
   _sampleRate(0), 
   _dataLength(0), 
   _samples(0)
{
    setPduType( 26 );
}

SignalPdu::~SignalPdu()
{
}

unsigned short SignalPdu::getEncodingScheme() const
{
    return _encodingScheme;
}

void SignalPdu::setEncodingScheme(unsigned short pX)
{
    _encodingScheme = pX;
}

unsigned short SignalPdu::getTdlType() const
{
    return _tdlType;
}

void SignalPdu::setTdlType(unsigned short pX)
{
    _tdlType = pX;
}

int SignalPdu::getSampleRate() const
{
    return _sampleRate;
}

void SignalPdu::setSampleRate(int pX)
{
    _sampleRate = pX;
}

short SignalPdu::getDataLength() const
{
    return _dataLength;
}

void SignalPdu::setDataLength(short pX)
{
    _dataLength = pX;
}

short SignalPdu::getSamples() const
{
    return _samples;
}

void SignalPdu::setSamples(short pX)
{
    _samples = pX;
}

void SignalPdu::marshal(DataStream& dataStream) const
{
    RadioCommunicationsFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    dataStream << _encodingScheme;
    dataStream << _tdlType;
    dataStream << _sampleRate;
    dataStream << _dataLength;
    dataStream << _samples;
}

void SignalPdu::unmarshal(DataStream& dataStream)
{
    RadioCommunicationsFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    dataStream >> _encodingScheme;
    dataStream >> _tdlType;
    dataStream >> _sampleRate;
    dataStream >> _dataLength;
    dataStream >> _samples;
}


bool SignalPdu::operator ==(const SignalPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = RadioCommunicationsFamilyPdu::operator==(rhs);

     if( ! (_encodingScheme == rhs._encodingScheme) ) ivarsEqual = false;
     if( ! (_tdlType == rhs._tdlType) ) ivarsEqual = false;
     if( ! (_sampleRate == rhs._sampleRate) ) ivarsEqual = false;
     if( ! (_dataLength == rhs._dataLength) ) ivarsEqual = false;
     if( ! (_samples == rhs._samples) ) ivarsEqual = false;

    return ivarsEqual;
 }

int SignalPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = RadioCommunicationsFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + 2;  // _encodingScheme
   marshalSize = marshalSize + 2;  // _tdlType
   marshalSize = marshalSize + 4;  // _sampleRate
   marshalSize = marshalSize + 2;  // _dataLength
   marshalSize = marshalSize + 2;  // _samples
    return marshalSize;
}

