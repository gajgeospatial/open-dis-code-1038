#include <DIS/IntercomControlPdu.h> 

using namespace DIS;


IntercomControlPdu::IntercomControlPdu() : RadioCommunicationsFamilyPdu(),
   _controlType(0), 
   _communicationsChannelType(0), 
   _sourceEntityID(), 
   _sourceCommunicationsDeviceID(0), 
   _sourceLineID(0), 
   _transmitPriority(0), 
   _transmitLineState(0), 
   _command(0), 
   _masterEntityID(), 
   _masterCommunicationsDeviceID(0), 
   _intercomParametersLength(0)
{
    setPduType( 32 );
}

IntercomControlPdu::~IntercomControlPdu()
{
    _intercomParameters.clear();
}

unsigned char IntercomControlPdu::getControlType() const
{
    return _controlType;
}

void IntercomControlPdu::setControlType(unsigned char pX)
{
    _controlType = pX;
}

unsigned char IntercomControlPdu::getCommunicationsChannelType() const
{
    return _communicationsChannelType;
}

void IntercomControlPdu::setCommunicationsChannelType(unsigned char pX)
{
    _communicationsChannelType = pX;
}

EntityID& IntercomControlPdu::getSourceEntityID() 
{
    return _sourceEntityID;
}

const EntityID& IntercomControlPdu::getSourceEntityID() const
{
    return _sourceEntityID;
}

void IntercomControlPdu::setSourceEntityID(const EntityID &pX)
{
    _sourceEntityID = pX;
}

unsigned char IntercomControlPdu::getSourceCommunicationsDeviceID() const
{
    return _sourceCommunicationsDeviceID;
}

void IntercomControlPdu::setSourceCommunicationsDeviceID(unsigned char pX)
{
    _sourceCommunicationsDeviceID = pX;
}

unsigned char IntercomControlPdu::getSourceLineID() const
{
    return _sourceLineID;
}

void IntercomControlPdu::setSourceLineID(unsigned char pX)
{
    _sourceLineID = pX;
}

unsigned char IntercomControlPdu::getTransmitPriority() const
{
    return _transmitPriority;
}

void IntercomControlPdu::setTransmitPriority(unsigned char pX)
{
    _transmitPriority = pX;
}

unsigned char IntercomControlPdu::getTransmitLineState() const
{
    return _transmitLineState;
}

void IntercomControlPdu::setTransmitLineState(unsigned char pX)
{
    _transmitLineState = pX;
}

unsigned char IntercomControlPdu::getCommand() const
{
    return _command;
}

void IntercomControlPdu::setCommand(unsigned char pX)
{
    _command = pX;
}

EntityID& IntercomControlPdu::getMasterEntityID() 
{
    return _masterEntityID;
}

const EntityID& IntercomControlPdu::getMasterEntityID() const
{
    return _masterEntityID;
}

void IntercomControlPdu::setMasterEntityID(const EntityID &pX)
{
    _masterEntityID = pX;
}

unsigned short IntercomControlPdu::getMasterCommunicationsDeviceID() const
{
    return _masterCommunicationsDeviceID;
}

void IntercomControlPdu::setMasterCommunicationsDeviceID(unsigned short pX)
{
    _masterCommunicationsDeviceID = pX;
}

unsigned int IntercomControlPdu::getIntercomParametersLength() const
{
   return _intercomParameters.size();
}

std::vector<IntercomCommunicationsParameters>& IntercomControlPdu::getIntercomParameters() 
{
    return _intercomParameters;
}

const std::vector<IntercomCommunicationsParameters>& IntercomControlPdu::getIntercomParameters() const
{
    return _intercomParameters;
}

void IntercomControlPdu::setIntercomParameters(const std::vector<IntercomCommunicationsParameters>& pX)
{
     _intercomParameters = pX;
}

