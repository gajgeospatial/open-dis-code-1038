//
//  MapAnnotation.m
//  MapApp
//
//  Created by Donald McGregor on 7/31/09.
//  Copyright 2009 NPS. All rights reserved.
//

#import "MapAnnotation.h"


@implementation MapAnnotation

@synthesize location; // getters and setters "written" automatically
@synthesize heading;  
@synthesize coordinate;
@synthesize entityID;
@synthesize positionChanged;

-(id)initWithTitle:(NSString*)pTitle andSubtitle:(NSString*)pSubTitle atLocation:(CLLocationCoordinate2D*)pLocation
{
    self = [super init];
    if(self)
    {
        title = pTitle;
        [title retain];
        
        subTitle = pSubTitle;
        [subTitle retain];
        
        coordinate.latitude = pLocation->latitude;
        coordinate.longitude = pLocation->longitude;
        
        // Initialize the entity ID. This is later used for lookups.
        entityID = [[EntityID alloc] init];
        [entityID setApplication:0];
        [entityID setSite:0];
        [entityID setEntity:0];
        positionChanged = NO;
    }
    
    return self;
}

-(NSString*)title
{
    return title;
}

-(NSString*)subTitle
{
    return subTitle;
}

-(NSString*)identifier
{
    NSString* aString = [NSString stringWithFormat:@"(%i,%i, %i)", [entityID site], [entityID application], [entityID entity]];
    [aString retain];
    return aString;
}

-(void)moveToLat:(float)aLat andLon:(float)aLon
{
    coordinate.latitude = aLat;
    coordinate.longitude = aLon;
}



@end
