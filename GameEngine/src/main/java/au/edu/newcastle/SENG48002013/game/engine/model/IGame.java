/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.model;

/**
 *
 * @author Ross
 */
public interface IGame
{
	public void step(double dt);
	public boolean addPlayer(long inputId);
	public void removePlayer(long inputId);
}
