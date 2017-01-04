package fr.enssat.regnaultnantel.geoquest.model;

import android.location.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Coordinates {

    private double longitude;
    private double latitude;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @JsonIgnore
    public Location toLocation() {
        Location target = new Location(""); //provider name is unecessary
        target.setLatitude(getLatitude());
        target.setLongitude(getLongitude());
        return target;
    }
}
