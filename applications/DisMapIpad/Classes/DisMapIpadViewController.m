//
//  DisMapIpadViewController.m
//  DisMapIpad
//
//  Created by Donald McGregor on 6/18/10.
//  Copyright NPS 2010. All rights reserved.
//

#import "DisMapIpadViewController.h"

#import "MapAnnotation.h"
#import "MapViewDelegate.h"
#import "PduFactory.h"
#import "EntityStatePdu.h"
#import "EntityID.h"

@implementation DisMapIpadViewController


/*
// The designated initializer. Override to perform setup that is required before the view is loaded.
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    if ((self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil])) {
        // Custom initialization
    }
    return self;
}
*/

/*
// Implement loadView to create a view hierarchy programmatically, without using a nib.
- (void)loadView {
}
*/



// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    [super viewDidLoad];
    
    // Add a Google Maps view
    mapView = [[MKMapView alloc] initWithFrame:[[self view] bounds]];
    mapView.showsUserLocation=FALSE;
    angleInRadians = 0.0;
    // mapView.mapType=MKMapTypeHybrid;
    mapView.mapType=MKMapTypeStandard;
    mapView.zoomEnabled=TRUE;
    
    // PDU dispatcher; maintains database of entities we know about,
    // sends received PDUs to those entities
    dispatcher = [[PduDispatcher alloc] init];
    
    // Factory for creating and decoding PDUs
    factory = [[PduFactory alloc] init];
   
    // The delegate for the map view
    mapViewDelegate = [[MapViewDelegate alloc] init];
    [mapView setDelegate:mapViewDelegate];
    
    // The area exposed on the map view
    MKCoordinateRegion region;
    region.span.latitudeDelta = 0.2;
    region.span.longitudeDelta = 0.2;
    region.center.latitude = 36.6;
    region.center.longitude = -121.8;
    
    [mapView setRegion:region animated:TRUE];
    
    [[self view] insertSubview:mapView atIndex:0];
    
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
        /*
        NSLog(@"EID is %i, %i, %i", [eid site], [eid application], [eid entity]);
        NSLog(@"  Lat=%f, Lon=%f", [[espdu entityLocation] x], [[espdu entityLocation] y]);
        NSLog(@"  Heading=%f", [[espdu entityOrientation] psi]);
         */
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
                
               // NSLog(@"Distance moved is %f", [newLoc distanceFromLocation:[a location]]);
                if([newLoc distanceFromLocation:[a location]] > 2.0)
                {
                   // NSLog(@"Distance moved is more than 2m");
                    a.positionChanged = YES;
                    [a setLocation:newLoc];
                    [a moveToLat:latitude andLon:longitude];
                    
                }
            }
        
        } // am type of our map annotation
        
    } // end loop through annotations
    
    // Didn't find it? Create a new map annotation
    if(!found)
    {
        //NSLog(@"Failed to find annotation to match EID: adding a new annotation");
        CLLocationCoordinate2D pos;
        NSString* title = [NSString stringWithFormat:@"EntityID:%i, %i, %i", [eid site], [eid application], [eid entity]];
        [title retain];
        pos.latitude =  [[espdu entityLocation] x];
        pos.longitude =  [[espdu entityLocation] y];
        
        MapAnnotation *newAnnotation = [[MapAnnotation alloc] initWithTitle:title andSubtitle:@"a subtitle" atLocation:&pos];
        [newAnnotation retain];
        [newAnnotation setEntityID:eid];
        CLLocation* newLoc = [[CLLocation alloc] initWithLatitude:pos.latitude longitude:pos.longitude];
        [newAnnotation setLocation:newLoc];
        
        [mapView addAnnotation:newAnnotation];
        [newAnnotation moveToLat:[[espdu entityLocation] x] andLon:[[espdu entityLocation] y]];
        //NSLog(@"Added annotation at %f, %f", pos.latitude, pos.longitude);
    }
    
    // Failed attempt to get the map to redraw
    [mapView setCenterCoordinate:mapView.region.center animated:NO];
    mapView.contentMode = UIViewContentModeRedraw;
    [mapView setNeedsDisplay];
    
    // Generally speaking, it is a bad idea to draw to the screen
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
    NSMutableArray *movedAnnotations = [[NSMutableArray alloc] init];
        
    //[mapView removeAnnotations:anno];
    //NSLog(@"number of annotation is copy list after removal is %i", [copyOfAnno count]);
    //[mapView addAnnotations:copyOfAnno];
    
    for(int idx = 0; idx < [anno count]; idx++)
    {
        MapAnnotation* anAnno = (MapAnnotation*)[anno objectAtIndex:idx];
        
        if([anAnno positionChanged] == YES)
        {
            [mapView removeAnnotation:anAnno];
            anAnno.positionChanged = NO;
            [movedAnnotations addObject:anAnno];
        }
       
    }  
    
    // re-insert the annotations that have moved
    [mapView addAnnotations:movedAnnotations];
    [mapView setNeedsDisplay];
    
}



// Override to allow orientations other than the default portrait orientation.
- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    [mapView setNeedsLayout];
    return YES;
}

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

@end
