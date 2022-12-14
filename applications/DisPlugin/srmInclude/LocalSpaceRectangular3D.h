/** @file LocalSpaceRectangular3D.h
@author David Shen
@brief LocalSpaceRectangular3D SRF.
*/
// SRM SDK Release 4.4.0 - January 21, 2010

// - SRM spec. 4.4

/*
 *                             NOTICE
 * 
 * This software is provided openly and freely for use in representing and
 * interchanging environmental data & databases.
 * 
 * This software was developed for use by the United States Government with
 * unlimited rights.  The software was developed under contract
 * DASG60-02-D-0006 TO-193 by Science Applications International Corporation.
 * The software is unclassified and is deemed as Distribution A, approved
 * for Public Release.
 * 
 * Use by others is permitted only upon the ACCEPTANCE OF THE TERMS AND
 * CONDITIONS, AS STIPULATED UNDER THE FOLLOWING PROVISIONS:
 * 
 *    1. Recipient may make unlimited copies of this software and give
 *       copies to other persons or entities as long as the copies contain
 *       this NOTICE, and as long as the same copyright notices that
 *       appear on, or in, this software remain.
 * 
 *    2. Trademarks. All trademarks belong to their respective trademark
 *       holders.  Third-Party applications/software/information are
 *       copyrighted by their respective owners.
 * 
 *    3. Recipient agrees to forfeit all intellectual property and
 *       ownership rights for any version created from the modification
 *       or adaptation of this software, including versions created from
 *       the translation and/or reverse engineering of the software design.
 * 
 *    4. Transfer.  Recipient may not sell, rent, lease, or sublicense
 *       this software.  Recipient may, however enable another person
 *       or entity the rights to use this software, provided that this
 *       AGREEMENT and NOTICE is furnished along with the software and
 *       /or software system utilizing this software.
 * 
 *       All revisions, modifications, created by the Recipient, to this
 *       software and/or related technical data shall be forwarded by the
 *       Recipient to the Government at the following address:
 * 
 *         SMDC
 *         Attention SEDRIS (TO193) TPOC
 *         P.O. Box 1500
 *         Huntsville, AL  35807-3801
 * 
 *         or via electronic mail to:  se-mgmt@sedris.org
 * 
 *    5. No Warranty. This software is being delivered to you AS IS
 *       and there is no warranty, EXPRESS or IMPLIED, as to its use
 *       or performance.
 * 
 *       The RECIPIENT ASSUMES ALL RISKS, KNOWN AND UNKNOWN, OF USING
 *       THIS SOFTWARE.  The DEVELOPER EXPRESSLY DISCLAIMS, and the
 *       RECIPIENT WAIVES, ANY and ALL PERFORMANCE OR RESULTS YOU MAY
 *       OBTAIN BY USING THIS SOFTWARE OR DOCUMENTATION.  THERE IS
 *       NO WARRANTY, EXPRESS OR, IMPLIED, AS TO NON-INFRINGEMENT OF
 *       THIRD PARTY RIGHTS, MERCHANTABILITY, OR FITNESS FOR ANY
 *       PARTICULAR PURPOSE.  IN NO EVENT WILL THE DEVELOPER, THE
 *       UNITED STATES GOVERNMENT OR ANYONE ELSE ASSOCIATED WITH THE
 *       DEVELOPMENT OF THIS SOFTWARE BE HELD LIABLE FOR ANY CONSEQUENTIAL,
 *       INCIDENTAL OR SPECIAL DAMAGES, INCLUDING ANY LOST PROFITS
 *       OR LOST SAVINGS WHATSOEVER.
 */

// SRM_OTHERS_GOES_HERE

// $Id: LocalSpaceRectangular3D.h,v 1.18 2009/10/12 21:23:19 worleym Exp $

#ifndef _LocalSpaceRectangular3D_h
#define _LocalSpaceRectangular3D_h

#include "BaseSRF.h"
#include "Coord.h"
#include "Exception.h"

