/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.newcastle.SENG48002013.game.engine.servlets;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author rossbille
 */
@ServerEndpoint("/MessageManager")
public class MessageManager
{

		private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

		@OnOpen
		public void onOpen()
		{
		}

		@OnClose
		public void onClose()
		{
		}

		@OnError
		public void onError()
		{
		}

		@OnMessage
		public void onMessage()
		{
		}
}
