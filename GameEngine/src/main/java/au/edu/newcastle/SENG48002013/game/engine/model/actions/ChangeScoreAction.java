package au.edu.newcastle.SENG48002013.game.engine.model.actions;

import au.edu.newcastle.SENG48002013.game.engine.model.environment.Player;

public class ChangeScoreAction extends BaseAction {

    private Player player;
    private double value;
    private Type type;

    /**
	 *
	 * @param id
	 */
	public ChangeScoreAction(long id) {
        super(id);
    }

    /**
	 *
	 * @return
	 */
	public double getValue() {
        return value;
    }

    /**
	 *
	 * @param value
	 */
	public void setValue(double value) {
        this.value = value;
    }

    /**
	 *
	 * @return
	 */
	public Player getPlayer() {
        return player;
    }

    /**
	 *
	 * @param player
	 */
	public void setPlayer(Player player) {
        this.player = player;
    }

    /**
	 *
	 * @return
	 */
	public Type getType() {
        return type;
    }

    /**
	 *
	 * @param type
	 */
	public void setType(Type type) {
        this.type = type;
    }

	/**
	 *
	 * @param dt
	 * @return
	 */
	@Override
    public int doAction(double dt) {
        switch (type) {
            case ADD:
                player.setScore(player.getScore() + value);
                break;
            case SUB:
                player.setScore(player.getScore() - value);
                break;
            case SET:
                player.setScore(value);
        }
        return -1;
    }

    /**
	 *
	 */
	public enum Type {

        ADD,
        SUB,
        SET
    }
}