package fr.enssat.regnaultnantel.geoquest.exceptions;

public class AbstractGeoQuestUncheckedException extends RuntimeException {

    public AbstractGeoQuestUncheckedException(String message) {
        super(message);
    }

    public AbstractGeoQuestUncheckedException(Throwable cause) {
        super(cause);
    }

    public AbstractGeoQuestUncheckedException(String message, Throwable cause) {
        super(message, cause);
    }
}