namespace srm
{
/** SRF_LocalSpaceRectangular3D class declaration.
    SRFs are allocated by the API, and when no longer needed they should be
    released by calling the release() method.
    @author David Shen
    @see BaseSRF_3D
*/
class EXPORT_SRM_CPP_DLL SRF_LocalSpaceRectangular3D: public BaseSRF_3D
{
public:
   /** Constructor by SRF parameter structure
       @exception This method throws srm::Exception
    */
    static SRF_LocalSpaceRectangular3D* create(      SRM_ORM_Code orm,
                                                     SRM_RT_Code  rt,
                                               const SRM_LSR_3D_Parameters &params );

  /** Constructor by individual SRF parameters
      @exception This method throws srm::Exception
   */
    static SRF_LocalSpaceRectangular3D* create(
                                               SRM_ORM_Code orm,
                                               SRM_RT_Code  rt,
                                               SRM_Axis_Direction forward_direction,
                                               SRM_Axis_Direction up_direction
                                               );

   /** SRF_LocalSpaceRectangular3D constructor by SRF parameter structure
       @exception This method throws srm::Exception
    */
    static SRF_LocalSpaceRectangular3D* create( SRM_SRF_Parameters_Info srf_params )
    {
         return create(srf_params.value.srf_template.orm_code,
                       srf_params.rt_code,
                       srf_params.value.srf_template.parameters.lsr_3d_srf_parameters );
    }

   /** Returns a 3D coordinate object
    */
    Coord3D* createCoordinate3D(SRM_Long_Float coord_comp1,
                                SRM_Long_Float coord_comp2,
                                SRM_Long_Float coord_comp3 );

   /** Returns a reference to the SRF parameter structure
       @exception This method throws srm::Exception
    */
    const SRM_LSR_3D_Parameters &getSRFParameters() const;

   /** Returns the forward_direction SRF parameter value
       @exception This method throws srm::Exception
    */
    SRM_Axis_Direction get_forward_direction() const;

   /** Returns the up_direction SRF parameter value
       @exception This method throws srm::Exception
    */
    SRM_Axis_Direction get_up_direction() const;

   /** Returns true if this SRF is of the given class type
    */
    virtual bool isA( SRF_ClassType type ) const;

   /** Returns the class type of this SRF instance
    */
    virtual SRF_ClassType getClassType() const
    {
        return BaseSRF::SRF_TYP_LSR_3D;
    }

   /** Returns true if the SRF parameters are equal
       @note This method is deprecated.  Use the equality operator instead.
    */
    bool isEqual( const SRF_LocalSpaceRectangular3D &srf ) const;

   /** The equality operator
       @note This operator returns true if the SRFs have identical parameter values.
    */
    bool operator==( const SRF_LocalSpaceRectangular3D &rhs ) const;

   /** Returns a copy of this SRF object
       @exception This method throws srm::Exception
    */
    SRF_LocalSpaceRectangular3D* makeCopy() const;

   /** Returns char* of parameter names and their values
       @exception This method throws srm::Exception
    */
    const char* toString();

protected:
    SRF_LocalSpaceRectangular3D( void *impl ) : BaseSRF_3D(impl) {} ///< No stack allocation
    SRF_LocalSpaceRectangular3D &operator =( const SRF_LocalSpaceRectangular3D & ) { return *this; } ///< No copy constructor
    virtual ~SRF_LocalSpaceRectangular3D() {} ///< Use release()
};


inline bool SRF_LocalSpaceRectangular3D::isA( SRF_ClassType type ) const
{
    if (type == BaseSRF::SRF_TYP_LSR_3D)
        return true;
    else
        return BaseSRF_3D::isA(type);
};


/// Shorthand version for SRF_LocalSpaceRectangular3D
typedef SRF_LocalSpaceRectangular3D SRF_LSR_3D;


/** A Coord3D for SRF_LocalSpaceRectangular3D.
    @author David Shen
    @see SRF_LocalSpaceRectangular3D
 */
class EXPORT_SRM_CPP_DLL Coord3D_LocalSpaceRectangular3D: public Coord3D
{
public:
   /** Constructor
    */
    Coord3D_LocalSpaceRectangular3D(SRF_LocalSpaceRectangular3D *srf,
                                    SRM_Long_Float u = 0.0,
                                    SRM_Long_Float v = 0.0,
                                    SRM_Long_Float w = 0.0 )
    : Coord3D(srf)
    {
        setComponentValues(u, v, w);
    }

