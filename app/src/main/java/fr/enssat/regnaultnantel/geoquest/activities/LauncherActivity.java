package fr.enssat.regnaultnantel.geoquest.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.enssat.regnaultnantel.geoquest.R;

/**
 * Entry point of the GeoQuest application.
 */
public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Button startGameButton = (Button)findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        Button pathEditorButton = (Button)findViewById(R.id.editPathButton);
        pathEditorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPathEditor();
            }
        });
    }

    private void startGame() {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    private void startPathEditor() {
        Intent intent = new Intent(this, PathEditorActivity.class);
        startActivity(intent);
    }
}
