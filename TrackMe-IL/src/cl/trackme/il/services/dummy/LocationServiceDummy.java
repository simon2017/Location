package cl.trackme.il.services.dummy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cl.trackme.data.Errors;
import cl.trackme.data.GenericResponse;
import cl.trackme.data.Location;
import cl.trackme.il.controllers.SessionController;
import cl.trackme.il.services.LocationService;

import com.fasterxml.jackson.databind.ObjectMapper;

@Path("Dummy/LocationService")
public class LocationServiceDummy implements LocationService {

	private SessionController sessionController;

	public LocationServiceDummy() {
		sessionController = new SessionController();
	}

	@Path("getLocationOf/{userId}")
	@GET
	@Produces("application/json")
	@Override
	public String getLocationOf(@Context HttpHeaders headers, @PathParam("userId") String profile) {

		GenericResponse response = new GenericResponse();

		String sessionId = "";
		boolean validaParam = true;
		try {
			sessionId = headers.getRequestHeader("sessionId").get(0);
		} catch (Exception e) {
			Errors error = new Errors(100, "Invalid parameter", "Invalid sessionID provided");
			response.setError(error);
			validaParam = false;
		}

		if (validaParam) {
			// validar sesion
			if (sessionController.ValidateSession(sessionId) == false) {
				// sesion invalida
				Errors error = new Errors(101, "Invalid session", "Invalid sessionID provided");
				response.setError(error);
			} else {
				Location userLoc = Dummies.makeDummyLocation();
				response.setData(userLoc);
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		String result = "";
		try {
			result = mapper.writeValueAsString(response);
		} catch (Exception e) {
			result += "\n " + e.toString();
			e.printStackTrace();
		}

		return result;

	}

	@Path("notifyLocation")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response notifyLocation(@Context HttpHeaders headers, String data) {

		String userId = headers.getRequestHeader("userId").get(0);

		ObjectMapper mapper = new ObjectMapper();
		Location location = null;
		String error = "";
		try {
			location = mapper.readValue(data, Location.class);
		} catch (Exception e) {
			error += "\n " + e.toString();
			e.printStackTrace();
		}
		String result = String.format("[%s -> %s | %s -> %s] %s", "Data", data, "userId", userId, error);
		return Response.status(201).entity(result).build();
	}

}
