/** @file Planetodetic.h
@author David Shen
@brief Planetodetic SRF.
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

// $Id: Planetodetic.h,v 1.10 2008/10/22 22:01:42 worleym Exp $

#ifndef _Planetodetic_h
#define _Planetodetic_h

#include "BaseSRF.h"
#include "Coord.h"
#include "Exception.h"

namespace srm
{
/** SRF_Planetodetic class declaration.
    SRFs are allocated by the API, and when no longer needed they should be
    released by calling the release() method.
    @author David Shen
    @see BaseSRF_WithEllipsoidalHeight
*/
class EXPORT_SRM_CPP_DLL SRF_Planetodetic: public BaseSRF_WithEllipsoidalHeight
{
public:
   /** SRF_Planetodetic only constructor by ORM code
       @exception This method throws srm::Exception
    */
    static SRF_Planetodetic* create( SRM_ORM_Code orm,
                                     SRM_RT_Code  rt );

   /** SRF_Planetodetic constructor by ORM parameters
       @exception This method throws srm::Exception
    */
    static SRF_Planetodetic* create( SRM_SRF_Parameters_Info srf_params )
    {
        return create( srf_params.value.srf_template.orm_code, srf_params.rt_code );
    }

   /** Returns a 3D coordinate object
    */
    Coord3D* createCoordinate3D(SRM_Long_Float coord_comp1,
                                SRM_Long_Float coord_comp2,
                                SRM_Long_Float coord_comp3 );

   /** Returns a surface coordinate object
    */
    CoordSurf* createSurfaceCoordinate(SRM_Long_Float coord_surf_comp1,
                                       SRM_Long_Float coord_surf_comp2 );

   /** Returns true if this SRF is of the given class type
    */
    virtual bool isA( SRF_ClassType type ) const;

   /** Returns the class type of this SRF instance
    */
    virtual SRF_ClassType getClassType() const {
        return BaseSRF::SRF_TYP_PD;
    }

   /** Returns true if the SRF parameters are equal
       @note This method is deprecated.  Use the equality operator instead.
    */
    bool isEqual( const SRF_Planetodetic &srf ) const;

   /** The equality operator
       @note This operator returns true if the SRFs have identical parameter values.
    */
    bool operator==( const SRF_Planetodetic &rhs ) const;

   /** Returns a copy of this SRF object
       @exception This method throws srm::Exception
    */
    SRF_Planetodetic* makeCopy() const;

   /** Returns char* of parameter names and their values
       @exception This method throws srm::Exception
    */
    const char* toString();

protected:
    friend class BaseSRF;
    SRF_Planetodetic( void *impl ) : BaseSRF_WithEllipsoidalHeight(impl) {} ///< No stack allocation
    SRF_Planetodetic &operator =( const SRF_Planetodetic & ) { return *this; } ///< No copy constructor
    virtual ~SRF_Planetodetic() {} ///< Use release()
};


inline bool SRF_Planetodetic::isA( SRF_ClassType type ) const
{
    if (type == BaseSRF::SRF_TYP_PD)
        return true;
    else
        return BaseSRF_WithEllipsoidalHeight::isA(type);
};


/// Shorthand version for SRF_Planetodetic
typedef SRF_Planetodetic SRF_PD;


/** A Coord3D for SRF_Planetodetic.
    @author David Shen
    @see SRF_Planetodetic
 */
class EXPORT_SRM_CPP_DLL Coord3D_Planetodetic: public Coord3D
{
public:
   /** Constructor
    */
    Coord3D_Planetodetic(SRF_Planetodetic *srf,
                         SRM_Long_Float latitude = 0.0,
                         SRM_Long_Float longitude = 0.0,
                         SRM_Long_Float ellipsoidal_height = 0.0 )
    : Coord3D(srf)
    {
        setComponentValues(latitude, longitude, ellipsoidal_height);
    }

   /** Copy constructor
    */
    Coord3D_Planetodetic( const Coord3D_Planetodetic &coord )
    : Coord3D(coord._srf)
    {
        setComponentValues( coord._values[0], coord._values[1], coord._values[2] );
    }

   /** Copies component values to the coordinate
       @note The coordinates must be associated with the same SRF instance.
       @note This method is deprecated.  Use the assignment operator.
       @exception This method throws srm::Exception
    */
    void copyTo( Coord3D_Planetodetic &coord ) const
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
    bool isEqual( const Coord3D_Planetodetic &coord ) const
    {
        return (_srf == coord._srf &&
                _values[0] == coord._values[0] &&
                _values[1] == coord._values[1] &&
                _values[2] == coord._values[2] );
    }

   /** Sets all coordinate component values
    */
    void setComponentValues( SRM_Long_Float latitude, SRM_Long_Float longitude, SRM_Long_Float ellipsoidal_height )
    {
        _values[0] = latitude;
        _values[1] = longitude;
        _values[2] = ellipsoidal_height;
    }

   /** Returns the latitude value
    */
    SRM_Long_Float get_latitude() const
    {
        return _values[0];
    }

   /** Returns the longitude value
    */
    SRM_Long_Float get_longitude() const
    {
        return _values[1];
    }

   /** Returns the ellipsoidal_height value
    */
    SRM_Long_Float get_ellipsoidal_height() const
    {
        return _values[2];
    }

   /** Sets the latitude value
    */
    void set_latitude( SRM_Long_Float value )
    {
        _values[0] = value;
    }

   /** Sets the longitude value
    */
    void set_longitude( SRM_Long_Float value )
    {
        _values[1] = value;
    }

