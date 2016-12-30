package fr.enssat.regnaultnantel.geoquest.model;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import fr.enssat.regnaultnantel.geoquest.R;

public class BeaconAdapter extends BaseAdapter {

    private Itinerary itinerary;
    private Context mContext;
    private LayoutInflater mInflater;

    public BeaconAdapter(Context context, Itinerary itinerary) {
        mContext = context;
        this.itinerary = itinerary;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return itinerary.getBeacons().size();
    }

    @Override
    public Object getItem(int position) {
        return itinerary.getBeacons().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutItem;
        //(1) : Réutilisation des layouts
        if (convertView == null) {
            //Initialisation de notre item à partir du  layout XML "person_layout.xml"
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.beacon_layout, parent, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }

        //(2) : Récupération des TextView de notre layout
        TextView name = (TextView) layoutItem.findViewById(R.id.Beacon_name);
        TextView hint = (TextView) layoutItem.findViewById(R.id.Beacon_hint);
        ImageView imageView = (ImageView) layoutItem.findViewById(R.id.Beacon_image);

        //(3) : Renseignement des valeurs
        name.setText(itinerary.getBeacons().get(position).getName());
        hint.setText(itinerary.getBeacons().get(position).getHintString());
        String image = itinerary.getBeacons().get(position).getHintImage();
        if (image != null) {
            byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            imageView.setImageBitmap(decodedByte);
        }

        return layoutItem;
    }
}
