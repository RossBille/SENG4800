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
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author rowanburgess
 *
 * This is an example implementation of a Client, catering for
 * web browsers. This servlet contacts the connection broker, and based on the
 * JSON that is returned, directs the end user accordingly.
 */
@WebServlet("/connect")
public class Client extends HttpServlet 
{

    String connection = "http://localhost:8080/Input/connect";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String requestType = request.getParameter("type");
        HttpSession session = request.getSession();
        
        if (requestType.equalsIgnoreCase("connect"))
        {
            Result res = requestConnection(); //from the Connection broker
            session.setAttribute("url", res.getMessage());
            session.setAttribute("token", res.getCode());
            
            if (!res.isError()) //if all goes well
            {              
               response.sendRedirect("accepted.jsp");
            }
            
            else
            {
                response.sendRedirect("welcome.jsp");
            }
        }     
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
