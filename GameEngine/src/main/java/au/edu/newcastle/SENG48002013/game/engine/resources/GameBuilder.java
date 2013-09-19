/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.resources;

import javax.vecmath.Vector2d;
import org.w3c.dom.*;

import au.edu.newcastle.SENG48002013.game.engine.model.*;
import au.edu.newcastle.SENG48002013.game.engine.model.events.*;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.*;

public class GameBuilder {

    public static IGame buildGame() {
        buildGame(ConfigReader.readGame());
        return GameResources.getGame();
    }

    public static Level loadLevel() {
        //TO DO
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void buildGame(Element gameElement) {
        Game game = new Game();
        //Set canvas size
        Vector2d size = XmlUtils.getVectorValue(gameElement, "CANVAS_SIZE");
        game.setSize(size);
        //Create players
        Vector2d maxMinPlayers = XmlUtils.getVectorValue(gameElement, "PLAYERS");
        int maxPlayers = (int) maxMinPlayers.x;
        int minPlayers = (int) maxMinPlayers.y;
        Player[] players = new Player[maxPlayers];
        for (int i = 0; i < maxPlayers; i++) {
            Player player = new Player(i);
            player.setScore(0);
            players[i] = player;
            GameResources.addPlayer(player);
        }
        game.setMaxPlayers(maxPlayers);
        game.setMinPlayers(minPlayers);
        game.setPlayers(players);
        //Build sprites
        NodeList spriteNodes = gameElement.getElementsByTagName("SPRITE");
        buildSprites(spriteNodes);
        //Build backgrounds
        NodeList backgroundNodes = gameElement.getElementsByTagName("BACKGROUND");
        buildBackgrounds(backgroundNodes);
        //Add to game resources
        GameResources.setGame(game);
        //Build levels
        buildLevels(ConfigReader.readLevels());
        //Set levels
        game.setLevels(GameResources.getAllLevels());
        //Set starting level
        long startingLevel = (long) XmlUtils.getNumericValue(gameElement, "STARTING_LEVEL");
        game.setCurrentLevel(startingLevel);
    }

    private static void buildLevels(NodeList levelNodes) {
        if (levelNodes != null && levelNodes.getLength() > 0) {
            //Build Levels
            for (int i = 0; i < levelNodes.getLength(); i++) {
                Element levelElement = (Element) levelNodes.item(i);
                buildLevel(levelElement);
            }
            //Build events for each level
            for (int i = 0; i < levelNodes.getLength(); i++) {
                Element levelElement = (Element) levelNodes.item(i);
                NodeList eventNodes = levelElement.getElementsByTagName("EVENT");
                long id = (long) XmlUtils.getNumericValue(levelElement, "LEVEL_ID");
                Level level = GameResources.getLevel(id);
                buildEvents(eventNodes, level);
            }
        }
    }

    private static void buildLevel(Element levelElement) {
        if (levelElement != null) {
            long id = (long) XmlUtils.getNumericValue(levelElement, "LEVEL_ID");
            Level level = new Level(id);
            long backgroundId = (long) XmlUtils.getNumericValue(levelElement, "BACKGROUND_ID");
            level.setBackground(GameResources.getBackground(backgroundId));
            level.setDimensions(((Game) GameResources.getGame()).getSize());
            NodeList objectNodes = levelElement.getElementsByTagName("OBJECT");
            buildGameObjects(objectNodes, level);
            GameResources.addLevel(level);
        }
    }

    private static void buildEvents(NodeList eventNodes, Level parentLevel) {
        if (eventNodes != null && eventNodes.getLength() > 0) {
            for (int i = 0; i < eventNodes.getLength(); i++) {
                Element eventElement = (Element) eventNodes.item(i);
                IEvent event = (EventFactory.buildEvent(eventElement));
                parentLevel.addEvent(event);
            }
        }
    }

    private static void buildGameObjects(NodeList objectNodes, Level parentLevel) {
        if (objectNodes != null && objectNodes.getLength() > 0) {
            for (int i = 0; i < objectNodes.getLength(); i++) {
                Element objectElement = (Element) objectNodes.item(i);
                //Instantiate object
                long id = (long) XmlUtils.getNumericValue(objectElement, "OBJECT_ID");
                GameObject gameObject = new GameObject(id);
                //Set Sprite
                long spriteId = (long) XmlUtils.getNumericValue(objectElement, "SPRITE_ID");
                gameObject.setSprite(GameResources.getSprite(spriteId));
                //Set Shape
                Element shapeElement = (Element) objectElement.getElementsByTagName("OBJECT_SHAPE").item(0);
                Shape shape = null;
                //If circle shape
                if (shapeElement.getElementsByTagName("CIRCLE").getLength() > 0) {
                    Element circleElement = (Element) shapeElement.getElementsByTagName("CIRCLE").item(0);
                    double radius = XmlUtils.getNumericValue(circleElement, "RADIUS");
                    shape = new Circle(radius);
                } //If rectangle shape
                else if (shapeElement.getElementsByTagName("RECTANGLE").getLength() > 0) {
                    Element rectangleElement = (Element) shapeElement.getElementsByTagName("RECTANGLE").item(0);
                    Vector2d size = XmlUtils.getVectorValue(rectangleElement, "SIZE");
                    shape = new Rectangle(size);
                }
                gameObject.setShape(shape);
                //Set Visible
                boolean visible;
                String strVisible = XmlUtils.getTextValue(objectElement, "OBJECT_VISIBLE");
                if (strVisible.toUpperCase().equals("YES")) {
                    visible = true;
                } else {
                    visible = false;
                }
                gameObject.setActive(visible);
                //Set Start Position
                Vector2d startPos = XmlUtils.getVectorValue(objectElement, "START_POS");
                gameObject.setPos(startPos);
                //Set Start Velocity
                Vector2d startVel = XmlUtils.getVectorValue(objectElement, "START_VEL");
                gameObject.setVel(startVel);
                //Set Start Acceleration
                Vector2d startAcc = XmlUtils.getVectorValue(objectElement, "START_ACC");
                gameObject.setAcc(startAcc);
                //Add game object to level
                parentLevel.addGameObject(gameObject);
                //Add game object to game resources
                GameResources.addGameObject(gameObject);
            }
        }
    }

    private static void buildSprites(NodeList spriteNodes) {
        if (spriteNodes != null && spriteNodes.getLength() > 0) {
            for (int i = 0; i < spriteNodes.getLength(); i++) {
                Element spriteElement = (Element) spriteNodes.item(i);
                buildSprite(spriteElement);
            }
        }
    }

    private static void buildSprite(Element spriteElement) {
        long spriteId = (long) XmlUtils.getNumericValue(spriteElement, "SPRITE_ID");
        Sprite sprite = new Sprite(spriteId);
        //Set the image urls
        NodeList imageNodes = spriteElement.getElementsByTagName("IMAGE");
        String[] imageUrls = new String[imageNodes.getLength()];
        for (int i = 0; i < imageNodes.getLength(); i++) {
            Element imageElement = (Element) imageNodes.item(i);
            String imageUrl = imageElement.getTextContent();
            imageUrls[i] = imageUrl;
        }
        sprite.setImageUrls(imageUrls);
        //Set the speed
        long speed = (long) XmlUtils.getNumericValue(spriteElement, "SPEED");
        sprite.setSpeed(speed);
        //Set the offset
        Vector2d offset = XmlUtils.getVectorValue(spriteElement, "OFFSET");
        sprite.setOffset(offset);
        //Add to game resources
        GameResources.addSprite(sprite);
    }

    private static void buildBackgrounds(NodeList backgroundNodes) {
        if (backgroundNodes != null && backgroundNodes.getLength() > 0) {
            for (int i = 0; i < backgroundNodes.getLength(); i++) {
                Element backgroundElement = (Element) backgroundNodes.item(i);
                buildBackground(backgroundElement);
            }
        }
    }

    private static void buildBackground(Element backgroundElement) {
        long backgroundId = (long) XmlUtils.getNumericValue(backgroundElement, "BACKGROUND_ID");
        Background background = new Background(backgroundId);
        //Set the image url
        String imageUrl = XmlUtils.getTextValue(backgroundElement, "IMAGE");
        background.setImageUrls(new String[]{imageUrl});
        //Set a default speed (since we inherit from sprite)
        background.setSpeed(24);
        //Set the position type
        String strPositionType = XmlUtils.getTextValue(backgroundElement, "POSITION_TYPE");
        switch (strPositionType.toUpperCase()) {
            case "TILED":
                background.setPositionType(Background.PositionType.TILED);
                break;
            case "STRETCH":
                background.setPositionType(Background.PositionType.STRETCH);
                break;
            case "FILL":
                background.setPositionType(Background.PositionType.FILL);
                break;
            case "CENTRE":
                background.setPositionType(Background.PositionType.CENTER);
                break;
        }
        //Add to game resources
        GameResources.addBackground(background);
    }
}
