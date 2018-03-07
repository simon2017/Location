package socketil.data;

public class ChatMessage extends BasicMessage {

	private String channel;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ChatMessage() {
		super();
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String toString() {
		return String.format("%s (%s)", getChannel(), getMessage());
	}

}
