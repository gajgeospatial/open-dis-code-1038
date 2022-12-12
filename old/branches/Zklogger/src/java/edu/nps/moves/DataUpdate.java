package edu.nps.moves;

import org.zkoss.zul.*;
import org.zkoss.zk.ui.*;

import java.net.*;
import java.util.*;
import java.util.logging.*;

// API for open-dis
import edu.nps.moves.dis.*;
import edu.nps.moves.disutil.PduFactory;


/**
 * Update thread for PDUs. Zk is a fairly thin piece of GUI
 * on top of the html page. While you can write full-fledged
 * classes in it to be evaluated by bean shell, it's better
 * (I think) to put most of the heavy lifting in traditional
 * java classes, where they can be debugged more easily.
 *
 * This reads from the network, and passes information to the
 * to pieces of UI given to us by the creator, a box for couting
 * the number of PDUs and a scrolling list box for displaying
 * them.
 *
 * @author DMcG
 */

public class DataUpdate implements Runnable
{
  /** How often the user display is updated, in ms. */
  public static final int UPDATE_FREQUENCY_MS = 1000;
  
  /** 8K is the old max pdu size; could be higher now, but unlikely. */
  public static final int MAX_PDU_SIZE = 8192;
  
  public static final int START  = 0;
  public static final int STOP   = 1;
  public static final int RESUME = 2;
  public static final int CLEAR  = 3;
  
  public static final int DEFAULT_TIMEOUT_IN_MS = 1000;
  
  private static Logger logger = Logger.getLogger("DataUpdate");
  
  
  /** Overall container for related pages; for concurrency control we have
   *  to lock on this
   */
  private Desktop desktop;
  
  /** Zk component for displaying the number of PDUs. Note the lower case b in Textbox, vs. the Swing TextBox */
  private Textbox pduCount;
  
  /** Grid (table) that holds pdu data */
  private Grid pduGrid;
  
  /** Exit out of loop */
  private boolean ceased = false;

  /** The list that contains the PDU objects */
  private List pdus = new ArrayList();
  
  /** List model for the grid */
  private ListModelList gridModel;
  
  /** Multicast socket */
  private MulticastSocket pduSocket = null;
  
  /** Port number */
  private int port;
  
  /** Multicast group */
  private InetAddress multicastGroup;
  

  /**
   * Constructor; takes the text box that displays the count and the scrolling listbox for
   * displaying PDU contents. Both of these are Zk components.
   */
  public DataUpdate()
  {
    pdus = new ArrayList();
    
    try
    {
        FileHandler fh = new FileHandler("/tmp/zk.log", true);
        logger.addHandler(fh);
        logger.setLevel(Level.FINEST);
        
        logger.log(Level.WARNING, "Starting logging");
    }
    catch(Exception e)
    {
        System.out.println(e);
    }
  }
  
  public void socketSetup(String groupString, String portString)
  {
      
    try
    {
        logger.log(Level.ALL, "starting to open sockets");
        // Set up sockets for reading from the network
        multicastGroup = InetAddress.getByName(groupString);
        port = Integer.parseInt(portString);
        
        if(pduSocket == null)
        {
            pduSocket = new MulticastSocket(port);
            pduSocket.joinGroup(multicastGroup);
            pduSocket.setSoTimeout(DEFAULT_TIMEOUT_IN_MS);
        }
    }
    catch(Exception e)
    {
        System.out.println(e);
        logger.info("exception thrown opening sockets");
    }
      
  }
  
  
  public void setUIElements(Textbox pduCount, 
                            Grid pduGrid)
  {
      logger.log(Level.INFO, "in setUIElements");
      this.pduCount = pduCount;
      this.pduGrid = pduGrid;
      this.desktop = pduCount.getDesktop();
  }
  
  public void clear()
  {
      logger.log(Level.INFO, "In clear() ");
      try
      {
          // Set up the data model and row rendering for the grid
          pdus = new ArrayList();
          gridModel = new ListModelList(pdus);
          pduGrid.setModel(gridModel);
          
        //Executions.activate(desktop);
        
         // pduCount.setValue("0");
          
        //Executions.deactivate(desktop);
    }
    catch(Exception e)
    {
        logger.log(Level.INFO, "clear exception is " + e);
    }
          
  }
  
  public void resume()
  {
      //logger.log(Level.INFO, "Resume called");
      this.setCeased(false);
  }
  
  /**
   * Update the UI. Note that we MUST lock on the desktop for anything
   * UI related, since there are other threads that may be accessing it.
   *
   * Also, note that we are effectively polling here--we have to yield to
   * let others modify the UI. Behind the scenes the client is actually 
   * polling the server side.
   */
  public void run()
  {
    int count = 0;
    PduFactory factory = new PduFactory();
    
     
    //logger.log(Level.INFO, "in run");
    
     try
      {
              Executions.activate(desktop);
          
              //logger.log(Level.INFO, "setting pdu value");
              pduCount.setValue(new Integer(pdus.size()).toString() );
              //logger.log(Level.INFO, "set pdu value");
              
              //logger.log(Level.INFO, "Number of PDUs is " + pdus.size());
             // if(pdus.size() == 0)
              {
                  // Set up the data model and row rendering for the grid
                  //logger.log(Level.INFO, "creating new model");
                  gridModel = new ListModelList(pdus);
                  pduGrid.setModel(gridModel);
                  pduGrid.setRowRenderer(new PduRowRenderer());
                 // logger.log(Level.INFO, "set new data model");
              }
              
          Executions.deactivate(desktop);
      }
      catch(Exception e)
      {
          logger.log(Level.WARNING, "Failed to set grid model and pdu count: " + e.toString());
      }
    
    while(!ceased)
    {
        byte buffer[] = new byte[MAX_PDU_SIZE];
        DatagramPacket datagram = new DatagramPacket(buffer, buffer.length);
        String pduType = "unknown";
            
        
       try
       {
           // We have so_timeout set on the socket, so this will throw an exception
           // after the specified wait time period. This allows us to break out
           // of the loop even if no PDUs are being received.
           //logger.log(Level.INFO, "Preparing to read PDU");
           pduSocket.receive(datagram);
           //logger.log(Level.INFO, "Got PDU traffic");
           count++;
           
           //logger.log(Level.INFO, "gridModel is " + gridModel);
           // If the user has told us to stop collecting sometime during the receive
           // operation, punt this loop.
           if(ceased)
               break;
              
           //  Turn the bytes into a PDU object
           Pdu pdu = (Pdu)factory.createPdu(datagram.getData());
           if(pdu == null)
               continue;
           
           //logger.log(Level.INFO, "Attempting to add PDU to list");
           
           // Update the UI--we have to lock this.
           Executions.activate(desktop);
           
             pdus.add(pdu);
             gridModel.add(pdu);
             pduCount.setValue(new Integer(pdus.size()).toString());

           Executions.deactivate(desktop);
           //logger.log(Level.INFO, "added newly received PDU to list");
       }
       catch(Exception e)
       {
           // A socket timeout falls through to here. We can safely ignore it.
           //logger.setLevel(Level.ALL);
           //logger.log(Level.WARNING, "main loop exception " + e);
       }
    }
    
     pduSocket.close();
     pduSocket = null;

     logger.log(Level.INFO, "Exiting run method");
  }

  /**
   * When the UI stop button is clicked, this gets flipped, stopping
   * the update process
   */
  public void setCeased(boolean state)
  {
      //logger.log(Level.INFO, "Called setCeased");
     ceased = state;
  }
  
  public ListModel getModel()
  {
      //logger.info("getting model");
      return gridModel;  
  }
}