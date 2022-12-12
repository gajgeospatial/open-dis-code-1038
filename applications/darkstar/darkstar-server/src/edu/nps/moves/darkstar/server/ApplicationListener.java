

package edu.nps.moves.darkstar.server;

import java.nio.ByteBuffer;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.sgs.app.AppContext;
import com.sun.sgs.app.ClientSession;
import com.sun.sgs.app.ClientSessionListener;
import com.sun.sgs.app.ManagedReference;

/**
 * A ClientListener object that is the default, direct communications listener
 * for the server-side. Other channel listeners may implement DIS, but this is
 * more or less generic. You can use it to handle meta-messages and the like.
 * 
 * @author mcgredo
 */
public class ApplicationListener implements Serializable, ClientSessionListener
{
    private static final long SerialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(ApplicationListener.class.getName());

    private final ManagedReference<ClientSession> sessionReference;
    private final String sessionName;

    public ApplicationListener(ClientSession clientSession)
    {
        if(clientSession == null)
        {
            throw new NullPointerException("null clientSesision in DisClientListener; DarkstarServer is apparently returning null in loggedIn()");
        }
        sessionReference = AppContext.getDataManager().createReference(clientSession);
        sessionName = clientSession.getName();

        logger.log(Level.INFO, "Created new DisClientListener named " + sessionName);
    }

    protected ClientSession getSession()
    {
        return sessionReference.get();
    }

    public void receivedMessage(ByteBuffer message)
    {
        logger.log(Level.INFO, "Got messge from client " + sessionName);
        System.out.println("got message");
        ClientSession session = this.getSession();
        session.send(message);
        logger.log(Level.INFO, "echoed message back");
    }

    public void disconnected(boolean graceful)
    {
        if(graceful)
            logger.log(Level.INFO, "Graceful disconnect");
        else
            logger.log(Level.INFO, "non-graceful disconnect");
    }
}
