package fr.enssat.regnaultnantel.geoquest.model;


/**
 * A beacon represents a step in the GeoQuest game.
 */
public class Beacon {

    private String mHintString;
    private String mHintImage;
    private Coordinates mCoordinates;

    /**
     * Returns a text information about the current beacon.
     */
    public String getHintString() {
        return mHintString;
    }

    /**
     * Returns a base64 encoded image representing the current beacon.
     */
    public String getHintImage() {
        return mHintImage;
    }

    /**
     * Returns the GPS mCoordinates of the current beacon.
     */
    public Coordinates getCoordinates() {
        return mCoordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.mCoordinates = coordinates;
    }

    public void setHintString(String hintString) {
        this.mHintString = hintString;
    }

    public void setHintImage(String hintImage) {
        this.mHintImage = hintImage;
    }

}
