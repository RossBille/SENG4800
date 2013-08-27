/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.processor;

import java.util.LinkedList;
import java.util.List;

import au.edu.newcastle.SENG48002013.game.engine.model.IGame;
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

	public void removePlayer(int player)
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	public void start()
	{
		game = GameBuilder.buildGame();
		running = true;
		while(running)
		{
			
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
