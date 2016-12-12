package cl.trackme.data;

import java.util.List;

public class UserInfo {
	private String profile;
	private String nickname;
	private String email;
	
	private List<Location> lastLocations;

	/**
	 * @param profile
	 * @param nickname
	 * @param email
	 * @param lastLocations
	 */
	public UserInfo(String profile, String nickname, String email, List<Location> lastLocations) {
		super();
		this.profile = profile;
		this.nickname = nickname;
		this.email = email;
		this.lastLocations = lastLocations;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Location> getLastLocations() {
		return lastLocations;
	}

	public void setLastLocations(List<Location> lastLocations) {
		this.lastLocations = lastLocations;
	}
	
		
}
