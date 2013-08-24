/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.resources.environment;
import au.edu.newcastle.SENG48002013.game.engine.resources.events.IEvent;
import au.edu.newcastle.SENG48002013.game.engine.util.Vector2d;

public class Level {
	private long id;
	private Vector2d dimensions;
	private GameObject[] gameObjects;
	private IEvent[] events;
	public Level(long id)
	{
		this.id = id;
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
		this.dimensions.copyTo(dimensions);
	}
	public GameObject getGameObject(int index)
	{
		return gameObjects[index];
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
	public void step(long dt)
	{
		stepNext(dt);
		stepEvents(dt);
		stepCommit();
	}
	private void stepNext(long dt)
	{
		for(int i = 0; i < gameObjects.length; i++)
		{
			gameObjects[i].stepNext(dt);
		}
	}
	private void stepEvents(long dt)
	{
		for(int i = 0; i < events.length; i++)
		{
			events[i].evaluate(dt);
		}
	}
	private void stepCommit()
	{
		for(int i = 0; i < gameObjects.length; i++)
		{
			gameObjects[i].commit();
		}
	}
}