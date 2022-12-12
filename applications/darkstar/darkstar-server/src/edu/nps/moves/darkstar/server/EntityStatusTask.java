

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
 * EntityStatusTask simply prints out all the entities at some frequency.
 *
 * @author mcgredo
 */
public class EntityStatusTask implements Task, Serializable
{
    public static final long serialVersionUID = 1L;

    public EntityStatusTask()
    {
    }

    public void run()
    {
        DataManager dataManager = AppContext.getDataManager();

        String binding="ENTITIES";

        try
        {
            ScalableHashMap entities = (ScalableHashMap)dataManager.getBinding(binding);
            Collection ent = entities.values();
            Iterator it = ent.iterator();
            System.out.println("----");
            System.out.println("Number of entities in system:" + ent.size());
            /*
            while(it.hasNext())
            {
                Entity e = (Entity)it.next();
                System.out.println("Entity: " + e.toString());
            }
             */
            System.out.println("----");
        }
        catch(Exception e)
        {
        }
    }

}
