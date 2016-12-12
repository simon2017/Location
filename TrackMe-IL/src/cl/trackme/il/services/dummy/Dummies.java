package cl.trackme.il.services.dummy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import cl.trackme.data.Location;

public class Dummies {

	public static List<Location> makeNDummyLoc(int n) {
		List<Location> dummies = new ArrayList<Location>();

		for (int i = 0; i < n; i++) {
			dummies.add(makeDummyLocation());
		}

		return dummies;
	}

	public static Location makeDummyLocation() {
		double lat = getRandomDouble(-90d, 90d);
		double lon = getRandomDouble(-180d, 180d);
		double alt = Math.random() * 10 + 5;
		double pres = Math.random() * 10 + 5;
		Date date = Calendar.getInstance().getTime();
		return new Location(lat, lon, alt, pres, date);
	}

	private static Random random = new Random();

	public static double getRandomDouble(double lower, double upper) {
		return ((random.nextDouble() * (upper - lower)) + lower);
	}

}
