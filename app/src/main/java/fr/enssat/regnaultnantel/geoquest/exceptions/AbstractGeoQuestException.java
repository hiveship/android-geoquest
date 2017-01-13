package fr.enssat.regnaultnantel.geoquest.exceptions;

public class AbstractGeoQuestException extends RuntimeException {

    /**
     * Constructs a new AbstractGeoQuestException with specified detail message.
     */
    public AbstractGeoQuestException(String message) {
        super(message);
    }

    /**
     * Constructs a new AbstractGeoQuestException with specified detail message
     * and nested Throwable.
     */
    public AbstractGeoQuestException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new AbstractGeoQuestException with specified nested Throwable
     * and default message.
     */
    public AbstractGeoQuestException(Throwable cause) {
        super(cause);
    }
}
