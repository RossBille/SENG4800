/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.input;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 *
 * @author rowanburgess
 *
 * http://stackoverflow.com/questions/17936440/accessing-httpsession-from-httpservletrequest-in-a-web-socket-socketendpoint
 */
public class SocketConfig extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig config,
            HandshakeRequest request,
            HandshakeResponse response) 
    {
        HttpSession httpSession = (HttpSession) request.getHttpSession();
        config.getUserProperties().put(HttpSession.class.getName(), httpSession);
    }
}
