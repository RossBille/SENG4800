package au.edu.newcastle.SENG48002013.input;

import au.edu.newcastle.SENG48002013.instructions.BaseInstruction;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Ross Bille
 */
@ServerEndpoint(value = "/endpoint", configurator = SocketConfig.class)
public class Engine {

    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    private HttpSession httpSession;
    private SecurityManager secMan;
    private int playerID = 0;

    @OnMessage
    public void onMessage(Session session, String message) 
    {
        System.out.println(message);
        //get json parser
        ObjectMapper mapper = new ObjectMapper();
        BaseInstruction instruction;
        //process the input

        if (message.charAt(0) == '$') // FIX THIS ASAP!!
        {
            String token = message.substring(1);
            System.out.println("Received Token: " + token);

            if (secMan.checkToken(token)) //add player to game
            {
                peers.add(session);
            }
        } 
        
        else 
        {
            try 
            {
                instruction = mapper.readValue(message, BaseInstruction.class);
                System.out.println("STATS");
                System.out.println(instruction.getDirection());
                System.out.println(instruction.getUpdated());
                System.out.println(instruction.getId());
            } 
            
            catch (IOException ex) 
            {
                //actually do something with the error
                ex.printStackTrace();
            }
        }
    }

    @OnOpen
    public void onOpen(Session peer, EndpointConfig config) {
        this.httpSession = (HttpSession) config.getUserProperties()
                .get(HttpSession.class.getName());

        secMan = (SecurityManager) httpSession.getServletContext().getAttribute("securityManager");
    }

    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);
    }

    public int currentPeers() {
        return peers.size();
    }
}
