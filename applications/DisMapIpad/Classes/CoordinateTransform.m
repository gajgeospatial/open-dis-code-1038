//
//  CoordinateTransform.m
//  DisMapIpad
//
//  Created by Donald McGregor on 6/23/10.
//  Copyright 2010 NPS. All rights reserved.
//

#import <math.h>

#import "CoordinateTransform.h"

static double _toDegrees = 57.2957795131;
static double _toRadians = 0.01745329252;

@implementation CoordinateTransform

-(id)init
{
    self = [super init];
    return self;
}


/**
 * Converts xyz world coordinates to latitude and longitude (IN RADIANS). This algorithm may not be 100% accurate
 * near the poles. Uses WGS84 , though you can change the ellipsoid constants a and b if you want to use something
 * else. These formulas were obtained from Military Handbook 600008
 * @param disLocation Vector3Double with the x, y, and z coordinates in the DIS reference frame (earth-centered)
 * @return An Core Location CLLocation with the lat, long, and elevation corresponding to those coordinates, in degrees and meters above geodetic
 * Elevation is in meters, lat and long are in degrees
 */
- (CLLocation *)locationFromDis:(Vector3Double*) disLocation
{
    // CLLocation is used as a return value because that's most useful in MapKit
    CLLocation* location;
    
    double x = [disLocation x]; 
    double y = [disLocation y]; 
    double z = [disLocation z]; 
    double answer[3] = {0.0, 0.0, 0.0};
    double a = 6378137.0;    //semi major axis
    double b = 6356752.3142; //semi minor axis
    
    double eSquared;     //first eccentricity squared
    double rSubN;        //radius of the curvature of the prime vertical
    double ePrimeSquared;//second eccentricity squared
    double W = sqrt((x*x + y*y));
    
    eSquared = (a*a - b*b) / (a*a);
    ePrimeSquared = (a*a - b*b) / (b*b);
    
    /**
     * Get the longitude.
     */
    if(x >= 0 )
    {
        answer[1] = atan(y/x);
    }
    else if(x < 0 && y >= 0)
    {
        answer[1] = atan(y/x) + M_PI;
    }
    else
    {
        answer[1] = atan(y/x) - M_PI;
    }
    
    /**
     * Longitude calculation done. Now calculate latitude.
     * NOTE: The handbook mentions using the calculated phi (latitude) value to recalculate B
     * using tan B = (1-f) tan phi and then performing the entire calculation again to get more accurate values.
     * However, for terrestrial applications, one iteration is accurate to .1 millimeter on the surface  of the
     * earth (Rapp, 1984, p.124), so one iteration is enough for our purposes
     */
    
    double tanBZero = (a*z) / (b * W);
    double BZero = atan((tanBZero));
    double tanPhi = (z + (ePrimeSquared * b * (pow(sin(BZero), 3))) ) /(W - (a * eSquared * (pow(cos(BZero), 3))));
    double phi = atan(tanPhi);
    answer[0] = phi;
    /**
     * Latitude done, now get the elevation. Note: The handbook states that near the poles, it is preferable to use
     * h = (Z / sin phi ) - rSubN + (eSquared * rSubN). Our applications are never near the poles, so this formula
     * was left unimplemented.
     */
    rSubN = (a*a) / sqrt(((a*a) * (cos(phi)*cos(phi)) + ((b*b) * (sin(phi)*sin(phi)))));
    
    answer[2] = (W / cos(phi)) - rSubN;
    
    // Convert to degrees, set values in location
    double radiansToDegrees = 180.0/M_PI;
    CLLocationCoordinate2D place;
    place.latitude = answer[0] * radiansToDegrees;
    place.longitude =  answer[1] * radiansToDegrees;
    
    location = [[CLLocation alloc] initWithCoordinate:place 
                                   altitude:answer[2] 
                                   horizontalAccuracy:kCLLocationAccuracyNearestTenMeters 
                                   verticalAccuracy:kCLLocationAccuracyNearestTenMeters 
                                   timestamp:[NSDate date]];
    
    return location;
}

