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

/*
* Copyright (c) 2008, MOVES Institute, Naval Postgraduate School. All rights reserved.
* This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
*
* @author Peter Smith (Naval Air Warfare Center - Training Systems Division)
*/

using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace EnumerationExample
{
    //
    // NOTE TO END USERS.  THIS FILE WAS PUT TOGETHER VERY QUICKLY TO JUST TEST THAT THE FILES CAN COMPILE.  THE FORM THAT IS
    // DISPLAYED WILL NOT CONTAIN ANY DATA.  THE INTENT WAS TO PUT BREAK POINTS IN THE CODE TO SEE THE RESULTS.
    //
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();

            
                string createEntity = DISnet.DISEnumerations.AcknowledgeFlag.CREATE_ENTITY.ToString();

                string createEntityGetDescription = DISnet.DISEnumerations.GetDescription(DISnet.DISEnumerations.AcknowledgeFlag.CREATE_ENTITY);

                DISnet.DISEnumerations.AcousticSystemName acousticSystemNameExists = DISnet.DISEnumerations.GetEnumerationForValue<DISnet.DISEnumerations.AcousticSystemName>(8);
                
                int acousticSystemNameExistsToInteger = (int)acousticSystemNameExists;

                try
                {
                    DISnet.DISEnumerations.AcousticSystemName acousticSystemNameDoesNotExist = DISnet.DISEnumerations.GetEnumerationForValue<DISnet.DISEnumerations.AcousticSystemName>(20);
                }
                catch (Exception ex2)
                {
                    string exceptionThrownDidNotExist = ex2.ToString();
                }
                
                string countryType = DISnet.DISEnumerations.GetDescription(DISnet.DISEnumerations.CountryType.AFGHANISTAN);
                string getDomainCode = DISnet.DISEnumerations.GetInternetDomainCode(DISnet.DISEnumerations.CountryType.AFGHANISTAN);
                DISnet.DISEnumerations.CountryType countryTypeCodeExists = DISnet.DISEnumerations.GetEnumerationForValue<DISnet.DISEnumerations.CountryType>(8);

                int countryTypeCodeExistsToInteger = (int)countryTypeCodeExists;

                try
                {
                    DISnet.DISEnumerations.CountryType countryTypeCodeExistsDoesNotExist = DISnet.DISEnumerations.GetEnumerationForValue<DISnet.DISEnumerations.CountryType>(720);
                }
                catch (Exception ex)
                {
                    string exceptionCountryTypeCodeExistsDoesNotExist = ex.ToString();
                }

  
            int acousticSystemsToInteger = (int)DISnet.DISEnumerations.AcousticSystemName.OTHER;
            bool doesNotExists = DISnet.DISEnumerations.EnumerationForValueExists<DISnet.DISEnumerations.AcousticSystemName>(20);
            bool doesExists = DISnet.DISEnumerations.EnumerationForValueExists<DISnet.DISEnumerations.AcousticSystemName>(8);

        }
    }
}
