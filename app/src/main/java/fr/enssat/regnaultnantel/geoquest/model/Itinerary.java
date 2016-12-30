package fr.enssat.regnaultnantel.geoquest.model;

import android.util.Log;
import fr.enssat.regnaultnantel.geoquest.exceptions.ItineraryCompleteException;

import java.util.ArrayList;
import java.util.List;

public class Itinerary {
    private static final String TAG = Itinerary.class.getCanonicalName();

    private List<Beacon> beacons;
    private int step = 0;

    public Itinerary() {
        beacons = new ArrayList<>();
    }

    public Beacon getNextStep() throws ItineraryCompleteException {
        if (step <= beacons.size()) {
            Beacon next = beacons.get(step);
            step++;
            Log.d(TAG, "step ++");
            return next;
        }
        throw new ItineraryCompleteException();
    }

    public List<Beacon> getBeacons() {
        return beacons;
    }

    public void setBeacons(List<Beacon> beacons) {
        this.beacons = beacons;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
