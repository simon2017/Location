package cl.trackme.il.services;

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

public interface LocationService {

	@Path("getLocationOf/{userId}")
	@GET
	@Produces("application/json")
	public String getLocationOf(@Context HttpHeaders headers, @PathParam("userId") String profile);

	@Path("notifyLocation")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response notifyLocation(@Context HttpHeaders headers, String data);
}
