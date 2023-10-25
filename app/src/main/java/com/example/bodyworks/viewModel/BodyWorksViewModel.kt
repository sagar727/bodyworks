package com.example.bodyworks.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bodyworks.database.DatabaseHelper
import com.example.bodyworks.model.User
import com.example.bodyworks.model.WorkoutDataModel
import kotlin.math.roundToInt

class BodyWorksViewModel: ViewModel() {
    var bmi = MutableLiveData<String>()

    init {
        bmi.value = "0"
    }

    fun calculateBmiInMetric(context: Context,wt: Double, ht: Double) {
        val cmToMeter = ht / 100
        val result = ((wt / (cmToMeter * cmToMeter)) * 100.0).roundToInt() / 100.0
        bmi.value = result.toString()
        val user = User(wt.toString(),result.toString())
        val db = DatabaseHelper(context)
        val count = db.countTableRow("user")
        if(count > 0){
            db.updateUserData(user)
        }else{
            db.addUserData(user)
        }
    }

    fun getBMI(context: Context){
        val db = DatabaseHelper(context)
        bmi.value = db.getBMI()
    }

    fun calculateBmiInImperial(context: Context,wt: Double, ft: Int, inch: Int){
        val ftToInch = ft * 12.5
        val totalInch = ftToInch + inch
        val result = ((703 * wt / (totalInch * totalInch)) * 100.0).roundToInt() / 100.0
        bmi.value = result.toString()
        val lbToKg = (wt / 2.2046).roundToInt() / 100.0
        val user = User(lbToKg.toString(),result.toString())
        val db = DatabaseHelper(context)
        val count = db.countTableRow("user")
        if(count > 0){
            db.updateUserData(user)
        }else{
            db.addUserData(user)
        }
    }

    fun addActivityData(context: Context, activityData: Array<String>){
        val db = DatabaseHelper(context)
        val count = db.countTableRow("activity")
        if(count == 0){
            db.addActivityData(activityData)
        }
    }

    fun addWorkoutData(context: Context,workoutData: WorkoutDataModel,tableName: String,totalCount:Int){
        val db = DatabaseHelper(context)
        val count = db.countTableRow(tableName)
        if(totalCount !== count){
            db.addWorkoutData(workoutData,tableName)
            val data = db.displayAll(tableName)
        }
    }

    fun getWorkOutData(context: Context, tableName: String){
        val db = DatabaseHelper(context)
        val count = db.countTableRow(tableName)
        if(count > 0){
            val data = db.displayAll(tableName)
            Log.i("workdata",data.toString())
        }
    }
}

