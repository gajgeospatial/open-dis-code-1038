//
//  MapAppAppDelegate.m
//  MapApp
//
//  Created by Donald McGregor on 7/30/09.
//  Copyright NPS 2009. All rights reserved.
//

#import "MapAppAppDelegate.h"
#import "MapAppViewController.h"
#import "DataOutput.h"

@implementation MapAppAppDelegate

@synthesize window;
@synthesize viewController;


- (void)applicationDidFinishLaunching:(UIApplication *)application 
{    
    // Start network
    network = [[Network alloc] initOnPort:62040 joinGroup:@"239.1.2.3"];
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
    
}


- (void)dealloc {
    [viewController release];
    [window release];
    [super dealloc];
}


@end
