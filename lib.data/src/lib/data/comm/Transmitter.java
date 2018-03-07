package lib.data.comm;

import lib.data.comm.messages.Message;

/**
 * 
 * @author sgutierc
 *
 * @param <X>
 */
public interface Transmitter<X extends Message<?, ?>> {

	void send(X message);

}
