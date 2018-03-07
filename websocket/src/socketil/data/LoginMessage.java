package socketil.data;

public class LoginMessage extends BasicMessage {

	private String Channel;

	public LoginMessage() {
		super();
	}

	public String getChannel() {
		return Channel;
	}

	public void setChannel(String channel) {
		Channel = channel;
	}

}
