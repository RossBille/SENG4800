/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.input.websocket;

import java.io.IOException;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

/**
 *
 * @author rowanburgess
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
            ex.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message)
    {
        System.out.println("Message from server: " + message);
    }

}
