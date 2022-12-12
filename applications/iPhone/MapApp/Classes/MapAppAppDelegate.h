
#import <UIKit/UIKit.h>

#import "Network.h"
#import "Location.h"

@class MapAppViewController;

/** 
 * delegate object for the application object. Various methods are called
 * (see the UIApplicationDelegate protocol) during the lifetime of the
 * application. The primary one we are worried about is the appDidInit.
 *
 * @author DMcG
 */

@interface MapAppAppDelegate : NSObject <UIApplicationDelegate> 
{
    UIWindow *window;
    MapAppViewController *viewController;
    Network *network;
    Location *location;
    NSTimer  *locationUpdate;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet MapAppViewController *viewController;

@end

