package cl.trackme.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseMessage {

	private int option;

	@JsonIgnore
	private String fullMessage;

	@JsonIgnore
	private Object sessionObj;

	public Object getSessionObj() {
		return sessionObj;
	}

	public void setSessionObj(Object sessionObj) {
		this.sessionObj = sessionObj;
	}

	public BaseMessage() {

	}

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}

	public String getFullMessage() {
		return fullMessage;
	}

	public void setFullMessage(String fullMessage) {
		this.fullMessage = fullMessage;
	}

}
