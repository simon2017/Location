package cl.sgutierc.trackme.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.security.acl.Permission;
import java.util.Map;

import cl.sgutierc.trackme.R;
import cl.sgutierc.trackme.view.ListableFragment;

/**
 * Created by sgutierc on 15-11-2016.
 */

public class MapActivity extends ListableFragment implements OnMapReadyCallback {

/*
        super(title, null, title);
        MapFragment mapFragment = (MapFragment) activity.getFragmentManager().findFragmentById(R.id.mapFragment);
        if(mapFragment==null)
            mapFragment=(MapFragment)activity.getFragmentManager().findFragmentByTag("googleMap");
        mapFragment.getMapAsync(this);
    }
*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_map, container, false);
        //inicializo mapa y reemplazo el elemento en la vista
        {
            MapFragment mapFragment = new MapFragment();
            mapFragment.getMapAsync(MapActivity.this);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.googleMap, mapFragment)
                    .commit();
        }
            return view;
    }

    @Override
    public String getListableText() {
        return "Mapa";
    }

    @Override
    public String getTitle() {
        return "Mapa";
    }

    private GoogleMap map;

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;

        MapsInitializer.initialize(getActivity());
        if (checkPermission())
            map.setMyLocationEnabled(true);
        else askPermission();

        LatLng sydney = new LatLng(-33.867, 151.206);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        map.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(sydney));

    }


    // Check for permission to access Location
    private boolean checkPermission() {
        // Ask for permission if it wasn't granted yet
        return (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED);
    }

    // Asks for permission
    private void askPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 10);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 10: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted
                    if (checkPermission())
                        map.setMyLocationEnabled(true);

                } else {
                    // Permission denied

                }
                break;
            }
        }
    }
}
