#include <DIS/SimulationAddress.h> 

using namespace DIS;


SimulationAddress::SimulationAddress():
   _site(0), 
   _application(0)
{
}

SimulationAddress::~SimulationAddress()
{
}

unsigned short SimulationAddress::getSite() const
{
    return _site;
}

void SimulationAddress::setSite(unsigned short pX)
{
    _site = pX;
}

unsigned short SimulationAddress::getApplication() const
{
    return _application;
}

void SimulationAddress::setApplication(unsigned short pX)
{
    _application = pX;
}

void SimulationAddress::marshal(DataStream& dataStream) const
{
    dataStream << _site;
    dataStream << _application;
}

void SimulationAddress::unmarshal(DataStream& dataStream)
{
    dataStream >> _site;
    dataStream >> _application;
}


bool SimulationAddress::operator ==(const SimulationAddress& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_site == rhs._site) ) ivarsEqual = false;
     if( ! (_application == rhs._application) ) ivarsEqual = false;

    return ivarsEqual;
 }

int SimulationAddress::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 2;  // _site
   marshalSize = marshalSize + 2;  // _application
    return marshalSize;
}

