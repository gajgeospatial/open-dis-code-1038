

#import <Foundation/Foundation.h>
#import <QuartzCore/QuartzCore.h>

#import "MapAppViewController.h"
#import "MapAnnotation.h"

#import "MapViewDelegate.h"
#import "PduFactory.h"
#import "EntityStatePdu.h"
#import "EntityID.h"

@implementation MapAppViewController




// The designated initializer. Override to perform setup that is required before the view is loaded.
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil 
{
    if (self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil]) 
    {
    }
    return self;
}

/*
// Implement loadView to create a view hierarchy programmatically, without using a nib.
- (void)loadView {
}
*/



// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad 
{
    [super viewDidLoad];
    mapView = [[MKMapView alloc] initWithFrame:[[self view] bounds]];
    mapView.showsUserLocation=FALSE;
    angleInRadians = 0.0;
   // mapView.mapType=MKMapTypeHybrid;
    mapView.mapType=MKMapTypeStandard;
    mapView.zoomEnabled=TRUE;
    
    // PDU dispatcher
    dispatcher = [[PduDispatcher alloc] init];
    factory = [[PduFactory alloc] init];
    
    mapViewDelegate = [[MapViewDelegate alloc] init];
    [mapView setDelegate:mapViewDelegate];
    
    // The area exposed on the map view
    MKCoordinateSpan span;
    span.latitudeDelta=0.2;
    span.longitudeDelta=0.2;
    
    CLLocationCoordinate2D location=mapView.userLocation.coordinate;
    
    location.latitude=36.6;
    location.longitude=-121.893;
    
    MKCoordinateRegion region;
    region.span=span;
    region.center=location;
    
    [mapView setRegion:region animated:TRUE];
    [mapView regionThatFits:region];
    
    
    // Add an annotation for NPS
    CLLocationCoordinate2D nps;
    nps.latitude=36.6;
    nps.longitude=-121.893;
   // MapAnnotation *npsAnnotation = [[MapAnnotation alloc] initWithTitle:@"Monterey"  andSubtitle:@"Monterey area" atLocation:&nps];
    //[mapView addAnnotation:npsAnnotation];
    
    // Add annotation for infantry unit
    CLLocationCoordinate2D unitLocation;
    unitLocation.latitude=36.67;
    unitLocation.longitude = -121.8;
   // MapAnnotation *infantryUnitAnnotation = [[MapAnnotation alloc] initWithTitle:@"1st ID" andSubtitle:@"First Infantry Division" atLocation:&unitLocation];
   // MKAnnotationView *infantryUnitAnnotationView = [[MKAnnotationView alloc] initWithAnnotation:infantryUnitAnnotation reuseIdentifier:@"Infantry"];
   // UIImage *infantrySymbol = [UIImage imageNamed:@"infantry.png"];
   // [infantryUnitAnnotationView setImage:infantrySymbol];
   // [mapView addAnnotation:infantryUnitAnnotation];
    
    [[self view] insertSubview:mapView atIndex:0];

}



/*
// Override to allow orientations other than the default portrait orientation.
- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}
*/

- (void)didReceiveMemoryWarning {
	// Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
	
	// Release any cached data, images, etc that aren't in use.
}

- (void)viewDidUnload {
	// Release any retained subviews of the main view.
	// e.g. self.myOutlet = nil;
}


- (void)dealloc {
    [super dealloc];
}

