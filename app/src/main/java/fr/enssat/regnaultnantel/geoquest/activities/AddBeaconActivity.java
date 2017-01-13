package fr.enssat.regnaultnantel.geoquest.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import fr.enssat.regnaultnantel.geoquest.R;
import fr.enssat.regnaultnantel.geoquest.model.Beacon;
import fr.enssat.regnaultnantel.geoquest.model.Coordinates;
import fr.enssat.regnaultnantel.geoquest.model.Itinerary;
import fr.enssat.regnaultnantel.geoquest.model.ItineraryRepository;
import fr.enssat.regnaultnantel.geoquest.utilities.AbstractLocationListenerImpl;
import fr.enssat.regnaultnantel.geoquest.utilities.Constants;
import fr.enssat.regnaultnantel.geoquest.utilities.GlobalUtils;

public class AddBeaconActivity extends AbstractGeoQuestActivity {

    private static final int REQUEST_CODE_CAMERA = 1;

    private Button mSaveButton;
    private ImageView mHintImageWidget;
    private EditText mHintStringWidget;
    private EditText mLongitudeWidget;
    private EditText mLatitudeWidget;

    private LocationListener mLocationListener;
    private LocationManager mLocationManager;

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
        mNewBeacon = new Beacon();

        mSaveButton = (Button) findViewById(R.id.beacon_save_button);
        mSaveButton.setEnabled(false);
        mSaveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                double longitude = Double.valueOf(mLongitudeWidget.getText().toString());
                double latitude = Double.valueOf(mLatitudeWidget.getText().toString());
                mNewBeacon.setCoordinates(new Coordinates(longitude, latitude));
                mNewBeacon.setHintString(mHintStringWidget.getText().toString());
                Bitmap bitmap = ((BitmapDrawable) mHintImageWidget.getDrawable()).getBitmap();
                mNewBeacon.setHintImage(GlobalUtils.bitmapToBase64String(bitmap));

                mItinerary.getBeacons().add(mNewBeacon);
                mItineraryRepository.update(mItinerary);
                onBackPressed();
            }
        });

        mHintImageWidget = (ImageView) findViewById(R.id.beacon_take_hint_image);
        mHintImageWidget.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });

        mHintStringWidget = (EditText) findViewById(R.id.field_beacon_hint_string);
        mLongitudeWidget = (EditText) findViewById(R.id.field_beacon_longitude);
        mLatitudeWidget = (EditText) findViewById(R.id.field_beacon_latitude);
        mHintStringWidget.addTextChangedListener(new EmptyTextWatcher());
        mLongitudeWidget.addTextChangedListener(new EmptyTextWatcher());
        mLatitudeWidget.addTextChangedListener(new EmptyTextWatcher());

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new AbstractLocationListenerImpl() {
            @Override
            public void onLocationChanged(Location location) {
                mLongitudeWidget.setText(Double.toString(location.getLongitude()));
                mLatitudeWidget.setText(Double.toString(location.getLatitude()));
            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Don't query for location updates if the activity is not active (battery...)
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(mLocationListener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mLocationListener != null) {
            mLocationManager.requestLocationUpdates(mLocationManager.getBestProvider(new Criteria(), true), 0, 0, mLocationListener);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult with request code = " + requestCode);
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            mHintImageWidget.setImageBitmap(photo);
            //mNewBeacon.setHintImage(GlobalUtils.bitmapToBase64String(photo));
        }
    }

    class EmptyTextWatcher implements TextWatcher {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!"".equals(mLatitudeWidget.getText().toString().trim()) && !"".equals(mLongitudeWidget.getText().toString().trim()) && !"".equals(mHintStringWidget.getText().toString().trim())) {
                mSaveButton.setEnabled(true);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Useless
        }

        @Override
        public void afterTextChanged(Editable s) {
            // Useless

        }
    }
}
