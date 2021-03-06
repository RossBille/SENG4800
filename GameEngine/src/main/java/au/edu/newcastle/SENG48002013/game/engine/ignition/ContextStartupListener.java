package au.edu.newcastle.SENG48002013.game.engine.ignition;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author rossbille
 */
@WebListener
public class ContextStartupListener implements ServletContextListener {

    /**
	 *
	 * @param sce
	 */
	@Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            //init game stuff here
            //read in config
            //start the game
            UpdateManager.start(sce.getServletContext().getRealPath("/config/Game1/"));
            System.out.println("Context Set");

        } catch (IOException ex) {
            Logger.getLogger(ContextStartupListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
	 *
	 * @param sce
	 */
	@Override
    public void contextDestroyed(ServletContextEvent sce) {
        UpdateManager.stop();
    }
}
