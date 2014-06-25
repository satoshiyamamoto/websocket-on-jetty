package name.yamamoto.satoshi.websocket.handler;

import java.io.IOException;
import java.util.Set;

import org.eclipse.jetty.util.ConcurrentHashSet;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class ChatHandler {
	private static Set<Session> sessions = new ConcurrentHashSet<>();

	@OnWebSocketConnect
	public void onConnect(Session session) throws IOException {
		System.out.println("connected. remote=" + session.getRemoteAddress());
		sessions.add(session);
	}

	@OnWebSocketMessage
	public void onMessage(Session session, String data) throws IOException {
		System.out.println("recieved. message=" + data);
		for (Session s : sessions) {
			s.getRemote().sendString(data);
		}
	}

	@OnWebSocketClose
	public void onClosed(Session session, int code, String reason) {
		System.out.println("closed. remote= " + session.getRemoteAddress() + ", code=" + code + ", reason=" + reason);
		sessions.remove(session);
	}

	@OnWebSocketError
	public void onError(Session session, Throwable e) {
		e.printStackTrace();
		sessions.remove(session);
	}

}
