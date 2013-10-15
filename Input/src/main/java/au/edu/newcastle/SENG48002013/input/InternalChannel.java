package au.edu.newcastle.SENG48002013.input;

import au.edu.newcastle.SENG48002013.input.websocket.WebSocketClient;
import au.edu.newcastle.SENG48002013.messages.PlayerNumberMessage;
import au.edu.newcastle.SENG48002013.messages.responses.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author rowanburgess
 */
public class InternalChannel
{
    private WebSocketClient engineChannel;
    private String playerManagerURL, instructionWebSocket;
    
    public InternalChannel(String engineWebSocket, String engineServlet)
    {
        this.playerManagerURL = engineServlet;
        this.instructionWebSocket = engineWebSocket;
        this.engineChannel = new WebSocketClient();
        this.engineChannel.start(instructionWebSocket);     
    }
    
    /*
     * Sends Http Post to Game Engine Servlet /PlayerManager indicating that
     * a player either connecting or disconnecting based on boolean flag
     */
    public long sendPlayerControlMessage(boolean connecting, long iD)
    {
        long playerID = -1;
        PlayerNumberMessage pnm = new PlayerNumberMessage();
        pnm.setConnecting(connecting);
        
        if (!connecting)
        {
            pnm.setPlayer((int) iD);
        }
        
        ArrayList<NameValuePair> parameters; // for name/value pairs of req params
        HttpClient httpclient = new DefaultHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        
        try
        {
            parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair("data", mapper.writeValueAsString(pnm)));         
            HttpPost post = new HttpPost(playerManagerURL);
            post.setEntity(new UrlEncodedFormEntity(parameters));           
            HttpResponse result = httpclient.execute(post); 
            InputStream stream = result.getEntity().getContent();
            String clientHello = IOUtils.toString(stream, "UTF-8"); 
            Response postResult = mapper.readValue(clientHello, Response.class);
            
            if (!postResult.isError())
            {
                playerID = postResult.getCode();
            }                   
        }
        
        catch (URISyntaxException | HttpException | IOException ex)
        {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return playerID;
    }

    public void sendGameInstruction(String instruction)
    {
        try
        {
            engineChannel.getSession().getBasicRemote().sendText(instruction);
        }
        
        catch (IOException ex)
        {
            Logger.getLogger(InternalChannel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
