package au.edu.newcastle.SENG48002013.game.engine.model;

/**
 *
 * @author Peter 
 */
public interface IGame {

	/**
	 * 
	 * @param dt 
	 */
    public void step(double dt);

	/**
	 *
	 * @param inputId
	 * @return  
	 */
    public boolean addPlayer(long inputId);

	/**
	 *
	 * @param inputId  
	 */
    public void removePlayer(long inputId);
}
