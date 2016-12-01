package fr.enssat.regnaultnantel.geoquest.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        i.addBeacon(new Beacon("test","lol",null));

        lV.setAdapter(new BeaconAdapter(this,i));
    }
}
