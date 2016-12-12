package fr.enssat.regnaultnantel.geoquest.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import fr.enssat.regnaultnantel.geoquest.R;
import fr.enssat.regnaultnantel.geoquest.model.Beacon;
import fr.enssat.regnaultnantel.geoquest.model.BeaconAdapter;
import fr.enssat.regnaultnantel.geoquest.model.Itinerary;

public class PathEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_editor);

        ListView lV = (ListView) findViewById(R.id.EditorBeaconList);

        Itinerary i = new Itinerary();
        Beacon beacon = new Beacon();
        beacon.setHintString("indice 1");
        beacon.setName("Name 1");
        i.addBeacon(beacon);

        lV.setAdapter(new BeaconAdapter(this,i));
    }
}
