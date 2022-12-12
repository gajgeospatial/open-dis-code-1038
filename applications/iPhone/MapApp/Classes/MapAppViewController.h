

#import <UIKit/UIKit.h>
#import <MapKit/MapKit.h>

#import "MapViewDelegate.h"
#import "PduDispatcher.h"
#import "PduFactory.h"

/**
 * Controller object for a MKMapView. This receives data from the network,
 * and in response adds annotations to the map view.
 *
 * @author DMcG
 */
@interface MapAppViewController : UIViewController 
{
    MKMapView *mapView;
    MapViewDelegate *mapViewDelegate;
    float angleInRadians;
    PduDispatcher* dispatcher;
    PduFactory  *factory;
}

/**
 * Called by a Network object when a packet arrives from the network.
 */
-(void)networkData:(NSData*)data;




@end

