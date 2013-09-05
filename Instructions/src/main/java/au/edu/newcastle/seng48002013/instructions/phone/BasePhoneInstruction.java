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
    
    public BasePhoneInstruction(String id, String os)
    {
        this.phoneId = id;
        this.os = os;
        this.iD = os + "," + phoneId + "," + updated;       
    }

    public void setId(String s)
    {
        this.iD = s;
    }

    @Override
    public String getId()
    {
        return this.iD;
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