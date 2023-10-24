package com.example.bodyworks.dailyWorkoutPlanner.planWorkouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bodyworks.databinding.FragmentFitnessHubBinding
import com.example.bodyworks.databinding.FragmentPlanDailyWorkoutsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlanDailyWorkoutsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlanDailyWorkoutsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentPlanDailyWorkoutsBinding
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var rcViewPlanNew: RecyclerView

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
        binding = FragmentPlanDailyWorkoutsBinding.inflate(inflater, container, false)
        rcViewPlanNew = binding.rcViewPlanNew
        val exerciseList = listOf("Abdomen", "Back Exercise", "Biceps & Forearm", "Cardio", "Chest", "Leg", "Shoulder", "Triceps")
        val days = listOf(EachDayModel("Monday", false, exerciseList),
            EachDayModel("Tuesday", false, exerciseList),
            EachDayModel("Wednesday", false, exerciseList),
            EachDayModel("Thursday", false, exerciseList),
            EachDayModel("Friday", false, exerciseList),
            EachDayModel("Saturday", false, exerciseList),
            EachDayModel("Sunday", false, exerciseList))
        rcViewPlanNew.adapter = PlanDailyWorkoutAdapter(days)
        rcViewPlanNew.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PlanDailyWorkoutsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlanDailyWorkoutsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}