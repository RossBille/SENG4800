package au.edu.newcastle.seng48002013.instructions.phone;

import javax.vecmath.Vector3d;

/**
 *
 * @author Ross Bille
 */
public class TouchScreen extends BasePhoneInstruction
{
	private float x1, x2, y1, y2;
	
	public void setX1(float x1)
	{
		this.x1 = x1;
	}

	public void setX2(float x2)
	{
		this.x2 = x2;
	}

	public void setY1(float y1)
	{
		this.y1 = y1;
	}

	public void setY2(float y2)
	{
		this.y2 = y2;
	}

	@Override
	public void setId(String id)
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Vector3d getDirection()
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public float getMagnitude()
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public long getUpdated()
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
