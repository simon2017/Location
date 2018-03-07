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
public class OutMessage extends Message<JsonObject, Session> {

	/**
	 * 
	 * @param destination
	 * @param message
	 */
	public OutMessage(Session destination, JsonObject message) {
		super(message, destination);
	}
	
	/**
	 * Returns the destination for the message 
	 * @return
	 */
	public Session getDestination() {
		return this.getSourceDestination();
	}

}
