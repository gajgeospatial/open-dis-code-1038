#import "NPSAnnotationView.h"
#import <UIKit/UIKit.h>

#import "MapAnnotation.h"
#import "EntityID.h"

@implementation NPSAnnotationView

@synthesize rotationInDegrees;

/**
 * Designated initializer
 */
- (id)initWithAnnotation:(id <MKAnnotation>)annotation reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithAnnotation:annotation reuseIdentifier:reuseIdentifier];
    if(self)
    {
        rotationInDegrees = 0.0;
        self.transform = CGAffineTransformIdentity;
        //CGAffineTransformMakeRotation([self degreesToRadians:rotationInDegrees]);
        
    }
    
    return self;
    
}

-(float)degreesToRadians:(float)degrees
{
    return degrees * (3.141592653589 / 180.0);
}

/**
 * This does not work yet--I think I need to set the frame to non-zero. By default the
 * frame size (view size) seems to be set to (0,0). There are probably plenty of other
 * bugs in the drawing code. The easiest way to handle annotation appearance is to
 * just set a .png image, and you can do that in the superclass.
 */
- (void)drawRect:(CGRect)rect 
{
    id model = [self annotation];
    
    if([model isKindOfClass:[MapAnnotation class]])
    {
        CGRect rect = [self frame];
        rect.origin.x  =0.0;
        rect.origin.y = 0.0;
        rect.size.width = 20.0;
        rect.size.height = 30.0;
        [self setFrame:rect];
        /*
        MapAnnotation *npsAnnotation = (MapAnnotation*)model;
        EntityID *eid = [npsAnnotation entityID];
        int site = [eid site];
        int application = [eid application];
        int entity = [eid entity];
         */
        
       // NSString *eidString = [[NSString alloc] initWithFormat:@"(%i, %i, %i)", site, application, entity];
        // grab the current view graphics context
        // the context is basically our invisible canvas that we draw into.
        CGContextRef context    = UIGraphicsGetCurrentContext();
        
        // Drawing lines with an RGB based color
        CGContextSetRGBStrokeColor(context, 0.3, 0.6, 1.0, 1.0);
        // Draw them with a 2.0 stroke width so they are a bit more visible.
        CGContextSetLineWidth(context, 2.0);
        
        // lets set the starting point of our line, at x position 10, and y position 30
        CGContextMoveToPoint(context, 10.0, 30.0);
        
        // Draw a connected sequence of line segments
        // here we are creating an Array of points that we will combine to form
        // a path
        CGPoint addLines[] =
        {
            CGPointMake(0.0, 150.0),
            CGPointMake(70.0, 60.0),
            CGPointMake(130.0, 90.0),
            CGPointMake(190.0, 60.0),
            CGPointMake(250.0, 90.0),
            CGPointMake(310.0, 60.0),
        };
        
        // now we can simply add the lines to the context
        CGContextAddLines(context, addLines, sizeof(addLines)/sizeof(addLines[0]));
        
        // and now draw the Path!
        CGContextStrokePath(context);        
    }
        
}

@end
