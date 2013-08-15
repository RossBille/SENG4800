package au.edu.newcastle.SENG48002013.instructions.phone;

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
	public Vector3d getDirection()
	{
		return new Vector3d(x1 - x2, y1 - y2, 0);
	}

	@Override
	public long getUpdated()
	{
		return updated;
	}

}
