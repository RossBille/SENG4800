package au.edu.newcastle.SENG48002013.input.servlets;

import au.edu.newcastle.SENG48002013.input.SecurityManager;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Initial entry point for clients wanting to connect to the system. Receives a
 * registration request from the client, determines if it should be granted.
 */
@WebServlet("/connect")
public class ConnectionBroker extends BaseServlet {

    private String extractId(HttpServletRequest request) {
        return request.getParameter("ID");
    }

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, SecurityManager secMan) throws IOException {

        if (secMan.checkRequest(request.getRemoteAddr())) //check for DOS
        {
            // check for space in game, request token from security manager
            response.setContentType("application/JSON");
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(secMan.serviceRequest()));
        }
    }
}
