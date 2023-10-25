package com.example.bodyworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bodyworks.databinding.ActivityMainBinding
import com.example.bodyworks.model.WorkoutDataModel
import com.example.bodyworks.viewModel.BodyWorksViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var bodyworksVM: BodyWorksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bodyworksVM = ViewModelProvider(this).get(BodyWorksViewModel::class.java)

        val activityList: Array<String> = resources.getStringArray(R.array.activity_array)
        bodyworksVM.addActivityData(this, activityList)

        insertWorkoutData()

        //Setting Workout page as home page
        replaceFragment(WorkoutFragment())
        //Changing fragment as per selection
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.workouts -> replaceFragment(WorkoutFragment())
                R.id.fitnessHub -> replaceFragment(FitnessHubFragment())
                else -> {

                }
            }
            true
        }

        binding.button2.setOnClickListener{
            val intent = Intent(this, WorkoutActivity :: class.java)
            startActivity(intent)
        }
    }


    private fun insertWorkoutData() {
        val abdomenWorkOutName: Array<String> =
            resources.getStringArray(R.array.abdomen_workout_name)
        val abdomenMuscle: Array<String> = resources.getStringArray(R.array.abdomen_Muscle)
        val abdomenVideo: Array<String> = resources.getStringArray(R.array.abdomen_video)
        val abdomenThumbnail: Array<String> = resources.getStringArray(R.array.abdomen_thumbnail)

        bodyworksVM.addWorkoutData(
            this,
            abdomenWorkOutName,
            abdomenMuscle,
            abdomenVideo,
            abdomenThumbnail,
            "abdomen"
        )

        val backWorkOutName: Array<String> = resources.getStringArray(R.array.back_workout_name)
        val backMuscle: Array<String> = resources.getStringArray(R.array.back_muscle)
        val backVideo: Array<String> = resources.getStringArray(R.array.back_video)
        val backThumbnail: Array<String> = resources.getStringArray(R.array.back_thumbnail)

        bodyworksVM.addWorkoutData(
            this,
            backWorkOutName,
            backMuscle,
            backVideo,
            backThumbnail,
            "back"
        )

        val bicepWorkOutName: Array<String> = resources.getStringArray(R.array.bicep_workout_name)
        val bicepMuscle: Array<String> = resources.getStringArray(R.array.bicep_muscle)
        val bicepVideo: Array<String> = resources.getStringArray(R.array.bicep_video)
        val bicepThumbnail: Array<String> = resources.getStringArray(R.array.bicep_thumbnail)

        bodyworksVM.addWorkoutData(
            this,
            bicepWorkOutName,
            bicepMuscle,
            bicepVideo,
            bicepThumbnail,
            "biceps"
        )

        val cardioWorkOutName: Array<String> = resources.getStringArray(R.array.cardio_workout_name)
        val cardioMuscle: Array<String> = resources.getStringArray(R.array.cardio_muscle)
        val cardioVideo: Array<String> = resources.getStringArray(R.array.cardio_video)
        val cardioThumbnail: Array<String> = resources.getStringArray(R.array.cardio_thumbnail)

        bodyworksVM.addWorkoutData(
            this,
            cardioWorkOutName,
            cardioMuscle,
            cardioVideo,
            cardioThumbnail,
            "cardio"
        )

        val chestWorkOutName: Array<String> = resources.getStringArray(R.array.chest_workout_name)
        val chestMuscle: Array<String> = resources.getStringArray(R.array.chest_muscle)
        val chestVideo: Array<String> = resources.getStringArray(R.array.chest_video)
        val chestThumbnail: Array<String> = resources.getStringArray(R.array.chest_thumbnail)

        bodyworksVM.addWorkoutData(
            this,
            chestWorkOutName,
            chestMuscle,
            chestVideo,
            chestThumbnail,
            "chest"
        )

        val legWorkOutName: Array<String> = resources.getStringArray(R.array.leg_workout_name)
        val legMuscle: Array<String> = resources.getStringArray(R.array.leg_muscle)
        val legVideo: Array<String> = resources.getStringArray(R.array.leg_video)
        val legThumbnail: Array<String> = resources.getStringArray(R.array.leg_thumbnail)

        bodyworksVM.addWorkoutData(this, legWorkOutName, legMuscle, legVideo, legThumbnail, "leg")

        val shoulderWorkOutName: Array<String> =
            resources.getStringArray(R.array.shoulder_workout_name)
        val shoulderMuscle: Array<String> = resources.getStringArray(R.array.shoulder_muscle)
        val shoulderVideo: Array<String> = resources.getStringArray(R.array.shoulder_video)
        val shoulderThumbnail: Array<String> = resources.getStringArray(R.array.shoulder_thumbnail)

        bodyworksVM.addWorkoutData(
            this,
            shoulderWorkOutName,
            shoulderMuscle,
            shoulderVideo,
            shoulderThumbnail,
            "shoulder"
        )

        val tricepWorkOutName: Array<String> =
            resources.getStringArray(R.array.triceps_workout_name)
        val tricepMuscle: Array<String> = resources.getStringArray(R.array.triceps_muscle)
        val tricepVideo: Array<String> = resources.getStringArray(R.array.triceps_video)
        val tricepThumbnail: Array<String> = resources.getStringArray(R.array.triceps_thumbnail)

        bodyworksVM.addWorkoutData(
            this,
            tricepWorkOutName,
            tricepMuscle,
            tricepVideo,
            tricepThumbnail,
            "triceps"
        )
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

}