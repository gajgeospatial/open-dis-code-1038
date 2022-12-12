var xUnit = new THREE.Vector3(1,0,0);
var yUnit = new THREE.Vector3(0,1,0);
var zUnit = new THREE.Vector3(0,0,1);
var yUnitNeg = new THREE.Vector3(0,-1,0);
var xUnitPos = new THREE.Vector3(1,0,0);
var xUnitNeg = new THREE.Vector3(-1,0,0);
var zUnitPos = new THREE.Vector3(0,0,1);
var zUnitNeg = new THREE.Vector3(0,0,-1);
function Tank(loader) 
{
  var locationX = 100.0 - Math.round(Math.random() * 200);
  var locationY = 30.0
  var locationZ =100.0 - Math.round(Math.random() * 200);  
  this.killed = false;
  this.translationSpeed = 0.0;
  this.rotationSpeed = 0.0;
  this.traverseSpeed = 0.0;
  this.elevateSpeed = 0.0;
  this.fireGun = false;
  this.flashOn = false;
  this.timeDoneFiring = null;
  var that = this;
  
  var mapA = THREE.ImageUtils.loadTexture( "images/smoke.png" );
  var cloud = new THREE.Object3D();
  //scene.add(cloud);
  that.psys = new SpriteParticleSystem({
    particlesMoveWithEmitter:false,
    cloud:cloud,
    rate:100,
    num:200,
    texture:mapA,
    scaleR:[.02,.03],
    speedR:[0,0.01],
    rspeedR:[-0.1,0.3],
    lifespanR:[.2,.3],
    terminalSpeed:20
  });
  that.psys.position.set(0,0.516,-3.13);
  that.psys.start();
  
  var onGeometry = function(geom, mats) {
    that.mesh = new THREE.Mesh( geom, new THREE.MeshFaceMaterial( mats ) );
    that.mesh.quaternions = true;
    //scene.add(that.mesh);
    that.mesh.add(that.psys);
	that.mesh.position.x = locationX;
	that.mesh.position.y = locationY;
	that.mesh.position.z = locationZ;
	
    loader.load("models/hovertank/turret.js", onGeometryTurret);
  };
 
  var onGeometryTurret = function(geom, mats) {
    that.turretMesh = new THREE.Mesh( geom, new THREE.MeshFaceMaterial( mats ) );
    that.turretMesh.quaternions = true;
    that.mesh.add(that.turretMesh);
    loader.load("models/hovertank/barrel.js", onGeometryBarrel);
  };
  var onGeometryBarrel = function(geom, mats) {
    that.barrelMesh = new THREE.Mesh( geom, new THREE.MeshFaceMaterial( mats ) );
    that.barrelMesh.quaternions = true;
    that.turretMesh.add(that.barrelMesh); 
    loader.load("models/hovertank/muzzleflash.js", onGeometryMuzzleflash);
  };
  var onGeometryMuzzleflash = function(geom, mats) {
    for (var i=0;i<mats.length;i++)
      mats[i].side = THREE.DoubleSide;
    var muzmat =  new THREE.MeshFaceMaterial( mats );
    that.muzzleflashMesh = new THREE.Mesh( geom, muzmat );
    that.muzzleflashMesh.quaternions = true;
    that.barrelMesh.add(that.muzzleflashMesh);
    that.muzzleflashMesh.visible = false;
  };
  loader.load("models/hovertank/hovertank10.js", onGeometry);


  
  this.quat = new THREE.Quaternion(); // scratch

  this.update = function(dt,now) {
    //this.psys.psUpdate();
    
    var dTheta = dt * this.rotationSpeed;
    this.quat.setFromAxisAngle(yUnit,dTheta);
    this.mesh.quaternion.multiply(this.quat);
    var dZ = dt * this.translationSpeed;
    this.mesh.translateZ(dZ);
	var dis = 3.0;
    var rayNegY = new THREE.Raycaster( this.mesh.position, yUnitNeg );
    var collisionsPosY = rayNegY.intersectObjects( colliders );
    //console.log("colliders",colliders,"collisions",collisions);
    if (collisionsPosY.length>0) {
      this.mesh.position.setY( collisionsPosY[0].point.y +1);
    }	
	
    var rayPosX = new THREE.Raycaster( this.mesh.position, xUnitPos );
    var collisionsPosX = rayPosX.intersectObjects( colliders );
    if (collisionsPosX.length>0) {
		if(collisionsPosX[0].distance <dis){		
			this.mesh.position.setX( collisionsPosX[0].point.x - dis );
		}
    }

 	var rayNegX = new THREE.Raycaster( this.mesh.position, xUnitNeg );
	var collisionsNegX = rayNegX.intersectObjects( colliders );
	if (collisionsNegX.length>0) {
		if(collisionsNegX[0].distance < dis){
			this.mesh.position.setX( collisionsNegX[0].point.x + dis );
		}
	}	
	
    var rayPosZ = new THREE.Raycaster( this.mesh.position, zUnitPos );
    var collisionsPosZ = rayPosZ.intersectObjects( colliders );
    if (collisionsPosZ.length>0) {
		if(collisionsPosZ[0].distance < dis){
			this.mesh.position.setZ( collisionsPosZ[0].point.z - dis );
		}
    }
    
	var rayNegZ = new THREE.Raycaster( this.mesh.position, zUnitNeg );
	var collisionsNegZ = rayNegZ.intersectObjects( colliders );
    if (collisionsNegZ.length>0) {
		if(collisionsNegZ[0].distance < dis){
			this.mesh.position.setZ( collisionsNegZ[0].point.z + dis );
		}
    }
			
    dTheta = dt * this.traverseSpeed;
    this.quat.setFromAxisAngle(yUnit,dTheta);
    this.turretMesh.quaternion.multiply(this.quat);
    
    dTheta = dt * this.elevateSpeed;
    this.quat.setFromAxisAngle(xUnit,dTheta);
	this.barrelMesh.quaternion.multiply(this.quat);
	if(this.barrelMesh.rotation.x > 0.06) this.barrelMesh.rotation.x = 0.06;
	if(this.barrelMesh.rotation.x < -0.2) this.barrelMesh.rotation.x = -0.2;
    
	if (this.fireGun) {
      this.timeDoneFiring = now + 0.2;
      this.muzzleflashMesh.visible = true;
      this.flashOn = true;
      this.fireGun = false;
    }
    
    if (this.flashOn && now > this.timeDoneFiring) {
      this.muzzleflashMesh.visible = false;
      this.flashON = false;
    }
    
  }
}