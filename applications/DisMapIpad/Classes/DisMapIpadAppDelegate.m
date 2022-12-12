//
//  DisMapIpadAppDelegate.m
//  DisMapIpad
//
//  Created by Donald McGregor on 6/18/10.
//  Copyright NPS 2010. All rights reserved.
//

#import "DisMapIpadAppDelegate.h"
#import "DisMapIpadViewController.h"

// Multicast group and port to listen on for DIS packets
#define MULTICAST_GROUP  "239.1.2.3"
#define PORT 62040

@implementation DisMapIpadAppDelegate

@synthesize window;
@synthesize viewController;


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {    
  
    // Start network
    network = [[Network alloc] initOnPort:PORT joinGroup:@MULTICAST_GROUP];
    if(network == nil)
    {
        NSLog(@"***Error joining multicast group or opening socket");
    }
    
    // The object and method that gets called when a packet arrives
    [network setCallbackObject:viewController andMethod:@selector(networkData:)];
    
    // Start the thread that listens on the network
    [NSThread detachNewThreadSelector:@selector(startThreadWithObject:) toTarget:network withObject:@"Network Thread"];
    
    NSLog(@"detached network thread");
    
    // Create a timer for periodically sending out position updates. First we need an object to get periodic
    // position updates:
    location = [[Location alloc] initWithNetwork:network];
    
    // Then create a timer to have it send out periodically
    
    locationUpdate = [NSTimer scheduledTimerWithTimeInterval:1.0 
                                                      target:location 
                                                    selector:@selector(timerFireMethod:) 
                                                    userInfo:nil 
                                                     repeats:YES];
    NSLog(@"Started periodic timed task to send position updates");
    
    // Override point for customization after app launch    
    [window addSubview:viewController.view];
    [window makeKeyAndVisible];

	return YES;
}


- (void)dealloc {
    [viewController release];
    [window release];
    [super dealloc];
}


@end
