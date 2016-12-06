package cl.sgutierc.trackme.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;

import lib.data.channel.DataDispatcher;
import lib.data.lib.data.handler.DataAction;

/**
 * Created by sgutierc on 15-11-2016.
 */

public class LocationProvider implements LocationListener {

    private LocationManager locationManager = null;
    private Activity activity = null;
    private long minTime;
    private float minDistance;

    public LocationProvider(Activity activity, long minTime, float minDistance) {
        this.activity = activity;
        this.minTime = minTime;
        this.minDistance = minDistance;
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
    }

    public LocationProvider(Activity activity) {
        this(activity, 5000, 10);
    }

    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    public void startAutoUpdates() {
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);

        } catch (SecurityException e) {
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        DataDispatcher.getInstance().spread(new DataAction(location, DataAction.Trigger.UPDATE));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
