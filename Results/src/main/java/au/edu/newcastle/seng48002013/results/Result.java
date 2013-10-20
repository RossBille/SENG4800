package au.edu.newcastle.seng48002013.results;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonTypeInfo;

/**
 * main usage is between game engine and input engine 
 * these represent result codes and messages to the respond to the input engines requests
 * @author rossbille
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@class")
public class Result implements Serializable
{

    private String message;
    private String code;
    private boolean error;

    /**
	 *
	 */
	public Result()
    {
    }

	/**
	 * 
	 * @param message
	 * @param code
	 * @param error 
	 */
    public Result(String message, String code, boolean error)
    {
        this.message = message;
        this.code = code;
        this.error = error;
    }

	/**
	 * 
	 * @return 
	 */
    public String getMessage()
    {
        return message;
    }

	/**
	 *
	 * @param message  
	 */
    public void setMessage(String message)
    {
        this.message = message;
    }

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
    public boolean isError()
    {
        return error;
    }

	/**
	 *
	 * @param error  
	 */
    public void setError(boolean error)
    {
        this.error = error;
    }
}
