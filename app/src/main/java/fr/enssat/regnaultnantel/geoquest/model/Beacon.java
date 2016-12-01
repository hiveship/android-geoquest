package fr.enssat.regnaultnantel.geoquest.model;

import android.location.Location;

public class Beacon {

    private String name;
    private String hintString;
    private String hintImage;
    private Location location;
    private boolean reached = false;

    public Beacon(String name, String hintString,Location location){
        this(name,hintString,null,location);
    }

    public Beacon(String name, String hintString, String hintImage, Location location) {
        this.name = name;
        this.hintString = hintString;
        this.hintImage = hintImage;
        this.location = location;
        this.reached = false;
    }

    public String getName() {
        return name;
    }

    public String getHintString() {
        return hintString;
    }

    public String getHintImage() {
        return hintImage;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isReached() {
        return reached;
    }
}
