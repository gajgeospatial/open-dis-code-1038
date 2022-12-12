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
using System.ComponentModel;
using System.Reflection;
using System.Text;

namespace OpenDis.Utilities
{
    public static class DisEnumerations
    {
		#region Methods (4) 

        public static bool EnumerationForValueExists<T>(int number)
        {
            if (Enum.IsDefined(typeof(T), number) == true)
            {
                return true;
            }

            return false;
        }

        /// <summary>
        /// Gets the description.
        /// </summary>
        /// <param name="value">The value.</param>
        /// <returns>the description</returns>
        /// <exception cref="InvalidOperationException">if the type parameter is not enum.</exception>
        public static string GetDescription(object value)
        {
            string retVal = string.Empty;

            if (value != null)
            {
                FieldInfo fieldInfo = value.GetType().GetField(value.ToString());

                if (fieldInfo != null)
                {
                    DescriptionAttribute[] attributes = (DescriptionAttribute[])fieldInfo.GetCustomAttributes(typeof(DescriptionAttribute), false);

                    if (attributes != null && 
                        attributes.Length > 0)
                    {
                        retVal = attributes[0].Description;
                    }
                }
            }

            return retVal;
        }

        /// <summary>
        /// Returns an Enumeration Type from a number passed in
        /// </summary>
        /// <typeparam name="T">Enumeration Type</typeparam>
        /// <param name="number">Enumeration value</param>
        /// <returns>Enumeration of that value</returns>
        /// <exception cref="EnumNotFoundException">if the nuber (parameter) 
        /// value is not found in the enum definition</exception>
        public static T GetEnumerationForValue<T>(int number)
        {
            if (Enum.IsDefined(typeof(T), number) == true)
            {
                return (T)Enum.ToObject(typeof(T), number);
            }
            else
            {
                throw new EnumNotFoundException("No enumeration found for value " + number.ToString() + " of enumeration " + typeof(T).Name, typeof(T));
            }
        }

        /// <summary>Returns the Description associated with the enumeration</summary>
        /// <param name="value">The enumeration object which has the attribute.</param>
        /// <returns>The description string of the attribute or string.empty</returns>
        public static string GetInternetDomainCode(object value)
        {
            string retVal = string.Empty;

            if (value != null)
            {
                FieldInfo fieldInfo = value.GetType().GetField(value.ToString());

                if (fieldInfo != null)
                {
                    InternetDomainCodeAttribute[] attributes = (InternetDomainCodeAttribute[])fieldInfo.GetCustomAttributes(typeof(InternetDomainCodeAttribute), false);

                    if (attributes != null &&
                        attributes.Length > 0)
                    {
                        retVal = attributes[0].InternetDomainCode;
                    }
                }
            }

            return retVal;
        }

		#endregion Methods 
    }
}
