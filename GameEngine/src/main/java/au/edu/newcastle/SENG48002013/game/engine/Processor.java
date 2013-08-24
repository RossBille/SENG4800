package au.edu.newcastle.SENG48002013.game.engine;

import au.edu.newcastle.SENG48002013.game.engine.objects.Player;
/**
 * Class to control all game logic
 * @author rossbille
 */
public class Processor
{
	private Player[] players;
	public Processor()
	{
		players = new Player[10];//TODO read from config
	}
	
	/**
	 *
	 * @param tempPlayer the player to add
	 * @return the players number if they were added, -1 if they were not added
	 */
	public int addPlayer(Player tempPlayer)
	{
		int nextSpot = isRoom();
		if(nextSpot >= 0)
		{
			//add player
			players[nextSpot] = tempPlayer;
		}
		return nextSpot;
	}
	/**
	 * 
	 * @return the next available player slot or -1 if none;
	 */
	private int isRoom()
	{
		for(int i = 0; i<players.length;i++)
		{
			if(players[i] == null)
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * opens up the slot that was used by the player
	 * @param player the player to remove
	 */
	public void removePlayer(int player)
	{
		players[player] = null;
	}
	
}
