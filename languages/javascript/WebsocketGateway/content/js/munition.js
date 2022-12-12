//Missile function
var speed = 100.0;
function Munition(scene)
{
	
	this.missileVector = new THREE.Vector3();
	this.missileGoal = new THREE.Vector3();
	this.missileShoot = false;
	this.missileReadyForShooting = true;
	this.missileEffect = false;
	this.timeDoneShoot = null;
	var that = this;
	//that.mesh = new THREE.Mesh(new THREE.SphereGeometry( 10, 30, 30 ),new THREE.MeshLambertMaterial( { color: 0x000000 } ));
	that.mesh = new THREE.Mesh(new THREE.SphereGeometry( 0.3, 30, 30 ),new THREE.MeshLambertMaterial( { color: 0x000000 } ));
	//that.mesh.position.set(3,100,3);
	//var that = this;
	that.mesh.position.set(3,-50,3);
	that.mesh.visible = true;
	scene.add(that.mesh);
	
	//update
	this.update = function(dt, now)
	{
		if(this.missileShoot && this.missileReadyForShooting)
		{
			this.timeDoneShoot = now + 2.0;
			this.mesh.visible = true;
			this.missileReadyForShooting = false;
			this.missileEffect = true;
			this.missileShoot = false;
		}
		if(now > this.timeDoneShoot)
		{
			this.missileReadyForShooting = true;
	
		}
		//judge the distance between wall and missile	
		var WBDistance	= new THREE.Vector3();
		if(this.missileGoal!== null)
		{
			WBDistance.subVectors(this.missileGoal,this.mesh.position);

			if(WBDistance.length()>3.0)
			{
				this.mesh.position.x = this.mesh.position.x + this.missileVector.x *dt*speed;
				this.mesh.position.y = this.mesh.position.y + this.missileVector.y *dt*speed;
				this.mesh.position.z = this.mesh.position.z + this.missileVector.z *dt*speed;
			}
			else
			{
				this.missileVector.x = 0.0;
				this.missileVector.y = 0.0;
				this.missileVector.z = 0.0;
				//this.mesh.position = this.missileGoal;
				this.mesh.position.copy(this.missileGoal);
				this.mesh.visible = false;
				this.missileEffect = false;
				
			}
		}
	}
}

//Peer Munition function
function PeerMunition(scene)
{
	this.missileVector = new THREE.Vector3();
	this.missileGoal = new THREE.Vector3();
	this.missileShoot = false;
	this.missileReadyForShooting = true;
	this.missileEffect = false;
	this.timeDoneShoot = null;
	var that = this;
	//that.mesh = new THREE.Mesh(new THREE.SphereGeometry( 10, 30, 30 ),new THREE.MeshLambertMaterial( { color: 0x000000 } ));
	that.mesh = new THREE.Mesh(new THREE.SphereGeometry( 0.3, 30, 30 ),new THREE.MeshLambertMaterial( { color: 0x000000 } ));
	//that.mesh.position.set(3,100,3);
	
	that.mesh.position.set(3,-50,3);
	that.mesh.visible = true;
	scene.add(that.mesh);
	
	//update
	this.peerUpdate = function(dt, now)
	{
		if(this.missileVector.x !== 0 ||this.missileVector.y !== 0 ||this.missileVector.z !== 0)
		{
		
			this.mesh.position.x = this.mesh.position.x + this.missileVector.x *dt*speed;
			this.mesh.position.y = this.mesh.position.y + this.missileVector.y *dt*speed;
			this.mesh.position.z = this.mesh.position.z + this.missileVector.z *dt*speed;
			this.mesh.visible = true;
			this.missileEffect = true;
		}
		else
		{
			this.mesh.position.x = this.mesh.position.x;
			this.mesh.position.y = this.mesh.position.y;
			this.mesh.position.z = this.mesh.position.z;
			this.missileEffect = false;
			this.mesh.visible = false;				
		}
	}
	
}
