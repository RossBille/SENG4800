package au.edu.newcastle.SENG48002013.game.engine.connections;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * Web socket endpoint Opens up a socket between input engine and game engine
 *
 * @author rossbille
 */
@ServerEndpoint("/MessageManager")
public class MessageManager
{

    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    private final int ALLOWED_CONNECTIONS = 1;
    
    @OnOpen
    public void onOpen(Session peer)
    {
        //check that they have a correct key to act as input to the engine
        
        //check the current number of connections
        if(currentPeers() < ALLOWED_CONNECTIONS)
        {
            peers.add(peer);
        }else{
            //log the access
            //throw error
            throw new IllegalAccessError(String.format("Only %d connection(s) at a time", ALLOWED_CONNECTIONS));
        }
    }

    @OnClose
    public void onClose(Session peer)
    {
        //alert front end that all input has been lost
    }

    /*@OnError
    public void onError()
    {
        //log error
        
        //try to handle
    }
*/
    @OnMessage
    public void onMessage(String Message)
    {
        //process the input
    }
    
    public int currentPeers()
    {
        return peers.size();
    }
}
