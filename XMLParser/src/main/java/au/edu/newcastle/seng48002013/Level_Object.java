/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.seng48002013;

/**
 *
 * @author Bracks
 */
public class Level_Object extends Game_Object {
	
	// Variables
	private String object_type;
        private String shape;
	private int init_speed;
	private int max_speed;
	private int position;
	private int direction;
        private boolean solid;
        private boolean visible;
	
	// Mutators
        public boolean isVisible() {
		return this.visible;
	}
        
        public void setVisible(String visible) {
		if(visible.equals("yes"))
                {
                    this.visible = true;
                } 
                else
                    this.visible = false;
	}
        
        public boolean isSolid() {
		return this.solid;
	}
        
        public void setSolid(String solid) {
		if(solid.equals("yes"))
                {
                    this.solid = true;
                } 
                else
                    this.solid = false;
	}
        
	public String getObject_type() {
		return object_type;
	}
	
	public void setObject_type(String object_type) {
		this.object_type = object_type;
	}
	
	public int getInit_speed() {
		return init_speed;
	}
	
	public void setInit_speed(int init_speed) {
		this.init_speed = init_speed;
	}
	
	public int getMax_speed() {
		return max_speed;
	}
	
	public void setMax_speed(int max_speed) {
		this.max_speed = max_speed;
	}
	
        public String getShape() {
		return shape;
	}
	
	public void setShape(String shape) {
		this.shape = shape;
	}
        
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
}
