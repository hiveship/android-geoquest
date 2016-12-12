package fr.enssat.regnaultnantel.geoquest.model;

import android.location.Location;

/**
 * A beacon represents a step in the GeoQuest game.
 */
public class Beacon {

    private String name;
    private String hintString;
    private String hintImage;
    private Location location;
    private boolean reached = false;

    public String getName() {
        return name;
    }

    /**
     * Returns a text information about the current beacon.
     */
    public String getHintString() {
        return hintString;
    }

    /**
     * Returns a base64 encoded image representing the current beacon.
     */
    public String getHintImage() {
        return hintImage;
    }

    /**
     * Returns the GPS coordinates of the current beacon.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Indicates wheter the player has reached or not the current beacon.
     */
    public boolean isReached() {
        return reached;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHintString(String hintString) {
        this.hintString = hintString;
    }

    public void setHintImage(String hintImage) {
        this.hintImage = hintImage;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setReached(boolean reached) {
        this.reached = reached;
    }
}
