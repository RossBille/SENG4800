package au.edu.newcastle.SENG48002013.game.engine.connections.input;

import au.edu.newcastle.SENG48002013.messages.PlayerControlMessage;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.vecmath.Vector2d;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Web socket endpoint Opens up a socket between input engine and game engine
 *
 * @author rossbille
 */
@ServerEndpoint("/MessageManager")
public class MessageManager {

    private static long id;
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    private final int ALLOWED_CONNECTIONS = 1;

    @OnOpen
    public void onOpen(Session peer) {
        //check that they have a correct key to act as input to the engine

        //check the current number of connections
        if (currentPeers() < ALLOWED_CONNECTIONS) {
            peers.add(peer);
        } else {
            //log the access
            //throw error
            throw new IllegalAccessError(String.format("Only %d connection(s) at a time", ALLOWED_CONNECTIONS));
        }
    }

    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);
        if (peers.size() <= 0) {
            //TODO: decide what to do when input drops out						
        }
    }

    @OnMessage
    public void onMessage(String Message) throws IOException {
        //process the input
        System.out.println("GAME ENGINE" + Message);
        ObjectMapper mapper = new ObjectMapper();
        PlayerControlMessage pcm = mapper.readValue(Message, PlayerControlMessage.class);
        Input input = new Input(getNextId());
        //convert 3d vector to 2d vector
        Vector2d vector = new Vector2d(pcm.getDirection().x, pcm.getDirection().y);
        input.setValue(vector);
        //use the 3rd parameter to indicate position or vector
        //1 means vector, 0 means position
        input.setPosition(pcm.getDirection().z == 0);
        InputManager.addInput(input);
    }

    private long getNextId() {
        return ++id;
    }

    public int currentPeers() {
        return peers.size();
    }
}
