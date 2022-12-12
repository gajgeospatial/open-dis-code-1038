
package edu.nps.moves;

import org.zkoss.zul.*;
import edu.nps.moves.dis.*;
import edu.nps.moves.dis.EntityStatePdu;

/**
 * Class that renders PDUs (from the DIS-XML package) into a Row of
 * a zk grid. See http://www.zkoss.org/smalltalks/livedata/livedataforgrid.dsp
 * for details.
 *
 * @author DMcG
 */
public class PduRowRenderer implements RowRenderer
{
    
    /** Creates a new instance of PduRowRenderer */
    public PduRowRenderer()
    {
    }
    
    /**
     * Render a PDU object (from DIS-XML) into a Row. 
     */
    public void render(Row row, Object data)
    {
        String pduType   = "Unknown";
        String timestamp = "Unknown";
        String content   = "...";
        Pdu pdu;
        EntityID eid;
        
        // It needs to be a PDU
        if(!(data instanceof Pdu))
        {
            return;
        }
        
        pdu = (Pdu)data;
        
        // First column: the pdu type. this needs to be handled less verbosely 
        // in the dis-xml package
        short pduShortType = pdu.getPduType();
        switch(pduShortType)
        {
            case 1: pduType = "ESPDU";
                    EntityStatePdu espdu = (EntityStatePdu)pdu;
                    content = "Source ID:";
                    eid = espdu.getEntityID();
                    content = content + "(" + new Integer(eid.getSite()).toString() + "," +
                       new Integer(eid.getApplication()).toString() + "," +
                       new Integer(eid.getEntity()).toString() + ")";

             break;
             
            case 2: pduType = "Fire";
            break;
            
            case 3: pduType = "Detonation";
            break;
            
            default:
                pduType = pdu.getClass().getName();
        }
        
        
        // Second column: the timestamp
        timestamp = new Long(pdu.getTimestamp()).toString();
        
          
        // Create new Label objects to be placed in the row.
        new Label(pduType).setParent(row);
        new Label(timestamp).setParent(row);
        new Label(content).setParent(row);
        
        return; 
    }
    
}
