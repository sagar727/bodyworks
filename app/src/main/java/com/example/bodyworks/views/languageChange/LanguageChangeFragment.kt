package com.example.bodyworks.views.languageChange

import android.app.Activity
import android.content.Context
import android.content.res.AssetManager
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.bodyworks.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Locale


class LanguageChangeFragment : BottomSheetDialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_language_change, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState);
        val btnEnglish = view.findViewById<Button>(R.id.btnEnglish);
        val btnHindi = view.findViewById<Button>(R.id.btnHindi);
        val btnFrench = view.findViewById<Button>(R.id.btnFrench);
        val btnSpanish = view.findViewById<Button>(R.id.btnSpanish);
        val hostActivity: Activity? = activity

        btnEnglish.setOnClickListener {
            if (hostActivity != null) {
                setLocale("en", hostActivity.assets)
            };
            hostActivity?.recreate()
            dismiss()
        }
        btnFrench.setOnClickListener {
            if (hostActivity != null) {
                setLocale("fr", hostActivity.assets)
            };
            hostActivity?.recreate()
            dismiss()
        }
        btnHindi.setOnClickListener {
            if (hostActivity != null) {
                setLocale("hi", hostActivity.assets)
            };
            hostActivity?.recreate()
            dismiss()
        }
        btnSpanish.setOnClickListener {
            if (hostActivity != null) {
                setLocale("es", hostActivity.assets)
            };
            hostActivity?.recreate()
            dismiss()
        }
    }

    private fun setLocale(languageCode: String, assets: AssetManager) {
        val locale: Locale = Locale(languageCode);
        Locale.setDefault(locale);

        val configuration = Configuration(resources.configuration);
        configuration.setLocale(locale)

        val newResources = Resources(assets, resources.displayMetrics, configuration)
        resources.updateConfiguration(configuration, resources.displayMetrics)

        val sharedPreferences = requireActivity().getSharedPreferences("ChangeLang", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("app_lang",languageCode).apply();
    }

    companion object {
        const val TAG = "LanguageChangeFragment";
    }
}