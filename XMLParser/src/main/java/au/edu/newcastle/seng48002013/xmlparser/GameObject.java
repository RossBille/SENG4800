package au.edu.newcastle.seng48002013.xmlparser;

/**
 *
 * @author Bracks
 */
import java.util.ArrayList;

public class GameObject
{

	// Variables
	private String name;
	private int id;
	private int posX;
	private int posY;
	private String colour;
	private int spriteSpeed;
	private ArrayList<String> sprite = new ArrayList<String>();

	// Mutators
	/**
	 *
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 *
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 *
	 * @return
	 */
	public int getId()
	{
		return id;
	}

	/**
	 *
	 * @param id
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 *
	 * @return
	 */
	public int getPosX()
	{
		return posX;
	}

	/**
	 *
	 * @param position_x
	 */
	public void setPosX(int position_x)
	{
		this.posX = position_x;
	}

	/**
	 *
	 * @return
	 */
	public int getPosY()
	{
		return posY;
	}

	/**
	 *
	 * @param position_y
	 */
	public void setPosY(int position_y)
	{
		this.posY = position_y;
	}

	/**
	 *
	 * @return
	 */
	public String getColour()
	{
		return colour;
	}

	/**
	 *
	 * @param colour
	 */
	public void setColour(String colour)
	{
		this.colour = colour;
	}

	/**
	 *
	 * @return
	 */
	public int getSpriteSpeed()
	{
		return spriteSpeed;
	}

	/**
	 *
	 * @param spriteSpeed
	 */
	public void setSpriteSpeed(int spriteSpeed)
	{
		this.spriteSpeed = spriteSpeed;
	}

	/**
	 *
	 * @return
	 */
	public ArrayList<String> getSprite()
	{
		return sprite;
	}

	/**
	 *
	 * @param sprite
	 */
	public void setSprite(ArrayList<String> sprite)
	{
		this.sprite = sprite;
	}

	/**
	 *
	 * @param newSprite
	 */
	public void addSprite(String newSprite)
	{
		sprite.add(newSprite);
	}
}
