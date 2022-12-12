#include <DIS/LayerHeader.h> 

using namespace DIS;


LayerHeader::LayerHeader():
   _layerNumber(0), 
   _layerSpecificInformaiton(0), 
   _length(0)
{
}

LayerHeader::~LayerHeader()
{
}

unsigned char LayerHeader::getLayerNumber() const
{
    return _layerNumber;
}

void LayerHeader::setLayerNumber(unsigned char pX)
{
    _layerNumber = pX;
}

unsigned char LayerHeader::getLayerSpecificInformaiton() const
{
    return _layerSpecificInformaiton;
}

void LayerHeader::setLayerSpecificInformaiton(unsigned char pX)
{
    _layerSpecificInformaiton = pX;
}

unsigned short LayerHeader::getLength() const
{
    return _length;
}

void LayerHeader::setLength(unsigned short pX)
{
    _length = pX;
}

void LayerHeader::marshal(DataStream& dataStream) const
{
    dataStream << _layerNumber;
    dataStream << _layerSpecificInformaiton;
    dataStream << _length;
}

void LayerHeader::unmarshal(DataStream& dataStream)
{
    dataStream >> _layerNumber;
    dataStream >> _layerSpecificInformaiton;
    dataStream >> _length;
}


bool LayerHeader::operator ==(const LayerHeader& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_layerNumber == rhs._layerNumber) ) ivarsEqual = false;
     if( ! (_layerSpecificInformaiton == rhs._layerSpecificInformaiton) ) ivarsEqual = false;
     if( ! (_length == rhs._length) ) ivarsEqual = false;

    return ivarsEqual;
 }

int LayerHeader::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 1;  // _layerNumber
   marshalSize = marshalSize + 1;  // _layerSpecificInformaiton
   marshalSize = marshalSize + 2;  // _length
    return marshalSize;
}

