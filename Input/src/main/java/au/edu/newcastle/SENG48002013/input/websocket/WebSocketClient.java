package au.edu.newcastle.SENG48002013.input.websocket;

import au.edu.newcastle.SENG48002013.input.Engine;
import java.io.IOException;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 *
 * @author rowanburgess
 */
public class WebSocketClient
{
    private Session session;
    WebSocketContainer container;

    /**
	 *
	 * @param remote
	 */
	public void start(String remote)
    {
        this.container = ContainerProvider.getWebSocketContainer();
        try
        {
            session = container.connectToServer(ClientEndpointDefinition.class, URI.create(remote));
        }
        catch (DeploymentException ex)
        {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
	 *
	 * @return
	 */
	public Session getSession()
    {
        return this.session;
    }
}
