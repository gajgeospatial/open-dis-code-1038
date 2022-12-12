/** @file Orientation.h
@author Warren Macchi, David Shen
@brief Declaration of Orientation.
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

// $Id: Orientation.h,v 1.47 2009/11/06 21:40:40 worleym Exp $

#ifndef _Orientation_h
#define _Orientation_h

#if !defined(_WIN32)
#define EXPORT_SRM_CPP_DLL
#elif defined(BUILD_SRM_CPP) /* SRM CPP Case */
#if !defined(EXPORT_SRM_CPP_DLL)
#if defined(_LIB)
#define EXPORT_SRM_CPP_DLL
#elif defined(_USRDLL)
#define EXPORT_SRM_CPP_DLL __declspec(dllexport)
#else
#define EXPORT_SRM_CPP_DLL __declspec(dllimport)
#endif
#endif
#else /* SRM C Case */
#define EXPORT_SRM_CPP_DLL
#endif /* _WIN32 && EXPORT_DLL */

#include "Exception.h"
#include <string>

namespace srm
{
/** The Orientation class.
    @author David Shen, Warren Macchi
*/
class EXPORT_SRM_CPP_DLL Orientation
{
public:
   /** Epsilon value for equivalence tests
    */
    static const SRM_Long_Float epsilon;

   /** Assignment operator
    */
    virtual Orientation & operator=(const Orientation &other);

   /** Gets the orientation in matrix 3x3 representation
    */
    virtual SRM_Matrix_3x3 getMatrix3x3() const;

   /** Gets the orientation in (axis, angle) representation
    */
    virtual SRM_Axis_Angle_Params getAxisAngle() const;

   /** Gets the orientation in Euler angles (ZXZ) representation
    */
    virtual SRM_Euler_Angles_ZXZ_Params getEulerAnglesZXZ() const;

   /** Gets the orientation in Tait-Bryan angles representation
    */
    virtual SRM_Tait_Bryan_Angles_Params getTaitBryanAngles() const;

   /** Gets the orientation in Quaternion representation
    */
    virtual SRM_Quaternion_Params getQuaternion() const;

   /** Sets the Matrix 3x3 orientation representation
       @note the INV(mat)=TRANSPOSE(mat), consequently, mat*TRANSPOSE(mat)=I
       @exception This method throws srm::Exception
    */
    virtual void setMatrix3x3(const SRM_Matrix_3x3 & params);

   /** Sets the orientation using elements of the matrix (mat) 3x3 parameter
       @note the INV(mat)=TRANSPOSE(mat), consequently, mat*TRANSPOSE(mat)=I
       @exception This method throws srm::Exception
    */
    virtual void setMatrix3x3
    (SRM_Long_Float a11, SRM_Long_Float a12, SRM_Long_Float a13,
     SRM_Long_Float a21, SRM_Long_Float a22, SRM_Long_Float a23,
     SRM_Long_Float a31, SRM_Long_Float a32, SRM_Long_Float a33);

   /** Sets the (axis, angle) orientation representation
       @note the input axis is a unit vector
       @note the input angle is in radians in the range of [-2PI, 2PI]
       @exception This method throws srm::Exception
    */
    virtual void setAxisAngle(const SRM_Axis_Angle_Params & params);

   /** Sets the orientation using (axis, angle) parameters.
       @param axis in: the axis of rotation, which is a unit vector
       @param angle in: the angle of rotation, which is in radians in
                        the range of [-2PI, 2PI]
       @exception This method throws srm::Exception
    */
    virtual void setAxisAngle(const SRM_Vector_3D & axis, SRM_Long_Float angle);

   /** Sets the Euler angles (ZXZ) orientation representation
       @note the spin angle is in radians in the range of [-2PI, 2PI]
       @note the nutation angle is in radians in the range of [-2PI, 2PI]
       @note the precession angle is in radians in the range of [-2PI, 2PI]
       @exception This method throws srm::Exception
    */
    virtual void setEulerAnglesZXZ(const SRM_Euler_Angles_ZXZ_Params & params);

