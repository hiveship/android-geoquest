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

    private LocationManager locationManager;
    private ItineraryRepository itineraryRepository = new ItineraryRepository(this);
    private AbstractLocationListenerImpl locationListener;

    private GoogleMap map;
    private LinearLayout layoutHintView;
    private TextView hintStringView;
    private ImageView hintImageView;

    private GameSessionData game;

    // ===================
    // ACTIVITY MANAGEMENT
    // ===================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        hintStringView = (TextView) findViewById(R.id.hintString);
        hintImageView = (ImageView) findViewById(R.id.hintImage);
        layoutHintView = (LinearLayout) findViewById(R.id.layoutHint);

        String itineraryName = getIntent().getStringExtra(Constants.ITINERARY_INTENT_PARAM);
        Itinerary itinerary = getItinerary(itineraryName);
        this.game = new GameSessionData(itinerary);

        this.locationListener = new AbstractLocationListenerImpl() {
            @Override
            public void onLocationChanged(Location location) {
                updateLocation(location);
            }
        };

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Don't query for location updates if the activity is not active (battery...)
        locationManager.removeUpdates(locationListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(locationManager.getBestProvider(new Criteria(), false), 0, 0, locationListener);
    }

    // ==================
    // GEO QUEST BUSINESS
    // ==================

    private Itinerary getItinerary(String itineraryName) {
        Itinerary itinerary;
        if (itineraryName == null) {
            // itinerary = itineraryRepository.load(itineraryName); FIXME
            itinerary = itineraryRepository.getDefaultItinerary();
        } else {
            itinerary = itineraryRepository.getDefaultItinerary();
        }
        return itinerary;
    }

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
        hintStringView.setText(game.getCurrentBeacon().getHintString());

        // Update hint image if present
        if (game.getCurrentBeacon().getHintImage() != null) {
            hintImageView.setImageBitmap(GlobalUtils.stringToBitmap(game.getCurrentBeacon().getHintImage()));
            hintImageView.setVisibility(View.VISIBLE);
        } else {
            hintImageView.setVisibility(View.INVISIBLE);
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
            layoutHintView.setBackgroundColor(getResources().getColor(R.color.beaconNear));
        } else if (distance < Constants.DISTANCE_MID_METERS) {
            layoutHintView.setBackgroundColor(getResources().getColor(R.color.beaconMid));
        } else {
            layoutHintView.setBackgroundColor(getResources().getColor(R.color.beaconFar));
        }
    }

    //TODO: MISE A JOUR DE LIMAGE EN PLUS DU TEXT VIEW

    // =====================
    // ON MAP READY CALLBACK
    // =====================

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;

        // Set default camera position
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), false));
        Log.d(TAG, "onMapReady - last known location = " + location);
        if (location != null) {
            this.map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), Constants.DEFAULT_MAP_ZOOM));
        }

        // Display the current user position
        this.map.setMyLocationEnabled(true);

        updateHintView();
    }

    /**
     * Method called on every location changed (notified by the locationListener)
     *
     * @param location
     *         the new location of the user
     */
    public void updateLocation(Location location) {
        Log.d(TAG, "Location changed. Lat = " + location.getLatitude() + " | Lon = " + location.getLongitude());
        this.map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), Constants.DEFAULT_MAP_ZOOM));

        Beacon currentBeacon = game.getCurrentBeacon();
        float distance = location.distanceTo(currentBeacon.getCoordinates().toLocation()); // in meters
        Log.d(TAG, "Distance to next step is " + distance + " meters");
        updateHintLayoutColor(distance);

        if (distance < Constants.SECURITY_DISTANCE_METERS) {
            try {
                Log.d(TAG, "Beacon reached !");
                game.processBeaconReached();
                displayBeaconReachedDialog();
                updateHintView();
            } catch (ItineraryCompleteException e) {
                Log.d(TAG, "Itinerary finish");
                game.setFinish(true);
                displayEndGameDialog();
            }
        }
    }
}
