/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.processor;

import au.edu.newcastle.SENG48002013.game.engine.connections.output.OutputConnectionManager;

import au.edu.newcastle.SENG48002013.game.engine.connections.output.TestOutputWindow;
import au.edu.newcastle.SENG48002013.game.engine.model.IGame;
import au.edu.newcastle.SENG48002013.game.engine.model.IGameOutput;
import au.edu.newcastle.SENG48002013.game.engine.resources.GameBuilder;
import java.io.IOException;

/**
 *
 * @author Ross
 */
public class Boss
{
	private static boolean running = false;
	private static IGame game;
	
	public static boolean addPlayer(long inputId)
	{
		boolean added = false;
		if(game != null)
		{
			added = game.addPlayer(inputId);
		}
		return added;
	}

	public static void removePlayer(long inputId)
	{
		if(game != null)
		{
			game.removePlayer(inputId);
		}
	}
	
	public static void start() throws IOException
	{
		game = GameBuilder.buildGame();
		//TestOutputWindow output = new TestOutputWindow();
		IClock clock = new Clock();
		running = true;
		while(running)
		{
			double dt = clock.rest();
			//output.sendOutput((IGameOutput)game);
			OutputConnectionManager.sendOutput((IGameOutput)game);
			/*for(int i = 0; i < objects.length; i++)
			{
				System.out.println("X:" + objects[i].getPos().x + " Y:" + objects[i].getPos().y);
			}*/
			game.step(dt);
		}
	}
	
	public static void stop()
	{
		running = false;
	}
	
	public static boolean isRunning()
	{
		return running;
	}
}
