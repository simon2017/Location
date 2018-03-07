/**
 * 
 */
package lib.data.comm.messages;

/**
 * 
 * @author sgutierc
 *
 * @param <T> the message
 * @param <U> the destination/origin of message
 */
public abstract class Message<T,U> {
	protected T message;
	protected U destOrig;
	
	public Message(T message, U destOrig) {
		this.message=message;
		this.destOrig=destOrig;
	}
	
	public T getMessage() {
		return this.message;
	}
	
	protected U getSourceDestination() {
		return this.destOrig;
	}
	
}
