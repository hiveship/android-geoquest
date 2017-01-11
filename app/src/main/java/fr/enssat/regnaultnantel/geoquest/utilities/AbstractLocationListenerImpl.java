package fr.enssat.regnaultnantel.geoquest.utilities;

import android.location.LocationListener;
import android.os.Bundle;

public abstract class AbstractLocationListenerImpl implements LocationListener {

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Don't need this event, just ignore it
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Don't need this event, just ignore it
    }

    @Override
    public void onProviderDisabled(String provider) {
        // Don't need this event, just ignore it
    }
}
