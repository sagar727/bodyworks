package com.example.bodyworks

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bodyworks.dailyWorkoutPlanner.DailyWorkoutPlanner
import com.example.bodyworks.databinding.FragmentFitnessHubBinding
import com.example.bodyworks.waterReminder.WaterReminderActivity


class FitnessHubFragment : Fragment() {

    private lateinit var binding: FragmentFitnessHubBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // TODO: ADD Other Features Code Here Like BMI, Daily Workout Planner, ETC
        // Inflate the layout for this fragment
        binding = FragmentFitnessHubBinding.inflate(inflater,container,false)

        binding.bmiLL.setOnClickListener {
            val intent = Intent(context,BmiActivity::class.java)
            startActivity(intent)
        }

        binding.dailyWorkourPlanner.setOnClickListener {
            val intent = Intent(context, DailyWorkoutPlanner::class.java)
            startActivity(intent)
        }

        binding.weightTracker.setOnClickListener {
            val intent = Intent(context,WeightTracker::class.java)
            startActivity(intent)
        }

        binding.waterReminder.setOnClickListener{
            val intent = Intent(context, WaterReminderActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}