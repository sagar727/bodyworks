package com.example.bodyworks.dailyWorkoutPlanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.bodyworks.databinding.ActivityDailyWorkoutPlannerBinding
import com.example.bodyworks.viewModel.BodyWorksViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class DailyWorkoutPlanner : AppCompatActivity() {
    private lateinit var binding: ActivityDailyWorkoutPlannerBinding
    private lateinit var bodyWorksViewModel: BodyWorksViewModel
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPagerWorkoutPlanner: ViewPager2
    private lateinit var fragmentAdapter: WorkoutPlannerFragmentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDailyWorkoutPlannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Binding views
        tabLayout = binding.tabLayout
        viewPagerWorkoutPlanner = binding.viewPagerWorkoutPlanner

        bodyWorksViewModel = ViewModelProvider(this)[BodyWorksViewModel::class.java]

        //Toolbar Code For Back button and title
        val toolbar = binding.dailyWorkoutPlannerToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Adapter Code for Fragment To Display
        fragmentAdapter = WorkoutPlannerFragmentAdapter(supportFragmentManager, lifecycle)
        viewPagerWorkoutPlanner.adapter = fragmentAdapter

        // Associating fragment to tabs
        tabLayout.addOnTabSelectedListener(object: OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPagerWorkoutPlanner.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        viewPagerWorkoutPlanner.registerOnPageChangeCallback(object : OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }
}