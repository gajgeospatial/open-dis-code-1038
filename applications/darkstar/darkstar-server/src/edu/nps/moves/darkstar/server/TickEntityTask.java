
package edu.nps.moves.darkstar.server;

import com.sun.sgs.app.AppContext;
import com.sun.sgs.app.DataManager;
import com.sun.sgs.app.ManagedObject;
import com.sun.sgs.app.ManagedReference;
import com.sun.sgs.app.Task;

import java.io.Serializable;

/**
 * A periodic task that runs every time quanta for certain entities. This can be
 * used to notify the Entity that it should update its position, do dead reckoning,
 * collision detection, AI, etc.<p>
 *
 * Remember the rules for darkstar: you want the task time quanta to be relatively
 * brief.<p>
 *
 * @author mcgredo
 */
public class TickEntityTask implements Task, Serializable
{
    ManagedReference<Entity> entityRef;

    public TickEntityTask(ManagedReference entityRef)
    {
        this.entityRef = entityRef;
    }


    /**
     * The run method is called by the task manager on a fairly short cycle
     */
    public void run()
    {
        Entity entity = entityRef.get();
        entity.tick();
    }
}
