package com.example.android.sunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    private TextView mWeatherTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mWeatherTextView = (TextView) findViewById(R.id.tv_zee_weather);
        if(getIntent().hasExtra(MainActivity.weatherExtra))
        {
            String zeeWeather = getIntent().getStringExtra(MainActivity.weatherExtra);
            mWeatherTextView.setText(zeeWeather);
        }


        // DONE (2) Display the weather forecast that was passed from MainActivity
    }
}