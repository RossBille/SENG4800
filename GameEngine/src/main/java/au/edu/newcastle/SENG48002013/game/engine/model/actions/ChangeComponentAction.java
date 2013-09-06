package au.edu.newcastle.SENG48002013.game.engine.model.actions;

import javax.vecmath.Vector2d;

import au.edu.newcastle.SENG48002013.game.engine.model.environment.GameObject;

public class ChangeComponentAction extends BaseAction {
	private GameObject gameObject;
	private Vector2d value;
	private Type type;
	private Component component;
	public ChangeComponentAction(long id)
	{
		super(id);
	}
	public GameObject getGameObject()
	{
		return gameObject;
	}
	public void setGameObject(GameObject gameObject)
	{
		this.gameObject = gameObject;
	}
	public Vector2d getValue()
	{
		return value;
	}
	public void setValue(Vector2d value)
	{
		this.value = value;
	}
	public Type getType()
	{
		return type;
	}
	public void setType(Type type)
	{
		this.type = type;
	}
	public Component getComponent()
	{
		return component;
	}
	public void setComponent(Component component)
	{
		this.component = component;
	}
	public int doAction(double dt)
	{
		switch(type)
		{
			case ADD:
			{
				switch(component)
				{
					case POS: gameObject.getNextPos().add(value);
					break;
					case VEL: gameObject.getNextVel().add(value);
					break;
					case ACC: gameObject.getNextAcc().add(value);
					break;
				}
			}
			break;
			case SUB:
			{
				switch(component)
				{
					case POS: gameObject.getNextPos().sub(value);
					break;
					case VEL: gameObject.getNextVel().sub(value);
					break;
					case ACC: gameObject.getNextAcc().sub(value);
					break;
				}
			}
			break;
			case SET:
			{
				switch(component)
				{
					case POS: gameObject.getNextPos().set(value);
					break;
					case VEL: gameObject.getNextVel().set(value);
					break;
					case ACC: gameObject.getNextAcc().set(value);
					break;
				}
			}
			break;
		}
		return -1;
	}
	public enum Component 
	{
		POS,
		VEL,
		ACC
	}
	public enum Type
	{
		ADD,
		SUB,
		SET
	}
}