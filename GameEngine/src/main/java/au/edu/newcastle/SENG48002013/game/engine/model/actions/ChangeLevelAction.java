package au.edu.newcastle.SENG48002013.game.engine.model.actions;

public class ChangeLevelAction extends BaseAction {

    private long levelId;

    /**
	 *
	 * @param id
	 */
	public ChangeLevelAction(long id) {
        super(id);
    }

    /**
	 *
	 * @return
	 */
	public long getLevelId() {
        return levelId;
    }

    /**
	 *
	 * @param levelId
	 */
	public void setLevelId(long levelId) {
        this.levelId = levelId;
    }

	/**
	 *
	 * @param dt
	 * @return
	 */
	@Override
    public int doAction(double dt) {
        return (int) levelId;
    }
}
