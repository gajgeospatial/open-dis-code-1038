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

using System;
using System.Collections.Generic;
using System.Text;
using System.Diagnostics;
using System.Xml.Serialization;
using DISnet.DataStreamUtilities;

namespace DIS1998net
{

    /**
     * 5.3.7.4.1: Navigational and IFF PDU. COMPLETE
     *
     * Copyright (c) 2008, MOVES Institute, Naval Postgraduate School. All rights reserved.
     * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
     *
     * @author DMcG
     * Modified for use with C#:
     * Peter Smith (Naval Air Warfare Center - Training Systems Division)
     */
    [Serializable]
    [XmlRoot]
    [XmlInclude(typeof(EntityID))]
    [XmlInclude(typeof(EventID))]
    [XmlInclude(typeof(Vector3Float))]
    [XmlInclude(typeof(SystemID))]
    [XmlInclude(typeof(IffFundamentalData))]
    public partial class IffAtcNavAidsLayer1Pdu : DistributedEmissionsFamilyPdu
    {
        /** ID of the entity that is the source of the emissions */
        protected EntityID  _emittingEntityId = new EntityID(); 

        /** Number generated by the issuing simulation to associate realted events. */
        protected EventID  _eventID = new EventID(); 

        /** Location wrt entity. There is some ambugiuity in the standard here, but this is the order it is listed in the table. */
        protected Vector3Float  _location = new Vector3Float(); 

        /** System ID information */
        protected SystemID  _systemID = new SystemID(); 

        /** padding */
        protected ushort  _pad2;

        /** fundamental parameters */
        protected IffFundamentalData  _fundamentalParameters = new IffFundamentalData(); 


        /** Constructor */
        ///<summary>
        ///5.3.7.4.1: Navigational and IFF PDU. COMPLETE
        ///</summary>
        public IffAtcNavAidsLayer1Pdu()
        {
            PduType = (byte)28;
        }

        new public int getMarshalledSize()
        {
            int marshalSize = 0; 

            marshalSize = base.getMarshalledSize();
            marshalSize = marshalSize + _emittingEntityId.getMarshalledSize();  // _emittingEntityId
            marshalSize = marshalSize + _eventID.getMarshalledSize();  // _eventID
            marshalSize = marshalSize + _location.getMarshalledSize();  // _location
            marshalSize = marshalSize + _systemID.getMarshalledSize();  // _systemID
            marshalSize = marshalSize + 2;  // _pad2
            marshalSize = marshalSize + _fundamentalParameters.getMarshalledSize();  // _fundamentalParameters

            return marshalSize;
        }


        ///<summary>
        ///ID of the entity that is the source of the emissions
        ///</summary>
        public void setEmittingEntityId(EntityID pEmittingEntityId)
        { 
            _emittingEntityId = pEmittingEntityId;
        }

        ///<summary>
        ///ID of the entity that is the source of the emissions
        ///</summary>
        public EntityID getEmittingEntityId()
        {
            return _emittingEntityId;
        }

        ///<summary>
        ///ID of the entity that is the source of the emissions
        ///</summary>
        [XmlElement(Type= typeof(EntityID), ElementName="emittingEntityId")]
        public EntityID EmittingEntityId
        {
            get
            {
                return _emittingEntityId;
            }
            set
            {
                _emittingEntityId = value;
            }
        }

        ///<summary>
        ///Number generated by the issuing simulation to associate realted events.
        ///</summary>
        public void setEventID(EventID pEventID)
        { 
            _eventID = pEventID;
        }

        ///<summary>
        ///Number generated by the issuing simulation to associate realted events.
        ///</summary>
        public EventID getEventID()
        {
            return _eventID;
        }

        ///<summary>
        ///Number generated by the issuing simulation to associate realted events.
        ///</summary>
        [XmlElement(Type= typeof(EventID), ElementName="eventID")]
        public EventID EventID
        {
            get
            {
                return _eventID;
            }
            set
            {
                _eventID = value;
            }
        }

