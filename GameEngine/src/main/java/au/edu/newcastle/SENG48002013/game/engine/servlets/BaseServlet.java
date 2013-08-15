package au.edu.newcastle.SENG48002013.game.engine.servlets;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Base servlet for all other servlets to extend from
 * to help with security 
 * @author rossbille
 */
public abstract class BaseServlet extends HttpServlet
{

    protected abstract void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        processRequest(request, response);
    }
}
