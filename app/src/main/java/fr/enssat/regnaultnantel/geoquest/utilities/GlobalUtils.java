package fr.enssat.regnaultnantel.geoquest.utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class GlobalUtils {

    public static Bitmap stringToBitmap(String base64Data) {
        byte[] decodedString = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}
