package au.edu.newcastle.SENG48002013.game.engine.connections.input;

import javax.vecmath.Vector2d;

/**
 *
 * @author Ross
 */
public interface IInput
{

    public long getId();

    public void setId(long id);

    public boolean isPosition();

    public void setPosition(boolean position);

    public Vector2d getValue();

    public void setValue(Vector2d pos);

    public Vector2d getPos();
}