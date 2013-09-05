package au.edu.newcastle.SENG20502013.output;

import javax.vecmath.Vector2d;

/**
 *
 * @author rossbille
 */
public class SetupMessage extends BaseMessage
{
	private String[] imageUrls;
	private long[] objectIds;
	public SetupMessage(long[] objectIds, String[] imageUrls)
	{
		this.imageUrls = imageUrls;
		this.objectIds = objectIds;
	}
	public String[] getImageUrls()
	{
		return imageUrls;
	}

	public void setImageUrls(String[] imageUrls)
	{
		this.imageUrls = imageUrls;
	}

	public long[] getObjectIds()
	{
		return objectIds;
	}

	public void setObjectIds(long[] objectIds)
	{
		this.objectIds = objectIds;
	}
}
