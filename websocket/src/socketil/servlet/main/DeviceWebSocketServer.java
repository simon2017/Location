package socketil.servlet.main;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import socketil.data.BasicMessage;
import socketil.data.ChatMessage;
import socketil.data.LoginMessage;

import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
@ServerEndpoint("/javaxTest")
public class DeviceWebSocketServer {

	private static final ChannelsHandler channelHandler = new ChannelsHandler();

	@OnOpen
	public void open(Session session) {
		System.out.println("open");

	}

	@OnClose
	public void close(Session session) {
		System.out.println("close");
	}

	@OnError
	public void onError(Throwable error) {
		System.out.println("Error");
	}

	@OnMessage
	public void handleMessage(String message, Session session) {
		ObjectMapper mapper = new ObjectMapper();
		BasicMessage basicMes = null;
		try {
			basicMes = mapper.readValue(message, BasicMessage.class);

			if (basicMes.getType().equals("login")) {
				LoginMessage loginMes = mapper.readValue(message, LoginMessage.class);
				System.out.println(loginMes.getChannel());
				channelHandler.addSessionto(loginMes.getChannel(), session);
				session.getBasicRemote().sendText("200 logged OK");
			}
			if (basicMes.getType().equals("chatmessage")) {
				ChatMessage chatMessage = mapper.readValue(message, ChatMessage.class);
				System.out.println(chatMessage);
				channelHandler.sendMessageTo(chatMessage.getChannel(), chatMessage.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}