
function idOverlay()
{
	var layoutcanvas = document.createElement('canvas');
	var size = 256; 
	layoutcanvas.width = size;
	layoutcanvas.height = size;
	
	this.context = layoutcanvas.getContext('2d');
	this.context.fillStyle = '#000000'; 
	this.context.textAlign = 'center';
	this.context.font = '24px Arial';
	//this.context.fillText("1234567890",size / 2, size / 2 - 12);
	
	
	var amap = new THREE.Texture(layoutcanvas);
	amap.needsUpdate = true;

	var mat = new THREE.SpriteMaterial({
		map: amap,
		transparent: false,
		useScreenCoordinates: false,
		color: 0xffffff 
	});

	this.sp = new THREE.Sprite(mat);
	this.sp.scale.set( 30, 30, 1 ); 
	this.sp.position.set(0,3,-3);
	
}

function infoOverlay()
{
	this.text = document.createElement('div');
	this.text.style.position = 'absolute';
	//text.style.zIndex = 1;    // if you still don't see the label, try uncommenting this
	this.text.style.width = 350;
	this.text.style.height = 100;
	this.text.style.color = "red";
	//this.text.style.backgroundColor = "blue";
	//this.text.innerHTML = "hi there!";
	this.text.style.font = '16px Arial';
	this.text.style.top = 6 + 'px';
	//this.text.style.left = 8 + 'px';
	document.body.appendChild(this.text);
}