   /** Sets the orientation using spin, nutation, and precession parameters
       @param spin in: the spin angle, which is in radians in the range of
                       [-2PI, 2PI]
       @param nutation in: the nutation angle is in radians in the range of
                           [-2PI, 2PI]
       @param precession in: the precession angle, which is in radians in the
                             range of [-2PI, 2PI]
       @exception This method throws srm::Exception
    */
    virtual void setEulerAnglesZXZ
    (
        SRM_Long_Float spin,
        SRM_Long_Float nutation,
        SRM_Long_Float precession
    );

   /** Sets the Tait-Bryan angles orientation representation.
       @note the roll angle is in radians in the range of [-2PI, 2PI]
       @note the pitch angle is in radians in the range of [-2PI, 2PI]
       @note the yaw angle is in radians in the range of [-2PI, 2PI]
       @exception This method throws srm::Exception
    */
    virtual void setTaitBryanAngles
    (
        const SRM_Tait_Bryan_Angles_Params & params
    );

   /** Sets the orientation using roll, pitch and yaw parameters
       @param roll in: the roll angle, which is in radians in the range of
                       [-2PI, 2PI]
       @param pitch in: the pitch angle, which is in radians in the range
                        of [-2PI, 2PI]
       @param yaw in: the yaw angle, which is in radians in the range of
                      [-2PI, 2PI]
       @exception This method throws srm::Exception
    */
    virtual void setTaitBryanAngles
    (
        SRM_Long_Float roll,
        SRM_Long_Float pitch,
        SRM_Long_Float yaw
    );

   /** Sets the quaternion orientation representation
       @note q = e0 + e1*i + e2*j + e3*k
       @note e0^2 + e1^2 + e2^2 + e3^2 = 1
       @exception This method throws srm::Exception
    */
    virtual void setQuaternion(const SRM_Quaternion_Params & params );

   /** Sets the orientation using quaternion (e0, e1, e2, e3 ) parameters
       @note q = e0 + e1*i + e2*j + e3*k
       @note e0^2 + e1^2 + e2^2 + e3^2 = 1
       @param e0 in: the scale (real) parameter
       @param e1 in: the vector multiplier in (i) direction
       @param e2 in: the vector multiplier in (j) direction
       @param e3 in: the vector multiplier in (k) direction
       @exception This method throws srm::Exception
    */
    virtual void setQuaternion
    (
        SRM_Long_Float e0,
        SRM_Long_Float e1,
        SRM_Long_Float e2,
        SRM_Long_Float e3
    );

   /** Returns the string for the orientation values.
    */
    virtual std::string toString() const = 0;

   /** Returns the native orientation representation type.
    */
    virtual SRM_Ori_Rep_Type getOriRep() const = 0;

   /** Applies this orientation to the input vector and sets the result in the
       return vector.
       @exception This method throws srm::Exception
    */
    virtual SRM_Vector_3D transformVector(const SRM_Vector_3D &vec ) const;

    static bool equivalence
    (
        const Orientation    &instance1,
        const Orientation    &instance2,
              SRM_Long_Float  tolerance = Orientation::epsilon
    );

    virtual bool equivalence
    (
        const Orientation    &compare_instance,
              SRM_Long_Float  tolerance = Orientation::epsilon
    ) const
    {
        return equivalence(*this, compare_instance, tolerance);
    }

   /** The equality operator.
    *  @note This operator is deprecated. Use equivalence() method.
    */
    bool operator==( Orientation & rhs );

private:
   /** Internal matrix 3x3 representation (for now) used for calculations
    */
    mutable SRM_Matrix_3x3 _matrix_base;
   /** is set to true when the internal data is equivalent to the
       representation class data.
    */
    mutable bool _internal_data_is_current;

protected:
    Orientation();
   /** Internal matrix 3x3 representation (for now) used for calculations
    */
    const SRM_Matrix_3x3 & _get_matrix_base() const;
    const SRM_Matrix_3x3 & _get_matrix_base_unch() const;
    void _set_matrix_base(const SRM_Matrix_3x3 & matrix_base ) const;
   /** is set to true when the internal data is equivalent to the
       representation class data.
    */
    bool _get_internal_data_is_current() const;
    void _set_internal_data_is_current( bool internal_data_is_current) const;
   /** the virtual function to convert the data from native representation to
       the internal representation
    */
    virtual void _updateIntData() const = 0;
   /** the virtual function to convert the data from internal representation
       to the native representation
    */
    virtual void _updateRepData() = 0;
};


/** The OrientationMatrix class.
    @brief The Orientation subclass in matrix 3x3 representation.
    @author David Shen
 */
class EXPORT_SRM_CPP_DLL OrientationMatrix : public Orientation
{
public:
   /** Default constructor.  The default value is the identity matrix.
    */
    OrientationMatrix();

