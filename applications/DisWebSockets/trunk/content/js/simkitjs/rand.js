var Rand = {};

Rand.generator = new MersenneTwister19937();
Rand.seed = function(sd) { Rand.generator.init_genrand(sd); };
Rand.random = function() { return Rand.generator.genrand_real2(); };

Rand.ConstantVariateParams = {
    __init__ : function(value) {
        this.value = value;
        },
    generate : function() {
        return this.value;
        },
    toString : function() {
        return "Constant (" + this.value.toString() + ")";
        }
    };
Rand.ConstantVariateBase = Object.newBase(Rand.ConstantVariateParams);

Rand.ExponentialVariateParams = {
    __init__ : function(mean) {	
        this.mean = mean;
        },
    generate : function() {
        return - this.mean * Math.log( Rand.random() );
        },
    toString : function() {
        return "Exponential (" + this.mean.toString() + ")";
        }
    };
Rand.ExponentialVariateBase = Object.newBase(Rand.ExponentialVariateParams);

Rand.UniformVariateParams = {
    __init__ : function(lower,upper) {
        this.lower = lower;
        this.upper = upper;
        },
    generate : function() {
        return this.lower + Rand.random() * ( this.upper - this.lower );
        },
    toString : function() {
        return "Uniform (" + this.lower + ", " + this.upper + ")";
        }
    };
Rand.UniformVariateBase = Object.newBase(Rand.UniformVariateParams);

Rand.TriangleVariateParams = {
    __init__ : function(min,max,mode) {
        this.min = min;
        this.max = max;
        this.mode = mode;
        },
    generate : function() {
        if (Rand.random() < (this.mode-this.min) / (this.max-this.min) ) {
            return this.min + (this.mode-this.min) * Math.sqrt( (this.mode-this.min) * (this.max-this.min) * Rand.random() );
            } 
        else {
            return this.max + (this.max-this.mode) * Math.sqrt( (this.max-this.mode) * (this.max-this.min) * Rand.random() );
            }
        },
    toString : function() {
        return "Triangle (" + this.min + ", " + this.max + ", " + this.mode + ")";
        }
    };
Rand.TriangleVariateBase = Object.newBase(Rand.TriangleVariateParams);


Rand.main = function() {
    var rv = Rand.ExponentialVariateBase.newObj(3.1);
    print( rv.generate().toString() );
    print( rv.toString() );
    
    var rv2 = Rand.TriangleVariateBase.newObj(-1.2, 4.2, 0.5);
    print( rv2.toString() );
    print( rv2.generate().toString() );
    };