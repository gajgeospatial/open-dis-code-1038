/*
if (typeof Object.spawn !== 'function') {
	Object.spawn = function(params,inits) {
        var F = function() {};
        F.prototype = this;
        var result = new F();
        if (typeof(params)!=='undefined') {
            for (var key in params)
                result[key] = params[key];
            }
        if (result.__init__ && typeof(inits)!=='undefined') {
            result.__init__.apply(result,inits);
        return result;
    };
}
*/
if (typeof Object.newBase !== 'function') {
	Object.newBase = function(params) {
        var F = function() {};
        F.prototype = this;
        var result = new F();
        if (typeof(params)!=='undefined') {
            for (var key in params)
                result[key] = params[key];
            }
        return result;
    };
}

if (typeof Object.newObj !== 'function') {
	Object.newObj = function() {
        var F = function() {};
        F.prototype = this;
        var result = new F();
        if (result.__init__)
            result.__init__.apply(result,arguments);
        return result;
    };
}

Array.prototype.contains = function (element) 
{
	for (var i = 0; i < this.length; i++) {
		if (this[i] == element) {
			return true;
			}
	}
	return false;
};

Array.prototype.remove = function (element) 
{
	for (var i = 0; i < this.length; i++) {
		if (this[i] == element) {
			this.splice(i,1);
			}
	}
};