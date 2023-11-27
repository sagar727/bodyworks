package com.example.bodyworks.views.workoutReminder

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.AppOpsManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.bodyworks.databinding.ActivityWorkoutReminderBinding
import com.example.bodyworks.views.waterReminder.StartAlarmReceiver
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.Calendar

class WorkoutReminder : AppCompatActivity() {

    private lateinit var binding: ActivityWorkoutReminderBinding
    private lateinit var txtViewWorkoutTimeSetOrNot: TextView
    private lateinit var btnWorkoutTime: Button

    private var workoutReminderHour: Int = 0
    private var workoutReminderMinute: Int = 0

    private var isReminderSet: Boolean = false
    private lateinit var sharedPreferences : SharedPreferences

    private var hour = ""
    private var minute = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWorkoutReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar Code For Back button and title
        val toolbar = binding.workoutReminderToolBar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        txtViewWorkoutTimeSetOrNot = binding.txtViewWorkoutTimeSetOrNot
        btnWorkoutTime = binding.btnWorkoutTime

        txtViewWorkoutTimeSetOrNot.text = "Workout Time Selected: -"

        sharedPreferences = getSharedPreferences("WorkoutReminderPrefs", MODE_PRIVATE)
        isReminderSet = sharedPreferences.getBoolean("isReminderSet", false)

        reminderView()

        btnWorkoutTime.setOnClickListener {
            if (btnWorkoutTime.text == "Select Workout Time") {
                openTimePicker()
            } else if(btnWorkoutTime.text == "Start Reminder") {
                btnWorkoutTime.text = "Stop Reminder"
                startNotification()
            } else{
                txtViewWorkoutTimeSetOrNot.visibility = View.GONE
                btnWorkoutTime.text = "Select Workout Time"
                cancelNotification()
            }
        }
    }
    private fun openTimePicker() {
        //Checking Whether User Has Enabled Notifications Or Not
        if (!isNotificationEnabled()) {
            Toast.makeText(
                applicationContext,
                "Please Allow App To Send Notifications",
                Toast.LENGTH_SHORT
            ).show()
            openNotificationSettings()
        }else {
            val picker =
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(7)
                    .setMinute(0)
                    .setTitleText("Select Workout Time")
                    .build()
            picker.show(supportFragmentManager, "Workout Time Picker")

            picker.addOnPositiveButtonClickListener {
                hour = if (picker.hour <= 9) "0${picker.hour}" else "${picker.hour}"
                minute = if (picker.minute <= 9) "0${picker.minute}" else "${picker.minute}"

                workoutReminderHour = picker.hour
                workoutReminderMinute = picker.minute

                txtViewWorkoutTimeSetOrNot.visibility = View.VISIBLE
                txtViewWorkoutTimeSetOrNot.text = "Workout Time Selected: $hour:$minute"
                updateButton()
            }
        }
    }

    private fun updateButton() {
        if (btnWorkoutTime.text == "Select Workout Time") {
            btnWorkoutTime.text = "Start Reminder"
        } else if(btnWorkoutTime.text == "Start Reminder") {
            btnWorkoutTime.text = "Stop Reminder"
        } else{
            btnWorkoutTime.text = "Select Workout Time"
        }
    }

    private fun isNotificationEnabled(): Boolean {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.areNotificationsEnabled()
        } else {
            val appOps = this.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
            val appOpsClass: Class<*> = appOps.javaClass
            val checkOpNoThrowMethod = appOpsClass.getMethod(
                "checkOpNoThrow", Integer.TYPE, Integer.TYPE, String::class.java
            )
            val opPostNotificationValue = appOpsClass.getDeclaredField("OP_POST_NOTIFICATION")
            val value = opPostNotificationValue.get(Int::class.java) as Int
            checkOpNoThrowMethod.invoke(
                appOps,
                value,
                this.applicationInfo.uid,
                this.packageName
            ) as Int == AppOpsManager.MODE_ALLOWED
        }
    }

    private fun openNotificationSettings() {
        val intent = Intent()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            }

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
                intent.putExtra("app_package", packageName)
                intent.putExtra("app_uid", applicationInfo.uid)
            }

            else -> {
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                intent.data = Uri.parse("package:$packageName")
            }
        }
        startActivity(intent)
    }

    @SuppressLint("ScheduleExactAlarm")
    private fun startNotification(){
        val intent = Intent(applicationContext, WorkoutReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext, 2, intent, PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val time = getTime()
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 86400000, pendingIntent)

        savePreferences()
    }
    private fun getTime(): Long {
        val hour = workoutReminderHour
        val minute = workoutReminderMinute

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)

        return calendar.timeInMillis

    }

    private fun cancelNotification() {
        val intent = Intent(applicationContext, WorkoutReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext, 2, intent, PendingIntent.FLAG_IMMUTABLE
        )

        val manager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        manager.cancel(pendingIntent)

        clearPreferences()
    }

    private fun savePreferences() {
        val editor = sharedPreferences.edit()
        editor.putBoolean("isReminderSet", true)
        editor.putString("reminderStartHour", hour)
        editor.putString("reminderStartMinute", minute)
        editor.apply()
    }

    private fun clearPreferences() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    private fun reminderView(){
        if (isReminderSet){
            hour = sharedPreferences.getString("reminderStartHour", "")!!
            minute = sharedPreferences.getString("reminderStartMinute", "")!!

            btnWorkoutTime.text = "Stop Reminder"
            txtViewWorkoutTimeSetOrNot.visibility = View.VISIBLE
            txtViewWorkoutTimeSetOrNot.text = "Workout Time Selected: $hour:$minute"
        } else{
            btnWorkoutTime.text = "Select Workout Time"

            txtViewWorkoutTimeSetOrNot.visibility = View.GONE
        }
    }
}