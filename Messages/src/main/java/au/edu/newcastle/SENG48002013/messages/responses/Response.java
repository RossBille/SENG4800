package au.edu.newcastle.SENG48002013.messages.responses;

import java.io.Serializable;

/**
 *
 * @author rossbille
 */
public class Response implements Serializable
{
	private int code;
	private boolean error;
	private String message;

	/**
	 *
	 */
	public Response()
	{
	}

	/**
	 * 
	 * @return 
	 */
	public int getCode()
	{
		return code;
	}

	/**
	 *
	 * @param code  
	 */
	public void setCode(int code)
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
	
}
