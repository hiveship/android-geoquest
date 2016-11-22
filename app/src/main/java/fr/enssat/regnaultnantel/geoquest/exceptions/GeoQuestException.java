package fr.enssat.regnaultnantel.geoquest.exceptions;

public class GeoQuestException extends RuntimeException {

    public GeoQuestException() {

    }

    public GeoQuestException(String message) {
        super(message);
    }

    public GeoQuestException(Throwable cause) {
        super(cause);
    }

    public GeoQuestException(String message, Throwable cause) {
        super(message, cause);
    }
}
