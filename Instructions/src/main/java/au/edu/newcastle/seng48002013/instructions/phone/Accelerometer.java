package au.edu.newcastle.seng48002013.instructions.phone;

import javax.vecmath.Vector3d;

/**
 *
 * @author Ross Bille
 */
public class Accelerometer extends BasePhoneInstruction
{

    public Accelerometer(String os, String iD)
    {
        super(iD, os);
    }
    //need variables to represent acc movement

    @Override
    public Vector3d getDirection()
    {
        throw new UnsupportedOperationException("Implement method to get direction");
    }

    @Override
    public long getUpdated()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}