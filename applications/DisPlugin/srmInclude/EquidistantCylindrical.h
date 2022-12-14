/** @file EquidistantCylindrical.h
@author David Shen
@brief EquidistantCylindrical SRF.
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

// $Id: EquidistantCylindrical.h,v 1.19 2009/10/12 19:40:16 worleym Exp $

#ifndef _EquidistantCylindrical_h
#define _EquidistantCylindrical_h

#include "BaseSRF.h"
#include "Coord.h"
#include "Exception.h"

namespace srm
{
/** SRF_EquidistantCylindrical class declaration.
    SRFs are allocated by the API, and when no longer needed they should be
    released by calling the release() method.
    @author David Shen
    @see BaseSRF_MapProjection
 */
class EXPORT_SRM_CPP_DLL SRF_EquidistantCylindrical: public BaseSRF_MapProjection
{
public:
   /** Constructor by SRF parameter structure
       @exception This method throws srm::Exception
    */
    static SRF_EquidistantCylindrical* create(      SRM_ORM_Code orm,
                                                    SRM_RT_Code  rt,
                                              const SRM_EC_Parameters &params);

    /** Constructor by individual SRF parameters
        @exception This method throws srm::Exception
    */
    static SRF_EquidistantCylindrical* create(
                                              SRM_ORM_Code   orm,
                                              SRM_RT_Code    rt,
                                              SRM_Long_Float origin_longitude,
                                              SRM_Long_Float central_scale,
                                              SRM_Long_Float false_easting,
                                              SRM_Long_Float false_northing
                                              );

   /** SRF_EquidistantCylindrical constructor by SRF parameter structure
       @exception This method throws srm::Exception
    */
    static SRF_EquidistantCylindrical* create( SRM_SRF_Parameters_Info srf_params )
    {
          return create( srf_params.value.srf_template.orm_code,
                         srf_params.rt_code,
                         srf_params.value.srf_template.parameters.ec_srf_parameters );
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

   /** Returns a reference to the SRF parameter structure
       @exception This method throws srm::Exception
    */
    const SRM_EC_Parameters &getSRFParameters() const;

   /** Returns the origin_longitude SRF parameter value
       @exception This method throws srm::Exception
    */
    SRM_Long_Float get_origin_longitude() const;

   /** Returns the central_scale SRF parameter value
       @exception This method throws srm::Exception
    */
    SRM_Long_Float get_central_scale() const;

   /** Returns the false_easting SRF parameter value
       @exception This method throws srm::Exception
    */
    SRM_Long_Float get_false_easting() const;

   /** Returns the false_northing SRF parameter value
       @exception This method throws srm::Exception
    */
    SRM_Long_Float get_false_northing() const;

   /** Returns true if this SRF is of the given class type
    */
    virtual bool isA( SRF_ClassType type ) const;

   /** Returns the class type of this SRF instance
    */
    virtual SRF_ClassType getClassType() const
    {
        return BaseSRF::SRF_TYP_EC;
    }

    /** Returns true if the SRF parameters are equal
        @note This method is deprecated.  Use the equality operator instead.
    */
    bool isEqual( const SRF_EquidistantCylindrical &srf ) const;

   /** The equality operator
       @note This operator returns true if the SRFs have identical parameter values.
    */
    bool operator==( const SRF_EquidistantCylindrical &rhs ) const;

   /** Returns a copy of this SRF object
       @exception This method throws srm::Exception
    */
    SRF_EquidistantCylindrical* makeCopy() const;

   /** Returns char* of parameter names and their values
       @exception This method throws srm::Exception
    */
    const char* toString();

protected:
    SRF_EquidistantCylindrical( void *impl ) : BaseSRF_MapProjection(impl) {} ///< No stack allocation
    SRF_EquidistantCylindrical &operator =( const SRF_EquidistantCylindrical & ) { return *this; } ///< No copy constructor
    virtual ~SRF_EquidistantCylindrical() {} ///< Use release()
};


inline bool SRF_EquidistantCylindrical::isA( SRF_ClassType type ) const
{
    if (type == BaseSRF::SRF_TYP_EC)
        return true;
    else
        return BaseSRF_MapProjection::isA(type);
};


/// Shorthand version for SRF_EquidistantCylindrical
typedef SRF_EquidistantCylindrical SRF_EC;


/** A Coord3D for SRF_EquidistantCylindrical.
    @author David Shen
    @see SRF_EquidistantCylindrical
 */
class EXPORT_SRM_CPP_DLL Coord3D_EquidistantCylindrical: public Coord3D
{
public:
   /** Constructor
    */
    Coord3D_EquidistantCylindrical( SRF_EquidistantCylindrical *srf,
                                    SRM_Long_Float easting = 0.0,
                                    SRM_Long_Float northing = 0.0,
                                    SRM_Long_Float ellipsoidal_height = 0.0 )
    : Coord3D(srf)
    {
        setComponentValues(easting, northing, ellipsoidal_height);
    }

