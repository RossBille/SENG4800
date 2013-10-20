package au.edu.newcastle.SENG48002013.game.engine.model.environment;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import au.edu.newcastle.SENG48002013.game.engine.model.events.IEvent;
import javax.vecmath.Vector2d;

public class Level {

    private long id;
    private Background background;
    private Vector2d dimensions;
    private List<GameObject> gameObjects;
    private List<IEvent> events;

    /**
	 *
	 * @param id
	 */
	public Level(long id) {
        this.id = id;
        dimensions = new Vector2d();
        gameObjects = new LinkedList<GameObject>();
        events = new LinkedList<IEvent>();
    }

    /**
	 *
	 * @return
	 */
	public long getId() {
        return id;
    }

    /**
	 *
	 * @param id
	 */
	public void setId(long id) {
        this.id = id;
    }

    /**
	 *
	 * @return
	 */
	public Background getBackground() {
        return background;
    }

    /**
	 *
	 * @param background
	 */
	public void setBackground(Background background) {
        this.background = background;
    }

    /**
	 *
	 * @return
	 */
	public Vector2d getDimensions() {
        return dimensions;
    }

    /**
	 *
	 * @param dimensions
	 */
	public void setDimensions(Vector2d dimensions) {
        this.dimensions.set(dimensions);
    }

    /**
	 *
	 * @param index
	 * @return
	 */
	public GameObject getGameObject(int index) {
        return gameObjects.get(index);
    }

    /**
	 *
	 * @return
	 */
	public IGameObject[] getOutputObjects() {
        List<GameObject> outputObjects = new LinkedList<GameObject>();
        Iterator<GameObject> objectIter = gameObjects.iterator();
        while (objectIter.hasNext()) {
            GameObject currentObject = objectIter.next();
            if (currentObject.isActive()) {
                outputObjects.add(currentObject);
            }
        }
        return outputObjects.toArray(new IGameObject[0]);
    }

    /**
	 *
	 * @param index
	 * @param gameObject
	 */
	public void setGameObject(int index, GameObject gameObject) {
        gameObjects.set(index, gameObject);
    }

    /**
	 *
	 * @param gameObject
	 */
	public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    /**
	 *
	 * @param index
	 * @return
	 */
	public IEvent getEvent(int index) {
        return events.get(index);
    }

    /**
	 *
	 * @param index
	 * @param event
	 */
	public void setEvent(int index, IEvent event) {
        events.set(index, event);
    }

    /**
	 *
	 * @param event
	 */
	public void addEvent(IEvent event) {
        events.add(event);
    }

    /**
	 *
	 * @param dt
	 * @return
	 */
	public int step(double dt) {
        int returnCode = -1;
        stepNext(dt);
        returnCode = stepEvents(dt);
        stepCommit();
        return returnCode;
    }

    private void stepNext(double dt) {
        Iterator<GameObject> objectIter = gameObjects.iterator();
        while (objectIter.hasNext()) {
            GameObject currentObject = objectIter.next();
            if (currentObject.isActive()) {
                currentObject.stepNext(dt);
            }
        }
    }

    private int stepEvents(double dt) {
        int returnCode = -1;
        Iterator<IEvent> eventIter = events.iterator();
        while (eventIter.hasNext()) {
            int newReturnCode = eventIter.next().evaluate(dt);
            if (newReturnCode != -1) {
                returnCode = newReturnCode;
            }
        }
        return returnCode;
    }

    private void stepCommit() {
        Iterator<GameObject> objectIter = gameObjects.iterator();
        while (objectIter.hasNext()) {
            GameObject currentObject = objectIter.next();
            if (currentObject.isActive()) {
                currentObject.commit();
            }
        }
    }
}