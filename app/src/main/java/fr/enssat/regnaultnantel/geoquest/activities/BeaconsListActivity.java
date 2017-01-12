package fr.enssat.regnaultnantel.geoquest.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.widget.ListView;
import fr.enssat.regnaultnantel.geoquest.R;
import fr.enssat.regnaultnantel.geoquest.model.BeaconAdapter;
import fr.enssat.regnaultnantel.geoquest.model.Itinerary;
import fr.enssat.regnaultnantel.geoquest.model.ItineraryRepository;
import fr.enssat.regnaultnantel.geoquest.utilities.Constants;

public class BeaconsListActivity extends AbstractGeoQuestActivity {

    private ListView mListView;
    private FloatingActionButton mAddButton;
    private ItineraryRepository mItineraryRepository = new ItineraryRepository(this);
    private Itinerary mItinerary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacons_list);
        mListView = (ListView) findViewById(R.id.beacons_list);

        initializeListView();
    }

    private void initializeListView() {
        String itineraryName = getIntent().getExtras().getString(Constants.ITINERARY_INTENT_PARAM);
        mItinerary = mItineraryRepository.load(itineraryName);
        mListView.setAdapter(new BeaconAdapter(this, mItinerary));
    }

}