   /** Copy constructor
    */
    Coord3D_EquidistantCylindrical( const Coord3D_EquidistantCylindrical &coord )
    : Coord3D(coord._srf)
    {
        setComponentValues( coord._values[0], coord._values[1], coord._values[2] );
    }

   /** Copies component values to the coordinate
       @note The coordinates must be associated with the same SRF instance.
       @note This method is deprecated.  Use the assignment operator.
       @exception This method throws srm::Exception
    */
    void copyTo( Coord3D_EquidistantCylindrical &coord ) const
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
    bool isEqual( const Coord3D_EquidistantCylindrical &coord ) const
    {
        return (_srf == coord._srf &&
                _values[0] == coord._values[0] &&
                _values[1] == coord._values[1] &&
                _values[2] == coord._values[2] );
    }

   /** Sets all coordinate component values
    */
    void setComponentValues( SRM_Long_Float easting, SRM_Long_Float northing, SRM_Long_Float ellipsoidal_height )
    {
        _values[0] = easting;
        _values[1] = northing;
        _values[2] = ellipsoidal_height;
    }

   /** Returns the easting value
    */
    SRM_Long_Float get_easting() const
    {
        return _values[0];
    }

   /** Returns the northing value
    */
    SRM_Long_Float get_northing() const
    {
        return _values[1];
    }

   /** Returns the ellipsoidal_height value
    */
    SRM_Long_Float get_ellipsoidal_height() const
    {
        return _values[2];
    }

   /** Sets the easting value
    */
    void set_easting( SRM_Long_Float value )
    {
        _values[0] = value;
    }

   /** Sets the northing value
    */
    void set_northing( SRM_Long_Float value )
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
        return Coord::COORD_TYP_EC;
    }

   /** The equality operator
    */
    bool operator==( const Coord3D_EquidistantCylindrical &rhs ) const;

   /** Returns true if the coordinates are associated with SRFs with identical parameters.
       @note This method should be used to evaluate coordinate compatibility before
             calling the coordinate assignment operator to avoid raising runtime exception
             when operating on incompatible coordinates.
    */
    bool isCompatibleWith( const Coord3D_EquidistantCylindrical &rhs ) const
    {
        return ((*(SRF_EquidistantCylindrical*)(this->_srf)) == (*(SRF_EquidistantCylindrical*)(rhs._srf)));
    }

   /** The assignment operator
       @note This operator will check whether the coordinates are compatible.
       @exception This method throws srm::Exception
    */
    Coord3D_EquidistantCylindrical &operator= ( const Coord3D_EquidistantCylindrical &rhs )
    {
        if((*(SRF_EquidistantCylindrical*)(this->_srf)) == (*(SRF_EquidistantCylindrical*)(rhs._srf)))
        {
            _values[0] = rhs._values[0];
            _values[1] = rhs._values[1];
            _values[2] = rhs._values[2];
        }
        else
            throw Exception(SRM_STATCOD_INVALID_TARGET_COORDINATE,
                            "Coord3D_EquidistantCylindrical op=: incompatible rhs coordinate");

        return *this;
    }
};


inline bool Coord3D_EquidistantCylindrical::isA( Coord_ClassType type ) const
{
    if (type == Coord::COORD_TYP_EC)
        return true;
    else
        return Coord3D::isA(type);
};


/// Shorthand version for Coord3D_EquidistantCylindrical
typedef Coord3D_EquidistantCylindrical Coord3D_EC;


