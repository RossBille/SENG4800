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

	public Response()
	{
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public boolean isError()
	{
		return error;
	}

	public void setError(boolean error)
	{
		this.error = error;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
	
}
