package au.edu.newcastle.SENG48002013.game.engine.model.actions;

/**
 *
 * @author rossbille
 */
public interface IAction {

    public long getId();

    public int doAction(double dt);
}