package fr.enssat.regnaultnantel.geoquest.activities;

import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import fr.enssat.regnaultnantel.geoquest.LocationListenerImpl;
import fr.enssat.regnaultnantel.geoquest.R;
import fr.enssat.regnaultnantel.geoquest.utilities.Constants;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.Observable;
import java.util.Observer;

public class GeoQuestMainActivity extends AppCompatActivity implements Observer {

    private static final String TAG = GeoQuestMainActivity.class.getName();

    private MapView mapView;

    private double initialLongitude;
    private double initialLatitude;

    public GeoQuestMainActivity(){
        this.initialLatitude = Constants.DEFAULT_LATITUDE;
        this.initialLongitude = Constants.DEFAULT_LONGITUDE;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        this.mapView = (MapView) findViewById(R.id.map);

        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);
        mapView.getController().setZoom(Constants.DEFAULT_MAP_ZOOM);

        LocationManager lm;
        LocationListener ll = new LocationListenerImpl();
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, ll);

        mapView.getController().setCenter(new GeoPoint(initialLatitude, initialLongitude));
        //mapView.setUseDataConnection(false);
    }


    @Override
    public void update(Observable o, Object arg) {
        // TODO: Récupérer la nouvelle position GPS
        Log.d(TAG, "on update");
        double newLatitude = -1; //TODO
        double newLongitude = -1; //TODO
        GeoPoint newUserPosition = new GeoPoint(newLatitude, newLongitude);
        mapView.getController().animateTo(newUserPosition);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
