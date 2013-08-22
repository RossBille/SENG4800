package au.edu.newcastle.SENG48002013.game.engine.connections;

import java.io.IOException;
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
 * Endpoint to send output to the output device
 * @author rossbille
 */
@ServerEndpoint("/output")
public class OutputConnectionManager
{
    private final int ALLOWED_CONNECTIONS;
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    public OutputConnectionManager()
    {
        ALLOWED_CONNECTIONS = 1;//read from config on startup 
    }
    /**
     * Broadcast to everyone connected to this socket
     * @param message the message to be sent to all peers
     * @throws IOException 
     */
    public void broadcast(String message) throws IOException
    {
        //might want to do some securty checking
        for(Session peer: peers)
        {
            peer.getBasicRemote().sendText(message);
        }
    }
    @OnOpen
    public void onOpen(Session peer) throws IOException
    {
        //check for correct key
        
        //check if we have reached the max number of connections
        if(currentPeers() < getAllowedConnections())
        {
            peers.add(peer);
        }else{
            //cancel handshake
        }
    }

    @OnClose
    public void onClose(Session peer)
    {
        peers.remove(peer);
        //check if there are any output devices left
        if(currentPeers() <= 0)
        {
            //shutdown the game
        }
    }

    /*
    @OnError
    public void onError()
    {
        //log the error
        //try and handle
    }
	*/
    @OnMessage
    public void onMessage(String message)
    {
        //log the message
        
        //log the error
        throw new IllegalAccessError("Output device shouldnt be talking to the engine");
    }

    public int currentPeers()
    {
        return peers.size();
    }

    public int getAllowedConnections()
    {
        return ALLOWED_CONNECTIONS;
    }    
}