-(void)networkData:(NSData*)data
{
    Pdu* aPdu = [factory pduForData:data];
    //NSLog(@"Got a PDU from the wire and decoded it");
    EntityStatePdu *espdu = nil;
    EntityID* eid = nil;
    BOOL found = NO;
    
    if([aPdu isMemberOfClass:[EntityStatePdu class]])
    {
        espdu = (EntityStatePdu*)aPdu;
        eid = [espdu entityID];
        NSLog(@"EID is %i, %i, %i", [eid site], [eid application], [eid entity]);
        NSLog(@"  Lat=%f, Lon=%f", [[espdu entityLocation] x], [[espdu entityLocation] y]);
        NSLog(@"  Heading=%f", [[espdu entityOrientation] psi]);
    }
   
    NSArray *anno = mapView.annotations;
    
    //NSLog(@"existing number of annoations:%i", [anno count]);
    
    for(int idx = 0; idx < [anno count]; idx++)
    {
        id obj = [anno objectAtIndex:idx];
        if([obj isKindOfClass:[MapAnnotation class]])
        {            
            // This should probably be done in the main graphics thread
            MapAnnotation *a = (MapAnnotation*)obj;
            
            if([[a entityID] isEqual:eid])
            {
                found = YES;
                //NSLog(@"Found an existing annotation that matches the EID; udpating that annotation");
                
                // Extract lat, lon and orientation from the PDU
                double latitude = [[espdu entityLocation] x];
                double longitude = [[espdu entityLocation] y];
                double bearing = [[espdu entityOrientation] psi];
                CLLocation* newLoc = [[CLLocation alloc] initWithLatitude:latitude longitude:longitude];
                [a setLocation:newLoc];

                [a moveToLat:[[espdu entityLocation] x] andLon:[[espdu entityLocation] y]];
                //[a setHeading:0.0];

 
                // Set heading
                //CLHeading newHeading = bearing;
            }
           //[mapView removeAnnotation:a];
          // [mapView addAnnotation:a];

            
           // mapView.transform = CGAffineTransformIdentity;
           // angleInRadians = angleInRadians + .001;
           // mapView.transform = CGAffineTransformMakeRotation(angleInRadians);           
        } // am type of our map annotation
        
          } // end loop through annotations
    
    // Didn't find it? Create a new map annotation
    if(!found)
    {
       // NSLog(@"Failed to find annotation to match EID: adding a new annotation");
        CLLocationCoordinate2D pos;
        pos.latitude =  [[espdu entityLocation] x];
        pos.longitude =  [[espdu entityLocation] y];
        MapAnnotation *newAnnotation = [[MapAnnotation alloc] initWithTitle:@"New annotation" andSubtitle:@"a subtitle" atLocation:&pos];
        //[newAnnotation retain];
        [newAnnotation setEntityID:eid];
        [mapView addAnnotation:newAnnotation];
        [newAnnotation moveToLat:[[espdu entityLocation] x] andLon:[[espdu entityLocation] y]];
        //NSLog(@"Added annotation at %f, %f", pos.latitude, pos.longitude);
    }
    
    //mapView.contentMode = UIViewContentModeRedraw;
    //[mapView setNeedsDisplay];
    
    // This may or may not be necessary. Generally speaking, it is a bad idea to draw to the screen
    // from any thread other than the main thread, and we are actually called from the network
    // thread. This causes us to redraw the screen in the next main event loop in the application.
    NSTimer *redraw = [NSTimer scheduledTimerWithTimeInterval:1.0 
                                                    target:self 
                                                    selector:@selector(redrawAnnotations) 
                                                    userInfo:nil 
                                                    repeats:NO];
    
    [redraw fire];
}

/**
 * Causes a redraw of all the annotations. Done by removing and then re-adding each annotation
 * in the view. This is horribly inefficent and should be optimized, once we have a better way
 * to do this.
 */
-(void)redrawAnnotations
{
    //NSLog(@"Redrawing annotations in main thread");
    NSArray *anno = mapView.annotations;
    
   // NSLog(@"number of annotations is %i", [anno count]);
    
    for(int idx = 0; idx < [anno count]; idx++)
    {
        id obj = [anno objectAtIndex:idx];
        [mapView removeAnnotation:obj];
        [mapView addAnnotation:obj];
    }    
    mapView.contentMode = UIViewContentModeRedraw;
    [mapView setNeedsDisplay];

}

@end
