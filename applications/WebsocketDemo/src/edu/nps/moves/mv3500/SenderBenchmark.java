package edu.nps.moves.mv3500;

/**
 * Sends some data to the client in an effort to find out just how fast we can
 * do this.
 * 
 * @author DMcG
 */
public class SenderBenchmark implements Runnable
{
    public static final int ITERATIONS = 100000;
    
    String message = null;
    WebPageConnection client = null;
    
    public SenderBenchmark(WebPageConnection client, String message)
    {
        this.message = message;
        this.client = client;
    }
    
    @Override
    public void run()
    {
       try
       {
           for(int idx = 0; idx < ITERATIONS; idx++)
           {
                client.sendToClient(message);
                //Thread.sleep(10);
           }
       }
       catch(Exception e)
       {
           System.out.println(e);
       }
        
    }

}
