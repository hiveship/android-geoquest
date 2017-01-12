package fr.enssat.regnaultnantel.geoquest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import fr.enssat.regnaultnantel.geoquest.R;
import fr.enssat.regnaultnantel.geoquest.model.Beacon;
import fr.enssat.regnaultnantel.geoquest.model.BeaconAdapter;
import fr.enssat.regnaultnantel.geoquest.model.Itinerary;

@Deprecated
public class PathEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test); //TODO: Rename

        ListView listView = (ListView) findViewById(R.id.EditorBeaconList);

        Itinerary i = new Itinerary();
        Beacon beacon = new Beacon();
        beacon.setHintString("indice 1");
        beacon.setName("Name 1");
        i.getBeacons().add(beacon);

        listView.setAdapter(new BeaconAdapter(this, i));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStep(view);
            }
        });
    }

    public void addStep(View view) {
        Intent intent = new Intent(this, AddPathStep.class);
        startActivity(intent);
    }
}
