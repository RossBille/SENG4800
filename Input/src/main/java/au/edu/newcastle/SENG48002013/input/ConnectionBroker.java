package au.edu.newcastle.SENG48002013.input;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Initial entry point for clients wanting to connect to the system. Receives a
 * registration request from the client, determines if it should be granted.
 */
@WebServlet("/connect")
public class ConnectionBroker extends HttpServlet
{
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException 
    {
        String mac = req.getParameter("mac");
	String type = req.getParameter("type");
        
        System.out.println("MAC: " + mac + " Device Type: " + type);
        
        // check for space in game, request token from security manager
        resp.sendRedirect("ClientConnectRequest.jsp"); //temp
        
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException 
    {
        doPost(req, resp);      
    }

   
}
