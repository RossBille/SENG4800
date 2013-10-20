package au.edu.newcastle.SENG48002013.game.engine.model.actions;

import javax.vecmath.Vector2d;

import au.edu.newcastle.SENG48002013.game.engine.model.environment.GameObject;

public class ChangeComponentAction extends BaseAction {

    private GameObject gameObject;
    private Vector2d value;
    private Type type;
    private Component component;

    /**
	 *
	 * @param id
	 */
	public ChangeComponentAction(long id) {
        super(id);
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
	 * @return
	 */
	public Component getComponent() {
        return component;
    }

    /**
	 *
	 * @param component
	 */
	public void setComponent(Component component) {
        this.component = component;
    }

	/**
	 *
	 * @param dt
	 * @return
	 */
	@Override
    public int doAction(double dt) {
        switch (type) {
            case ADD: {
                switch (component) {
                    case POS:
                        gameObject.getNextPos().add(value);
                        break;
                    case VEL:
                        gameObject.getNextVel().add(value);
                        break;
                    case ACC:
                        gameObject.getNextAcc().add(value);
                        break;
                }
            }
            break;
            case SUB: {
                switch (component) {
                    case POS:
                        gameObject.getNextPos().sub(value);
                        break;
                    case VEL:
                        gameObject.getNextVel().sub(value);
                        break;
                    case ACC:
                        gameObject.getNextAcc().sub(value);
                        break;
                }
            }
            break;
            case SET: {
                switch (component) {
                    case POS:
                        gameObject.getNextPos().set(value);
                        break;
                    case VEL:
                        gameObject.getNextVel().set(value);
                        break;
                    case ACC:
                        gameObject.getNextAcc().set(value);
                        break;
                }
            }
            break;
        }
        return -1;
    }

    /**
	 *
	 */
	public enum Component {

        POS,
        VEL,
        ACC
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