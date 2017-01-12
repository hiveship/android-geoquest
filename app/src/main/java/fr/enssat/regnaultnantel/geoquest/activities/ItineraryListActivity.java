package fr.enssat.regnaultnantel.geoquest.activities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import fr.enssat.regnaultnantel.geoquest.R;
import fr.enssat.regnaultnantel.geoquest.model.ItineraryRepository;
import fr.enssat.regnaultnantel.geoquest.utilities.Constants;

import java.util.List;

public class ItineraryListActivity extends AbstractGeoQuestActivity implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private ItineraryRepository mItineraryRepository = new ItineraryRepository(this);
    private List<String> mItineraries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary_list);
        mListView = (ListView) findViewById(R.id.itinerary_list);
        mItineraries = mItineraryRepository.getAll();

      //  ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mItineraries);
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);

                //String currentLocation = getResources().getString(R.string.Current_Location);
                int textColor = R.color.textWhite;
                textView.setTextColor(getResources().getColor(textColor));
                textView.setText(mItineraries.get(position));

                return textView;
            }
        });
       // mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, LauncherActivity.class);
        intent.putExtra(Constants.ITINERARY_INTENT_PARAM, mItineraries.get(position));
        Log.d(TAG, "Start intent with extra itinerary name = " + mItineraries.get(position));
        setResult(RESULT_OK, intent);
        finish();
    }
}
