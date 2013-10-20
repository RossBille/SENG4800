package au.edu.newcastle.SENG48002013.output;

import javax.vecmath.Vector2d;

/**
 * used to supply all setup information (file locations etc) to an output/frontend device
 * @author rossbille
 */
public class SetupMessage extends BaseMessage
{

    private String[] imageUrls;
    private long[] objectIds;

	/**
	 * 
	 * @param objectIds
	 * @param imageUrls 
	 */
    public SetupMessage(long[] objectIds, String[] imageUrls)
    {
        this.imageUrls = imageUrls;
        this.objectIds = objectIds;
    }

	/**
	 *
	 * @return  
	 */
    public String[] getImageUrls()
    {
        return imageUrls;
    }

	/**
	 *
	 * @param imageUrls  
	 */
    public void setImageUrls(String[] imageUrls)
    {
        this.imageUrls = imageUrls;
    }

	/**
	 *
	 * @return  
	 */
    public long[] getObjectIds()
    {
        return objectIds;
    }

	/**
	 *
	 * @param objectIds  
	 */
    public void setObjectIds(long[] objectIds)
    {
        this.objectIds = objectIds;
    }
}
