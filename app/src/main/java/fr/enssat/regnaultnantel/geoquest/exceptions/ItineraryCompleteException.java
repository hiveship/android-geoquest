package fr.enssat.regnaultnantel.geoquest.exceptions;


public class ItineraryCompleteException extends AbstractGeoQuestException {

    public ItineraryCompleteException() {
        super("The current itinerary is completed.");
    }

}
