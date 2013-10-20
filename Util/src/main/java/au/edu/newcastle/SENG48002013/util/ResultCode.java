package au.edu.newcastle.SENG48002013.util;

/**
 *
 * @author rossbille
 */
public class ResultCode
{
	/**
	 * System wide success code
	 */
	public static final int SUCCESS = 1;
	/**
	 * System wide unauthorised access code 
	 */
	public static final int UNAUTHORISED_ACCESS = -1;
	/**
	 * System wide code to identify there is no more allowed connections to a socket
	 */
    public static final int INSUFFICIENT_ROOM = -2;	
}
