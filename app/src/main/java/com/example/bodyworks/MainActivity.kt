package com.example.bodyworks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bodyworks.databinding.ActivityMainBinding
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
        binding.button.setOnClickListener{
            val intent = Intent(this, TimerActivity :: class.java)
            startActivity(intent)
        }

        binding.button2.setOnClickListener{
            val intent = Intent(this, WorkoutActivity :: class.java)
            startActivity(intent)
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

}