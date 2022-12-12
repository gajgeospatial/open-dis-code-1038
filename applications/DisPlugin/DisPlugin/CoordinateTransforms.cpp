#include "CoordinateTransforms.h"

#include <iostream>

// Sedris SRM package. See sedris.org. This uses
// the 4.4 release version.
#include <BaseSRF.h>
#include <srf_all.h>
#include <Celestiodetic.h>
#include "Exception.h"

using namespace std;

#define SRM_PI_DEFINE 3.14159265358979323846
#define SRM_PI_DIV_2  1.57079632679489661923



CoordinateTransforms::CoordinateTransforms(void)
{
	
}

CoordinateTransforms::~CoordinateTransforms(void)
{
}

/**
 * Convert from (lat, lon, alt), supplied by XPlane, to DIS coordinates, a
 * rectilinear coordinate system with origin at the center of the earth.
 */
void CoordinateTransforms::latLonAltToDIS(double latitude, double longitude, double altitude)
{
	srm::SRF_Celestiodetic* geodetic_WGS84_SRF = srm::SRF_Celestiodetic::create(
		SRM_ORMCOD_WGS_1984,           // ORM model code
		SRM_RTCOD_WGS_1984_IDENTITY);  // Reference transformation code


}
