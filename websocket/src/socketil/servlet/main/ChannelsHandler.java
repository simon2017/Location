package socketil.servlet.main;

import java.io.IOException;
import java.util.HashMap;

import javax.websocket.Session;

public class ChannelsHandler {
	private static final HashMap<String, Channel> sessionsMap = new HashMap<String, Channel>();

	public ChannelsHandler() {

	}

	public void addSessionto(String channelName, Session session) {
		synchronized (sessionsMap) {
			Channel channel = sessionsMap.get(channelName);
			if (channel == null)
				channel = new Channel(channelName);
			channel.addSession(session);
			sessionsMap.put(channelName, channel);
		}
	}

	public void removeSessionFrom(String channelName, Session session) {
		synchronized (sessionsMap) {
			Channel channel = sessionsMap.get(channelName);
			if (channel != null)
				channel.removeSession(session);
			if (channel.isEmpty())
				sessionsMap.remove(channel);
		}
	}

	public void sendMessageTo(String channelName, String message) {
		Channel channel = sessionsMap.get(channelName);
		if (channel == null)
			return;
		try {
			channel.sendMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendBroadCastMessage(String message) {
		synchronized (sessionsMap) {
			for (Channel channel : sessionsMap.values())
				try {
					channel.sendMessage(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
