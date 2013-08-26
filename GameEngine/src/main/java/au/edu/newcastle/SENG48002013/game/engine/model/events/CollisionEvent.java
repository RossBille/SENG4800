/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.model.events;

import au.edu.newcastle.SENG48002013.game.engine.model.environment.Circle;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.GameObject;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Rectangle;
import javax.vecmath.Vector2d;


public class CollisionEvent extends BaseEvent {
	private GameObject object1;
	private GameObject object2;
	private boolean allowOverlap;
	public CollisionEvent(long id)
	{
		super(id);
	}
	public CollisionEvent(long id, GameObject object1, GameObject object2)
	{
		super(id);
		this.object1 = object1;
		this.object2 = object2;
	}
	public GameObject getObject1()
	{
		return object1;
	}
	public void setObject1(GameObject object1)
	{
		this.object1 = object1;
	}
	public GameObject getObject2()
	{
		return object2;
	}
	public void setObject2(GameObject object2)
	{
		this.object2 = object2;
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
		//Calculate gradient/undefined
		if(object1.getNextPos().x - object1.getPos().x == 0)
		{
			undefined = true;
		}
		else
		{
			gradient = (object1.getNextPos().y - object1.getPos().y)/(object1.getNextPos().x - object1.getPos().x);
		}
		//If BOTH rectangle shape
		if(object1.getShape() instanceof Rectangle && object1.getShape() instanceof Rectangle)
		{
			Vector2d object1Size = ((Rectangle)object1.getShape()).getSize();
			Vector2d object2Size = ((Rectangle)object2.getShape()).getSize();
			//Check for intersection
			if(object1.getNextPos().x < object2.getPos().x + object2Size.x &&
					object1.getNextPos().x + object1Size.x > object2.getPos().x &&
					object1.getNextPos().y < object2.getPos().y + object2Size.y &&
					object1.getNextPos().y + object1Size.y > object2.getPos().y)
			{
				intersects = true;
				if(!allowOverlap)
				{
					double xDir, yDir, xCover, yCover;
					xDir = object1.getNextPos().x - object1.getPos().x;
					yDir = object1.getNextPos().y - object1.getPos().y;
					if(undefined)
					{
						if(yDir > 0)
						{
							object1.getNextPos().y = object2.getPos().y - object1Size.y; 
						}
						else
						{
							object1.getNextPos().y = object2.getPos().y + object2Size.y;
						}
					}
					else if(gradient == 0)
					{
						if(xDir > 0)
						{
							object1.getNextPos().x = object2.getPos().x - object1Size.x; 
						}
						else
						{
							object1.getNextPos().x = object2.getPos().x + object2Size.x;
						}
					}
					else
					{
						double x1, x2, y1, y2;
						if(xDir > 0)
						{
							x1 = object2.getPos().x;
							x2 = object1.getNextPos().x + object1Size.x;
							y1 = gradient*(x1 - object1.getNextPos().x) + object1.getNextPos().y;
							y2 = gradient*(x2 - object1.getNextPos().x) + object1.getNextPos().y;
							xCover = (x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1); 
						}
						else
						{
							x1 = object2.getPos().x + object2Size.x;
							x2 = object1.getNextPos().x;
							y1 = gradient*(x1 - object1.getNextPos().x) + object1.getNextPos().y;
							y2 = gradient*(x2 - object1.getNextPos().x) + object1.getNextPos().y;
							xCover = (x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1); 
						}
						if(yDir > 0)
						{
							y1 = object1.getNextPos().y + object1Size.y;
							y2 = object2.getPos().y;
							x1 = (y1 - object1.getNextPos().y)/gradient + object1.getNextPos().y;
							x2 = (y2 - object1.getNextPos().y)/gradient + object1.getNextPos().y;
							yCover = (x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1);
						}
						else
						{
							y1 = object2.getPos().y + object2Size.y;
							y2 = object1.getNextPos().y;
							x1 = (y1 - object1.getNextPos().y)/gradient + object1.getNextPos().y;
							x2 = (y2 - object1.getNextPos().y)/gradient + object1.getNextPos().y;
							yCover = (x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1);
						}
						double length;
						if(xCover < yCover)
						{
							length = Math.sqrt(xCover);
						}
						else
						{
							length = Math.sqrt(yCover); 
						}
						Vector2d shift = new Vector2d();
						shift.sub(object1.getPos(), object1.getNextPos());
						shift.normalize();
						shift.scale(length);
						object1.getPos().add(shift);
					}
				}
			}
		}
		//If BOTH Circle shapes
		else if(object1.getShape() instanceof Circle && object1.getShape() instanceof Circle)
		{
			//Check for intersection
			double radius1 = ((Circle)object1.getShape()).getRadius();
			double radius2 = ((Circle)object2.getShape()).getRadius();
			if((object1.getNextPos().x - object2.getPos().x)*(object1.getNextPos().x - object2.getPos().x) +
				(object1.getNextPos().y - object2.getPos().y)*(object1.getNextPos().y - object2.getPos().y) <
				(radius1 + radius2)*(radius1 + radius2))
			{
				intersects = true;
				Vector2d lengthVec = new Vector2d();
				lengthVec.sub(object2.getPos(), object1.getNextPos());
				double length = lengthVec.length();
				//double length = object2.getPos().difference(object1.getPos()).length();
				object1.getNextPos().x = (object1.getNextPos().x - object2.getPos().x)/length*(radius1 + radius2) + object2.getPos().x;
				object1.getNextPos().y = (object1.getNextPos().y - object2.getPos().y)/length*(radius1 + radius2) + object2.getPos().y;
			}
		}
		//One Circle one Rectangle shape
		else
		{
			//To do
		}
		if(intersects)
		{
			doActions(dt);
		}
	}
}