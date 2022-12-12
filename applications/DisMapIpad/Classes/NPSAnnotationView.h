/**
 * Arguably this class isn't needed at all. We could just set an image in the superclass and
 * then do a rotation on the image. But this gives us the ability to do some quartz drawing if
 * we were so inclined, such as drawing an infantry unit symbol with the unit name included,
 * based on input from the network.
 *
 * @author DMcG
 */

#import <UIKit/UIKit.h>
#import <MapKit/MapKit.h>

@interface NPSAnnotationView : MKAnnotationView 
{
    float rotationInDegrees;
}
@property float rotationInDegrees;


- (void)drawRect:(CGRect)rect;

- (id)initWithAnnotation:(id <MKAnnotation>)annotation reuseIdentifier:(NSString *)reuseIdentifier;

-(float)degreesToRadians:(float)degrees;

@end
