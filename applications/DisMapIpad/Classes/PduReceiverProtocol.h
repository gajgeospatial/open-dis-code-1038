#import "Pdu.h"
#import "EntityID.h"

/**
 * Protocol defines a way to send PDUs to objects that represent entities
 * in the world. The objects must be identified by an EntityID, a triplet
 * of site, application, and entity IDs. The objects can receive a PDU,
 * of any subclass, and must be able to identify themselves by returning
 * an entity ID that uniquely identifies them.
 */

@protocol PduReceiverProtocol  

/** Accepts a PDU sent by the dispatcher */
-(void)receivePdu:(Pdu*) from:(id)sender; 

@end
