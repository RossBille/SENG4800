package au.edu.newcastle.seng48002013.instructions.phone;

import javax.vecmath.Vector3d;

/**
 *
 * @author Ross Bille
 */
public class Accelerometer extends BasePhoneInstruction
{

    /**
	 *
	 * @param os
	 * @param iD
	 */
	public Accelerometer(String os, String iD)
    {
        super(iD, os);
    }

    /**
	 *
	 * @return
	 */
	@Override
    public Vector3d getDirection()
    {
        throw new UnsupportedOperationException("Implement method to get direction");
    }

    /**
	 *
	 * @return
	 */
	@Override
    public long getUpdated()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}