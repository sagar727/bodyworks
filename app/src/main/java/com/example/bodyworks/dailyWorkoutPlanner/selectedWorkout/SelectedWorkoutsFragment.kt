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
        floatingActionButton = binding.floatingActionButton

        floatingActionButton.setOnClickListener{
            Toast.makeText(context, "Show Help", Toast.LENGTH_SHORT).show()
            // TODO: Implement Reset Functionality Here
        }

        // This will set today's day of week to the text view
        // TODO: Setting Today's Day Here
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
        txtViewDay.text = dayOfWeekText

        // RecyclerView Code
        // TODO: Set selected exercises here and pass it to recyclerview
        rcViewSelectedExercises.adapter = ExerciseDayDataAdapter(listOf(
            DayExerciseModel("Monday:",
                listOf("Exercise1","Exercise2")
            ),
            DayExerciseModel("Tuesday:",
                listOf("Exercise1","Exercise2")
            ),
            DayExerciseModel("Wednesday:",
                listOf("Exercise1","Exercise2")
            ),
            DayExerciseModel("Thursday:",
                listOf("Exercise1","Exercise2")
            ),
            DayExerciseModel("Friday:",
                listOf("Exercise1","Exercise2")
            ),
            DayExerciseModel("Saturday:",
                listOf("Exercise1","Exercise2")
            ),
            DayExerciseModel("Sunday:",
                listOf("Exercise1","Exercise2")
            )
        ))
        rcViewSelectedExercises.layoutManager = LinearLayoutManager(context)

        return binding.root
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