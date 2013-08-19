package au.edu.newcastle.seng48002013.results;

/**
 * 
 * @author rossbille
 */
public class Result
{
	private String message;	
	private String code;
	private boolean error;
	public Result()
	{
	}

	public Result(String message, String code, boolean error)
	{
		this.message = message;
		this.code = code;
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

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
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
}
