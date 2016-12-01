package fr.enssat.regnaultnantel.geoquest.model;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import fr.enssat.regnaultnantel.geoquest.activities.GeoQuestMainActivity;

import java.util.Observable;

public class Position extends Observable{
    private Location location;

    public Position(GeoQuestMainActivity mainActivity) {
        LocationManager locationManager = (LocationManager) mainActivity.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                System.out.println(location);

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        // Register the listener with the Location Manager to receive location updates
        try{
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }
}
