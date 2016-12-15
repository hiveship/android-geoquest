package fr.enssat.regnaultnantel.geoquest.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import fr.enssat.regnaultnantel.geoquest.R;

public class AddPathStep extends AppCompatActivity {

    private Button saveButton;
    private Button takePictureButton;
    private Button delPictureButton;
    private ImageView imageView;
    private Intent cameraIntent;
    private Bitmap hintPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_path_step);
        hintPicture = null;

        cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        saveButton = (Button)findViewById(R.id.save_new_step);
        imageView = (ImageView)findViewById(R.id.photo_indice);
        takePictureButton = (Button)findViewById(R.id.take_picture);

        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture(v);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBeaconAndQuit();
            }
        });

    }

    public void takePicture(View v){
        startActivityForResult(cameraIntent, 42);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 42 && resultCode != 0) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            hintPicture = photo;
            /**
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

            System.out.println(encodedImage);

            byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            imageView.setImageBitmap(decodedByte);**/
        }
    }

    private void saveBeaconAndQuit(){

    }

}
