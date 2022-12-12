#include <DIS/TwoByteChunk.h> 

using namespace DIS;


TwoByteChunk::TwoByteChunk()
{
}

TwoByteChunk::~TwoByteChunk()
{
}

char* TwoByteChunk::getOtherParameters() 
{
    return _otherParameters;
}

const char* TwoByteChunk::getOtherParameters() const
{
    return _otherParameters;
}

void TwoByteChunk::setOtherParameters(const char* x)
{
   for(int i = 0; i < 2; i++)
   {
        _otherParameters[i] = x[i];
   }
}

void TwoByteChunk::marshal(DataStream& dataStream) const
{

     for(size_t idx = 0; idx < 2; idx++)
     {
        dataStream << _otherParameters[idx];
     }

}

void TwoByteChunk::unmarshal(DataStream& dataStream)
{

     for(size_t idx = 0; idx < 2; idx++)
     {
        dataStream >> _otherParameters[idx];
     }

}


bool TwoByteChunk::operator ==(const TwoByteChunk& rhs) const
 {
     bool ivarsEqual = true;


     for(char idx = 0; idx < 2; idx++)
     {
          if(!(_otherParameters[idx] == rhs._otherParameters[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int TwoByteChunk::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 2 * 1;  // _otherParameters
    return marshalSize;
}

