package au.edu.newcastle.SENG48002013.game.engine.connections.input;

import javax.vecmath.Vector2d;

/**
 * Implementation of IInput
 * an Input represents a message that comes in from an external source (generally a client device)
 * to instruct an object in the game to react a certain way.
 */
public class Input implements IInput
{

    private long id;
    private Vector2d value;
    private boolean position;
    private boolean accessed;

    /**
	 *
	 * @param id
	 */
	public Input(long id)
    {
        this.id = id;
        value = new Vector2d();
        accessed = false;
    }

    /**
	 *
	 * @return
	 */
	@Override
    public long getId()
    {
        return id;
    }

    /**
	 *
	 * @param id
	 */
	@Override
    public void setId(long id)
    {
        this.id = id;
    }

    /**
	 *
	 * @return
	 */
	@Override
    public Vector2d getValue()
    {
        accessed = true;
        return value;
    }

    /**
	 *
	 * @param value
	 */
	@Override
    public void setValue(Vector2d value)
    {
        this.value = value;
        accessed = false;
    }

    /**
	 *
	 * @return
	 */
	@Override
    public boolean isPosition()
    {
        return position;
    }

    /**
	 *
	 * @param position
	 */
	@Override
    public void setPosition(boolean position)
    {
        this.position = position;
    }

    /**
	 *
	 * @return
	 */
	@Override
    public Vector2d getPos()
    {
        return getValue();
    }

    /**
	 *
	 * @return
	 */
	public boolean isAccessed()
    {
        return accessed;
    }

    /**
	 *
	 */
	public void flush()
    {
        if (accessed)
        {
            position = false;
            value.set(0, 0);
            accessed = false;
        }
    }
}