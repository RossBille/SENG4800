package au.edu.newcastle.seng48002013.instructions.phone;

import au.edu.newcastle.seng48002013.instructions.BaseInstruction;

/**
 *
 * @author Ross Bille
 */
public abstract class BasePhoneInstruction extends BaseInstruction
{
	protected String phoneId;
	protected String os;
	public String getId()
	{
		return os+","+phoneId+","+updated;
	}

	public String getPhoneId()
	{
		return phoneId;
	}

	public String getOs()
	{
		return os;
	}

	public void setPhoneId(String phoneId)
	{
		this.phoneId = phoneId;
	}

	public void setOs(String os)
	{
		this.os = os;
	}
	
}