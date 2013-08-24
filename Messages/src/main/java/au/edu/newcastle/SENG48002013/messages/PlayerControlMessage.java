package au.edu.newcastle.SENG48002013.messages;

import javax.vecmath.Vector3d;

/**
 *
 * @author rossbille
 */
public class PlayerControlMessage extends BaseMessage
{
	private String code;
	private Vector3d direction;

		public String getCode()
		{
				return code;
		}

		public void setCode(String code)
		{
				this.code = code;
		}

		public Vector3d getDirection()
		{
				return direction;
		}

		public void setDirection(Vector3d direction)
		{
				this.direction = direction;
		}
	
}
