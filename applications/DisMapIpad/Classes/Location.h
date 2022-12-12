/**
 * The location class embeds a CLLocationManager and acts as a delegate to that class.
 * This means we receive periodic position updates, and heading updates on devices equiped
 * with a compass. We save the position and heading every time we are updated.<p>
 *
 * This class can also send out a periodic position update. This is done via an NSTimer that
 * is set up in the application delegate that calls the timerFireMethod: every specified
 * number of seconds.<p>
 *
 * @author DMcG
 */

#import <UIKit/UIKit.h>
#import <CoreLocation/CoreLocation.h>

#import "Network.h"
#import "EntityID.h"


@interface Location : NSObject <CLLocationManagerDelegate>
{
    Network *network;
    CLLocationManager *locationManager;
    CLLocation *lastLocation;
    CLHeading *lastHeading;
    EntityID *eid;
}

@property (nonatomic, retain) CLLocation *lastLocation;
@property (nonatomic, retain) CLHeading *lastHeading;

-(id)initWithNetwork:(Network*)network;
-(void)timerFireMethod:(NSTimer*)theTimer;

// CLLocationManagerDelegate methods (all optional)
- (void)locationManager:(CLLocationManager *)manager 
            didUpdateToLocation:(CLLocation *)newLocation 
            fromLocation:(CLLocation *)oldLocation;

- (void)locationManager:(CLLocationManager *)manager 
       didFailWithError:(NSError *)error ;

- (void)locationManager:(CLLocationManager *)manager 
       didUpdateHeading:(CLHeading *)newHeading;

- (BOOL)locationManagerShouldDisplayHeadingCalibration:(CLLocationManager *)manager;





@end
