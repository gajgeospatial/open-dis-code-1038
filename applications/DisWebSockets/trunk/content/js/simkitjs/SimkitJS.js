var SimkitJS = {};

SimkitJS.SimEventParams = {
    nextID : 0,
    __init__ : function(source, eventName, scheduledTime, arguments, priority) {
        this.source = source;
        this.eventName = eventName;
        this.scheduledTime = scheduledTime;  
        this.arguments = arguments;
        this.priority = priority;
        this.id = this.nextID;
        this.nextID += 1;
        },
    toString : function() {
        var string = this.scheduledTime.toString() + ' ';
        string += this.eventName + ' ';
        string += '[' + this.arguments + '] ';
        string += '(' + this.priority + ') ';
        string += '<' + this.source.toString() + '>';
        return string;
        }
    };
SimkitJS.SimEventBase = Object.newBase(SimkitJS.SimEventParams);

SimkitJS.PriorityQueueParams = {
	__init__ : function() {
		this.data = [];
		},
	add : function( elem ) {
		var i = this.data.length;
        var time = elem.scheduledTime;
		var pri = elem.priority;
        while( i>0 && time > this.data[i-1].scheduledTime ) {
			-- i;
			}
		while( i>0 && time===this.data[i-1].scheduledTime && pri < this.data[i-1].priority ) {
			-- i;
			}
		this.data.splice(i,0,elem);
		},
    remove : function(eventName, args) {
        // removes first event with same name and matching arg list
        for (var i=this.data.length-1;i>=0;i-=1) {
            event = this.data[i];
            if (event.eventName != eventName) {
                continue;
                }
            var match = true;
            for (var j=0;j<args.length;j+=1) {
                var arg1 = args[j];
                var arg2 = event.arguments[j];
                if (arg1 != arg2) {
                    match = false;
                }   }        
            if (match) {
                this.data.splice(i,1);
                return(true);
            }   } 
        return(false);
        },
    pop : function() {
        return this.data.pop();
        },
    peek : function() {
        if (this.data)
            return this.data[ this.data.length-1 ];
        return null;
        },
    toString : function() {
        var string = '';
        for (var i=this.data.length-1;i>=0;i-=1) {
            var elem = this.data[i];
            string += elem.toString() + '\n';
            }
        return string;
        }
	};
SimkitJS.PriorityQueueBase = Object.newBase(SimkitJS.PriorityQueueParams);	

SimkitJS.EventList = {
	priorityQueue : SimkitJS.PriorityQueueBase.newObj(),
	simEntities : [],
	simTime : 0.0,
	stopTime : 0.0,
	verbose : false,
	running : false,
	stopAtTime : function(time) {
		this.stopTime = time;
		},
    init : function() {
    	priorityQueue = SimkitJS.PriorityQueueBase.newObj();
        simEntities = [];
        simTime = 0.0;
        stopTime = 0.0;
        verbose = false;
        running = false;
        },
	reset : function() {
		this.simTime = 0.0;
		this.priorityQueue.__init__();
		for (i=0;i<this.simEntities.length; i += 1) {
			se = this.simEntities[i]
			if ( se.doRun ) {
				se.waitDelay('Run',0.0,[],Number.POSITIVE_INFINITY);
                }
            }
        if (SimkitJS.EventList.stopTime > 0.0) {
            var stopper = SimkitJS.StopperBase.newObj();
            stopper.waitDelay( 'Stop', SimkitJS.EventList.stopTime,[],Number.NEGATIVE_INFINITY);
            }
        },
    startSimulation : function() {
        SimkitJS.EventList.running = true;
        if (this.verbose) {
            hprint('Starting Simulation...');
            hprint(this.toString());
            }
        while (SimkitJS.EventList.running && SimkitJS.EventList.priorityQueue.peek()) {
            var currentEvent = SimkitJS.EventList.priorityQueue.pop();
            // cancelled event check was here
            SimkitJS.EventList.simTime = currentEvent.scheduledTime;
            currentEvent.source.processSimEvent( currentEvent );
            if (this.verbose) {
                hprint('Time: ' + this.simTime + ' Current Event: ' + currentEvent.toString());
                hprint(this.toString());
                }
            }
        SimkitJS.EventList.running = false;
        },
    pause : function() {
        SimkitJS.EventList.running = false;
        },
    scheduleEvent : function(source, name, delay, args, priority) {
        args = typeof(args) != "undefined" ? args : [];
        priority = typeof(priority) != "undefined" ? priority : 0;
        newEvent = SimkitJS.SimEventBase.newObj(source, name, SimkitJS.EventList.simTime+delay, args, priority);
        this.priorityQueue.add( newEvent );
        },
    cancelEvent : function(eventName, args) {
        args = typeof(args) != "undefined" ? args : [];
        SimkitJS.EventList.priorityQueue.remove(eventName, args);
        },
    registerSimEntity : function(source) {
        SimkitJS.EventList.simEntities.push(source);
        }, 
    toString : function() {
        var string = '*** Event List ***\n';
        string += this.priorityQueue.toString();
        return string;
        },
    setVerbose : function(value) {
        this.verbose = value;
        }
	};

