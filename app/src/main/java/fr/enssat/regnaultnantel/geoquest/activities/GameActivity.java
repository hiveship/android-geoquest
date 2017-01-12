package fr.enssat.regnaultnantel.geoquest.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import fr.enssat.regnaultnantel.geoquest.R;
import fr.enssat.regnaultnantel.geoquest.exceptions.ItineraryCompleteException;
import fr.enssat.regnaultnantel.geoquest.model.Beacon;
import fr.enssat.regnaultnantel.geoquest.model.GameSessionData;
import fr.enssat.regnaultnantel.geoquest.model.Itinerary;
import fr.enssat.regnaultnantel.geoquest.model.ItineraryRepository;
import fr.enssat.regnaultnantel.geoquest.utilities.AbstractLocationListenerImpl;
import fr.enssat.regnaultnantel.geoquest.utilities.Constants;
import fr.enssat.regnaultnantel.geoquest.utilities.GlobalUtils;

public class GameActivity extends AbstractGeoQuestActivity implements OnMapReadyCallback {

    private LocationManager mLocationManager;
    private ItineraryRepository mItineraryRepository = new ItineraryRepository(this);
    private AbstractLocationListenerImpl mLocationListener;

    private GoogleMap mMap;
    private LinearLayout mLayoutHintView;
    private TextView mHintStringView;
    private ImageView mHintImageView;

    private GameSessionData mGameData;

    // ===================
    // ACTIVITY MANAGEMENT
    // ===================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mHintStringView = (TextView) findViewById(R.id.hintString);
        mHintImageView = (ImageView) findViewById(R.id.hintImage);
        mLayoutHintView = (LinearLayout) findViewById(R.id.layoutHint);
        mLocationListener = new AbstractLocationListenerImpl() {
            @Override
            public void onLocationChanged(Location location) {
                updateLocation(location);
            }
        };
        String itineraryName = getIntent().getStringExtra(Constants.ITINERARY_INTENT_PARAM);
        Itinerary itinerary = mItineraryRepository.load(itineraryName);
        this.mGameData = new GameSessionData(itinerary);

        // Obtain the SupportMapFragment and get notified when the mMap is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Don't query for location updates if the activity is not active (battery...)
        mLocationManager.removeUpdates(mLocationListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Enable location updates
        mLocationManager.requestLocationUpdates(mLocationManager.getBestProvider(new Criteria(), true), 0, 0, mLocationListener);
    }

    // ==================
    // GEO QUEST BUSINESS
    // ==================

    private void displayEndGameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.congrats);
        builder.setMessage(R.string.game_finish);
        builder.setNeutralButton(R.string.game_finish_dialog_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(GameActivity.this, LauncherActivity.class);
                startActivity(intent);
            }
        });
        builder.create().show();
    }

    private void displayBeaconReachedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.congrats);
        builder.setMessage(R.string.finish_beacon);
        builder.create().show();
    }

    /**
     * Update the widgets associated to the current beacon's hint.
     */
    private void updateHintView() {
        // Update hint string
        mHintStringView.setText(mGameData.getCurrentBeacon().getHintString());

        // Update hint image if present
        if (mGameData.getCurrentBeacon().getHintImage() != null) {
            mHintImageView.setImageBitmap(GlobalUtils.stringToBitmap(mGameData.getCurrentBeacon().getHintImage()));
            mHintImageView.setVisibility(View.VISIBLE);
        } else {
            mHintImageView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Changes the background color of the layout containing the 'hintString' and 'hintImage' widget according to the distance.
     *
     * @param distance
     *         in meters between the current beacon and the current user location
     */
    private void updateHintLayoutColor(float distance) {
        if (distance < Constants.DISTANCE_NEAR_METERS) {
            mLayoutHintView.setBackgroundColor(getResources().getColor(R.color.beaconNear));
        } else if (distance < Constants.DISTANCE_MID_METERS) {
            mLayoutHintView.setBackgroundColor(getResources().getColor(R.color.beaconMid));
        } else {
            mLayoutHintView.setBackgroundColor(getResources().getColor(R.color.beaconFar));
        }
    }

    // =====================
    // ON MAP READY CALLBACK
    // =====================

    /**
     * Manipulates the mMap once available.
     * This callback is triggered when the mMap is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;

        // Set default camera position
        Location location = mLocationManager.getLastKnownLocation(mLocationManager.getBestProvider(new Criteria(), false));
        Log.d(TAG, "onMapReady - last known location = " + location);
        if (location != null) {
            this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), Constants.DEFAULT_MAP_ZOOM));
        }

        // Display the current user position
        this.mMap.setMyLocationEnabled(true);

        updateHintView();
    }

    /**
     * Method called on every location changed (notified by the mLocationListener)
     *
     * @param location
     *         the new location of the user
     */
    private void updateLocation(Location location) {
        if (mMap != null) {
            Log.d(TAG, "Location changed. Lat = " + location.getLatitude() + " | Lon = " + location.getLongitude());
            this.mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), Constants.DEFAULT_MAP_ZOOM));

            Beacon currentBeacon = mGameData.getCurrentBeacon();
            float distance = location.distanceTo(currentBeacon.getCoordinates().toLocation()); // in meters
            Log.d(TAG, "Distance to next step is " + distance + " meters");
            updateHintLayoutColor(distance);

            if (distance < Constants.SECURITY_DISTANCE_METERS) {
                try {
                    Log.d(TAG, "Beacon reached !");
                    mGameData.processBeaconReached();
                    displayBeaconReachedDialog();
                    updateHintView();
                } catch (ItineraryCompleteException e) {
                    Log.d(TAG, "Itinerary finish");
                    mGameData.setFinish(true);
                    displayEndGameDialog();
                }
            }
        }
    }
}
