/**
 * Represents the "model" of some annotation on the map. An MapAnnotation conforms
 * to the MKAnnotation protocol, and must be associated with a MKAnnoationView
 * to be displayed. 
 *
 * @author DMcG
 */

#import <UIKit/UIKit.h>
#import <MapKit/MapKit.h>
#import <CoreLocation/CoreLocation.h>

#import "PduReceiverProtocol.h"
# import "EntityID.h"

/**
 * Extends MKAnnotation to include DIS information. This represents an annotation
 * on a map. The annotation is generated from DIS traffic, so that when a
 * DIS ESPDU  that describes an entity arrives, it is added to the map
 * if it is not already present.
 */

@interface MapAnnotation : NSObject <MKAnnotation>
{
    NSString    *title;
    NSString    *subTitle;
    CLLocationCoordinate2D coordinate;
    CLLocation  *location;
    
    /** The entity ID for the DIS Entity this annotation represents */
    EntityID    *entityID;
    float       heading;
    
    /** True if the position has changed, and therefore its view needs to be redrawn on the MapView */
    BOOL        positionChanged;

}

@property (nonatomic, readonly) CLLocationCoordinate2D coordinate;
@property (nonatomic, retain)   CLLocation* location;
@property (nonatomic) float     heading;
@property (nonatomic, retain)   EntityID* entityID;
@property (nonatomic) BOOL      positionChanged;

-(id)initWithTitle:(NSString*)title andSubtitle:(NSString*)subTitle atLocation:(CLLocationCoordinate2D*)pLocation;

-(NSString*)title;
-(NSString*)subTitle;

-(NSString*)identifier;
-(void)moveToLat:(float)aLat andLon:(float)aLon;


@end
