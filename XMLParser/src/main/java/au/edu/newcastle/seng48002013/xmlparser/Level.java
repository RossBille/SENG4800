package au.edu.newcastle.seng48002013.xmlparser;

import java.util.ArrayList;

/**
 *
 * @author Bracks
 */
public class Level
{

	private int ID;
	private int levelNo;
	private int endEventId;
	private int nextLevelId;
	ArrayList<LevelEvent> levelEvents = new ArrayList<LevelEvent>();     // will be <Event>   
	ArrayList<LevelObject> levelObjects = new ArrayList<LevelObject>();     // will be <Object>

	/**
	 *
	 * @param ID
	 * @param num
	 */
	Level(String ID, String num)
	{
		this.ID = Integer.parseInt(ID);
		this.levelNo = Integer.parseInt(num);
	}

	/**
	 *
	 * @param event
	 */
	public void addEvent(LevelEvent event)
	{
		levelEvents.add(event);
	}

	/**
	 *
	 * @param object
	 */
	public void addObject(LevelObject object)
	{
		levelObjects.add(object);
	}

	/**
	 *
	 * @return
	 */
	// Mutators
	public int getId()
	{
		return ID;
	}

	/**
	 *
	 * @param id  
	 */
	public void setId(int id)
	{
		this.ID = id;
	}

	/**
	 *
	 * @return  
	 */
	public int getLevelNo()
	{
		return levelNo;
	}

	/**
	 *
	 * @param levelNo  
	 */
	public void setLevelNo(int levelNo)
	{
		this.levelNo = levelNo;
	}

	/**
	 *
	 * @return  
	 */
	public int getEndEventId()
	{
		return endEventId;
	}

	/**
	 *
	 * @param endEventId  
	 */
	public void setEndEventId(int endEventId)
	{
		this.endEventId = endEventId;
	}

	/**
	 *
	 * @return  
	 */
	public int getNextLevelId()
	{
		return nextLevelId;
	}

	public void setNextLevelId(int nextLevelId)
	{
		this.nextLevelId = nextLevelId;
	}

	public ArrayList<LevelObject> getLevelObjects()
	{
		return levelObjects;
	}

	/**
	 *
	 * @param levelObjects  
	 */
	public void setLevelObjects(ArrayList<LevelObject> levelObjects)
	{
		this.levelObjects = levelObjects;
	}

	/**
	 *
	 * @return  
	 */
	public ArrayList<LevelEvent> getLevelEvents()
	{
		return levelEvents;
	}

	/**
	 *
	 * @param levelEvents  
	 */
	public void setLevelEvents(ArrayList<LevelEvent> levelEvents)
	{
		this.levelEvents = levelEvents;
	}

	/**
	 *
	 * @param levelObj  
	 */
	public void addLevelObject(LevelObject levelObj)
	{
		levelObjects.add(levelObj);
	}

	/**
	 *
	 * @param levelEvent  
	 */
	public void addLevelEvent(LevelEvent levelEvent)
	{
		levelEvents.add(levelEvent);
	}
}
