package au.edu.newcastle.SENG48002013;

import au.edu.newcastle.seng48002013.instructions.phone.TouchScreen;
import au.edu.newcastle.seng48002013.results.Result;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author rowanburgess
 *
 * This is an example implementation of a Client, catering for web browsers.
 * This servlet contacts the connection broker, requesting a position in the
 * game.
 */
@WebServlet(name = "connect", urlPatterns =
{
    "/connect"
})
public class Client extends HttpServlet
{

    String connection;
    ArrayList<String> instructions;

    public Client()
    {
        this.connection= "http://localhost:8181/Input/connect";
        this.instructions = new ArrayList<>();
        this.instructions.add("left");
        this.instructions.add("right");
        this.instructions.add("up");
        this.instructions.add("down");
    }

    /*
     * Calls the connection broker to try and reserve a place in the game.
     * If the request is successful, result mesage will contain a token for us
     * to connect with, along with a URL for the web socket endpoint to connect to
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        System.out.println("Inside doGet...");
        requestConnection(request); //from the Connection broker
        setupInstructions(request);
        request.getRequestDispatcher("/WEB-INF/sampleClient.jsp").forward(request, response);
    }

    /*
     * Post method to fall back on, in the event that the client is not able
     * to utilise web sockets, we can post requests to the game engine instead
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        //fallback method in the event that client doesnt support web sockets
    }

    public void requestConnection(HttpServletRequest r) throws IOException
    {
        HttpClient httpclient = new DefaultHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        InputStream stream = null;

        try
        {
            HttpGet httpget = new HttpGet(connection);
            HttpResponse res = httpclient.execute(httpget);
            stream = res.getEntity().getContent();
            String clientHello = IOUtils.toString(stream, "UTF-8"); //send this back to the server to prove ID
            Result result = mapper.readValue(clientHello, Result.class);
            r.setAttribute("result", result);
        }
        
        catch (URISyntaxException | HttpException ex)
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally //ensure that the stream gets closed regardless of outcome
        {
            if (stream != null)
            {
                stream.close();
            }
        }
    }

    public void setupInstructions(HttpServletRequest r) throws IOException
    {
        ArrayList<instructionSet> instructionList = new ArrayList<>(); 
        ObjectMapper mapper = new ObjectMapper();
        String OS = r.getHeader("User-Agent");
        String JSESSIONID = r.getSession().getId();
        
        for (String temp : instructions)
        {
            TouchScreen t = new TouchScreen(temp, OS, JSESSIONID);
            instructionSet i = new instructionSet(temp, mapper.writeValueAsString(t));
            instructionList.add(i);
        }

        r.setAttribute("instructions", instructionList);
    }
    
    public class instructionSet
    {
        String key;
        String value;

        public instructionSet(String k, String v)
        {
            this.key = k;
            this.value = v;
        }

        public String getKey()
        {
            return key;
        }

        public void setKey(String key)
        {
            this.key = key;
        }

        public String getValue()
        {
            return value;
        }

        public void setValue(String value)
        {
            this.value = value;
        }   
    }
    
}
