/* 
 * Used to generate X-plane UDPs and send to a multicast address without
 * needing to have X-plane running
 * 
 * 
 * Contains a method to record and store xplane UDPS
 * 
 * 
 */

package edu.nps.moves.xplane;

import java.io.*;
import java.net.*;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
  

/**
 *
 * @author $Tariq Rashid
 */
public class XPlaneOutputSimulator implements Runnable {
    
    private String OUTPUT_FILE_DEFAULT_NAME = "xPlaneUdps.txt";
    
    private String outputFileName;
    private String inputFileName;
    
    PrintWriter out;
    
    private static RandomAccessFile file;
    
    
    private int XplaneUdpLength;
    
    private byte[] xPlaneUdp;
    
            
            
   public XPlaneOutputSimulator(){
        
    }
            
    
   
    
  public  void writeToOutFile(DatagramPacket pkt, String outputFileName) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(outputFileName,true));
        
       for (int i = 0;i < pkt.getData().length;i++){
        out.write(pkt.getData()[i]);
       }
        out.newLine();
        out.close();
    } //End WriteToOutFile
    

  
  
  public byte[] readInputFile(String fileName){
      
      byte[] pdu = new byte[149];
      
      
      //BufferedReader in = new BufferedReader(new java.io.FileReader(inputFileName));
      
       try {
            file = new RandomAccessFile(fileName, "r");
        } catch (IOException io) {
            System.out.println("Unable to open input file: " + fileName);
        }
     
      
  try{
        file.read(pdu);
  }catch(IOException ioe){
      
  }//
      
      return pdu;
      
  }
  
  
  
  
  
    
   public void run(){
       
       
   }

}
