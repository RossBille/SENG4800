/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.model.actions;

/**
 *
 * @author rossbille
 */
public interface IAction {
	public long getId();
	public void doAction(long dt);
}