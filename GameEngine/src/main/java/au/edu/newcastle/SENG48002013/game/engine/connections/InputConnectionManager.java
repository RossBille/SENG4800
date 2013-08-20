package au.edu.newcastle.SENG48002013.game.engine.connections;

import au.edu.newcastle.SENG48002013.game.engine.Processor;
import au.edu.newcastle.SENG48002013.game.engine.objects.Player;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import au.edu.newcastle.SENG48002013.messages.PlayerNumberMessage;
import au.edu.newcastle.SENG48002013.messages.responses.Response;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Servlet class to access when adding or removing players from the game
 *
 * @author rossbille
 */
@WebServlet("/PlayerManager")
public class InputConnectionManager extends BaseServlet
{

	private Processor processor = null;

	@Override
	protected void processRequest() throws IOException
	{
		//parse instruction
		ObjectMapper mapper = new ObjectMapper();
		PlayerNumberMessage pnm;
		pnm = mapper.readValue(extractJson(), PlayerNumberMessage.class);
		System.out.println(pnm.toString());
		//check if adding or removing
		if (pnm.isConnecting())
		{
			setProcessor();
			//check if there is room
			if (processor.isRoom())
			{
				//add new player to the game
				Player tempPlayer = new Player();
				//set all variables extracted from the new player message
				processor.addPlayer(tempPlayer);
			} else
			{
				//send error mesage
				
				//construct response to send
				Response r = new Response();

			}

			throw new UnsupportedOperationException("Not supported yet.");
		} else
		{
			//remove player from the game
			throw new UnsupportedOperationException("Not supported yet.");
		}

	}

	private void setProcessor()
	{
		if (processor == null)
		{
			processor = (Processor) getServletContext().getAttribute("processor");
		}
	}

	private String extractJson()
	{
		System.out.println(request.getParameter("data"));
		return "{\"@class\": \"au.edu.newcastle.SENG48002013.messages.PlayerNumberMessage\", \"player\":1, \"connecting\":true}";
	}

	private void respond(Response r) throws IOException
	{
		response.setContentType("text/JSON");
		response.getWriter().write(r.toString());

	}
}
