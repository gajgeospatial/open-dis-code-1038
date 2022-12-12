//
//  DisMapIpadViewController.h
//  DisMapIpad
//
//  Created by Donald McGregor on 6/18/10.
//  Copyright NPS 2010. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MapKit/MapKit.h>

#import "MapViewDelegate.h"
#import "PduDispatcher.h"
#import "PduFactory.h"

@interface DisMapIpadViewController : UIViewController 
    {
        MKMapView *mapView;
        MapViewDelegate *mapViewDelegate;
        float angleInRadians;
        PduDispatcher* dispatcher;
        PduFactory  *factory;
        NSArray* annotations;
    }
    
    /**
     * Called by a Network object when a packet arrives from the network.
     */
    -(void)networkData:(NSData*)data;
    

@end

