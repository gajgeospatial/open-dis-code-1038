package edu.nps.moves.darkstar.server;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import com.sun.sgs.app.AppContext;
import com.sun.sgs.app.DataManager;
import com.sun.sgs.app.ManagedObject;
import com.sun.sgs.app.util.ScalableHashMap;
import com.sun.sgs.app.Task;

/**
 * Removes entities from the persistent object store if they haven't
 * been heard from in some period of time. This is only for network-
 * controlled objects; if the live on the server side, they are
 * not pruned. Note that this is not correct right now; this simply
 * removes the entity from the ScalableHashMap, but not from the
 * persistent object store. The problem is that various tasks have
 * the persistent object embedded in them, and removing the object
 * can or will cause null pointer errors when, say, the heartbeat
 * task tries to retrieve the object associated with it. We need some
 * way to also cancel the tasks associated with the objects.
 *
 * @author mcgredo
 */
public class ReaperTask implements Task, Serializable
{
    public static final long serialVersionUID = 1L;

    /** The number of heartbeat cycles after which we assume the entity on the
     * network is no longer present, causing us to remove it.
     */
    public static final int MAX_HEARTBEAT_CYCLES = 4;

    public ReaperTask()
    {
    }

    /**
     * Called by the darkstar TaskManager at periodic intervals. 
     */
    public void run()
    {
        DataManager dataManager = AppContext.getDataManager();

        String binding="ENTITIES";

        try
        {
            // Get the list of all the entities in the world
            ScalableHashMap entities = (ScalableHashMap)dataManager.getBinding(binding);
            Collection ent = entities.values();
            Iterator it = ent.iterator();
            long now = System.currentTimeMillis();

            // Iterate through the list, removing any entities that haven't been heard
            // from in a few heartbeat cycles.
            while(it.hasNext())
            {
                Entity e = (Entity)it.next();
                if(e.controlLocation == Entity.ControlLocation.SERVER)
                    continue;
                
                // If we haven't heard from them in some multiple of the heartbeat,
                // assume they're gone. This is true only for network-hosted entities, not those
                // run from the server side.
                if(now - (DarkstarServer.HEARTBEAT_FREQUENCY * MAX_HEARTBEAT_CYCLES) > e.lastUpdated)
                {
                    String entityName = "ENTITY(" + e.entityId.getSite() + "," + e.entityId.getApplication() + "," + e.entityId.getEntity() + ")";
                    entities.remove(entityName);
                    dataManager.markForUpdate(entities);
                    System.out.println("---Removed " + entityName);

                    // TO DO: we also need to remove the object from the datastore itself, and cancel
                    // the tasks (like heartbeat and tick) that are assoicated with this object.
                }
                
            }
        }
        catch(Exception e)
        {
        }
    }

}