package au.edu.newcastle.SENG48002013.input;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import au.edu.newcastle.seng48002013.results.Result;
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
		// check for space in game, request token from security manager
		resp.setContentType("application/JSON");
		Result r = new Result();
		r.setError(false);
		//TODO: dynamically fetch url
		r.setMessage("ws://localhost:8080/Input/endpoint");
		r.setCode("super random code " + extractId(req));
		ObjectMapper mapper = new ObjectMapper();

		resp.getWriter().write(mapper.writeValueAsString(r));
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
