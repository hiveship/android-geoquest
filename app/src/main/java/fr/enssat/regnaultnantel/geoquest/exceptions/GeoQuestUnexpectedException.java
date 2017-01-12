package fr.enssat.regnaultnantel.geoquest.exceptions;


public class GeoQuestUnexpectedException extends AbstractGeoQuestException {
    public GeoQuestUnexpectedException(String message) {
        super(message);
    }

    public GeoQuestUnexpectedException(Throwable cause) {
        super(cause);
    }

    public GeoQuestUnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }
}
