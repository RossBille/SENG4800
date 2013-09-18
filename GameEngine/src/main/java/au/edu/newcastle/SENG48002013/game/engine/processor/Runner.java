/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.processor;

import au.edu.newcastle.SENG48002013.game.engine.connections.output.OutputConnectionManager;
import au.edu.newcastle.SENG48002013.game.engine.model.IGame;
import au.edu.newcastle.SENG48002013.game.engine.model.IGameOutput;
import au.edu.newcastle.SENG48002013.game.engine.resources.GameBuilder;
import java.io.IOException;

/**
 *
 * @author Pete
 */
public class Runner extends Thread
{

	private IGame game;
	boolean running;

	public Runner()
	{
		running = false;
	}

	@Override
	public void run()
	{
		System.out.println("STARTING GAME");
		game = GameBuilder.buildGame();
		//TestOutputWindow output = new TestOutputWindow();
		IClock clock = new Clock();
		running = true;
		while (running)
		{
			double dt = clock.rest();
			//output.sendOutput((IGameOutput)game);
			try
			{
				OutputConnectionManager.sendOutput((IGameOutput) game);
			} catch (IOException e)
			{
					System.out.println("Runner encountered an IOException: "+ e.getMessage());
			}
			/*for(int i = 0; i < objects.length; i++)
			 {
			 System.out.println("X:" + objects[i].getPos().x + " Y:" + objects[i].getPos().y);
			 }*/
			game.step(dt);
		}
	}

	public boolean addPlayer(long inputId)
	{
		boolean added = false;
		if (game != null)
		{
			added = game.addPlayer(inputId);
		}
		return added;
	}

	public void removePlayer(long inputId)
	{
		if (game != null)
		{
			game.removePlayer(inputId);
		}
	}

	public void stopRunning()
	{
		running = false;
	}
}
