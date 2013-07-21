package au.edu.newcastle.seng48002013.instructions.phone;

import javax.vecmath.Vector3d;

/**
 *
 * @author Ross Bille
 */
public class Accelerometer extends BasePhoneInstruction
{
	//need variables to represent acc movement
	@Override
	public Vector3d getDirection()
	{
		throw new UnsupportedOperationException("Implement method to get direction");
	}

	@Override
	public void setId(String id)
	{
		throw new UnsupportedOperationException("Not supported yet."); 
	}

	@Override
	public float getMagnitude()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public long getUpdated()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
}