/**
 * 
 */
package lib.data.user;

/**
 * @author sgutierc
 *
 */
public abstract class User<T extends Identifier> {
	private Identifier userID;
	private String name;

	/**
	 * 
	 * @param userId
	 */
	public User(String name, Identifier userId) {
		this.userID = userId;
	}

	/**
	 * 
	 * @param userId
	 */
	public User(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the userID
	 */
	public Identifier getUserID() {
		return userID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		return getUserID().hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return getUserID().equals(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("User (%s)", getName());
	}

}
