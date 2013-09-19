package au.edu.newcastle.SENG48002013.game.engine.connections.output;

import au.edu.newcastle.SENG48002013.game.engine.model.IGameOutput;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.OnClose;
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

    private static final int ALLOWED_CONNECTIONS = 5;
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    /**
     * Broadcast to everyone connected to this socket
     *
     * @param gameOutput
     * @throws IOException if the JSON mapping fails
     */
    public static void sendOutput(IGameOutput gameOutput) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        final String message = mapper.writeValueAsString(gameOutput.getOutputObjects());
        for (Session peer : peers) {
            Thread thread = new Runner(peer, message);
            thread.start();
        }
    }

    @OnOpen
    public void onOpen(Session peer) throws IOException {
        System.out.println("connections: " + currentPeers());

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
        System.out.println("connections: " + currentPeers());
    }

    public int currentPeers() {
        return peers.size();
    }

    public int getAllowedConnections() {
        return ALLOWED_CONNECTIONS;
    }

    private static class Runner extends Thread {

        private Session peer;
        private String message;
        private boolean started = false;

        public Runner(Session peer, String message) {
            this.peer = peer;
            this.message = message;
        }

        @Override
        public void run() {
            if (!started) {
                try {
                    started = true;
                    peer.getBasicRemote().sendText(message);
                } catch (IOException ex) {
                    try {
                        peer.close(new CloseReason(CloseCodes.PROTOCOL_ERROR, "Unable to write"));
                    } catch (IOException ex1) {
                        Logger.getLogger(OutputConnectionManager.class.getName()).log(Level.SEVERE, "Client isnt writable", ex1);
                    }
                }
            }
        }
    }
}
