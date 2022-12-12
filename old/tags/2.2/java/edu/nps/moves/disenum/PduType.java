
package edu.nps.moves.disenum;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import edu.nps.moves.dis.Pdu;
import edu.nps.moves.dis.*;

/**
 * Java enumeration for PDU type. This should be generated from XML, but I'm just
 * doing this by hand for now.
 *
 * @author mcgredo
 */
public enum PduType
{
    OTHER(0, "Undefined/Other")
    {
        public Pdu getPdu(byte data[])
        {
            return null;
        }
    },
    ESPDU(1, "Entity State PDU")
    { 
        public Pdu getPdu(byte data[])
        {
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
            Pdu aPdu = new EntityStatePdu();
            aPdu.unmarshal(dis);
            return aPdu;
        }
    },
    FIRE_PDU(2, "Fire PDU")
    { 
        public Pdu getPdu(byte data[])
        {
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
            Pdu aPdu = new FirePdu();
            aPdu.unmarshal(dis);
            return aPdu;
        }
    },
    DETONATION_PDU(3, "Detonation PDU")
    { 
        public Pdu getPdu(byte data[])
        {
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
            Pdu aPdu = new DetonationPdu();
            aPdu.unmarshal(dis);
            return aPdu;
        }
    },
    COLLISION_PDU(4, "Collision PDU")
    { 
        public Pdu getPdu(byte data[])
        {
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
            Pdu aPdu = new CollisionPdu();
            aPdu.unmarshal(dis);
            return aPdu;
        }
    },
    SERVICE_REQUEST_PDU(5, "Service Request PDU")
     { 
        public Pdu getPdu(byte data[])
        {
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
            Pdu aPdu = new ServiceRequestPdu();
            aPdu.unmarshal(dis);
            return aPdu;
        }
    },
    RESUPPLY_OFFER_PDU(6, "Resupply Offer PDU")
     { 
        public Pdu getPdu(byte data[])
        {
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
            Pdu aPdu = new ResupplyOfferPdu();
            aPdu.unmarshal(dis);
            return aPdu;
        }
    },
    RESUPPLY_RECEIVED_PDU(7, "Resupply Recevied PDU")
     { 
        public Pdu getPdu(byte data[])
        {
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
            Pdu aPdu = new ResupplyReceivedPdu();
            aPdu.unmarshal(dis);
            return aPdu;
        }
    },
    RESUPPLY_CANCEL_PDU(8, "Resupply Cancel PDU")
     { 
        public Pdu getPdu(byte data[])
        {
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
            Pdu aPdu = new ResupplyCancelPdu();
            aPdu.unmarshal(dis);
            return aPdu;
        }
    },
    REPAIR_COMPLETE_PDU(9, "Repair Complete PDU")
     { 
        public Pdu getPdu(byte data[])
        {
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
            Pdu aPdu = new RepairCompletePdu();
            aPdu.unmarshal(dis);
            return aPdu;
        }
    },
    REPAIR_RESPONSE_PDU(10, "Repair Response PDU")
     { 
        public Pdu getPdu(byte data[])
        {
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
            Pdu aPdu = new RepairResponsePdu();
            aPdu.unmarshal(dis);
            return aPdu;
        }
    },
    CREATE_ENTITY_PDU(11, "Create Entity PDU")
     { 
        public Pdu getPdu(byte data[])
        {
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
            Pdu aPdu = new CreateEntityPdu();
            aPdu.unmarshal(dis);
            return aPdu;
        }
    },
    REMOVE_ENTITY_PDU(12, "Remove Entity PDU")
     { 
        public Pdu getPdu(byte data[])
        {
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
            Pdu aPdu = new RemoveEntityPdu();
            aPdu.unmarshal(dis);
            return aPdu;
        }
    },
    START_RESUME_PDU(13, "Start/Resume PDU")
     { 
        public Pdu getPdu(byte data[])
        {
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
            Pdu aPdu = new StartResumePdu();
            aPdu.unmarshal(dis);
            return aPdu;
        }
    },
    STOP_FREEZE_PDU(14, "Stop/Freeze PDU")
     { 
        public Pdu getPdu(byte data[])
        {
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
            Pdu aPdu = new StopFreezePdu();
            aPdu.unmarshal(dis);
            return aPdu;
        }
    },
    ACKNOWLEDGE_PDU(15, "Acknowledge PDU"),
    ACTION_REQUEST_PDU(16, "Action/Request PDU"),
    ACTION_RESPONSE_PDU(17, "Action Response PDU"),
    DATA_QUERY_PDU(18, "Data Query PDU"),
    SET_DATA_PDU(19, "Set Data PDU"),
    DATA_PDU(20, "Data PDU"),
    EVENT_REPORT_PDU(21, "Event Report PDU"),
    COMMENT_PDU(22, "Comment PDU"),
    ELECTRONIC_EMMISSION_PDU(23, "Electronic Emission PDU"),
    DESIGNATOR_PDU(24, "Designator PDU"),
    TRANSMITTER_PDU(25, "Transmitter PDU"),
    SIGNAL_PDU(26, "Signal PDU"),
    RECEIVER_PDU(27, "Receiver PDU"),
    IFF_ATC_NAV_AIDS_PDU(28, "IFF?ATC/Nav Aids PDU"),
    UNDERWATER_ACOUSTIC_PDU(29, "Underwater Acoustic PDU"),
    ENTITY_STATE_UPDATE_PDU(30, "Entity State Update PDU"),
    INTERCOM_SIGNAL_PDU(31, "Intercom Signam PDU"),
    INTERCOM_CONTROL_PDU(32, "Intercom Control PDU"),
    AGGREGATE_STATE_PDU(33, "Aggregate State PDU"),
    IS_GROUP_OF_PDU(34, "Is Group Of PDU"),
    TRANSFER_CONTROL_PDU(35, "Transfer Control PDU"),
    IS_PART_OF_PDU(36, "Is Part Of PDU"),
    MINEFIELD_STATE_PDU(37, "Minefield State PDU"),
    MINEFIELD_QUERY_PDU(38, "Minefield Query PDU"),
    MINEFIELD_DATA_PDU(39, "Minefield Data PDU"),
    MINEFIELD_RESPONSE_NAK_PDU(40, "Minefield Response NAK PDU"),
    ENVIRONMENTAL_PROCESS_PDU(41, "Environmental Process PDU"),
    GRIDDED_DATA_PDU(42, "Gridded Data PDU"),
    POINT_OBJECT_STATE_PDU(43, "Point Object State PDU"),
    LINEAR_OBJECT_STATE_PDU(44, "Linear Object State PDU"),
    AREAL_OBJECT_STATE_PDU(45, "Linear Object State PDU");
    
    
    
    
    /** Augh. There should be a better way to do this. The problem is that in switch
     * statements we can't use variables, even final variables. This sets up a lookup
     * table, with the index of each array containing an enumeration instance. For
     * instance, the espdu enum is saved in slot 1, other in slot 0, etc. When we
     * read pdu type from the wire, we simply use that to index into the array and
     * find the right enumeration type. This is fast, and doesn't generate garbage,
     * as a hashtable lookup might.
     */
    
    static public PduType lookup[] = new PduType[255];
    
    // Initialize the lookup array.
    static 
    {
        for(PduType pdu: PduType.values())
        {
            lookup[pdu.value] = pdu;
        }
    }
    
    /** The cardinal value of the enum, what we're usually intersted in */
    public final int value;
    
    /** Description of the enum */
    public final String description;
    
    /** Constructor run when each enum instance is created above */
    PduType(int value, String description)
    {
        this.value = value;
        this.description = description;
    }
    
    public int getValue()
    { 
        return value;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public Pdu getPdu(byte data[])
    {
        return null;
    }
    
    
}
