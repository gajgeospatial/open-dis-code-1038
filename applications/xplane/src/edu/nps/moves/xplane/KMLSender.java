/*
 * 
 * Takes an ESPDU and writes a KML placemark
 * 
 */
package edu.nps.moves.xplane;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import edu.nps.moves.dis.*;


/**
 *
 * @author $Tariq Rashid
 */
public class KMLSender implements Updater{

    public static final boolean DEBUG = true;
    public static final String DEFAULT_KML_FILE_NAME =  "xplanedata.kml";
    static FileWriter KMLOutput = null;
    static Transformer transformer;
    static String root = "Document";
    private String outputFileName = DEFAULT_KML_FILE_NAME;
    boolean on = false;
    
    public KMLSender(){
        
    }
    
    public void setDestination(String url){
        
    }
    
    public void setOutputFileName(String name){
        outputFileName = name;
    }
    
    
    
    public void sendEspdu(EntityStatePdu espdu) {
        //dataMap.readIntoMap(dataList);
        
        if (DEBUG) System.out.println("Call to Write KML Made");
        
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            //document.createElementNS("http://www.opengis.net/kml/2.2", "");

            Element rootElement = document.createElement(root);
            //rootElement.setAttribute(root, root)
            document.appendChild(rootElement);

            PrintWriter out = null;
            try {

                KMLOutput = new FileWriter(outputFileName);

                out = new PrintWriter(outputFileName);
            } catch (IOException e) {
                System.err.println("Caught IOException: " + e.getMessage());
            //throw e;
            }
 
            String location = "LookAt";
            Element LA = document.createElement(location);
            
            
            String longitude = "longitude";
            String data = (""+espdu.getEntityLocation().getX());
            Element value = document.createElement(longitude);
            LA.appendChild(value);
            value.appendChild(document.createTextNode(data));

            String latitude = "latitude";
            data = (""+espdu.getEntityLocation().getY());
            value = document.createElement(latitude);
            LA.appendChild(value);
            value.appendChild(document.createTextNode(data));

            String altitude = "altitude";
            data = (""+espdu.getEntityLocation().getZ());
            value = document.createElement(altitude);
            LA.appendChild(value);
            value.appendChild(document.createTextNode(data));
            
      
            String heading = "heading";
            data = (""+espdu.getEntityOrientation().getPsi());
            value = document.createElement(heading);
            LA.appendChild(value);
            value.appendChild(document.createTextNode(data));
            
            
            String tilt = "tilt";
            data = (""+espdu.getEntityOrientation().getTheta());
            value = document.createElement(heading);
            LA.appendChild(value);
            value.appendChild(document.createTextNode(data));
            
            rootElement.appendChild(LA);
            
            String Placemark = "Placemark";
             Element pm = document.createElement(Placemark);
            
            String point = "Point";
            Element pt = document.createElement(point);
            pm.appendChild(pt);
            
            String coordinates = "coordinates";
            String lon = (""+espdu.getEntityLocation().getX());
            String lat = (""+espdu.getEntityLocation().getY());
            String alt = (""+espdu.getEntityLocation().getZ());
            data = (lon + "," + lat + "," + alt);
            value = document.createElement(coordinates);
            pt.appendChild(value);
            value.appendChild(document.createTextNode(data));
 
            rootElement.appendChild(pm);
  
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            try {
                transformer = transformerFactory.newTransformer();
            } catch (TransformerConfigurationException tce) {
                System.out.println("Transformer exception" + tce.getMessage());
            }
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(out);
            try {
                transformer.transform(source, result);
            } catch (TransformerException te) {
                System.out.println("Transformer exception" + te.getMessage());
            }
            out.close();
            try{
            KMLOutput.close();
            }catch(IOException ie){
                System.out.println("Could not close file" + ie.getMessage());
            }
   } catch(ParserConfigurationException pce){
    pce.printStackTrace();
    System.out.println(pce);
}
    }

     public boolean isOn()
    {
        return on;
    }

    public void setOn(boolean state)
    {
        on = state;
    }
}

