
package edu.nps.moves.darkstar.server;

import java.nio.ByteBuffer;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.sgs.app.AppContext;
import com.sun.sgs.app.Channel;
import com.sun.sgs.app.ClientSession;
import com.sun.sgs.app.ChannelListener;
import com.sun.sgs.app.DataManager;
import com.sun.sgs.app.ManagedReference;
import com.sun.sgs.app.util.ScalableHashMap;


// Assorted open-dis packages for the distributed interactive simulation protocol
import edu.nps.moves.dis.*;
import edu.nps.moves.disutil.*;
import edu.nps.moves.disenum.*;

/**
 * A channel listener that interprets DIS messages. This runs on the server side.
 *
 * @author mcgredo
 */
public class DisChannelServerListener implements Serializable, ChannelListener
{

    private static final long SerialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(ApplicationListener.class.getName());

    public void receivedMessage(Channel channel, 
                                ClientSession session,
                                ByteBuffer message)
    {
        PduFactory factory = new PduFactory(); // SHould move this to ivars, but don't think it is serializable
        Pdu aPdu = factory.createPdu(message);
        int pduType = aPdu.getPduType();

        // Get the list of the entities in the system
        DataManager dataManager = AppContext.getDataManager();
        ScalableHashMap entities = (ScalableHashMap)dataManager.getBinding("ENTITIES");

        try
        {
            PduType type = PduType.getEnumerationForValue(pduType);

            // Switch statement for handling each type of PDU that comes in
            switch(type)
            {
                case ENTITY_STATE:
                    EntityStatePdu espdu = (EntityStatePdu)aPdu;
                    EntityID id = espdu.getEntityID();
                    String entityName = "ENTITY(" + id.getSite() + "," + id.getApplication() + "," + id.getEntity() + ")";
                    logger.log(Level.INFO, "Got espdu message for entity " + entityName + " from client " + session.getName());

                    // Look up the entity
                    Entity anEntity = (Entity)entities.get(entityName);

                    // Not found? Add it to our list of entities, with it being listed
                    // as being controlled from a host on the network, rather than the server
                    // side. This means we do NOT heartbeat() the entity; that should
                    // be handled by the client. Open question as to whether we should
                    // tick().
                    if(anEntity == null)
                    {
                        anEntity = new Entity();
                        anEntity.controlLocation = Entity.ControlLocation.NETWORK;
                        anEntity.entityId = espdu.getEntityID();
                        anEntity.entityType = espdu.getEntityType();
                        anEntity.alternativeEntityType = espdu.getAlternativeEntityType();
                        anEntity.deadReckoningParameters = espdu.getDeadReckoningParameters();
                        anEntity.entityAppearance = espdu.getEntityAppearance();
                        anEntity.entityLocation = espdu.getEntityLocation();
                        anEntity.entityOrientation = espdu.getEntityOrientation();
                        anEntity.entityVelocity = espdu.getEntityLinearVelocity();
                        anEntity.forceId = espdu.getForceId();
                        //anEntity.articulationParameters = espdu.getArticulationParameters();
                        anEntity.lastUpdated = System.currentTimeMillis();
                        // Probably other fields to be set
                        
                        dataManager.markForUpdate(anEntity);
                        entities.put(entityName, anEntity); // Add to list of entities
                        dataManager.markForUpdate(entities);
                        logger.log(Level.INFO, "Added new entity " + entityName);

                    }

                    anEntity.lastUpdated = System.currentTimeMillis();
                    
                    break;

                case FIRE:
                    logger.log(Level.INFO, "Got fire PDU");
                    break;

                case DETONATION:
                    logger.log(Level.INFO, "got detonation PDU");
                    break;

                case COLLISION:
                    logger.log(Level.INFO, "got collision PDU");
                    break;

                default:
                    // Silently continue
            }
        }
        catch(Exception e)
        {
            // Just punt and fail
            logger.log(Level.WARNING, "Problem decoding DIS message in DisChannelListener: " + e);
        }

    }

}
