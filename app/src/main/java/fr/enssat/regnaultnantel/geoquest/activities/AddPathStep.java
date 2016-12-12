package fr.enssat.regnaultnantel.geoquest.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import fr.enssat.regnaultnantel.geoquest.R;

public class AddPathStep extends AppCompatActivity {

    private Button bt;
    private ImageView imageView;
    private Intent cameraIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_path_step);

        cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        bt = (Button)findViewById(R.id.button);
        imageView = (ImageView)findViewById(R.id.photo_indice);

    }
}
