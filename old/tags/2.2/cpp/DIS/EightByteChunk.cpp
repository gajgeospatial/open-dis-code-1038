#include <DIS/EightByteChunk.h> 

using namespace DIS;


EightByteChunk::EightByteChunk()
{
}

EightByteChunk::~EightByteChunk()
{
}

char* EightByteChunk::getOtherParameters() 
{
    return _otherParameters;
}

const char* EightByteChunk::getOtherParameters() const
{
    return _otherParameters;
}

void EightByteChunk::setOtherParameters(const char* x)
{
   for(int i = 0; i < 8; i++)
   {
        _otherParameters[i] = x[i];
   }
}

void EightByteChunk::marshal(DataStream& dataStream) const
{

     for(size_t idx = 0; idx < 8; idx++)
     {
        dataStream << _otherParameters[idx];
     }

}

void EightByteChunk::unmarshal(DataStream& dataStream)
{

     for(size_t idx = 0; idx < 8; idx++)
     {
        dataStream >> _otherParameters[idx];
     }

}


bool EightByteChunk::operator ==(const EightByteChunk& rhs) const
 {
     bool ivarsEqual = true;


     for(char idx = 0; idx < 8; idx++)
     {
          if(!(_otherParameters[idx] == rhs._otherParameters[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int EightByteChunk::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 8 * 1;  // _otherParameters
    return marshalSize;
}

