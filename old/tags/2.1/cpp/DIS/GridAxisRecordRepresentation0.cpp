#include <DIS/GridAxisRecordRepresentation0.h> 

using namespace DIS;


GridAxisRecordRepresentation0::GridAxisRecordRepresentation0() : GridAxisRecord(),
   _numberOfBytes(0)
{
}

GridAxisRecordRepresentation0::~GridAxisRecordRepresentation0()
{
    _dataValues.clear();
}

unsigned short GridAxisRecordRepresentation0::getNumberOfBytes() const
{
   return _dataValues.size();
}

std::vector<OneByteChunk>& GridAxisRecordRepresentation0::getDataValues() 
{
    return _dataValues;
}

const std::vector<OneByteChunk>& GridAxisRecordRepresentation0::getDataValues() const
{
    return _dataValues;
}

void GridAxisRecordRepresentation0::setDataValues(const std::vector<OneByteChunk>& pX)
{
     _dataValues = pX;
}

void GridAxisRecordRepresentation0::marshal(DataStream& dataStream) const
{
    GridAxisRecord::marshal(dataStream); // Marshal information in superclass first
    dataStream << ( unsigned short )_dataValues.size();

     for(size_t idx = 0; idx < _dataValues.size(); idx++)
     {
        OneByteChunk x = _dataValues[idx];
        x.marshal(dataStream);
     }

}

void GridAxisRecordRepresentation0::unmarshal(DataStream& dataStream)
{
    GridAxisRecord::unmarshal(dataStream); // unmarshal information in superclass first
    dataStream >> _numberOfBytes;

     _dataValues.clear();
     for(size_t idx = 0; idx < _numberOfBytes; idx++)
     {
        OneByteChunk x;
        x.unmarshal(dataStream);
        _dataValues.push_back(x);
     }
}


bool GridAxisRecordRepresentation0::operator ==(const GridAxisRecordRepresentation0& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = GridAxisRecord::operator==(rhs);


     for(size_t idx = 0; idx < _dataValues.size(); idx++)
     {
        if( ! ( _dataValues[idx] == rhs._dataValues[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int GridAxisRecordRepresentation0::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = GridAxisRecord::getMarshalledSize();
   marshalSize = marshalSize + 2;  // _numberOfBytes

   for(int idx=0; idx < _dataValues.size(); idx++)
   {
        OneByteChunk listElement = _dataValues[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

