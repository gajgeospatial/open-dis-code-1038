import javax.swing.*;
import java.util.HashMap;
import java.awt.*;
import java.io.File;

//import org.xj3d.ui.awt.browser.ogl.X3DBrowserJPanel;
import org.web3d.browser.BrowserComponent;
import org.web3d.x3d.sai.*;
import org.xj3d.sai.Xj3DBrowser;

/**
 * MOVES Institute
 * Naval Postgraduate School, Monterey, CA
 * www.nps.edu
 * @author Mike Bailey
 * @since Feb 1, 2006
 * @since 10:20:08 AM
 */

/**
 * Taken from X3dLoader of AUV Workbench
 */
public class X3dManager
{
  /**
   * Interface to the browser to do stuff with it
   */
  private ExternalBrowser _x3dBrowser;

  /**
   * Scene for the main file to load
   */
  private X3DScene _mainScene;

  /**
   * The base file URL for the file to load
   */
  private String _baseFileURL;

  private X3DComponent x3d_comp;
  private JPanel parent;

  protected static final String DEADRECKON_POSITION_PROP =
      "org.web3d.vrml.renderer.common.dis.input.deadreckonPosition";

  /**
   * Property describing the dead reckon
   */
  protected static final String DEADRECKON_ROTATION_PROP =
      "org.web3d.vrml.renderer.common.dis.input.deadreckonRotation";

  //----------------------------------------------------------------------------

  /**
   * constructor
   *
   * @param parent owner
   */
  public X3dManager(JPanel parent)
  {
    this.parent = parent;

    // Turn off rotation dead reckoning
    System.setProperty(DEADRECKON_ROTATION_PROP, "false");

    // Xj3D initialization parameters
    // http://www.xj3d.org/tutorials/xj3d_application.html
    HashMap requestedParameters = new HashMap();
    requestedParameters.put("Xj3D_ConsoleShown", Boolean.TRUE);
    requestedParameters.put("Xj3D_FPSShown", Boolean.TRUE);
    requestedParameters.put("Xj3D_LocationShown", Boolean.TRUE);
    requestedParameters.put("Xj3D_LocationPosition", "top");
    requestedParameters.put("Xj3D_LocationReadOnly", Boolean.FALSE);
    requestedParameters.put("Xj3D_OpenButtonShown", Boolean.TRUE);
    requestedParameters.put("Xj3D_ReloadButtonShown", Boolean.TRUE);
    requestedParameters.put("Xj3D_ShowConsole", Boolean.FALSE);
    requestedParameters.put("Xj3D_StatusBarShown", Boolean.TRUE);

    String dir = System.getProperty("user.dir") + "/Models";

    requestedParameters.put("Xj3D_ContentDirectory", dir);

    x3d_comp = BrowserFactory.createX3DComponent(requestedParameters);
    
     _x3dBrowser = x3d_comp.getBrowser();
     ((Xj3DBrowser)_x3dBrowser).setMinimumFrameInterval(40);
      
  }


  /**
   * Get the X3D component used by this loader.
   *
   * @return The component used.
   */
  public X3DComponent getComponent()
  {
    return x3d_comp;
  }

  /**
   * Set the minimum frame cycle interval to throttle rendering.  0 is full speed.
   *
   * @param millis The number of milliseconds.
   */
  public void setMinimumFrameInterval(int millis)
  {
    // Cast for the moment, bad
    ((Xj3DBrowser) x3d_comp).setMinimumFrameInterval(millis);
  }

  /**
   * Get the X3D browser used by this loader.
   *
   * @return The browser object.
   */
  public ExternalBrowser getBrowser()
  {
    return _x3dBrowser;
  }

  /**
   * Make this component active in the frame.
   */
  public void setComponent()
  {
    JComponent x3d_panel = (JComponent) x3d_comp.getImplementation();
    parent.add("Center", x3d_panel);

    Dimension size = x3d_panel.getSize();
    x3d_panel.repaint(0, 0, (int) size.getHeight(), (int) size.getWidth());
  }

  /**
   * Returns a reference to the active scene
   */
  public X3DScene getScene()
  {
    return _mainScene;
  }

  /**
   * Load a new scene.  This will replace the currently loaded scene.
   */
  public void load(String strURL)
  {
      ((Xj3DBrowser)_x3dBrowser).setMinimumFrameInterval(40);

    try {
      _baseFileURL = (new File(strURL)).toURL().toString();
    }
    catch (Exception ex) {
      // do nothing
      ex.printStackTrace(System.out);
    }

    _mainScene = _x3dBrowser.createX3DFromURL(new String[]{_baseFileURL});
    _x3dBrowser.beginUpdate();
    _x3dBrowser.replaceWorld(_mainScene);
    _x3dBrowser.endUpdate();
  }

  /**
   * Add a scene.  This will add the scene to the currently loaded scenes.
   *
   * @param strURL The urls to load in priority order.
   * @return An object token for later deletion
   */
  public Object add(String[] strURL)
  {
    System.out.println("Adding: " + strURL[0]);
    try {
      Thread.sleep(500);
    }
    catch (Exception e) {
    }

    X3DNode inline = (X3DNode) _mainScene.createNode("Inline");
    MFString url_field = (MFString) inline.getField("url");
    url_field.setValue(strURL.length, strURL);

    inline.realize();
    _mainScene.addRootNode(inline);

    return inline;
  }

  /**
   * Remove a node from the scene.  Must provide the object
   * returned from an add call.
   *
   * @param node The object returned from an add call.
   */
  public void remove(Object node)
  {
    _mainScene.removeRootNode((X3DNode) node);
  }

  /**
   * write a error messgae to console
   *
   * @param aStr line to be written to console
   */
  public void writeErr(String aStr)
  {
    System.err.println(aStr);
  }

  /**
   * write a line to console
   *
   * @param aStr line to be written to console
   */
  public void writeLn(String aStr)
  {
    System.out.println(aStr);
  }

  /**
   * write a line to console
   */
  public void writeLn()
  {
    System.out.println("");
  }
}
