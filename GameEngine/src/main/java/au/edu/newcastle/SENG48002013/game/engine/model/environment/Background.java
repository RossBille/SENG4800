package au.edu.newcastle.SENG48002013.game.engine.model.environment;

public class Background extends Sprite {

    private PositionType positionType;

    /**
	 *
	 * @param id
	 */
	public Background(long id) {
        super(id);
    }

    /**
	 *
	 * @return
	 */
	public PositionType getPositionType() {
        return positionType;
    }

    /**
	 *
	 * @param positionType
	 */
	public void setPositionType(PositionType positionType) {
        this.positionType = positionType;
    }

    /**
	 *
	 */
	public enum PositionType {

        TILED,
        STRETCH,
        FILL,
        CENTER
    }
}