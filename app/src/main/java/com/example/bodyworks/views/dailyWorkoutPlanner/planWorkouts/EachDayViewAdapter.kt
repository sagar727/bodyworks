package com.example.bodyworks.views.dailyWorkoutPlanner.planWorkouts

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bodyworks.R
import com.example.bodyworks.views.dailyWorkoutPlanner.selectedWorkout.DayExerciseModel
import com.example.bodyworks.database.DatabaseHelper
import com.google.android.material.card.MaterialCardView

/**
 * Author: Ketul Chauhan
 * Date: October 25, 2023
 */
class EachDayViewAdapter(
    val context: Context,
    private var day: String,
    private val exerciseList: Array<String>
) :
    RecyclerView.Adapter<EachDayViewAdapter.EachDayViewHolder>() {

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EachDayViewHolder {
        return EachDayViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.each_day_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    override fun onBindViewHolder(holder: EachDayViewHolder, position: Int) {

        sharedPreferences = this.let { PreferenceManager.getDefaultSharedPreferences(context) }!!
        val themeColor = sharedPreferences.getString("Current Theme", context.getString(R.string.red))
        val color =  changeTheme(themeColor)
        holder.txtViewExercise.setTextColor(context.getColor(color))
        holder.selectionIcon.imageTintList = context.getColorStateList(color)
        holder.customChip.strokeColor = context.getColor(color)
        //TODO: Fetch Query
        val db = DatabaseHelper(context)
        val weekDays =
            arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        var exerciseListFetched = mutableListOf<String>()
        weekDays.forEach {
            if(it == day){
                val allExercisesThatWasSelected = db.getExerciseSelectedList(it)
                exerciseListFetched.addAll(allExercisesThatWasSelected.split(","))
            }
        }

        if (exerciseListFetched.size > 0) {
            if (exerciseListFetched.contains(exerciseList[position])) {
                holder.selectionIcon.visibility = View.VISIBLE
            } else {
                holder.selectionIcon.visibility = View.GONE
            }
        }

        holder.txtViewExercise.text = exerciseList[position]
        holder.customChip.setOnClickListener {
            if (holder.selectionIcon.visibility == View.GONE) {

                holder.selectionIcon.visibility = View.VISIBLE

                //Database insert to insert planned workouts
                val db = DatabaseHelper(context)
                db.addSelectedExercisesToDB(day, exerciseList[position])
            } else {
                holder.selectionIcon.visibility = View.GONE

                //Database update to update planned workouts
                val db = DatabaseHelper(context)
                db.updateExerciseFromDB(day, exerciseList[position])
            }
        }
    }

    class EachDayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val customChip: MaterialCardView = itemView.findViewById(R.id.customChip)
        val selectionIcon: ImageView = itemView.findViewById(R.id.selectionIcon)
        val txtViewExercise: TextView = itemView.findViewById(R.id.txtViewExercise)
    }

    private fun changeTheme(themeColor: String?): Int {
        when (themeColor) {
            "Red" -> {
                return (R.color.primary)
            }
            "Orange" -> {
                return (R.color.orange)
            }
            "Brown" -> {
                return (R.color.brown)
            }
        }
        return 0
    }
}