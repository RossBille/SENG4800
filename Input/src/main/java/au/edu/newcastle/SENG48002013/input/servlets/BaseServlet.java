package au.edu.newcastle.SENG48002013.input.servlets;

import java.io.IOException;
import au.edu.newcastle.SENG48002013.input.SecurityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ross
 */
public abstract class BaseServlet extends HttpServlet 
{

    /**
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {    
        SecurityManager secMan = (SecurityManager) getServletContext().getAttribute("securityManager");
        
        if (secMan.checkRequest(request)) //perform security check on request object
        {
            processRequest(request, response, secMan);
        }
    }

 
    /**
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        SecurityManager secMan = (SecurityManager) getServletContext().getAttribute("securityManager");
        
        if (secMan.checkRequest(request)) //perform security check on request object
        {
            processRequest(request, response, secMan);
        }      
    }

    /**
	 *
	 * @param request
	 * @param response
	 * @param secMan
	 * @throws IOException
	 */
	protected abstract void processRequest(HttpServletRequest request, HttpServletResponse response, SecurityManager secMan) throws IOException;

}
