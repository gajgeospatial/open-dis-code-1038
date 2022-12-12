/*
 * Tools for taking a data InputStream and turning into esdu or xplane Actor<p>
 * 
 * This XplaneUDPInterpreter has been tested with version 8.64 of X-plane<p>
 * 
 * XplaneUDPInterpreter may need to be modified if Laminar Research 
 * Changes the Xplane UDP Model<p>
 *
 * It appears that in xplane 9 all data is sent in little-endian format. This
 * means you CANNOT use the standard Java streams tools, such as DataInputStream,
 * to decode multi-byte primitive types, since those tools expect the data to
 * be in big-endian format. Earlier versions of xplane had a flag that defined
 * the data as either big or little endian, but it appears to all be little
 * endian now.<p>
 * 
 * Xplane is a registered trademark of Laminar Research
 * http://www.x-plane.com/
 * 
 */
package edu.nps.moves.xplane;

import java.util.Map.*;
import java.io.*;
import java.net.*;
import java.util.*;


import edu.nps.moves.dis.*;
import edu.nps.moves.disenum.*;

/**
 *
 * @author $Tariq Rashid
 */
public class XPlanePacketParser {


    public static final boolean DEBUG = true;
    File parsedUDPFile = null;
    PrintWriter out = null;
    int dataGroups;
    int udpLength;
    public final static int HEADER_LENGTH = 5;  //This is the length of the header in an xplane UDP
    public final static int DATA_GROUP_ARRAY_LENGTH = 36;  //An X-plane data group is 36 bytes long
    public final static int VEHN_PACKET_LENGTH = 144;
    public final static float GRAVITY = 9.8f;
    public final double FEET_TO_METERS = 0.3048;
    Object[] parsedData = new Object[2];

    /** big endian is network byte order, used by PPC and most networking software, and java natively.
     * LITTLE_ENDIAN is Intel native.
     */
    public enum ByteOrder {BIG_ENDIAN, LITTLE_ENDIAN};
    
     EntityStatePdu espdu;
     ByteOrder packetByteOrder;
     
     public void XPlaneUDP(){
         
     }
     
      /**Parse one of several possible UDP packets 
       * 
       * @param packet
       * @return Object, the object will be either a espdu or a string vehicle name
       * @throws java.io.IOException
       */
    public void parseUDP(DatagramPacket packet) throws IOException {
        
        //if (DEBUG) System.out.println("Call to parseUDP made");

        String packetName = null;
        byte   endian = 0;  // 0 = little-endian (Intel), 1= big-endian (PPC, some unix, Java native streams)
        EntityStatePdu espdu;

        try
        {
            byte[] payload = packet.getData();

            /*
            for(int idx=0; idx < payload.length; idx++)
            {
                System.out.print(payload[idx] + " ");
            }
            System.out.println();
             * */
            
            // The packet name--a string--is in the first four bytes. The fifth byte
            // is some internal xplane thing no one is willing to describe.
            byte[] packetNameArray = Arrays.copyOfRange(payload, 0, HEADER_LENGTH - 1);
            //System.out.println(packet.getAddress());
            packetName = new String(packetNameArray, "UTF-8");

            // Earlier versions of xplane had a flag to identify the data as big or
            // little endian. In version 9 it appears to all be little endian.
            packetByteOrder = ByteOrder.LITTLE_ENDIAN;

            //System.out.println("Packet type:" + packetName + " sender endian:" + packetByteOrder);

            //If it is a DATA packet parse it accordingly
            if (packetName.equals("DATA")) {
              espdu =  parseXPlaneDataPacket(payload, packetByteOrder);
            }
            else if (packetName.equals("VEHN")) {
               parseXPlaneVEHNPacket(payload, packetByteOrder);
             }//End if name.equales("VEHN")
            else
                System.out.println("Unrecognized packet type:" + packetName + "; ignored");

        }
        catch(Exception e)
        {
            System.out.println(e);
        }

      return;
    } //End method parseUDP
    



    /**
     * 
     * @param dis
     * @param length
     * @return String which is decoded from SNAP packets from xplane
     * @throws java.io.IOException
     */
    public String parseXPlaneVEHNPacket(byte[] payload, ByteOrder byteOrder) throws IOException {
        //Decode the next DATA_GROUP_ARRAY_LENGTH byte segments into  words

        //System.out.println("Decoding the vehicle name");
        
        byte[] vehicleNameArray = Arrays.copyOfRange(payload, payload.length - HEADER_LENGTH - 4, 4);
        
        String vehicleName = "DEFAULT";// = sb.toString();

        try
        {
            //name = new String(nameArray,HEADER_LENGTH,lengthCount,"UTF-8");
            vehicleName = new String(vehicleNameArray, 0, DATA_GROUP_ARRAY_LENGTH - 1, "UTF-8");

          
        } catch (UnsupportedEncodingException uee) {
        }

        //System.out.println("vehcile name decoded as " + vehicleName);
        
        return vehicleName;
    } //End parseXPlaneVEHNPacket

    
    
