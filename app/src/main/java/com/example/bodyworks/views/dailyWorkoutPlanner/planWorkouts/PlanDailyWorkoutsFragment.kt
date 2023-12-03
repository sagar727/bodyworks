package com.example.bodyworks.views.dailyWorkoutPlanner.planWorkouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bodyworks.R
import com.example.bodyworks.databinding.FragmentPlanDailyWorkoutsBinding


/**
 * Author: Ketul Chauhan
 * Date: October 25, 2023
 */
class PlanDailyWorkoutsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentPlanDailyWorkoutsBinding
    private lateinit var rcViewPlanNew: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlanDailyWorkoutsBinding.inflate(inflater, container, false)
        rcViewPlanNew = binding.rcViewPlanNew

        val exerciseList = resources.getStringArray(R.array.activity_array)
        val days = listOf(
            EachDayModel("Monday", false, exerciseList),
            EachDayModel("Tuesday", false, exerciseList),
            EachDayModel("Wednesday", false, exerciseList),
            EachDayModel("Thursday", false, exerciseList),
            EachDayModel("Friday", false, exerciseList),
            EachDayModel("Saturday", false, exerciseList),
            EachDayModel("Sunday", false, exerciseList)
        )
        rcViewPlanNew.adapter = PlanDailyWorkoutAdapter(requireContext(), days)
        rcViewPlanNew.layoutManager = LinearLayoutManager(context)
        return binding.root
    }
}