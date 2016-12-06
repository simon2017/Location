package cl.trackme.il;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("testservice")	
public class TestServiceRest {

	public TestServiceRest() {
		// TODO Auto-generated constructor stub
	}
	
	@GET
	@Produces("application/xml")
	public Response convertCtoF() {
		 
		JSONObject jsonObject = new JSONObject();
		float celsius;
		celsius =  (40 - 32)*5/9; 
		jsonObject.put("F Value", 40); 
		jsonObject.put("C Value", celsius);
		
		return Response.status(200).entity(jsonObject).build();
	  }

}
