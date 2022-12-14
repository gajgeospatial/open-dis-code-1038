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
// Author: DMcG
// Modified for use with C#:
//  - Peter Smith (Naval Air Warfare Center - Training Systems Division)
//  - Zvonko Bostjancic (Blubit d.o.o. - zvonko.bostjancic@blubit.si)

using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Diagnostics.CodeAnalysis;
using System.Globalization;
using System.Text;
using System.Xml.Serialization;
using OpenDis.Core;
using OpenDis.Utilities;

namespace OpenDis.Dis1998
{
    /// <summary>
    /// Shaft RPMs, used in underwater acoustic clacluations.
    /// </summary>
    [Serializable]
    [XmlRoot]
    public partial class ShaftRPMs
    {
        /// <summary>
        /// Current shaft RPMs
        /// </summary>
        private short _currentShaftRPMs;

        /// <summary>
        /// ordered shaft rpms
        /// </summary>
        private short _orderedShaftRPMs;

        /// <summary>
        /// rate of change of shaft RPMs
        /// </summary>
        private float _shaftRPMRateOfChange;

        /// <summary>
        /// Initializes a new instance of the <see cref="ShaftRPMs"/> class.
        /// </summary>
        public ShaftRPMs()
        {
        }

        /// <summary>
        /// Implements the operator !=.
        /// </summary>
        /// <param name="left">The left operand.</param>
        /// <param name="right">The right operand.</param>
        /// <returns>
        /// 	<c>true</c> if operands are not equal; otherwise, <c>false</c>.
        /// </returns>
        public static bool operator !=(ShaftRPMs left, ShaftRPMs right)
        {
            return !(left == right);
        }

        /// <summary>
        /// Implements the operator ==.
        /// </summary>
        /// <param name="left">The left operand.</param>
        /// <param name="right">The right operand.</param>
        /// <returns>
        /// 	<c>true</c> if both operands are equal; otherwise, <c>false</c>.
        /// </returns>
        public static bool operator ==(ShaftRPMs left, ShaftRPMs right)
        {
            if (object.ReferenceEquals(left, right))
            {
                return true;
            }

            if (((object)left == null) || ((object)right == null))
            {
                return false;
            }

            return left.Equals(right);
        }

        public virtual int GetMarshalledSize()
        {
            int marshalSize = 0; 

            marshalSize += 2;  // this._currentShaftRPMs
            marshalSize += 2;  // this._orderedShaftRPMs
            marshalSize += 4;  // this._shaftRPMRateOfChange
            return marshalSize;
        }

        /// <summary>
        /// Gets or sets the Current shaft RPMs
        /// </summary>
        [XmlElement(Type = typeof(short), ElementName = "currentShaftRPMs")]
        public short CurrentShaftRPMs
        {
            get
            {
                return this._currentShaftRPMs;
            }

            set
            {
                this._currentShaftRPMs = value;
            }
        }

        /// <summary>
        /// Gets or sets the ordered shaft rpms
        /// </summary>
        [XmlElement(Type = typeof(short), ElementName = "orderedShaftRPMs")]
        public short OrderedShaftRPMs
        {
            get
            {
                return this._orderedShaftRPMs;
            }

            set
            {
                this._orderedShaftRPMs = value;
            }
        }

        /// <summary>
        /// Gets or sets the rate of change of shaft RPMs
        /// </summary>
        [XmlElement(Type = typeof(float), ElementName = "shaftRPMRateOfChange")]
        public float ShaftRPMRateOfChange
        {
            get
            {
                return this._shaftRPMRateOfChange;
            }

            set
            {
                this._shaftRPMRateOfChange = value;
            }
        }

        /// <summary>
        /// Occurs when exception when processing PDU is caught.
        /// </summary>
        public event Action<Exception> Exception;

        /// <summary>
        /// Called when exception occurs (raises the <see cref="Exception"/> event).
        /// </summary>
        /// <param name="e">The exception.</param>
        protected void OnException(Exception e)
        {
            if (this.Exception != null)
            {
                this.Exception(e);
            }
        }

        /// <summary>
        /// Marshal the data to the DataOutputStream.  Note: Length needs to be set before calling this method
        /// </summary>
        /// <param name="dos">The DataOutputStream instance to which the PDU is marshaled.</param>
        [SuppressMessage("Microsoft.Design", "CA1031:DoNotCatchGeneralExceptionTypes", Justification = "Due to ignoring errors.")]
        public virtual void Marshal(DataOutputStream dos)
        {
            if (dos != null)
            {
                try
                {
                    dos.WriteShort((short)this._currentShaftRPMs);
                    dos.WriteShort((short)this._orderedShaftRPMs);
                    dos.WriteFloat((float)this._shaftRPMRateOfChange);
                }
                catch (Exception e)
                {
#if DEBUG
                    Trace.WriteLine(e);
                    Trace.Flush();
#endif
                    this.OnException(e);
                }
            }
        }

