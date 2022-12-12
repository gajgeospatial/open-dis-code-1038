#include <DIS/AggregateMarking.h> 

using namespace DIS;


AggregateMarking::AggregateMarking():
   _characterSet(0)
{
}

AggregateMarking::~AggregateMarking()
{
}

unsigned char AggregateMarking::getCharacterSet() const
{
    return _characterSet;
}

void AggregateMarking::setCharacterSet(unsigned char pX)
{
    _characterSet = pX;
}

char* AggregateMarking::getCharacters() 
{
    return _characters;
}

const char* AggregateMarking::getCharacters() const
{
    return _characters;
}

void AggregateMarking::setCharacters(const char* x)
{
   for(int i = 0; i < 31; i++)
   {
        _characters[i] = x[i];
   }
}

void AggregateMarking::marshal(DataStream& dataStream) const
{
    dataStream << _characterSet;

     for(size_t idx = 0; idx < 31; idx++)
     {
        dataStream << _characters[idx];
     }

}

void AggregateMarking::unmarshal(DataStream& dataStream)
{
    dataStream >> _characterSet;

     for(size_t idx = 0; idx < 31; idx++)
     {
        dataStream >> _characters[idx];
     }

}


bool AggregateMarking::operator ==(const AggregateMarking& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_characterSet == rhs._characterSet) ) ivarsEqual = false;

     for(char idx = 0; idx < 31; idx++)
     {
          if(!(_characters[idx] == rhs._characters[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int AggregateMarking::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 1;  // _characterSet
   marshalSize = marshalSize + 31 * 1;  // _characters
    return marshalSize;
}

