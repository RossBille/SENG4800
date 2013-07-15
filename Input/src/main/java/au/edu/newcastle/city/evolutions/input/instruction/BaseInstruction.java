/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.city.evolutions.input.instruction;
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
public class BaseInstruction {
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
    public Vector3d getInstruction()
    {
        return direction;
    }
    
}