void IntercomControlPdu::marshal(DataStream& dataStream) const
{
    RadioCommunicationsFamilyPdu::marshal(dataStream); // Marshal information in superclass first
    dataStream << _controlType;
    dataStream << _communicationsChannelType;
    _sourceEntityID.marshal(dataStream);
    dataStream << _sourceCommunicationsDeviceID;
    dataStream << _sourceLineID;
    dataStream << _transmitPriority;
    dataStream << _transmitLineState;
    dataStream << _command;
    _masterEntityID.marshal(dataStream);
    dataStream << _masterCommunicationsDeviceID;
    dataStream << ( unsigned int )_intercomParameters.size();

     for(size_t idx = 0; idx < _intercomParameters.size(); idx++)
     {
        IntercomCommunicationsParameters x = _intercomParameters[idx];
        x.marshal(dataStream);
     }

}

void IntercomControlPdu::unmarshal(DataStream& dataStream)
{
    RadioCommunicationsFamilyPdu::unmarshal(dataStream); // unmarshal information in superclass first
    dataStream >> _controlType;
    dataStream >> _communicationsChannelType;
    _sourceEntityID.unmarshal(dataStream);
    dataStream >> _sourceCommunicationsDeviceID;
    dataStream >> _sourceLineID;
    dataStream >> _transmitPriority;
    dataStream >> _transmitLineState;
    dataStream >> _command;
    _masterEntityID.unmarshal(dataStream);
    dataStream >> _masterCommunicationsDeviceID;
    dataStream >> _intercomParametersLength;

     _intercomParameters.clear();
     for(size_t idx = 0; idx < _intercomParametersLength; idx++)
     {
        IntercomCommunicationsParameters x;
        x.unmarshal(dataStream);
        _intercomParameters.push_back(x);
     }
}


bool IntercomControlPdu::operator ==(const IntercomControlPdu& rhs) const
 {
     bool ivarsEqual = true;

     ivarsEqual = RadioCommunicationsFamilyPdu::operator==(rhs);

     if( ! (_controlType == rhs._controlType) ) ivarsEqual = false;
     if( ! (_communicationsChannelType == rhs._communicationsChannelType) ) ivarsEqual = false;
     if( ! (_sourceEntityID == rhs._sourceEntityID) ) ivarsEqual = false;
     if( ! (_sourceCommunicationsDeviceID == rhs._sourceCommunicationsDeviceID) ) ivarsEqual = false;
     if( ! (_sourceLineID == rhs._sourceLineID) ) ivarsEqual = false;
     if( ! (_transmitPriority == rhs._transmitPriority) ) ivarsEqual = false;
     if( ! (_transmitLineState == rhs._transmitLineState) ) ivarsEqual = false;
     if( ! (_command == rhs._command) ) ivarsEqual = false;
     if( ! (_masterEntityID == rhs._masterEntityID) ) ivarsEqual = false;
     if( ! (_masterCommunicationsDeviceID == rhs._masterCommunicationsDeviceID) ) ivarsEqual = false;

     for(size_t idx = 0; idx < _intercomParameters.size(); idx++)
     {
        if( ! ( _intercomParameters[idx] == rhs._intercomParameters[idx]) ) ivarsEqual = false;
     }


    return ivarsEqual;
 }

int IntercomControlPdu::getMarshalledSize() const
{
   int marshalSize = 0;

   marshalSize = RadioCommunicationsFamilyPdu::getMarshalledSize();
   marshalSize = marshalSize + 1;  // _controlType
   marshalSize = marshalSize + 1;  // _communicationsChannelType
   marshalSize = marshalSize + _sourceEntityID.getMarshalledSize();  // _sourceEntityID
   marshalSize = marshalSize + 1;  // _sourceCommunicationsDeviceID
   marshalSize = marshalSize + 1;  // _sourceLineID
   marshalSize = marshalSize + 1;  // _transmitPriority
   marshalSize = marshalSize + 1;  // _transmitLineState
   marshalSize = marshalSize + 1;  // _command
   marshalSize = marshalSize + _masterEntityID.getMarshalledSize();  // _masterEntityID
   marshalSize = marshalSize + 2;  // _masterCommunicationsDeviceID
   marshalSize = marshalSize + 4;  // _intercomParametersLength

   for(int idx=0; idx < _intercomParameters.size(); idx++)
   {
        IntercomCommunicationsParameters listElement = _intercomParameters[idx];
        marshalSize = marshalSize + listElement.getMarshalledSize();
    }

    return marshalSize;
}

