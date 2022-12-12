#include <DIS/WarfareFamilyPdu.h> 

using namespace DIS;


WarfareFamilyPdu::WarfareFamilyPdu() : Pdu(),
   _firingEntityID(), 
   _targetEntityID()
{
    setProtocolFamily( 2 );
}

WarfareFamilyPdu::~WarfareFamilyPdu()
{
}

EntityID& WarfareFamilyPdu::getFiringEntityID() 
{
    return _firingEntityID;
}

const EntityID& WarfareFamilyPdu::getFiringEntityID() const
{
    return _firingEntityID;
}

void WarfareFamilyPdu::setFiringEntityID(const EntityID &pX)
{
    _firingEntityID = pX;
}

EntityID& WarfareFamilyPdu::getTargetEntityID() 
{
    return _targetEntityID;
}

const EntityID& WarfareFamilyPdu::getTargetEntityID() const
{
    return _targetEntityID;
}

void WarfareFamilyPdu::setTargetEntityID(const EntityID &pX)
{
    _targetEntityID = pX;
}

void WarfareFamilyPdu::marshal(DataStream& dataStream) const
{
    Pdu::marshal(dataStream); // Marshal information in superclass first
    _firingEntityID.marshal(dataStream);
    _targetEntityID.marshal(dataStream);
}

void WarfareFamilyPdu::unmarshal(DataStream& dataStream)
{
    Pdu::unmarshal(dataStream); // unmarshal information in superclass first
    _firingEntityID.unmarshal(dataStream);
    _targetEntityID.unmarshal(dataStream);
}


bool WarfareFamilyPdu::operator ==(const WarfareFamilyPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = Pdu::operator==(rhs);

     if( ! (_firingEntityID == rhs._firingEntityID) ) ivarsEqual = false;
     if( ! (_targetEntityID == rhs._targetEntityID) ) ivarsEqual = false;

    return ivarsEqual;
 }

int WarfareFamilyPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = Pdu::getMarshalledSize();
   marshalSize = marshalSize + _firingEntityID.getMarshalledSize();  // _firingEntityID
   marshalSize = marshalSize + _targetEntityID.getMarshalledSize();  // _targetEntityID
    return marshalSize;
}

