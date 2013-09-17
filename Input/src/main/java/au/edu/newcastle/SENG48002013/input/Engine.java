package au.edu.newcastle.SENG48002013.input;

import au.edu.newcastle.SENG48002013.input.websocket.WebSocketClient;
import au.edu.newcastle.SENG48002013.messages.PlayerControlMessage;
import au.edu.newcastle.SENG48002013.messages.PlayerNumberMessage;
import au.edu.newcastle.seng48002013.instructions.BaseInstruction;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;

/*
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
    private HttpSession httpSession;
    private final int MAX_CLIENTS;
    private static SecurityManager manager;
    private static WebSocketClient engineChannel;

    public Engine()
    {
       this.MAX_CLIENTS = 5; // get this value from the XML config for final project
       log.info("*** Engine Instantiated ***");
    }

    // ********** Web Socket Methods **********

    /*
     * OnMessage
     * Determine if the user is is currently authenticated or not by checking
     * what list their session is currently in
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
                System.out.println("Vector from Instruction: " + b.getDirection());              
                PlayerControlMessage pcm = new PlayerControlMessage();
                pcm.setCode(session.getId()); // player ID
                pcm.setDirection(b.getDirection());               
                engineChannel.getSession().getBasicRemote().sendText(mapper.writeValueAsString(pcm));
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

    /*
     * onOpen - add session to list containing active connections
     * that are yet authenticated. Clients must pass valid security token
     * to move from unauth list to auth list where they can start
     * passing instructions to the game engine
     */
    @OnOpen
    public void onOpen(Session peer, EndpointConfig config) throws IOException
    {
        this.httpSession = (HttpSession) config.getUserProperties()
                .get(HttpSession.class.getName());

        peer.getBasicRemote().sendText("token"); //request token from client
        unknown.add(peer);
    }

    /*
     * Client is disconnecting from Input system. If they were part of a game, 
     * then the game engine must be notified that they are leaving
     */
    @OnClose
    public void onClose(Session peer)
    {
        if (known.contains(peer)) //player was connected, must notify Game Engine
        {
            known.remove(peer);
            notifyGameEngine(false);
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
        if (manager.checkToken(token)) //id token passed from client
        {
            log.info("Valid token received, adding player");
            known.add(session);
            unknown.remove(session);
            notifyGameEngine(true); //notify Game Engine of conencting client
        }
        
        else //invalid token, remove client
        {
            log.info("Token check failed, removing player");
            unknown.remove(session);
        }
    }
    
    /*
     * Sends Http Post to Game Engine Servlet /PlayerManager indicating that
     * a player either connecting or disconnecting based on boolean flag
     */
    private void notifyGameEngine(boolean connecting)
    {
        PlayerNumberMessage pnm = new PlayerNumberMessage();
        pnm.setConnecting(connecting);
        
        ArrayList<NameValuePair> parameters; // for name/value pairs of req params
        HttpClient httpclient = new DefaultHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        
        try
        {
            parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair("data", mapper.writeValueAsString(pnm)));         
            HttpPost post = new HttpPost("http://localhost:8080/GameEngine/PlayerManager");
            post.setEntity(new UrlEncodedFormEntity(parameters));           
            HttpResponse result = httpclient.execute(post);       
        }
        
        catch (URISyntaxException | HttpException | IOException ex)
        {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // ********** Servlet Context Methods **********
    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        //these hardcoded values can stay for now.
        Engine.manager = new SecurityManager(10000, "ws://localhost:8080/Input/endpoint", this);
        sce.getServletContext().setAttribute("securityManager", Engine.manager);
        Engine.engineChannel = new WebSocketClient();
        
        //URL for "internal" web socket will need to be passed from XML config
        engineChannel.start("ws://localhost:8080/GameEngine/MessageManager");
        log.info("*** Servlet Context Initialized ***");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        sce.getServletContext().removeAttribute("securityManager");
        log.info("*** Servlet Context Destroyed ***");
    }
}
