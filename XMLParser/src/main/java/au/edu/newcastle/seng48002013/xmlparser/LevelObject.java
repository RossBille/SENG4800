package au.edu.newcastle.seng48002013.xmlparser;

/**
 *
 * @author Bracks
 */
public class LevelObject extends GameObject
{

	// Variables
	private String objectType;
	private String shape;
	private int initSpeed;
	private int maxSpeed;
	private int position;
	private int direction;
	private boolean solid;
	private boolean visible;

	// Mutators
	/**
	 * 
	 * @return 
	 */
	public boolean isVisible()
	{
		return this.visible;
	}

	/**
	 *
	 * @param visible  
	 */
	public void setVisible(String visible)
	{
		if (visible.equals("yes"))
		{
			this.visible = true;
		} else
		{
			this.visible = false;
		}
	}

	/**
	 *
	 * @return  
	 */
	public boolean isSolid()
	{
		return this.solid;
	}

	/**
	 *
	 * @param solid  
	 */
	public void setSolid(String solid)
	{
		if (solid.equals("yes"))
		{
			this.solid = true;
		} else
		{
			this.solid = false;
		}
	}

	/**
	 *
	 * @return  
	 */
	public String getObjectType()
	{
		return objectType;
	}

	/**
	 *
	 * @param objectType  
	 */
	public void setObjectType(String objectType)
	{
		this.objectType = objectType;
	}

	/**
	 *
	 * @return  
	 */
	public int getInitSpeed()
	{
		return initSpeed;
	}

	/**
	 *
	 * @param initSpeed  
	 */
	public void setInitSpeed(int initSpeed)
	{
		this.initSpeed = initSpeed;
	}

	/**
	 *
	 * @return  
	 */
	public int getMaxSpeed()
	{
		return maxSpeed;
	}

	/**
	 *
	 * @param maxSpeed  
	 */
	public void setMaxSpeed(int maxSpeed)
	{
		this.maxSpeed = maxSpeed;
	}

	/**
	 *
	 * @return  
	 */
	public String getShape()
	{
		return shape;
	}

	/**
	 *
	 * @param shape  
	 */
	public void setShape(String shape)
	{
		this.shape = shape;
	}

	/**
	 *
	 * @return  
	 */
	public int getPosition()
	{
		return position;
	}

	/**
	 *
	 * @param position  
	 */
	public void setPosition(int position)
	{
		this.position = position;
	}

	/**
	 *
	 * @return  
	 */
	public int getDirection()
	{
		return direction;
	}

	/**
	 *
	 * @param direction  
	 */
	public void setDirection(int direction)
	{
		this.direction = direction;
	}
}
