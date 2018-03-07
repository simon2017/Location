/**
 * 
 */
package lib.data.dispatcher;

import lib.data.comm.messages.Message;

/**
 * @author sgutierc
 *
 */
public interface Dispatcher  {

	public void addListener(Listener listener,Class<?> interest);
	public void removeListener(Listener listener,Class<?> interest);
	public void dispatch(Message<?,?> message);
}
