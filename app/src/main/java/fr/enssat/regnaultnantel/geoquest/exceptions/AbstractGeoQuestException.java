package fr.enssat.regnaultnantel.geoquest.exceptions;

public class AbstractGeoQuestException extends RuntimeException {

    public AbstractGeoQuestException(String message) {
        super(message);
    }

    public AbstractGeoQuestException(Throwable cause) {
        super(cause);
    }

    public AbstractGeoQuestException(String message, Throwable cause) {
        super(message, cause);
    }
}
