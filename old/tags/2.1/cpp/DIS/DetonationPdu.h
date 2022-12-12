#ifndef DETONATIONPDU_H
#define DETONATIONPDU_H

#include <DIS/EntityID.h>
#include <DIS/EventID.h>
#include <DIS/Vector3Float.h>
#include <DIS/Vector3Double.h>
#include <DIS/BurstDescriptor.h>
#include <DIS/ArticulationParameter.h>
#include <vector>
#include <DIS/WarfareFamilyPdu.h>
#include <DIS/DataStream.h>
#include <DIS/msLibMacro.h>


namespace DIS
{
// Section 5.3.4.2. Information about stuff exploding. COMPLETE

// Copyright (c) 2007, MOVES Institute, Naval Postgraduate School. All rights reserved. 
//
// This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
//
// @author DMcG, jkg

class EXPORT_MACRO DetonationPdu : public WarfareFamilyPdu
{
protected:
  // ID of muntion that was fired
  EntityID _munitionID; 

  // ID firing event
  EventID _eventID; 

  // ID firing event
  Vector3Float _velocity; 

  // where the detonation is, in world coordinates
  Vector3Double _locationInWorldCoordinates; 

  // Describes munition used
  BurstDescriptor _burstDescriptor; 

  // result of the explosion
  unsigned char _detonationResult; 

  // How many articulation parameters we have
  unsigned char _numberOfArticulationParameters; 

  // padding
  short _pad; 

  std::vector<ArticulationParameter> _articulationParameters; 


 public:
    DetonationPdu();
    virtual ~DetonationPdu();

    virtual void marshal(DataStream& dataStream) const;
    virtual void unmarshal(DataStream& dataStream);

    EntityID& getMunitionID(); 
    const EntityID&  getMunitionID() const; 
    void setMunitionID(const EntityID    &pX);

    EventID& getEventID(); 
    const EventID&  getEventID() const; 
    void setEventID(const EventID    &pX);

    Vector3Float& getVelocity(); 
    const Vector3Float&  getVelocity() const; 
    void setVelocity(const Vector3Float    &pX);

    Vector3Double& getLocationInWorldCoordinates(); 
    const Vector3Double&  getLocationInWorldCoordinates() const; 
    void setLocationInWorldCoordinates(const Vector3Double    &pX);

    BurstDescriptor& getBurstDescriptor(); 
    const BurstDescriptor&  getBurstDescriptor() const; 
    void setBurstDescriptor(const BurstDescriptor    &pX);

    unsigned char getDetonationResult() const; 
    void setDetonationResult(unsigned char pX); 

    unsigned char getNumberOfArticulationParameters() const; 

    short getPad() const; 
    void setPad(short pX); 

    std::vector<ArticulationParameter>& getArticulationParameters(); 
    const std::vector<ArticulationParameter>& getArticulationParameters() const; 
    void setArticulationParameters(const std::vector<ArticulationParameter>&    pX);


virtual int getMarshalledSize() const;

     bool operator  ==(const DetonationPdu& rhs) const;
};
}

#endif
