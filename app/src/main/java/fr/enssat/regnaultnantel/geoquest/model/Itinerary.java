package fr.enssat.regnaultnantel.geoquest.model;

import java.util.ArrayList;
import java.util.List;

public class Itinerary {

    private List<Beacon> beacons;

    public Itinerary(){
        beacons = new ArrayList<>();
    }

    public void addBeacon(Beacon B){
        beacons.add(B);
    }

    public Beacon getBeacon(int i){
        return beacons.get(i);
    }

    public int size(){
        return beacons.size();
    }
}
