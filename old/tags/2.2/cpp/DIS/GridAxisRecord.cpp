#include <DIS/GridAxisRecord.h> 

using namespace DIS;


GridAxisRecord::GridAxisRecord():
   _sampleType(0), 
   _dataRepresentation(0)
{
}

GridAxisRecord::~GridAxisRecord()
{
}

unsigned short GridAxisRecord::getSampleType() const
{
    return _sampleType;
}

void GridAxisRecord::setSampleType(unsigned short pX)
{
    _sampleType = pX;
}

unsigned short GridAxisRecord::getDataRepresentation() const
{
    return _dataRepresentation;
}

void GridAxisRecord::setDataRepresentation(unsigned short pX)
{
    _dataRepresentation = pX;
}

void GridAxisRecord::marshal(DataStream& dataStream) const
{
    dataStream << _sampleType;
    dataStream << _dataRepresentation;
}

void GridAxisRecord::unmarshal(DataStream& dataStream)
{
    dataStream >> _sampleType;
    dataStream >> _dataRepresentation;
}


bool GridAxisRecord::operator ==(const GridAxisRecord& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_sampleType == rhs._sampleType) ) ivarsEqual = false;
     if( ! (_dataRepresentation == rhs._dataRepresentation) ) ivarsEqual = false;

    return ivarsEqual;
 }

int GridAxisRecord::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 2;  // _sampleType
   marshalSize = marshalSize + 2;  // _dataRepresentation
    return marshalSize;
}

