package au.edu.newcastle.SENG48002013.input;

import au.edu.newcastle.seng48002013.instructions.BaseInstruction;
import au.edu.newcastle.seng48002013.results.Result;
import java.io.IOException;
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
import org.codehaus.jackson.map.ObjectMapper;

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

    public Engine()
    {
        this.MAX_CLIENTS = 5;
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
        log.info(message);

        if (known.contains(session)) //instruction received from known connected client
        {
            log.info("*** Authenticated Session Found ***");
            BaseInstruction instruction = parseInstruction(message);

            if (instruction != null)
            {
                log.log(Level.INFO, "Instruction: {0}", instruction.getDirection());
                /*
                 * Action required here - send instruction up to game engine
                 */
            }
            
            else
            {
                log.severe("Instruction deserialize failed");
            }
        }
        else if (unknown.contains(session)) //session alive, but unauthenticated at this point
        {
            Result result = parseToken(message);

            if (result != null)
            {
                processToken(result, session);
            }
            
            else
            {
                log.severe("Token deserialize failed");
            }

        }
        else // catch all for any other random states we may end up in
        {
            log.warning("Unknown session found. Closing connection...");
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

    @OnClose
    public void onClose(Session peer)
    {
        known.remove(peer);
        unknown.remove(peer);
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
    
    // ********** Servlet Context Methods **********

    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        Engine.manager = new SecurityManager(10000, "ws://localhost:8080/Input/endpoint", this);
        sce.getServletContext().setAttribute("securityManager", Engine.manager);
        log.info("*** Servlet Context Initialized ***");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        sce.getServletContext().removeAttribute("securityManager");
        log.info("*** Servlet Context Destroyed ***");
    }
    
    // ********** JSON Methods **********

    private Result parseToken(String s)
    {
        ObjectMapper mapper = new ObjectMapper();
        Result temp = null;

        try
        {
            temp = mapper.readValue(s, Result.class);
        }
        
        catch (IOException ex)
        {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }

    private void processToken(Result r, Session session)
    {
        if (manager.checkToken(r.getCode())) //add player to game
        {
            log.info("Valid token received, adding player");
            known.add(session);
            unknown.remove(session);
        }
        
        else
        {
            log.info("Token check failed, removing player");
            unknown.remove(session);
        }
    }

    private BaseInstruction parseInstruction(String s)
    {
        ObjectMapper mapper = new ObjectMapper();
        BaseInstruction temp = null;
        
        try
        {
            temp = mapper.readValue(s, BaseInstruction.class);
        }
        
        catch (IOException ex)
        {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }
}
