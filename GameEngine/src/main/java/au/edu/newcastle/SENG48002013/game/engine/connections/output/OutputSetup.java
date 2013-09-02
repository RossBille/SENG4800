package au.edu.newcastle.SENG48002013.game.engine.connections.output;

import au.edu.newcastle.SENG48002013.game.engine.connections.BaseServlet;
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
	//need some structure to store all the game setup info here

	
	/**
	 * sends all setup info in the form of 
	 * 	[{  
	 * 		"id":  ,
	 * 		"pos": {"x":  , "y",  },
	 * 		"spriteLocations": ["some/url/", ... , "some/other/url"],
	 * 		"spriteId":  
	 * 	},
	 * 	... ,
	 * 	{  
	 * 		"id":  ,
	 * 		"pos": {"x":  , "y",  },
	 * 		"spriteLocations": ["some/url/", ... , "some/other/url"],
	 * 		"spriteId":  
	 * 	}]
	 * whenever a new output client connects
	 * @throws IOException
	 */
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
