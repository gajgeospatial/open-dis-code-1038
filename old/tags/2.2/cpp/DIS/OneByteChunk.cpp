#include <DIS/OneByteChunk.h> 

using namespace DIS;


OneByteChunk::OneByteChunk()
{
}

OneByteChunk::~OneByteChunk()
{
}

char* OneByteChunk::getOtherParameters() 
{
    return _otherParameters;
}

const char* OneByteChunk::getOtherParameters() const
{
    return _otherParameters;
}

void OneByteChunk::setOtherParameters(const char* x)
{
   for(int i = 0; i < 1; i++)
   {
        _otherParameters[i] = x[i];
   }
}

void OneByteChunk::marshal(DataStream& dataStream) const
{

     for(size_t idx = 0; idx < 1; idx++)
     {
        dataStream << _otherParameters[idx];
     }

}

void OneByteChunk::unmarshal(DataStream& dataStream)
{

     for(size_t idx = 0; idx < 1; idx++)
     {
        dataStream >> _otherParameters[idx];
     }

}


bool OneByteChunk::operator ==(const OneByteChunk& rhs) const
 {
     bool ivarsEqual = true;


     for(char idx = 0; idx < 1; idx++)
     {
          if(!(_otherParameters[idx] == rhs._otherParameters[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int OneByteChunk::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 1 * 1;  // _otherParameters
    return marshalSize;
}

