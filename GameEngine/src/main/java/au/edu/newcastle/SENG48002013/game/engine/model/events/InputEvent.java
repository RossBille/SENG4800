package au.edu.newcastle.SENG48002013.game.engine.model.events;

import javax.vecmath.Vector2d;

import au.edu.newcastle.SENG48002013.game.engine.model.environment.GameObject;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Player;

public class InputEvent extends BaseEvent {

    private Player player;
    private GameObject gameObject;
    private Vector2d value;
    private Type type;

    public InputEvent(long id) {
        super(id);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public Vector2d getValue() {
        return value;
    }

    public void setValue(Vector2d value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int evaluate(double dt) {
        boolean triggerEvents = false;
        boolean isPosition = player.getInput().isPosition();
        Vector2d inputVec = player.getInput().getValue();
        switch (type) {
            case ANY: {
                if (inputVec.lengthSquared() != 0) {
                    triggerEvents = true;
                }
            }
            break;
            case LEFT: {
                if (!isPosition && inputVec.x < 0) {
                    triggerEvents = true;
                }
            }
            break;
            case RIGHT: {
                if (!isPosition && inputVec.x > 0) {
                    triggerEvents = true;
                }
            }
            break;
            case UP: {
                if (!isPosition && inputVec.y < 0) {
                    triggerEvents = true;
                }
            }
            break;
            case DOWN: {
                if (!isPosition && inputVec.y > 0) {
                    triggerEvents = true;
                }
            }
            break;
            case VECTOR: {
                double dot1 = value.dot(value);
                dot1 /= value.lengthSquared();
                double dot2 = value.dot(inputVec);
                dot2 /= (value.length() * inputVec.length());
                //If the two vectors are 90% alike
                if (dot1 != 0 && dot2 / dot1 > 0.9) {
                    triggerEvents = true;
                }
            }
            break;
        }
        if (triggerEvents) {
            return doActions(dt);
        } else {
            return -1;
        }
    }

    public enum Type {

        LEFT,
        RIGHT,
        UP,
        DOWN,
        VECTOR,
        ANY
    }
}