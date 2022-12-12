//
//  Location.m
//  MapApp
//
//  Created by Donald McGregor on 8/13/09.
//  Copyright 2009 NPS. All rights reserved.
//

#import "Location.h"
#import "EntityStatePdu.h"


@implementation Location

@synthesize lastLocation;
@synthesize lastHeading;

-(id)initWithNetwork:(Network*)pNetwork;
{
    self = [super init];
    if(self)
    {
        network = pNetwork;
        
        // Initialize the location manager. This sets this instance of the object
        // as the delegate, with maximum accuracy asked for.
        locationManager = [[CLLocationManager alloc] init];
        [locationManager startUpdatingLocation];
        locationManager.delegate = self;
        locationManager.distanceFilter = kCLDistanceFilterNone; 
        locationManager.desiredAccuracy = kCLLocationAccuracyBest;
        lastLocation = nil;
        lastHeading = nil;
        
        // We can send out ESPDUs for our location. We pick, randomly, an entity
        // ID to identify this entity.
        int seed = (int)time(0);
        srand(seed);
        eid = [[EntityID alloc] init];
        [eid setSite:4];
        [eid setApplication:7];
        [eid setEntity:(short)rand()];
    }
    
    return self;
}

-(void)timerFireMethod:(NSTimer*)theTimer;
{
    // Note that we care mostly about position, not heading. If we don't have a heading we send an update
    // anyway, with a heading of north.
    
    // It is possible that we will be called by the timer before the location manager has initialized
    // itself and given us a position.
    if(lastLocation == nil)
    {
        NSLog(@"Unable to send position update--unknown own position, probably because location manager hasn't updated us yet");
        return;
    }
    
    // Retrieve a position and build an ESPDU around that
    CLLocationCoordinate2D latLon = [lastLocation coordinate];
    EntityStatePdu *espdu = [[EntityStatePdu alloc] init];
    [espdu setEntityID:eid];
    [[espdu entityLocation] setX:latLon.latitude];
    [[espdu entityLocation] setY:latLon.longitude];
    time_t currentTime = time(0);
    [espdu setTimestamp:currentTime];
    
    // Marshal out an espdu to memory, then send it
    DataOutput *out = [[DataOutput alloc] init];
    [espdu marshalUsingStream:out];
    NSData *ieee = [out data];
    [network sendData:ieee];
    
    NSLog(@"IEEE DIS data for own position update sent, %f %f", latLon.latitude, latLon.longitude );
    NSLog(@"  EID is %i, %i, %i", [[espdu entityID] site], [[espdu entityID] application], [[espdu entityID] entity]);
    }

/**
 * Called when the location manager provides us with a position update
 */

- (void)locationManager:(CLLocationManager *)manager 
          didUpdateToLocation:(CLLocation *)newLocation 
          fromLocation:(CLLocation *)oldLocation 
{
    NSLog(@"position update recieved");
    CLLocationCoordinate2D latLon = [newLocation coordinate];
    [self setLastLocation:newLocation];
    NSLog(@"New position=(%f, %f)", latLon.latitude, latLon.longitude);
}

/**
 * Called when the location manager fails for some reason
 */
- (void)locationManager:(CLLocationManager *)manager 
         didFailWithError:(NSError *)error 
{
    
	NSString *errorType = (error.code == kCLErrorDenied) ? @"Access Denied" : @"Unknown Error";
	UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Error gettingg location from Core Location" message:errorType delegate:nil cancelButtonTitle:@"Okay" otherButtonTitles:nil];
	[alert show];
	[alert release];
    
}

- (void)locationManager:(CLLocationManager *)manager 
       didUpdateHeading:(CLHeading *)newHeading
{
    [self setLastHeading:newHeading];
}

- (BOOL)locationManagerShouldDisplayHeadingCalibration:(CLLocationManager *)manager
{
    return YES;
}


@end