SimkitJS.SimEntityParams = {
	nextID : 0,
	__init__ : function(name) {
        name = typeof(name)!='undefined' ? name : 'SimEntity';
		this.eventListeners = [];
		this.name = name;
		SimkitJS.EventList.simEntities.push( this );
        this.id = SimkitJS.SimEntityBase.nextID;
		SimkitJS.SimEntityBase.nextID += 1;
        return this;
		},
	toString : function() {
		return this.name + '.' + this.id;
		},
	processSimEvent : function(simEvent) {
		var methodName = 'do' + simEvent.eventName;
		if  (this[methodName]) {
			this[methodName].apply(this,simEvent.arguments);
            } 
		if (simEvent.source === this) {
			this.notifyEventListeners( simEvent );
			}
		},
	waitDelay : function(eventName, delay, args, priority) {
            args = typeof(args) != "undefined" ? args : [];
            priority = typeof(priority) != 'undefined' ? priority : 0;
			SimkitJS.EventList.scheduleEvent(this, eventName, delay, args, priority);
		},
	addSimEventListener : function(listener) {
		if (listener == this) {
			return;
            }
		if (!this.eventListeners.contains(listener)) {
            this.eventListeners.push( listener );
            }
        },
    removeSimEventListener : function(listener) {
        this.eventListeners.remove(listener);
        },
    notifyEventListeners : function(event) {
        if (event.eventName == 'Run' ) {
            return;
            }
        for (var i=0;i<this.eventListeners.length;i+=1) {
            listener = this.eventListeners[i];
            listener.processSimEvent(event);
            }
        },
    interrupt : function(eventName, args) {
        SimkitJS.EventList.cancelEvent(eventName, args);
        }	
	};
SimkitJS.SimEntityBase = Object.newBase(SimkitJS.SimEntityParams);

SimkitJS.StopperParams = {
    __init__ : function() {
        SimkitJS.SimEntityBase.__init__.call(this,'Stopper');
        },
    doStop : function() {
        SimkitJS.EventList.priorityQueue.__init__();
        }
    };
SimkitJS.StopperBase =  SimkitJS.SimEntityBase.newBase(SimkitJS.StopperParams);   

SimkitJS.main = function () {
    var TestSimEntityParams = {
        __init__ : function() {
            SimkitJS.SimEntityBase.__init__.call(this);
            },
        doA : function() {
            this.waitDelay('B', 1.5);
            this.waitDelay('C', 5.1, [42, 7]);
            },
        doB : function() {
            hprint('in testSimEntity.doB');
            }
        };
    var TestSimEntityBase = SimkitJS.SimEntityBase.newBase(TestSimEntityParams);
    var testSimEntity = TestSimEntityBase.newObj();
    var TestListenerParams = {
        __init__ : function() {
            SimkitJS.SimEntityBase.__init__.call(this);
            },
        doA : function() {
            hprint('listener heard A');
            }
        };
    var TestListenerBase = SimkitJS.SimEntityBase.newBase(TestListenerParams);
    var testListener = TestListenerBase.newObj();
    testSimEntity.addSimEventListener( testListener );
    SimkitJS.EventList.reset();
    SimkitJS.EventList.scheduleEvent( testSimEntity, 'A', 2.5 );
    SimkitJS.EventList.setVerbose(true);
    SimkitJS.EventList.startSimulation();
    };