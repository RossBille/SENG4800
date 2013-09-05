package au.edu.newcastle.SENG48002013.game.engine.ignition;

import au.edu.newcastle.SENG48002013.game.engine.processor.Boss;
import au.edu.newcastle.SENG48002013.game.engine.processor.Clock;
import au.edu.newcastle.SENG48002013.game.engine.processor.IClock;
import java.io.IOException;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		Boss myBoss = new Boss();
		myBoss.start();
	}
}
