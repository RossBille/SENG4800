package au.edu.newcastle.SENG48002013.game.engine.connections.input;

import javax.vecmath.Vector2d;

/**
 *
 */
public class Input implements IInput
{

    private long id;
    private Vector2d value;
    private boolean position;
    private boolean accessed;

    public Input(long id)
    {
        this.id = id;
        value = new Vector2d();
        accessed = false;
    }

    @Override
    public long getId()
    {
        return id;
    }

    @Override
    public void setId(long id)
    {
        this.id = id;
    }

    @Override
    public Vector2d getValue()
    {
        accessed = true;
        return value;
    }

    @Override
    public void setValue(Vector2d value)
    {
        this.value = value;
        accessed = false;
    }

    @Override
    public boolean isPosition()
    {
        return position;
    }

    @Override
    public void setPosition(boolean position)
    {
        this.position = position;
    }

    @Override
    public Vector2d getPos()
    {
        return getValue();
    }

    public boolean isAccessed()
    {
        return accessed;
    }

    public void flush()
    {
        if (accessed)
        {
            position = false;
            value.set(0, 0);
        }
    }
}