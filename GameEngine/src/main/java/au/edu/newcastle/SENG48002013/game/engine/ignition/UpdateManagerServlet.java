package au.edu.newcastle.SENG48002013.game.engine.ignition;

import au.edu.newcastle.SENG48002013.game.engine.connections.BaseServlet;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

/**
 * Expose the interface to start and stop the game
 *
 * @author rossbille
 */
@WebServlet("/ignition")
public class UpdateManagerServlet extends BaseServlet
{

    @Override
    protected void processRequest() throws IOException
    {
        if (authenticated())
        {
            String action = request.getParameter("action");
            switch (action)
            {
                case "start":
                    UpdateManager.start();
                case "stop":
                    UpdateManager.stop();
                case "restart":
                    UpdateManager.restart();
            }
        }
    }

    private boolean authenticated()
    {
        return true; //TODO some form of authentication
    }
}
