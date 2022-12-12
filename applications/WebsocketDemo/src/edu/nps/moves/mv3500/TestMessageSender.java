
package edu.nps.moves.mv3500;

/**
 * Sends an extremely simple JSON message to the clients at a regular interval.
 * This is designed to run via thread.
 * 
 * @author DMcG
 */
public class TestMessageSender implements Runnable
{
    public TestMessageSender()
    {
        
    }
    
    @Override
    public void run()
    {
        // get singleton
        ConnectionManager connectionManager = ConnectionManager.getConnectionManager();
                
        while(true)
        {
            for(int idx = 0; idx < 20; idx++)
            {
                // JSON format message we will send out--goes from 0 to 99
                // on the x axis, then repeats.
                String message = "{ id:0, x:" + idx + ", y:1, z:1}";
                // sends message to all clients
                connectionManager.repeatMessage(message, null);
                
                try
                {
                    // Wait two seconds, then do it again
                    Thread.sleep(1000);
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
        }
        
        
    }

}
