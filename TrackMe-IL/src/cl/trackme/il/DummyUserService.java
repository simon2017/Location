package cl.trackme.il;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.fasterxml.jackson.databind.ObjectMapper;

@Path("DummyUserService")
public class DummyUserService {

	public DummyUserService() {
	}

	@Path("{profile}")
	@GET
	@Produces("application/json")
	public String getInfo(@PathParam("profile") String profile) {
		String result = "ERROR";
		UserInfo userInfo = new UserInfo(profile, "nickname", "email", makeDummyLocations());
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = mapper.writeValueAsString(userInfo);
		} catch (Exception e) {
			result += "\n " + e.toString();
			e.printStackTrace();
		}
		return result;

	}

	private List<Location> makeDummyLocations() {
		List<Location> dummies = new ArrayList<Location>();

		for (int i = 0; i < 10; i++) {
			double lat = getRandomDouble(-90d, 90d);
			double lon = getRandomDouble(-180d, 180d);
			double alt = Math.random() * 10 + 5;
			double pres = Math.random() * 10 + 5;
			Date date = Calendar.getInstance().getTime();
			dummies.add(new Location(lat, lon, alt, pres, date));
		}

		return dummies;
	}

	private Random random = new Random();

	private double getRandomDouble(double lower, double upper) {
		return ((random.nextDouble() * (upper - lower)) + lower);
	}

}
