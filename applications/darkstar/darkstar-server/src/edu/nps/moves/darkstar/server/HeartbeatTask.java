
package edu.nps.moves.darkstar.server;

import com.sun.sgs.app.AppContext;
import com.sun.sgs.app.Channel;
import com.sun.sgs.app.ChannelManager;
import com.sun.sgs.app.DataManager;
import com.sun.sgs.app.ManagedObject;
import com.sun.sgs.app.ManagedReference;
import com.sun.sgs.app.Task;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.logging.Logger;
import java.util.logging.Level;

import edu.nps.moves.dis.*;
import edu.nps.moves.disutil.*;

/**
 * The heartbeat task contains an entity object, and is scheduled to run periodically
 * by darkstar. When it runs it causes the entity to issue an entity state PDU.<p>
 * 
 * @author mcgredo
 */
public class HeartbeatTask implements Task, Serializable
{

    public static Logger logger = Logger.getLogger(HeartbeatTask.class.getName());
    ManagedReference<Entity> entityRef;

    public HeartbeatTask(ManagedReference entityRef)
    {
        this.entityRef = entityRef;
    }


    /**
     * This method is called at the heartbeat interval, when every entity MUST
     * send out a DIS ESPDU update.
     */
    public void run()
    {
        Entity entity = entityRef.get();

        // Only send out heartbeat updates for entities that are hosted on the server side
        if(entity.controlLocation == Entity.ControlLocation.SERVER)
        {
            EntityStatePdu espdu = new EntityStatePdu();

            // Check to make sure I got all of these....
            espdu.setAlternativeEntityType(entity.alternativeEntityType);
            espdu.setCapabilities(entity.capabilities);
            espdu.setDeadReckoningParameters(entity.deadReckoningParameters);
            espdu.setEntityAppearance(entity.entityAppearance);
            espdu.setForceId((short)entity.forceId);
            espdu.setEntityID(entity.entityId);
            espdu.setEntityLinearVelocity(entity.entityVelocity);
            espdu.setEntityLocation(entity.entityLocation);
            espdu.setEntityOrientation(entity.entityOrientation);
            espdu.setEntityType(entity.entityType);
            espdu.setNumberOfArticulationParameters(entity.numberOfArticulationParameters);
            //espdu.setArticulationParameters(entity.articulationParameters);

            // Marshal the espdu object to an IEEE 1278.1 byte array
            byte ieee[] = espdu.marshal();
            ByteBuffer buffer = ByteBuffer.wrap(ieee);

            // Retrieve the DIS channel and send the espdu on it
            ChannelManager channelManager = AppContext.getChannelManager();
            Channel disChannel = channelManager.getChannel(DarkstarServer.DIS_CHANNEL );
            disChannel.send(null, buffer);
            //logger.log(Level.INFO, "++++Sent ESPDU heartbeat");
        }



    }

}
