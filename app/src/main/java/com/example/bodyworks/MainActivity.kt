package com.example.bodyworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val abdomenWorkOutName: Array<String> = resources.getStringArray(R.array.abdomen_workout_name)
        val abdomenMuscle: Array<String> = resources.getStringArray(R.array.abdomen_Muscle)
        val abdomenVideo: Array<String> = resources.getStringArray(R.array.abdomen_video)
        val abdomenThumbnail: Array<String> = resources.getStringArray(R.array.abdomen_thumbnail)

        val iteration = abdomenWorkOutName.size
        var i = 0;
        while(i < iteration){
            val workoutDataModel = WorkoutDataModel(abdomenWorkOutName[i],abdomenVideo[i],abdomenThumbnail[i],abdomenMuscle[i])
            bodyworksVM.addAbdomenWorkoutData(this, workoutDataModel,"abdomen")
            i++
        }

        val backWorkOutName: Array<String> = resources.getStringArray(R.array.back_workout_name)
        val backMuscle: Array<String> = resources.getStringArray(R.array.back_muscle)
        val backVideo: Array<String> = resources.getStringArray(R.array.back_video)
        val backThumbnail: Array<String> = resources.getStringArray(R.array.back_thumbnail)

        val iteration1 = backWorkOutName.size
        var j = 0;
        while(j < iteration1){
            val workoutDataModel = WorkoutDataModel(backWorkOutName[j],backVideo[j],backThumbnail[j],backMuscle[j])
            bodyworksVM.addBackWorkoutData(this, workoutDataModel,"back")
            j++
        }

        val bicepWorkOutName: Array<String> = resources.getStringArray(R.array.bicep_workout_name)
        val bicepMuscle: Array<String> = resources.getStringArray(R.array.bicep_muscle)
        val bicepVideo: Array<String> = resources.getStringArray(R.array.bicep_video)
        val bicepThumbnail: Array<String> = resources.getStringArray(R.array.bicep_thumbnail)

        val iteration2 = bicepWorkOutName.size
        var k = 0;
        while(k < iteration2){
            val workoutDataModel = WorkoutDataModel(bicepWorkOutName[k],bicepVideo[k],bicepThumbnail[k],bicepMuscle[k])
            bodyworksVM.addBicepWorkoutData(this, workoutDataModel,"biceps")
            k++
        }

        val cardioWorkOutName: Array<String> = resources.getStringArray(R.array.cardio_workout_name)
        val cardioMuscle: Array<String> = resources.getStringArray(R.array.cardio_muscle)
        val cardioVideo: Array<String> = resources.getStringArray(R.array.cardio_video)
        val cardioThumbnail: Array<String> = resources.getStringArray(R.array.cardio_thumbnail)

        val iteration3 = cardioWorkOutName.size
        var l = 0;
        while(l < iteration3){
            val workoutDataModel = WorkoutDataModel(cardioWorkOutName[l],cardioVideo[l],cardioThumbnail[l],cardioMuscle[l])
            bodyworksVM.addCardioWorkoutData(this, workoutDataModel,"cardio")
            l++
        }

        val chestWorkOutName: Array<String> = resources.getStringArray(R.array.chest_workout_name)
        val chestMuscle: Array<String> = resources.getStringArray(R.array.chest_muscle)
        val chestVideo: Array<String> = resources.getStringArray(R.array.chest_video)
        val chestThumbnail: Array<String> = resources.getStringArray(R.array.chest_thumbnail)

        val iteration4 = chestWorkOutName.size
        var m = 0;
        while(m < iteration4){
            val workoutDataModel = WorkoutDataModel(chestWorkOutName[m],chestVideo[m],chestThumbnail[m],chestMuscle[m])
            bodyworksVM.addChestWorkoutData(this, workoutDataModel,"chest")
            m++
        }

        val legWorkOutName: Array<String> = resources.getStringArray(R.array.leg_workout_name)
        val legMuscle: Array<String> = resources.getStringArray(R.array.leg_muscle)
        val legVideo: Array<String> = resources.getStringArray(R.array.leg_video)
        val legThumbnail: Array<String> = resources.getStringArray(R.array.leg_thumbnail)

        val iteration5 = legWorkOutName.size
        var n = 0;
        while(n < iteration5){
            val workoutDataModel = WorkoutDataModel(legWorkOutName[n],legVideo[n],legThumbnail[n],legMuscle[n])
            bodyworksVM.addLegWorkoutData(this, workoutDataModel,"leg")
            n++
        }

        val shoulderWorkOutName: Array<String> = resources.getStringArray(R.array.shoulder_workout_name)
        val shoulderMuscle: Array<String> = resources.getStringArray(R.array.shoulder_muscle)
        val shoulderVideo: Array<String> = resources.getStringArray(R.array.shoulder_video)
        val shoulderThumbnail: Array<String> = resources.getStringArray(R.array.shoulder_thumbnail)

        val iteration6 = shoulderWorkOutName.size
        var o = 0;
        while(o < iteration6){
            val workoutDataModel = WorkoutDataModel(shoulderWorkOutName[o],shoulderVideo[o],shoulderThumbnail[o],shoulderMuscle[o])
            bodyworksVM.addShoulderWorkoutData(this, workoutDataModel,"shoulder")
            o++
        }

        val tricepWorkOutName: Array<String> = resources.getStringArray(R.array.triceps_workout_name)
        val tricepMuscle: Array<String> = resources.getStringArray(R.array.triceps_muscle)
        val tricepVideo: Array<String> = resources.getStringArray(R.array.triceps_video)
        val tricepThumbnail: Array<String> = resources.getStringArray(R.array.triceps_thumbnail)

        val iteration7 = tricepWorkOutName.size
        var p = 0;
        while(p < iteration7){
            val workoutDataModel = WorkoutDataModel(tricepWorkOutName[p],tricepVideo[p],tricepThumbnail[p],tricepMuscle[p])
            bodyworksVM.addTricepWorkoutData(this, workoutDataModel,"triceps")
            p++
        }

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
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

}