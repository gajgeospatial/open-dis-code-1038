
#import <Foundation/Foundation.h>

#import "EntityID.h"
#import "PduReceiverProtocol.h"

/**
 * Maintains a list or database of entities in the world. When
 * we receive a PDU from the wire intended for a specific PDU,
 * this routes the PDU to that entity.
 */

@interface PduDispatcher : NSObject 
{
    /** List of all the entities in the world. They key is the DIS entity ID,
     * the value the entity that corresponds to that entity ID. The value
     * is something that implements the PduReceiverProtocol.
     */
    NSMutableDictionary *entities;

}

-(id)init;
-(void)addEntity:(id <PduReceiverProtocol> )anEntity withEntityID:(EntityID*)entityID;
-(void)dispatchPdu:(Pdu*)aPdu;


@end
