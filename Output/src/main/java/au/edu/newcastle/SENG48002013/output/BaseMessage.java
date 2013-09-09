package au.edu.newcastle.SENG48002013.output;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author rossbille
 */
public abstract class BaseMessage 
{
	protected long timeStamp;
	@JsonIgnore
	public long getTimeStamp()
	{
			return timeStamp;
	}
	public BaseMessage()
	{
			timeStamp = System.currentTimeMillis();
	}
}