        ///<summary>
        ///Location wrt entity. There is some ambugiuity in the standard here, but this is the order it is listed in the table.
        ///</summary>
        public void setLocation(Vector3Float pLocation)
        { 
            _location = pLocation;
        }

        ///<summary>
        ///Location wrt entity. There is some ambugiuity in the standard here, but this is the order it is listed in the table.
        ///</summary>
        public Vector3Float getLocation()
        {
            return _location;
        }

        ///<summary>
        ///Location wrt entity. There is some ambugiuity in the standard here, but this is the order it is listed in the table.
        ///</summary>
        [XmlElement(Type= typeof(Vector3Float), ElementName="location")]
        public Vector3Float Location
        {
            get
            {
                return _location;
            }
            set
            {
                _location = value;
            }
        }

        ///<summary>
        ///System ID information
        ///</summary>
        public void setSystemID(SystemID pSystemID)
        { 
            _systemID = pSystemID;
        }

        ///<summary>
        ///System ID information
        ///</summary>
        public SystemID getSystemID()
        {
            return _systemID;
        }

        ///<summary>
        ///System ID information
        ///</summary>
        [XmlElement(Type= typeof(SystemID), ElementName="systemID")]
        public SystemID SystemID
        {
            get
            {
                return _systemID;
            }
            set
            {
                _systemID = value;
            }
        }

        ///<summary>
        ///padding
        ///</summary>
        public void setPad2(ushort pPad2)
        { 
            _pad2 = pPad2;
        }

        [XmlElement(Type= typeof(ushort), ElementName="pad2")]
        public ushort Pad2
        {
            get
            {
                return _pad2;
            }
            set
            {
                _pad2 = value;
            }
        }

        ///<summary>
        ///fundamental parameters
        ///</summary>
        public void setFundamentalParameters(IffFundamentalData pFundamentalParameters)
        { 
            _fundamentalParameters = pFundamentalParameters;
        }

        ///<summary>
        ///fundamental parameters
        ///</summary>
        public IffFundamentalData getFundamentalParameters()
        {
            return _fundamentalParameters;
        }

        ///<summary>
        ///fundamental parameters
        ///</summary>
        [XmlElement(Type= typeof(IffFundamentalData), ElementName="fundamentalParameters")]
        public IffFundamentalData FundamentalParameters
        {
            get
            {
                return _fundamentalParameters;
            }
            set
            {
                _fundamentalParameters = value;
            }
        }

        ///<summary>
        ///Automatically sets the length of the marshalled data, then calls the marshal method.
        ///</summary>
        new public void marshalAutoLengthSet(DataOutputStream dos)
        {
            //Set the length prior to marshalling data
            this.setLength((ushort)this.getMarshalledSize());
            this.marshal(dos);
        }

        ///<summary>
        ///Marshal the data to the DataOutputStream.  Note: Length needs to be set before calling this method
        ///</summary>
        new public void marshal(DataOutputStream dos)
        {
            base.marshal(dos);
            try
            {
                _emittingEntityId.marshal(dos);
                _eventID.marshal(dos);
                _location.marshal(dos);
                _systemID.marshal(dos);
                dos.writeUshort((ushort)_pad2);
                _fundamentalParameters.marshal(dos);
            } // end try
            catch(Exception e)
            {
                Trace.WriteLine(e);
                Trace.Flush();
            }
        } // end of marshal method

        new public void unmarshal(DataInputStream dis)
        {
            base.unmarshal(dis);

            try
            {
                _emittingEntityId.unmarshal(dis);
                _eventID.unmarshal(dis);
                _location.unmarshal(dis);
                _systemID.unmarshal(dis);
                _pad2 = dis.readUshort();
                _fundamentalParameters.unmarshal(dis);
            } // end try
            catch(Exception e)
            {
                Trace.WriteLine(e);
                Trace.Flush();
            }
        } // end of unmarshal method

