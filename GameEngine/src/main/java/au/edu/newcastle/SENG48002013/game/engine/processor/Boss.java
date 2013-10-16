package au.edu.newcastle.SENG48002013.game.engine.processor;

import java.io.IOException;

/**
 *
 * @author Peter
 */
public class Boss {

    private static boolean running = false;
    private static Runner runner = null;

	/**
	 * 
	 * @param inputId
	 * @return 
	 */
    public static boolean addPlayer(long inputId) {
        return runner.addPlayer(inputId);
    }

	/**
	 *
	 * @param inputId  
	 */
    public static void removePlayer(long inputId) {
        runner.removePlayer(inputId);
    }

	/**
	 *
	 * @throws IOException  
	 */
    public static void start() throws IOException {
        runner = new Runner();
        runner.start();
    }

	/**
	 * 
	 */
    public static void stop() {
        runner.stopRunning();
    }

	/**
	 *
	 * @return  
	 */
    public static boolean isRunning() {
        return running;
    }
}
