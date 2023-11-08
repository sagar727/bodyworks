package com.example.bodyworks.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bodyworks.database.DatabaseHelper
import com.example.bodyworks.model.CalorieTracker

class CalorieViewModel : ViewModel() {
    val totalCalories = MutableLiveData<Double>()

    fun getCalorieTrackerData(context: Context): ArrayList<CalorieTracker> {
        val db = DatabaseHelper(context)
        var data = ArrayList<CalorieTracker>()
        val count = db.countTableRow("calorieTracker")
        if (count != 0) {
            data = db.getCalorieTrackerData()
        }
        return data
    }

    fun getCurrentCalorie(context: Context, dt: String): ArrayList<CalorieTracker> {
        val db = DatabaseHelper(context)
        var data = ArrayList<CalorieTracker>()
        val count = db.countTableRow("calorieTracker")
        if (count != 0) {
            data = db.getCurrentCalorie(dt)
        }
        return data
    }

    fun updateCalorie(context: Context, calorieTracker: CalorieTracker){
        val db = DatabaseHelper(context)
        totalCalories.value = calorieTracker.calories
        db.updateCalorie(calorieTracker)
    }

    fun insertCalorie(context: Context, calorieTracker: CalorieTracker){
        val db = DatabaseHelper(context)
        totalCalories.value = calorieTracker.calories
        db.insertCalorie(calorieTracker)
    }
}