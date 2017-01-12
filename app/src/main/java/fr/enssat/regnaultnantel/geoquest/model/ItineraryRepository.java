package fr.enssat.regnaultnantel.geoquest.model;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import fr.enssat.regnaultnantel.geoquest.R;
import fr.enssat.regnaultnantel.geoquest.exceptions.GeoQuestUnexpectedException;
import fr.enssat.regnaultnantel.geoquest.utilities.Constants;
import fr.enssat.regnaultnantel.geoquest.utilities.JSONHelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ItineraryRepository {

    private static final String TAG = ItineraryRepository.class.getCanonicalName();

    private static final File SD_CARD = Environment.getExternalStorageDirectory();
    private Context mContext;

    public ItineraryRepository(Context context) {
        this.mContext = context;
        // Make sure the path exists
        new File(SD_CARD.getAbsolutePath() + Constants.GEO_QUEST_SD_CARD_DIRECTORY).mkdirs();
    }

    // ====
    // SAVE
    // ====

    public void save(Itinerary itinerary) {
        File file = new File(SD_CARD + Constants.GEO_QUEST_SD_CARD_DIRECTORY, itinerary.getName());
        try (FileOutputStream ouputStream = new FileOutputStream(file)) {
            String jsonString = JSONHelper.toJSON(itinerary);
            ouputStream.write(jsonString.getBytes());
        } catch (IOException e) {
            Log.e(TAG, "Error occured while trying to save itinerary " + itinerary.getName());
            throw new GeoQuestUnexpectedException("Cannot save the itinerary " + itinerary.getName(), e);
        }
    }

    // ====
    // LOAD
    // ====

    /**
     * Load an existing itinerary from the SD Card GeoQuest directory.
     * Throws GeoQuestUnexpectedException if an error occured.
     *
     * @return itinerary
     */
    public Itinerary load(String itineraryName) {
        if (itineraryName.equals(Constants.DEFAULT_ITINERARY_NAME)) {
            return getDefaultItinerary();
        }
        File file = new File(SD_CARD + Constants.GEO_QUEST_SD_CARD_DIRECTORY, itineraryName);
        try {
            StringBuilder text = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    text.append(line);
                }
                return JSONHelper.fromJSON(text.toString(), Itinerary.class);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error occured while trying to read itinerary " + itineraryName);
            throw new GeoQuestUnexpectedException("Cannot load itinerary", e);
        }
    }

    /**
     * @return the list of all existing itinerary name. Don't include the default one.
     */
    public List<String> getAll() {
        File folder = new File(SD_CARD + Constants.GEO_QUEST_SD_CARD_DIRECTORY);
        File[] files = folder.listFiles();

        List<String> names = new ArrayList<>();
        for (File file : files) {
            if (file.isFile()) {
                names.add(file.getName());
            }
        }
        Log.d(TAG, "Found " + names.size() + " itinerary");
        if (names.isEmpty()) {
            names.add(Constants.DEFAULT_ITINERARY_NAME);
        }
        return names;
    }

    /**
     * @return the default itinerary, specified in res/raw/default_itinerary.json
     */
    private Itinerary getDefaultItinerary() {
        InputStream inputStream = mContext.getResources().openRawResource(R.raw.default_itinerary);
        return JSONHelper.fromJSON(inputStream, Itinerary.class);
    }

    // ======
    // UPDATE
    // ======

    // Quick and dirty: remove the file and re-save it
    public Itinerary update(Itinerary itinerary) {
        remove(itinerary);
        save(itinerary);
        return itinerary;
    }

    // ======
    // DELETE
    // ======

    public void removeAll() {
        File folder = new File(SD_CARD + Constants.GEO_QUEST_SD_CARD_DIRECTORY);
        File[] files = folder.listFiles();

        int count = 0;
        for (File file : files) {
            if (file.isFile()) {
                file.delete();
            }
        }
        Log.d(TAG, count + " file(s) removed");
    }

    public void remove(Itinerary itinerary) {
        remove(itinerary.getName());
    }

    public void remove(String itineraryName) {
        File folder = new File(SD_CARD + Constants.GEO_QUEST_SD_CARD_DIRECTORY);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile() && file.getName().equals(itineraryName)) {
                file.delete();
            }
        }
    }
}
