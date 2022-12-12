package edu.nps.moves.disutil;

import edu.nps.moves.dis.*;
import edu.nps.moves.jaxb.dis.*;

import javax.xml.bind.*;
import javax.xml.bind.util.*;
import javax.xml.namespace.*;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;



/**
 * Example class for reading and writing XML representations of DIS PDUs. <p>
 *
 * The open-dis objects are set up to have parallel JAXB objects. The idea is that
 * when writing to XML, we take the open-dis object and make a copy of it in the
 * jaxb objects. When reading from an XML file, we read to a jaxb object, then
 * convert it (if we like) to an open-dis object. The two objects have identical
 * getter/setter methods, though the semantics are different on some setters, and
 * the open-dis objects have additional methods and are able to marshal themselves
 * to the IEEE binary standard.
 *
 * @author DMcG
 */
public class XmlReadWrite extends Object
{
    ObjectFactory factory = new ObjectFactory();
    
    /**
     * Example/test code showing writing to and reading from an XML file.
     */
   public static void main(String args[])
    {
        XmlReadWrite rw = new XmlReadWrite();
        List<edu.nps.moves.dis.Pdu> pdus = new ArrayList();
        
        // Create a list of open-dis pdus that we will write out
        edu.nps.moves.dis.EntityStatePdu espdu1 = new edu.nps.moves.dis.EntityStatePdu();
        edu.nps.moves.dis.EntityStatePdu espdu2 = new edu.nps.moves.dis.EntityStatePdu();
        edu.nps.moves.dis.EntityStatePdu espdu3 = new edu.nps.moves.dis.EntityStatePdu();
        edu.nps.moves.dis.FirePdu firePdu1 = new edu.nps.moves.dis.FirePdu();
        edu.nps.moves.dis.DetonationPdu detPdu = new edu.nps.moves.dis.DetonationPdu();
        
        espdu1.getEntityID().setApplication(2);
        espdu2.getEntityID().setApplication(3);
        espdu3.getEntityID().setApplication(4);
        
        
        pdus.add(espdu1);
        pdus.add(espdu2);
        pdus.add(espdu3);
        pdus.add(firePdu1);
        pdus.add(detPdu);
        
        // Marshal them to a file
        rw.marshalToXml(pdus, "testMarshal.xml");
        
        System.out.println("Wrote " + pdus.size() + " PDUs out");

        
        // Read the data back from the XML file, convert to open-dis objects
        
        List<edu.nps.moves.dis.Pdu> openDisPdus = rw.readFromXml("testMarshal.xml");
        
        System.out.println("Got " + openDisPdus.size() + " PDUs back");
        for(int idx = 0; idx < openDisPdus.size(); idx++)
        {
            edu.nps.moves.dis.Pdu aPdu = openDisPdus.get(idx);
            
            if(aPdu instanceof edu.nps.moves.dis.EntityStatePdu)
            {
                System.out.println("app id is " + ((edu.nps.moves.dis.EntityStatePdu)aPdu).getEntityID().getApplication());
            }
        }
        
    }
    
