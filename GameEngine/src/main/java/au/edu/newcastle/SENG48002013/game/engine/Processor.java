package au.edu.newcastle.SENG48002013.game.engine;

import au.edu.newcastle.SENG48002013.game.engine.objects.Player;
import java.util.HashMap;
import java.util.Map;
/**
 * Class to control all game logic
 * @author rossbille
 */
public class Processor
{
	private Map<String, Player> players;
	private final int MAX_PLAYERS;
	public Processor()
	{
		players = new HashMap<>();
		MAX_PLAYERS = 10;//TODO: read from config
	}
	
	public void newPlayer(Player p) 
	{
			
	}
	public int getEmptyPlayerSlots()
	{
		return MAX_PLAYERS - players.size();
	}
	public boolean isRoom()
	{
		return getEmptyPlayerSlots() > 0;
	}

	public void addPlayer(Player tempPlayer)
	{
		System.out.println("added player " + tempPlayer.toString());
	//	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
