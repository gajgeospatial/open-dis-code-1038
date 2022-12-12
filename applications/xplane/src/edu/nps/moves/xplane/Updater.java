/*
 * Interface for writing ESPDU information to various
 * media, such as XMPP, multicst, or KML (google earth).
 *
 * @author DMcG
 */

package edu.nps.moves.xplane;

import edu.nps.moves.dis.*;

/**
 *
 * @author mcgredo
 */
public interface Updater
{
  public void sendEspdu(EntityStatePdu espdu);
  public boolean isOn();
  public void setOn(boolean state);
}
