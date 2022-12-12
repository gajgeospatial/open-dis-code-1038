/**
 * 5.2.44: Grid data record, representation 1
 *
 * Copyright (c) 2008-2013, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
if (typeof dis === "undefined")
 dis = {};

 dis.GridAxisRecordRepresentation2 = function()
{
   /** type of environmental sample */
  this.sampleType = 0;

   /** value that describes data representation */
  this.dataRepresentation = 0;

   /** number of values */
  this.numberOfValues = 0;

   /** variable length list of data parameters ^^^this is wrong--need padding as well */
    dataValues = new Array();
 
} // end of class
