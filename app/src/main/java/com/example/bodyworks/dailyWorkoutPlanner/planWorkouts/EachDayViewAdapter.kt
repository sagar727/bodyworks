package com.example.bodyworks.dailyWorkoutPlanner.planWorkouts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bodyworks.R
import com.example.bodyworks.dailyWorkoutPlanner.selectedWorkout.DayExerciseModel
import com.example.bodyworks.database.DatabaseHelper
import com.google.android.material.card.MaterialCardView

class EachDayViewAdapter(
    val context: Context,
    private var day: String,
    private val exerciseList: Array<String>
) :
    RecyclerView.Adapter<EachDayViewAdapter.EachDayViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EachDayViewHolder {
        return EachDayViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.each_day_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    override fun onBindViewHolder(holder: EachDayViewHolder, position: Int) {
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
}