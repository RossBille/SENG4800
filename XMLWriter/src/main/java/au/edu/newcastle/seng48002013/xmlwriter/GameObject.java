/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.seng48002013.xmlwriter;

/**
 *
 * @author Bracks
 */
import java.util.ArrayList;

public class GameObject {

	// Variables
	private String name;
	private int id;
	private int posX;
	private int posY;
	private String colour;
	private int spriteSpeed;
	private ArrayList<String> sprite = new ArrayList<String>();
	
	// Mutators
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public void setPosX(int position_x) {
		this.posX = position_x;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public void setPosY(int position_y) {
		this.posY = position_y;
	}
	
	public String getColour() {
		return colour;
	}
	
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public int getSpriteSpeed() {
		return spriteSpeed;
	}
	
	public void setSpriteSpeed(int spriteSpeed) {
		this.spriteSpeed = spriteSpeed;
	}
	
	public ArrayList<String> getSprite() {
		return sprite;
	}
	
	public void setSprite(ArrayList<String> sprite) {
		this.sprite = sprite;
	}
	
	public void addSprite(String newSprite) {
		sprite.add(newSprite);
	}
	
}
