import edu.nps.moves.dis.*;
import edu.nps.moves.disutil.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Hashtable;

/**
 * something sleazy I hacked together fast. Open up the vrml file sliderExample.x3dv in
 * an M10 version of xj3d. Run this program and move the sliders around. See the
 * object in the scene translate and rotate. The top three sliders control the
 * x, y, and z, respectively; the bottom three sliders control psi, theta, and
 * phi, respectively.<p>
 *
 * @author DMcG
 * @author modified by Al Shaffer
 * @author further modified by Mike Bailey / XJ3D integration
 */

public class SliderController extends JFrame
{
  // Translation along each axis, controlled by sliders
  JSlider xAxisTranslation;
  JSlider yAxisTranslation;
  JSlider zAxisTranslation;

  // Orientation
  JSlider psi;
  JSlider theta;
  JSlider phi;

  /**
   * Single, shared ESPDU--created once, position & orientation by the various sliders
   */
  EntityStatePdu espdu;

  /**
   * Socket for sending packets
   */
  MulticastSocket socket = null;

  /**
   * Destination group
   */
  public static final String MULTICAST_GROUP = "239.1.2.3";

  /**
   * Destination port
   */
  public static final int PORT = 62040;

  /**
   * Object responsible for marshalling out PDUs
   */
  PduFactory marshaller = new PduFactory();

  /**
   * Multicast address we use
   */
  InetAddress address;
  
  /** Timestamp, with is in this case simply a monotonically increasing long */
  long timestamp = 0;

  /**
   * Constants
   */
  public static final float PI = (float) 3.14159;

