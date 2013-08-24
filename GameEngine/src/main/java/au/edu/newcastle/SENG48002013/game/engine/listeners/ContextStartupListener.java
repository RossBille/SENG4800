package au.edu.newcastle.SENG48002013.game.engine.listeners;

import au.edu.newcastle.SENG48002013.game.engine.Processor;
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
		Processor processor = new Processor();
		//read in config
		
		//add to context
		sce.getServletContext().setAttribute("processor", processor);
		System.out.println("Context Set");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
	}
}
