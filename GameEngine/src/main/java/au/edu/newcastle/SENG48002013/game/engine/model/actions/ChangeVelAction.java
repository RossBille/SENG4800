package au.edu.newcastle.SENG48002013.game.engine.model.actions;

import javax.vecmath.Vector2d;

import au.edu.newcastle.SENG48002013.game.engine.model.environment.GameObject;

public class ChangeVelAction extends BaseAction {
	private Vector2d value;
	private GameObject gameObject;
	private Type type;
	public ChangeVelAction(long id)
	{
		super(id);
		value = new Vector2d();
	}
	public Vector2d getValue()
	{
		return value;
	}
	public void setValue(Vector2d value)
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
	public Type getType()
	{
		return type;
	}
	public void setType(Type type)
	{
		this.type = type;
	}
	public int doAction(double dt)
	{
		if(type == Type.HORIZONTAL)
		{
			gameObject.getNextVel().x = value.x;
		}
		else if(type == Type.VERTICAL)
		{
			gameObject.getNextVel().y = value.y;
		}
		else
		{
			gameObject.setNextVel(value);
		}
		return -1;
	}
	public enum Type
	{
		VERTICAL,
		HORIZONTAL,
		VECTOR
	}
}