// Namespace
if(nvemodel === undefined)
{
    var nvemodel = {};
}

// Database of known entity types, and the collada models that correspond to the types
// If the entity type is unknown it can be replaced with a default model, a cube in this case.
// The "key" is the JSON of the entity type object, the value the path to the collada model

nvemodel.entityTypeToModelDatabase = new Object();

nvemodel.colladaLoader = new THREE.ColladaLoader();

// LAV/stryker model
nvemodel.entityTypeToModelDatabase['{"entityKind":1,"domain":1,"country":225,"category":2,"subcategory":5,"spec":0,"extra":0}'] = '{"type": "collada", "modelPath":"colladaModels/models/stryker.dae", "scale":0.01}';
// M270 Multiple Launch Rocket System (MLRS)
nvemodel.entityTypeToModelDatabase['{"entityKind":1,"domain":1,"country":225,"category":4,"subcategory":1,"spec":0,"extra":0}'] = '{"type": "collada","modelPath":"colladaModels/models/mlrs.dae", "scale": 0.01}';
nvemodel.entityTypeToModelDatabase['defaultModel'] = '{"type": "collada", "modelPath":"cube/models/Cube.dae","scale":0.01}'; 

nvemodel.entityDatabase = new Object();

nvemodel.Entity = function(entityId, entityType)
{
    // The last time we heard from the entity
    this.timeLastHeardFrom = new Date();
    
    // Default scale size. The scale size should be set in the entityTypeToModelDatabase
    this.scale = 0.01;
    
    // How long we wait before we decide this entity is dead and remove it from the scene
    this.timeoutPeriod = 120000; // two min
    
    // The 3D model in the scene we're pushing around
    //this.collada = new GLGE.Collada();
    
    // Unique identifier for the entity--DIS convention of site, application, entityID
    this.entityId = entityId;
    
    // The EBV document/DIS entity type
    this.entityType = entityType;
    
    // The last entity state PDU received. Useful for dead reckoning.
    this.lastEspduReceived = undefined;
    
    // Look up the geoemetry/model for that entity type. If not found, use
    // a cube as a placeholder.
    
    this.model = nvemodel.entityTypeToModelDatabase[this.entityType];
    
    if(this.model === undefined)
    {
        this.model = entityTypeToModelDatabase["default"]; 
        
        //this.collada.setScale(0.01);
    }
    
    var modelData = eval('(' + this.model + ')');
    
    //alert(modelData.type);
    
    if(modelData.type == "collada")
    {
        //alert("made it past test");
        
        
        /*
        nvemodel.colladaLoader.load( modelData.modelPath, function colladaReady( collada )
        {
            //alert("loading collada model");

            // Grab the collada scene data:
            dae = collada.scene;

            // No skin applied to my model so no need for the following:
            // var skin = collada.skins[ 0 ];

            // Scale-up the model so that we can see it:
            //dae.scale.x = dae.scale.y = dae.scale.z = 25.0;

            // Initialise and then animate the 3D scene
            // since we have now successfully loaded the model:
            init();
            animate();
        });
        */
        
    }
    
    /**
     * Returns true if the heartbeat period has expired, otherwise false.
     */
    this.expiredTime =  function()
    {
        var now = new Date();
        
        if(now.getTime() - lastTimeHeardFrom.getTime() > timeoutPeriod)
        {
            return true;    
        }
        
        return false;
    }   
    
    this.addToScene = function()
    {
       
    }
    
    this.removeFromScene = function()
    {
        scene.removeChild(this.collada);
    }
    
    this.updateEntity = function(espdu)
    {
        // Save this for future use in dead reckoning
        this.lastEspduReceived = espdu;
       
    }
} // End of entity class