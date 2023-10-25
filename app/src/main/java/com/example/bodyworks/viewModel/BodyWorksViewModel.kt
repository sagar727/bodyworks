package com.example.bodyworks.viewModel

import android.content.Context
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

    fun addAbdomenWorkoutData(context: Context,workoutData: WorkoutDataModel,tableName: String){
        val db = DatabaseHelper(context)
        val count = db.countTableRow(tableName)
        if(count == 0){
            db.addAbdomenWorkoutData(workoutData)
        }
    }

    fun addBackWorkoutData(context: Context,workoutData: WorkoutDataModel,tableName: String){
        val db = DatabaseHelper(context)
        val count = db.countTableRow(tableName)
        if(count == 0){
            db.addBackWorkoutData(workoutData)
        }
    }

    fun addBicepWorkoutData(context: Context,workoutData: WorkoutDataModel,tableName: String){
        val db = DatabaseHelper(context)
        val count = db.countTableRow(tableName)
        if(count == 0){
            db.addBicepWorkoutData(workoutData)
        }
    }

    fun addCardioWorkoutData(context: Context,workoutData: WorkoutDataModel,tableName: String){
        val db = DatabaseHelper(context)
        val count = db.countTableRow(tableName)
        if(count == 0){
            db.addCardioWorkoutData(workoutData)
        }
    }

    fun addChestWorkoutData(context: Context,workoutData: WorkoutDataModel,tableName: String){
        val db = DatabaseHelper(context)
        val count = db.countTableRow(tableName)
        if(count == 0){
            db.addChestWorkoutData(workoutData)
        }
    }

    fun addLegWorkoutData(context: Context,workoutData: WorkoutDataModel,tableName: String){
        val db = DatabaseHelper(context)
        val count = db.countTableRow(tableName)
        if(count == 0){
            db.addLegWorkoutData(workoutData)
        }
    }

    fun addShoulderWorkoutData(context: Context,workoutData: WorkoutDataModel,tableName: String){
        val db = DatabaseHelper(context)
        val count = db.countTableRow(tableName)
        if(count == 0){
            db.addShoulderWorkoutData(workoutData)
        }
    }

    fun addTricepWorkoutData(context: Context,workoutData: WorkoutDataModel,tableName: String){
        val db = DatabaseHelper(context)
        val count = db.countTableRow(tableName)
        if(count == 0){
            db.addTricepWorkoutData(workoutData)
        }
    }
}

