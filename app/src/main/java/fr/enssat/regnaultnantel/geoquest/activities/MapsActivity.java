package fr.enssat.regnaultnantel.geoquest.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import fr.enssat.regnaultnantel.geoquest.R;
import fr.enssat.regnaultnantel.geoquest.exceptions.ItineraryCompleteException;
import fr.enssat.regnaultnantel.geoquest.model.Beacon;
import fr.enssat.regnaultnantel.geoquest.model.GameData;
import fr.enssat.regnaultnantel.geoquest.model.Itinerary;
import fr.enssat.regnaultnantel.geoquest.model.ItineraryRepository;
import fr.enssat.regnaultnantel.geoquest.utilities.Constants;


public class MapsActivity extends AbstractGeoQuestActivity implements OnMapReadyCallback, LocationListener {

    private LocationManager locationManager;
    private ItineraryRepository itineraryRepository = new ItineraryRepository(this);

    private GoogleMap map;
    private GameData gameData;

    // ===================
    // ACTIVITY MANAGEMENT
    // ===================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        String itineraryName = getIntent().getStringExtra(Constants.ITINERARY_INTENT_PARAM);
        Itinerary itinerary = getItinerary(itineraryName);
        this.gameData = new GameData(itinerary);

        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

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

    @Override
    protected void onPause() {
        super.onPause();
        // Don't query for location updates if the activity is not active (battery...)
        locationManager.removeUpdates(this);
    }

    // ==================
    // GEO QUEST BUSINESS
    // ==================

    //TODO: A tester que ça marche
    private void displayEndGameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Congrats !");
        builder.setMessage("Game finished !");
        builder.create().show();
    }

    private void updateView() {
        TextView textView = (TextView) findViewById(R.id.hintString);
        if (gameData.isFinish()) {
            textView.setText(R.string.game_finish);
        } else {
            textView.setText(gameData.getCurrentBeacon().getHintString());
        }
        textView.setVisibility(View.VISIBLE);

        // TODO: A tester que ça marche et extraire dans un truc plus générique d'Utils
        if (gameData.getCurrentBeacon().getHintImage() != null) {
            ImageView imageView = (ImageView) findViewById(R.id.hintImage);
            byte[] decodedString = Base64.decode(gameData.getCurrentBeacon().getHintImage(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageView.setImageBitmap(decodedByte);
            imageView.setVisibility(View.VISIBLE);
        }
    }

    //TODO: ALERT DIALOG QUAND UN BEACON EST REACHE + MISE A JOUR DE LIMAGE EN PLUS DU TEXT VIEW

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
        locationManager.requestLocationUpdates(locationManager.getBestProvider(new Criteria(), false), 0, 0, this);

        // Set default camera position
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), false));
        Log.d(TAG, "onMapReady - last known location = " + location);
        if (location != null) {
            this.map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), Constants.DEFAULT_MAP_ZOOM));
        }

        // Display the current user position
        this.map.setMyLocationEnabled(true);

        updateView();
    }

    // ======================
    // LOCATION LISTENIR IMPL
    // ======================

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "Location changed. Lat = " + location.getLatitude() + " | Lon = " + location.getLongitude());
        this.map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), Constants.DEFAULT_MAP_ZOOM));

        if (!gameData.isFinish()) {
            Beacon currentBeacon = gameData.getCurrentBeacon();
            float distance = location.distanceTo(currentBeacon.getCoordinates().toLocation()); // in meters
            Log.d(TAG, "Distance to next step is " + distance + " meters");
            if (distance < Constants.SECURITY_DISTANCE_METERS) {
                try {
                    Log.d(TAG, "Beacon reached !");
                    gameData.processBeaconReached();
                    updateView();
                } catch (ItineraryCompleteException e) {
                    Log.d(TAG, "Itinerary finish");
                    gameData.setFinish(true);
                    displayEndGameDialog();
                }
            }
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        // Don't need this event, just ignore it
    }

    @Override
    public void onProviderEnabled(String s) {
        // Don't need this event, just ignore it
    }

    @Override
    public void onProviderDisabled(String s) {
        // Don't need this event, just ignore it
    }
}
