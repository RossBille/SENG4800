package au.edu.newcastle.SENG48002013.game.engine.model.events;

import au.edu.newcastle.SENG48002013.game.engine.model.environment.Circle;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.GameObject;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Rectangle;
import javax.vecmath.Vector2d;

/**
 *
 * @author Peter
 */
public class CollisionEvent extends BaseEvent {

    private GameObject object1;
    private GameObject object2;
    private boolean allowOverlap;

    /**
	 *
	 * @param id
	 */
	public CollisionEvent(long id) {
        super(id);
    }

    /**
	 *
	 * @param id
	 * @param object1
	 * @param object2
	 */
	public CollisionEvent(long id, GameObject object1, GameObject object2) {
        super(id);
        this.object1 = object1;
        this.object2 = object2;
    }

    /**
	 *
	 * @return
	 */
	public GameObject getObject1() {
        return object1;
    }

    /**
	 *
	 * @param object1
	 */
	public void setObject1(GameObject object1) {
        this.object1 = object1;
    }

    /**
	 *
	 * @return
	 */
	public GameObject getObject2() {
        return object2;
    }

    /**
	 *
	 * @param object2
	 */
	public void setObject2(GameObject object2) {
        this.object2 = object2;
    }

    /**
	 *
	 * @return
	 */
	public boolean isAllowOverlap() {
        return allowOverlap;
    }

    /**
	 *
	 * @param allowOverlap
	 */
	public void setAllowOverlap(boolean allowOverlap) {
        this.allowOverlap = allowOverlap;
    }

	/**
	 * 
	 * @param dt
	 * @return 
	 */
    @Override
    public int evaluate(double dt) {
        int returnCode = -1;
        boolean intersects = false;
        //If BOTH rectangle shape
        if (object1.getShape() instanceof Rectangle && object2.getShape() instanceof Rectangle) {
            intersects = bothRectangles();
        } //If BOTH Circle shapes
        else if (object1.getShape() instanceof Circle && object2.getShape() instanceof Circle) {
            intersects = bothCircles();
        } //One Circle one Rectangle shape
        else {
            intersects = rectangleAndCircle();
        }
        if (intersects) {
            returnCode = doActions(dt);
        }
        return returnCode;
    }

    private boolean bothCircles() {
        boolean intersects = false;
        //Check for intersection
        double radius1 = ((Circle) object1.getShape()).getRadius();
        double radius2 = ((Circle) object2.getShape()).getRadius();
        if ((object1.getNextPos().x - object2.getNextPos().x) * (object1.getNextPos().x - object2.getNextPos().x)
                + (object1.getNextPos().y - object2.getNextPos().y) * (object1.getNextPos().y - object2.getNextPos().y)
                < (radius1 + radius2) * (radius1 + radius2)) {
            intersects = true;
            if (!allowOverlap) {
                Vector2d lengthVec = new Vector2d();
                lengthVec.sub(object2.getNextPos(), object1.getNextPos());
                double velLength1 = object1.getVel().length();
                double velLength2 = object2.getVel().length();
                double scale1 = velLength1 / (velLength1 + velLength2);
                double scale2 = velLength2 / (velLength1 + velLength2);
                double p1x = object1.getNextPos().x;
                double p2x = object2.getNextPos().x;
                double p1y = object1.getNextPos().y;
                double p2y = object2.getNextPos().y;
                double v1x = object1.getNextVel().x;
                double v2x = object2.getNextVel().x;
                double v1y = object1.getNextVel().y;
                double v2y = object2.getNextVel().y;
                if (velLength1 > 0) {
                    v1x /= -velLength1;
                    v1y /= -velLength1;
                }
                if (velLength2 > 0) {
                    v2x /= -velLength2;
                    v2y /= -velLength2;
                }
                //Need to solve the equation
                //((p1x + (v1x*scale1*w)) - (p2x + (v2x*scale2*w)))^2 + 
                //    ((p1y + (v1y*scale1*w)) - (p2y + (v2y*scale2*w))^2 = (radius1 + radius2)^2
                //with respect to w

                //First rearrange into format
                //aw^2 + bw + c = 0
                double a = (v1x * scale1 - v2x * scale2) * (v1x * scale1 - v2x * scale2) + (v1y * scale1 - v2y * scale2) * (v1y * scale1 - v2y * scale2);
                double b = 2 * (p1x - p2x) * (v1x * scale1 - v2x * scale2) + 2 * (p1y - p2y) * (v1y * scale1 - v2y * scale2);
                double c = (p1x - p2x) * (p1x - p2x) + (p1y - p2y) * (p1y - p2y) - (radius1 + radius2) * (radius1 + radius2);
                //Possibly need to check if root is real, but for now ignore
                double root = Math.sqrt(b * b - 4 * a * c);
                double w;
                //Because of restrictions we've
                if ((-b - root) / (2 * a) >= 0) {
                    w = (-b - root) / (2 * a);
                } else {
                    w = (root - b) / (2 * a);
                }
                object1.getNextPos().x += (v1x) * scale1 * w;
                object1.getNextPos().y += (v1y) * scale1 * w;
                object2.getNextPos().x += (v2x) * scale2 * w;
                object2.getNextPos().y += (v2y) * scale2 * w;
            }
        }
        return intersects;
    }

