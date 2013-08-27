/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.model.environment;
import au.edu.newcastle.SENG48002013.game.engine.model.events.IEvent;
import javax.vecmath.Vector2d;

public class Level {
	private long id;
	private Vector2d dimensions;
	private GameObject[] gameObjects;
	private IEvent[] events;
	public Level(long id)
	{
		this.id = id;
		dimensions = new Vector2d();
	}
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public Vector2d getDimensions()
	{
		return dimensions;
	}
	public void setDimensions(Vector2d dimensions)
	{
		this.dimensions.set(dimensions);
	}
	public GameObject getGameObject(int index)
	{
		return gameObjects[index];
	}
	public IGameObject[] getGameObjects()
	{
		return gameObjects;
	}
	public void setGameObject(int index, GameObject gameObject)
	{
		gameObjects[index] = gameObject;
	}
	public IEvent getEvent(int index)
	{
		return events[index];
	}
	public void setEvent(int index, IEvent event)
	{
		events[index] = event;
	}
	public int step(long dt)
	{
		int returnCode = -1;
		stepNext(dt);
		returnCode = stepEvents(dt);
		stepCommit();
		return returnCode;
	}
	private void stepNext(long dt)
	{
		for(int i = 0; i < gameObjects.length; i++)
		{
			gameObjects[i].stepNext(dt);
		}
	}
	private int stepEvents(long dt)
	{
		int returnCode = -1;
		for(int i = 0; i < events.length; i++)
		{
			int newReturnCode = events[i].evaluate(dt);
			if(newReturnCode != -1)
			{
				returnCode = newReturnCode;
			}
		}
		return returnCode;
	}
	private void stepCommit()
	{
		for(int i = 0; i < gameObjects.length; i++)
		{
			gameObjects[i].commit();
		}
	}
}