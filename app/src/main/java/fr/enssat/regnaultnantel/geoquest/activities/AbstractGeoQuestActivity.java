package fr.enssat.regnaultnantel.geoquest.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import fr.enssat.regnaultnantel.geoquest.R;

/**
 * A base activity that handles common functionality in the app.
 */
public abstract class AbstractGeoQuestActivity extends AppCompatActivity {

    /**
     * This TAG is used by the Android Logger
     */
    protected final String TAG = getClass().getCanonicalName();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.launcher) {
            Intent intent = new Intent(this, LauncherActivity.class);
            startActivity(intent);
        }
        return true;
    }
}