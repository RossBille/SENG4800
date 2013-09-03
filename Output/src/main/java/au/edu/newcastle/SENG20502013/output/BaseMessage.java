package au.edu.newcastle.SENG20502013.output;

/**
 *
 * @author rossbille
 */
public abstract class BaseMessage 
{
	protected long timeStamp;
	public long getTimeStamp()
	{
			return timeStamp;
	}
	public BaseMessage()
	{
			timeStamp = System.currentTimeMillis();
	}
}
