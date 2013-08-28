package au.edu.newcastle.SENG48002013.game.engine.connections.output;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.Vector2d;

import au.edu.newcastle.SENG48002013.game.engine.model.IGameOutput;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.IGameObject;

public class TestOutputWindow extends JFrame
{
	HashMap<Long, Vector2d> objects;
	public TestOutputWindow()
	{
		super("Output");
		objects = new HashMap<Long, Vector2d>();
		InitializeComponents(new Dimension(640,480));
	}

	private void InitializeComponents(Dimension size)
	{
		//setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		
		Container content = getContentPane(); 
		content.add(new EnvironmentPanel());
		setContentPane(content); 
		setVisible(true);
		int width = getInsets().left + getInsets().left + size.width;
		int height = getInsets().top + getInsets().bottom + size.height;
		setSize(width, height);
	}
	private class EnvironmentPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D brush = (Graphics2D) g;
	        brush.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);
	        brush.setColor(Color.BLACK);
	        brush.setStroke(new BasicStroke(10));
	        Iterator<Vector2d> objectIter = objects.values().iterator();
	        while(objectIter.hasNext())
	        {
	        	Vector2d pos = objectIter.next();
	        	brush.drawRect((int)pos.x, (int)pos.y, 10, 10);
	        }
		}
	}
	public void sendOutput(IGameOutput game)
	{
		IGameObject[] gameObjects = game.getOutputObjects();
		for(int i = 0; i < gameObjects.length; i++)
		{
			objects.put(gameObjects[i].getId(), gameObjects[i].getPos());
		}
		this.repaint();
	}
}