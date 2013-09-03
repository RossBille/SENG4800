package au.edu.newcastle.seng48002013.instructions.phone;

import javax.vecmath.Vector3d;

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
    public TouchScreen(float x1, float x2, float y1, float y2, String os, String iD)
    {
        this.setValues(x1, x2, y1, y2);
        super.os = os;
        super.phoneId = iD;
    }

    /*
     * String as constructor arg, eg "left"
     */
    public TouchScreen(String direction, String os, String iD)
    {
        super.os = os;
        super.phoneId = iD;

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

    @Override
    public Vector3d getDirection()
    {
        return new Vector3d(x1 - x2, y1 - y2, 0);
    }

    @Override
    public long getUpdated()
    {
        return updated;
    }
}
