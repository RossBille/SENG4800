package au.edu.newcastle.SENG48002013.input.instruction;

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
public class BaseInstruction
{

	protected long lastUpdated;
	protected boolean retrieved;
	protected Vector3d direction;
	protected float magnitude;

	public BaseInstruction()
	{
		lastUpdated = 0l;
		direction = new Vector3d(0d, 0d, 0d);
		magnitude = 0f;
		retrieved = false;
	}

	public Vector3d getDirection()
	{
		return direction;
	}
}
