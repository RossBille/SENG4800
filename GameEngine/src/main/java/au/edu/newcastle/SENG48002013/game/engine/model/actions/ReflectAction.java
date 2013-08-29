/**
 * 
 */
package au.edu.newcastle.SENG48002013.game.engine.model.actions;

import javax.vecmath.Vector2d;

import au.edu.newcastle.SENG48002013.game.engine.model.actions.ChangeVelAction.Type;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Circle;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.GameObject;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Level;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Rectangle;

/**
 * @author Pete
 *
 */
public class ReflectAction extends BaseAction {
	private GameObject gameObject;
	private GameObject surfaceObject;
	private Level level;
	private Type type;
	public ReflectAction(long id)
	{
		super(id);
		gameObject = null;
		surfaceObject = null;
		level = null;
		type = Type.OBJECT;
	}
	public GameObject getGameObject()
	{
		return gameObject;
	}
	public void setGameObject(GameObject gameObject)
	{
		this.gameObject = gameObject;
		this.type = Type.OBJECT;
	}
	public GameObject getSurfaceObject()
	{
		return surfaceObject;
	}
	public void setSurfaceObject(GameObject surfaceObject)
	{
		this.surfaceObject = surfaceObject;
	}
	public Level getLevel()
	{
		return level;
	}
	public void setLevel(Level level)
	{
		this.level = level;
		this.type = Type.LEVEL;
	}
	public int doAction(double dt)
	{
		//Object -> Object
		if(type == Type.OBJECT)
		{
			boolean undefined = false;
			double gradient = 0;
			if(gameObject.getNextPos().x - surfaceObject.getNextPos().x == 0)
			{
				undefined = true;
			}
			else
			{
				gradient = (gameObject.getNextPos().y - surfaceObject.getNextPos().y)/(gameObject.getNextPos().x - surfaceObject.getNextPos().x);
			}
			//Both circles
			if(gameObject.getShape() instanceof Circle && surfaceObject.getShape() instanceof Circle)
			{
				if(undefined)
				{
					gameObject.getNextVel().y *= -1;
					gameObject.getNextAcc().y *= -1;
				}
				else if(gradient == 0)
				{
					gameObject.getNextVel().x *= -1;
					gameObject.getNextAcc().x *= -1;
				}
				else
				{
					Vector2d normal = new Vector2d();
					normal.sub(gameObject.getNextPos(), surfaceObject.getNextPos());
					double normalLength = normal.lengthSquared();
					double velScale = -(2*gameObject.getNextVel().dot(normal))/normalLength;
					double accScale = -(2*gameObject.getNextAcc().dot(normal))/normalLength;
					Vector2d reflectedVel = new Vector2d();
					Vector2d reflectedAcc = new Vector2d();
					reflectedVel.scaleAdd(velScale, normal, gameObject.getNextVel());
					reflectedAcc.scaleAdd(accScale, normal, gameObject.getNextAcc());
					gameObject.setNextAcc(reflectedAcc);
					gameObject.setNextVel(reflectedVel);
				}
			}
		}
		//Object -> Boundary
		else
		{
			double[] boundaries = new double[4];
			//Rectangle
			if(gameObject.getShape() instanceof Rectangle)
			{
				Vector2d objectSize = ((Rectangle)gameObject.getShape()).getSize();
				//Top
				boundaries[0] = gameObject.getNextPos().y;
				//Bottom
				boundaries[1] = level.getDimensions().y - (gameObject.getNextPos().y + objectSize.y);
				//Left
				boundaries[2] = gameObject.getNextPos().x;
				//Right
				boundaries[3] = level.getDimensions().x - (gameObject.getNextPos().x + objectSize.x);
			}
			//Circle
			else if(gameObject.getShape() instanceof Circle)
			{
				double radius = ((Circle)gameObject.getShape()).getRadius();
				//Top
				boundaries[0] = gameObject.getNextPos().y-radius;
				//Bottom
				boundaries[1] = level.getDimensions().y - (gameObject.getNextPos().y + radius);
				//Left
				boundaries[2] = gameObject.getNextPos().x-radius;
				//Right
				boundaries[3] = level.getDimensions().x - (gameObject.getNextPos().x + radius);
			}
			int minIndex = 0;
			double min = boundaries[0];
			for(int i = 1; i < 4; i++)
			{
				if(boundaries[i] < min)
				{
					minIndex = i;
					min = boundaries[i];
				}
			}
			//Top
			if(minIndex == 0)
			{
				gameObject.getNextVel().y *= -1;
				gameObject.getNextAcc().y *= -1;
			}
			//Bottom
			else if(minIndex == 1)
			{
				gameObject.getNextVel().y *= -1;
				gameObject.getNextAcc().y *= -1;
			}
			//Left
			else if(minIndex == 3)
			{
				gameObject.getNextVel().x *= -1;
				gameObject.getNextAcc().x *= -1;
			}
			//Right
			else
			{
				gameObject.getNextVel().x *= -1;
				gameObject.getNextAcc().x *= -1;
			}
		}
		return -1;
	}
	private enum Type
	{
		OBJECT,
		LEVEL
	}
}
