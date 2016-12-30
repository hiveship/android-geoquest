package fr.enssat.regnaultnantel.geoquest.exceptions;

public class JSONProcessingException extends AbstractGeoQuestUncheckedException {

    /**
     * Constructs a new JSONProcessingException with specified detail message.
     */
    public JSONProcessingException(String message) {
        super(message);
    }

    /**
     * Constructs a new JSONProcessingException with specified detail message
     * and nested Throwable.
     */
    public JSONProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new JSONProcessingException with specified nested Throwable
     * and default message.
     */
    public JSONProcessingException(Throwable cause) {
        super(cause);
    }
}
