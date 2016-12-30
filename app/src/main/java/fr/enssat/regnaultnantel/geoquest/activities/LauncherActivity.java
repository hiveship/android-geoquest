package fr.enssat.regnaultnantel.geoquest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import fr.enssat.regnaultnantel.geoquest.R;
import fr.enssat.regnaultnantel.geoquest.model.Itinerary;
import fr.enssat.regnaultnantel.geoquest.model.ItineraryRepository;
import fr.enssat.regnaultnantel.geoquest.utilities.Constants;

/**
 * Entry point of the GeoQuest application.
 */
public class LauncherActivity extends AbstractGeoQuestActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Button startGameButton = (Button) findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        Button pathEditorButton = (Button) findViewById(R.id.editPathButton);
        pathEditorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPathEditor();
            }
        });
    }

    private void startGame() {
        Intent intent = new Intent(this, MapsActivity.class);

        // TODO: Récupérer un nom d'itineraire avec un start activity for result
        String itineraryName = "todo";
        ItineraryRepository repository = new ItineraryRepository(getApplicationContext());
       // repository.getDefaultItinerary();

        repository.save(new Itinerary());


        intent.putExtra(Constants.ITINERARY_INTENT_PARAM, itineraryName);

        startActivity(intent);
    }

    private void startPathEditor() {
        Intent intent = new Intent(this, PathEditorActivity.class);
        startActivity(intent);
    }
}