- (Vector3Double *)disFromLocation:(CLLocation*)location
{
    Vector3Double* disLocation = [[Vector3Double alloc] init]; // Return value
    
    // Convert lat lon (in degrees) to radians
    CLLocationCoordinate2D loc = [location coordinate];
    double latitude = loc.latitude;
    double longitude = loc.longitude;
    double height = [location altitude];
    
    double a = 6378137.0; //semi major axis
    double b = 6356752.3142; //semi minor axis
    double cosLat = cos(latitude);
    double sinLat = sin(latitude);
    
    
    double rSubN = (a*a) / sqrt(((a*a) * (cosLat*cosLat) + ((b*b) * (sinLat*sinLat))));
    
    double X = (rSubN + height) * cosLat * cos(longitude);
    double Y = (rSubN + height) * cosLat * sin(longitude);
    double Z = ((((b*b) / (a*a)) * rSubN) + height) * sinLat;
    
    [disLocation setX:X];
    [disLocation setY:Y];
    [disLocation setZ:Z];
    
    return disLocation;
}

/**
 * Gets a degree heading for an entity based on euler angles. All angular values passed in must be in radians.
 * @param lat Entity's latitude,    IN RADIANS
 * @param lon Entity's longitude,   IN RADIANS
 * @param psi Psi angle,            IN RADIANS
 * @param theta Theta angle,        IN RADIANS
 * @return the heading, in degrees, with 0 being north, positive angles going clockwise,
 * and negative angles going counterclockwise (i.e., 90 deg is east, -90 is west)
 */
- (double) getOrientationFromEulerLat:(double)lat lon:(double)lon psi:(double)psi theta:(double)theta
{
    double sinlat = sin(lat);
    double sinlon = sin(lon);
    double coslon = cos(lon);
    double coslat = cos(lat);
    double sinsin = sinlat * sinlon;
    
    double cosTheta = cos(theta);
    double cosPsi = cos(psi);
    double sinPsi = sin(psi);
    double sinTheta = sin(theta);
    
    
    double cosThetaCosPsi = cosTheta * cosPsi;
    double cosThetaSinPsi = cosTheta * sinPsi;
    double sincos = sinlat * coslon;
    
    double b11 = -sinlon * cosThetaCosPsi + coslon * cosThetaSinPsi;
    double b12 = -sincos * cosThetaCosPsi - sinsin * cosThetaSinPsi - coslat * sinTheta;
    
    return _toDegrees * (atan2(b11, b12));//range is -pi to pi
}

/**
 * Gets a degree pitch for an entity based on euler angles. All angular values passed in must be in radians.
 * @param lat Entity's latitude,    IN RADIANS
 * @param lon Entity's longitude,   IN RADIANS
 * @param psi Psi angle,            IN RADIANS
 * @param theta Theta angle,        IN RADIANS
 * @return the pitch, in degrees, with 0 being level. A negative values is when the entity's
 * nose is pointing downward, positive value is when the entity's nose is pointing upward.
 */
-(double) getPitchFromEulerLat:(double)lat lon:(double) lon psi:(double) psi theta:(double) theta
{
    double sinlat = sin(lat);
    double sinlon = sin(lon);
    double coslon = cos(lon);
    double coslat = cos(lat);
    double cosLatCosLon = coslat * coslon;
    double cosLatSinLon = coslat * sinlon;
    
    double cosTheta = cos(theta);
    double cosPsi = cos(psi);
    double sinPsi = sin(psi);
    double sinTheta = sin(theta);
    
    return _toDegrees * (asin(cosLatCosLon*cosTheta*cosPsi + cosLatSinLon*cosTheta*sinPsi - sinlat*sinTheta));
}

/**
 * Gets the degree roll for an entity based on euler angles. All angular values passed in must be in radians.
 * @param lat Entity's latitude,    IN RADIANS
 * @param lon Entity's longitude,   IN RADIANS
 * @param psi Psi angle,            IN RADIANS
 * @param theta Theta angle,        IN RADIANS
 * @param phi Phi angle,            IN RADIANS
 * @return the roll, in degrees, with 0 being level flight, + roll is clockwise when looking out the front of the entity.
 */
-(double) getRollFromEulerLat:(double) lat lon:(double)lon psi:(double)psi theta:(double)theta phi:(double) phi
{
    double sinlat = sin(lat);
    double sinlon = sin(lon);
    double coslon = cos(lon);
    double coslat = cos(lat);
    double cosLatCosLon = coslat * coslon;
    double cosLatSinLon = coslat * sinlon;
    
    double cosTheta = cos(theta);
    double sinTheta = sin(theta);
    double cosPsi   = cos(psi);
    double sinPsi   = sin(psi);
    double sinPhi   = sin(phi);
    double cosPhi   = cos(phi);
    
    double sinPhiSinTheta = sinPhi * sinTheta;
    double cosPhiSinTheta = cosPhi * sinTheta;
    
    double b23 = cosLatCosLon*(-cosPhi*sinPsi + sinPhiSinTheta*cosPsi) +
    cosLatSinLon*( cosPhi*cosPsi + sinPhiSinTheta*sinPsi) +
    sinlat * (sinPhi * cosTheta);
    
    double b33 = cosLatCosLon*( sinPhi*sinPsi + cosPhiSinTheta*cosPsi) +
    cosLatSinLon*(-sinPhi*cosPsi + cosPhiSinTheta*sinPsi) +
    sinlat * (cosPhi * cosTheta);
    
    return _toDegrees * (atan2(-b23, -b33));
}

