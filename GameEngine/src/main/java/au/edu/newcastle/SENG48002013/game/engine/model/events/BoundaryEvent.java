package au.edu.newcastle.SENG48002013.game.engine.model.events;

import au.edu.newcastle.SENG48002013.game.engine.model.environment.Circle;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.GameObject;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Level;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Rectangle;
import javax.vecmath.Vector2d;

/**
 *
 * @author rossbille
 */
public class BoundaryEvent extends BaseEvent {

    private GameObject gameObject;
    private Level level;
    private boolean allowOverlap;
    private Edge edge;

    /**
	 *
	 * @param id
	 */
	public BoundaryEvent(long id) {
        super(id);
    }

    /**
	 *
	 * @param id
	 * @param gameObject
	 * @param level
	 */
	public BoundaryEvent(long id, GameObject gameObject, Level level) {
        super(id);
        this.gameObject = gameObject;
        this.level = level;
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
	public Level getLevel() {
        return level;
    }

    /**
	 *
	 * @param level
	 */
	public void setLevel(Level level) {
        this.level = level;
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
	 * @return
	 */
	public Edge getEdge() {
        return edge;
    }

    /**
	 *
	 * @param edge
	 */
	public void setEdge(Edge edge) {
        this.edge = edge;
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
        boolean undefined = false;
        double gradient = 0;
        Vector2d levelDimensions = level.getDimensions();
        //Calculate gradient/undefined
        if (gameObject.getNextPos().x - gameObject.getPos().x == 0) {
            undefined = true;
        } else {
            gradient = (gameObject.getNextPos().y - gameObject.getPos().y) / (gameObject.getNextPos().x - gameObject.getPos().x);
        }
        //If rectangle shape
        if (gameObject.getShape() instanceof Rectangle) {
            Vector2d objectSize = ((Rectangle) gameObject.getShape()).getSize();
            //Intersect Top
            if (edge == Edge.TOP || edge == Edge.ALL) {
                if (gameObject.getNextPos().y < 0) {
                    intersects = true;
                    if (!allowOverlap) {
                        gameObject.getNextPos().y = 0;
                        if (gradient != 0) {
                            gameObject.getNextPos().x = gameObject.getPos().x - (gameObject.getPos().y / gradient);
                        }
                    }
                }
            }
            //Intersect Bottom
            if (edge == Edge.BOTTOM || edge == Edge.ALL) {
                if (gameObject.getNextPos().y + objectSize.y >= levelDimensions.y) {
                    intersects = true;
                    if (!allowOverlap) {
                        gameObject.getNextPos().y = levelDimensions.y - 1 - objectSize.y;
                        if (gradient != 0) {
                            gameObject.getNextPos().x = ((gameObject.getNextPos().y - gameObject.getPos().y) / gradient) + gameObject.getPos().x;
                        }
                    }
                }
            }
            //Intersect Left
            if (edge == Edge.LEFT || edge == Edge.ALL) {
                if (gameObject.getNextPos().x < 0) {
                    intersects = true;
                    if (!allowOverlap) {
                        gameObject.getNextPos().x = 0;
                        if (!undefined) {
                            gameObject.getNextPos().y = gameObject.getPos().y - gradient * (gameObject.getPos().x);
                        }
                    }
                }
            }
            //Intersect Right
            if (edge == Edge.RIGHT || edge == Edge.ALL) {
                if (gameObject.getNextPos().x + objectSize.x >= levelDimensions.x) {
                    intersects = true;
                    if (!allowOverlap) {
                        gameObject.getNextPos().x = levelDimensions.x - 1 - objectSize.x;
                        if (!undefined) {
                            gameObject.getNextPos().y = gradient * (gameObject.getNextPos().x - gameObject.getPos().x) + gameObject.getPos().y;
                        }
                    }
                }
            }
        } //If circle shape
        else if (gameObject.getShape() instanceof Circle) {
            double radius = ((Circle) gameObject.getShape()).getRadius();
            //Intersect Top
            if (edge == Edge.TOP || edge == Edge.ALL) {
                if (gameObject.getNextPos().y - radius < 0) {
                    intersects = true;
                    if (!allowOverlap) {
                        gameObject.getNextPos().y = radius;
                        if (gradient != 0) {
                            gameObject.getNextPos().x = ((gameObject.getNextPos().y - gameObject.getPos().y) / gradient) + gameObject.getPos().x;
                        }
                    }
                }
            }
            //Intersect Bottom
            if (edge == Edge.BOTTOM || edge == Edge.ALL) {
                if (gameObject.getNextPos().y + radius >= levelDimensions.y) {
                    intersects = true;
                    if (!allowOverlap) {
                        gameObject.getNextPos().y = levelDimensions.y - 1 - radius;
                        if (gradient != 0) {
                            gameObject.getNextPos().x = ((gameObject.getNextPos().y - gameObject.getPos().y) / gradient) + gameObject.getPos().x;
                        }
                    }
                }
            }
            //Intersect Left
            if (edge == Edge.LEFT || edge == Edge.ALL) {
                if (gameObject.getNextPos().x - radius < 0) {
                    intersects = true;
                    if (!allowOverlap) {
                        gameObject.getNextPos().x = radius;
                        if (!undefined) {
                            gameObject.getNextPos().y = gradient * (gameObject.getNextPos().x - gameObject.getPos().x) + gameObject.getPos().y;
                        }
                    }
                }
            }
            //Intersect Right
            if (edge == Edge.RIGHT || edge == Edge.ALL) {
                if (gameObject.getNextPos().x + radius >= levelDimensions.x) {
                    intersects = true;
                    if (!allowOverlap) {
                        gameObject.getNextPos().x = levelDimensions.x - 1 - radius;
                        if (!undefined) {
                            gameObject.getNextPos().y = gradient * (gameObject.getNextPos().x - gameObject.getPos().x) + gameObject.getPos().y;
                        }
                    }
                }
            }
        }
        if (intersects) {
            returnCode = doActions(dt);
        }
        return returnCode;
    }

    /**
	 *
	 */
	public enum Edge {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        ALL
    }
}