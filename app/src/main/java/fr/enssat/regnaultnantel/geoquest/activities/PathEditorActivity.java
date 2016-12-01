package fr.enssat.regnaultnantel.geoquest.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import fr.enssat.regnaultnantel.geoquest.R;
import fr.enssat.regnaultnantel.geoquest.model.BeaconAdapter;

public class PathEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_editor);

        ListView lV = (ListView) findViewById(R.id.EditorBeaconList);
/*
        try {
            lV.setAdapter(new BeaconAdapter(this, ));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
