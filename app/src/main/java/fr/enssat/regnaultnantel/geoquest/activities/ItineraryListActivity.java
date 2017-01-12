package fr.enssat.regnaultnantel.geoquest.activities;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import fr.enssat.regnaultnantel.geoquest.R;
import fr.enssat.regnaultnantel.geoquest.model.Itinerary;
import fr.enssat.regnaultnantel.geoquest.model.ItineraryRepository;
import fr.enssat.regnaultnantel.geoquest.utilities.Constants;

import java.util.List;

import static fr.enssat.regnaultnantel.geoquest.utilities.Constants.ITINERARY_INTENT_PARAM;

public class ItineraryListActivity extends AbstractGeoQuestActivity {

    private ListView mListView;
    private FloatingActionButton mAddButton;
    private ArrayAdapter<String> mAdapter;
    private ItineraryRepository mItineraryRepository = new ItineraryRepository(this);
    private List<String> mItineraries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary_list);
        mListView = (ListView) findViewById(R.id.itinerary_list);
        mItineraries = mItineraryRepository.getAll();

        initializeListView();
        initializeCreateButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }

    private void initializeListView() {
        mAdapter = new ArrayAdapter<>(this, R.layout.itinerary_row_layout);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String originAction = getIntent().getExtras().getString(Constants.ITINERARY_LIST_ACTION_PARAM);
                String selected = mItineraries.get(position);
                if (originAction.equals(Constants.ITINERARY_LIST_ACTION_GAME)) {
                    doGameAction(selected);
                } else if(originAction.equals(Constants.ITINERARY_LIST_ACTION_EDITOR)) {
                    doEditorAction(selected);
                }

            }
        });
    }

    private void doEditorAction(String selected) {
        Intent intent = new Intent(this, BeaconsListActivity.class);
        intent.putExtra(ITINERARY_INTENT_PARAM, selected);
        startActivity(intent);
    }

    private void doGameAction(String selected) {
        Intent intent = new Intent(ItineraryListActivity.this, LauncherActivity.class);
        intent.putExtra(ITINERARY_INTENT_PARAM, selected);
        Log.d(TAG, "Start intent with extra itinerary name = " + selected);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void initializeCreateButton() {
        mAddButton = (FloatingActionButton) findViewById(R.id.add_itinerary_button);
        mAddButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inflate view and get widgets
                LayoutInflater layoutInflater = LayoutInflater.from(ItineraryListActivity.this);
                View promptView = layoutInflater.inflate(R.layout.create_itinerary_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ItineraryListActivity.this);
                alertDialogBuilder.setView(promptView);
                final EditText itineraryName = (EditText) promptView.findViewById(R.id.field_itinerary_name);

                // Configure behavior
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Itinerary newItinerary = new Itinerary();
                        newItinerary.setName(itineraryName.getText().toString());
                        Log.d(TAG, "Creating a new itinerary with name = " + itineraryName.getText().toString());
                        mItineraryRepository.save(newItinerary);
                        refreshListView();
                    }
                });

                alertDialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                // Display
                alertDialogBuilder.create().show();
            }
        });
    }

    private void refreshListView() {
        mItineraries = mItineraryRepository.getAll();
        mAdapter.clear();
        mAdapter.addAll(mItineraries);
    }

}
