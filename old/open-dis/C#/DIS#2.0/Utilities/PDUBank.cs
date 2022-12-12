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
using OpenDis.Dis1998;

namespace OpenDis.Utilities
{
    public static class PduBank
    {
		#region Methods (2) 

        public static Pdu GetPdu(uint pdu_type)
        {
            PduType1998 enumType = (PduType1998)pdu_type;
            return GetPdu(enumType);
        }

        public static Pdu GetPdu(PduType1998 pduType)
        {
            Pdu pdu = null;

            switch (pduType)
            {
                case PduType1998.EntityState:
                    pdu = new EntityStatePdu();
                    break;
                case PduType1998.Fire: 
                    pdu = new FirePdu(); 
                    break;
                case PduType1998.Detonation:
                    pdu = new DetonationPdu();
                    break;
                case PduType1998.Collision: 
                    pdu = new CollisionPdu();
                    break;
                case PduType1998.ServiceRequest:
                    pdu = new ServiceRequestPdu();
                    break;
                case PduType1998.ResupplyOffer: 
                    pdu = new ResupplyOfferPdu();
                    break;
                case PduType1998.ResupplyReceived:
                    pdu = new ResupplyReceivedPdu();
                    break;
                case PduType1998.ResupplyCancel: 
                    pdu = new ResupplyCancelPdu(); 
                    break;
                case PduType1998.RepairComplete: 
                    pdu = new RepairCompletePdu(); 
                    break;
                case PduType1998.RepairResponse:
                    pdu = new RepairResponsePdu(); 
                    break;
                case PduType1998.CreateEntity: 
                    pdu = new CreateEntityPdu(); 
                    break;
                case PduType1998.RemoveEntity:
                    pdu = new RemoveEntityPdu();
                    break;
                case PduType1998.StartResume: 
                    pdu = new StartResumePdu(); 
                    break;
                case PduType1998.Acknowledge: 
                    pdu = new AcknowledgePdu(); 
                    break;
                case PduType1998.ActionRequest: 
                    pdu = new ActionRequestPdu();
                    break;
                case PduType1998.ActionResponse: 
                    pdu = new ActionResponsePdu();
                    break;
                case PduType1998.DataQuery:
                    pdu = new DataQueryPdu();
                    break;
                case PduType1998.SetData:
                    pdu = new SetDataPdu(); 
                    break;
                case PduType1998.EventReport:
                    pdu = new EventReportPdu();
                    break;
                case PduType1998.Comment:
                    pdu = new CommentPdu(); 
                    break;
                case PduType1998.StopFreeze: 
                    pdu = new StopFreezePdu(); 
                    break;
                case PduType1998.Signal:
                    pdu = new SignalPdu(); 
                    break;
                case PduType1998.Transmitter: 
                    pdu = new TransmitterPdu(); 
                    break;
            }

            return pdu;
        }

		#endregion Methods 
    }
}
