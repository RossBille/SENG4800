package au.edu.newcastle.SENG48002013.game.engine.model.environment;

public class Circle extends Shape {

    double radius;

    /**
	 *
	 * @param radius
	 */
	public Circle(double radius) {
        this.radius = radius;
    }

    /**
	 *
	 * @return
	 */
	public double getRadius() {
        return radius;
    }

    /**
	 *
	 * @param radius
	 */
	public void setRadius(double radius) {
        this.radius = radius;
        this.area = Math.PI * radius * radius;
    }
}