   /** Constructor using elements of the matrix (mat) 3x3 parameter
       @note the INV(mat)=TRANSPOSE(mat), consequently, mat*TRANSPOSE(mat)=I
       @exception This method throws srm::Exception
    */
    OrientationMatrix
    (SRM_Long_Float a11, SRM_Long_Float a12, SRM_Long_Float a13,
     SRM_Long_Float a21, SRM_Long_Float a22, SRM_Long_Float a23,
     SRM_Long_Float a31, SRM_Long_Float a32, SRM_Long_Float a33);

   /** constructor.using matrix 3x3 parameter data structure
       @note the INV(mat)=TRANSPOSE(mat), consequently, mat*TRANSPOSE(mat)=I
       @exception This method throws srm::Exception
    */
    OrientationMatrix(const SRM_Matrix_3x3 &params);

   /** Copy constructor
    */
    OrientationMatrix(const Orientation &other);

   /** Assignment operator
    */
    virtual Orientation & operator=(const Orientation &other);

   /** Returns the string for the matrix 3x3 orientation representation
    */
    virtual std::string toString() const;

   /** Returns the native orientation representation type.
    */
    virtual SRM_Ori_Rep_Type getOriRep() const
    {
        return SRM_ORI_REP_MATRIX_3X3;
    }

   /** Returns true if the matrix parameters represent a valid orientation.
    */
    static bool isValidParams(const SRM_Matrix_3x3 &params);

   /** Composes the right orientation with the left orientation and returns
       the result in the output orientation, i.e., composed_ori = left*right.
       @exception This method throws srm::Exception
    */
    static OrientationMatrix compose
    (
        const Orientation &left,
        const Orientation &right
    );

   /** Matrix element equality, plus or minus tolerance.
    */
    static bool equivParams
    (
        const SRM_Matrix_3x3 &params1,
        const SRM_Matrix_3x3 &params2,
              SRM_Long_Float  tolerance = 0.0
    );

protected:
    SRM_Matrix_3x3 _matrix;

    virtual void _updateIntData() const;
    virtual void _updateRepData();
};


/** The OrientationAxisAngle class.
    @brief The Orientation subclass in (axis, angle) representation.
    @author David Shen
 */
class EXPORT_SRM_CPP_DLL OrientationAxisAngle : public Orientation
{
public:
   /** Default constructor.  The default value is ((1, 0, 0), 0),
    */
    OrientationAxisAngle();

   /** Constructor using (axis, angle) parameter
       @note the input axis is a unit vector
       @note the input angle is in radians in the range of [-2PI, 2PI]
       @param params in: the (axis, angle) parameter
       @exception This method throws srm::Exception
    */
    OrientationAxisAngle(const SRM_Axis_Angle_Params & params);

   /** Constructor using (axis, angle) parameters.
       @param axis in: the axis of rotation, which is a unit vector
       @param angle in: the angle of rotation, which is in radians in
                        the range of [-2PI, 2PI]
       @exception This method throws srm::Exception
    */
    OrientationAxisAngle(const SRM_Vector_3D & axis, SRM_Long_Float angle );

   /** Copy constructor
    */
    OrientationAxisAngle(const Orientation &other);

   /** Assignment operator
    */
    virtual Orientation & operator=(const Orientation &other);

   /** Gets the orientation in (axis, angle) representation
    */
    virtual SRM_Axis_Angle_Params getAxisAngle() const
    {
        return _axis_angle;
    }

   /** Sets the (axis, angle) orientation representation
       @note the input axis is a unit vector
       @note the input angle is in radians in the range of [-2PI, 2PI]
       @exception This method throws srm::Exception
    */
    virtual void setAxisAngle(const SRM_Axis_Angle_Params & params);

   /** Sets the orientation using (axis, angle) parameters.
       @param axis in: the axis of rotation, which is a unit vector
       @param angle in: the angle of rotation, which is in radians in
                        the range of [-2PI, 2PI]
       @exception This method throws srm::Exception
    */
    virtual void setAxisAngle(const SRM_Vector_3D & axis, SRM_Long_Float angle)
    {
        Orientation::setAxisAngle(axis, angle);
    }

