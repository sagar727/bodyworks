package com.example.bodyworks

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.example.bodyworks.databinding.FragmentFitnessHubBinding
import com.example.bodyworks.views.bmiActivity.BmiActivity
import com.example.bodyworks.views.calorieTracker.CalorieTrackerActivity
import com.example.bodyworks.views.dailyWorkoutPlanner.DailyWorkoutPlanner
import com.example.bodyworks.views.dietPlans.DietMealPlans
import com.example.bodyworks.views.nutritionalValues.FoodNutritionalValues
import com.example.bodyworks.views.sleepReminder.SleepReminder
import com.example.bodyworks.views.waterReminder.WaterReminderActivity
import com.example.bodyworks.views.weightTracker.WeightTracker
import com.example.bodyworks.views.workoutReminder.WorkoutReminder


class FitnessHubFragment : Fragment() {

    private lateinit var binding: FragmentFitnessHubBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFitnessHubBinding.inflate(inflater, container, false)

        sharedPreferences =
            this.let { PreferenceManager.getDefaultSharedPreferences(requireContext()) }!!
        val themeColor = sharedPreferences.getString("Current Theme", getString(R.string.red))
        val color = changeTheme(themeColor)

        binding.bmiLL.setBackgroundColor(requireActivity().getColor(color))
        binding.dailyWorkourPlanner.setBackgroundColor(requireActivity().getColor(color))
        binding.weightTracker.setBackgroundColor(requireActivity().getColor(color))
        binding.waterReminder.setBackgroundColor(requireActivity().getColor(color))
        binding.calorieTracker.setBackgroundColor(requireActivity().getColor(color))
        binding.sleepReminder.setBackgroundColor(requireActivity().getColor(color))
        binding.dietMealPlanLL.setBackgroundColor(requireActivity().getColor(color))
        binding.workoutReminder.setBackgroundColor(requireActivity().getColor(color))
        binding.nutritionalValueLL.setBackgroundColor(requireActivity().getColor(color))

        binding.bmiLL.setOnClickListener {
            val intent = Intent(context, BmiActivity::class.java)
            startActivity(intent)
        }

        binding.dailyWorkourPlanner.setOnClickListener {
            val intent = Intent(context, DailyWorkoutPlanner::class.java)
            startActivity(intent)
        }

        binding.weightTracker.setOnClickListener {
            val intent = Intent(context, WeightTracker::class.java)
            startActivity(intent)
        }

        binding.waterReminder.setOnClickListener {
            val intent = Intent(context, WaterReminderActivity::class.java)
            startActivity(intent)
        }

        binding.calorieTracker.setOnClickListener {
            val intent = Intent(context, CalorieTrackerActivity::class.java)
            startActivity(intent)
        }

        binding.sleepReminder.setOnClickListener {
            val intent = Intent(context, SleepReminder::class.java)
            startActivity(intent)
        }

        binding.dietMealPlanLL.setOnClickListener {
            val intent = Intent(context, DietMealPlans::class.java)
            startActivity(intent)
        }

        binding.workoutReminder.setOnClickListener {
            val intent = Intent(context, WorkoutReminder::class.java)
            startActivity(intent)
        }

        binding.nutritionalValueLL.setOnClickListener {
            val intent = Intent(context, FoodNutritionalValues::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    private fun changeTheme(themeColor: String?): Int {
        when (themeColor) {
            "Red" -> {
                return R.color.primary
            }

            "Orange" -> {
                return R.color.orange
            }

            "Brown" -> {
                return R.color.brown
            }
        }
        return 0
    }
}