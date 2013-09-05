/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.processor;

import java.util.LinkedList;
import java.util.List;

import au.edu.newcastle.SENG48002013.game.engine.connections.output.TestOutputWindow;
import au.edu.newcastle.SENG48002013.game.engine.model.IGame;
import au.edu.newcastle.SENG48002013.game.engine.model.IGameOutput;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.IGameObject;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Player;
import au.edu.newcastle.SENG48002013.game.engine.resources.GameBuilder;

/**
 *
 * @author Ross
 */
public class Boss
{
	private boolean running;
	private IGame game;
	public Boss()
	{
		running = false;
	}
	public boolean addPlayer(long inputId)
	{
		boolean added = false;
		if(game != null)
		{
			added = game.addPlayer(inputId);
		}
		return added;
	}

	public void removePlayer(long inputId)
	{
		if(game != null)
		{
			game.removePlayer(inputId);
		}
	}
	
	public void start()
	{
		game = GameBuilder.buildGame();
		TestOutputWindow output = new TestOutputWindow();
		IClock clock = new Clock();
		running = true;
		while(running)
		{
			double dt = clock.rest();
			output.sendOutput((IGameOutput)game);
			/*for(int i = 0; i < objects.length; i++)
			{
				System.out.println("X:" + objects[i].getPos().x + " Y:" + objects[i].getPos().y);
			}*/
			game.step(dt);
		}
	}
	
	public void stop()
	{
		running = false;
	}
	
	public boolean isRunning()
	{
		return running;
	}
}
