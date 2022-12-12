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
 * Initializer
 */
-(id)init
{
    self = [super init];
    
    if(self)
    {
        viewsForAnnotations = [[NSMutableDictionary alloc] initWithCapacity:10];
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
   // UIImage *infantrySymbol = [UIImage imageNamed:@"infantry.png"];
   // unitView = [[MKAnnotationView alloc] initWithAnnotation:annotation reuseIdentifier:@"infantry"];
   // [unitView setImage:infantrySymbol];
   // [unitView retain];
    
    // This could grow to a long if-then-else chain as we expend the types of views that
    // we return. 
    if([annotation isKindOfClass:[MapAnnotation class]])
    {
        MapAnnotation *npsAnnotation = (MapAnnotation*)annotation;
        
        // Every annotation is marked (in this case) with an entity ID from DIS, which is a unique
        // identifier for the entity.
        EntityID *eid = [npsAnnotation entityID];
        int site = [eid site];
        int application = [eid application];
        int entity = [eid entity];
        
        NSString *eidString = [[NSString alloc] initWithFormat:@"(%i, %i, %i)", site, application, entity];
        NSLog(eidString);
        
        // See if we already have this view in our database of views; if so return it. viewsForAnnotation
        // is a dictionary with a key of the entity ID and a value of the view associated with that entity ID.
        MKAnnotationView *myView = [viewsForAnnotations objectForKey:eidString];
        if(myView)
        {
            //myView.transform = CGAffineTransformIdentity;
            //myView.transform = CGAffineTransformMakeRotation([npsAnnotation heading]);  
            return myView;
        }
        
        // If not found, create a new view, add it to our list, and return that.
        NPSAnnotationView *npsView = [[NPSAnnotationView alloc] initWithAnnotation:annotation reuseIdentifier:eidString];
        [npsView setImage:[UIImage imageNamed:@"symbol.png"]];
        //npsView.transform = CGAffineTransformIdentity;
        //npsView.transform = CGAffineTransformMakeRotation(([npsAnnotation heading]) * 3.1415/180.0);   
        //NSLog(@"heading is %f", [npsAnnotation heading]);
        
        [viewsForAnnotations setObject:npsView forKey:eidString];
        return npsView;
    }
    else
    {
         UIImage *infantrySymbol = [UIImage imageNamed:@"symbol.png"];
         MKAnnotationView*  unitView = [[MKAnnotationView alloc] initWithAnnotation:annotation reuseIdentifier:@"infantry"];
         [unitView setImage:infantrySymbol];
          return unitView;
    }
     
   
    return defaultPinAnnotationView;
}

/**
 * Tells the delegate that one or more annotation views were added to the map. 
 * This method is optional.
 */
- (void)mapView:(MKMapView *)mapView didAddAnnotationViews:(NSArray *)views
{
    
}
@end
