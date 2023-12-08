package com.example.bodyworks

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.bodyworks.databinding.ActivityMainBinding
import com.example.bodyworks.views.languageChange.LanguageChangeFragment
import com.example.bodyworks.viewModel.BodyWorksViewModel
import com.example.bodyworks.views.workoutCategory.WorkoutFragment
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bodyworksVM: BodyWorksViewModel
    private var languageChangeFragment: LanguageChangeFragment? = null
    private lateinit var sharedPreferences: SharedPreferences
    private var isDarkModeSelected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        sharedPreferences = this.let { PreferenceManager.getDefaultSharedPreferences(it) }!!
        isDarkModeSelected = sharedPreferences.getBoolean("darkMode",  false)
        if(isDarkModeSelected)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        sharedPreferences = this.let { PreferenceManager.getDefaultSharedPreferences(it) }!!
        val themeColor = sharedPreferences.getString("Current Theme", getString(R.string.red))
        changeTheme(themeColor)
        setContentView(binding.root)

        bodyworksVM = ViewModelProvider(this).get(BodyWorksViewModel::class.java)

        //Setting Workout page as home page
        replaceFragment(WorkoutFragment())
        //Changing fragment as per selection
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.workouts -> replaceFragment(WorkoutFragment())
                R.id.fitnessHub -> replaceFragment(FitnessHubFragment())
                R.id.settings -> replaceFragment(SettingsFragment())
                else -> {

                }
            }
            true
        }
        // Language change button code
        languageChangeFragment = LanguageChangeFragment();
        loadLocale();
        updateBottomNav();
        binding.fabLanguageChange.setOnClickListener {
            changeLanguage();
        }
    }

    private fun changeTheme(themeColor: String?) {
        val theme = super.getTheme()
        when (themeColor) {
            "Red" -> {
                theme.applyStyle(R.style.Base_Theme_Red, true)
                binding.bottomNavigationView.setBackgroundColor(getColor(R.color.primary))
                binding.bottomNavigationView.itemActiveIndicatorColor = getColorStateList(R.color.primary_shade)
                binding.fabLanguageChange.backgroundTintList = getColorStateList(R.color.primary_shade)
            }
            "Orange" -> {
                theme.applyStyle(R.style.Base_Theme_Orange, true)
                binding.bottomNavigationView.setBackgroundColor(getColor(R.color.orange))
                binding.bottomNavigationView.itemActiveIndicatorColor = getColorStateList(R.color.orange_shade)
                binding.fabLanguageChange.backgroundTintList = getColorStateList(R.color.orange_shade)
            }
            "Brown" -> {
                theme.applyStyle(R.style.Base_Theme_Brown, true)
                binding.bottomNavigationView.setBackgroundColor(getColor(R.color.brown))
                binding.bottomNavigationView.itemActiveIndicatorColor = getColorStateList(R.color.brown_shade)
                binding.fabLanguageChange.backgroundTintList = getColorStateList(R.color.brown_shade)
            }
        }
    }

    /* Author: Dhruv Patel
    *  Description: Changes the language of the app
    * */
    private fun changeLanguage() {
        languageChangeFragment?.show(supportFragmentManager, languageChangeFragment!!.tag);
    }
    private fun updateBottomNav(){
        val menu = binding.bottomNavigationView.menu;
        menu.findItem(R.id.workouts).title = getString(R.string.workouts);
        menu.findItem(R.id.fitnessHub).title = getString(R.string.fitness_hub);
        menu.findItem(R.id.settings).title = getString(R.string.settings)
    }
    private fun setLocale(languageCode: String): Context {
        val locale = Locale(languageCode)
        Log.e("Language:",languageCode)
        Locale.setDefault(locale)

        val configuration = this.resources.configuration
        configuration.setLocale(locale)

        val context:Context = createConfigurationContext(configuration)

        createConfigurationContext(configuration)
        resources.updateConfiguration(configuration,resources.displayMetrics)

        val langPref = getSharedPreferences("ChangeLang", Context.MODE_PRIVATE);
        val editor = langPref.edit();
        editor.putString("app_lang", languageCode);
        editor.apply();
        return context
    }
    private fun loadLocale()
    {
        val loadLangPref = getSharedPreferences("ChangeLang", Context.MODE_PRIVATE);
        val language = loadLangPref.getString("app_lang","");

        if (language != null) {
            setLocale(language)
            Log.e("Language:",language)
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}