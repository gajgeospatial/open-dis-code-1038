#include <DIS/GridAxisRecordRepresentation1.h> 

using namespace DIS;


GridAxisRecordRepresentation1::GridAxisRecordRepresentation1() : GridAxisRecord(),
   _fieldScale(0.0), 
   _fieldOffset(0.0), 
   _numberOfValues(0)
{
}

GridAxisRecordRepresentation1::~GridAxisRecordRepresentation1()
{
    _dataValues.clear();
}

float GridAxisRecordRepresentation1::getFieldScale() const
{
    return _fieldScale;
}

void GridAxisRecordRepresentation1::setFieldScale(float pX)
{
    _fieldScale = pX;
}

float GridAxisRecordRepresentation1::getFieldOffset() const
{
    return _fieldOffset;
}

void GridAxisRecordRepresentation1::setFieldOffset(float pX)
{
    _fieldOffset = pX;
}

unsigned short GridAxisRecordRepresentation1::getNumberOfValues() const
{
   return _dataValues.size();
}

std::vector<TwoByteChunk>& GridAxisRecordRepresentation1::getDataValues() 
{
    return _dataValues;
}

const std::vector<TwoByteChunk>& GridAxisRecordRepresentation1::getDataValues() const
{
    return _dataValues;
}

void GridAxisRecordRepresentation1::setDataValues(const std::vector<TwoByteChunk>& pX)
{
     _dataValues = pX;
}

void GridAxisRecordRepresentation1::marshal(DataStream& dataStream) const
{
    GridAxisRecord::marshal(dataStream); // Marshal information in superclass first
    dataStream << _fieldScale;
    dataStream << _fieldOffset;
    dataStream << ( unsigned short )_dataValues.size();

     for(size_t idx = 0; idx < _dataValues.size(); idx++)
     {
        TwoByteChunk x = _dataValues[idx];
        x.marshal(dataStream);
     }

}

void GridAxisRecordRepresentation1::unmarshal(DataStream& dataStream)
{
    GridAxisRecord::unmarshal(dataStream); // unmarshal information in superclass first
    dataStream >> _fieldScale;
    dataStream >> _fieldOffset;
    dataStream >> _numberOfValues;

     _dataValues.clear();
     for(size_t idx = 0; idx < _numberOfValues; idx++)
     {
        TwoByteChunk x;
        x.unmarshal(dataStream);
        _dataValues.push_back(x);
     }
}


bool GridAxisRecordRepresentation1::operator ==(const GridAxisRecordRepresentation1& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = GridAxisRecord::operator==(rhs);

     if( ! (_fieldScale == rhs._fieldScale) ) ivarsEqual = false;
     if( ! (_fieldOffset == rhs._fieldOffset) ) ivarsEqual = false;

     for(size_t idx = 0; idx < _dataValues.size(); idx++)
     {
        if( ! ( _dataValues[idx] == rhs._dataValues[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int GridAxisRecordRepresentation1::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = GridAxisRecord::getMarshalledSize();
   marshalSize = marshalSize + 4;  // _fieldScale
   marshalSize = marshalSize + 4;  // _fieldOffset
   marshalSize = marshalSize + 2;  // _numberOfValues

   for(int idx=0; idx < _dataValues.size(); idx++)
   {
        TwoByteChunk listElement = _dataValues[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

