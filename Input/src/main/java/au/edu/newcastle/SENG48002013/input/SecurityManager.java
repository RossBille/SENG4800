package au.edu.newcastle.SENG48002013.input;

/**
 * Security manager to handle token generation and checking for invalid requests
 * to the Connection Broker
 */

@javax.enterprise.context.ApplicationScoped
public class SecurityManager 
{
       
    public String requestToken(String s)
    {
        /* generate a token based on s here, store it with a timeout, client 
         * must provide token to engine, who checks its validity here
         */
        return "mysecrettoken";
    }
    
    public boolean checkToken(String s)
    {
        if (s.equalsIgnoreCase("mysecrettoken"))
        {
            return true;
        }
        
        else
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
    
}
