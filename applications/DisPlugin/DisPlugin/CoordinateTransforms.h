#pragma once

// Sedris SRM package, version 4.4. 
#include "BaseSRF.h"
#include "srf_all.h"

class CoordinateTransforms
{
private:

public:
	CoordinateTransforms(void);
	~CoordinateTransforms(void);

	void latLonAltToDIS(double latitude, double longitude, double altitude);
};
