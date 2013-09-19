package au.edu.newcastle.SENG48002013.game.engine.connections.output;

import au.edu.newcastle.SENG48002013.output.SetupMessage;
import au.edu.newcastle.SENG48002013.game.engine.connections.BaseServlet;
import au.edu.newcastle.SENG48002013.game.engine.resources.GameResources;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author rossbille
 */
@WebServlet("/setup")
public class OutputSetup extends BaseServlet {

    @Override
    protected void processRequest() throws IOException {
        ServletContext sc = getServletContext();
        response.setContentType("text/json");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter();
        SetupMessage setupMessages = GameResources.getResources();
        String outMessages = mapper.writeValueAsString(setupMessages);
        String callback = request.getParameter("callback");
        String output = String.format("%s(%s)", callback, outMessages);
        out.print(output);
    }
}
