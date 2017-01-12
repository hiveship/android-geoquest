package fr.enssat.regnaultnantel.geoquest.model;

import android.location.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Coordinates {

    private double mLongitude;
    private double mLatitude;

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        this.mLongitude = longitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    @JsonIgnore
    public Location toLocation() {
        Location target = new Location(""); //provider name is unecessary
        target.setLatitude(getLatitude());
        target.setLongitude(getLongitude());
        return target;
    }
}
