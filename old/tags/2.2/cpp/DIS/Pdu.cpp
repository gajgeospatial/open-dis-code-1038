#include <DIS/Pdu.h> 

using namespace DIS;


Pdu::Pdu():
   _protocolVersion(6), 
   _exerciseID(0), 
   _pduType(0), 
   _protocolFamily(0), 
   _timestamp(0), 
   _length(0), 
   _padding(0)
{
}

Pdu::~Pdu()
{
}

unsigned char Pdu::getProtocolVersion() const
{
    return _protocolVersion;
}

void Pdu::setProtocolVersion(unsigned char pX)
{
    _protocolVersion = pX;
}

unsigned char Pdu::getExerciseID() const
{
    return _exerciseID;
}

void Pdu::setExerciseID(unsigned char pX)
{
    _exerciseID = pX;
}

unsigned char Pdu::getPduType() const
{
    return _pduType;
}

void Pdu::setPduType(unsigned char pX)
{
    _pduType = pX;
}

unsigned char Pdu::getProtocolFamily() const
{
    return _protocolFamily;
}

void Pdu::setProtocolFamily(unsigned char pX)
{
    _protocolFamily = pX;
}

unsigned int Pdu::getTimestamp() const
{
    return _timestamp;
}

void Pdu::setTimestamp(unsigned int pX)
{
    _timestamp = pX;
}

unsigned short Pdu::getLength() const
{
    return _length;
}

void Pdu::setLength(unsigned short pX)
{
    _length = pX;
}

short Pdu::getPadding() const
{
    return _padding;
}

void Pdu::setPadding(short pX)
{
    _padding = pX;
}

void Pdu::marshal(DataStream& dataStream) const
{
    dataStream << _protocolVersion;
    dataStream << _exerciseID;
    dataStream << _pduType;
    dataStream << _protocolFamily;
    dataStream << _timestamp;
    dataStream << _length;
    dataStream << _padding;
}

void Pdu::unmarshal(DataStream& dataStream)
{
    dataStream >> _protocolVersion;
    dataStream >> _exerciseID;
    dataStream >> _pduType;
    dataStream >> _protocolFamily;
    dataStream >> _timestamp;
    dataStream >> _length;
    dataStream >> _padding;
}


bool Pdu::operator ==(const Pdu& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_protocolVersion == rhs._protocolVersion) ) ivarsEqual = false;
     if( ! (_exerciseID == rhs._exerciseID) ) ivarsEqual = false;
     if( ! (_pduType == rhs._pduType) ) ivarsEqual = false;
     if( ! (_protocolFamily == rhs._protocolFamily) ) ivarsEqual = false;
     if( ! (_timestamp == rhs._timestamp) ) ivarsEqual = false;
     if( ! (_length == rhs._length) ) ivarsEqual = false;
     if( ! (_padding == rhs._padding) ) ivarsEqual = false;

    return ivarsEqual;
 }

int Pdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 1;  // _protocolVersion
   marshalSize = marshalSize + 1;  // _exerciseID
   marshalSize = marshalSize + 1;  // _pduType
   marshalSize = marshalSize + 1;  // _protocolFamily
   marshalSize = marshalSize + 4;  // _timestamp
   marshalSize = marshalSize + 2;  // _length
   marshalSize = marshalSize + 2;  // _padding
    return marshalSize;
}

