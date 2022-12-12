#include <DIS/FourByteChunk.h> 

using namespace DIS;


FourByteChunk::FourByteChunk()
{
}

FourByteChunk::~FourByteChunk()
{
}

char* FourByteChunk::getOtherParameters() 
{
    return _otherParameters;
}

const char* FourByteChunk::getOtherParameters() const
{
    return _otherParameters;
}

void FourByteChunk::setOtherParameters(const char* x)
{
   for(int i = 0; i < 4; i++)
   {
        _otherParameters[i] = x[i];
   }
}

void FourByteChunk::marshal(DataStream& dataStream) const
{

     for(size_t idx = 0; idx < 4; idx++)
     {
        dataStream << _otherParameters[idx];
     }

}

void FourByteChunk::unmarshal(DataStream& dataStream)
{

     for(size_t idx = 0; idx < 4; idx++)
     {
        dataStream >> _otherParameters[idx];
     }

}


bool FourByteChunk::operator ==(const FourByteChunk& rhs) const
 {
     bool ivarsEqual = true;


     for(char idx = 0; idx < 4; idx++)
     {
          if(!(_otherParameters[idx] == rhs._otherParameters[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int FourByteChunk::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 4 * 1;  // _otherParameters
    return marshalSize;
}

