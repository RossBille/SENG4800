package au.edu.newcastle.SENG48002013.game.engine.ignition;

import au.edu.newcastle.SENG48002013.game.engine.processor.Boss;
import au.edu.newcastle.SENG48002013.game.engine.resources.ConfigReader;
import java.io.IOException;

/**
 * Class to start and stop the game for loading of new games and remote
 * restarting
 *
 * @author rossbille
 */
public class UpdateManager {

    public static void start(String path) throws IOException {
        ConfigReader.BASEDIR = path;
        Boss.start();
        System.out.println("Game is starting...");
    }

    public static void stop() {
        Boss.stop();
        System.out.println("Game is stopping...");
    }

    public static void restart() throws IOException {
        Boss.stop();
        Boss.start();
        System.out.println("Game is restarting...");
    }
}
