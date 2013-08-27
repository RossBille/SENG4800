package au.edu.newcastle.SENG48002013.input;

import au.edu.newcastle.seng48002013.results.Result;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Security manager to handle token generation and checking for invalid requests
 * to the Connection Broker
 */
public class SecurityManager
{
    private final static Logger log = Logger.getLogger(SecurityManager.class.getName());
    private SecureRandom random;
    private HashMap validTokens;
    private long timeout; //milliseconds a generated token is valid for
    private String endpoint;
    private Engine engine;

    public SecurityManager(long time, String end, Engine e)
    {
        validTokens = new HashMap();
        timeout = time;
        endpoint = end;
        engine = e;
        log.log(Level.INFO, "Security Manager created. ID: {0}", this.toString());
    }

    public String generateToken()
    {
        random = new SecureRandom();
        String token = new BigInteger(130, random).toString(32); //generate token
        long validity = System.currentTimeMillis() + timeout;

        validTokens.put(token, validity);
        log.log(Level.INFO, "Token generated: {0}", token);
        return token;
    }

    public boolean checkToken(String token)
    {
        long current = System.currentTimeMillis();

        if (validTokens.containsKey(token)) //check we have that token
        {
            long expiry = (long) validTokens.get(token);
            log.log(Level.INFO, "Token: {0} Time Remaining: {1}", new Object[]{token, expiry - current});
            if ((expiry - current) > 0) // if token has not yet expired
            {
                validTokens.remove(token); //token used, session now established
                return true;
            }
            else //token timeout
            {
                log.log(Level.INFO, "Token: {0} has timed out. Validation failed.", token);
                return false;
            }
        }
        
        else // token not found
        {
            log.log(Level.INFO, "Token {0} was not found", token);
            return false;
        }
    }

    public boolean checkRequest(String message)
    {
        /* check for malformed requests, or other suspicious activity here first
         * before actioning the request
         */
        return true;
    }

    /*
     * Returns a result obj inicating the result
     * of a clients connection request
     * 
     */
    public Result serviceRequest()
    {
        Result r = new Result();
        /*
         * if there is space for another player in the engine, and number of
         * currently issued valid tokens leaves > 0 free spaces, issue token
         */

        if (engine.spaceFree() && engine.freeSpaces() - validTokens.size() > 0) 
        {
            r.setError(false);
            r.setMessage(endpoint);
            r.setCode(generateToken());
            log.info("Token Issued");
        }
        
        else // cannot service request, no space available
        {
            r.setError(true);
            r.setMessage("fail");
            r.setCode("0");
            log.info("oken request failed");
        }

        return r;
    }
}
