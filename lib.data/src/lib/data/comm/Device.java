/**
 * 
 */
package lib.data.comm;

import javax.websocket.Session;

/**
 * @author sgutierc
 *
 */
public class Device {
	private Session session;

	public Device(Session session) {
		this.session = session;
	}

	public Session getSession() {
		return this.session;
	}
}
