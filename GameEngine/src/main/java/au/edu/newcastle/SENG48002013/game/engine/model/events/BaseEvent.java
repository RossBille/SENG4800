/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.model.events;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import au.edu.newcastle.SENG48002013.game.engine.model.actions.IAction;

public abstract class BaseEvent implements IEvent {

    private long id;
    private List<IAction> actions;

    public BaseEvent(long id) {
        this.id = id;
        actions = new LinkedList<IAction>();
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addAction(IAction action) {
        actions.add(action);
    }

    protected int doActions(double dt) {
        int returnCode = -1;
        Iterator<IAction> actionIter = actions.iterator();
        while (actionIter.hasNext()) {
            int newReturnCode = actionIter.next().doAction(dt);
            if (newReturnCode != -1) {
                returnCode = newReturnCode;
            }
        }
        return returnCode;
    }

    @Override
    public abstract int evaluate(double dt);
}