package au.edu.newcastle.seng48002013.instructions.phone;

import javax.vecmath.Vector3d;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author Ross Bille, Rowan Burgess
 */
public class TouchScreen extends BasePhoneInstruction
{

    private float x1, x2, y1, y2;


    /*
     * If you want to create spcific touch screen directions
     */
    @JsonCreator
    public TouchScreen(
            @JsonProperty("x1") float x1,
            @JsonProperty("x2") float x2,
            @JsonProperty("y1") float y1,
            @JsonProperty("y2") float y2,
            @JsonProperty("os") String os,
            @JsonProperty("iD") String iD)
    {
        super(iD, os);
        this.setValues(x1, x2, y1, y2);
    }

    /*
     * String as constructor arg, eg "left"
     */
    public TouchScreen(String direction, String os, String iD)
    {
        super(iD, os);

        switch (direction.toLowerCase())
        {
            case "left":
                this.setValues(1, 0, 0, 0);
                break;
            case "right":
                this.setValues(0, 1, 0, 0);
                break;
            case "up":
                this.setValues(0, 0, 0, 1);
                break;
            case "down":
                this.setValues(0, 0, 1, 0);
                break;
            default:
                this.setValues(0, 0, 0, 0);
        }

    }

    private void setValues(float x1, float x2, float y1, float y2)
    {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public void setX1(float x1)
    {
        this.x1 = x1;
    }

    public void setX2(float x2)
    {
        this.x2 = x2;
    }

    public void setY1(float y1)
    {
        this.y1 = y1;
    }

    public void setY2(float y2)
    {
        this.y2 = y2;
    }

    public float getX1()
    {
        return x1;
    }

    public float getX2()
    {
        return x2;
    }

    public float getY1()
    {
        return y1;
    }

    public float getY2()
    {
        return y2;
    }

    @Override
    public Vector3d getDirection()
    {
        return new Vector3d(x2 - x1, y2 - y1, 0);
    }

    @Override
    public long getUpdated()
    {
        return updated;
    }
}