  /**
   * Constructor; set up various GUI elements, open socket
   */
  public SliderController(String pFrameName)
  {
    super(pFrameName);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    PduFactory pduFactory = new PduFactory();

    // Setup the main panel
    JPanel general = new JPanel();
    general.setLayout(new BoxLayout(general, BoxLayout.X_AXIS));

    // Create the espdu
    espdu = new EntityStatePdu();

    // Set the entity ID, the unique identifer of an entity in the world.
    EntityID id = espdu.getEntityID();   // Globally unique identifier for the entity
    id.setSite(0);
    id.setApplication(1);
    id.setEntity(2);
    espdu.setEntityID(id);

    // "default" length of an entity state PDU with no articulation parameters--144 bytes
    espdu.setLength(144);

    // create multicast socket, join group
    try 
    {
      socket = new MulticastSocket(1234);
      socket.joinGroup(InetAddress.getByName(MULTICAST_GROUP));
      address = InetAddress.getByName(MULTICAST_GROUP);
    }
    catch (Exception e) 
    {
      System.out.println("Can't create socket");
    }

    // Set up a panel to hold the slider subpanels
    JPanel sliderPanel = new JPanel();
    sliderPanel.setLayout(new GridLayout(2, 1));

    // Set up a panel to hold the three translation sliders
    JPanel translationPanel = new JPanel();
    translationPanel.setLayout(new BoxLayout(translationPanel, BoxLayout.Y_AXIS));
    JLabel translationLabel = new JLabel("<html><b><i>Open-DIS Translation Panel</i></b></html>", JLabel.CENTER);
    translationLabel.setAlignmentX(CENTER_ALIGNMENT);
    translationPanel.add(translationLabel);

    // Set up x-axis translation slider. The anonymous class calls the method that
    // changes the espdu values.
    xAxisTranslation = new JSlider(-100, 100, 0);
    xAxisTranslation.setBorder(BorderFactory.createTitledBorder("X-Axis Translation (meters)"));
    xAxisTranslation.setMajorTickSpacing(25);
    xAxisTranslation.setMinorTickSpacing(5);
    xAxisTranslation.setPaintTicks(true);
    xAxisTranslation.setPaintLabels(true);
    xAxisTranslation.setToolTipText("X-Axis translation slider");
    xAxisTranslation.addChangeListener(new ChangeListener()
    {
      public void stateChanged(ChangeEvent e)
      {
        SliderController.this.xAxisChanged(e);
      }
    });
    translationPanel.add(xAxisTranslation);

    // y-axis translation slider
    yAxisTranslation = new JSlider(-100, 100, 0);
    yAxisTranslation.setBorder(BorderFactory.createTitledBorder("Y-Axis Translation (meters)"));
    yAxisTranslation.setMajorTickSpacing(25);
    yAxisTranslation.setMinorTickSpacing(5);
    yAxisTranslation.setPaintTicks(true);
    yAxisTranslation.setPaintLabels(true);
    yAxisTranslation.setToolTipText("Y-Axis translation slider");
    yAxisTranslation.addChangeListener(new ChangeListener()
    {
      public void stateChanged(ChangeEvent e)
      {
        SliderController.this.yAxisChanged(e);
      }
    });
    translationPanel.add(yAxisTranslation);

    // z-axis translation slider
    zAxisTranslation = new JSlider(-100, 100, 0);
    zAxisTranslation.setBorder(BorderFactory.createTitledBorder("Z-Axis Translation (meters)"));
    zAxisTranslation.setMajorTickSpacing(25);
    zAxisTranslation.setMinorTickSpacing(5);
    zAxisTranslation.setPaintTicks(true);
    zAxisTranslation.setPaintLabels(true);
    zAxisTranslation.setToolTipText("Z-Axis translation slider");
    zAxisTranslation.addChangeListener(new ChangeListener()
    {
      public void stateChanged(ChangeEvent e)
      {
        SliderController.this.zAxisChanged(e);
      }
    });
    translationPanel.add(zAxisTranslation);

    // Add the translation subpanel to the slider panel
    sliderPanel.add(translationPanel, BorderLayout.NORTH);

    // Set up rotation sliders, same deal.
    JPanel rotationPanel = new JPanel();
    rotationPanel.setLayout(new BoxLayout(rotationPanel, BoxLayout.Y_AXIS));
    JLabel rotationLabel = new JLabel("<html><b><i>DIS-XML Rotation Panel</i></b></html>", JLabel.CENTER);
    rotationLabel.setAlignmentX(CENTER_ALIGNMENT);
    rotationPanel.add(rotationLabel);

    //Create the label table for radians on rotation sliders
    Hashtable labelTable = new Hashtable();
    labelTable.put(new Integer(0), new JLabel("0"));
    labelTable.put(new Integer(4), new JLabel("Pi"));
    labelTable.put(new Integer(8), new JLabel("2*Pi"));

    // Rotation is in radians, runs from 0 to 2*pi. Slider increments are pi/4
    psi = new JSlider(0, 8, 0);
    psi.setBorder(BorderFactory.createTitledBorder("Psi Rotation (radians)"));
    psi.setMajorTickSpacing(1);
    psi.setPaintTicks(true);
    psi.setLabelTable(labelTable);
    psi.setPaintLabels(true);
    psi.setToolTipText("Entity orientation--psi euler angle ");
    psi.addChangeListener(new ChangeListener()
    {
      public void stateChanged(ChangeEvent e)
      {
        SliderController.this.psiChanged(e);
      }
    });
    rotationPanel.add(psi);

    // same deal for theta
    theta = new JSlider(0, 8, 0);
    theta.setBorder(BorderFactory.createTitledBorder("Theta Rotation (radians)"));
    theta.setMajorTickSpacing(1);
    theta.setPaintTicks(true);
    theta.setLabelTable(labelTable);
    theta.setPaintLabels(true);
    theta.setToolTipText("Entity orientation--theta euler angle ");
    theta.addChangeListener(new ChangeListener()
    {
      public void stateChanged(ChangeEvent e)
      {
        SliderController.this.thetaChanged(e);
      }
    });
    rotationPanel.add(theta);

    // and for phi
    phi = new JSlider(0, 8, 0);
    phi.setBorder(BorderFactory.createTitledBorder("Phi Rotation (radians)"));
    phi.setMajorTickSpacing(1);
    phi.setPaintTicks(true);
    phi.setLabelTable(labelTable);
    phi.setPaintLabels(true);
    phi.setToolTipText("Entity orientation--phi euler angle ");
    phi.addChangeListener(new ChangeListener()
    {
      public void stateChanged(ChangeEvent e)
      {
        SliderController.this.phiChanged(e);
      }
    });
    rotationPanel.add(phi);

    // Add the rotation subpanel to the bottom of slider panel
    sliderPanel.add(rotationPanel, BorderLayout.SOUTH);
    Dimension d = sliderPanel.getPreferredSize();
    System.out.println("height: "+d.height+" w: "+d.width);
    sliderPanel.setMinimumSize(new Dimension(d.width+100,0));
    sliderPanel.setPreferredSize(new Dimension(d.width+100,d.height));
    // Add slider panel to main panel
    general.add(sliderPanel);

    // This part is screwing: the xj3d browser component i/f is sorta screwy
    // Create a panel for viewing Xj3D scene
    JPanel viewingPanel = new JPanel(new BorderLayout());
    viewingPanel.setPreferredSize(new Dimension(750, 0));
    viewingPanel.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
    viewingPanel.setOpaque(false);

    final X3dManager xman = new X3dManager(viewingPanel);
    JPanel pnlBottom = new JPanel(new BorderLayout());
    viewingPanel.add(pnlBottom, BorderLayout.SOUTH);
    pnlBottom.addAncestorListener(new AncestorListener()
    {
      public void ancestorAdded(AncestorEvent evt)
      {
        xman.setComponent();
      }

      public void ancestorMoved(AncestorEvent evt)
      {
      }

      public void ancestorRemoved(AncestorEvent evt)
      {
      }
    });

    general.add(viewingPanel);
    this.getContentPane().add(general);

    new Thread(new Runnable()
    {
      public void run()
      {
        // Wait till the main frame is visible.
        // 3D scenes cannot be loaded till they are visibile for the first time

        Component comp = (Component) xman.getComponent().getImplementation();

        while ((!comp.isShowing()) || (!comp.isValid()) || (!comp.isDisplayable())) {
          try {
            Thread.sleep(100);
          }
          catch (Exception e) {
          }
        }
        xman.load("src/sliderExample.x3dv");
      }
    }).start();
  }

