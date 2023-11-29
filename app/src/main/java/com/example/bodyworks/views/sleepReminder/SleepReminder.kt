package com.example.bodyworks.views.sleepReminder

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
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.bodyworks.databinding.ActivitySleepReminderBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.Calendar

class SleepReminder : AppCompatActivity() {
    private lateinit var binding: ActivitySleepReminderBinding
    private lateinit var txtViewSleepTimeSetOrNot: TextView
    private lateinit var btnSleepTime: Button

    private var sleepReminderHour: Int = 0
    private var sleepReminderMinute: Int = 0

    private var isReminderSet: Boolean = false
    private lateinit var sharedPreferences : SharedPreferences

    private var hour = ""
    private var minute = ""

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

        createNotificationChannel()

        txtViewSleepTimeSetOrNot = binding.txtViewSleepTimeSetOrNot
        btnSleepTime = binding.btnSleepTime

        txtViewSleepTimeSetOrNot.text = "Sleep Time Selected: -"

        sharedPreferences = getSharedPreferences("SleepReminderPrefs", MODE_PRIVATE)
        isReminderSet = sharedPreferences.getBoolean("isReminderSet", false)

        reminderView()

        btnSleepTime.setOnClickListener {
            if (btnSleepTime.text == "Select Sleep Time") {
                openTimePicker()
            } else if(btnSleepTime.text == "Start Reminder") {
                btnSleepTime.text = "Stop Reminder"
                startNotification()
            } else{
                txtViewSleepTimeSetOrNot.visibility = View.GONE
                btnSleepTime.text = "Select Sleep Time"
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
                    .setHour(23)
                    .setMinute(0)
                    .setTitleText("Select Sleep Time")
                    .build()
            picker.show(supportFragmentManager, "Sleep Time Picker")

            picker.addOnPositiveButtonClickListener {
                hour = if (picker.hour <= 9) "0${picker.hour}" else "${picker.hour}"
                minute = if (picker.minute <= 9) "0${picker.minute}" else "${picker.minute}"

                sleepReminderHour = picker.hour
                sleepReminderMinute = picker.minute

                txtViewSleepTimeSetOrNot.visibility = View.VISIBLE
                txtViewSleepTimeSetOrNot.text = "Sleep Time Selected: $hour:$minute"
                updateButton()
            }
        }
    }

    private fun updateButton() {
        if (btnSleepTime.text == "Select Sleep Time") {
            btnSleepTime.text = "Start Reminder"
        } else if(btnSleepTime.text == "Start Reminder") {
            btnSleepTime.text = "Stop Reminder"
        } else{
            btnSleepTime.text = "Select Sleep Time"
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
        val intent = Intent(applicationContext, SleepReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext, 100, intent, PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val time = getTime()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
        savePreferences()
    }
    private fun getTime(): Long {
        val hour = sleepReminderHour
        val minute = sleepReminderMinute

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND,0)

        return calendar.timeInMillis
    }

    private fun cancelNotification() {
        val intent = Intent(applicationContext, SleepReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext, 100, intent,
            PendingIntent.FLAG_IMMUTABLE
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

            btnSleepTime.text = "Stop Reminder"
            txtViewSleepTimeSetOrNot.visibility = View.VISIBLE
            txtViewSleepTimeSetOrNot.text = "Sleep Time Selected: $hour:$minute"
        } else{
            btnSleepTime.text = "Select Sleep Time"
            txtViewSleepTimeSetOrNot.visibility = View.GONE
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "sleep_reminder_channel",
                "Sleep Reminder",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = "Notification Settings For Sleep Reminder"

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}