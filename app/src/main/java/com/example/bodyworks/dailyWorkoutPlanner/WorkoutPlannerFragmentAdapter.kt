package com.example.bodyworks.dailyWorkoutPlanner

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bodyworks.dailyWorkoutPlanner.planWorkouts.PlanDailyWorkoutsFragment
import com.example.bodyworks.dailyWorkoutPlanner.selectedWorkout.SelectedWorkoutsFragment

class WorkoutPlannerFragmentAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if(position == 0)
            SelectedWorkoutsFragment()
        else
            PlanDailyWorkoutsFragment()
    }
}