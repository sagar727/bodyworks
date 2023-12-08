package com.example.bodyworks

import android.content.Intent
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val themeSelection = findPreference<ListPreference>("Current Theme")
        val currentTheme = themeSelection?.value
        themeSelection?.summary = "Current Selected Theme: $currentTheme"
        themeSelection?.setOnPreferenceChangeListener { _, newValue ->
            themeSelection.summary = "Current Selected Theme: $newValue"
            val intent = Intent(activity,MainActivity::class.java)
            startActivity(intent)
            true
        }

        val darkModeSelection = findPreference<SwitchPreferenceCompat>("darkMode")
        darkModeSelection?.setOnPreferenceChangeListener { _, newValue ->
            val intent = Intent(activity,MainActivity::class.java)
            startActivity(intent)
            true
        }
    }
}