//
//  MapViewDelegate.m
//  MapApp
//
//  Created by Donald McGregor on 8/12/09.
//  Copyright 2009 NPS. All rights reserved.
//

#import "MapViewDelegate.h"
#import "MapAnnotation.h"
#import "NPSAnnotationView.h"

@implementation MapViewDelegate

/**
 * Initializer. Creates the annotation view list.
 */
-(id)init
{
    self = [super init];
    
    if(self)
    {
        defaultPinAnnotationView = [[MKPinAnnotationView alloc] initWithAnnotation:nil reuseIdentifier:@"default"]; 
    }
    
    return self;
}


/**
 * Returns an AnnotationView associated with a given Annotation. The method takes an annotation and
 * looks up the associated view. It is essentially a way
 * for the view to become associated with the annotation model.
 */
- (MKAnnotationView *)mapView:(MKMapView *)mapView viewForAnnotation:(id <MKAnnotation>)annotation
{    
     //return defaultPinAnnotationView;
    
    //NSLog(@"associating map annotation view with annotation");
    
    MapAnnotation *npsAnnotation = (MapAnnotation*)annotation;
    
    MKAnnotationView *annotationView = [mapView dequeueReusableAnnotationViewWithIdentifier:@"DISMap"];
    
    if(annotationView == nil)
    {
        //MKAnnotationView *annotationView = [[[MKAnnotationView alloc] initWithAnnotation:npsAnnotation reuseIdentifier:[npsAnnotation identifier]] autorelease];
        MKAnnotationView *annotationView = [[[MKAnnotationView alloc] initWithAnnotation:npsAnnotation reuseIdentifier:@"DISMap"] autorelease];
        [annotationView setImage:[UIImage imageNamed:@"symbol.png"]];
    }
    
            
   //NSLog(@"Adding new annotation view for %s", [eidString cString]);
        
   return annotationView;
}

/**
 * Tells the delegate that one or more annotation views were added to the map. 
 * This method is optional.
 */
- (void)mapView:(MKMapView *)mapView didAddAnnotationViews:(NSArray *)views
{
    //NSLog(@"In MapViewDelegate, informed of adding annotation views");
}
@end
