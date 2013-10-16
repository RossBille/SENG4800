package au.edu.newcastle.SENG48002013.game.engine.model.events;

/**
 *
 * @author Peter 
 */
public class StepEvent extends BaseEvent {

    /**
	 *
	 * @param id
	 */
	public StepEvent(long id) {
        super(id);
    }

	/**
	 * 
	 * @param dt
	 * @return 
	 */
	@Override
    public int evaluate(double dt) {
        return doActions(dt);
    }
}