package au.edu.newcastle.SENG48002013.input.websocket;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 *
 * @author rowanburgess
 * 
 * Represents the client end of a webSocket between the Input System and Game Engine
 * Belongs to an instance of Engine, and used to pass player instructions.
 */
@ClientEndpoint
public class ClientEndpointDefinition
{
    @OnOpen
    public void onOpen(Session session)
    {
        System.out.println("Connected to endpoint: " + session.getBasicRemote());
        try
        {
            session.getBasicRemote().sendText("Hello from client");
        }
        catch (IOException ex)
        {
             Logger.getLogger(ClientEndpointDefinition.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * Check for what messages are being returned from the Game Engine!
     */
    @OnMessage
    public void onMessage(String message)
    {
        System.out.println("Message from server: " + message);
    }

}
