package au.edu.newcastle.SENG48002013.messages;

/**
 * used to tell the game engine what actions the connected players are taking
 *
 * @author rossbille
 */
public class PlayerNumberMessage extends BaseMessage
{

	private boolean connecting;//true is connecting, false if disconnecting

	/**
	 * 
	 * @return 
	 */
	public boolean isConnecting()
	{
		return connecting;
	}

	/**
	 *
	 * @param connecting  
	 */
	public void setConnecting(boolean connecting)
	{
		this.connecting = connecting;
	}
}
