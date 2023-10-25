package com.example.bodyworks.dailyWorkoutPlanner.selectedWorkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bodyworks.R
import com.example.bodyworks.dailyWorkoutPlanner.planWorkouts.EachDayViewAdapter
import com.example.bodyworks.database.DatabaseHelper
import com.example.bodyworks.databinding.FragmentPlanDailyWorkoutsBinding
import com.example.bodyworks.databinding.FragmentSelectedWorkoutsBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Calendar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SelectedWorkoutsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectedWorkoutsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentSelectedWorkoutsBinding
    private lateinit var txtViewDay: TextView
    private lateinit var txtViewExercise: TextView
    private lateinit var rcViewSelectedExercises: RecyclerView
    private lateinit var floatingActionButton: ExtendedFloatingActionButton
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectedWorkoutsBinding.inflate(inflater, container, false)
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
            else -> "ERROR"
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

        rcViewSelectedExercises.adapter = ExerciseDayDataAdapter(exerciseListFetched)
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
            else -> "ERROR"
        }
        val todaysExercise = db.getExerciseSelectedList(dayOfWeekText)
        txtViewDay.text = dayOfWeekText
        txtViewExercise.text = todaysExercise

        val weekDays = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
        val exerciseListFetched = mutableListOf<DayExerciseModel>()
        weekDays.forEach {
            val allExercisesThatWasSelected = db.getExerciseSelectedList(it)
            exerciseListFetched.add(DayExerciseModel("$it:",allExercisesThatWasSelected.split(",")))
        }

        rcViewSelectedExercises.adapter = ExerciseDayDataAdapter(exerciseListFetched)
        rcViewSelectedExercises.adapter?.notifyDataSetChanged()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SelectedWorkoutsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SelectedWorkoutsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}