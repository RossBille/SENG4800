package au.edu.newcastle.SENG48002013.input;

import au.edu.newcastle.SENG48002013.messages.PlayerControlMessage;
import au.edu.newcastle.seng48002013.instructions.BaseInstruction;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author Rowan
 * This class is where end users are referred to after being sued with a token
 * from their contact with the ConnectionBroker. 
 * 
 * Basic Workflow
 * 
 * 1) Client connects using the websocket URL provided by ConnectionBroker. 
 *    The OnOpen method adds the client to a list of currently connected, but 
 *    not yet validated clients. Engine sends a response message of "token" back
 *    to the client, indicating they should provide their token
 * 
 * 2) Client sends back their token, triggering the OnMessage method. If the 
 *    message source is an unverified client, then this message is assumed to
 *    be a token, and is deserialized and verified.
 * 
 * 3) Once the token has been successfully verified (using the Security Manager)
 *    the client is added to the list of known clients. From here they may begin
 *    sending instructions to the engine. The Engine relays these instructions
 *    to the Game Engine using its own "internal" websocket called "engineChannel"
 * 
 * WebSocket session ID's are used as player ID's when referring to a player
 * passing instructions into the Game Engine. This enables the player to be 
 * identified quickly and unambiguously between the input and game engines. 
 */

@ServerEndpoint("/endpoint")
@WebListener
public class Engine implements ServletContextListener
{
    private final static Logger log = Logger.getLogger(Engine.class.getName());
    private static Set<Session> known = Collections.synchronizedSet(new HashSet<Session>());
    private static Set<Session> unknown = Collections.synchronizedSet(new HashSet<Session>());
    private static int MAX_CLIENTS;
    private static SecurityManager securityManager;
    private static InternalChannel internalChannel;
    private Map playerMappings;

    public Engine()
    {
       log.info("*** Engine Instantiated ***");
       playerMappings = new HashMap<>();
    }

    // ********** Web Socket Methods **********

    /**
	 * 
     * Determine if the user is is currently authenticated or not by checking
     * what list their session is currently in
	 * @param session
	 * @param message
	 * @throws IOException
	 */
	@OnMessage
    public void onMessage(Session session, String message) throws IOException
    {      
        ObjectMapper mapper = new ObjectMapper();

        if (known.contains(session)) //instruction received from known/ authenticated client
        {
            /*
             * message.replace is required for the current version of BrowserClient,
             * as quotes need to be properly encoded to avoid having the JS function
             * prematurely terminated. May require review when adding new client
             * applications.
             */
            
            message = message.replace("&quot", "\""); 
            log.log(Level.INFO, "Client: {0}  Message: {1}", new Object[]{session.getId(), message});
            
            try
            {
                BaseInstruction b = mapper.readValue(message, BaseInstruction.class);
                PlayerControlMessage pcm = new PlayerControlMessage();
				pcm.setPlayer(Integer.parseInt(playerMappings.get(session.getId()).toString()));
                pcm.setCode(playerMappings.get(session.getId()).toString()); // player ID
                pcm.setDirection(b.getDirection());               
                internalChannel.sendGameInstruction(mapper.writeValueAsString(pcm));
            }
            
            catch (IOException e)
            {
                log.log(Level.WARNING, "Error decoding instruction:{0}", e.getMessage());
            }
        }
        
        else if (unknown.contains(session)) //session valid, but unauthenticated at this point
        {
            log.log(Level.INFO, "Token Received: {0}", message);
            processToken(message, session);
        }
        
        else // catch all for any other random states we may end up in
        {
            log.warning("Unknown session found. Closing connection.");
            session.close();
        }
    }

	/**
	 *
	 * add session to list containing active connections
     * that are yet authenticated. Clients must pass valid security token
     * to move from unauth list to auth list where they can start
     * passing instructions to the game engine 
	 * @param peer
	 * @param config
	 * @throws IOException 
	 */
    @OnOpen
    public void onOpen(Session peer, EndpointConfig config) throws IOException
    {
        peer.getBasicRemote().sendText("Please transmit your token."); //request token from client
        unknown.add(peer);
    }

    /**
	 *
	 * Client is disconnecting from Input system. If they were part of a game, 
     * then the game engine must be notified that they are leaving
	 * @param peer
	 */
	@OnClose
    public void onClose(Session peer)
    {
        if (known.contains(peer)) //player was connected, must notify Game Engine
        {
            long playerID = (long) playerMappings.get(peer.getId());
            known.remove(peer);
            playerMappings.remove(peer.getId());
            long result = internalChannel.sendPlayerControlMessage(false, playerID);           
        }
        
        else
        {
            unknown.remove(peer);
        }
    }

    // ********** Player Management Methods **********
    protected boolean spaceFree()
    {
        if (known.size() < MAX_CLIENTS)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    protected int freeSpaces()
    {
        return MAX_CLIENTS - known.size();
    }

    private void processToken(String token, Session session)
    {
        if (securityManager.checkToken(token)) //id token passed from client
        {
            log.info("Valid token received, adding player");
            known.add(session);
            unknown.remove(session);
            long result = internalChannel.sendPlayerControlMessage(true, 0); //notify Game Engine of connecting client
            playerMappings.put(session.getId(), result);
        }
        
        else //invalid token, remove client
        {
            log.info("Token check failed, removing player");
            unknown.remove(session);
        }
    }
    
    

    // ********** Servlet Context Methods **********
    /**
	 *
	 * @param sce
	 */
	@Override
    public void contextInitialized(ServletContextEvent sce)
    {
        Engine.securityManager = new SecurityManager(10000, "ws://192.168.1.1:22501/Input/endpoint", this);
        Engine.MAX_CLIENTS = 2; 
        Engine.internalChannel = new InternalChannel("ws://localhost:8080/GameEngine/MessageManager", 
                "http://localhost:8080/GameEngine/PlayerManager");
        
        sce.getServletContext().setAttribute("securityManager", Engine.securityManager);
        
        log.info("*** Servlet Context Initialized ***");
    }

    /**
	 *
	 * @param sce
	 */
	@Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        sce.getServletContext().removeAttribute("securityManager");
        log.info("*** Servlet Context Destroyed ***");
    }
}
