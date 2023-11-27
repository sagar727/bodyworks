package com.example.bodyworks.views.sleepReminder

import android.app.AppOpsManager
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.bodyworks.R
import com.example.bodyworks.databinding.ActivitySleepReminderBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class SleepReminder : AppCompatActivity() {
    private lateinit var binding: ActivitySleepReminderBinding
    private lateinit var txtViewSleepTimeSetOrNot: TextView
    private lateinit var btnSleepTime: Button

    private var sleepReminderHour: Int = 0
    private var sleepReminderMinute: Int = 0

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

        txtViewSleepTimeSetOrNot = binding.txtViewSleepTimeSetOrNot
        btnSleepTime = binding.btnSleepTime

        txtViewSleepTimeSetOrNot.text = "Sleep Time Selected: -"

        btnSleepTime.setOnClickListener {
            if (btnSleepTime.text == "Select Sleep Time") {
                openTimePicker()
            } else if(btnSleepTime.text == "Start Reminder") {
                btnSleepTime.text = "Stop Reminder"
                // TODO: Start Reminder Code Here
            } else{
                txtViewSleepTimeSetOrNot.visibility = View.GONE
                btnSleepTime.text = "Select Sleep Time"
                // TODO: Stop Reminder Code Here
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
                var hour: String = if (picker.hour <= 9) "0${picker.hour}" else "${picker.hour}"
                var minute: String = if (picker.minute <= 9) "0${picker.minute}" else "${picker.minute}"

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
}