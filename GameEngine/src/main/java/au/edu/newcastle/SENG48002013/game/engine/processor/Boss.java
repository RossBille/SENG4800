/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.processor;

import java.io.IOException;

/**
 *
 * @author Ross
 */
public class Boss {

    private static boolean running = false;
    private static Runner runner = null;

    public static boolean addPlayer(long inputId) {
        return runner.addPlayer(inputId);
    }

    public static void removePlayer(long inputId) {
        runner.removePlayer(inputId);
    }

    public static void start() throws IOException {
        runner = new Runner();
        runner.start();
    }

    public static void stop() {
        runner.stopRunning();
    }

    public static boolean isRunning() {
        return running;
    }
}
