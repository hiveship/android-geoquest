package fr.enssat.regnaultnantel.geoquest.exceptions;


public class ItineraryCompleteException extends AbstractGeoQuestCheckedException {

    public ItineraryCompleteException() {
        super("The current itinerary is completed.");
    }

}
