package fr.enssat.regnaultnantel.geoquest.model;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class BeaconAdapter extends BaseAdapter {

    private Itinerary itinerary;

    public BeaconAdapter(Itinerary itinerary){
        this.itinerary = itinerary;
    }

    @Override
    public int getCount() {
        return itinerary.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
