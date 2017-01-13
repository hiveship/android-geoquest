package fr.enssat.regnaultnantel.geoquest.exceptions;


public class ItineraryCompleteException extends AbstractGeoQuestException {

    /**
     * Constructs a new ItineraryCompleteException with default detail message.
     */
    public ItineraryCompleteException() {
        super("Itinerary is fully completed.");
    }

}
