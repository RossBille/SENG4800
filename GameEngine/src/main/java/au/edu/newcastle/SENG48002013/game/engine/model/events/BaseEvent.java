package au.edu.newcastle.SENG48002013.game.engine.model.events;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import au.edu.newcastle.SENG48002013.game.engine.model.actions.IAction;

/**
 *
 * @author Peter
 */
public abstract class BaseEvent implements IEvent {

    private long id;
    private List<IAction> actions;

    /**
	 *
	 * @param id
	 */
	public BaseEvent(long id) {
        this.id = id;
        actions = new LinkedList<IAction>();
    }

    /**
	 *
	 * @return
	 */
	@Override
    public long getId() {
        return id;
    }

    /**
	 *
	 * @param id
	 */
	public void setId(long id) {
        this.id = id;
    }

    /**
	 *
	 * @param action
	 */
	public void addAction(IAction action) {
        actions.add(action);
    }

    /**
	 *
	 * @param dt
	 * @return
	 */
	protected int doActions(double dt) {
        int returnCode = -1;
        Iterator<IAction> actionIter = actions.iterator();
        while (actionIter.hasNext()) {
            int newReturnCode = actionIter.next().doAction(dt);
            if (newReturnCode != -1) {
                returnCode = newReturnCode;
            }
        }
        return returnCode;
    }

    /**
	 *
	 * @param dt
	 * @return
	 */
	@Override
    public abstract int evaluate(double dt);
}