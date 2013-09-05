/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.model.environment;

import javax.vecmath.Vector2d;

/**
 *
 * @author rossbille
 */
public interface IGameObject
{
	public Vector2d getPos();
	public long getId();
	public long getSpriteId();
	
}