/** A CoordSurf for SRF_EquidistantCylindrical.
    @author David Shen
    @see SRF_EquidistantCylindrical
 */
class EXPORT_SRM_CPP_DLL CoordSurf_EquidistantCylindrical: public CoordSurf
{
public:
   /** Constructor
    */
    CoordSurf_EquidistantCylindrical(SRF_EquidistantCylindrical *srf,
                                     SRM_Long_Float easting = 0.0,
                                     SRM_Long_Float northing = 0.0 )
    : CoordSurf(srf)
    {
        setComponentValues(easting, northing);
    }

   /** Copy constructor
    */
    CoordSurf_EquidistantCylindrical( const CoordSurf_EquidistantCylindrical &coord )
    : CoordSurf(coord._srf)
    {
        setComponentValues( coord._values[0], coord._values[1] );
    }

   /** Copies component values to the coordinate
       @note The coordinates must be associated with the same SRF instance.
       @note This method is deprecated.  Use the assignment operator.
       @exception This method throws srm::Exception
    */
    void copyTo( CoordSurf_EquidistantCylindrical &coord ) const
    {
        if (coord._srf != _srf)
            throw Exception( SRM_STATCOD_INVALID_SOURCE_COORDINATE, "copyTo: Coordinate associated with a difference SRF" );

        coord._values[0] = _values[0];
        coord._values[1] = _values[1];
    }

   /** Returns true if the coordinate component values are identical
       @note This method is deprecated.  Use the equality operator.
    */
    bool isEqual( const CoordSurf_EquidistantCylindrical &coord ) const
    {
        return (_srf == coord._srf &&
                _values[0] == coord._values[0] &&
                _values[1] == coord._values[1] );
    }

   /** Sets all coordinate component values
    */
    void setComponentValues( SRM_Long_Float easting, SRM_Long_Float northing )
    {
        _values[0] = easting;
        _values[1] = northing;
    }

   /** Returns the easting value
    */
    SRM_Long_Float get_easting() const
    {
        return _values[0];
    }

   /** Returns the northing value
    */
    SRM_Long_Float get_northing() const
    {
        return _values[1];
    }

   /** Sets the easting value
    */
    void set_easting( SRM_Long_Float value )
    {
        _values[0] = value;
    }

   /** Sets the northing value
    */
    void set_northing( SRM_Long_Float value )
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
        return Coord::COORD_TYP_SURF_EC;
    }

   /** The equality operator
    */
    bool operator==( const CoordSurf_EquidistantCylindrical &rhs ) const;

   /** Returns true if the coordinates are associated with SRFs with identical parameters.
       @note This method should be used to evaluate coordinate compatibility before
             calling the coordinate assignment operator to avoid raising runtime exception
             when operating on incompatible coordinates.
    */
    bool isCompatibleWith( const CoordSurf_EquidistantCylindrical &rhs ) const
    {
        return ((*(SRF_EquidistantCylindrical*)(this->_srf)) == (*(SRF_EquidistantCylindrical*)(rhs._srf)));
    }

   /** The assignment operator
       @note This operator will check whether the coordinates are compatible.
       @exception This method throws srm::Exception
    */
    CoordSurf_EquidistantCylindrical &operator= ( const CoordSurf_EquidistantCylindrical &rhs )
    {
        if((*(SRF_EquidistantCylindrical*)(this->_srf)) == (*(SRF_EquidistantCylindrical*)(rhs._srf)))
        {
            _values[0] = rhs._values[0];
            _values[1] = rhs._values[1];
        }
        else
            throw Exception(SRM_STATCOD_INVALID_TARGET_COORDINATE,
                            "CoordSurf_EquidistantCylindrical op=: incompatible rhs coordinate");

        return *this;
    }
};


inline bool CoordSurf_EquidistantCylindrical::isA( Coord_ClassType type ) const
{
    if (type == Coord::COORD_TYP_SURF_EC)
        return true;
    else
        return CoordSurf::isA(type);
};


/// Shorthand version for CoordSurf_EquidistantCylindrical
typedef CoordSurf_EquidistantCylindrical CoordSurf_EC;

} // namespace srm

#endif // _EquidistantCylindrical_h