   /** Returns the string for the (axis, angle) orientation representation
    */
    virtual std::string toString() const;

   /** Returns the native orientation representation type.
    */
    virtual SRM_Ori_Rep_Type getOriRep() const
    {
        return SRM_ORI_REP_AXIS_ANGLE;
    }

   /** Returns true if the axis angle parameters represent a valid orientation.
    */
    static bool isValidParams(const SRM_Axis_Angle_Params & params );

   /** Composes the right orientation with the left orientation and returns
       the result in the output orientation, i.e., composed_ori = left*right.
       @exception This method throws srm::Exception
    */
    static OrientationAxisAngle compose
    (
        const Orientation &left,
        const Orientation &right
    );

  /** ref: Table 7
    */
    static bool equivParams
    (
        const SRM_Axis_Angle_Params &params1,
        const SRM_Axis_Angle_Params &params2,
              SRM_Long_Float         tolerance = 0.0
    );

protected:
    SRM_Axis_Angle_Params _axis_angle;

    virtual void _updateIntData() const;
    virtual void _updateRepData();
};


/** The OrientationEulerAnglesZXZ class.
    @brief The Orientation subclass in Euler Angles (ZXZ) representation.
    @author David Shen
 */
class EXPORT_SRM_CPP_DLL OrientationEulerAnglesZXZ : public Orientation
{
public:
   /** Default constructor.  The default value is (0, 0, 0).
    */
    OrientationEulerAnglesZXZ();

   /** Constructor using Euler angles (ZXZ) parameter
       @note the spin angle is in radians in the range of [-2PI, 2PI]
       @note the nutation angle is in radians in the range of [-2PI, 2PI]
       @note the precession angle is in radians in the range of [-2PI, 2PI]
       @exception This method throws srm::Exception
    */
    OrientationEulerAnglesZXZ(const SRM_Euler_Angles_ZXZ_Params & params );

   /** Constructor using spin, nutation, and precession parameters
       @param spin in: the spin angle, which is in radians in the range of
                       [-2PI, 2PI]
       @param nutation in: the nutation angle is in radians in the range of
                           [-2PI, 2PI]
       @param precession in: the precession angle, which is in radians in the
                             range of [-2PI, 2PI]
       @exception This method throws srm::Exception
    */
    OrientationEulerAnglesZXZ
    (
        SRM_Long_Float spin,
        SRM_Long_Float nutation,
        SRM_Long_Float precession
    );

   /** Copy constructor
    */
    OrientationEulerAnglesZXZ(const Orientation &other);

   /** Assignment operator
    */
    virtual Orientation & operator=(const Orientation &other);

   /** Gets the orientation in Euler angles (ZXZ) representation
    */
    virtual SRM_Euler_Angles_ZXZ_Params getEulerAnglesZXZ() const
    {
        return _euler_angles;
    }

   /** Sets the Euler angles (ZXZ) orientation representation
       @note the spin angle is in radians in the range of [-2PI, 2PI]
       @note the nutation angle is in radians in the range of [-2PI, 2PI]
       @note the precession angle is in radians in the range of [-2PI, 2PI]
       @exception This method throws srm::Exception
    */
    virtual void setEulerAnglesZXZ
    (
        const SRM_Euler_Angles_ZXZ_Params & params
    );

   /** Sets the orientation using spin, nutation, and precession parameters
       @param spin in: the spin angle, which is in radians in the range of
                       [-2PI, 2PI]
       @param nutation in: the nutation angle is in radians in the range of
                           [-2PI, 2PI]
       @param precession in: the precession angle, which is in radians in the
                             range of [-2PI, 2PI]
       @exception This method throws srm::Exception
    */
    virtual void setEulerAnglesZXZ
    (
        SRM_Long_Float spin,
        SRM_Long_Float nutation,
        SRM_Long_Float precession
    )
    {
        Orientation::setEulerAnglesZXZ(spin, nutation, precession);
    }

   /** Returns the string for the Euler angles (ZXZ) orientation representation
    */
    virtual std::string toString() const;

   /** Returns the native orientation representation type.
    */
    virtual SRM_Ori_Rep_Type getOriRep() const
    {
        return SRM_ORI_REP_EULER_ANGLES_ZXZ;
    }

