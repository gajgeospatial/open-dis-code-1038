#include <DIS/VariableDatum.h> 

using namespace DIS;


VariableDatum::VariableDatum():
   _variableDatumID(0), 
   _variableDatumLength(0)
{
}

VariableDatum::~VariableDatum()
{
    _variableDatums.clear();
}

unsigned int VariableDatum::getVariableDatumID() const
{
    return _variableDatumID;
}

void VariableDatum::setVariableDatumID(unsigned int pX)
{
    _variableDatumID = pX;
}

unsigned int VariableDatum::getVariableDatumLength() const
{
   return _variableDatums.size();
}

std::vector<EightByteChunk>& VariableDatum::getVariableDatums() 
{
    return _variableDatums;
}

const std::vector<EightByteChunk>& VariableDatum::getVariableDatums() const
{
    return _variableDatums;
}

void VariableDatum::setVariableDatums(const std::vector<EightByteChunk>& pX)
{
     _variableDatums = pX;
}

void VariableDatum::marshal(DataStream& dataStream) const
{
    dataStream << _variableDatumID;
    dataStream << ( unsigned int )_variableDatums.size();

     for(size_t idx = 0; idx < _variableDatums.size(); idx++)
     {
        EightByteChunk x = _variableDatums[idx];
        x.marshal(dataStream);
     }

}

void VariableDatum::unmarshal(DataStream& dataStream)
{
    dataStream >> _variableDatumID;
    dataStream >> _variableDatumLength;

     _variableDatums.clear();
     for(size_t idx = 0; idx < _variableDatumLength; idx++)
     {
        EightByteChunk x;
        x.unmarshal(dataStream);
        _variableDatums.push_back(x);
     }
}


bool VariableDatum::operator ==(const VariableDatum& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_variableDatumID == rhs._variableDatumID) ) ivarsEqual = false;

     for(size_t idx = 0; idx < _variableDatums.size(); idx++)
     {
        if( ! ( _variableDatums[idx] == rhs._variableDatums[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int VariableDatum::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 4;  // _variableDatumID
   marshalSize = marshalSize + 4;  // _variableDatumLength

   for(int idx=0; idx < _variableDatums.size(); idx++)
   {
        EightByteChunk listElement = _variableDatums[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

