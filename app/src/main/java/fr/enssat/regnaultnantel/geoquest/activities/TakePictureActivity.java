package fr.enssat.regnaultnantel.geoquest.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import fr.enssat.regnaultnantel.geoquest.R;

import java.io.ByteArrayOutputStream;

public class TakePictureActivity extends AppCompatActivity {

    private Button bt;
    private ImageView imageView;
    private Intent cameraIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);

        cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        bt = (Button)findViewById(R.id.button);
        imageView = (ImageView)findViewById(R.id.imageView);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture(v);
            }
        });
    }

    public void takePicture(View v){
        startActivityForResult(cameraIntent, 42);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 42) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();
            String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

            System.out.println(encodedImage);

            byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            imageView.setImageBitmap(decodedByte);
        }
    }
}
