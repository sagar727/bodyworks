package com.example.bodyworks.views.dailyWorkoutPlanner.selectedWorkout

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bodyworks.R
import com.example.bodyworks.views.dailyWorkoutPlanner.planWorkouts.EachDayViewAdapter
import com.example.bodyworks.database.DatabaseHelper
import com.example.bodyworks.databinding.FragmentPlanDailyWorkoutsBinding
import com.example.bodyworks.databinding.FragmentSelectedWorkoutsBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Calendar


/**
 * Author: Ketul Chauhan
 * Date: October 25, 2023
 */
class SelectedWorkoutsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentSelectedWorkoutsBinding
    private lateinit var txtViewDay: TextView
    private lateinit var txtViewExercise: TextView
    private lateinit var rcViewSelectedExercises: RecyclerView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectedWorkoutsBinding.inflate(inflater, container, false)
        sharedPreferences = this.let { PreferenceManager.getDefaultSharedPreferences(requireContext()) }!!
        val themeColor = sharedPreferences.getString("Current Theme", getString(R.string.red))
        val color =  changeTheme(themeColor)

        binding.txtViewTodayExercise.setTextColor(requireActivity().getColor(color))
        binding.materialDivider.dividerColor = requireActivity().getColor(color)
        txtViewDay = binding.txtViewDay
        txtViewExercise = binding.txtViewExercise
        rcViewSelectedExercises = binding.rcViewSelectedExercises
        // This will set today's day of week to the text view
        val db = DatabaseHelper(requireContext())
        val dayOfWeekText = when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> "Monday"
            Calendar.TUESDAY -> "Tuesday"
            Calendar.WEDNESDAY -> "Wednesday"
            Calendar.THURSDAY -> "Thursday"
            Calendar.FRIDAY -> "Friday"
            Calendar.SATURDAY -> "Saturday"
            Calendar.SUNDAY -> "Sunday"
            else -> getString(R.string.error);
        }
        val todaysExercise = db.getExerciseSelectedList(dayOfWeekText)
        txtViewDay.text = dayOfWeekText
        txtViewExercise.text = todaysExercise

        // RecyclerView Code
        val weekDays = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        val exerciseListFetched = mutableListOf<DayExerciseModel>()
        weekDays.forEach {
            val allExercisesThatWasSelected = db.getExerciseSelectedList(it)
            exerciseListFetched.add(DayExerciseModel("$it:",allExercisesThatWasSelected.split(",")))
        }

        rcViewSelectedExercises.adapter = ExerciseDayDataAdapter(requireContext(),exerciseListFetched)
        rcViewSelectedExercises.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val db = DatabaseHelper(requireContext())
        val dayOfWeekText = when (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> "Monday"
            Calendar.TUESDAY -> "Tuesday"
            Calendar.WEDNESDAY -> "Wednesday"
            Calendar.THURSDAY -> "Thursday"
            Calendar.FRIDAY -> "Friday"
            Calendar.SATURDAY -> "Saturday"
            Calendar.SUNDAY -> "Sunday"
            else -> getString(R.string.error);
//            Calendar.MONDAY -> getString(R.string.monday);
//            Calendar.TUESDAY -> getString(R.string.tuesday);
//            Calendar.WEDNESDAY -> getString(R.string.wednesday);
//            Calendar.THURSDAY -> getString(R.string.thursday);
//            Calendar.FRIDAY -> getString(R.string.friday);
//            Calendar.SATURDAY -> getString(R.string.saturday);
//            Calendar.SUNDAY -> getString(R.string.sunday);
//            else -> getString(R.string.error);
        }
        val todaysExercise = db.getExerciseSelectedList(dayOfWeekText)
        txtViewDay.text = dayOfWeekText
        txtViewExercise.text = todaysExercise

        val weekDays = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
//        val weekDays = arrayOf(getString(R.string.monday), getString(R.string.tuesday), getString(R.string.wednesday), getString(R.string.thursday), getString(R.string.friday), getString(R.string.saturday), getString(R.string.sunday))
        val exerciseListFetched = mutableListOf<DayExerciseModel>()
        weekDays.forEach {
            val allExercisesThatWasSelected = db.getExerciseSelectedList(it)
            exerciseListFetched.add(DayExerciseModel("$it:",allExercisesThatWasSelected.split(",")))
        }

        rcViewSelectedExercises.adapter = ExerciseDayDataAdapter(requireContext(),exerciseListFetched)
        rcViewSelectedExercises.adapter?.notifyDataSetChanged()
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