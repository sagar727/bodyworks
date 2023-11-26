package com.example.bodyworks.views.dietPlans

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.bodyworks.R
import com.example.bodyworks.databinding.ActivityDietMealPlansBinding

class DietMealPlans : AppCompatActivity() {

    private lateinit var binding: ActivityDietMealPlansBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_meal_plans)

        binding = ActivityDietMealPlansBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.materialToolbar3
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        //string array for plans
        val dietSelectionArray = resources.getStringArray(R.array.diet_plans_name)
        val dietPlanAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, dietSelectionArray)
        binding.dietPlanDropDown.setAdapter(dietPlanAdapter)

        binding.dietPlanDropDown.setOnItemClickListener { _, _, position, _ ->
            when(position){
                0 -> {
                    binding.breakfastText.text = splitText(getString(R.string.loose_breakfast))
                    binding.lunchText.text = splitText(getString(R.string.loose_lunch))
                    binding.snackText.text = splitText(getString(R.string.loose_snacks))
                    binding.dinnerText.text = splitText(getString(R.string.loose_dinner))
                    binding.preWorkoutText.text = splitText(getString(R.string.loose_pre_workout))
                    binding.postWorkoutText.text = splitText(getString(R.string.loose_post_workout))
                }
                1 -> {
                    binding.breakfastText.text = splitText(getString(R.string.gain_breakfast))
                    binding.lunchText.text = splitText(getString(R.string.gain_lunch))
                    binding.snackText.text = splitText(getString(R.string.gain_snacks))
                    binding.dinnerText.text = splitText(getString(R.string.gain_dinner))
                    binding.preWorkoutText.text = splitText(getString(R.string.gain_pre_workout))
                    binding.postWorkoutText.text = splitText(getString(R.string.gain_post_workout))
                }
                2 -> {
                    binding.breakfastText.text = splitText(getString(R.string.muscle_breakfast))
                    binding.lunchText.text = splitText(getString(R.string.muscle_lunch))
                    binding.snackText.text = splitText(getString(R.string.muscle_snacks))
                    binding.dinnerText.text = splitText(getString(R.string.muscle_dinner))
                    binding.postWorkoutText.text = splitText(getString(R.string.muscle_post_workout))
                }
            }
        }
    }

    private fun splitText(txt: String): String{
        val str = txt.split("-").toTypedArray()
        var newStr = ""
        for(teststr in str){
            if(newStr == ""){
                newStr = "* $teststr"
            }else{
                newStr = "$newStr\n* $teststr"
            }
        }
        return newStr
    }
}