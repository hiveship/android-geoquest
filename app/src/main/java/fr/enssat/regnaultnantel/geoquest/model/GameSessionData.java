package fr.enssat.regnaultnantel.geoquest.model;

import fr.enssat.regnaultnantel.geoquest.exceptions.ItineraryCompleteException;

public class GameSessionData {

    private boolean finish;
    private Beacon currentBeacon;
    private Itinerary itinerary;

    public GameSessionData(Itinerary itinerary) {
        this.itinerary = itinerary;
        try {
            this.currentBeacon = itinerary.getNextStep();
        } catch (ItineraryCompleteException e) {
            // Should not happen, just ignore.
            e.printStackTrace();
        }
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public Beacon getCurrentBeacon() {
        return currentBeacon;
    }

    public void setCurrentBeacon(Beacon currentBeacon) {
        this.currentBeacon = currentBeacon;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    public void processBeaconReached() throws ItineraryCompleteException {
        currentBeacon.setReached(true);
        currentBeacon = itinerary.getNextStep(); // throw if game finish
    }
}
