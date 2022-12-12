#include <DIS/IffAtcNavAidsLayer2Pdu.h> 

using namespace DIS;


IffAtcNavAidsLayer2Pdu::IffAtcNavAidsLayer2Pdu() : IffAtcNavAidsLayer1Pdu(),
   _layerHeader(), 
   _beamData(), 
   _secondaryOperationalData()
{
}

IffAtcNavAidsLayer2Pdu::~IffAtcNavAidsLayer2Pdu()
{
    _fundamentalIffParameters.clear();
}

LayerHeader& IffAtcNavAidsLayer2Pdu::getLayerHeader() 
{
    return _layerHeader;
}

const LayerHeader& IffAtcNavAidsLayer2Pdu::getLayerHeader() const
{
    return _layerHeader;
}

void IffAtcNavAidsLayer2Pdu::setLayerHeader(const LayerHeader &pX)
{
    _layerHeader = pX;
}

BeamData& IffAtcNavAidsLayer2Pdu::getBeamData() 
{
    return _beamData;
}

const BeamData& IffAtcNavAidsLayer2Pdu::getBeamData() const
{
    return _beamData;
}

void IffAtcNavAidsLayer2Pdu::setBeamData(const BeamData &pX)
{
    _beamData = pX;
}

BeamData& IffAtcNavAidsLayer2Pdu::getSecondaryOperationalData() 
{
    return _secondaryOperationalData;
}

const BeamData& IffAtcNavAidsLayer2Pdu::getSecondaryOperationalData() const
{
    return _secondaryOperationalData;
}

void IffAtcNavAidsLayer2Pdu::setSecondaryOperationalData(const BeamData &pX)
{
    _secondaryOperationalData = pX;
}

std::vector<FundamentalParameterDataIff>& IffAtcNavAidsLayer2Pdu::getFundamentalIffParameters() 
{
    return _fundamentalIffParameters;
}

const std::vector<FundamentalParameterDataIff>& IffAtcNavAidsLayer2Pdu::getFundamentalIffParameters() const
{
    return _fundamentalIffParameters;
}

void IffAtcNavAidsLayer2Pdu::setFundamentalIffParameters(const std::vector<FundamentalParameterDataIff>& pX)
{
     _fundamentalIffParameters = pX;
}

void IffAtcNavAidsLayer2Pdu::marshal(DataStream& dataStream) const
{
    IffAtcNavAidsLayer1Pdu::marshal(dataStream); // Marshal information in superclass first
    _layerHeader.marshal(dataStream);
    _beamData.marshal(dataStream);
    _secondaryOperationalData.marshal(dataStream);

     for(size_t idx = 0; idx < _fundamentalIffParameters.size(); idx++)
     {
        FundamentalParameterDataIff x = _fundamentalIffParameters[idx];
        x.marshal(dataStream);
     }

}

void IffAtcNavAidsLayer2Pdu::unmarshal(DataStream& dataStream)
{
    IffAtcNavAidsLayer1Pdu::unmarshal(dataStream); // unmarshal information in superclass first
    _layerHeader.unmarshal(dataStream);
    _beamData.unmarshal(dataStream);
    _secondaryOperationalData.unmarshal(dataStream);

     _fundamentalIffParameters.clear();
     for(size_t idx = 0; idx < _pad2; idx++)
     {
        FundamentalParameterDataIff x;
        x.unmarshal(dataStream);
        _fundamentalIffParameters.push_back(x);
     }
}


bool IffAtcNavAidsLayer2Pdu::operator ==(const IffAtcNavAidsLayer2Pdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = IffAtcNavAidsLayer1Pdu::operator==(rhs);

     if( ! (_layerHeader == rhs._layerHeader) ) ivarsEqual = false;
     if( ! (_beamData == rhs._beamData) ) ivarsEqual = false;
     if( ! (_secondaryOperationalData == rhs._secondaryOperationalData) ) ivarsEqual = false;

     for(size_t idx = 0; idx < _fundamentalIffParameters.size(); idx++)
     {
        if( ! ( _fundamentalIffParameters[idx] == rhs._fundamentalIffParameters[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int IffAtcNavAidsLayer2Pdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = IffAtcNavAidsLayer1Pdu::getMarshalledSize();
   marshalSize = marshalSize + _layerHeader.getMarshalledSize();  // _layerHeader
   marshalSize = marshalSize + _beamData.getMarshalledSize();  // _beamData
   marshalSize = marshalSize + _secondaryOperationalData.getMarshalledSize();  // _secondaryOperationalData

   for(int idx=0; idx < _fundamentalIffParameters.size(); idx++)
   {
        FundamentalParameterDataIff listElement = _fundamentalIffParameters[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

