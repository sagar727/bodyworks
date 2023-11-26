package com.example.bodyworks.views.sleepReminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bodyworks.R
import com.example.bodyworks.databinding.ActivitySleepReminderBinding
import com.example.bodyworks.databinding.ActivityWaterReminderBinding

class SleepReminder : AppCompatActivity() {
    private lateinit var binding: ActivitySleepReminderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySleepReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar Code For Back button and title
        val toolbar = binding.sleepReminderToolBar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}