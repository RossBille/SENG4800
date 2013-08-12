package au.edu.newcastle.SENG48002013.input;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Initial entry point for clients wanting to connect to the system. Receives a registration request from the client,
 * determines if it should be granted.
 */
@WebServlet("/connect")
public class ConnectionBroker extends HttpServlet
{


	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
            SecurityManager secMan = (SecurityManager) getServletContext().getAttribute("securityManager");

            if (secMan.checkRequest(req.getRemoteAddr())) //check for DOS
            {
                // check for space in game, request token from security manager
		resp.setContentType("application/JSON");		
		ObjectMapper mapper = new ObjectMapper();
		resp.getWriter().write(mapper.writeValueAsString(secMan.serviceRequest()));         
            }              		
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		doPost(req, resp);
	}

	private String extractId(HttpServletRequest request)
	{
		return request.getParameter("ID");
	}
}
