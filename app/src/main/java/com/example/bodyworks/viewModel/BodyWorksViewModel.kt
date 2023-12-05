package com.example.bodyworks.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bodyworks.database.DatabaseHelper
import com.example.bodyworks.model.User
import com.example.bodyworks.model.WeightTracker
import com.example.bodyworks.model.WorkoutDataModel
import kotlin.math.roundToInt

class BodyWorksViewModel : ViewModel() {
    var bmi = MutableLiveData<String>()

    init {
        bmi.value = "0"
    }

    //code to calculate bmi
    fun calculateBmiInMetric(context: Context, wt: Double, ht: Double) {
        val cmToMeter = ht / 100
        val result = ((wt / (cmToMeter * cmToMeter)) * 100.0).roundToInt() / 100.0
        bmi.value = result.toString()
        val user = User(wt.toString(), result.toString())
        val db = DatabaseHelper(context)
        val count = db.countTableRow("user")
        if (count > 0) {
            db.updateUserData(user)
        } else {
            db.addUserData(user)
        }
    }

    //code to retrieve bmi
    fun getBMI(context: Context) {
        val db = DatabaseHelper(context)
        bmi.value = db.getBMI()
    }

    fun generateDropDownNumbrs(startNum: Int, endNum: Int): MutableList<Int> {
        val inchArray = intArrayOf().toMutableList()
        var i = startNum
        while (i < endNum) {
            inchArray.add(i)
            i++
        }
        return inchArray
    }

    fun calculateBmiInImperial(context: Context, wt: Double, ft: Int, inch: Int) {
        val ftToInch = ft * 12.5
        val totalInch = ftToInch + inch
        val result = ((703 * wt / (totalInch * totalInch)) * 100.0).roundToInt() / 100.0
        bmi.value = result.toString()
        val lbToKg = (wt / 2.2046).roundToInt() / 100.0
        val user = User(lbToKg.toString(), result.toString())
        val db = DatabaseHelper(context)
        val count = db.countTableRow("user")
        if (count > 0) {
            db.updateUserData(user)
        } else {
            db.addUserData(user)
        }
    }

    fun addWeightTrackerData(context: Context, wt: Double, date: Long, isMetric: Boolean) {
        val db = DatabaseHelper(context)
        var kg: Double
        var lb: Double
        if(isMetric){
            kg = wt
            lb = wt / 2.2
        }else{
            lb = wt
            kg = wt * 2.2
        }
        db.addWeightData(WeightTracker(kg, lb, date))
    }

    fun addWorkoutData(
        context: Context,
        workoutName: Array<String>,
        muscle: Array<String>,
        video: Array<String>,
        thumbnail: Array<String>,
        tableName: String,
    ):Boolean {
        val iteration = workoutName.size
        val db = DatabaseHelper(context)
        val count = db.countTableRow(tableName)
        if (count != iteration) {
            var i = 0;
            var workoutData: WorkoutDataModel? = null
            while (i < iteration) {
                workoutData = WorkoutDataModel(workoutName[i], video[i], thumbnail[i], muscle[i])
                i++
                db.addWorkoutData(workoutData, tableName)
            }
        }
        return db.countTableRow("triceps") != 0
    }

    //to get weight tracker data
    fun getWeightTrackerData(context: Context): ArrayList<WeightTracker> {
        val db = DatabaseHelper(context)
        var data = ArrayList<WeightTracker>()
        val count = db.countTableRow("weightTracker")
        if (count != 0) {
            data = db.getWeightTrackerData()
        }
        return data
    }
}

