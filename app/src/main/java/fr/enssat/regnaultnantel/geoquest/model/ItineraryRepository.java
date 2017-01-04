package fr.enssat.regnaultnantel.geoquest.model;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import fr.enssat.regnaultnantel.geoquest.R;
import fr.enssat.regnaultnantel.geoquest.utilities.Constants;
import fr.enssat.regnaultnantel.geoquest.utilities.JSONHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

import static com.google.android.gms.wearable.DataMap.TAG;

public class ItineraryRepository {

    private Context context;

    public ItineraryRepository(Context context) {
        this.context = context;
    }

    //TODO
    public static Itinerary load(String itineraryName) {
        Itinerary itinerary = new Itinerary();

        Beacon fake1 = new Beacon();
        fake1.setHintString("la mie caline");
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude(48.731483);
        coordinates.setLongitude(-3.460341);
        fake1.setCoordinates(coordinates);

        Beacon fake2 = new Beacon();
        fake2.setHintString("leclerc perros");
        Coordinates coordinates2 = new Coordinates();
        coordinates2.setLatitude(48.725448);
        coordinates2.setLongitude(-3.449291);
        fake2.setCoordinates(coordinates2);

        itinerary.getBeacons().add(fake1);
        itinerary.getBeacons().add(fake2);

        return itinerary;
    }

    public void save(Itinerary itinerary) {

        Beacon fake1 = new Beacon();
        fake1.setHintString("la mie caline");
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude(48.731483);
        coordinates.setLongitude(-3.460341);
        fake1.setCoordinates(coordinates);

        Beacon fake2 = new Beacon();
        fake2.setHintString("leclerc perros");
        Coordinates coordinates2 = new Coordinates();
        coordinates2.setLatitude(48.725448);
        coordinates2.setLongitude(-3.449291);
        fake2.setCoordinates(coordinates2);

        itinerary.getBeacons().add(fake1);
        itinerary.getBeacons().add(fake2);

        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File(sdCard.getAbsolutePath() + Constants.GEO_QUEST_SD_CARD_DIRECTORY);
        dir.mkdirs();
        if (itinerary.getName() == null) {
            itinerary.setName(UUID.randomUUID().toString());
        }
        File file = new File(dir, "filename");

        try (FileOutputStream f = new FileOutputStream(file);) {
            String jsonString = JSONHelper.toJSON(itinerary);
            Log.d(TAG, jsonString);
            f.write(jsonString.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO: Sauvegarde un itinéraire dans la sd card

    }

    public void getAll() throws IllegalAccessException {
        //  TODO: Récupère la liste des itinéraires créés par l'utilisateur dans la SD card
    }

    public Itinerary getDefaultItinerary() {
        InputStream inputStream = context.getResources().openRawResource(R.raw.default_itinerary);
        return JSONHelper.fromJSON(inputStream, Itinerary.class);
    }
}
