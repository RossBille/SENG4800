package au.edu.newcastle.SENG48002013.game.engine.listeners;

import au.edu.newcastle.SENG48002013.game.engine.ignition.UpdateManager;
import au.edu.newcastle.SENG48002013.game.engine.processor.Boss;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author rossbille
 */
@WebListener
public class ContextStartupListener implements ServletContextListener
{
	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		//init game stuff here
		Boss boss = new Boss();
		//read in config

		//add to context
		sce.getServletContext().setAttribute("boss", boss);
		//start the game
		UpdateManager.start(sce.getServletContext());
		System.out.println("Context Set");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
		UpdateManager.stop(sce.getServletContext());
	}
}
