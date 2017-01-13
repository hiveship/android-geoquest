package fr.enssat.regnaultnantel.geoquest.model;

public class GameSessionData {

    private boolean mFinish;
    private Beacon mCurrentBeacon;
    private Itinerary mItinerary;

    public GameSessionData(Itinerary itinerary) {
        this.mItinerary = itinerary;
        this.mCurrentBeacon = itinerary.getNextStep();
    }

    public boolean isFinish() {
        return mFinish;
    }

    public void setFinish(boolean finish) {
        this.mFinish = finish;
    }

    public Beacon getCurrentBeacon() {
        return mCurrentBeacon;
    }

    public void setCurrentBeacon(Beacon currentBeacon) {
        this.mCurrentBeacon = currentBeacon;
    }

    public Itinerary getItinerary() {
        return mItinerary;
    }

    public void setItinerary(Itinerary itinerary) {
        this.mItinerary = itinerary;
    }

    public void processBeaconReached() {
        mCurrentBeacon = mItinerary.getNextStep(); // throw if game is finish
    }
}