/**
 * Gets the Euler Theta value (in radians) from position and Tait-Brayn yaw and roll angles
 * @param lat Entity's latitude,    IN RADIANS
 * @param lon Entity's longitude,   IN RADIANS
 * @param yaw   entity's yaw angle (also know as the entity's bearing or heading angle), in degrees
 * @param pitch entity's pitch angle, in degrees
 * @return the Theta value in radians
 */
-(double)getThetaFromTaitBryanAnglesLat:(double)lat lon:(double)lon yaw:(double)yaw pitch:(double) pitch
{
    double sinLat = sin(lat);
    double cosLat = cos(lat);
    
    double cosPitch = cos(pitch*_toRadians);
    double sinPitch = sin(pitch*_toRadians);
    double cosYaw   = cos(yaw*_toRadians);
    
    return asin(  -cosLat * cosYaw * cosPitch - sinLat * sinPitch );
}

/**
 * Gets the Euler Psi value (in radians) from position and Tait-Brayn yaw and roll angles
 * @param lat Entity's latitude,    IN RADIANS
 * @param lon Entity's longitude,   IN RADIANS
 * @param yaw   ettity's yaw angle (also know as the entity's bearing or heading angle), in degrees
 * @param pitch entity's pitch angle, in degrees
 * @return the Psi value in radians
 */
-(double)getPsiFromTaitBryanAnglesLat:(double)lat lon:(double)lon yaw:(double)yaw pitch:(double)pitch
{
    
    double sinLat = sin(lat);
    double sinLon = sin(lon);
    double cosLon = cos(lon);
    double cosLat = cos(lat);
    double cosLatCosLon = cosLat * cosLon;
    double cosLatSinLon = cosLat * sinLon;
    double sinLatCosLon = sinLat * cosLon;
    double sinLatSinLon = sinLat * sinLon;
    
    double cosPitch = cos(pitch*_toRadians);
    double sinPitch = sin(pitch*_toRadians);
    double sinYaw   = sin(yaw*_toRadians);
    double cosYaw   = cos(yaw*_toRadians);
    
    double a_11 = -sinLon * sinYaw * cosPitch - sinLatCosLon * cosYaw * cosPitch + cosLatCosLon * sinPitch;
    double a_12 =  cosLon * sinYaw * cosPitch - sinLatSinLon * cosYaw * cosPitch + cosLatSinLon * sinPitch;
    
    return atan2(a_12, a_11);
}

/**
 * Gets the Euler Phi value (in radians) from position and Tait-Brayn yaw, pitch and roll angles
 * @param lat Entity's latitude,    IN RADIANS
 * @param lon Entity's longitude,   IN RADIANS
 * @param yaw yaw angle (also know as the entity's bearing or heading angle), in degrees
 * @param pitch entity's pitch angle, in degrees
 * @param roll  entity's roll angle (0 is level flight, + roll is clockwise looking out the nose), in degrees
 * @return the Phi value in radians
 */
-(double) getPhiFromTaitBryanAnglesLat:(double)lat lon:(double)lon yaw:(double)yaw pitch:(double)pitch roll:(double)roll
{
    
    double sinLat = sin(lat);
    double cosLat = cos(lat);
    
    double cosRoll  = cos(roll*_toRadians);
    double sinRoll  = sin(roll*_toRadians);
    double cosPitch = cos(pitch*_toRadians);
    double sinPitch = sin(pitch*_toRadians);
    double sinYaw   = sin(yaw*_toRadians);
    double cosYaw   = cos(yaw*_toRadians);
    
    double a_23 = cosLat * (-sinYaw * cosRoll + cosYaw * sinPitch * sinRoll) - sinLat * cosPitch * sinRoll;
    double a_33 = cosLat * ( sinYaw * sinRoll + cosYaw * sinPitch * cosRoll) - sinLat * cosPitch * cosRoll;
    
    return atan2(a_23, a_33);
}

@end
