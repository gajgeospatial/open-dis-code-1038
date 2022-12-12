//
//  DisMapIpadAppDelegate.h
//  DisMapIpad
//
//  Created by Donald McGregor on 6/18/10.
//  Copyright NPS 2010. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Network.h"
#import "Location.h"

@class DisMapIpadViewController;

@interface DisMapIpadAppDelegate : NSObject <UIApplicationDelegate> 
{
    UIWindow *window;
    DisMapIpadViewController *viewController;
    
    // Handles network communications
    Network *network;
    
    // Location class--interacts with GPS/cell phone tower triangulation
    // to give real-world position of the device
    Location *location;
    NSTimer  *locationUpdate;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet DisMapIpadViewController *viewController;

@end

