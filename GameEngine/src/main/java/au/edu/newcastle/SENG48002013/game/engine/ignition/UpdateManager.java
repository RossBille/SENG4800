package au.edu.newcastle.SENG48002013.game.engine.ignition;

import au.edu.newcastle.SENG48002013.game.engine.processor.Boss;
import javax.servlet.ServletContext;

/**
 * Class to start and stop the game for loading of new games and remote restarting
 *
 * @author rossbille
 */
public class UpdateManager
{

	public static void start(ServletContext sc)
	{
		System.out.println("Game is starting...");
	}

	public static void stop(ServletContext sc)
	{
		System.out.println("Game is stopping...");
	}

	public static void restart(ServletContext sc)
	{
		System.out.println("Game is restarting...");
	}
}
