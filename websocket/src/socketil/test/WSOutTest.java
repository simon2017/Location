/**
 * 
 */
package socketil.test;

import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;

import lib.data.comm.messages.OutMessage;
import lib.data.dispatcher.Listener;

/**
 * @author sgutierc
 *
 */
public class WSOutTest implements Listener {

	/**
	 * 
	 */
	public WSOutTest() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see lib.data.dispatcher.Listener#receive(java.lang.Object)
	 */
	@Override
	public void receive(Object object) {
		if (object instanceof OutMessage) {
			OutMessage outm = (OutMessage) object;
			Session destination = outm.getDestination();
			try {
				synchronized (destination) {
					if (destination.isOpen()) {
						Basic channel = destination.getBasicRemote();
						channel.sendText(outm.getMessage().toString());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