   /** Returns true if the Euler angles (ZXZ) parameters represent a valid
       orientation.
    */
    static bool isValidParams(const SRM_Euler_Angles_ZXZ_Params &params);

   /** Composes the right orientation with the left orientation and returns
       the result in the output orientation, i.e., composed_ori = left*right.
       @exception This method throws srm::Exception
    */
    static OrientationEulerAnglesZXZ compose
    (
        const Orientation &left,
        const Orientation &right
    );

   /** ref: Table 3
    */
    static bool equivParams
    (
        const SRM_Euler_Angles_ZXZ_Params &params1,
        const SRM_Euler_Angles_ZXZ_Params &params2,
              SRM_Long_Float               tolerance = 0.0
    );

protected:
    SRM_Euler_Angles_ZXZ_Params _euler_angles;

    virtual void _updateIntData() const;
    virtual void _updateRepData();
};


/** The OrientationTaitBryanAngles class.
    @brief The Orientation subclass in Tait-Bryan angles (XYZ) representation.
    @author David Shen
 */
class EXPORT_SRM_CPP_DLL OrientationTaitBryanAngles : public Orientation
{
public:
   /** Default constructor.  The default value is (0, 0, 0).
    */
    OrientationTaitBryanAngles();

   /** Constructor using Tait-Bryan angles parameter
       @note the roll angle is in radians in the range of [-2PI, 2PI]
       @note the pitch angle is in radians in the range of [-2PI, 2PI]
       @note the yaw angle is in radians in the range of [-2PI, 2PI]
       @exception This method throws srm::Exception
    */
    OrientationTaitBryanAngles(const SRM_Tait_Bryan_Angles_Params &params);

   /** Constructor using roll, pitch and yaw parameters
       @param roll in: the roll angle, which is in radians in the range of
                       [-2PI, 2PI]
       @param pitch in: the pitch angle, which is in radians in the range
                        of [-2PI, 2PI]
       @param yaw in: the yaw angle, which is in radians in the range of
                      [-2PI, 2PI]
       @exception This method throws srm::Exception
    */
    OrientationTaitBryanAngles
    (
        SRM_Long_Float roll,
        SRM_Long_Float pitch,
        SRM_Long_Float yaw
    );

   /** Copy constructor
    */
    OrientationTaitBryanAngles(const Orientation &other);

   /** Assignment operator
    */
    virtual Orientation & operator=(const Orientation &other);

   /** Gets the orientation in Tait-Bryan angles representation
    */
    virtual SRM_Tait_Bryan_Angles_Params getTaitBryanAngles() const
    {
        return _tait_bryan_angles;
    }

   /** Sets the Tait-Bryan angles orientation representation.
       @note the roll angle is in radians in the range of [-2PI, 2PI]
       @note the pitch angle is in radians in the range of [-2PI, 2PI]
       @note the yaw angle is in radians in the range of [-2PI, 2PI]
       @exception This method throws srm::Exception
    */
    virtual void setTaitBryanAngles
    (
        const SRM_Tait_Bryan_Angles_Params & params
    );

   /** Sets the orientation using roll, pitch and yaw parameters
       @param roll in: the roll angle, which is in radians in the range of
                       [-2PI, 2PI]
       @param pitch in: the pitch angle, which is in radians in the range
                        of [-2PI, 2PI]
       @param yaw in: the yaw angle, which is in radians in the range of
                      [-2PI, 2PI]
       @exception This method throws srm::Exception
    */
    virtual void setTaitBryanAngles
    (
        SRM_Long_Float roll,
        SRM_Long_Float pitch,
        SRM_Long_Float yaw
    )
    {
        Orientation::setTaitBryanAngles(roll, pitch, yaw);
    }

   /** Returns the string for the Tait-Bryan angles orientation representation.
    */
    virtual std::string toString() const;

   /** Returns the native orientation representation type.
    */
    virtual SRM_Ori_Rep_Type getOriRep() const
    {
        return SRM_ORI_REP_TAIT_BRYAN_ANGLES;
    }

   /** Returns true if the Tait-Bryan angles parameters represent a valid
       orientation.
    */
    static bool isValidParams(const SRM_Tait_Bryan_Angles_Params & params );

