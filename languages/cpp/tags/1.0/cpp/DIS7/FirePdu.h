#ifndef FIREPDU_H
#define FIREPDU_H

#include <DIS/EntityID.h>
#include <DIS/EventIdentifier.h>
#include <DIS/Vector3Double.h>
#include <DIS/MunitionDescriptor.h>
#include <DIS/Vector3Float.h>
#include <DIS/WarfareFamilyPdu.h>
#include <DIS/DataStream.h>
#include <DIS/msLibMacro.h>


namespace DIS
{
//  The firing of a weapon or expendable shall be communicated by issuing a Fire PDU. Sectioin 7.3.2. COMPLETE

// Copyright (c) 2007-2009, MOVES Institute, Naval Postgraduate School. All rights reserved. 
//
// @author DMcG, jkg

class EXPORT_MACRO FirePdu : public WarfareFamilyPdu
{
protected:
  /** This field shall specify the entity identification of the fired munition or expendable. This field shall be represented by an Entity Identifier record (see 6.2.28). */
  EntityID _munitionExpendibleID; 

  /** This field shall contain an identification generated by the firing entity to associate related firing and detonation events. This field shall be represented by an Event Identifier record (see 6.2.34). */
  EventIdentifier _eventID; 

  /** This field shall identify the fire mission (see 5.4.3.3). This field shall be representedby a 32-bit unsigned integer. */
  unsigned int _fireMissionIndex; 

  /** This field shall specify the location, in world coordinates, from which the munition was launched, and shall be represented by a World Coordinates record (see 6.2.97). */
  Vector3Double _locationInWorldCoordinates; 

  /** This field shall describe the firing or launch of a munition or expendable represented by one of the following types of Descriptor records: Munition Descriptor (6.2.20.2) or Expendable Descriptor (6.2.20.4). */
  MunitionDescriptor _descriptor; 

  /** This field shall specify the velocity of the fired munition at the point when the issuing simulation application intends the externally visible effects of the launch (e.g. exhaust plume or muzzle blast) to first become apparent. The velocity shall be represented in world coordinates. This field shall be represented by a Linear Velocity Vector record [see 6.2.95 item c)]. */
  Vector3Float _velocity; 

  /** This field shall specify the range that an entity�s fire control system has assumed in computing the fire control solution. This field shall be represented by a 32-bit floating point number in meters. For systems where range is unknown or unavailable, this field shall contain a value of zero. */
  float _range; 


 public:
    FirePdu();
    virtual ~FirePdu();

    virtual void marshal(DataStream& dataStream) const;
    virtual void unmarshal(DataStream& dataStream);

    EntityID& getMunitionExpendibleID(); 
    const EntityID&  getMunitionExpendibleID() const; 
    void setMunitionExpendibleID(const EntityID    &pX);

    EventIdentifier& getEventID(); 
    const EventIdentifier&  getEventID() const; 
    void setEventID(const EventIdentifier    &pX);

    unsigned int getFireMissionIndex() const; 
    void setFireMissionIndex(unsigned int pX); 

    Vector3Double& getLocationInWorldCoordinates(); 
    const Vector3Double&  getLocationInWorldCoordinates() const; 
    void setLocationInWorldCoordinates(const Vector3Double    &pX);

    MunitionDescriptor& getDescriptor(); 
    const MunitionDescriptor&  getDescriptor() const; 
    void setDescriptor(const MunitionDescriptor    &pX);

    Vector3Float& getVelocity(); 
    const Vector3Float&  getVelocity() const; 
    void setVelocity(const Vector3Float    &pX);

    float getRange() const; 
    void setRange(float pX); 


virtual int getMarshalledSize() const;

     bool operator  ==(const FirePdu& rhs) const;
};
}

#endif
// Copyright (c) 1995-2009 held by the author(s).  All rights reserved.
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
//  are met:
// 
//  * Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
// * Redistributions in binary form must reproduce the above copyright
// notice, this list of conditions and the following disclaimer
// in the documentation and/or other materials provided with the
// distribution.
// * Neither the names of the Naval Postgraduate School (NPS)
//  Modeling Virtual Environments and Simulation (MOVES) Institute
// (http://www.nps.edu and http://www.MovesInstitute.org)
// nor the names of its contributors may be used to endorse or
//  promote products derived from this software without specific
// prior written permission.
// 
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// AS IS AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
// FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
// COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
// INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
// BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
// LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
// CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
// LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
// ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
// POSSIBILITY OF SUCH DAMAGE.