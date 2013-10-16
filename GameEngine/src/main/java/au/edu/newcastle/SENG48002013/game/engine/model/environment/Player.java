package au.edu.newcastle.SENG48002013.game.engine.model.environment;

import au.edu.newcastle.SENG48002013.game.engine.connections.input.IInput;
import au.edu.newcastle.SENG48002013.game.engine.connections.input.InputManager;

/**
 *
 * @author Ross
 */
public class Player {

    long id;
    long inputId;
    double score;

    /**
	 *
	 * @param id
	 */
	public Player(long id) {
        this.id = id;
        this.inputId = -1;
        this.score = 0;
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
	public long getInputId() {
        return inputId;
    }

    /**
	 *
	 * @param inputId
	 */
	public void setInputId(long inputId) {
        this.inputId = inputId;
    }

    /**
	 *
	 * @return
	 */
	public double getScore() {
        return score;
    }

    /**
	 *
	 * @param score
	 */
	public void setScore(double score) {
        this.score = score;
    }

    /**
	 *
	 * @return
	 */
	public IInput getInput() {
        return InputManager.getInput(inputId);
    }

    /**
	 *
	 * @return
	 */
	public boolean isInitialized() {
        return (inputId != -1);
    }
}