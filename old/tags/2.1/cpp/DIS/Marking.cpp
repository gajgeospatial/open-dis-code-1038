#include <DIS/Marking.h> 

using namespace DIS;


Marking::Marking():
   _characterSet(0)
{
}

Marking::~Marking()
{
}

unsigned char Marking::getCharacterSet() const
{
    return _characterSet;
}

void Marking::setCharacterSet(unsigned char pX)
{
    _characterSet = pX;
}

char* Marking::getCharacters() 
{
    return _characters;
}

const char* Marking::getCharacters() const
{
    return _characters;
}

void Marking::setCharacters(const char* x)
{
   for(int i = 0; i < 11; i++)
   {
        _characters[i] = x[i];
   }
}

void Marking::marshal(DataStream& dataStream) const
{
    dataStream << _characterSet;

     for(size_t idx = 0; idx < 11; idx++)
     {
        dataStream << _characters[idx];
     }

}

void Marking::unmarshal(DataStream& dataStream)
{
    dataStream >> _characterSet;

     for(size_t idx = 0; idx < 11; idx++)
     {
        dataStream >> _characters[idx];
     }

}


bool Marking::operator ==(const Marking& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_characterSet == rhs._characterSet) ) ivarsEqual = false;

     for(char idx = 0; idx < 11; idx++)
     {
          if(!(_characters[idx] == rhs._characters[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int Marking::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 1;  // _characterSet
   marshalSize = marshalSize + 11 * 1;  // _characters
    return marshalSize;
}

