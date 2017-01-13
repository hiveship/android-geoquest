package fr.enssat.regnaultnantel.geoquest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import fr.enssat.regnaultnantel.geoquest.R;
import fr.enssat.regnaultnantel.geoquest.model.BeaconAdapter;
import fr.enssat.regnaultnantel.geoquest.model.Itinerary;
import fr.enssat.regnaultnantel.geoquest.model.ItineraryRepository;
import fr.enssat.regnaultnantel.geoquest.utilities.Constants;

public class BeaconsListActivity extends AbstractGeoQuestActivity {

    private static final int REQUEST_CODE_NEW_BEACON = 1;

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
        initializeCreateButton();
    }

    private void initializeListView() {
        String itineraryName = getIntent().getExtras().getString(Constants.ITINERARY_INTENT_PARAM);
        mItinerary = mItineraryRepository.load(itineraryName);
        mListView.setAdapter(new BeaconAdapter(this, mItinerary));
    }

    private void initializeCreateButton() {
        mAddButton = (FloatingActionButton) findViewById(R.id.add_beacon_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BeaconsListActivity.this, AddBeaconActivity.class);
                intent.putExtra(Constants.ITINERARY_INTENT_PARAM, mItinerary.getName());
                startActivityForResult(intent, REQUEST_CODE_NEW_BEACON);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult with request code = " + requestCode);
        if (resultCode == RESULT_OK) {
            //TODO: Refresh list view
        }
    }

}
