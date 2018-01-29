//  done (1) Create a class called SunshineSyncTask
//  done (2) Within SunshineSyncTask, create a synchronized public static void method called syncWeather
//      done (3) Within syncWeather, fetch new weather data
//      done (4) If we have valid results, delete the old data and insert the new
package com.example.android.sunshine.sync;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.example.android.sunshine.MainActivity;
import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import java.net.URL;

public class SunshineSyncTask {
    public static synchronized void syncWeather(Context context) {
        URL weatherRequestUrl = NetworkUtils.getUrl(context);

        try {
            String jsonWeatherResponse = NetworkUtils
                    .getResponseFromHttpUrl(weatherRequestUrl);

            Log.d("BRAT", "Generated a request URL "+weatherRequestUrl);
            ContentValues[] weatherValues  = OpenWeatherJsonUtils
                    .getWeatherContentValuesFromJson(context, jsonWeatherResponse);

            Log.d("BRAT", "Received a response with "+weatherValues.length+" rows");
            if (weatherValues.length > 0) {
                Log.d("BRAT", "Replacing values");
                int deleted = context.getContentResolver().delete(WeatherContract.WeatherEntry.CONTENT_URI,
                        null, null);
                Log.d("BRAT", "Deleted "+deleted+" entries.");
                int inserted = context.getContentResolver().bulkInsert(WeatherContract.WeatherEntry.CONTENT_URI, weatherValues);
                Log.d("BRAT", "Added "+inserted+" entries.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}