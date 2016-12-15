package fr.enssat.regnaultnantel.geoquest.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.enssat.regnaultnantel.geoquest.R;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        Button start = (Button)findViewById(R.id.startGameButton);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame(v);
            }
        });

        Button edit = (Button)findViewById(R.id.editPathButton);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEdition(v);
            }
        });
    }

    public void startGame(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void startEdition(View view) {
        Intent intent = new Intent(this, PathEditorActivity.class);
        startActivity(intent);
    }
}
