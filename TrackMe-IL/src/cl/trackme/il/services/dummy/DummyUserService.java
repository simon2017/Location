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

import cl.trackme.data.Location;
import cl.trackme.data.UserInfo;

import com.fasterxml.jackson.databind.ObjectMapper;

@Path("Dummy/UserService")
public class DummyUserService {

	public DummyUserService() {
	}

	@Path("info/{profile}")
	@GET
	@Produces("application/json")
	public String getInfo(@PathParam("profile") String profile) {
		String result = "ERROR";
		UserInfo userInfo = new UserInfo(profile, "nickname", "email", Dummies.makeNDummyLoc(20));
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = mapper.writeValueAsString(userInfo);
		} catch (Exception e) {
			result += "\n " + e.toString();
			e.printStackTrace();
		}
		return result;

	}

	@Path("reportLocation")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response reportLocation(@Context HttpHeaders headers, String data) {

		String userId ="";
		ObjectMapper mapper = new ObjectMapper();
		Location location = null;
		String error = "";
		
		try{
			userId=headers.getRequestHeader("userId").get(0);
			location = mapper.readValue(data, Location.class);
			
		} catch (Exception e) {
			error += "\n " + e.toString();
			e.printStackTrace();
		}
		String result = String.format("[%s -> %s | %s -> %s] %s", "Data", data, "userId", userId, error);
		return Response.status(201).entity(result).build();
	}

}
