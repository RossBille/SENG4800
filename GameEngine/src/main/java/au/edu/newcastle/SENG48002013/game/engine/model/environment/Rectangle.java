package au.edu.newcastle.SENG48002013.game.engine.model.environment;

import javax.vecmath.Vector2d;

public class Rectangle extends Shape {

    private Vector2d size;

    /**
	 *
	 * @param size
	 */
	public Rectangle(Vector2d size) {
        this.size = new Vector2d(size);
    }

    /**
	 *
	 * @return
	 */
	public Vector2d getSize() {
        return size;
    }

    /**
	 *
	 * @param size
	 */
	public void setSize(Vector2d size) {
        this.size.set(size);
        this.area = size.x * size.y;
    }
}