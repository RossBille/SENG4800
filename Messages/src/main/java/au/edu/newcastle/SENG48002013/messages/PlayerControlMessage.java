package au.edu.newcastle.SENG48002013.messages;

import javax.vecmath.Vector3d;

/**
 * used to tell the game engine if a player has joined or left the game
 *
 * @author rossbille
 */
public class PlayerControlMessage extends BaseMessage
{

	private String code;
	private Vector3d direction;

	/**
	 * 
	 * @return 
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 *
	 * @param code  
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 *
	 * @return  
	 */
	public Vector3d getDirection()
	{
		return direction;
	}

	/**
	 *
	 * @param direction  
	 */
	public void setDirection(Vector3d direction)
	{
		this.direction = direction;
	}
}