   /** Sets the ellipsoidal_height value
    */
    void set_ellipsoidal_height( SRM_Long_Float value )
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
        return Coord::COORD_TYP_PD;
    }

   /** The equality operator
    */
    bool operator==( const Coord3D_Planetodetic &rhs ) const;

   /** Returns true if the coordinates are associated with SRFs with identical parameters.
       @note This method should be used to evaluate coordinate compatibility before
       calling the coordinate assignment operator to avoid raising runtime exception
       when operating on incompatible coordinates.
    */
    bool isCompatibleWith( const Coord3D_Planetodetic &rhs ) const {
        return ((*(SRF_Planetodetic*)(this->_srf)) == (*(SRF_Planetodetic*)(rhs._srf)));
    }

   /** The assignment operator
       @note This operator will check whether the coordinates are compatible.
       @exception This method throws srm::Exception
    */
    Coord3D_Planetodetic &operator= ( const Coord3D_Planetodetic &rhs )
    {
        if((*(SRF_Planetodetic*)(this->_srf)) == (*(SRF_Planetodetic*)(rhs._srf)))
        {
            _values[0] = rhs._values[0];
            _values[1] = rhs._values[1];
            _values[2] = rhs._values[2];
        }
        else
            throw Exception(SRM_STATCOD_INVALID_TARGET_COORDINATE,
                            "Coord3D_Planetodetic op=: incompatible rhs coordinate");

        return *this;
    }
};


inline bool Coord3D_Planetodetic::isA( Coord_ClassType type ) const
{
    if (type == Coord::COORD_TYP_PD)
        return true;
    else
        return Coord3D::isA(type);
};


/// Shorthand version for Coord3D_Planetodetic
typedef Coord3D_Planetodetic Coord3D_PD;


/** A CoordSurf for SRF_Planetodetic.
    @author David Shen
    @see SRF_Planetodetic
 */
class EXPORT_SRM_CPP_DLL CoordSurf_Planetodetic: public CoordSurf
{
public:
   /** Constructor
    */
    CoordSurf_Planetodetic(SRF_Planetodetic *srf,
                           SRM_Long_Float latitude = 0.0,
                           SRM_Long_Float longitude = 0.0 )
    : CoordSurf(srf)
    {
        setComponentValues(latitude, longitude);
    }

   /** Copy constructor
    */
    CoordSurf_Planetodetic( const CoordSurf_Planetodetic &coord )
    : CoordSurf(coord._srf)
    {
        setComponentValues( coord._values[0], coord._values[1] );
    }

   /** Copies component values to the coordinate
       @note The coordinates must be associated with the same SRF instance.
       @note This method is deprecated.  Use the assignment operator.
       @exception This method throws srm::Exception
    */
    void copyTo( CoordSurf_Planetodetic &coord ) const
    {
        if (coord._srf != _srf)
            throw Exception( SRM_STATCOD_INVALID_SOURCE_COORDINATE, "copyTo: Coordinate associated with a difference SRF" );

        coord._values[0] = _values[0];
        coord._values[1] = _values[1];
    }

   /** Returns true if the coordinate component values are identical
       @note This method is deprecated.  Use the equality operator.
    */
    bool isEqual( const CoordSurf_Planetodetic &coord ) const
    {
        return (_srf == coord._srf &&
                _values[0] == coord._values[0] &&
                _values[1] == coord._values[1] );
    }

   /** Sets all coordinate component values
    */
    void setComponentValues( SRM_Long_Float longitude, SRM_Long_Float latitude )
    {
        _values[0] = latitude;
        _values[1] = longitude;
    }

   /** Returns the latitude value
    */
    SRM_Long_Float get_latitude() const
    {
        return _values[0];
    }

   /** Returns the longitude value
    */
    SRM_Long_Float get_longitude() const
    {
        return _values[1];
    }

   /** Sets the latitude value
    */
    void set_latitude( SRM_Long_Float value )
    {
        _values[0] = value;
    }

   /** Sets the longitude value
    */
    void set_longitude( SRM_Long_Float value )
    {
        _values[1] = value;
    }

   /** Returns true if this coordinate is of the given class type
    */
    virtual bool isA( Coord_ClassType type ) const;

   /** Returns true if this SRF is of the given class type
    */
    virtual Coord_ClassType getClassType() const
    {
        return Coord::COORD_TYP_SURF_PD;
    }

   /** The equality operator
    */
    bool operator==( const CoordSurf_Planetodetic &rhs ) const;

   /** Returns true if the coordinates are associated with SRFs with identical parameters.
       @note This method should be used to evaluate coordinate compatibility before
       calling the coordinate assignment operator to avoid raising runtime exception
       when operating on incompatible coordinates.
    */
    bool isCompatibleWith( const CoordSurf_Planetodetic &rhs ) const
    {
        return ((*(SRF_Planetodetic*)(this->_srf)) == (*(SRF_Planetodetic*)(rhs._srf)));
    }

   /** The assignment operator
       @note This operator will check whether the coordinates are compatible.
       @exception This method throws srm::Exception
    */
    CoordSurf_Planetodetic &operator= ( const CoordSurf_Planetodetic &rhs )
    {
        if((*(SRF_Planetodetic*)(this->_srf)) == (*(SRF_Planetodetic*)(rhs._srf)))
        {
            _values[0] = rhs._values[0];
            _values[1] = rhs._values[1];
        }
        else
            throw Exception(SRM_STATCOD_INVALID_TARGET_COORDINATE,
                            "CoordSurf_Planetodetic op=: incompatible rhs coordinate");

        return *this;
    }
};


inline bool CoordSurf_Planetodetic::isA( Coord_ClassType type ) const
{
    if (type == Coord::COORD_TYP_SURF_PD)
        return true;
    else
        return CoordSurf::isA(type);
};


/// Shorthand version for CoordSurf_Planetodetic
typedef CoordSurf_Planetodetic CoordSurf_PD;

} // namespace srm

#endif // _Planetodetic_h