    private boolean bothRectangles() {
        boolean intersects = false;
        Vector2d object1Size = ((Rectangle) object1.getShape()).getSize();
        Vector2d object2Size = ((Rectangle) object2.getShape()).getSize();
        //Check for intersection
//		if(object1.getNextPos().x < object2.getNextPos().x + object2Size.x &&
//				object1.getNextPos().x + object1Size.x > object2.getNextPos().x &&
//				object1.getNextPos().y < object2.getNextPos().y + object2Size.y &&
//				object1.getNextPos().y + object1Size.y > object2.getNextPos().y)
        if (((object1.getNextPos().x > object2.getNextPos().x
                && object1.getNextPos().x < object2.getNextPos().x + object2Size.x)
                || (object1.getNextPos().x + object1Size.x > object2.getNextPos().x
                && object1.getNextPos().x + object1Size.x < object2.getNextPos().x + object2Size.x))
                && ((object1.getNextPos().y > object2.getNextPos().y
                && object1.getNextPos().y < object2.getNextPos().y + object2Size.y)
                || (object1.getNextPos().y + object1Size.y > object2.getNextPos().y
                && object1.getNextPos().y + object1Size.y < object2.getNextPos().y + object2Size.y))) {
            intersects = true;
            if (!allowOverlap) {
                Vector2d v1 = new Vector2d();
                v1.sub(object1.getNextPos(), object1.getPos());
                Vector2d v2 = new Vector2d();
                v2.sub(object2.getNextPos(), object2.getPos());
                double velLength1 = v1.length();
                double velLength2 = v2.length();
//				double velLength1 = object1.getNextVel().length();
//				double velLength2 = object2.getNextVel().length();
                double p1x = object1.getNextPos().x;
                double p1y = object1.getNextPos().y;
                double p2x = object2.getNextPos().x;
                double p2y = object2.getNextPos().y;
                double v1x = v1.x;
                double v1y = v1.y;
                double v2x = v2.x;
                double v2y = v2.y;
//				double v1x = object1.getNextVel().x;
//				double v1y = object1.getNextVel().y;
//				double v2x = object2.getNextVel().x;
//				double v2y = object2.getNextVel().y;
                if (velLength1 > 0) {
                    v1x /= -velLength1;
                    v1y /= -velLength1;
                }
                if (velLength2 > 0) {
                    v2x /= -velLength1;
                    v2y /= -velLength2;
                }
                double scale1 = velLength1 / (velLength1 + velLength2);
                double scale2 = velLength2 / (velLength1 + velLength2);
                //Crosses left
                boolean left = ((object1.getPos().x > object2.getPos().x + object2Size.x
                        && object1.getNextPos().x < object2.getNextPos().x + object2Size.x)
                        || (object1.getPos().x < object2.getPos().x + object2Size.x
                        && object1.getNextPos().x > object2.getNextPos().x + object2Size.x));
                //Crosses right
                boolean right = ((object1.getPos().x + object1Size.x > object2.getPos().x
                        && object1.getNextPos().x + object1Size.x < object2.getNextPos().x)
                        || (object1.getPos().x + object1Size.x < object2.getPos().x
                        && object1.getNextPos().x + object1Size.x > object2.getNextPos().x));
                //Crosses top
                boolean top = ((object1.getPos().y > object2.getPos().y + object2Size.y
                        && object1.getNextPos().y < object2.getNextPos().y + object2Size.y)
                        || (object1.getPos().y < object2.getPos().y + object2Size.y
                        && object1.getNextPos().y > object2.getNextPos().y + object2Size.y));
                //Crosses bottom
                boolean bottom = ((object1.getPos().y + object1Size.y > object2.getPos().y
                        && object1.getNextPos().y + object1Size.y < object2.getNextPos().y)
                        || (object1.getPos().y + object1Size.y < object2.getPos().y
                        && object1.getNextPos().y + object1Size.y > object2.getNextPos().y));
                double w = -1;
                if (left) {
                    w = (p2x + object2Size.x - p1x) / (v1x * scale1 - v2x * scale2);
                }
                if (right) {
                    double temp = (p2x - object1Size.x - p1x) / (v1x * scale1 - v2x * scale2);
                    if (w == -1) {
                        w = temp;
                    } else if (temp < w) {
                        w = temp;
                    }
                }
                if (top) {
                    double temp = (p2y + object2Size.y - p1y) / (v1y * scale1 - v2y * scale2);
                    if (w == -1) {
                        w = temp;
                    } else if (temp < w) {
                        w = temp;
                    }
                }
                if (bottom) {
                    double temp = (p2y - object1Size.y - p1y) / (v1y * scale1 - v2y * scale2);
                    if (w == -1) {
                        w = temp;
                    } else if (temp < w) {
                        w = temp;
                    }
                }
                object1.getNextPos().x += (v1x) * scale1 * w;
                object1.getNextPos().y += (v1y) * scale1 * w;
                object2.getNextPos().x += (v2x) * scale2 * w;
                object2.getNextPos().y += (v2y) * scale2 * w;
            }
        }
        return intersects;
    }