    /**
     * Given a List of PDU objects (which may have concrete subclasses of EntityStatePdu, FirePdu, etc)
     * write to an XML file.
     */
    public void marshalToXml(List<edu.nps.moves.dis.Pdu> pdus, String filename)
    {
        
        edu.nps.moves.jaxb.dis.PduCollection jaxbPduCollection = factory.createPduCollection();
        List jaxbPdus = jaxbPduCollection.getPdus();
        
        // Convert the list of open-dis pdus to jaxb pdus. 
        for(int idx = 0; idx < pdus.size(); idx++)
        {
            edu.nps.moves.dis.Pdu aPdu = pdus.get(idx);
            
            edu.nps.moves.jaxb.dis.Pdu jaxbPdu = this.getJaxbObject(aPdu);
            jaxbPdu.setTimestamp(100);
            jaxbPdus.add(jaxbPdu); // We add to the live list of jaxbPduCollection here
        }
        
        // write the list of jaxb pdus to the XML file
        try
        {
            JAXBContext context = JAXBContext.newInstance("edu.nps.moves.jaxb.dis");
            Marshaller marshaller = context.createMarshaller();
            
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));
            marshaller.marshal(jaxbPduCollection, new FileOutputStream(filename));   
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    
    /**
     * Given an XML file, read from it to jaxb objects, then convert the jaxb
     * objects to open-dis objects and return a list of those.
     */
    public List<edu.nps.moves.dis.Pdu> readFromXml(String filename)
    {
        List<edu.nps.moves.dis.Pdu> openDisPdus = new ArrayList();
        
        try
        {
            JAXBContext context = JAXBContext.newInstance("edu.nps.moves.jaxb.dis");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            
            edu.nps.moves.jaxb.dis.PduCollection pduCollection =(edu.nps.moves.jaxb.dis.PduCollection) unmarshaller.unmarshal(new File( filename ));
            
            // Convert the jaxb pdus to open-dis pdus
            for(int idx = 0; idx < pduCollection.getPdus().size(); idx++)
            {
                edu.nps.moves.jaxb.dis.Pdu jaxbPdu = pduCollection.getPdus().get(idx);
                
                openDisPdus.add( this.getOpenDisPdu(jaxbPdu)); 
            }

        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
        
        
        return openDisPdus;
    }
    
    /**
     * Given a Pdu instance from open-dis, returns an initialized PDU from the parallel
     * jaxb packaage.<p>
     *
     * This should probably be optimized....good enough for now.
     */
    private edu.nps.moves.jaxb.dis.Pdu getJaxbObject(edu.nps.moves.dis.Pdu aPdu)
    {
        String fullyQualifiedClassName = aPdu.getClass().getName();
        String className;
        int lastPeriod;
        String getMethodName;
        Method getMethod = null;
        edu.nps.moves.jaxb.dis.Pdu jaxbPdu = null;
        
        // Find the class name
        lastPeriod = fullyQualifiedClassName.lastIndexOf('.');
        className  = fullyQualifiedClassName.substring(lastPeriod + 1);
        getMethodName = "create" + className;
    
        // use reflection on the factory object to call the correct method to create a new
        // jaxb pdu object.
        
        Method[] allFactoryMethods = factory.getClass().getMethods();
        
        for(int idx = 0; idx < allFactoryMethods.length; idx++)
        {
            //System.out.println(allFactoryMethods[idx].getName());
            if(allFactoryMethods[idx].getName().equals(getMethodName))
            {
                getMethod = allFactoryMethods[idx];
                break;
            }
        }
        
        try
        {
            // We have the method, now call this method on the factory object.
            jaxbPdu = (edu.nps.moves.jaxb.dis.Pdu)getMethod.invoke(factory, new Object[0]);
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
        
        // We have an uninitialized jaxb pdu of the correct type--initialize it to the settings of the
        // current open-dis pdu. This is trickier than it sounds--we need to pass in an object of
        // the correct concrete type. Just passing in something of type PDU is not enough--each of
        // the concrete subclasses have initializeJaxbObject methods, but take as arguments that subclass
        // of jaxb. If we simply call with an instance of jaxb.pdu it will go to the top level PDU
        // class and not call the subclass initializers. To prevent this we use reflection.
        
        try
        {
            Class methodArgs[] = new Class[1];
            methodArgs[0] = jaxbPdu.getClass();
            
            Method initializeMethod = aPdu.getClass().getMethod("initializeJaxbObject", methodArgs);
            Object callArgs[] = new Object[1];
            callArgs[0] = jaxbPdu;
            initializeMethod.invoke(aPdu, callArgs);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        //aPdu.initializeJaxbObject(jaxbPdu);
        
        if(aPdu instanceof edu.nps.moves.dis.EntityStatePdu)
        {
            //System.out.println("original PDU:" + ((edu.nps.moves.dis.EntityStatePdu)aPdu).getEntityID().getApplication());
            //System.out.println("JAXB pdu:" + jaxbPdu);
            //System.out.println("JAXB pdu eid:" + ((edu.nps.moves.jaxb.dis.EntityStatePdu)jaxbPdu).getEntityID());
            //System.out.println("jaxb PDU:" + ((edu.nps.moves.jaxb.dis.EntityStatePdu)jaxbPdu).getEntityID().getApplication());
        }
        
        return jaxbPdu;
    }
    
    /**
     * Given a jaxb pdu, create a new open-dis pdu of the correct concrete subclass
     * and return that.
     *
     * This should probably be more optimized, good enough for now.
     */
    private edu.nps.moves.dis.Pdu getOpenDisPdu(edu.nps.moves.jaxb.dis.Pdu jaxbPdu)
    {
        String fullyQualifiedJaxbClassName = jaxbPdu.getClass().getName();
        String fullyQualifiedOpenDisClassname;
        String shortClassName;
        int startPoint;
        edu.nps.moves.dis.Pdu openDisPdu = null;
        
        startPoint = fullyQualifiedJaxbClassName.lastIndexOf('.') + 1;
        shortClassName = fullyQualifiedJaxbClassName.substring(startPoint);
        
        fullyQualifiedOpenDisClassname = "edu.nps.moves.dis." + shortClassName;
        
        try
        {
            
            Class aPduClass = Class.forName(fullyQualifiedOpenDisClassname);
            Class[] params = new Class[1];
            params[0] = Class.forName(fullyQualifiedJaxbClassName);
            Constructor constructor = aPduClass.getConstructor(params);
            Object[] args = new Object[1];
            args[0] = jaxbPdu;
            openDisPdu = (edu.nps.moves.dis.Pdu)(constructor.newInstance(args));
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
           
        return openDisPdu;
    }
}
