package au.edu.newcastle.SENG48002013.game.engine.connections;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author rossbille
 */
@WebServlet("/objects")
public class OutputSetup extends BaseServlet
{

	@Override
	protected void processRequest() throws IOException
	{
		ServletContext sc = getServletContext();
		response.setContentType("text/json");
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		out.print(mapper.writeValueAsString("i have no values to send yet :)"));
	}
	
}
