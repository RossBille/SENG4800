package au.edu.newcastle.SENG48002013.game.engine.connections;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Base servlet for all other servlets to extend from to help with security
 *
 * @author rossbille
 */
public abstract class BaseServlet extends HttpServlet {

    protected HttpServletRequest request;
    protected HttpServletResponse response;

	/**
	 * Abstract method for all processing to go, takes no parameters as the request and response
	 * objects are member variables and will be set by the doGet() and doPost() methods
	 * @throws IOException
	 */
	protected abstract void processRequest() throws IOException;

	/**
	 * Standard method to deal with http:GET requests and call processRequest(), method will set member variables request and response
	 * to be used by processRequest()
	 * @param request
	 * @param response
	 * @throws IOException if processRequest throws an IOException
	 */
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.request = request;
        this.response = response;
        processRequest();
    }

	/**
	 * Standard method to deal with http:POST requests and call processRequest(), method will set member variables request and response
	 * to be used by processRequest()
	 * @param request
	 * @param response
	 * @throws IOException if processRequest throws an IOException
	 */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.request = request;
        this.response = response;
        processRequest();
    }
}
