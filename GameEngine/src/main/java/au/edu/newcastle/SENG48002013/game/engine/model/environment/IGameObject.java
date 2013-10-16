package au.edu.newcastle.SENG48002013.game.engine.model.environment;

import javax.vecmath.Vector2d;

/**
 *
 * @author rossbille
 */
public interface IGameObject {

    public Vector2d getOutputPos();

    public long getId();

    public String getImageUrl();
}