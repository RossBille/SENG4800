package au.edu.newcastle.SENG48002013.game.engine.model.actions;

import au.edu.newcastle.SENG48002013.game.engine.model.environment.GameObject;
import javax.vecmath.Vector2d;

public class InputDirectionAction extends BaseInputAction {
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	private int direction;
	private double value;
	private GameObject gameObject;
	public InputDirectionAction(long id)
	{
		super(id);
	}
	public int getDirection()
	{
		return direction;
	}
	public void setDirection(int direction)
	{
		this.direction = direction;
	}
	public double getValue()
	{
		return value;
	}
	public void setValue(double value)
	{
		this.value = value;
	}
	public GameObject getGameObject()
	{
		return gameObject;
	}
	public void setGameObject(GameObject gameObject)
	{
		this.gameObject = gameObject;
	}

	public void doAction(long dt)
	{
		if(getInput() != null)
		{
			Vector2d vel = getInput().getValue();
			gameObject.setPos(getInput().getPos());
		}
	}
}

