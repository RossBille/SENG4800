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

    protected abstract void processRequest() throws IOException;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.request = request;
        this.response = response;
        processRequest();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.request = request;
        this.response = response;
        processRequest();
    }
}
