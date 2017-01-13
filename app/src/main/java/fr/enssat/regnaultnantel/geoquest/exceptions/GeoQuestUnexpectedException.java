package fr.enssat.regnaultnantel.geoquest.exceptions;


public class GeoQuestUnexpectedException extends AbstractGeoQuestException {

    /**
     * Constructs a new GeoQuestUnexpectedException with specified detail message.
     */
    public GeoQuestUnexpectedException(String message) {
        super(message);
    }

    /**
     * Constructs a new GeoQuestUnexpectedException with specified detail message
     * and nested Throwable.
     */
    public GeoQuestUnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new GeoQuestUnexpectedException with specified nested Throwable
     * and default message.
     */
    public GeoQuestUnexpectedException(Throwable cause) {
        super(cause);
    }
}