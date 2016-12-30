package fr.enssat.regnaultnantel.geoquest.model;

import android.location.Location;

public class ItineraryRepository {

    //TODO
    public static Itinerary load(String itineraryName) {
        Itinerary itinerary = new Itinerary();

        Beacon fake1 = new Beacon();
        fake1.setHintString("la mie caline");
        Location targetLocation = new Location("");
        targetLocation.setLatitude(48.731483);
        targetLocation.setLongitude(-3.460341);
        fake1.setLocation(targetLocation);

        Beacon fake2 = new Beacon();
        fake1.setHintString("leclerc perros");
        Location targetLocation2 = new Location("");
        targetLocation2.setLatitude(48.725448);
        targetLocation2.setLongitude(-3.449291);
        fake2.setLocation(targetLocation2);

        itinerary.getBeacons().add(fake1);
        itinerary.getBeacons().add(fake2);

        return itinerary;
    }

    public static void save(Itinerary itinerary) {
        //TODO
    }
}
