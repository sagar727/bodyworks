package com.example.bodyworks.views.dailyWorkoutPlanner.selectedWorkout

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bodyworks.R

/**
 * Author: Ketul Chauhan
 * Date: October 25, 2023
 */
class ExerciseDayDataAdapter(val context: Context, private val dayExerciseList : List<DayExerciseModel>):
    RecyclerView.Adapter<ExerciseDayDataAdapter.ExerciseDayDataViewHolder>() {

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseDayDataViewHolder {
        return ExerciseDayDataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.each_day_selected_exercise, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ExerciseDayDataViewHolder, position: Int) {
        sharedPreferences = this.let { PreferenceManager.getDefaultSharedPreferences(context) }!!
        val themeColor = sharedPreferences.getString("Current Theme", context.getString(R.string.red))
        val color =  changeTheme(themeColor)
        holder.txtViewDayOfWeek.setTextColor(context.getColor(color))
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