package au.edu.newcastle.SENG48002013.game.engine.model.actions;

import au.edu.newcastle.SENG48002013.game.engine.model.environment.Player;

public class ChangeScoreAction extends BaseAction {

    private Player player;
    private double value;
    private Type type;

    public ChangeScoreAction(long id) {
        super(id);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

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

    public enum Type {

        ADD,
        SUB,
        SET
    }
}