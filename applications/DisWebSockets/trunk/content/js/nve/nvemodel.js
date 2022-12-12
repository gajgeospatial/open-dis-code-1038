/** The model contains underlying data that is displayed by the view.
 * The objective is to keep this free of graphics data, and thus make it
 * reusable. 
 * 
 * @author DMcG
 */

//Namespace
if(nve === undefined)
{
    var nve = {};
}

/**
 * Contains a list of all the enity models in the world. Keyed by entity ID
 */
nve.entityDatabase = new Object();

/** Constructor. Takes a string that is the entityID object (normalized via JSON),
 * the entity type (EBV document enumeration), and whether this is a local entity--
 * one controlled from this web page--or a remote entity--one controlled from another
 * web page. Remote enitites are subject to being removed in they are not heard from
 * in a certain amount of time.
 */
nve.Entity = function(entityId, entityType, isLocal)
{
    nve.entityDatabase[entityId] = this;
    // Whether this is a local entity (created within this web page = true)
    // or one created to represent something from off this web page
    this.local = isLocal
    // The last time we heard from the entity
    this.timeLastHeardFrom = new Date();
    
    // Default scale size. The scale size should be set in the entityTypeToGraphicDatabase
    this.scale = 0.01;
    
    // How long we wait before we decide this entity is dead and remove it from the scene
    this.timeoutPeriod = 120000; // two min
    
    // The 3D model in the scene we're pushing around
    //this.collada = new GLGE.Collada();
    
    // Unique identifier for the entity--DIS convention of site, application, entityID
    this.entityId = entityId;
    
    // The EBV document/DIS entity type
    this.entityType = entityType;
    
    // The last entity state PDU received. Useful for dead reckoning. initially set to
    // a default value. Copy the prototype object and reset it to have the specified
    // entity type and entity ID.
    var stringEspdu = JSON.stringify(nve.espduPrototype);
    this.espdu = eval("(" + stringEspdu + ")");
    //console.log(stringEspdu);
    this.espdu.entityID = this.entityID;
    var et = eval("(" + this.entityType + ")");
    var eid = eval("(" + this.entityId + ")");
    this.espdu.entityType = et;
    this.espdu.entityID = eid;
  
  // The controller is set separately. 
    this.controller = undefined;
       
}

 
/** Update the entity position with the newest espdu */
nve.Entity.prototype.updateEntity = function(espdu)
{
    // Save this for future use in dead reckoning
    this.espdu = espdu;
    this.timeLastHeardFrom = new Date();
    this.controller.modelChanged();

    //this.mesh.position.set(espdu.entityLocation.x, espdu.entityLocation.y, espdu.entityLocation.z);

}

/**
 * Debugging
 */
nve.printEntityDatabase = function()
{
    console.log("---Entity database:");
   for(var propName in nve.entityDatabase)
    {
       console.log(propName);
    } 
    console.log("----");
}