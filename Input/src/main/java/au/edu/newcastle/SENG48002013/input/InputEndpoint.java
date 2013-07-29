package au.edu.newcastle.SENG48002013.input;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import org.codehaus.jackson.map.ObjectMapper;
import au.edu.newcastle.seng48002013.instructions.BaseInstruction;
import java.io.IOException;

/**
 * this class opens endpoints to accept all instructions and set up players and actions to send to the game engine This
 * endpoint needs to be accessed publicly
 *
 * @author Ross Bille
 */
@ServerEndpoint("/endpoint")
public class InputEndpoint
{

	private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

	@OnMessage
	public String onMessage(String message)
	{
		System.out.println(message);
		//get json parser
		ObjectMapper mapper = new ObjectMapper();
		BaseInstruction instruction;
		//process the input
		try
		{
			instruction = mapper.readValue(message, BaseInstruction.class);
			System.out.println("STATS");
			System.out.println(instruction.getDirection());
			System.out.println(instruction.getUpdated());
			System.out.println(instruction.getId());
		} catch (IOException ex)
		{
			//actually do something with the error
			ex.printStackTrace();
		}
		return null;
	}

	@OnOpen
	public void onOpen(Session peer)
	{
		peers.add(peer);
	}

	@OnClose
	public void onClose(Session peer)
	{
		peers.remove(peer);
	}
}
