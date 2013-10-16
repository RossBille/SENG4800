package au.edu.newcastle.SENG48002013.game.engine.model.actions;

/**
 *
 * @author rossbille
 */
public abstract class BaseAction implements IAction {

    private long id;

    /**
	 *
	 * @param id
	 */
	public BaseAction(long id) {
        this.id = id;
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

    @Override
    public abstract int doAction(double dt);
}
