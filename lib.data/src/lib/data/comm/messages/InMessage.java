/**
 * 
 */
package lib.data.comm.messages;

import javax.json.JsonObject;
import javax.websocket.Session;

/**
 * @author sgutierc
 *
 */
public class InMessage extends Message<JsonObject, Session> {

	/**
	 * 
	 * @param origin
	 * @param message
	 */
	public InMessage(Session origin, JsonObject message) {
		super(message, origin);
	}

	/**
	 * Returns the origin for the message
	 * 
	 * @return
	 */
	public Session getOrigin() {
		return this.getSourceDestination();
	}

	public String toString() {
		return String.format("M: [%s] O: [%s]", getMessage(),getOrigin().getId());
	}
}
