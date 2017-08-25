package com.pk.myresume.util;

import android.support.v4.app.FragmentActivity;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Purvesh on 3/10/2017.
 */

public class JSONUtil {

    /**
     * Returns the JSON string read from the asset json file.
     * @param filename
     * @param activity
     * @return
     */
    public String loadJSONFromAsset(String filename, FragmentActivity activity) {

        String json = null;

        try {
            InputStream is = activity.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
