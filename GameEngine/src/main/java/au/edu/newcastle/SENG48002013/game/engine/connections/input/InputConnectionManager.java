package au.edu.newcastle.SENG48002013.game.engine.connections.input;

import au.edu.newcastle.SENG48002013.game.engine.connections.BaseServlet;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Player;
import au.edu.newcastle.SENG48002013.game.engine.processor.Boss;
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

	private Boss boss= null;

	@Override
	protected void processRequest() throws IOException
	{
		Response r = new Response();

		//parse instruction
		ObjectMapper mapper = new ObjectMapper();
		PlayerNumberMessage pnm;
		pnm = mapper.readValue(extractJson(), PlayerNumberMessage.class);
		System.out.println(pnm.toString());
		//check if adding or removing
		setProcessor();
		if (pnm.isConnecting())
		{
			long generatedId = generateId(pnm);
			boolean addPlayer = boss.addPlayer(generatedId);
			if(addPlayer)
			{
				r.setError(false);
				r.setCode(convert(addPlayer));
				r.setMessage("Player has been added");	
			}else{
				r.setError(true);
				r.setMessage("No Room");
				r.setCode(-1);//TODO create error code list
			}
				
			
		}else{
			//remove player from the game
			boss.removePlayer(pnm.getPlayer());
			r.setError(false);
			r.setMessage("Player was removed");
			r.setCode(1);//TODO create error code list
		}

	}

	private void setProcessor()
	{
		if (boss == null)
		{
			boss = (Boss) getServletContext().getAttribute("processor");
		}
	}

	private String extractJson()
	{
		System.out.println(request.getParameter("data"));
		return request.getParameter("data");
	}

	private void respond(Response r) throws IOException
	{
		response.setContentType("text/JSON");
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().write(mapper.writeValueAsString(r));

	}
	private int convert (boolean result)
	{
		if(result)
		{
			return 1;
		}else{
			return -1;
		}
	}
	private long lastId = 0;
	private long generateId(PlayerNumberMessage pnm)
	{
		return ++ lastId;
	}
}