   /** Composes the right orientation with the left orientation and returns
       the result in the output orientation, i.e., composed_ori = left*right.
       @exception This method throws srm::Exception
    */
    static OrientationTaitBryanAngles compose
    (
        const Orientation &left,
        const Orientation &right
    );

   /** ref: Table 6
    */
    static bool equivParams
    (
        const SRM_Tait_Bryan_Angles_Params &params1,
        const SRM_Tait_Bryan_Angles_Params &params2,
              SRM_Long_Float                tolerance = 0.0
    );

protected:
    SRM_Tait_Bryan_Angles_Params _tait_bryan_angles;

    virtual void _updateIntData() const;
    virtual void _updateRepData();
};


/** The OrientationQuaternion class.
    @brief The Orientation subclass in quaternion representation.
    @author David Shen
 */
class EXPORT_SRM_CPP_DLL OrientationQuaternion : public Orientation
{
public:
   /** Default constructor.  The default value is (0, 1, 0, 0).
    */
    OrientationQuaternion();

   /** Constructor using quaternion parameter
       @note q = e0 + e1*i + e2*j + e3*k
       @note e0^2 + e1^2 + e2^2 + e3^2 = 1
       @exception This method throws srm::Exception
    */
    OrientationQuaternion(const SRM_Quaternion_Params &params);

   /** Constructor using quaternion (e0, e1, e2, e3 ) parameters
       @note q = e0 + e1*i + e2*j + e3*k
       @note e0^2 + e1^2 + e2^2 + e3^2 = 1
       @param e0 in: the scale (real) parameter
       @param e1 in: the vector multiplier in (i) direction
       @param e2 in: the vector multiplier in (j) direction
       @param e3 in: the vector multiplier in (k) direction
       @exception This method throws srm::Exception
    */
    OrientationQuaternion
    (
        SRM_Long_Float e0,
        SRM_Long_Float e1,
        SRM_Long_Float e2,
        SRM_Long_Float e3
    );

   /** Copy constructor
    */
    OrientationQuaternion(const Orientation &other);

   /** Assignment operator
    */
    virtual Orientation & operator=(const Orientation &other);

   /** Gets the orientation in Quaternion representation
    */
    virtual SRM_Quaternion_Params getQuaternion() const
    {
        return _quaternion;
    }

   /** Sets the quaternion orientation representation
       @note q = e0 + e1*i + e2*j + e3*k
       @note e0^2 + e1^2 + e2^2 + e3^2 = 1
       @exception This method throws srm::Exception
    */
    virtual void setQuaternion(const SRM_Quaternion_Params & params );

   /** Sets the orientation using quaternion (e0, e1, e2, e3 ) parameters
       @note q = e0 + e1*i + e2*j + e3*k
       @note e0^2 + e1^2 + e2^2 + e3^2 = 1
       @param e0 in: the scale (real) parameter
       @param e1 in: the vector multiplier in (i) direction
       @param e2 in: the vector multiplier in (j) direction
       @param e3 in: the vector multiplier in (k) direction
       @exception This method throws srm::Exception
    */
    virtual void setQuaternion
    (
        SRM_Long_Float e0,
        SRM_Long_Float e1,
        SRM_Long_Float e2,
        SRM_Long_Float e3
    )
    {
        Orientation::setQuaternion(e0, e1, e2, e3);
    }

   /** Returns the string for the quaternion orientation representation
    */
    virtual std::string toString() const;

   /** Returns the native orientation representation type.
    */
    virtual SRM_Ori_Rep_Type getOriRep() const
    {
        return SRM_ORI_REP_QUATERNION;
    }

   /** Returns true if the quaternion parameters represent a valid orientation.
    */
    static bool isValidParams(const SRM_Quaternion_Params &params );

   /** Composes the right orientation with the left orientation and returns
       the result in the output orientation, i.e., composed_ori = left*right.
       @exception This method throws srm::Exception
    */
    static OrientationQuaternion compose
    (
        const Orientation &left,
        const Orientation &right
    );

   /** ref: Table 7
    */
    static bool equivParams
    (
        const SRM_Quaternion_Params &params1,
        const SRM_Quaternion_Params &params2,
              SRM_Long_Float         tolerance = 0.0
    );

protected:
    SRM_Quaternion_Params _quaternion;

    virtual void _updateIntData() const;
    virtual void _updateRepData();
};

} // namespace srm

#endif // _Orientation_h
