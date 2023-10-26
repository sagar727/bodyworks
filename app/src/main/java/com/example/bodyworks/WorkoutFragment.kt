package com.example.bodyworks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bodyworks.adapter.ParentWorkoutAdapter
import com.example.bodyworks.databinding.ActivityMainBinding
import com.example.bodyworks.model.ChildSubWorkoutModel
import com.example.bodyworks.model.ParentWorkoutModel
import java.util.Calendar

/**Author: Dhruv Patel
 * Description: user can choose workouts from cards
 **/
class WorkoutFragment : Fragment() {
    private lateinit var layoutBind: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val parentWorkoutRecyclerView = view.findViewById<RecyclerView>(R.id.parentRecyclerview)
        val parentItemAdapter = ParentWorkoutAdapter(parentItemList())
        parentWorkoutRecyclerView.adapter = parentItemAdapter

        //code for greeting text
        val greetingsTextView = view.findViewById<TextView>(R.id.greetingsTxt);
        greetingsTextView.text = myGreetingMessage();
    }

    //Change greeting message according to hour
    fun myGreetingMessage(): String {
        val cal = Calendar.getInstance();
        val timeofDay = cal.get(Calendar.HOUR_OF_DAY);

        return when (timeofDay) {
            in 0..11 -> "Good Morning!"
            in 12..15 -> "Good Afternoon!"
            in 16..24 -> "Good Evening!"
            else -> "Hello, How are you today?"
        }
    }
    private fun parentItemList(): MutableList<ParentWorkoutModel> {
        val itemList : MutableList<ParentWorkoutModel> = ArrayList<ParentWorkoutModel>()

        itemList.add(ParentWorkoutModel("Abdomen", abdomenItemList()))
        itemList.add(ParentWorkoutModel("Back Exercise", backExItemList()))
        itemList.add(ParentWorkoutModel("Biceps", bicepsForearmItemList()))
        itemList.add(ParentWorkoutModel("Cardio", cardioItemList()))
        itemList.add(ParentWorkoutModel("Chest", chestItemList()))
        itemList.add(ParentWorkoutModel("Leg", legItemList()))
        itemList.add(ParentWorkoutModel("Shoulder", shoulderItemList()))
        itemList.add(ParentWorkoutModel("Triceps", tricepsItemList()))

        return itemList
    }

    private fun tricepsItemList(): MutableList<ChildSubWorkoutModel> {
        val childItemList: MutableList<ChildSubWorkoutModel> = ArrayList<ChildSubWorkoutModel>()

        childItemList.add(ChildSubWorkoutModel(R.drawable.skullcrusher, "Skull Crusher"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.dip, "Dip"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.standingoverheadcabeltricepsextension, "Standing Overhead Cabel Triceps Extension"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.cabelropetriceppushdown, "Cabel Rope Tricep Pushdown"))

        return childItemList;
    }

    private fun shoulderItemList(): MutableList<ChildSubWorkoutModel> {
        val childItemList: MutableList<ChildSubWorkoutModel> = ArrayList<ChildSubWorkoutModel>()

        childItemList.add(ChildSubWorkoutModel(R.drawable.dumbbellfrontraise, "Dumbell Front Raise"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.dumbbelllateralraise, "Dumbbell lateral Raise"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.seatedmilitarypress, "Seated Military Press"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.cabellateralraise, "Cabel Lateral raise"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.overheadpress, "Overhead Press"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.facepull, "Face Pull"))

        return childItemList;
    }

    private fun legItemList(): MutableList<ChildSubWorkoutModel> {
        val childItemList: MutableList<ChildSubWorkoutModel> = ArrayList<ChildSubWorkoutModel>()

        childItemList.add(ChildSubWorkoutModel(R.drawable.squats, "Squats"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.legpress, "Leg Press"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.hacksquat, "Hack Squats"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.legextension, "Leg Extension"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.legcurl, "Leg Curl"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.lunges, "Lunges"))

        return childItemList;
    }

    private fun chestItemList(): MutableList<ChildSubWorkoutModel> {
        val childItemList: MutableList<ChildSubWorkoutModel> = ArrayList<ChildSubWorkoutModel>()

        childItemList.add(ChildSubWorkoutModel(R.drawable.dumbbellbenchpress, "Dumbbell Bench Press"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.inclinebenchpress, "Incline Bench Press"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.machinechestpress, "Machine Chest Press"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.pushups, "Push-up"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.dip, "Dip"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.chestfly, "Chest Fly"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.machinefly, "Machine Fy"))

        return childItemList;
    }

    private fun cardioItemList(): MutableList<ChildSubWorkoutModel> {
        val childItemList: MutableList<ChildSubWorkoutModel> = ArrayList<ChildSubWorkoutModel>()

        childItemList.add(ChildSubWorkoutModel(R.drawable.runontreadmill, "Run on the treadmill"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.pedalawayonanelliptical, "Pedal away on an elliptical"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.climbstepsonastairmachine, "Clim steps on a stair machine"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.burpee, "Burpee"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.jumpingjacks, "Jumping Jacks"))

        return childItemList;
    }

    private fun bicepsForearmItemList(): MutableList<ChildSubWorkoutModel> {
        val childItemList: MutableList<ChildSubWorkoutModel> = ArrayList<ChildSubWorkoutModel>()

        childItemList.add(ChildSubWorkoutModel(R.drawable.dumbellcurls, "Dumbbell Curl"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.inclinedumbellcurls, "Incline Dumbbell Curl"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.hammercurls, "Hammer Curl"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.cabelcurls, "Cabel Curl"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.barbellcurls, "Barbell Curl"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.concentrationcurls, "Concentration Curl"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.palmsupwristcurls, "Palms-up Wrist Curl"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.palmdownwristcurls, "Palms-down Wrist Curl"))

        return childItemList;
    }

    private fun backExItemList(): MutableList<ChildSubWorkoutModel> {
        val childItemList: MutableList<ChildSubWorkoutModel> = ArrayList<ChildSubWorkoutModel>()

        childItemList.add(ChildSubWorkoutModel(R.drawable.deadlift, "Deadlift"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.singlearmdumbbellrow, "Single-Arm Dumbbell Row"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.inclinedumbbellrow, "Incline Rows"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.pullups, "Pull-ups"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.seatedcabelrow, "Seated Cabel Row"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.latpulldown, "Lat Pull-Downs"))

        return childItemList;
    }

    // Method to pass the arguments for the elements of child RecyclerView
    private fun abdomenItemList(): MutableList<ChildSubWorkoutModel> {
        val childItemList: MutableList<ChildSubWorkoutModel> = ArrayList<ChildSubWorkoutModel>()

        childItemList.add(ChildSubWorkoutModel(R.drawable.plank, "Plank"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.legraise, "Leg Raise"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.bicyclecrunch, "Bicycle Crunch"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.mountainclimber, "Mountain Climber"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.russiantwist, "Russian Twists"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.crunches, "Crunches"))
        childItemList.add(ChildSubWorkoutModel(R.drawable.hanginglegraise, "Hanging Leg Raise"))

        return childItemList;
    }
}