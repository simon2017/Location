package cl.trackme.data;

import java.util.Date;

public class Location {

	//-90 0 90
	private double latitude_deg;
	//-180 0 180
	private double longitude_deg;
	private double altitude_deg;
	private double precision_mts;
	private Date locationDate;
	
	
	public Location(){}
	/**
	 * @param latitude_deg
	 * @param longitude_deg
	 * @param altitude_deg
	 * @param locationDate
	 */
	public Location(double latitude_deg, double longitude_deg, double altitude_deg,double precision_mts, Date locationDate) {
		super();
		this.latitude_deg = latitude_deg;
		this.longitude_deg = longitude_deg;
		this.altitude_deg = altitude_deg;
		this.locationDate = locationDate;
		this.precision_mts=precision_mts;
	}
	public double getLatitude_deg() {
		return latitude_deg;
	}
	public void setLatitude_deg(double latitude_deg) {
		this.latitude_deg = latitude_deg;
	}
	public double getLongitude_deg() {
		return longitude_deg;
	}
	public void setLongitude_deg(double longitude_deg) {
		this.longitude_deg = longitude_deg;
	}
	public double getAltitude_deg() {
		return altitude_deg;
	}
	public void setAltitude_deg(double altitude_deg) {
		this.altitude_deg = altitude_deg;
	}
	public Date getLocationDate() {
		return locationDate;
	}
	public void setLocationDate(Date locationDate) {
		this.locationDate = locationDate;
	}
	public double getPrecision_mts() {
		return precision_mts;
	}
	public void setPrecision_mts(double precision_mts) {
		this.precision_mts = precision_mts;
	}
	
	
	
}
