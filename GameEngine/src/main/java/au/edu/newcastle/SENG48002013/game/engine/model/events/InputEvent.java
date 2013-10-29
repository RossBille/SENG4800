package au.edu.newcastle.SENG48002013.game.engine.model.events;

import javax.vecmath.Vector2d;

import au.edu.newcastle.SENG48002013.game.engine.model.environment.GameObject;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Player;

/**
 *
 * @author rossbille
 */
public class InputEvent extends BaseEvent {

    private Player player;
    private GameObject gameObject;
    private Vector2d value;
    private Type type;

    /**
	 *
	 * @param id
	 */
	public InputEvent(long id) {
        super(id);
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
	public GameObject getGameObject() {
        return gameObject;
    }

    /**
	 *
	 * @param gameObject
	 */
	public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    /**
	 *
	 * @return
	 */
	public Vector2d getValue() {
        return value;
    }

    /**
	 *
	 * @param value
	 */
	public void setValue(Vector2d value) {
        this.value = value;
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
    public int evaluate(double dt) {
        if(player.getInput() == null) return -1;
        boolean triggerEvents = false;
        boolean isPosition = player.getInput().isPosition();
        Vector2d inputVec = player.getInput().getValue();
        //System.out.println("X:" + inputVec.x);
        //System.out.println("Y:" + inputVec.y);
        //System.out.println("IsPos:" + isPosition);
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
                if (!isPosition && inputVec.y > 0) {
                    triggerEvents = true;
                }
            }
            break;
            case DOWN: {
                if (!isPosition && inputVec.y < 0) {
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

    /**
	 *
	 */
	public enum Type {
        LEFT,
        RIGHT,
        UP,
        DOWN,
        VECTOR,
        ANY
    }
}