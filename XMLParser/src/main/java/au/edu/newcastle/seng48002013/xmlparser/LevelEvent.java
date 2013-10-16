package au.edu.newcastle.seng48002013.xmlparser;

/**
 *
 * @author Bracks
 */
import java.util.ArrayList;

public class LevelEvent
{

	// Variables
	private int eventId;
	private String eventType;
	private String rule;
	private int triggerActionId;
	private boolean collision;
	private int objectId1;
	private int objectId2;

	// Mutators
	/**
	 * 
	 * @return 
	 */
	public int getEventId()
	{
		return eventId;
	}

	/**
	 *
	 * @param eventId  
	 */
	public void setEventId(int eventId)
	{
		this.eventId = eventId;
	}

	/**
	 *
	 * @return  
	 */
	public String getEventType()
	{
		return eventType;
	}

	/**
	 *
	 * @param eventType  
	 */
	public void setEventType(String eventType)
	{
		this.eventType = eventType;
	}

	/**
	 *
	 * @return  
	 */
	public String getRule()
	{
		return rule;
	}

	/**
	 *
	 * @param rule  
	 */
	public void setRule(String rule)
	{
		this.rule = rule;
	}

	/**
	 *
	 * @return  
	 */
	public int getTriggerActionId()
	{
		return triggerActionId;
	}

	/**
	 *
	 * @param triggerActionId  
	 */
	public void setTriggerActionId(int triggerActionId)
	{
		this.triggerActionId = triggerActionId;
	}

	/**
	 *
	 * @return  
	 */
	public boolean isCollision()
	{
		return collision;
	}

	/**
	 *
	 * @param collision  
	 */
	public void setCollision(boolean collision)
	{
		this.collision = collision;
	}

	/**
	 *
	 * @return  
	 */
	// Mutators
	public int getObjectId1()
	{
		return objectId1;
	}

	/**
	 *
	 * @param objectId1  
	 */
	public void setObjectId1(int objectId1)
	{
		this.objectId1 = objectId1;
	}

	/**
	 *
	 * @return  
	 */
	public int getObjectId2()
	{
		return objectId2;
	}

	/**
	 *
	 * @param objectId2  
	 */
	public void setObjectId2(int objectId2)
	{
		this.objectId2 = objectId2;
	}
}
