/*
 * Represents an XPlane entity. This operates in two modes. In Actor mode, it reflects
 * the last-reported position of the entity from the xplane application. In
 * Ghost mode, it represents a "ghost"--what this entity would be on a remote
 * system if that remote system were doing dead reckoning. Whenever the distance
 * between the two exceeds a given amount, or whenever it has been X amount of
 * time since the last update, it asks the ConnectionManager to send out a
 * new update of its position.
 * 
 */
package edu.nps.moves.xplane;

import edu.nps.moves.disenum.*;
import edu.nps.moves.dis.*;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.*;

import edu.nps.moves.deadreckoning.*;

/**
 *
 * @author $Tariq Rashid
 */
public class XPlaneActor {

    
    public static final boolean DEBUG = true;
    public static final double[] DEFAULT_INITIAL_LOCATION = {0, 0, 0};
    public final double POSITION_ERROR_TOLERANCE = 1.0;
    public final double COORDINATES_TO_METERS = 60 * 1.1515 * 1.609344 * 1000;
    public final double FEET_TO_METERS = 0.3048;
    private String description;
    private short xPlaneEntityKind = (short) EntityKind.PLATFORM.value;
    private short xPlaneDomain;
    private short category;
    private short subCategory;
    private short specificOrdinal;
    private short xPlaneCountry;
    private EntityStatePdu myEspdu;
    DIS_DR_RVW_04 deadReckoning;
  

    public static enum ActorType {
        ACTOR, GHOST
    };
    private ActorType myType;
    //This EntityData File associates aircraft types with DIS enumerations
    public static String path = "";
    public static String EntityDataFileName = "specificAir.txt"; 

    public XPlaneActor(ActorType type) {
        //Read a data array 
        myType = type;
        myEspdu = new EntityStatePdu();
        initialize();

    }//End Constuctor
    
    
    public XPlaneActor(){
        
    }
    

    public void initialize() {
        //Puts the actor at the default origin upon creation
        myEspdu.getEntityLocation().setX(DEFAULT_INITIAL_LOCATION[0]);
        myEspdu.getEntityLocation().setX(DEFAULT_INITIAL_LOCATION[1]);
        myEspdu.getEntityLocation().setX(DEFAULT_INITIAL_LOCATION[2]);

    }

    
    
    public String[] getEntityTypes() {

        StringTokenizer wordTokenizer;
        ArrayList<String> typeList= new ArrayList<String>();
        if (DEBUG) System.out.println("Attempting to Read File " + path +EntityDataFileName );
        
        try {
            if (DEBUG) System.out.println("Trying to create File reader");
            File file = new File(path+EntityDataFileName);
            if (DEBUG) System.out.println("File is " + file.getAbsolutePath());
           Scanner listFileScanner = new Scanner(file);
            String line;
            String specificAirDescription;

            if (DEBUG) System.out.println("File is" + file.getAbsolutePath());

            //Pull header off data file.
            
            line = listFileScanner.nextLine();
            line = listFileScanner.nextLine();
            
             //if (DEBUG) System.out.println("Line is" + line);
         
            while (listFileScanner.hasNext()) {
                //Grab an element from the file

                 if (DEBUG) System.out.println("Trying to read");
                try {
                    line = listFileScanner.nextLine();
           
                    
                    wordTokenizer = new StringTokenizer(line, ",");
                    specificAirDescription = wordTokenizer.nextToken();
                   if (DEBUG) System.out.println(specificAirDescription);
                    typeList.add(specificAirDescription);

                    xPlaneDomain = Short.parseShort(wordTokenizer.nextToken());
                    category = Short.parseShort(wordTokenizer.nextToken());
                    xPlaneCountry = Short.parseShort(wordTokenizer.nextToken());
                    subCategory = Short.parseShort(wordTokenizer.nextToken());
                    specificOrdinal = Short.parseShort(wordTokenizer.nextToken());


                } catch (NoSuchElementException nsee) {
                    //System.out.println("Reached end of file: " + EntityDataFileName);
                }
            }
            listFileScanner.close();
        } catch (FileNotFoundException e) {
        }//end catch
        
        return (typeList.toArray(new String[typeList.size()]));
    } //End getEntityTypes
    
    
    