        [SuppressMessage("Microsoft.Design", "CA1031:DoNotCatchGeneralExceptionTypes", Justification = "Due to ignoring errors.")]
        public virtual void Unmarshal(DataInputStream dis)
        {
            if (dis != null)
            {
                try
                {
                    this._currentShaftRPMs = dis.ReadShort();
                    this._orderedShaftRPMs = dis.ReadShort();
                    this._shaftRPMRateOfChange = dis.ReadFloat();
                }
                catch (Exception e)
                {
#if DEBUG
                    Trace.WriteLine(e);
                    Trace.Flush();
#endif
                    this.OnException(e);
                }
            }
        }

        /// <summary>
        /// This allows for a quick display of PDU data.  The current format is unacceptable and only used for debugging.
        /// This will be modified in the future to provide a better display.  Usage: 
        /// pdu.GetType().InvokeMember("Reflection", System.Reflection.BindingFlags.InvokeMethod, null, pdu, new object[] { sb });
        /// where pdu is an object representing a single pdu and sb is a StringBuilder.
        /// Note: The supplied Utilities folder contains a method called 'DecodePDU' in the PDUProcessor Class that provides this functionality
        /// </summary>
        /// <param name="sb">The StringBuilder instance to which the PDU is written to.</param>
        [SuppressMessage("Microsoft.Design", "CA1031:DoNotCatchGeneralExceptionTypes", Justification = "Due to ignoring errors.")]
        public virtual void Reflection(StringBuilder sb)
        {
            sb.AppendLine("<ShaftRPMs>");
            try
            {
                sb.AppendLine("<currentShaftRPMs type=\"short\">" + this._currentShaftRPMs.ToString(CultureInfo.InvariantCulture) + "</currentShaftRPMs>");
                sb.AppendLine("<orderedShaftRPMs type=\"short\">" + this._orderedShaftRPMs.ToString(CultureInfo.InvariantCulture) + "</orderedShaftRPMs>");
                sb.AppendLine("<shaftRPMRateOfChange type=\"float\">" + this._shaftRPMRateOfChange.ToString(CultureInfo.InvariantCulture) + "</shaftRPMRateOfChange>");
                sb.AppendLine("</ShaftRPMs>");
            }
            catch (Exception e)
            {
#if DEBUG
                    Trace.WriteLine(e);
                    Trace.Flush();
#endif
                    this.OnException(e);
            }
        }

        /// <summary>
        /// Determines whether the specified <see cref="System.Object"/> is equal to this instance.
        /// </summary>
        /// <param name="obj">The <see cref="System.Object"/> to compare with this instance.</param>
        /// <returns>
        /// 	<c>true</c> if the specified <see cref="System.Object"/> is equal to this instance; otherwise, <c>false</c>.
        /// </returns>
        public override bool Equals(object obj)
        {
            return this == obj as ShaftRPMs;
        }

        /// <summary>
        /// Compares for reference AND value equality.
        /// </summary>
        /// <param name="obj">The object to compare with this instance.</param>
        /// <returns>
        /// 	<c>true</c> if both operands are equal; otherwise, <c>false</c>.
        /// </returns>
        public bool Equals(ShaftRPMs obj)
        {
            bool ivarsEqual = true;

            if (obj.GetType() != this.GetType())
            {
                return false;
            }

            if (this._currentShaftRPMs != obj._currentShaftRPMs)
            {
                ivarsEqual = false;
            }

            if (this._orderedShaftRPMs != obj._orderedShaftRPMs)
            {
                ivarsEqual = false;
            }

            if (this._shaftRPMRateOfChange != obj._shaftRPMRateOfChange)
            {
                ivarsEqual = false;
            }

            return ivarsEqual;
        }

        /// <summary>
        /// HashCode Helper
        /// </summary>
        /// <param name="hash">The hash value.</param>
        /// <returns>The new hash value.</returns>
        private static int GenerateHash(int hash)
        {
            hash = hash << (5 + hash);
            return hash;
        }

        /// <summary>
        /// Gets the hash code.
        /// </summary>
        /// <returns>The hash code.</returns>
        public override int GetHashCode()
        {
            int result = 0;

            result = GenerateHash(result) ^ this._currentShaftRPMs.GetHashCode();
            result = GenerateHash(result) ^ this._orderedShaftRPMs.GetHashCode();
            result = GenerateHash(result) ^ this._shaftRPMRateOfChange.GetHashCode();

            return result;
        }
    }
}
