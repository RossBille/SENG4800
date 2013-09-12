package au.edu.newcastle.SENG48002013.game.engine.model.actions;

import javax.vecmath.Vector2d;

import au.edu.newcastle.SENG48002013.game.engine.model.environment.GameObject;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Player;

public class InputAction extends BaseAction {
	private Player player;
	private GameObject gameObject;
	private Type type;
	public InputAction(long id)
	{
		super(id);
	}
	public Player getPlayer()
	{
		return player;
	}
	public void setPlayer(Player player)
	{
		this.player = player;
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
		Vector2d value = player.getInput().getValue();
		if(player.getInput().isPosition())
		{
			switch(type)
			{
				case ADD: gameObject.getNextPos().add(value);
				break;
				case SUB: gameObject.getNextPos().sub(value);
				break;
				case SET: gameObject.getNextPos().set(value);
				break;
			}
		}
		else
		{
			switch(type)
			{
				case ADD: gameObject.getNextVel().add(value);
				break;
				case SUB: gameObject.getNextVel().sub(value);
				break;
				case SET: gameObject.getNextVel().set(value);
				break;
			}
		}
		return -1;
	}
	public enum Type
	{
		ADD,
		SUB,
		SET
	}
	
}