    /**
     * If passed a String name will attempt to set the DIS Enumerations based on what
     * is read from the Entity Data File
     * @param desc
     */
    public void setModelEnumerations(String desc) {

        String descFromFile;
        int count = 0;
        StringTokenizer wordTokenizer;


        System.out.println(desc);



        try {
            //Open the file that I will read from
            File file = new File(EntityDataFileName);
            Scanner listFileScanner = new Scanner(file);
            String line;

            //Pull header off data file.
            line = listFileScanner.nextLine();

            while (listFileScanner.hasNextLine()) {
                //Grab an element from the file


                try {
                    line = listFileScanner.nextLine();
                    //System.out.println(line);
                    //If the line does not match the pattern skip tokenizing

                    wordTokenizer = new StringTokenizer(line, ",");
                    descFromFile = wordTokenizer.nextToken();
                    System.out.println(descFromFile);
                    if (desc.contains(descFromFile)) {
                        System.out.println("Found description in file: " + descFromFile);
                        this.xPlaneDomain = Short.parseShort(wordTokenizer.nextToken());
                        this.category = Short.parseShort(wordTokenizer.nextToken());
                        this.xPlaneCountry = Short.parseShort(wordTokenizer.nextToken());
                        this.subCategory = Short.parseShort(wordTokenizer.nextToken());
                        this.specificOrdinal = Short.parseShort(wordTokenizer.nextToken());
                    }

                } catch (NoSuchElementException nsee) {
                    //System.out.println("Reached end of file: " + EntityDataFileName);
                }
            }
            listFileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    } //End
    
    
    
          /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
       XPlaneActor actor = new XPlaneActor();
       
       System.out.println(actor.getEntityTypes().length); 
     
    }
    
    public EntityType getEntityType() {
        EntityType et = new EntityType();

        et.setCategory(category);
        et.setDomain(xPlaneDomain);
        et.setSpecific(specificOrdinal);
        et.setCountry(xPlaneCountry);
        et.setSubcategory(subCategory);
        et.setEntityKind(xPlaneEntityKind);

        return et;
    }

    public void updateActor(EntityStatePdu espdu) {
        myEspdu = espdu;
    }

    /**Takes an espdu and gets the data needed for Sheldon Snyders
     * Dead reckoning algorith
     * @param pdu
     * @return
     */
    public double[] allDis(EntityStatePdu pdu) {

        double[] values = new double[15];

        values[0] = pdu.getEntityLocation().getX();
        values[1] = pdu.getEntityLocation().getY();
        values[2] = pdu.getEntityLocation().getX();

        values[3] = pdu.getEntityOrientation().getPsi();
        values[4] = pdu.getEntityOrientation().getTheta();
        values[5] = pdu.getEntityOrientation().getPhi();

        values[6] = pdu.getEntityLinearVelocity().getX();
        values[7] = pdu.getEntityLinearVelocity().getY();
        values[8] = pdu.getEntityLinearVelocity().getZ();

        values[9] = pdu.getDeadReckoningParameters().getEntityLinearAcceleration().getX();
        values[10] = pdu.getDeadReckoningParameters().getEntityLinearAcceleration().getY();
        values[11] = pdu.getDeadReckoningParameters().getEntityLinearAcceleration().getZ();

        values[12] = pdu.getDeadReckoningParameters().getEntityAngularVelocity().getX();
        values[13] = pdu.getDeadReckoningParameters().getEntityAngularVelocity().getY();
        values[14] = pdu.getDeadReckoningParameters().getEntityAngularVelocity().getZ();



        return values;
    }//End allDis

    /**
     * 
     * @param espdu
     * 
     * Initializes the internal dead reckoning model
     * 
     * For an actor The state is updated each
     * time an Xplane UDP is received.
     * 
     * If this is a ghost then we deadReckoningIsGood the ghosts position to the last
     * recieved Xplane UDP using the deadReckoningIsGood method.  
     */
    public void initDeadReckoning(EntityStatePdu espdu) {

    //Ok this is not working

        deadReckoning = new DIS_DR_RVW_04();

        /**Make a param allDis - 15 double percisions that match
         * The specifications in Sheldon Snyders 
         * edu.nps.moves.deadReckoning.DIS_DeadReckoning.java
         */
        try {
            deadReckoning.setNewAll(allDis(espdu));

        } catch (Exception e) {
            System.out.println("Failed to initialize dead reckoning");
        }

        
        System.out.println("Starting DR");
        
        java.lang.Thread runDeadReckoning;
          //Instantiate a Thread
        runDeadReckoning = new java.lang.Thread(deadReckoning);

        //Start the thread
        runDeadReckoning.start();
        
    }//End 
    
