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
    protected String iD;

    /**
	 *
	 * @param id
	 * @param os
	 */
	public BasePhoneInstruction(String id, String os)
    {
        this.phoneId = id;
        this.os = os;
        this.iD = os + "," + phoneId + "," + updated;
    }

	/**
	 * 
	 * @param s 
	 */
    public void setId(String s)
    {
        this.iD = s;
    }

	/**
	 *
	 * @return  
	 */
    @Override
    public String getId()
    {
        return this.iD;
    }

	/**
	 *
	 * @return  
	 */
    public String getPhoneId()
    {
        return phoneId;
    }

	/**
	 *
	 * @return  
	 */
    public String getOs()
    {
        return os;
    }

	/**
	 *
	 * @param phoneId  
	 */
    public void setPhoneId(String phoneId)
    {
        this.phoneId = phoneId;
    }

	/**
	 *
	 * @param os  
	 */
    public void setOs(String os)
    {
        this.os = os;
    }
}