   /** Copy constructor
    */
    Coord3D_LocalSpaceRectangular3D( const Coord3D_LocalSpaceRectangular3D &coord )
    : Coord3D(coord._srf)
    {
        setComponentValues( coord._values[0], coord._values[1], coord._values[2] );
    }

   /** Copies component values to the coordinate
       @note The coordinates must be associated with the same SRF instance.
       @note This method is deprecated.  Use the assignment operator.
       @exception This method throws srm::Exception
    */
    void copyTo( Coord3D_LocalSpaceRectangular3D &coord ) const
    {
        if (coord._srf != _srf)
            throw Exception( SRM_STATCOD_INVALID_SOURCE_COORDINATE, "copyTo: Coordinate associated with a difference SRF" );

        coord._values[0] = _values[0];
        coord._values[1] = _values[1];
        coord._values[2] = _values[2];
    }

   /** Returns true if the coordinate component values are identical
       @note This method is deprecated.  Use the equality operator.
    */
    bool isEqual( const Coord3D_LocalSpaceRectangular3D &coord ) const
    {
        return (_srf == coord._srf &&
                _values[0] == coord._values[0] &&
                _values[1] == coord._values[1] &&
                _values[2] == coord._values[2] );
    }

   /** Sets all coordinate component values
    */
    void setComponentValues( SRM_Long_Float u, SRM_Long_Float v, SRM_Long_Float w )
    {
        _values[0] = u;
        _values[1] = v;
        _values[2] = w;
    }

   /** Returns the u value
    */
    SRM_Long_Float get_u() const
    {
        return _values[0];
    }

   /** Returns the v value
    */
    SRM_Long_Float get_v() const
    {
        return _values[1];
    }

   /** Returns the w value
    */
    SRM_Long_Float get_w() const
    {
        return _values[2];
    }

   /** Sets the u value
    */
    void set_u( SRM_Long_Float value )
    {
        _values[0] = value;
    }

   /** Sets the v value
    */
    void set_v( SRM_Long_Float value )
    {
        _values[1] = value;
    }

   /** Sets the w value
    */
    void set_w( SRM_Long_Float value )
    {
        _values[2] = value;
    }

   /** Returns true if this coordinate is of the given class type
    */
    virtual bool isA( Coord_ClassType type ) const;


   /** Returns true if this SRF is of the given class type
    */
    virtual Coord_ClassType getClassType() const
    {
        return Coord::COORD_TYP_LSR_3D;
    }

   /** The equality operator
    */
    bool operator==( const Coord3D_LocalSpaceRectangular3D &rhs ) const;

   /** Returns true if the coordinates are associated with SRFs with identical parameters.
       @note This method should be used to evaluate coordinate compatibility
             before calling the coordinate assignment operator to avoid raising
             runtime exception when operating on incompatible coordinates.
    */
    bool isCompatibleWith( const Coord3D_LocalSpaceRectangular3D &rhs ) const
    {
        return ((*(SRF_LocalSpaceRectangular3D*)(this->_srf)) == (*(SRF_LocalSpaceRectangular3D*)(rhs._srf)));
    }

   /** The assignment operator
       @note This operator will check whether the coordinates are compatible.
       @exception This method throws srm::Exception
    */
    Coord3D_LocalSpaceRectangular3D &operator= ( const Coord3D_LocalSpaceRectangular3D &rhs )
    {
        if((*(SRF_LocalSpaceRectangular3D*)(this->_srf)) == (*(SRF_LocalSpaceRectangular3D*)(rhs._srf)))
        {
            _values[0] = rhs._values[0];
            _values[1] = rhs._values[1];
            _values[2] = rhs._values[2];
        }
        else
            throw Exception(SRM_STATCOD_INVALID_TARGET_COORDINATE,
                            "Coord3D_LocalSpaceRectangular3D op=: incompatible rhs coordinate");

        return *this;
    }
};


inline bool Coord3D_LocalSpaceRectangular3D::isA( Coord_ClassType type ) const
{
    if (type == Coord::COORD_TYP_LSR_3D)
        return true;
    else
        return Coord3D::isA(type);
};


/// Shorthand version for Coord3D_LocalSpaceRectangular3D
typedef Coord3D_LocalSpaceRectangular3D Coord3D_LSR_3D;

} // namespace srm

#endif // _LocalSpaceRectangular3D_h
