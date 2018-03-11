package com.kongzi.view

import android.os.Bundle
import android.support.v7.preference.PreferenceFragmentCompat
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import com.kongzi.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}