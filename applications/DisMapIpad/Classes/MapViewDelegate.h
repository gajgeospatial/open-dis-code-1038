/**
 * This acts as a delegate for an MKMapView; the methods below are called when
 * the specified events occur in the MapView.
 *
 * @author DMcG
 */

#import <UIKit/UIKit.h>
#import <MapKit/MapKit.h>
#import <Foundation/Foundation.h>


@interface MapViewDelegate : NSObject  <MKMapViewDelegate>
{
    MKPinAnnotationView *defaultPinAnnotationView;
}

-(id)init;

/**
 * Returns the view associated with a given annotation
 */
- (MKAnnotationView *)mapView:(MKMapView *)mapView viewForAnnotation:(id <MKAnnotation>)annotation;

/**
 * Tells the delegate that one or more annotation views were added to the map. 
 * This method is optional.
 */

- (void)mapView:(MKMapView *)mapView didAddAnnotationViews:(NSArray *)views;

@end
