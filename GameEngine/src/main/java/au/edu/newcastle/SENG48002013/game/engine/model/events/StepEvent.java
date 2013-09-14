package au.edu.newcastle.SENG48002013.game.engine.model.events;

public class StepEvent extends BaseEvent {
	public StepEvent(long id)
	{
		super(id);
	}
	public int evaluate(double dt)
	{
		return doActions(dt);
	}
}