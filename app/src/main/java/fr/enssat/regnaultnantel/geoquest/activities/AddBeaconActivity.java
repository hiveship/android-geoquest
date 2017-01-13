package fr.enssat.regnaultnantel.geoquest.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import fr.enssat.regnaultnantel.geoquest.R;
import fr.enssat.regnaultnantel.geoquest.model.Beacon;
import fr.enssat.regnaultnantel.geoquest.model.Itinerary;
import fr.enssat.regnaultnantel.geoquest.model.ItineraryRepository;
import fr.enssat.regnaultnantel.geoquest.utilities.Constants;
import fr.enssat.regnaultnantel.geoquest.utilities.GlobalUtils;

public class AddBeaconActivity extends AbstractGeoQuestActivity {

    private static final int REQUEST_CODE_CAMERA = 1;

    private Button mSaveButton;
    private ImageView mHintImageWidget;
    private EditText mHintStringWidget;
    private EditText mLongitudeWidget;
    private EditText mLatitudeWidget;

    private ItineraryRepository mItineraryRepository;
    private Itinerary mItinerary;
    private Beacon mNewBeacon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beacon);
        mItineraryRepository = new ItineraryRepository(this);
        String itineraryName = getIntent().getExtras().getString(Constants.ITINERARY_INTENT_PARAM);
        mItinerary = mItineraryRepository.load(itineraryName);

        mSaveButton = (Button) findViewById(R.id.beacon_save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Récupérer valeurs et ajouter beacon
            }
        });

        mHintImageWidget = (ImageView) findViewById(R.id.beacon_take_hint_image);
        mHintImageWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });

        mHintStringWidget = (EditText) findViewById(R.id.field_beacon_hint_string);
        mLongitudeWidget = (EditText) findViewById(R.id.field_beacon_longitude);
        mLatitudeWidget = (EditText) findViewById(R.id.field_beacon_latitude);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult with request code = " + requestCode);
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            mHintImageWidget.setImageBitmap(photo);
            mNewBeacon.setHintImage(GlobalUtils.bitmapToBase64String(photo));
        }
    }


    //        //TODO: Pour vérifier que les champs sont renseignés
    //        if (editText.getText().toString().trim().equalsIgnoreCase("")) {
    //            editText.setError("This field can not be blank");
    //        }

    //    private Button mTakePictureButton;
    //    private Button mDelPictureButton;
    //    private ImageView mHintImageWidget;
    //    private Intent mCameraIntent;
    //    private Bitmap mHintPicture;
    //
    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_add_path_step);
    //        mHintPicture = null;
    //
    //        mCameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
    //        mSaveButton = (Button) findViewById(R.id.save_new_step);
    //        mHintImageWidget = (ImageView) findViewById(R.id.photo_indice);
    //        mTakePictureButton = (Button) findViewById(R.id.take_picture);
    //
    //        mTakePictureButton.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View v) {
    //                takePicture(v);
    //            }
    //        });
    //
    //        mSaveButton.setOnClickListener(new View.OnClickListener() {
    //            @Override
    //            public void onClick(View v) {
    //                saveBeaconAndQuit();
    //            }
    //        });
    //
    //    }
    //
    //
    //    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    //        if (requestCode == 42 && resultCode != 0) {
    //            Bitmap photo = (Bitmap) data.getExtras().get("data");
    //            mHintPicture = photo;
    //            /**
    //             ByteArrayOutputStream baos = new ByteArrayOutputStream();
    //             photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
    //             byte[] b = baos.toByteArray();
    //             String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
    //
    //             System.out.println(encodedImage);
    //
    //             byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
    //             Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    //
    //             mHintImageWidget.setImageBitmap(decodedByte);**/
    //        }
    //    }
    //
    //    private void saveBeaconAndQuit() {
    //
    //    }

}
