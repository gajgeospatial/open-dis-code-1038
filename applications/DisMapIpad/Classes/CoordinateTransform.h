//
//  CoordinateTransform.h
//  DisMapIpad
//
//  Created by Donald McGregor on 6/23/10.
//  Copyright 2010 NPS. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MapKit/MapKit.h>
#import "Vector3Double.h"


/**
 * Handles coordinate transforms to and from the DIS
 * coordinate system (earth-centered, euclidian) to
 * geodetic (lat/lon, altitude).
 * 
 * The DIS location is generally in Vector3Double format
 * and called "disLocation", while the MapKit location is
 * in CLLocation objects that use lat/lon and are called
 * "location". 
 *
 * Scary math implemented in Java by loyaj and bhughes from Military Handbook 600008.
 * Conversions may not be accurate near the poles. Uses WGS84. Ported
 * from Java to ObjC by DMcG.
 *
 * DMcG
 */
@interface CoordinateTransform : NSObject 
{
}

-(id)init;

/**
 * Converts xyz world coordinates to latitude and longitude (IN DEGREES). This algorithm may not be 100% accurate
 * near the poles. Uses WGS84 , though you can change the ellipsoid constants a and b if you want to use something
 * else. These formulas were obtained from Military Handbook 600008
 * @param xyz A Vector3Double with earth-centered x, y, z
 * @return An CLLocation object with the lat, long, and elevation corresponding to those coordinates.
 * Elevation is in meters, lat and long are in degrees
 */
-(CLLocation *) locationFromDis:(Vector3Double *) disLocation;

/**
 * Converts lat long and geodetic height (elevation) into DIS World Coordinates XYZ
 * This algorithm also uses the WGS84 ellipsoid, though you can change the values
 * of a and b for a different ellipsoid. Adapted from Military Handbook 600008
 * @param latitude The latitude, IN DEGREES
 * @param longitude The longitude, in DEGREES
 * @param height The elevation, in meters
 * @return a Vector3Double with the calculated X, Y, and Z values in DIS World coordinates (earth-centered)
 */
-(Vector3Double *) disFromLocation:(CLLocation *)location;


// TODO: convert inputs to degrees, I suppose, to be consistent.

/**
 * Gets a degree heading for an entity based on euler angles. All angular values passed in must be in radians.
 * @param lat Entity's latitude,    IN RADIANS
 * @param lon Entity's longitude,   IN RADIANS
 * @param psi Psi angle,            IN RADIANS
 * @param theta Theta angle,        IN RADIANS
 * @return the heading, in degrees, with 0 being north, positive angles going clockwise,
 * and negative angles going counterclockwise (i.e., 90 deg is east, -90 is west)
 */
- (double)getOrientationFromEulerLat:(double)lat lon:(double)lon psi:(double)psi theta:(double)theta;

/**
 * Gets a degree pitch for an entity based on euler angles. All angular values passed in must be in radians.
 * @param lat Entity's latitude,    IN RADIANS
 * @param lon Entity's longitude,   IN RADIANS
 * @param psi Psi angle,            IN RADIANS
 * @param theta Theta angle,        IN RADIANS
 * @return the pitch, in degrees, with 0 being level. A negative values is when the entity's
 * nose is pointing downward, positive value is when the entity's nose is pointing upward.
 */
-(double) getPitchFromEulerLat:(double) lat lon:(double) lon psi:(double) psi theta:(double) theta;


/**
 * Gets the degree roll for an entity based on euler angles. All angular values passed in must be in radians.
 * @param lat Entity's latitude,    IN RADIANS
 * @param lon Entity's longitude,   IN RADIANS
 * @param psi Psi angle,            IN RADIANS
 * @param theta Theta angle,        IN RADIANS
 * @param phi Phi angle,            IN RADIANS
 * @return the roll, in degrees, with 0 being level flight, + roll is clockwise when looking out the front of the entity.
 */
-(double) getRollFromEulerLat:(double) lat lon:(double)lon psi:(double)psi theta:(double)theta phi:(double) phi;


/**
 * Gets the Euler Theta value (in radians) from position and Tait-Brayn yaw and roll angles
 * @param lat Entity's latitude,    IN RADIANS
 * @param lon Entity's longitude,   IN RADIANS
 * @param yaw   entity's yaw angle (also know as the entity's bearing or heading angle), in degrees
 * @param pitch entity's pitch angle, in degrees
 * @return the Theta value in radians
 */
-(double)getThetaFromTaitBryanAnglesLat:(double)lat lon:(double)lon yaw:(double)yaw pitch:(double) pitch;

/**
 * Gets the Euler Psi value (in radians) from position and Tait-Brayn yaw and roll angles
 * @param lat Entity's latitude,    IN RADIANS
 * @param lon Entity's longitude,   IN RADIANS
 * @param yaw   ettity's yaw angle (also know as the entity's bearing or heading angle), in degrees
 * @param pitch entity's pitch angle, in degrees
 * @return the Psi value in radians
 */
-(double)getPsiFromTaitBryanAnglesLat:(double)lat lon:(double)lon yaw:(double)yaw pitch:(double)pitch;

/**
 * Gets the Euler Phi value (in radians) from position and Tait-Brayn yaw, pitch and roll angles
 * @param lat Entity's latitude,    IN RADIANS
 * @param lon Entity's longitude,   IN RADIANS
 * @param yaw yaw angle (also know as the entity's bearing or heading angle), in degrees
 * @param pitch entity's pitch angle, in degrees
 * @param roll  entity's roll angle (0 is level flight, + roll is clockwise looking out the nose), in degrees
 * @return the Phi value in radians
 */
-(double) getPhiFromTaitBryanAnglesLat:(double)lat lon:(double)lon yaw:(double)yaw pitch:(double)pitch roll:(double)roll;

@end
