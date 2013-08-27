package au.edu.newcastle.seng48002013.instructions.camera;

import au.edu.newcastle.seng48002013.instructions.BaseInstruction;

/**
 *
 * @author Ross Bille
 */
public abstract class BaseCameraInstruction extends BaseInstruction
{
	//put in all common camera operations here
    protected String id;
    public abstract void setId(String id);
}
