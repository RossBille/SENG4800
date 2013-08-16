/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.seng48002013;

/**
 *
 * @author Bracks
 */
import java.util.ArrayList;

public class Game_Object {

	// Variables
	private String name;
	private int id;
	private int def_pos_x;
	private int def_pos_y;
	private String colour;
	private int sprite_speed;
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
	
	public int getDef_pos_x() {
		return def_pos_x;
	}
	
	public void setDef_pos_x(int position_x) {
		this.def_pos_x = position_x;
	}
	
	public int getDef_pos_y() {
		return def_pos_y;
	}
	
	public void setDef_pos_y(int position_y) {
		this.def_pos_y = position_y;
	}
	
	public String getColour() {
		return colour;
	}
	
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public int getSprite_speed() {
		return sprite_speed;
	}
	
	public void setSprite_speed(int sprite_speed) {
		this.sprite_speed = sprite_speed;
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
