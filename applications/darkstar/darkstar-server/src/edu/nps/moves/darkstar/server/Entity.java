
package edu.nps.moves.darkstar.server;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

import com.sun.sgs.app.ManagedObject;

import edu.nps.moves.dis.*;

/**
 * The Enitity class represents an object in a 3D world, very close to that of
 * a DIS entity.<p>
 *
 * This is a managed object. Managed objects are persistent; the ManagedObject
 * interface is a marker interface, like Serializable, with no methods associated
 * with it.
 * 
 * @author mcgredo
 */
public class Entity implements Serializable, ManagedObject
{
    /** where the entity is controlled from--either a host on the network is updating
     * it, or it is controlled by the server itself.
     */
    public enum ControlLocation{NETWORK, SERVER};

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(Entity.class.getName());

    EntityID    entityId;
    EntityType  entityType;
    EntityType  alternativeEntityType;
    int         entityAppearance;
    int         capabilities;
    int         forceId;
    Marking     marking;
    byte        numberOfArticulationParameters;
    Vector3Float    entityVelocity;
    Vector3Double   entityLocation;
    Orientation     entityOrientation;
    DeadReckoningParameter  deadReckoningParameters;
    List articulationParameters;

    /** The last time we heard from this entit on the network */
    long        lastUpdated;
    ControlLocation controlLocation;


   

    public Entity()
    {
        entityId = new EntityID();
        entityType =new EntityType();
        alternativeEntityType = new EntityType();
        entityAppearance = 0;
        capabilities = 0;
        forceId = 0;
        marking = new Marking();
        numberOfArticulationParameters = 0;
        entityVelocity = new Vector3Float();
        entityLocation = new Vector3Double();
        entityOrientation = new Orientation();
        deadReckoningParameters = new DeadReckoningParameter();

        lastUpdated = System.currentTimeMillis();
        controlLocation = ControlLocation.SERVER;
    }

    public String toString()
    {
        return "ENTITY(" + entityId.getSite() + "," + entityId.getApplication() + "," + entityId.getEntity() + ")";
    }

    /**
     * Can be called periodically for dead reckoning or whatever. Typically the time intervals between
     * ticks should be fairly small, and the tick() method should be brief and not take very
     * long to execute.
     */
    public void tick()
    {
        
    }

}
