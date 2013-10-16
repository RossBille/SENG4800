package au.edu.newcastle.SENG48002013.game.engine.model.events;

/**
 *
 * @author Peter
 */
public class TimerEvent extends BaseEvent {

    private long totalTime;
    private long currentTime;
    private boolean repeat;

    /**
	 *
	 * @param id
	 */
	public TimerEvent(long id) {
        super(id);
    }

    /**
	 *
	 * @param id
	 * @param totalTime
	 * @param repeat
	 */
	public TimerEvent(long id, long totalTime, boolean repeat) {
        super(id);
        this.totalTime = totalTime;
        this.repeat = repeat;
    }

    /**
	 *
	 * @return
	 */
	public long getTotalTime() {
        return totalTime;
    }

	/**
	 * 
	 * @param totalTime 
	 */
    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
        this.currentTime = totalTime;
    }

	/**
	 *
	 * @return  
	 */
    public boolean isRepeat() {
        return repeat;
    }

	/**
	 *
	 * @param repeat  
	 */
    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

	/**
	 *
	 * @param dt
	 * @return  
	 */
    @Override
    public int evaluate(double dt) {
        int returnCode = -1;
        currentTime -= dt;
        if (currentTime <= 0) {
            returnCode = doActions(dt);
            if (repeat) {
                currentTime = totalTime;
            }
        }
        return returnCode;
    }
}
