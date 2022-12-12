#include <DIS/GridAxisRecordRepresentation2.h> 

using namespace DIS;


GridAxisRecordRepresentation2::GridAxisRecordRepresentation2() : GridAxisRecord(),
   _numberOfValues(0)
{
}

GridAxisRecordRepresentation2::~GridAxisRecordRepresentation2()
{
    _dataValues.clear();
}

unsigned short GridAxisRecordRepresentation2::getNumberOfValues() const
{
   return _dataValues.size();
}

std::vector<FourByteChunk>& GridAxisRecordRepresentation2::getDataValues() 
{
    return _dataValues;
}

const std::vector<FourByteChunk>& GridAxisRecordRepresentation2::getDataValues() const
{
    return _dataValues;
}

void GridAxisRecordRepresentation2::setDataValues(const std::vector<FourByteChunk>& pX)
{
     _dataValues = pX;
}

void GridAxisRecordRepresentation2::marshal(DataStream& dataStream) const
{
    GridAxisRecord::marshal(dataStream); // Marshal information in superclass first
    dataStream << ( unsigned short )_dataValues.size();

     for(size_t idx = 0; idx < _dataValues.size(); idx++)
     {
        FourByteChunk x = _dataValues[idx];
        x.marshal(dataStream);
     }

}

void GridAxisRecordRepresentation2::unmarshal(DataStream& dataStream)
{
    GridAxisRecord::unmarshal(dataStream); // unmarshal information in superclass first
    dataStream >> _numberOfValues;

     _dataValues.clear();
     for(size_t idx = 0; idx < _numberOfValues; idx++)
     {
        FourByteChunk x;
        x.unmarshal(dataStream);
        _dataValues.push_back(x);
     }
}


bool GridAxisRecordRepresentation2::operator ==(const GridAxisRecordRepresentation2& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = GridAxisRecord::operator==(rhs);


     for(size_t idx = 0; idx < _dataValues.size(); idx++)
     {
        if( ! ( _dataValues[idx] == rhs._dataValues[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int GridAxisRecordRepresentation2::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = GridAxisRecord::getMarshalledSize();
   marshalSize = marshalSize + 2;  // _numberOfValues

   for(int idx=0; idx < _dataValues.size(); idx++)
   {
        FourByteChunk listElement = _dataValues[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

