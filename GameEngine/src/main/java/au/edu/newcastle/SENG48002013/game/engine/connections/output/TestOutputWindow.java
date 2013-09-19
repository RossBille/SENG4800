package au.edu.newcastle.SENG48002013.game.engine.connections.output;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.Vector2d;

import au.edu.newcastle.SENG48002013.output.SetupMessage;
import au.edu.newcastle.SENG48002013.game.engine.model.IGameOutput;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.IGameObject;
import au.edu.newcastle.SENG48002013.game.engine.resources.GameResources;

public class TestOutputWindow extends JFrame {

    HashMap<String, BufferedImage> images;
    HashMap<Long, Vector2d> objects;
    IGameObject[] gameObjects;

    public TestOutputWindow() {
        super("Output");
        objects = new HashMap<Long, Vector2d>();
        images = new HashMap<String, BufferedImage>();
        InitializeComponents(new Dimension(640, 480));
        SetupMessage sm = GameResources.getResources();
        String[] imageUrls = sm.getImageUrls();
        for (int i = 0; i < imageUrls.length; i++) {
            try {
                BufferedImage img = ImageIO.read(new File(imageUrls[i]));
                images.put(imageUrls[i], img);
            } catch (Exception e) {
            }
        }
        long[] objectIds = sm.getObjectIds();
        for (int i = 0; i < objectIds.length; i++) {
            objects.put(objectIds[i], new Vector2d());
        }
    }

    private void InitializeComponents(Dimension size) {
        setUndecorated(true);
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

    private class EnvironmentPanel extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D brush = (Graphics2D) g;
            brush.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            brush.setColor(Color.BLACK);
            brush.setStroke(new BasicStroke(1));
            Iterator<Vector2d> objectIter = objects.values().iterator();
            if (gameObjects != null) {
                for (int i = 0; i < gameObjects.length; i++) {
                    Vector2d pos = gameObjects[i].getOutputPos();
                    brush.drawImage(images.get(gameObjects[i].getImageUrl()), (int) pos.x, (int) pos.y, null);
                }
            }
        }
    }

    public void sendOutput(IGameOutput game) {
        gameObjects = game.getOutputObjects();
        this.repaint();
    }
}