package au.edu.newcastle.SENG48002013.game.engine.model.actions;

import au.edu.newcastle.SENG48002013.game.engine.model.environment.GameObject;
import javax.vecmath.Vector2d;

public class InputDirectionAction extends BaseInputAction {
	private Direction direction;
	private double value;
	private GameObject gameObject;
	public InputDirectionAction(long id)
	{
		super(id);
	}
	public Direction getDirection()
	{
		return direction;
	}
	public void setDirection(Direction direction)
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

	public int doAction(double dt)
	{
		if(getInput() != null)
		{
			Vector2d vel = getInput().getValue();
			gameObject.setPos(getInput().getPos());
		}
		return -1;
	}
	public enum Direction
	{
		UP,
		DOWN,
		LEFT,
		RIGHT
	}
}

