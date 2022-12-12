// Copyright (c) 1995-2009 held by the author(s).  All rights reserved.
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
// are met:
// * Redistributions of source code must retain the above copyright
//    notice, this list of conditions and the following disclaimer.
// * Redistributions in binary form must reproduce the above copyright
//   notice, this list of conditions and the following disclaimer
//   in the documentation and/or other materials provided with the
//   distribution.
// * Neither the names of the Naval Postgraduate School (NPS)
//   Modeling Virtual Environments and Simulation (MOVES) Institute
//   (http://www.nps.edu and http://www.MovesInstitute.org)
//   nor the names of its contributors may be used to endorse or
//   promote products derived from this software without specific
//   prior written permission.
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
//
// Copyright (c) 2008, MOVES Institute, Naval Postgraduate School. All 
// rights reserved. This work is licensed under the BSD open source license,
// available at https://www.movesinstitute.org/licenses/bsd.html
//
// Author Peter Smith (Naval Air Warfare Center - Training Systems Division) 01/23/2009
// Modified by Zvonko Bostjancic (Blubit d.o.o. - zvonko.bostjancic@blubit.si)

using System;
using System.Collections.Generic;
using System.Text;

namespace OpenDis.Utilities
{
    /// <summary>
    /// PDU types
    /// </summary>
    public enum PduType1998
    {
        /// <summary>
        /// Other
        /// </summary>
        Other = 0,

        /// <summary>
        /// Entity state
        /// </summary>
        EntityState = 1,

        /// <summary>
        /// Fire 
        /// </summary>
        Fire = 2,

        /// <summary>
        /// Detonation
        /// </summary>
        Detonation = 3,

        /// <summary>
        /// Collision
        /// </summary>
        Collision = 4,

        /// <summary>
        /// Service request
        /// </summary>
        ServiceRequest = 5,

        /// <summary>
        /// Resupply offer
        /// </summary>
        ResupplyOffer = 6,

        /// <summary>
        /// Resupply received
        /// </summary>
        ResupplyReceived = 7,

        /// <summary>
        /// Resupply cancel
        /// </summary>
        ResupplyCancel = 8,

        /// <summary>
        /// Repair complete
        /// </summary>
        RepairComplete = 9,

        /// <summary>
        /// Repair response
        /// </summary>
        RepairResponse = 10,

        /// <summary>
        /// Create entity
        /// </summary>
        CreateEntity = 11,

        /// <summary>
        /// Remove entity
        /// </summary>
        RemoveEntity = 12,

        /// <summary>
        /// Start / Resume
        /// </summary>
        StartResume = 13,

        /// <summary>
        /// Stop / Freeze
        /// </summary>
        StopFreeze = 14,

        /// <summary>
        /// Acknowledge
        /// </summary>
        Acknowledge = 15,

        /// <summary>
        /// Action request
        /// </summary>
        ActionRequest = 16,

        /// <summary>
        /// Action response
        /// </summary>
        ActionResponse = 17,

        /// <summary>
        /// Data query
        /// </summary>
        DataQuery = 18,

        /// <summary>
        /// Set data
        /// </summary>
        SetData = 19,

        /// <summary>
        /// Data
        /// </summary>
        Data = 20,

        /// <summary>
        /// Event report
        /// </summary>
        EventReport = 21,

        /// <summary>
        /// Comment
        /// </summary>
        Comment = 22,

        /// <summary>
        /// Electromagnetic emission
        /// </summary>
        ElectromagneticEmission = 23,

        /// <summary>
        /// Designator
        /// </summary>
        Designator = 24,

        /// <summary>
        /// Transmitter
        /// </summary>
        Transmitter = 25,

        /// <summary>
        /// Signal
        /// </summary>
        Signal = 26
    }
}
