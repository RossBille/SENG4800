package au.edu.newcastle.SENG48002013.game.engine.model.environment;

import javax.vecmath.Vector2d;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GameObject implements IGameObject {

    private long id;
    private String name;
    private Sprite sprite;
    private Shape shape;
    private Vector2d size;
    private Vector2d pos;
    private Vector2d vel;
    private Vector2d acc;
    private Vector2d nextPos;
    private Vector2d nextVel;
    private Vector2d nextAcc;
    private boolean committed;
    private boolean active;
    private String imageUrl;
    private Vector2d outputPos;
    private int currentFrame;
    private double currentTime;
    private final long TARGET_FPS = 60;

    /**
	 *
	 * @param id
	 */
	public GameObject(long id) {
        this.id = id;
        size = new Vector2d(0, 0);
        pos = new Vector2d(0, 0);
        vel = new Vector2d(0, 0);
        acc = new Vector2d(0, 0);
        nextPos = new Vector2d(0, 0);
        nextVel = new Vector2d(0, 0);
        nextAcc = new Vector2d(0, 0);
        active = true;
        currentFrame = 0;
        currentTime = 0;
        imageUrl = null;
    }

    /**
	 *
	 * @return
	 */
	@Override
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
	@JsonIgnore
    public String getName() {
        return name;
    }

    /**
	 *
	 * @param name
	 */
	public void setName(String name) {
        this.name = name;
    }

    /**
	 *
	 * @return
	 */
	@JsonIgnore
    public Sprite getSprite() {
        return sprite;
    }

    /**
	 *
	 * @param sprite
	 */
	public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        this.currentFrame = 0;
        this.currentTime = 0;
    }

    /**
	 *
	 * @return
	 */
	@JsonIgnore
    public Shape getShape() {
        return shape;
    }

    /**
	 *
	 * @param shape
	 */
	public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
	 *
	 * @return
	 */
	@JsonIgnore
    public Vector2d getSize() {
        return this.size;
    }

    /**
	 *
	 * @param size
	 */
	public void setSize(Vector2d size) {
        this.size.set(size);
    }

    /**
	 *
	 * @return
	 */
	@JsonIgnore
    public Vector2d getPos() {
        return pos;
    }

    /**
	 *
	 * @param pos
	 */
	public void setPos(Vector2d pos) {
        this.pos.set(pos);
    }

    /**
	 *
	 * @return
	 */
	@JsonIgnore
    public Vector2d getVel() {
        return vel;
    }

    /**
	 *
	 * @param vel
	 */
	public void setVel(Vector2d vel) {
        this.vel.set(vel);
    }

    /**
	 *
	 * @return
	 */
	@JsonIgnore
    public Vector2d getAcc() {
        return acc;
    }

    /**
	 *
	 * @param acc
	 */
	public void setAcc(Vector2d acc) {
        this.acc.set(acc);
    }

    /**
	 *
	 * @return
	 */
	@JsonIgnore
    public Vector2d getNextPos() {
        return nextPos;
    }

    /**
	 *
	 * @param nextPos
	 */
	public void setNextPos(Vector2d nextPos) {
        committed = false;
        this.nextPos.set(nextPos);
    }

    /**
	 *
	 * @return
	 */
	@JsonIgnore
    public Vector2d getNextVel() {
        return nextVel;
    }

    /**
	 *
	 * @param nextVel
	 */
	public void setNextVel(Vector2d nextVel) {
        committed = false;
        this.nextVel.set(nextVel);
    }

    /**
	 *
	 * @return
	 */
	@JsonIgnore
    public Vector2d getNextAcc() {
        return nextAcc;
    }

    /**
	 *
	 * @param nextAcc
	 */
	public void setNextAcc(Vector2d nextAcc) {
        committed = false;
        this.nextAcc = nextAcc;
    }

    /**
	 *
	 * @return
	 */
	@JsonIgnore
    public boolean isActive() {
        return active;
    }

    /**
	 *
	 * @param active
	 */
	public void setActive(boolean active) {
        this.active = active;
    }

    /**
	 *
	 * @param dt
	 */
	public void stepNext(double dt) {
        committed = false;
        nextAcc.set(acc);
        nextVel.scaleAdd((double) (dt), nextAcc, vel);
        nextPos.scaleAdd((double) (dt), nextVel, pos);
        stepAnimation(dt);
    }

    /**
	 *
	 */
	public void commit() {
        acc.set(nextAcc);
        vel.set(nextVel);
        pos.set(nextPos);
        committed = true;
    }

    private void stepAnimation(double dt) {
        if (sprite != null) {
            currentTime += dt / TARGET_FPS;
            currentFrame = (int) Math.floor(currentTime * sprite.getSpeed());
            if (currentFrame >= sprite.length()) {
                currentTime = 0;
                currentFrame = 0;
            }
        }
    }

    /**
	 *
	 * @return
	 */
	@JsonIgnore
    public boolean isCommitted() {
        return committed;
    }

    /**
	 *
	 * @return
	 */
	@Override
    public Vector2d getOutputPos() {
        Vector2d outputPos = new Vector2d();
        outputPos.add(pos, sprite.getOffset());
        if (shape instanceof Circle) {
            double radius = ((Circle) shape).getRadius();
            outputPos.x -= radius;
            outputPos.y -= radius;
        }
        return outputPos;
    }

    /**
	 *
	 * @return
	 */
	@Override
    public String getImageUrl() {
        if (sprite != null) {
            return sprite.getImageUrl(currentFrame);
        } else {
            return "";
        }
    }

    /**
	 *
	 * @param imageUrl
	 */
	public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}