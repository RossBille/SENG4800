package au.edu.newcastle.SENG48002013.game.engine.listeners;

import au.edu.newcastle.SENG48002013.game.engine.Processor;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author rossbille
 */
public abstract class ContextStartupListener implements ServletContextListener
{

	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		//init game stuff here
		Processor processor = new Processor();
		//read in config
		
		//add to context
		sce.getServletContext().setAttribute("processor", processor);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
	}
}
