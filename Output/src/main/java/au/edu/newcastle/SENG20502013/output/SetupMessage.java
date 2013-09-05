package au.edu.newcastle.SENG20502013.output;

import javax.vecmath.Vector2d;

/**
 *
 * @author rossbille
 */
public class SetupMessage extends BaseMessage
{

	private long id;
	private Vector2d pos;
	//private String[] spriteLocations;
	//private long[] spriteId;
	//private int rotation;
	private String sprite;

	public SetupMessage()
	{
		super();
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public Vector2d getPos()
	{
		return pos;
	}

	public void setPos(Vector2d pos)
	{
		this.pos = pos;
	}

	public String getSprite()
	{
		return sprite;
	}

	public void setSprite(String sprite)
	{
		this.sprite = sprite;
	}
}