    /**
     * parseXPlaneDataPacket takes a DataInputStream argument in the form of an Xplane UDP and return
     * A dis espdu object
     *
     * @param  DataInputStram
     * @param  lengh
     * @return  an Open-DIS espdu
     */
    public EntityStatePdu parseXPlaneDataPacket(byte[] payload, ByteOrder packetByteOrder) throws IOException {

        DataInput dataReader = null;
        int numberOfDataElements = (payload.length - HEADER_LENGTH) / DATA_GROUP_ARRAY_LENGTH;

        
        //if (DEBUG) System.out.println("Call to parseXPlaneDataPacket made, number of data elements=" + numberOfDataElements);

        // The DataReader interface
        // lets us use either a big or little endian reader, as the case may be.
        if(packetByteOrder == ByteOrder.LITTLE_ENDIAN) // Intel (little-endian) byte order
        {
            dataReader = new LEDataInputStream(new ByteArrayInputStream(payload));
        }
        else // Network byte order, aka big-endian, used natively by PPC and Java
        {
            dataReader = new DataInputStream(new ByteArrayInputStream(payload));
        }

        // Skip past the initial header bytes
        dataReader.skipBytes(HEADER_LENGTH);
  
        espdu = new EntityStatePdu();
        espdu.setProtocolVersion((short) ProtocolVersion.IEEE_12781A_1998.getValue());
        DeadReckoningParameter deadReckoningParameter = new DeadReckoningParameter();
  
        // There are N 36-byte data chunks here
        for (int i = 0; i < numberOfDataElements; i++)
        {
            //Get the Xplane data element index number. The dataIndex describes the
            // nature of the data being sent; see the xplane->Data Input & Output
            // dialog box for details. For example, 0 means we are receving g-load
            // data, 3 is speeds, 16 is angular accellerations, etc.
            int dataIndex = dataReader.readInt();

            // Sometimes if the stream pointer is off, we'll get garbage
            // back. This is a small sanity check.
            if((dataIndex < 0) || (dataIndex > 254))
                continue;

            //System.out.println("index value is " + dataIndex);

            XPlaneEnumsVersion9 dataType;

            try
            {
                dataType = XPlaneEnumsVersion9.getEnumerationForValue(dataIndex);
            }
            catch(Exception e)
            {
                System.out.println(e);
                dataType = XPlaneEnumsVersion9.UNKNOWN;
            }
      
            //System.out.println("data type is " + dataType);

            switch(dataType)
            {
                case FRAME_RATE:
                    dataReader.skipBytes(32);
                    break;

                case G_LOAD:
                     Vector3Float linearAccel = deadReckoningParameter.getEntityLinearAcceleration();
                     dataReader.readFloat();//The first element is Mach number which I dont need
                     linearAccel.setZ(GRAVITY*dataReader.readFloat() - GRAVITY);
                     linearAccel.setX(GRAVITY*dataReader.readFloat());
                     linearAccel.setY(GRAVITY*dataReader.readFloat());
                     dataReader.skipBytes(16); // 32 bytes data, 16 read, 16 to skip over
                     break;

                case ANGULAR_VELOCITY:
                    Vector3Float angVel = deadReckoningParameter.getEntityAngularVelocity();
                    angVel.setX(dataReader.readFloat());
                    angVel.setY(dataReader.readFloat());
                    angVel.setZ(dataReader.readFloat());

                    //Set the angular velocity dead reckoning parameter
                    dataReader.skipBytes(20); // 12 bytes read, 32 bytes in group, 20 left to read
                    break;

                case ATTITUDE:
                    Orientation att = espdu.getEntityOrientation();
                    att.setTheta(dataReader.readFloat());
                    att.setPhi(dataReader.readFloat());
                    att.setPsi(dataReader.readFloat());
                    dataReader.skipBytes(20); // 12 bytes read, 20 bytes left
                    break;

                case LOCATION:
                    Vector3Double loc = espdu.getEntityLocation();
                    loc.setY((double) dataReader.readFloat());
                    loc.setX((double) dataReader.readFloat());
                    loc.setZ(FEET_TO_METERS*(double) dataReader.readFloat());

                    //System.out.println("x=" + loc.getX() + "," + loc.getY());
                    dataReader.skipBytes(20); // 12 bytes read, 20 bytes left
                    break;

                case VELOCITY:
                    Vector3Float linearVel = espdu.getEntityLinearVelocity();

                     // The first elements of the Xplane UDP are x,y,z locations (in
                     // xplane coordinates?). Not used.
                    dataReader.skipBytes(12);
              
                    linearVel.setY(dataReader.readFloat());
                    linearVel.setZ(dataReader.readFloat());
                    linearVel.setX(dataReader.readFloat());

                    dataReader.skipBytes(8); // 12 + 12 = 24 bytes read, 8 left out of 32
                    break;

                case ANGULAR_ACCEL:
                    dataReader.skipBytes(32);
                    break;
                    
                default:
                    //System.out.println("data type not handled, " + dataIndex + " " + dataType);
                    dataReader.skipBytes(32); // advance pointer
            } // End switch
         

        }//end for loop through data elements
        
        //Set the Dead Reckoning Parameters
        espdu.setDeadReckoningParameters(deadReckoningParameter);
          //TimeStamp the espdu
        espdu.setTimestamp(System.currentTimeMillis());
        return espdu;
        
    }//End parseXPlaneDataPacket
    
    @Override
    public String toString(){
        
        return (new String("ID: " + espdu.getEntityID().getEntity() + "\nLat " + espdu.getEntityLocation().getY() 
                + "\nlong " + espdu.getEntityLocation().getX() +"\nalt " + espdu.getEntityLocation().getZ()));
        
    }
    
    public EntityStatePdu getEspu(){
        return this.espdu;
    }
    
    
    
}//End class XPlaneUdp
