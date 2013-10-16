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
@JsonIgnoreProperties(
        {
    "updated", "direction"
})
public abstract class BaseInstruction implements Serializable
{

    protected long updated;

    /**
	 *
	 */
	public BaseInstruction()
    {
        updated = System.currentTimeMillis();
    }

    /**
	 *
	 * @return
	 */
	public abstract Vector3d getDirection();

    /**
	 *
	 * @return
	 */
	public abstract String getId();

    /**
	 *
	 * @return
	 */
	public abstract long getUpdated();
}