package socketil.servlet.main;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.Session;

/**
 * Implementa un canal con Nombre definido y un listado de clientes conectados
 * 
 * @author sgutierc
 *
 */
public class Channel {
	private Set<Session> sessions = null;
	private final String name;

	public Channel(String name) {
		this.name = name;
		sessions = new HashSet<Session>();
	}

	public synchronized void addSession(Session session) {
		sessions.add(session);
	}

	public synchronized void removeSession(Session session) {
		sessions.remove(session);
	}

	public boolean isEmpty() {
		return sessions.isEmpty();
	}

	public String getName() {
		return this.name;
	}

	/**
	 * Envia mensaje a todos los usuarios conectados al canal
	 * 
	 * @param message
	 * @throws IOException
	 */
	public synchronized void sendMessage(String message) throws IOException {
		for (Session session : sessions) {
			if (session.isOpen() == false)
				removeSession(session);
			else
				session.getAsyncRemote().sendText(message);
		}
	}

	public String toString() {
		return this.name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Channel other = (Channel) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
