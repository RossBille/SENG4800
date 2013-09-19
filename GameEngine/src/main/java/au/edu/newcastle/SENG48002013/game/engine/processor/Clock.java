package au.edu.newcastle.SENG48002013.game.engine.processor;

public class Clock implements IClock {

    public final int TARGET_FPS = 60;
    private final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
    private long lastLoopTime;

    public Clock() {
        lastLoopTime = -1;
    }

    @Override
    public double rest() {
        if (lastLoopTime == -1) {
            lastLoopTime = System.nanoTime();
        } else {
            try {
                Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
            } catch (Exception e) {
            }
        }
        long now = System.nanoTime();
        long updateLength = now - lastLoopTime;
        lastLoopTime = now;
        double delta = (updateLength / (double) OPTIMAL_TIME);
        return delta;
    }
}