  /**
   * The x-axis has changed; set the new espdu location and send out an update.
   * This method is called whenever the x-slider is moved.
   */
  public void xAxisChanged(ChangeEvent e)
  {
    //System.out.println("Sending x-axis change" + xAxisTranslation.getValue());

    Vector3Double location = espdu.getEntityLocation();
    location.setX((double) xAxisTranslation.getValue());

    //System.out.println(espdu);
    this.sendPdu();
  }

  public void yAxisChanged(ChangeEvent e)
  {
    Vector3Double location = espdu.getEntityLocation();
    location.setY((double) yAxisTranslation.getValue());
    this.sendPdu();
  }

  public void zAxisChanged(ChangeEvent e)
  {
    Vector3Double location = espdu.getEntityLocation();
    location.setZ((double) zAxisTranslation.getValue());
    this.sendPdu();
  }

  public void psiChanged(ChangeEvent e)
  {
    Orientation orientation = espdu.getEntityOrientation();
    orientation.setPsi((float) psi.getValue() * PI / 4);
    this.sendPdu();
  }

  public void thetaChanged(ChangeEvent e)
  {
    Orientation orientation = espdu.getEntityOrientation();
    orientation.setTheta((float) theta.getValue() * PI / 4);
    this.sendPdu();
  }

  public void phiChanged(ChangeEvent e)
  {
    Orientation orientation = espdu.getEntityOrientation();
    orientation.setPhi((float) phi.getValue() * PI / 4);
    this.sendPdu();
  }

  /**
   * Sends a PDU out over the multicast socket.
   */
  public void sendPdu()
  {
    DatagramPacket packet;
    byte buffer[];

    try {

      // write out the Java espdu object to DIS format. 

      espdu.setTimestamp(timestamp++);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      DataOutputStream dos = new DataOutputStream(baos);
      espdu.marshal(dos);
      buffer = baos.toByteArray();

      /*
      System.out.println("Got marshalled packet of length " + buffer.length);
      System.out.println("location: " + espdu.getEntityLocation().getX() + " " + espdu.getEntityLocation().getY() + " "  + espdu.getEntityLocation().getZ());
      System.out.println("Timestamp: " + espdu.getPduHeader().getTimestamp());
      System.out.println("ID: " + espdu.getEntityID().getApplication() + " " + espdu.getEntityID().getSite() + " " + espdu.getEntityID().getEntity());
      */

      packet = new DatagramPacket(buffer, buffer.length, address, PORT);
      socket.send(packet);
    }
    catch (Exception e) {
      System.out.println("drat.");
    }
  }

  public static void main(String args[])
  {
    SliderController slider = new SliderController("Slider Controls");

    slider.setSize(1200, 800);
    slider.setLocation(100,100);
    slider.setVisible(true);
    slider.pack();          // this resize-after-visible is required for xj3d

  }
}