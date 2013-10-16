package au.edu.newcastle.SENG48002013.game.engine.model.actions;

import au.edu.newcastle.SENG48002013.game.engine.model.environment.GameObject;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Sprite;

public class ChangeSpriteAction extends BaseAction {

    private Sprite newSprite;
    private GameObject gameObject;

    /**
	 *
	 * @param id
	 */
	public ChangeSpriteAction(long id) {
        super(id);
    }

    /**
	 *
	 * @return
	 */
	public Sprite getSprite() {
        return newSprite;
    }

    /**
	 *
	 * @param sprite
	 */
	public void setSprite(Sprite sprite) {
        this.newSprite = sprite;
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
	 * @param dt
	 * @return
	 */
	@Override
    public int doAction(double dt) {
        gameObject.setSprite(newSprite);
        return -1;
    }
}
