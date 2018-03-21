/**
 * 
 */
package lib.data.user;

/**
 * @author sgutierc
 *
 */
public abstract class Identifier {

	/**
	 * 
	 */
	public Identifier() {

	}

	@Override
	public abstract int hashCode();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public abstract boolean equals(Object obj);

}