    /**
     * 
     * @param pdu
     * @param deltaT
     * @return
     */
    public Vector3Double secondOrderMotion(EntityStatePdu pdu, float deltaT) {

        Vector3Double newLoc = new Vector3Double();
    
        newLoc.setX((myEspdu.getEntityLocation().getX()*this.COORDINATES_TO_METERS
                + myEspdu.getEntityLinearVelocity().getX() * deltaT 
                + myEspdu.getDeadReckoningParameters().getEntityLinearAcceleration().getX() * deltaT * deltaT)/COORDINATES_TO_METERS);

        newLoc.setY((myEspdu.getEntityLocation().getY()*this.COORDINATES_TO_METERS
                + myEspdu.getEntityLinearVelocity().getY() * deltaT 
                + myEspdu.getDeadReckoningParameters().getEntityLinearAcceleration().getY() * deltaT * deltaT)/COORDINATES_TO_METERS);

        newLoc.setZ(myEspdu.getEntityLocation().getZ()
                + myEspdu.getEntityLinearVelocity().getZ() * deltaT 
                + myEspdu.getDeadReckoningParameters().getEntityLinearAcceleration().getZ() * deltaT * deltaT);

        return newLoc;
    }//end secondOrderMotion
    
    
    public float calculateFrameRate(EntityStatePdu pdu){
         long deltaT = pdu.getTimestamp() - myEspdu.getTimestamp();
        return  1/(deltaT * 0.001f);    
    }
    
    
    
    
/**
 * 
 * @param pdu
 * @param deltaT
 * @return
 */
    public Vector3Float accelerate(EntityStatePdu pdu, float deltaT){
         Vector3Float newVel = new Vector3Float();
         
        newVel.setX(myEspdu.getEntityLinearVelocity().getX()+ myEspdu.getDeadReckoningParameters().getEntityLinearAcceleration().getX() * deltaT);

        newVel.setY(myEspdu.getEntityLinearVelocity().getY() + myEspdu.getDeadReckoningParameters().getEntityLinearAcceleration().getY() * deltaT);

        newVel.setZ(myEspdu.getEntityLinearVelocity().getZ() + myEspdu.getDeadReckoningParameters().getEntityLinearAcceleration().getZ() * deltaT);

        return newVel;
       
    }//end accelerate
    
    
    
    
    
    //Implement my own simple dead reckoning until I sit down wish sheldon
    /**
     * 
     * @param pdu
     */
    public void deadReckon(EntityStatePdu pdu){
        long deltaT = pdu.getTimestamp() - myEspdu.getTimestamp();
        float timeStep = deltaT * 0.001f;
        
        //System.out.println("Delta T" + timeStep);
        
        myEspdu.setEntityLocation(this.secondOrderMotion(pdu, timeStep));
        myEspdu.setEntityLinearVelocity(this.accelerate(pdu, timeStep));
        
        myEspdu.setTimestamp(pdu.getTimestamp());
        
    }//End deadReckon
    
    
    /**
     * 
     * @param espdu1
     * @param espdu2
     * @return double
     * 
     * Just gives the straight line distance between the locations in the two espdu
     */
    public double getDistance(EntityStatePdu espdu1, EntityStatePdu espdu2) {

        Vector3Double vector3 = new Vector3Double();


        vector3.setX(espdu1.getEntityLocation().getX() - espdu2.getEntityLocation().getX());
        vector3.setY(espdu1.getEntityLocation().getY() - espdu2.getEntityLocation().getY());
        vector3.setZ(espdu1.getEntityLocation().getZ() - espdu2.getEntityLocation().getZ());

        double distance = Math.sqrt(vector3.getX() * vector3.getX() + vector3.getY() * vector3.getY() + vector3.getZ() * vector3.getZ());

        return distance;
    }//End getDistance
    
    /**
     * Gives you the distance between two points on a spherical approximation
     * of the earth
     * 
     * 
     * @param espdu1
     * @param espdu2
     * @param unit
     * @return
     */
      public static double getGeoSurfaceDistance(EntityStatePdu espdu1, EntityStatePdu espdu2, char unit) {
        double lat1 = espdu1.getEntityLocation().getX();
        double lon1 = espdu1.getEntityLocation().getZ();
        double lat2 = espdu2.getEntityLocation().getX();
        double lon2 = espdu2.getEntityLocation().getZ();

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }


        return new Double(dist);
    }//End getGeoSurfaceDistance
      
      
       /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    /**
     * 
     * @param espdu
     * @return boolean
     * 
     * Returns true if this.myEspdu.getEntityLocation is within the 
     * ERROR_TOLERANCE distance of of espdu.getEntityLocation
     * 
     */
    public boolean deadReckoningIsGood(EntityStatePdu espdu) {

        if (getDistance(this.myEspdu, espdu) < this.POSITION_ERROR_TOLERANCE) {

            return true;
        }


        return false;
    }//End deadReckoningIsGood
}
