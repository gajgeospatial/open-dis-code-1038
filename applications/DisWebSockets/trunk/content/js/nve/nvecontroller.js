/**
 * This is an apple-style implementation of MVC, which differs in some
 * ways from the way other people define the pattern. See 
 * https://developer.apple.com/library/mac/#documentation/General/Conceptual/DevPedia-CocoaCore/MVC.html
 * for details. Briefly, models contain basic information and behaviors
 * such as position, views show the model contents to the user, and 
 * controllers tie the view to the model, so the two do not directly
 * refer to each other. The overall objective is to keep the model portion
 * from being polluted with graphics package-specific information, and allow
 * us to repurpose the model portion in, say, X3D, GLGE, or some other
 * scene graph.
 */

// Namespace
if(nve === undefined)
{
    var nve = {};
};

/** lookup table of all the MVC controllers, keyed by entity ID */
nve.controllerDatabase = {};

/**
 * At periodic intervals this function is called to remove entities that have
 * not been heard from in a while. Removes only remote entities, not locally
 * controlled entities.
 */
nve.heartbeatReaper = function()
{
    // Loop through all the entries in the controller database
    for(var propName in nve.controllerDatabase)
    {
       var aController = nve.controllerDatabase[propName];
       var anEntity = aController.model;
       var now = new Date();
       var timeDifferential = now.getTime() - anEntity.timeLastHeardFrom.getTime();
       if((timeDifferential > anEntity.timeoutPeriod) && (anEntity.local == false))
       {
           // Removes view, model, and controller
            aController.remove(); 
       }
    }
}

/**
 * At periodic intervals we send out an entity state PDU; this tells other
 * participants that we are alive, and allows them to discover our presence.
 * The interval at which this is called is set in the main scene.
 */
nve.heartbeat = function()
{
    for(var propName in nve.controllerDatabase)
    {
       var aController = nve.controllerDatabase[propName];
       var anEntity = aController.model;
       
       if(anEntity.local == true)
       {
           webSocket.send(JSON.stringify(anEntity.espdu));
       }
    }
       
       
}
    

/**
 * Constructor. 
 */
nve.Controller = function( model, view )
{
    this.model = model;
    this.view = view;
    this.model.controller = this;
    nve.controllerDatabase[model.entityId] = this;
}

/** Model has changed; notify the view */
nve.Controller.prototype.modelChanged = function()
{

    this.view.setPosition(this.model.espdu.entityLocation.x,
                     this.model.espdu.entityLocation.y,
                     this.model.espdu.entityLocation.z);
                     
    // Should also change orientation, etc
}

/** Remove this controller, entity, and view from the application */
nve.Controller.prototype.remove = function()
{
    scene.remove(this.view.graphicsObject);
    var stringEid = this.model.entityId;
    cDelete = delete nve.controllerDatabase[stringEid];
    eDelete = delete nve.entityDatabase[stringEid];
    
}

/** Debugging */
nve.printControllerDatabase = function()
{
    console.log("---Controller database:");
   for(var propName in nve.controllerDatabase)
    {
       console.log(propName);
    } 
    console.log("----");
}