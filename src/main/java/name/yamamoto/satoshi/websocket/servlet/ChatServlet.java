package name.yamamoto.satoshi.websocket.servlet;

import name.yamamoto.satoshi.websocket.handler.ChatHandler;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class ChatServlet extends WebSocketServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void configure(WebSocketServletFactory factory) {
		factory.register(ChatHandler.class);
	}

}
