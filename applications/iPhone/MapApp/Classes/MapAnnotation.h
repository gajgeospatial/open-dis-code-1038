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


@interface MapAnnotation : NSObject <MKAnnotation>
{
    NSString *title;
    NSString *subTitle;
    CLLocationCoordinate2D coordinate;
    CLLocation *location;
    float heading;
    EntityID *entityID;

}

@property (nonatomic, readonly) CLLocationCoordinate2D coordinate;
@property (nonatomic, retain)  CLLocation* location;
@property (nonatomic) float heading;
@property (nonatomic, retain) EntityID* entityID;

-(id)initWithTitle:(NSString*)title andSubtitle:(NSString*)subTitle atLocation:(CLLocationCoordinate2D*)pLocation;

-(NSString*)title;
-(NSString*)subTitle;

-(NSString*)identifier;
-(void)moveToLat:(float)aLat andLon:(float)aLon;


@end
