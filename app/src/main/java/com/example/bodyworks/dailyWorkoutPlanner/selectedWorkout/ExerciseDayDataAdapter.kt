package com.example.bodyworks.dailyWorkoutPlanner.selectedWorkout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bodyworks.R

/**
 * Author: Ketul Chauhan
 * Date: October 25, 2023
 */
class ExerciseDayDataAdapter(private val dayExerciseList : List<DayExerciseModel>):
    RecyclerView.Adapter<ExerciseDayDataAdapter.ExerciseDayDataViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseDayDataViewHolder {
        return ExerciseDayDataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.each_day_selected_exercise, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExerciseDayDataViewHolder, position: Int) {
        holder.txtViewDayOfWeek.text = dayExerciseList[position].day
        holder.txtViewExercisesSelected.text = dayExerciseList[position].exerciseList.joinToString(separator = ", ")
    }

    override fun getItemCount(): Int {
        return dayExerciseList.size
    }

    class ExerciseDayDataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtViewDayOfWeek: TextView = itemView.findViewById<TextView>(R.id.txtViewDayOfWeek)
        val txtViewExercisesSelected: TextView = itemView.findViewById<TextView>(R.id.txtViewExercisesSelected)
    }
}