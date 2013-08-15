package au.edu.newcastle.SENG48002013.input;

import au.edu.newcastle.seng48002013.results.Result;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;

/**
 * Security manager to handle token generation and checking for invalid requests
 * to the Connection Broker
 */

public class SecurityManager
{
    private SecureRandom random;
    private HashMap connectedClients;
    private HashMap validTokens;
    private long timeout; //milliseconds a generated token is valid for
    private final int MAX_CLIENTS;
    private String endpoint;
    
        
    
    public SecurityManager(long time, int maxClients, String end)
    {
        validTokens = new HashMap();
        connectedClients = new HashMap();
        timeout = time;
        MAX_CLIENTS = maxClients;
        endpoint = end;
    }
    
    public String generateToken()
    {
        random = new SecureRandom();
        String token = new BigInteger(130, random).toString(32); //generate token
        long validity = System.currentTimeMillis() + timeout;
       
        validTokens.put(token, validity);
        return token;
    }
    
    public boolean checkToken(String token, String iD)
    {
        long current = System.currentTimeMillis();
 
        if (validTokens.containsKey(token)) //check we have that token
        {
            long expiry = (long) validTokens.get(token);
            if ((current-expiry) > 0) // if token has not yet expired
            {
                validTokens.remove(token); //token used, session now established
                connectedClients.put(iD, token); //client is now in the system
                return true; 
            }
            
            else //token timeout
            {
                return false;
            }
        }
        
        else // token not found
        {
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
    
    public boolean spaceAvailable()
    {
        if (connectedClients.size() < MAX_CLIENTS)
        {
            return true;
        }
        
        else
        {
            return false;
        }
    }
    
    public Result serviceRequest()
    {
        Result r = new Result();
        
        if (spaceAvailable())
        {
            r.setError(false);
            r.setMessage(endpoint);
            r.setCode(generateToken());         
        }
        
        else // cannot service request, no space available
        {
            r.setError(true);
            r.setMessage("No spaces available");
            r.setCode("404");
        }
        
        return r;       
    }
    


 
}
