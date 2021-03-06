package com.example.android.sunshine;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("BRAT", "SettingsActivity: onCreate()");
        setContentView(R.layout.activity_settings);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("BRAT", "SettingsActivity: Clicked a menu!");
        if (item.getItemId() == android.R.id.home )
        {

            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
