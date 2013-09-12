/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.seng48002013.xmlparser;

/**
 *
 * @author Bracks
 */
public class LevelObject extends GameObject {
	
	// Variables
	private String objectType;
        private String shape;
	private int initSpeed;
	private int maxSpeed;
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
        
	public String getObjectType() {
		return objectType;
	}
	
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	
	public int getInitSpeed() {
		return initSpeed;
	}
	
	public void setInitSpeed(int initSpeed) {
		this.initSpeed = initSpeed;
	}
	
	public int getMaxSpeed() {
		return maxSpeed;
	}
	
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
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
