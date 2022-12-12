//
//  PduDispatcher.m
//  ObjcDis
//
//  Created by Donald McGregor on 8/28/09.
//  Copyright 2009 NPS. All rights reserved.
//

#import "PduDispatcher.h"
#import "PduReceiverProtocol.h"
#import "EntityStatePdu.h"


@implementation PduDispatcher

-(id)init
{
    self = [super init];
    if(self)
    {
        entities = [NSMutableDictionary dictionaryWithCapacity:1];
    }
    return self;
}

-(void)addEntity:(id <PduReceiverProtocol> )anEntity withEntityID:(EntityID*)entityId
{
    [entities setObject:anEntity forKey:entityId];
}

-(void)dispatchPdu:(Pdu*)aPdu
{
    // Dispatch an entity state pdu
    if([aPdu isMemberOfClass:[Pdu class]])
    {
        EntityStatePdu *espdu = (EntityStatePdu*)aPdu;
        <PduReceiverProtocol> anEntity = [entities valueForKey: (id)[espdu entityID]];
        [((<PduReceiverProtocol> )anEntity) receivePdu:espdu from:self];
        
        // SHould also add the entity to the database here
    }
    
}

@end
