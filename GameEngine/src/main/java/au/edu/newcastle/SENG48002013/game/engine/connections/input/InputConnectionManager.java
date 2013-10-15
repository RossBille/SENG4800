package au.edu.newcastle.SENG48002013.game.engine.connections.input;

import au.edu.newcastle.SENG48002013.game.engine.connections.BaseServlet;
import au.edu.newcastle.SENG48002013.game.engine.processor.Boss;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import au.edu.newcastle.SENG48002013.messages.PlayerNumberMessage;
import au.edu.newcastle.SENG48002013.messages.responses.Response;
import au.edu.newcastle.SENG48002013.util.ResultCode;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Servlet class to access when adding or removing players from the game
 *
 * @author rossbille
 */
@WebServlet("/PlayerManager")
public class InputConnectionManager extends BaseServlet {

    private long lastId;

    public InputConnectionManager() {
        lastId = 0;
    }

    @Override
    protected void processRequest() throws IOException {
        Response r = new Response();

        //parse instruction
        ObjectMapper mapper = new ObjectMapper();
        PlayerNumberMessage pnm;
        pnm = mapper.readValue(extractJson(), PlayerNumberMessage.class);
        System.out.println(pnm.toString());
        //check if adding or removing
        if (pnm.isConnecting()) {
            long generatedId = generateId(pnm);
			System.out.println("GeneratedId:"+generatedId);
            boolean addPlayer = Boss.addPlayer(generatedId);
            if (addPlayer) {
                r.setError(false);
                r.setCode((int) generatedId);//return the player number to the Input component 
                r.setMessage("Player has been added");
            } else {
                r.setError(true);
                r.setMessage("No Room");
                r.setCode(ResultCode.INSUFFICIENT_ROOM);
            }

        } else {
            //remove player from the game
            Boss.removePlayer(pnm.getPlayer());
            r.setError(false);
            r.setMessage("Player was removed");
            r.setCode(ResultCode.SUCCESS);
        }

    }

    private String extractJson() {
        System.out.println(request.getParameter("data"));
        return request.getParameter("data");
    }

    private void respond(Response r) throws IOException {
        response.setContentType("text/JSON");
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(r));
    }

    private int convert(boolean result) {
        if (result) {
            return 1;
        } else {
            return -1;
        }
    }

    private long generateId(PlayerNumberMessage pnm) {
        System.out.println("generated a new playerid");
		System.out.println("new player id:"+ (lastId +1));
        return ++lastId;
    }
}
