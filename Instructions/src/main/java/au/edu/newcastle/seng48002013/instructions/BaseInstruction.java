package au.edu.newcastle.seng48002013.instructions;

import java.io.Serializable;
import javax.vecmath.Vector3d;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
/**
 *
 * @author Ross Bille
 */
@JsonTypeInfo(
	use = JsonTypeInfo.Id.CLASS,
	include = JsonTypeInfo.As.PROPERTY,
	property = "@class")
@JsonIgnoreProperties({"updated"})
public abstract class BaseInstruction implements Serializable
{
	protected float magnitude;
	protected long updated;
	public BaseInstruction()
	{
		updated = System.currentTimeMillis();
	}
    
	public abstract Vector3d getDirection();
	public abstract float getMagnitude();
	public abstract String getId();
	public abstract long getUpdated();
}