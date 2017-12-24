package com.example.android.sunshine;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    // done (5) Override onCreatePreferences and add the preference xml file using addPreferencesFromResource
    // Do step 9 within onCreatePreference
    // done (9) Set the preference summary on each preference that isn't a CheckBoxPreference
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.sunshine_preferences);
        int prefCount = getPreferenceScreen().getPreferenceCount();
        for(int i=0; i < prefCount; i++) {
            Preference pref = getPreferenceScreen().getPreference(i);
            if (pref instanceof EditTextPreference) {
                EditTextPreference etPref = (EditTextPreference) pref;
                setPreferenceSummary(pref, etPref.getText());
            } else if (pref instanceof ListPreference) {
                ListPreference listPref = (ListPreference) pref;
                int valIndex = listPref.findIndexOfValue(listPref.getValue());
                String value = listPref.getEntries()[valIndex].toString();
                setPreferenceSummary(pref, value);
            }

        }
    }

// Do steps 5 - 11 within SettingsFragment
    // DONE (10) Implement OnSharedPreferenceChangeListener from SettingsFragment

    // done (8) Create a method called setPreferenceSummary that accepts a Preference and an Object and sets the summary of the preference
    private void setPreferenceSummary(Preference pref, Object newSummary) {
        if (newSummary instanceof String)
            pref.setSummary((CharSequence) newSummary);
    }






    // DONE (13) Unregister SettingsFragment (this) as a SharedPreferenceChangedListener in onStop

    @Override
    public void onStop() {
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onStop();
    }


    // DONE (12) Register SettingsFragment (this) as a SharedPreferenceChangedListener in onStart


    @Override
    public void onStart() {
        super.onStart();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    // DONE (11) Override onSharedPreferenceChanged to update non CheckBoxPreferences when they are changed
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Preference pref = findPreference(s);
        if (pref != null) {
            if (pref instanceof EditTextPreference) {
                EditTextPreference etPref = (EditTextPreference) pref;
                setPreferenceSummary(pref, etPref.getText());
            } else if (pref instanceof ListPreference) {
                ListPreference listPref = (ListPreference) pref;
                int valIndex = listPref.findIndexOfValue(listPref.getValue());
                String value = listPref.getEntries()[valIndex].toString();
                setPreferenceSummary(pref, value);
            }
        }

    }

}
