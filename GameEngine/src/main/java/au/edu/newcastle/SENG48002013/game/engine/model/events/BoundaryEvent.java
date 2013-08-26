/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.model.events;

import au.edu.newcastle.SENG48002013.game.engine.model.environment.Circle;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.GameObject;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Level;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Rectangle;
import javax.vecmath.Vector2d;


public class BoundaryEvent extends BaseEvent {
	private GameObject gameObject;
	private Level level;
	private boolean allowOverlap;
	public BoundaryEvent(long id)
	{
		super(id);
	}
	public BoundaryEvent(long id, GameObject gameObject, Level level)
	{
		super(id);
		this.gameObject = gameObject;
		this.level = level;
	}
	public GameObject getGameObject()
	{
		return gameObject;
	}
	public void setGameObject(GameObject gameObject)
	{
		this.gameObject = gameObject;
	}
	public Level getLevel()
	{
		return level;
	}
	public void setLevel(Level level)
	{
		this.level = level;
	}
	public boolean isAllowOverlap()
	{
		return allowOverlap;
	}
	public void setAllowOverlap(boolean allowOverlap)
	{
		this.allowOverlap = allowOverlap; 
	}
	public void evaluate(long dt)
	{
		boolean intersects = false;
		boolean undefined = false;
		double gradient = 0;
		Vector2d levelDimensions = level.getDimensions();
		//Calculate gradient/undefined
		if(gameObject.getNextPos().x - gameObject.getPos().x == 0)
		{
			undefined = true;
		}
		else
		{
			gradient = (gameObject.getNextPos().y - gameObject.getPos().y)/(gameObject.getNextPos().x - gameObject.getPos().x);
		}
		//If rectangle shape
		if(gameObject.getShape() instanceof Rectangle)
		{
			Vector2d objectSize = ((Rectangle)gameObject.getShape()).getSize();
			//Intersect Top
			if(gameObject.getPos().y < 0)
			{
				intersects = true;
				if(!allowOverlap)
				{
					gameObject.getNextPos().y = 0;
					if(gradient != 0)
					{
						gameObject.getNextPos().y = gameObject.getPos().x - (gameObject.getPos().y/gradient); 
					}
				}
			}
			//Intersect Bottom
			else if(gameObject.getPos().y + objectSize.y > levelDimensions.y)
			{
				intersects = true;
				if(!allowOverlap)
				{
					gameObject.getNextPos().y = levelDimensions.y - objectSize.y;
					if(gradient != 0)
					{
						gameObject.getNextPos().y = ((gameObject.getNextPos().y - gameObject.getPos().y)/gradient) + gameObject.getPos().x;
					}
				}
			}
			//Intersect Left
			if(gameObject.getPos().x < 0)
			{
				intersects = true;
				if(!allowOverlap)
				{
					gameObject.getNextPos().x = 0;
					if(!undefined)
					{
						gameObject.getNextPos().y = gameObject.getPos().y - gradient*(gameObject.getPos().x);
					}
				}
			}
			//Intersect Right
			else if(gameObject.getPos().x + objectSize.x > levelDimensions.x)
			{
				intersects = true;
				if(!allowOverlap)
				{
					gameObject.getNextPos().x = levelDimensions.x - objectSize.x;
					if(!undefined)
					{
						gameObject.getNextPos().y = gradient*(gameObject.getNextPos().x - gameObject.getPos().x) + gameObject.getPos().y;
					}
				}
			}
		}
		//If circle shape
		else if(gameObject.getShape() instanceof Circle)
		{
			double radius = ((Circle)gameObject.getShape()).getRadius();
			//Intersect Top
			if(gameObject.getPos().y - radius < 0)
			{
				intersects = true;
				if(!allowOverlap)
				{
					gameObject.getNextPos().y = radius;
					if(gradient != 0)
					{
						gameObject.getNextPos().y = ((gameObject.getNextPos().y - gameObject.getPos().y)/gradient) + gameObject.getPos().x; 
					}
				}
			}
			//Intersect Bottom
			else if(gameObject.getPos().y + radius > levelDimensions.y)
			{
				intersects = true;
				if(!allowOverlap)
				{
					gameObject.getNextPos().y = levelDimensions.y - radius;
					if(gradient != 0)
					{
						gameObject.getNextPos().y = ((gameObject.getNextPos().y - gameObject.getPos().y)/gradient) + gameObject.getPos().x; 
					}
				}
			}
			//Intersect Left
			if(gameObject.getPos().x - radius < 0)
			{
				intersects = true;
				if(!allowOverlap)
				{
					gameObject.getNextPos().x = radius;
					if(!undefined)
					{
						gameObject.getNextPos().y = gradient*(gameObject.getNextPos().x - gameObject.getPos().x) + gameObject.getPos().y;
					}
				}
			}
			//Intersect Right
			else if(gameObject.getPos().x + radius > levelDimensions.x)
			{
				intersects = true;
				if(!allowOverlap)
				{
					gameObject.getNextPos().x = levelDimensions.x - radius;
					if(!undefined)
					{
						gameObject.getNextPos().y = gradient*(gameObject.getNextPos().x - gameObject.getPos().x) + gameObject.getPos().y;
					}
				}
			}
		}
		if(intersects) doActions(dt);
	}
}