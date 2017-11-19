/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.android.sunshine.data.SunshinePreferences;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView mWeatherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        /*
         * Using findViewById, we get a reference to our TextView from xml. This allows us to
         * do things like set the text of the TextView.
         */
        mWeatherTextView = (TextView) findViewById(R.id.tv_weather_data);

        // DONE (4) Delete the dummy weather data. You will be getting REAL data from the Internet in this lesson.
        /*
         * This String array contains dummy weather data. Later in the course, we're going to get
         * real weather data. For now, we want to get something on the screen as quickly as
         * possible, so we'll display this dummy data.
         */
//        String[] dummyWeatherData = {
//                "Today, May 17 - Clear - 17°C / 15°C",
//                "Tomorrow - Cloudy - 19°C / 15°C",
//                "Thursday - Rainy- 30°C / 11°C",
//                "Friday - Thunderstorms - 21°C / 9°C",
//                "Saturday - Thunderstorms - 16°C / 7°C",
//                "Sunday - Rainy - 16°C / 8°C",
//                "Monday - Partly Cloudy - 15°C / 10°C",
//                "Tue, May 24 - Meatballs - 16°C / 18°C",
//                "Wed, May 25 - Cloudy - 19°C / 15°C",
//                "Thu, May 26 - Stormy - 30°C / 11°C",
//                "Fri, May 27 - Hurricane - 21°C / 9°C",
//                "Sat, May 28 - Meteors - 16°C / 7°C",
//                "Sun, May 29 - Apocalypse - 16°C / 8°C",
//                "Mon, May 30 - Post Apocalypse - 15°C / 10°C",
//        };

        // DONE (3) Delete the for loop that populates the TextView with dummy data
        /*
         * Iterate through the array and append the Strings to the TextView. The reason why we add
         * the "\n\n\n" after the String is to give visual separation between each String in the
         * TextView. Later, we'll learn about a better way to display lists of data.
//         */
//        for (String dummyWeatherDay : dummyWeatherData) {
//            mWeatherTextView.append(dummyWeatherDay + "\n\n\n");
//        }

        // DONE (9) Call loadWeatherData to perform the network request to get the weather

        loadWeatherData();
    }

    private void loadWeatherData() {
       // String pref_loc = SunshinePreferences.getPreferredWeatherLocation(this);
        URL u = NetworkUtils.buildUrl("David");
        new WeatherFetcher().execute(u);
    }
    // done (8) Create a method that will get the user's preferred location and execute your new AsyncTask and call it loadWeatherData


    // done (5) Create a class that extends AsyncTask to perform network requests
    // done (6) Override the doInBackground method to perform your network requests
    // done (7) Override the onPostExecute method to display the results of the network request
    public class WeatherFetcher extends AsyncTask<URL, Void, String[] > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String[] doInBackground(URL... urls) {
            try {
               String raw_json = NetworkUtils.getResponseFromHttpUrl(urls[0]);
                try {
                   return  OpenWeatherJsonUtils.getSimpleWeatherStringsFromJson(MainActivity.this, raw_json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] sarr) {
                for(String s: sarr) {
                    mWeatherTextView.append(s+"\n\n\n");
                }
        }
        //
//        protected void onPostExecute(String s) {
//            /* Run some Json parsing and list filling on s */
//            DateFormatSymbols dfs = new DateFormatSymbols();
//            try {
//                JSONObject top = new JSONObject(s);
//                int listSize = Integer.parseInt(top.getString("cnt"));
//                JSONArray list = top.getJSONArray("list");
//                if (list.length() != listSize)
//                    Log.w("BRAT", "Mismatch of list sizes!");
//
//                for(int i=0; i<list.length();i++)
//                {
//                    JSONObject weather_line = list.getJSONObject(i);
//                    Date d = new Date(Long.parseLong(weather_line.getString("dt"))*1000);
//                    int day_of_week = d.getDay();
//                    int day_of_month = d.getDate();
//                    int month = d.getMonth();
//
//                    JSONObject temp = weather_line.getJSONObject("temp");
//                    String day = temp.getString("day");
//                    String night = temp.getString("night");
//                    String month_name = dfs.getShortMonths()[month];
//                    String day_name = dfs.getShortWeekdays()[day_of_week+1];
//                    JSONObject weather = weather_line.getJSONArray("weather").getJSONObject(0);
//                    String description = weather.getString("description");
//                    mWeatherTextView.append(day_name + " " +month_name +", "+Integer.toString(day_of_month)+
//                            " - " + description + " - "+day+"°C / "+night+"°C" + "\n\n\n");
//                  //  May 28 - Meteors - 16°C / 7°C"
//
//                }
//            } catch (JSONException e) {
//                Log.e("BRAT", "Error parsing json: " + s);
//                e.printStackTrace();
//            }
//
//        }



    }
}