    private boolean rectangleAndCircle() {
        boolean intersects = false;
        GameObject circleObj;
        GameObject rectObj;
        if (object1.getShape() instanceof Circle) {
            circleObj = object1;
            rectObj = object2;
        } else {
            circleObj = object2;
            rectObj = object1;
        }
        double radius = ((Circle) circleObj.getShape()).getRadius();
        Vector2d rectSize = ((Rectangle) rectObj.getShape()).getSize();
        double p1x = circleObj.getNextPos().x;
        double p1y = circleObj.getNextPos().y;
        double p2x = rectObj.getNextPos().x;
        double p2y = rectObj.getNextPos().y;

        boolean completeInside = (p1x > p2x && p1x < p2x + rectSize.x
                && p1y > p2y && p1y < p2y + rectSize.y);
        boolean left = ((p1x - p2x) * (p1x - p2x) < (radius) * (radius))
                && (p1y > p2y && p1y < p2y + rectSize.y);
        boolean right = ((p1x - (p2x + rectSize.x)) * (p1x - (p2x + rectSize.x)) < (radius) * (radius))
                && (p1y > p2y && p1y < p2y + rectSize.y);
        boolean top = ((p1y - p2y) * (p1y - p2y) < (radius) * (radius))
                && (p1x > p2x && p1x < p2x + rectSize.y);
        boolean bottom = ((p1y - (p2y + rectSize.y)) * (p1y - (p2y + rectSize.y)) < (radius) * (radius))
                && (p1x > p2x && p1x < p2x + rectSize.x);
        boolean corner1 = (p1x - p2x) * (p1x - p2x) + (p1y - p2y) * (p1y - p2y) < (radius) * (radius);
        boolean corner2 = (p1x - (p2x + rectSize.x)) * (p1x - (p2x + rectSize.x))
                + (p1y - p2y) * (p1y - p2y) < (radius) * (radius);
        boolean corner3 = (p1x - p2x) * (p1x - p2x)
                + (p1y - (p2y + rectSize.y)) * (p1y - (p2y + rectSize.y)) < (radius) * (radius);
        boolean corner4 = (p1x - (p2x + rectSize.x)) * (p1x - (p2x + rectSize.x))
                + (p1y - (p2y + rectSize.y)) * (p1y - (p2y + rectSize.y)) < (radius) * (radius);
        if (completeInside || left || right || top || bottom
                || corner1 || corner2 || corner3 || corner4) {
            intersects = true;
            if (!allowOverlap) {
                Vector2d v1 = new Vector2d();
                v1.sub(circleObj.getNextPos(), circleObj.getPos());
                Vector2d v2 = new Vector2d();
                v2.sub(rectObj.getNextPos(), rectObj.getPos());
                double velLength1 = v1.length();
                double velLength2 = v2.length();
//				double velLength1 = object1.getNextVel().length();
//				double velLength2 = object2.getNextVel().length();
                double v1x = v1.x;
                double v1y = v1.y;
                double v2x = v2.x;
                double v2y = v2.y;
//				double v1x = object1.getNextVel().x;
//				double v1y = object1.getNextVel().y;
//				double v2x = object2.getNextVel().x;
//				double v2y = object2.getNextVel().y;
                if (velLength1 > 0) {
                    v1x /= -velLength1;
                    v1y /= -velLength1;
                }
                if (velLength2 > 0) {
                    v2x /= -velLength2;
                    v2y /= -velLength2;
                }
                double scale1 = velLength1 / (velLength1 + velLength2);
                double scale2 = velLength2 / (velLength1 + velLength2);
                //Crosses left
                boolean crossLeft = ((circleObj.getPos().x - radius > rectObj.getPos().x + rectSize.x
                        && circleObj.getNextPos().x - radius < rectObj.getNextPos().x + rectSize.x)
                        || (circleObj.getPos().x - radius < rectObj.getPos().x + rectSize.x
                        && circleObj.getNextPos().x - radius > rectObj.getNextPos().x + rectSize.x));
                //Crosses right
                boolean crossRight = ((circleObj.getPos().x + radius > rectObj.getPos().x
                        && circleObj.getNextPos().x + radius < rectObj.getNextPos().x)
                        || (circleObj.getPos().x + radius < rectObj.getPos().x
                        && circleObj.getNextPos().x + radius > rectObj.getNextPos().x));
                //Crosses top
                boolean crossTop = ((circleObj.getPos().y - radius > rectObj.getPos().y + rectSize.y
                        && circleObj.getNextPos().y - radius < rectObj.getNextPos().y + rectSize.y)
                        || (circleObj.getPos().y - radius < rectObj.getPos().y + rectSize.y
                        && circleObj.getNextPos().y - radius > rectObj.getNextPos().y + rectSize.y));
                //Crosses bottom
                boolean crossBottom = ((circleObj.getPos().y + radius > rectObj.getPos().y
                        && circleObj.getNextPos().y + radius < rectObj.getNextPos().y)
                        || (circleObj.getPos().y + radius < rectObj.getPos().y
                        && circleObj.getNextPos().y + radius > rectObj.getNextPos().y));
                double w = -1;
                //crossCorner = false;
                if (crossLeft) {
                    w = (p2x + rectSize.x - (p1x - radius)) / (v1x * scale1 - v2x * scale2);
                }
                if (crossRight) {
                    double temp = (p2x - (p1x + radius)) / (v1x * scale1 - v2x * scale2);
                    if (w == -1) {
                        w = temp;
                    } else if (temp < w) {
                        w = temp;
                    }
                }
                if (crossTop) {
                    double temp = (p2y + rectSize.y - (p1y - radius)) / (v1y * scale1 - v2y * scale2);
                    if (w == -1) {
                        w = temp;
                    } else if (temp < w) {
                        w = temp;
                    }
                }
                if (crossBottom) {
                    double temp = (p2y - (p1y + radius)) / (v1y * scale1 - v2y * scale2);
                    if (w == -1) {
                        w = temp;
                    } else if (temp < w) {
                        w = temp;
                    }
                }
                //Top Right corner of Rectangle
                if ((p1x - (p2x + rectSize.x)) * (p1x - (p2x + rectSize.x)) + (p1y - p2y) * (p1y - p2y) < (radius) * (radius)) {
                    double temp = -1;
                    double a2 = (v1x * scale1 - v2x * scale2) * (v1x * scale1 - v2x * scale2) + (v1y * scale1 - v2y * scale2) * (v1y * scale1 - v2y * scale2);
                    double b2 = 2 * (p1x - (p2x + rectSize.x)) * (v1x * scale1 - v2x * scale2) + 2 * (p1y - p2y) * (v1y * scale1 - v2y * scale2);
                    double c2 = (p1x - (p2x + rectSize.x)) * (p1x - (p2x + rectSize.x)) + (p1y - p2y) * (p1y - p2y) - (radius) * (radius);
                    double root = Math.sqrt(b2 * b2 - 4 * a2 * c2);
                    if ((-b2 - root) / (2 * a2) >= 0) {
                        temp = (-b2 - root) / (2 * a2);
                    } else {
                        temp = (root - b2) / (2 * a2);
                    }
                    if (temp != -1 && temp < w) {
                        w = temp;
                    }
                }
                //Bottom Right corner of Rectangle
                if ((p1x - (p2x + rectSize.x)) * (p1x - (p2x + rectSize.x))
                        + (p1y - (p2y + rectSize.y)) * (p1y - (p2y + rectSize.y)) < (radius) * (radius)) {
                    double temp = -1;
                    double a2 = (v1x * scale1 - v2x * scale2) * (v1x * scale1 - v2x * scale2) + (v1y * scale1 - v2y * scale2) * (v1y * scale1 - v2y * scale2);
                    double b2 = 2 * (p1x - (p2x + rectSize.x)) * (v1x * scale1 - v2x * scale2) + 2 * (p1y - (p2y + rectSize.y)) * (v1y * scale1 - v2y * scale2);
                    double c2 = (p1x - (p2x + rectSize.x)) * (p1x - (p2x + rectSize.x)) + (p1y - (p2y + rectSize.y)) * (p1y - (p2y + rectSize.y)) - (radius) * (radius);
                    double root = Math.sqrt(b2 * b2 - 4 * a2 * c2);
                    if ((-b2 - root) / (2 * a2) >= 0) {
                        temp = (-b2 - root) / (2 * a2);
                    } else {
                        temp = (root - b2) / (2 * a2);
                    }
                    if (temp != -1 && temp < w) {
                        w = temp;
                    }
                }
                //Top Left corner of rectangle
                if ((p1x - p2x) * (p1x - p2x) + (p1y - p2y) * (p1y - p2y) < (radius) * (radius)) {
                    double temp = -1;
                    double a2 = (v1x * scale1 - v2x * scale2) * (v1x * scale1 - v2x * scale2) + (v1y * scale1 - v2y * scale2) * (v1y * scale1 - v2y * scale2);
                    double b2 = 2 * (p1x - p2x) * (v1x * scale1 - v2x * scale2) + 2 * (p1y - p2y) * (v1y * scale1 - v2y * scale2);
                    double c2 = (p1x - p2x) * (p1x - p2x) + (p1y - p2y) * (p1y - p2y) - (radius) * (radius);
                    double root = Math.sqrt(b2 * b2 - 4 * a2 * c2);
                    if ((-b2 - root) / (2 * a2) >= 0) {
                        temp = (-b2 - root) / (2 * a2);
                    } else {
                        temp = (root - b2) / (2 * a2);
                    }
                    if (temp != -1 && temp < w) {
                        w = temp;
                    }
                }
                //Bottom left corner of Rectangle
                if ((p1x - p2x) * (p1x - p2x) + (p1y - (p2y + rectSize.y)) * (p1y - (p2y + rectSize.y)) < (radius) * (radius)) {
                    double temp = -1;
                    double a2 = (v1x * scale1 - v2x * scale2) * (v1x * scale1 - v2x * scale2) + (v1y * scale1 - v2y * scale2) * (v1y * scale1 - v2y * scale2);
                    double b2 = 2 * (p1x - p2x) * (v1x * scale1 - v2x * scale2) + 2 * (p1y - (p2y + rectSize.y)) * (v1y * scale1 - v2y * scale2);
                    double c2 = (p1x - p2x) * (p1x - p2x) + (p1y - (p2y + rectSize.y)) * (p1y - (p2y + rectSize.y)) - (radius) * (radius);
                    double root = Math.sqrt(b2 * b2 - 4 * a2 * c2);
                    if ((-b2 - root) / (2 * a2) >= 0) {
                        temp = (-b2 - root) / (2 * a2);
                    } else {
                        temp = (root - b2) / (2 * a2);
                    }
                    if (temp != -1 && temp < w) {
                        w = temp;
                    }
                }

                System.out.println("W:" + w);
                circleObj.getNextPos().x += (v1x) * scale1 * w;
                circleObj.getNextPos().y += (v1y) * scale1 * w;
                rectObj.getNextPos().x += (v2x) * scale2 * w;
                rectObj.getNextPos().y += (v2y) * scale2 * w;
            }
        }
        return intersects;
    }
}