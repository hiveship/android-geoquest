package fr.enssat.regnaultnantel.geoquest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import fr.enssat.regnaultnantel.geoquest.R;
import fr.enssat.regnaultnantel.geoquest.model.ItineraryRepository;
import fr.enssat.regnaultnantel.geoquest.utilities.Constants;

/**
 * Entry point of the GeoQuest application.
 */
public class LauncherActivity extends AbstractGeoQuestActivity {

    private static final int REQUEST_CODE_START_GET_ITINERARY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        ItineraryRepository repository = new ItineraryRepository(this);
        repository.removeAll();

        Button gameButton = (Button) findViewById(R.id.start_game_button);
        gameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askForItinerary();
            }
        });

        Button editorButton = (Button) findViewById(R.id.edit_itinerary_button);
        editorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startItineraryEditor();
            }
        });

        Log.d(TAG, "GeoQuest Application successfully started !");
    }

    private void askForItinerary() {
        Intent intent = new Intent(this, ItineraryListActivity.class);
        intent.putExtra(Constants.ITINERARY_LIST_ACTION_PARAM, Constants.ITINERARY_LIST_ACTION_GAME);
        startActivityForResult(intent, REQUEST_CODE_START_GET_ITINERARY);
    }

    private void startGame(String itinerary) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(Constants.ITINERARY_INTENT_PARAM, itinerary);
        startActivity(intent);
    }

    private void startItineraryEditor() {
        Intent intent = new Intent(this, ItineraryListActivity.class);
        intent.putExtra(Constants.ITINERARY_LIST_ACTION_PARAM, Constants.ITINERARY_LIST_ACTION_EDITOR);
        startActivityForResult(intent, REQUEST_CODE_START_GET_ITINERARY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult with request code = " + requestCode);
        if (requestCode == REQUEST_CODE_START_GET_ITINERARY && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            String itineraryName = (String) extras.get(Constants.ITINERARY_INTENT_PARAM);
            Log.d(TAG, "Going to start game for itinerary name = " + itineraryName);
            startGame(itineraryName);
        }
    }
}
