package au.edu.newcastle.seng48002013.instructions;

import java.io.Serializable;
import javax.vecmath.Vector3d;
import org.codehaus.jackson.annotate.JsonTypeInfo;

/**
 *
 * @author Ross Bille
 */
@JsonTypeInfo(
	use = JsonTypeInfo.Id.CLASS,
	include = JsonTypeInfo.As.PROPERTY,
	property = "@class")
public abstract class BaseInstruction implements Serializable
{
	protected Vector3d direction;
	protected float magnitude;
	protected String id;
	protected long updated;
	public BaseInstruction()
	{
		direction = new Vector3d(0d, 0d, 0d);
		magnitude = 0f;
		id = "NA";
		updated = System.currentTimeMillis();
	}
	public abstract void setId(String id);
	public abstract Vector3d getDirection();
	public abstract float getMagnitude();
	public abstract String getId();
	public abstract long getUpdated();
}