package fr.enssat.regnaultnantel.geoquest.model;

import android.util.Log;
import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.enssat.regnaultnantel.geoquest.exceptions.ItineraryCompleteException;

import java.util.ArrayList;
import java.util.List;

public class Itinerary {
    private static final String TAG = Itinerary.class.getCanonicalName();

    private String mName;
    private List<Beacon> mBeacons;
    @JsonIgnore
    private int mStep = 0;

    public Itinerary() {
        mBeacons = new ArrayList<>();
    }

    @JsonIgnore
    public Beacon getNextStep() {
        if (mStep < mBeacons.size()) {
            Beacon next = mBeacons.get(mStep);
            mStep++;
            Log.d(TAG, "mStep ++");
            return next;
        }
        throw new ItineraryCompleteException();
    }

    public List<Beacon> getBeacons() {
        return mBeacons;
    }

    public void setBeacons(List<Beacon> beacons) {
        this.mBeacons = beacons;
    }

    public int getStep() {
        return mStep;
    }

    public void setStep(int step) {
        this.mStep = step;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

}
