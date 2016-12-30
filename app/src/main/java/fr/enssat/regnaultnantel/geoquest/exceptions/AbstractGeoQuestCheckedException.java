package fr.enssat.regnaultnantel.geoquest.exceptions;

public abstract class AbstractGeoQuestCheckedException extends Exception {

    public AbstractGeoQuestCheckedException(String message) {
        super(message);
    }

    public AbstractGeoQuestCheckedException(Throwable cause) {
        super(cause);
    }

    public AbstractGeoQuestCheckedException(String message, Throwable cause) {
        super(message, cause);
    }
}
