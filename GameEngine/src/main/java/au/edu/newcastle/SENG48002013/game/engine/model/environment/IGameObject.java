package au.edu.newcastle.SENG48002013.game.engine.model.environment;

import javax.vecmath.Vector2d;

public interface IGameObject {
	public long getId();
	public Vector2d getPos();
	public long getSpriteId();
}