package au.edu.newcastle.SENG48002013.messages;

/**
 *
 * @author rossbille
 */
public class PlayerNumberMessage extends BaseMessage
{
	private boolean connecting;//true is connecting, false if disconnecting

		public boolean isConnecting()
		{
				return connecting;
		}

		public void setConnecting(boolean connecting)
		{
				this.connecting = connecting;
		}
}
