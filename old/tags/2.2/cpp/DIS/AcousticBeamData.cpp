#include <DIS/AcousticBeamData.h> 

using namespace DIS;


AcousticBeamData::AcousticBeamData():
   _beamDataLength(0), 
   _beamIDNumber(0), 
   _pad2(0), 
   _fundamentalDataParameters()
{
}

AcousticBeamData::~AcousticBeamData()
{
}

unsigned short AcousticBeamData::getBeamDataLength() const
{
    return _beamDataLength;
}

void AcousticBeamData::setBeamDataLength(unsigned short pX)
{
    _beamDataLength = pX;
}

unsigned char AcousticBeamData::getBeamIDNumber() const
{
    return _beamIDNumber;
}

void AcousticBeamData::setBeamIDNumber(unsigned char pX)
{
    _beamIDNumber = pX;
}

unsigned short AcousticBeamData::getPad2() const
{
    return _pad2;
}

void AcousticBeamData::setPad2(unsigned short pX)
{
    _pad2 = pX;
}

AcousticBeamFundamentalParameter& AcousticBeamData::getFundamentalDataParameters() 
{
    return _fundamentalDataParameters;
}

const AcousticBeamFundamentalParameter& AcousticBeamData::getFundamentalDataParameters() const
{
    return _fundamentalDataParameters;
}

void AcousticBeamData::setFundamentalDataParameters(const AcousticBeamFundamentalParameter &pX)
{
    _fundamentalDataParameters = pX;
}

void AcousticBeamData::marshal(DataStream& dataStream) const
{
    dataStream << _beamDataLength;
    dataStream << _beamIDNumber;
    dataStream << _pad2;
    _fundamentalDataParameters.marshal(dataStream);
}

void AcousticBeamData::unmarshal(DataStream& dataStream)
{
    dataStream >> _beamDataLength;
    dataStream >> _beamIDNumber;
    dataStream >> _pad2;
    _fundamentalDataParameters.unmarshal(dataStream);
}


bool AcousticBeamData::operator ==(const AcousticBeamData& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_beamDataLength == rhs._beamDataLength) ) ivarsEqual = false;
     if( ! (_beamIDNumber == rhs._beamIDNumber) ) ivarsEqual = false;
     if( ! (_pad2 == rhs._pad2) ) ivarsEqual = false;
     if( ! (_fundamentalDataParameters == rhs._fundamentalDataParameters) ) ivarsEqual = false;

    return ivarsEqual;
 }

int AcousticBeamData::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 2;  // _beamDataLength
   marshalSize = marshalSize + 1;  // _beamIDNumber
   marshalSize = marshalSize + 2;  // _pad2
   marshalSize = marshalSize + _fundamentalDataParameters.getMarshalledSize();  // _fundamentalDataParameters
    return marshalSize;
}

