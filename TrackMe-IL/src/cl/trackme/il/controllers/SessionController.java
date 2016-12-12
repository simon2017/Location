package cl.trackme.il.controllers;

public class SessionController {

	public SessionController() {
	}

	public boolean ValidateSession(String sessionId){
		return !(sessionId.isEmpty());
	}
	
}
