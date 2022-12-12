
package edu.nps.moves.darkstar.server;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.sgs.app.AppContext;
import com.sun.sgs.app.AppListener;
import com.sun.sgs.app.Channel;
import com.sun.sgs.app.ChannelManager;
import com.sun.sgs.app.ClientSession;
import com.sun.sgs.app.ClientSessionListener;
import com.sun.sgs.app.DataManager;
import com.sun.sgs.app.Delivery;
import com.sun.sgs.app.ManagedReference;
import com.sun.sgs.app.NameNotBoundException;
import com.sun.sgs.app.TaskManager;
import com.sun.sgs.app.util.ScalableHashMap;


/**
 * Persistent Virtual World Listener. This is effectivley the main method
 * of the server; it is called by the darkstar server at startup, and when
 * a client connects.<p>
 *
 * @author mcgredo
 */
public class DarkstarServer implements AppListener, Serializable
{
    public static final long serialVersionUID = 1L;

    /** Frequency (in ms) at which we log all the entities in the system */
    public static final long ENTITY_LOG_FREQUENCY = 300000;
    
    /** How often for entities living on the server to send out heartbeat ESPDUs, in ms */
    public static final long HEARTBEAT_FREQUENCY = 10000;

    /**
     * How frequently the tick() method should be called on every entity, in ms. This is mostly
     * a test on concurrency; high frequency ticks can cause task scheduling problems and
     * data update conflicts, slowing down the system. WARNING: the value of the tick frequncy
     * has a big effect on CPU load as you add more entities to the server. On the other hand,
     * if we have a multi-node server the updates can be spread across many hosts, evening
     * out the CPU load by distributing it to multiple hosts. In other words, there are a lot
     * of emperical things to look at here.
     */
    public static final long TICK_FREQUENCY = 150;

    /** How many server-side entities we add to the data store */
    public static final int SERVER_SIDE_ENTITY_COUNT = 10;

    /** DIS channel */
    public static final String DIS_CHANNEL = "CHANNEL_DIS";
    
     /** The {@link Logger} for this class. */
    private static final Logger logger = Logger.getLogger(DarkstarServer.class.getName());

    /** A reference to a channel that is used exclusively for DIS messges in IEEE-1278.1 format */
    private ManagedReference<Channel> disChannelRef;

    /**
     * All the entities in the world
     */
    private  ManagedReference<ScalableHashMap> entitiesMapRef;

    /**
     * Initialize method, which is called once at server startup time. You
     * can use this do add initial objects to the system. Note that this
     * method will NOT be called the second time you start up the application,
     * assuming the object store is intact. It is called only when the object
     * store is empty.<p>
     *
     * The properties passed in are the union of:
     * Properties specified on the command line with the -D flag<br>
     * Properties file with the filename .sgs.properties in the user's home directory<br>
     * The properteis file specified by name with the SGS_DEPLOY property in the boot_config_file<br>
     * Properties file with the resource name META-INF/app.properties. This file is typically
     * included in the META-INF directory of one of the application jars copied into the deploy
     * folder of the PDS installation.<br>
     * 
     * @param props Server properties
     */
    public void initialize(Properties props)
    {
      logger.log(Level.INFO, "Started Persistent Virtual World");

      // Managaes persistent objects
      DataManager dataManager = AppContext.getDataManager();

      // Manages communications channels
      ChannelManager channelManager = AppContext.getChannelManager();

      // Periodic tasks to run
      TaskManager taskManager = AppContext.getTaskManager();


      try
      {
          // Retrieve an existing, known entity from the data manager. This code
          // should ever execute; initialize() is called only the very first time
          // a datastore is created, when the datastore will always be empty.
          Entity anEntity = (Entity)dataManager.getBinding("ENTITY(0,0,1)");
          logger.log(Level.INFO, "Found existing entity object");
      }
      catch(NameNotBoundException nnbe)
      {
          // We have a brand new object store that is completly empty. Add some
          // objects and tasks to it.
          logger.log(Level.INFO, "Creating initial server-side entities and tasks");

          // The hash map contains a key of a string in the format of
          // ENTITY(x,y,z), using the entityID of the DIS entity, which is
          // always unique to each entity. The value is the persistent entity
          // object.
          entitiesMapRef = dataManager.createReference(new ScalableHashMap());
          ScalableHashMap entitiesMap = entitiesMapRef.get();

          // Save the hash map of entities in the system under a named string
          dataManager.setBinding("ENTITIES", entitiesMap);

          // Create some new entities
          for(int idx = 0; idx < SERVER_SIDE_ENTITY_COUNT; idx++)
          {
              Entity anEntity = new Entity();
              anEntity.entityId.setSite(0);
              anEntity.entityId.setApplication(0);
              anEntity.entityId.setEntity(idx);
              anEntity.controlLocation = Entity.ControlLocation.SERVER;
              // Add other information, such as entity type. We really should be reading
              // info from a config file.

              // Put the entity into a list of entities. Should this be a reference being added?
              entitiesMap.put(anEntity.toString(), anEntity);

              // Instruct the scheduler to run  a heartbeat task for this entity, which
              // will send out an ESPDU at a given interval on the DIS channel.
              ManagedReference entityRef = dataManager.createReference(anEntity);
              HeartbeatTask heartbeatTask = new HeartbeatTask(entityRef);
              taskManager.schedulePeriodicTask(heartbeatTask, 10000, HEARTBEAT_FREQUENCY);

              // Tick frequency. Mostly a test of object contention. The tick method in an
              // entity gets called every TICK_FREQUENCY ms.
              // A task-per-entity is a somewhat suspect choice as you go to more and
              // more entities. It might be better to have one tick task that simply
              // cycles through the list of all entities. However, calling tick()
              // on all the entities may take a while, longer than the task scheduler
              // is willing to give. So it's a tradeoff.
              TickEntityTask tickEntityTask = new TickEntityTask(entityRef);
              taskManager.schedulePeriodicTask(tickEntityTask, 10000, TICK_FREQUENCY);
         }

          // Create a periodic task that tallies out all the entities in the entity list
          EntityStatusTask task = new EntityStatusTask();
          taskManager.schedulePeriodicTask(task, 10000, ENTITY_LOG_FREQUENCY);

          // Create a periodic task to prune entities from the server that have not
          // been heard from in some time.
          ReaperTask pruneTask = new ReaperTask();
          taskManager.schedulePeriodicTask(pruneTask, 10000, HEARTBEAT_FREQUENCY);

          // Channel dedicated for DIS messages. Passing in a null as the second
          // argument means that all messages sent to it will be heard only by
          // clients.
          Channel c1 = channelManager.createChannel(DIS_CHANNEL, new DisChannelServerListener(), Delivery.RELIABLE);
          disChannelRef = dataManager.createReference(c1);
      }

    }

    /**
     * Notification that a client has logged in. Returns a ClientSessionListener,
     * which provides us notifications when the client does something. THe Client
     * SessionListener can be used for general messages. This method also notifies
     * the client that it should join the DIS channel.
     * 
     * @param session
     * @return
     */
    public ClientSessionListener loggedIn(ClientSession session)
    {
         logger.log(Level.INFO, "User {0} almost logged in", session.getName());
         logger.log(Level.INFO, "Session name: " + session.getName());

         // This is a listener for generic messages from the client. 
         ApplicationListener receiver = new ApplicationListener(session);

         // Retrive the DIS channel object and tell the client that it should listen
         // to it.
         Channel disChannel = (Channel)disChannelRef.get();
         disChannel.join(session);
         logger.log(Level.INFO, "Instructed client " + session.getName() + "to join DIS channel on server side");

         return receiver;
    }

}
