/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.model.events;

public class TimerEvent extends BaseEvent {

    private long totalTime;
    private long currentTime;
    private boolean repeat;

    public TimerEvent(long id) {
        super(id);
    }

    public TimerEvent(long id, long totalTime, boolean repeat) {
        super(id);
        this.totalTime = totalTime;
        this.repeat = repeat;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
        this.currentTime = totalTime;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

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
