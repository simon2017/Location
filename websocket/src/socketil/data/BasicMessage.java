package socketil.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BasicMessage {

	private String type;
	//private List<Object> data;

	public BasicMessage() {

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

//	public List<Object> getData() {
//		return data;
//	}
//
//	public void setData(List<Object> data) {
//		this.data = data;
//	}

}
