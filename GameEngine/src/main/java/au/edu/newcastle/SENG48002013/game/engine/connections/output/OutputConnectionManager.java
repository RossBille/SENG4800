package au.edu.newcastle.SENG48002013.game.engine.connections.output;

import au.edu.newcastle.SENG48002013.game.engine.model.IGameOutput;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Endpoint to send output to the output device
 *
 * @author rossbille
 */
@ServerEndpoint("/output")
public class OutputConnectionManager {

    private static final int ALLOWED_CONNECTIONS = 1;
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    /**
     * Broadcast to everyone connected to this socket
     *
     * @param gameOutput
     * @throws IOException if the JSON mapping fails
     */
    public static void sendOutput(IGameOutput gameOutput) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String message = mapper.writeValueAsString(gameOutput.getOutputObjects());
        for (Session peer : peers) {
            try {
                peer.getBasicRemote().sendText(message);
            } catch (IOException ex) {
                //client has errored, out of our control
            }
        }

        //alternate
        //create objects to stream 
		/*
         String[] objects = new String[gameOutput.getOutputObjects().length];
         for (int i = 0; i < objects.length; i++)
         {
         objects[i] = mapper.writeValueAsString(gameOutput.getOutputObjects()[i]);
         }
         //stream to peers
         for (Session peer : peers)
         {
         for (String str : objects)
         {
         peer.getBasicRemote().sendText(str);
         }
         }
         */
        //problem, unless threaded there will be a longer delay between updating peers
    }

    @OnOpen
    public void onOpen(Session peer) throws IOException {
        //check if we have reached the max number of connections
        if (currentPeers() < getAllowedConnections()) {
            peers.add(peer);
            peer.getBasicRemote().sendText("{ \"hello\": \"world\"}");
        } else {
            //cancel handshake
            peer.close(new CloseReason(CloseCodes.VIOLATED_POLICY, "Too many output devices connected"));
        }
    }

    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);
        //check if there are any output devices left
        if (currentPeers() <= 0) {
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
    public void onMessage(String message) {
        //log the message

        //log the error
        throw new IllegalAccessError("Output device shouldnt be talking to the engine");
    }

    public int currentPeers() {
        return peers.size();
    }

    public int getAllowedConnections() {
        return ALLOWED_CONNECTIONS;
    }
}
