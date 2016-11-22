package fr.enssat.regnaultnantel.geoquest.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import fr.enssat.regnaultnantel.geoquest.R;
import fr.enssat.regnaultnantel.geoquest.model.Position;
import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.Observable;
import java.util.Observer;

public class GeoQuestMainActivity extends AppCompatActivity implements Observer {
    private IMapController mapController;
    private MapView mapView;

    private double initialLongitude;
    private double initialLatitude;
    private Position position;

    public GeoQuestMainActivity(){
        this.initialLatitude = 48.730031;
        this.initialLongitude = -3.462632;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.position = new Position(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        mapView = (MapView) findViewById(R.id.map);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapController = mapView.getController();
        mapController.setZoom(20);

        GeoPoint initialUserPosition = new GeoPoint(initialLatitude, initialLongitude);
        mapController.setCenter(initialUserPosition);
        //mapView.setUseDataConnection(false);
    }


    @Override
    public void update(Observable o, Object arg) {
        // TODO: Récupérer la nouvelle position GPS
        double newLatitude = -1; //TODO
        double newLongitude = -1; //TODO
        GeoPoint newUserPosition = new GeoPoint(newLatitude, newLongitude);
        mapController.animateTo(newUserPosition);
    }
}
