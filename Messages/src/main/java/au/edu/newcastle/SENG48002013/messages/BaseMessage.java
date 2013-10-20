package au.edu.newcastle.SENG48002013.messages;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonTypeInfo;

/**
 *
 * @author rossbille
 */
@JsonTypeInfo(
	use = JsonTypeInfo.Id.CLASS,
	include = JsonTypeInfo.As.PROPERTY,
	property = "@class")
@JsonIgnoreProperties(
{
	"timeStamp"
})
public abstract class BaseMessage
{

	protected int player;
	protected float timeStamp;

	/**
	 * 
	 */
	public BaseMessage()
	{
		timeStamp = System.currentTimeMillis();
	}

	/**
	 *
	 * @return  
	 */
	public float getTimeStamp()
	{
		return timeStamp;
	}

	/**
	 *
	 * @return  
	 */
	public int getPlayer()
	{
		return player;
	}

	/**
	 *
	 * @param player  
	 */
	public void setPlayer(int player)
	{
		this.player = player;
	}
}
