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

	public BaseMessage()
	{
		timeStamp = System.currentTimeMillis();
	}

	public float getTimeStamp()
	{
		return timeStamp;
	}

	public int getPlayer()
	{
		return player;
	}

	public void setPlayer(int player)
	{
		this.player = player;
	}
}
