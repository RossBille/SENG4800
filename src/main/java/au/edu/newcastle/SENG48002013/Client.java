package au.edu.newcastle.SENG48002013;

import au.edu.newcastle.seng48002013.results.Result;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
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
 * This servlet contacts the connection broker, and based on the JSON that is
 * returned, directs the end user accordingly.
 */
@WebServlet(name = "connect", urlPatterns ={"/connect"})
public class Client extends HttpServlet
{
    String connection = "http://localhost:8080/Input/connect";

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
        Result res = requestConnection(); //from the Connection broker
        request.setAttribute("result", res);
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

    public Result requestConnection() throws IOException
    {
        HttpClient httpclient = new DefaultHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        HttpGet httpget = null;
        HttpResponse res = null;
        Result r = null;

        try
        {
            httpget = new HttpGet(connection);
        }
        catch (URISyntaxException ex)
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        try
        {
            res = httpclient.execute(httpget);
        }
        catch (HttpException ex)
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (res.getEntity() != null)
        {
            InputStream stream = res.getEntity().getContent();

            try
            {
                String myString = IOUtils.toString(stream, "UTF-8");
                System.out.println("Output: " + myString);

                r = mapper.readValue(myString, Result.class);
                System.out.println("Message: " + r.getMessage());
            }
            finally
            {
                stream.close();
            }
        }

        return r;
    }
}
