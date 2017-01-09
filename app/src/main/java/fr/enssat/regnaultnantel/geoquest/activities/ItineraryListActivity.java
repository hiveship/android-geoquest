package fr.enssat.regnaultnantel.geoquest.activities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import fr.enssat.regnaultnantel.geoquest.R;
import fr.enssat.regnaultnantel.geoquest.model.ItineraryRepository;
import fr.enssat.regnaultnantel.geoquest.utilities.Constants;

import java.util.List;

public class ItineraryListActivity extends AbstractGeoQuestActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private ItineraryRepository itineraryRepository = new ItineraryRepository(this);
    private List<String> itineraries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itineraries);
        listView = (ListView) findViewById(R.id.itineraryListView);
        itineraries = itineraryRepository.getAll();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itineraries);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, LauncherActivity.class);
        intent.putExtra(Constants.ITINERARY_INTENT_PARAM, itineraries.get(position));
        Log.d(TAG, "Start intent with extra itinerary name = " + itineraries.get(position));
        setResult(RESULT_OK, intent);
        finish();
    }
}
