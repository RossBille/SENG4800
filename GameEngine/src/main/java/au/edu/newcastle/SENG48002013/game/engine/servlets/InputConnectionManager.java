package au.edu.newcastle.SENG48002013.game.engine.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import au.edu.newcastle.SENG48002013.messages.PlayerNumberMessage;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Servlet class to access when adding or removing players from the game
 *
 * @author rossbille
 */
@WebServlet("/PlayerManager")
public class InputConnectionManager extends BaseServlet
{

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        //parse instruction
        ObjectMapper mapper = new ObjectMapper();
        PlayerNumberMessage pnm;
        pnm = mapper.readValue(extractJson(request), PlayerNumberMessage.class);
        //check if adding or removing
        if (pnm.isConnecting())
        {
            //add new player to the game
            throw new UnsupportedOperationException("Not supported yet.");
        } else
        {
            //remove player from the game
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    private String extractJson(HttpServletRequest request)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
