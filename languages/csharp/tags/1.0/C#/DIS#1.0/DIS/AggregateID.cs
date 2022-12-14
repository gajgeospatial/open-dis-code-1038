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
     * Section 5.2.36. Each agregate in a given simulation app is given an aggregate identifier number unique for all other aggregates in that app and in that exercise. The id is valid for the duration of the the exercise.
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
    public partial class AggregateID : Object
    {
        /** The site ID */
        protected ushort  _site;

        /** The application ID */
        protected ushort  _application;

        /** the aggregate ID */
        protected ushort  _aggregateID;


        /** Constructor */
        ///<summary>
        ///Section 5.2.36. Each agregate in a given simulation app is given an aggregate identifier number unique for all other aggregates in that app and in that exercise. The id is valid for the duration of the the exercise.
        ///</summary>
        public AggregateID()
        {
        }

        public int getMarshalledSize()
        {
            int marshalSize = 0; 

            marshalSize = marshalSize + 2;  // _site
            marshalSize = marshalSize + 2;  // _application
            marshalSize = marshalSize + 2;  // _aggregateID

            return marshalSize;
        }


        ///<summary>
        ///The site ID
        ///</summary>
        public void setSite(ushort pSite)
        { 
            _site = pSite;
        }

        [XmlElement(Type= typeof(ushort), ElementName="site")]
        public ushort Site
        {
            get
            {
                return _site;
            }
            set
            {
                _site = value;
            }
        }

        ///<summary>
        ///The application ID
        ///</summary>
        public void setApplication(ushort pApplication)
        { 
            _application = pApplication;
        }

        [XmlElement(Type= typeof(ushort), ElementName="application")]
        public ushort Application
        {
            get
            {
                return _application;
            }
            set
            {
                _application = value;
            }
        }

        ///<summary>
        ///the aggregate ID
        ///</summary>
        public void setAggregateID(ushort pAggregateID)
        { 
            _aggregateID = pAggregateID;
        }

        [XmlElement(Type= typeof(ushort), ElementName="aggregateID")]
        public ushort AggregateID_
        {
            get
            {
                return _aggregateID;
            }
            set
            {
                _aggregateID = value;
            }
        }


        ///<summary>
        ///Marshal the data to the DataOutputStream.  Note: Length needs to be set before calling this method
        ///</summary>
        public void marshal(DataOutputStream dos)
        {
            try
            {
                dos.writeUshort((ushort)_site);
                dos.writeUshort((ushort)_application);
                dos.writeUshort((ushort)_aggregateID);
            } // end try
            catch(Exception e)
            {
                Trace.WriteLine(e);
                Trace.Flush();
            }
        } // end of marshal method

        public void unmarshal(DataInputStream dis)
        {
            try
            {
                _site = dis.readUshort();
                _application = dis.readUshort();
                _aggregateID = dis.readUshort();
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
        public void reflection(StringBuilder sb)
        {
            sb.Append("<AggregateID>"  + System.Environment.NewLine);
            try
            {
                sb.Append("<site type=\"ushort\">" + _site.ToString() + "</site> " + System.Environment.NewLine);
                sb.Append("<application type=\"ushort\">" + _application.ToString() + "</application> " + System.Environment.NewLine);
                sb.Append("<aggregateID type=\"ushort\">" + _aggregateID.ToString() + "</aggregateID> " + System.Environment.NewLine);
                sb.Append("</AggregateID>"  + System.Environment.NewLine);
            } // end try
            catch(Exception e)
            {
                Trace.WriteLine(e);
                Trace.Flush();
            }
        } // end of reflection method

        public static bool operator !=(AggregateID a, AggregateID b)
        {
            return !(a == b);
        }

        public static bool operator ==(AggregateID a, AggregateID b)
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
            return this == obj as AggregateID;
        }


        /**
         * Compares for reference equality and value equality.
         */
        public bool equals(AggregateID rhs)
        {
            bool ivarsEqual = true;

            if(rhs.GetType() != this.GetType())
                return false;


            if( ! (_site == rhs._site)) ivarsEqual = false;
            if( ! (_application == rhs._application)) ivarsEqual = false;
            if( ! (_aggregateID == rhs._aggregateID)) ivarsEqual = false;

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

            result = GenerateHash(result) ^ _site.GetHashCode();
            result = GenerateHash(result) ^ _application.GetHashCode();
            result = GenerateHash(result) ^ _aggregateID.GetHashCode();

            return result;
        }
    } // end of class
} // end of namespace
