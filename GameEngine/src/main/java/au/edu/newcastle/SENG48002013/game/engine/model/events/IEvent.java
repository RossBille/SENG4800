package au.edu.newcastle.SENG48002013.game.engine.model.events;

/**
 *
 * @author Peter
 */
public interface IEvent {

    public long getId();

    public int evaluate(double dt);
}