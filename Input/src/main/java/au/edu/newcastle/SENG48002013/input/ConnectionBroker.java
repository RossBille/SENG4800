package au.edu.newcastle.SENG48002013.input;

import au.edu.newcastle.seng48002013.instructions.BaseInstruction;
import javax.inject.Inject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Initial entry point for clients wanting to connect to the system. Receives a
 * registration request from the client, determines if it should be granted.
 */
@ServerEndpoint("/entry")
public class ConnectionBroker 
{

    @Inject
    SecurityManager secManager; //requires review, not working yet

    @OnOpen
    public void onOpen(Session peer) 
    {
        System.out.println("Inside the onOpen method");
    }

    @OnMessage
    public void onMessage(Session s, String message) 
    {
        System.out.println(message);
        System.out.println("Session ID" + s.getId());

        ObjectMapper mapper = new ObjectMapper();
        BaseInstruction instruction;

        try 
        {
            instruction = mapper.readValue(message, BaseInstruction.class);
            System.out.println("STATS");
            System.out.println(instruction.getId());
            
            if (true) //if there is room for another player
            {
                //have a token generated
                //send the token back to the client 
                s.getAsyncRemote().sendText("Reply From Connection Broker!");
            }
        } 
        
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }

    }
}
