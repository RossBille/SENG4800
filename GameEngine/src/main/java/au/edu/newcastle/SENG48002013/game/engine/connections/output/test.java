/*
	Test class set up to see how output can be sent
	Delete from final copy
 */
package au.edu.newcastle.SENG48002013.game.engine.connections.output;

import au.edu.newcastle.SENG48002013.game.engine.model.IGameOutput;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.GameObject;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.IGameObject;
import au.edu.newcastle.SENG48002013.game.engine.model.environment.Sprite;
import java.io.IOException;
import javax.vecmath.Vector2d;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author rossbille
 */
public class test
{

	public static void main(String[] args) throws IOException
	{
		GameOutput gameOutput = new GameOutput();
		ObjectMapper mapper = new ObjectMapper();
		String m = mapper.typedWriter(IGameObject.class).writeValueAsString(gameOutput.getOutputObjects()[0]);
		String message = mapper.writeValueAsString(gameOutput.getOutputObjects());
		System.out.println(message);
		
	}

	private static class GameOutput implements IGameOutput
	{

		@Override
		public IGameObject[] getOutputObjects()
		{
			GameObject object1 = new GameObject(1);
			GameObject object2 = new GameObject(2);
			GameObject object3 = new GameObject(3);
			
			object1.setSprite(new Sprite(4));
			object2.setSprite(new Sprite(5));
			object3.setSprite(new Sprite(6));
			
			object1.setPos(new Vector2d(1, 2));
			object2.setPos(new Vector2d(1, 2));
			object3.setPos(new Vector2d(1, 2));
			
			IGameObject gameObject1 = object1;
			IGameObject gameObject2 = object2;
			IGameObject gameObject3 = object3;

			IGameObject[] gameObjects = new IGameObject[3];
			gameObjects[0] = gameObject1;
			gameObjects[1] = gameObject2;
			gameObjects[2] = gameObject3;

			return gameObjects;

		}

	}
}
