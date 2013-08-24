package au.edu.newcastle.SENG48002013.input;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author rowanburgess
 */
@WebListener
public class Listener implements ServletContextListener 
{

    @Override
    public void contextInitialized(ServletContextEvent event) 
    {
                event.getServletContext().setAttribute("securityManager", 
                new SecurityManager(10000, 5, "ws://localhost:8080/Input/endpoint"));
        System.out.println("Security Manager Created");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) 
    {
        //To DO
    }
}
