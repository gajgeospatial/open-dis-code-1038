/** A view is the visual representation of the model. We may have several ways
 * to represent a view--via a Collada model, for example, or via a native Three
 * model. 
 * 
 */

// Namespace
if(nve === undefined)
{
    var nve = {};
}

nve.colladaLoader = new THREE.ColladaLoader();

// A database of all the entities in the world. They key is the entityID string--normalized via JSON
nve.entityTypeToGraphicDatabase = {};

// LAV/stryker model
nve.entityTypeToGraphicDatabase['{"entityKind":1,"domain":1,"country":225,"category":2,"subcategory":5,"spec":0,"extra":0}'] = '{"type": "collada", "modelPath":"colladaModels/models/stryker.dae", "scale":0.1}';
// M270 Multiple Launch Rocket System (MLRS)
nve.entityTypeToGraphicDatabase['{"entityKind":1,"domain":1,"country":225,"category":4,"subcategory":1,"spec":0,"extra":0}'] = '{"type": "collada","modelPath":"colladaModels/models/mlrs.dae", "scale": 0.1}';
nve.entityTypeToGraphicDatabase['default'] = '{"type": "three", "modelPath":"none","scale":0.01}'; 

/**
 * Constructor. Takes an object that is the RH side of the entityTypeToGraphicDatabase,
 * and object that contains properties type, modelPath, and scale.
 */
nve.NveView = function(graphicInformation)
{
    
 this.graphicsObject = {};
 this.graphicsInformation = eval("(" + graphicInformation + ")" );
 //console.log("graphicsInformation: " + graphicInformation);
 this.load();
 
 //alert("Creating nve");
}
 
// Create the graphic representation and add it to the scene.
nve.NveView.prototype.load = function()
{
    //console.log("Loading graphics info" + JSON.stringify(this.graphicsInformation));
  
 // The type of view is a collada model  
 if(this.graphicsInformation.type == "collada")
 {
     nve.colladaLoader.load( 'colladaModels/models/stryker.dae', function(collada)
     {
         var dae = collada.scene;
         dae.scale.x = dae.scale.y = dae.scale.z = graphicInforation.scale;
         graphicsObject = collada.scene;

         scene.add(collada.scene);
         
         animate();
     });   
 }
 
 // Three type view, in this case a simple cube.
 else if(this.graphicsInformation.type == "three")
 {
     //console.log("loading grahpics for three");
     this.cubeMaterialArray = [];
    // order to add materials: x+,x-,y+,y-,z+,z-
    this.cubeMaterialArray.push( new THREE.MeshBasicMaterial( { color: 0xff3333 } ) );
    this.cubeMaterialArray.push( new THREE.MeshBasicMaterial( { color: 0xff8800 } ) );
    this.cubeMaterialArray.push( new THREE.MeshBasicMaterial( { color: 0xffff33 } ) );
    this.cubeMaterialArray.push( new THREE.MeshBasicMaterial( { color: 0x33ff33 } ) );
    this.cubeMaterialArray.push( new THREE.MeshBasicMaterial( { color: 0x3333ff } ) );
    this.cubeMaterialArray.push( new THREE.MeshBasicMaterial( { color: 0x8833ff } ) );
    this.geometry = new THREE.CubeGeometry(20, 20, 20,1, 1, 1, this.cubeMaterialArray);
    //this.material = new THREE.MeshBasicMaterial({color:0xff0000, wireframe:true});
    this.graphicsObject = new THREE.Mesh(this.geometry, new THREE.MeshFaceMaterial() );
    scene.add(this.graphicsObject);

 }
 else
 {
   console.log("******cannot find graphics type for " + this.grahicsInformation.type);
 }
}

/** Modify the graphic object position */
nve.NveView.prototype.setPosition = function(x, y, z)
{
    this.graphicsObject.position.set(x, y, z);
}

/** Modify the graphic object orientation */
nve.NveView.prototype.setOrientation = function()
{
    
}

