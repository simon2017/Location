package cl.trackme.data;

public class GenericResponse {
	private Errors[] error;
	private Object data;

	public GenericResponse() {

	}

	public GenericResponse(Errors[] error, Object data) {
		this.error = error;
		this.data = data;
	}

	public Errors[] getErrors() {
		return error;
	}

	public void setErrors(Errors[] error) {
		this.error = error;
	}

	public void setError(Errors error) {
		this.error = new Errors[] { error };

	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
