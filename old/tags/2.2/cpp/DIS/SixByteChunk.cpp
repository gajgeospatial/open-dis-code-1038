#include <DIS/SixByteChunk.h> 

using namespace DIS;


SixByteChunk::SixByteChunk()
{
}

SixByteChunk::~SixByteChunk()
{
}

char* SixByteChunk::getOtherParameters() 
{
    return _otherParameters;
}

const char* SixByteChunk::getOtherParameters() const
{
    return _otherParameters;
}

void SixByteChunk::setOtherParameters(const char* x)
{
   for(int i = 0; i < 6; i++)
   {
        _otherParameters[i] = x[i];
   }
}

void SixByteChunk::marshal(DataStream& dataStream) const
{

     for(size_t idx = 0; idx < 6; idx++)
     {
        dataStream << _otherParameters[idx];
     }

}

void SixByteChunk::unmarshal(DataStream& dataStream)
{

     for(size_t idx = 0; idx < 6; idx++)
     {
        dataStream >> _otherParameters[idx];
     }

}


bool SixByteChunk::operator ==(const SixByteChunk& rhs) const
 {
     bool ivarsEqual = true;


     for(char idx = 0; idx < 6; idx++)
     {
          if(!(_otherParameters[idx] == rhs._otherParameters[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int SixByteChunk::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 6 * 1;  // _otherParameters
    return marshalSize;
}

