package au.edu.newcastle.seng48002013.instructions.camera;

import au.edu.newcastle.seng48002013.instructions.BaseInstruction;

/**
 *
 * @author Ross Bille
 */
public abstract class BaseCameraInstruction extends BaseInstruction
{
    protected String id;
    /**
	 *
	 * @param id
	 */
	public abstract void setId(String id);
}
