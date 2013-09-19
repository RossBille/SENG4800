package au.edu.newcastle.SENG48002013.game.engine.model.actions;

public class ChangeLevelAction extends BaseAction {

    private long levelId;

    public ChangeLevelAction(long id) {
        super(id);
    }

    public long getLevelId() {
        return levelId;
    }

    public void setLevelId(long levelId) {
        this.levelId = levelId;
    }

    public int doAction(double dt) {
        return (int) levelId;
    }
}
