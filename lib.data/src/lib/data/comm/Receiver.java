package lib.data.comm;

import lib.data.comm.messages.Message;

/**
 * 
 * @author sgutierc
 *
 * @param <X>
 */
public interface Receiver <X extends Message<?, ?>>{

	public void receive(X message);
}
