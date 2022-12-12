#include <DIS/DeadReckoningParameter.h> 

using namespace DIS;


DeadReckoningParameter::DeadReckoningParameter():
   _deadReckoningAlgorithm(0), 
   _entityLinearAcceleration(), 
   _entityAngularVelocity()
{
}

DeadReckoningParameter::~DeadReckoningParameter()
{
}

unsigned char DeadReckoningParameter::getDeadReckoningAlgorithm() const
{
    return _deadReckoningAlgorithm;
}

void DeadReckoningParameter::setDeadReckoningAlgorithm(unsigned char pX)
{
    _deadReckoningAlgorithm = pX;
}

char* DeadReckoningParameter::getOtherParameters() 
{
    return _otherParameters;
}

const char* DeadReckoningParameter::getOtherParameters() const
{
    return _otherParameters;
}

void DeadReckoningParameter::setOtherParameters(const char* x)
{
   for(int i = 0; i < 15; i++)
   {
        _otherParameters[i] = x[i];
   }
}

Vector3Float& DeadReckoningParameter::getEntityLinearAcceleration() 
{
    return _entityLinearAcceleration;
}

const Vector3Float& DeadReckoningParameter::getEntityLinearAcceleration() const
{
    return _entityLinearAcceleration;
}

void DeadReckoningParameter::setEntityLinearAcceleration(const Vector3Float &pX)
{
    _entityLinearAcceleration = pX;
}

Vector3Float& DeadReckoningParameter::getEntityAngularVelocity() 
{
    return _entityAngularVelocity;
}

const Vector3Float& DeadReckoningParameter::getEntityAngularVelocity() const
{
    return _entityAngularVelocity;
}

void DeadReckoningParameter::setEntityAngularVelocity(const Vector3Float &pX)
{
    _entityAngularVelocity = pX;
}

void DeadReckoningParameter::marshal(DataStream& dataStream) const
{
    dataStream << _deadReckoningAlgorithm;

     for(size_t idx = 0; idx < 15; idx++)
     {
        dataStream << _otherParameters[idx];
     }

    _entityLinearAcceleration.marshal(dataStream);
    _entityAngularVelocity.marshal(dataStream);
}

void DeadReckoningParameter::unmarshal(DataStream& dataStream)
{
    dataStream >> _deadReckoningAlgorithm;

     for(size_t idx = 0; idx < 15; idx++)
     {
        dataStream >> _otherParameters[idx];
     }

    _entityLinearAcceleration.unmarshal(dataStream);
    _entityAngularVelocity.unmarshal(dataStream);
}


bool DeadReckoningParameter::operator ==(const DeadReckoningParameter& rhs) const
 {
     bool ivarsEqual = true;

     if( ! (_deadReckoningAlgorithm == rhs._deadReckoningAlgorithm) ) ivarsEqual = false;

     for(char idx = 0; idx < 15; idx++)
     {
          if(!(_otherParameters[idx] == rhs._otherParameters[idx]) ) ivarsEqual = false;
     }

     if( ! (_entityLinearAcceleration == rhs._entityLinearAcceleration) ) ivarsEqual = false;
     if( ! (_entityAngularVelocity == rhs._entityAngularVelocity) ) ivarsEqual = false;

    return ivarsEqual;
 }

int DeadReckoningParameter::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = marshalSize + 1;  // _deadReckoningAlgorithm
   marshalSize = marshalSize + 15 * 1;  // _otherParameters
   marshalSize = marshalSize + _entityLinearAcceleration.getMarshalledSize();  // _entityLinearAcceleration
   marshalSize = marshalSize + _entityAngularVelocity.getMarshalledSize();  // _entityAngularVelocity
    return marshalSize;
}

