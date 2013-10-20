package au.edu.newcastle.SENG48002013.output;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Abstract message class to be used between the game engine and frontend devices
 * @author rossbille
 */
public abstract class BaseMessage
{

    protected long timeStamp;

	/**
	 * 
	 * @return 
	 */
    @JsonIgnore
    public long getTimeStamp()
    {
        return timeStamp;
    }

	/**
	 * 
	 */
    public BaseMessage()
    {
        timeStamp = System.currentTimeMillis();
    }
}