        ///<summary>
        ///This allows for a quick display of PDU data.  The current format is unacceptable and only used for debugging.
        ///This will be modified in the future to provide a better display.  Usage: 
        ///pdu.GetType().InvokeMember("reflection", System.Reflection.BindingFlags.InvokeMethod, null, pdu, new object[] { sb });
        ///where pdu is an object representing a single pdu and sb is a StringBuilder.
        ///Note: The supplied Utilities folder contains a method called 'DecodePDU' in the PDUProcessor Class that provides this functionality
        ///</summary>
        new public void reflection(StringBuilder sb)
        {
            sb.Append("<IffAtcNavAidsLayer1Pdu>"  + System.Environment.NewLine);
            base.reflection(sb);
            try
            {
                sb.Append("<emittingEntityId>"  + System.Environment.NewLine);
                _emittingEntityId.reflection(sb);
                sb.Append("</emittingEntityId>"  + System.Environment.NewLine);
                sb.Append("<eventID>"  + System.Environment.NewLine);
                _eventID.reflection(sb);
                sb.Append("</eventID>"  + System.Environment.NewLine);
                sb.Append("<location>"  + System.Environment.NewLine);
                _location.reflection(sb);
                sb.Append("</location>"  + System.Environment.NewLine);
                sb.Append("<systemID>"  + System.Environment.NewLine);
                _systemID.reflection(sb);
                sb.Append("</systemID>"  + System.Environment.NewLine);
                sb.Append("<pad2 type=\"ushort\">" + _pad2.ToString() + "</pad2> " + System.Environment.NewLine);
                sb.Append("<fundamentalParameters>"  + System.Environment.NewLine);
                _fundamentalParameters.reflection(sb);
                sb.Append("</fundamentalParameters>"  + System.Environment.NewLine);
                sb.Append("</IffAtcNavAidsLayer1Pdu>"  + System.Environment.NewLine);
            } // end try
            catch(Exception e)
            {
                Trace.WriteLine(e);
                Trace.Flush();
            }
        } // end of reflection method

        public static bool operator !=(IffAtcNavAidsLayer1Pdu a, IffAtcNavAidsLayer1Pdu b)
        {
            return !(a == b);
        }

        public static bool operator ==(IffAtcNavAidsLayer1Pdu a, IffAtcNavAidsLayer1Pdu b)
        {
            if (System.Object.ReferenceEquals(a, b))
            {
                return true;
            }

            if (((object)a == null) || ((object)b == null))
            {
                return false;
            }

            return a.equals(b);
        }


        public override bool Equals(object obj)
        {
            return this == obj as IffAtcNavAidsLayer1Pdu;
        }


        /**
         * Compares for reference equality and value equality.
         */
        public bool equals(IffAtcNavAidsLayer1Pdu rhs)
        {
            bool ivarsEqual = true;

            if(rhs.GetType() != this.GetType())
                return false;

            ivarsEqual = base.Equals(rhs);

            if( ! (_emittingEntityId.Equals( rhs._emittingEntityId) )) ivarsEqual = false;
            if( ! (_eventID.Equals( rhs._eventID) )) ivarsEqual = false;
            if( ! (_location.Equals( rhs._location) )) ivarsEqual = false;
            if( ! (_systemID.Equals( rhs._systemID) )) ivarsEqual = false;
            if( ! (_pad2 == rhs._pad2)) ivarsEqual = false;
            if( ! (_fundamentalParameters.Equals( rhs._fundamentalParameters) )) ivarsEqual = false;

            return ivarsEqual;
        }

        /**
         * HashCode Helper
         */
        private int GenerateHash(int hash)
        {
            hash = hash << 5 + hash;
            return(hash);
        }


        /**
         * Return Hash
         */
        public override int GetHashCode()
        {
            int result = 0;

            result = GenerateHash(result) ^ base.GetHashCode();

            result = GenerateHash(result) ^ _emittingEntityId.GetHashCode();
            result = GenerateHash(result) ^ _eventID.GetHashCode();
            result = GenerateHash(result) ^ _location.GetHashCode();
            result = GenerateHash(result) ^ _systemID.GetHashCode();
            result = GenerateHash(result) ^ _pad2.GetHashCode();
            result = GenerateHash(result) ^ _fundamentalParameters.GetHashCode();

            return result;
        }
    } // end of class
} // end of namespace
