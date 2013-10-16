package au.edu.newcastle.SENG48002013.game.engine.model.environment;

import javax.vecmath.Vector2d;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Sprite {

    private long id;
    private String[] imageUrls;
    private long speed;
    private Vector2d offset;

    /**
	 *
	 * @param id
	 */
	public Sprite(long id) {
        this.id = id;
        speed = 40;
        offset = new Vector2d(0, 0);
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
	public String[] getImageUrls() {
        return imageUrls;
    }

    /**
	 *
	 * @param index
	 * @return
	 */
	public String getImageUrl(int index) {
        return imageUrls[index];
    }

    /**
	 *
	 * @param imageUrls
	 */
	public void setImageUrls(String[] imageUrls) {
        this.imageUrls = imageUrls;
    }

    /**
	 *
	 * @return
	 */
	public long getSpeed() {
        return speed;
    }

    /**
	 *
	 * @param speed
	 */
	public void setSpeed(long speed) {
        this.speed = speed;
    }

    /**
	 *
	 * @return
	 */
	public Vector2d getOffset() {
        return offset;
    }

    /**
	 *
	 * @param offset
	 */
	public void setOffset(Vector2d offset) {
        this.offset.set(offset);
    }

    /**
	 *
	 * @return
	 */
	public int length() {
        if (imageUrls != null) {
            return imageUrls.length;
        } else {
            return 0;
        }
    }
}