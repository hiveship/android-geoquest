package fr.enssat.regnaultnantel.geoquest.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import fr.enssat.regnaultnantel.geoquest.R;
import fr.enssat.regnaultnantel.geoquest.utilities.GlobalUtils;

public class BeaconAdapter extends BaseAdapter {

    private Itinerary mItinerary;
    private LayoutInflater mInflater;

    public BeaconAdapter(Context context, Itinerary itinerary) {
        this.mItinerary = itinerary;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mItinerary.getBeacons().size();
    }

    @Override
    public Object getItem(int position) {
        return mItinerary.getBeacons().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout item;
        if (convertView == null) {
            item = (LinearLayout) mInflater.inflate(R.layout.row_beacon_layout, parent, false);
        } else {
            item = (LinearLayout) convertView;
        }

        TextView hintStringWidget = (TextView) item.findViewById(R.id.beacon_hint_string);
        TextView longitudeWidget = (TextView) item.findViewById(R.id.beacon_longitude);
        TextView latitudeWidget = (TextView) item.findViewById(R.id.beacon_latitude);
        ImageView hintImageWidget = (ImageView) item.findViewById(R.id.beacon_hint_image);

        hintStringWidget.setText(mItinerary.getBeacons().get(position).getHintString());
        longitudeWidget.setText("Longitude " + mItinerary.getBeacons().get(position).getCoordinates().getLongitude()); //FIXME
        latitudeWidget.setText("Latitude " + mItinerary.getBeacons().get(position).getCoordinates().getLatitude()); //FIXME

        String image = mItinerary.getBeacons().get(position).getHintImage();
        if (image != null) {
            hintImageWidget.setImageBitmap(GlobalUtils.stringToBitmap(image));
        }
        return item;
    }
}
