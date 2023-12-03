package com.example.bodyworks.views.waterReminder

import android.app.AlarmManager
import android.app.AppOpsManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import com.example.bodyworks.R
import com.example.bodyworks.databinding.ActivityWaterReminderBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.Calendar

/**
 * Author: Ketul Chauhan
 * Date: Nov 7, 2023
 * */
class WaterReminderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWaterReminderBinding
    private lateinit var txtViewStartTime: TextView
    private lateinit var txtViewEndTime: TextView
    private lateinit var btnStartTime: Button
    private lateinit var btnEndTime: Button
    private lateinit var timeIntervalInputTxt: TextInputEditText
    private lateinit var btnStartReminder: Button
    private var reminderStartHour: Int = 0
    private var reminderStartMinute: Int = 0
    private var reminderEndHour: Int = 0
    private var reminderEndMinute: Int = 0

    private lateinit var sharedPreferences: SharedPreferences

    private val isReminderSetKey = "isReminderSet"
    private val reminderStartHourKey = "reminderStartHour"
    private val reminderStartMinuteKey = "reminderStartMinute"
    private val reminderEndHourKey = "reminderEndHour"
    private val reminderEndMinuteKey = "reminderEndMinute"
    private val intervalKey = "interval"

    private var isStartTimeSelected = false
    private var isEndTimeSelected = false

    companion object {
        const val MY_PERMISSIONS_REQUEST_SEND_NOTIFICATION = 1001
        lateinit var startAlarmPendingIntent: PendingIntent
        lateinit var endAlarmPendingIntent: PendingIntent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = this.let { PreferenceManager.getDefaultSharedPreferences(it) }!!
        val themeColor = sharedPreferences.getString("Current Theme", getString(R.string.red))
        val color = changeTheme(themeColor)
        binding = ActivityWaterReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar Code For Back button and title
        val toolbar = binding.waterReminderToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        binding.txtViewWaterReminderTime.setTextColor(getColor(color))

        val startAlarmIntent = Intent(this, StartAlarmReceiver::class.java)
        startAlarmPendingIntent =
            PendingIntent.getBroadcast(this, 0, startAlarmIntent, PendingIntent.FLAG_IMMUTABLE)

        val endAlarmIntent = Intent(this, EndAlarmReceiver::class.java)
        endAlarmPendingIntent =
            PendingIntent.getBroadcast(this, 0, endAlarmIntent, PendingIntent.FLAG_IMMUTABLE)

        txtViewStartTime = binding.txtViewStartTime
        txtViewEndTime = binding.txtViewEndTime
        btnStartTime = binding.btnStartTime
        btnEndTime = binding.btnEndTime
        timeIntervalInputTxt = binding.timeIntervalInputTxt
        btnStartReminder = binding.btnStartReminder

        sharedPreferences = getSharedPreferences("WaterReminderPrefs", Context.MODE_PRIVATE)
        restorePreferences()

        // Checking if reminder is set
        updateButton()

        btnStartTime.setOnClickListener {
            openTimePicker("Select Reminder Start Time")
        }

        btnEndTime.setOnClickListener {
            openTimePicker("Select Reminder End Time")
        }

        btnStartReminder.setOnClickListener {
            // Opening notification settings if notifications are off
            if (!isNotificationEnabled()) {
                Toast.makeText(
                    applicationContext,
                    "Please Allow App To Send Notifications",
                    Toast.LENGTH_SHORT
                ).show()
                openNotificationSettings()
            } else {
                if (isStartTimeSelected && isEndTimeSelected) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        if (ContextCompat.checkSelfPermission(
                                this,
                                android.Manifest.permission.VIBRATE
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            // Permission is not granted
                            // Request the permission
                            ActivityCompat.requestPermissions(
                                this,
                                arrayOf(android.Manifest.permission.VIBRATE),
                                MY_PERMISSIONS_REQUEST_SEND_NOTIFICATION
                            )
                        } else {
                            // Permission has already been granted
                            // Proceed with sending the notification
                            if (!isReminderSet()) {
                                scheduleNotification()
                            } else {
                                cancelNotification()
                            }
                        }
                    } else {
                        // For devices running on lower API levels, no explicit permission is required
                        if (!isReminderSet()) {
                            scheduleNotification()
                        } else {
                            cancelNotification()
                        }
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Please Select Start Time And End Time To Set Reminder",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun scheduleNotification() {
        if(timeIntervalInputTxt.text.toString() != "" &&  timeIntervalInputTxt.text.toString() != "0" && timeIntervalInputTxt.text.toString().toInt() > 0) {
            //Saving Preferences
            savePreferences()

            //Updating Button To Stop Reminder
            updateButton()

            val manager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val calendar = Calendar.getInstance()
            val currentTime = Calendar.getInstance()

            calendar.set(Calendar.HOUR_OF_DAY, reminderStartHour)
            calendar.set(Calendar.MINUTE, reminderStartMinute)
            calendar.set(Calendar.SECOND, 0)

            if (calendar.before(currentTime)) {
                calendar.add(Calendar.DATE, 1) // Set to next day if time has already passed today
            }

            val interval = timeIntervalInputTxt.text.toString().toLong() * 60 * 1000

            manager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                interval,
                startAlarmPendingIntent
            )

            val endTimeCalendar = Calendar.getInstance()
            endTimeCalendar.set(Calendar.HOUR_OF_DAY, reminderEndHour)
            endTimeCalendar.set(Calendar.MINUTE, reminderEndMinute)
            endTimeCalendar.set(Calendar.SECOND, 0)

            if (endTimeCalendar.before(currentTime)) {
                endTimeCalendar.add(
                    Calendar.DATE,
                    1
                ) // Set to next day if time has already passed today
            }

            manager.set(AlarmManager.RTC_WAKEUP, endTimeCalendar.timeInMillis, endAlarmPendingIntent)
        }else {
            Toast.makeText(applicationContext, "Please Select Valid Time Interval In Minutes", Toast.LENGTH_SHORT).show()
            updateButton()
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

    private fun openTimePicker(title: String) {
        val picker =
            MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(7)
                .setMinute(0)
                .setTitleText(title)
                .build()
        picker.show(supportFragmentManager, "Time Picker")

        picker.addOnPositiveButtonClickListener {
            var hour: String = if (picker.hour <= 9) "0${picker.hour}" else "${picker.hour}"
            var minute: String = if (picker.minute <= 9) "0${picker.minute}" else "${picker.minute}"

            // Checking if the time picker is for the start or end time.
            if (title === "Select Reminder Start Time") {
                txtViewStartTime.text = "$hour:$minute"
                reminderStartHour = picker.hour
                reminderStartMinute = picker.minute
                isStartTimeSelected = true
            } else {
                txtViewEndTime.text = "$hour:$minute"
                reminderEndHour = picker.hour
                reminderEndMinute = picker.minute
                isEndTimeSelected = true
            }
        }
    }

    private fun isReminderSet(): Boolean {
        return sharedPreferences.getBoolean(isReminderSetKey, false)
    }

    private fun savePreferences() {
        val editor = sharedPreferences.edit()
        editor.putBoolean(isReminderSetKey, true)
        editor.putInt(reminderStartHourKey, reminderStartHour)
        editor.putInt(reminderStartMinuteKey, reminderStartMinute)
        editor.putInt(reminderEndHourKey, reminderEndHour)
        editor.putInt(reminderEndMinuteKey, reminderEndMinute)
        editor.putString(intervalKey, timeIntervalInputTxt.text.toString())
        editor.apply()
    }

    private fun clearPreferences() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    private fun restorePreferences() {
        if (isReminderSet()) {
            reminderStartHour = sharedPreferences.getInt(reminderStartHourKey, 0)
            reminderStartMinute = sharedPreferences.getInt(reminderStartMinuteKey, 0)
            reminderEndHour = sharedPreferences.getInt(reminderEndHourKey, 0)
            reminderEndMinute = sharedPreferences.getInt(reminderEndMinuteKey, 0)
            timeIntervalInputTxt.setText(sharedPreferences.getString(intervalKey, ""))

            if (reminderStartHour != 0) {
                var startHour: String =
                    if (reminderStartHour <= 9) "0$reminderEndHour" else "$reminderEndHour"
                var endHour: String =
                    if (reminderEndHour <= 9) "0$reminderEndHour" else "$reminderEndHour"
                var startMinute: String =
                    if (reminderStartMinute <= 9) "0$reminderStartMinute" else "$reminderStartMinute"
                var endMinute: String =
                    if (reminderEndMinute <= 9) "0$reminderEndMinute" else "$reminderEndMinute"

                txtViewStartTime.text = "$startHour:$startMinute"
                txtViewEndTime.text = "$endHour:$endMinute"
                isStartTimeSelected = true
                isEndTimeSelected = true
            } else {
                txtViewStartTime.text = "Start Time: "
                txtViewEndTime.text = "End Time: "
                isStartTimeSelected = false
                isEndTimeSelected = false
            }
        }
    }

    private fun updateButton() {
        if (isReminderSet()) {
            btnStartReminder.text = "Stop Reminder"
        } else {
            btnStartReminder.text = "Start Reminder"
        }
    }

    private fun cancelNotification() {
        //Clearing Preferences
        clearPreferences()
        //Updating Button To Start Reminder
        updateButton()

        //Resetting TextViews as Default
        txtViewStartTime.text = "Start Time: "
        txtViewEndTime.text = "End Time: "
        timeIntervalInputTxt.setText("")

        val manager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        manager.cancel(startAlarmPendingIntent)
        manager.cancel(endAlarmPendingIntent)
    }

    private fun changeTheme(themeColor: String?): Int {
        val theme = super.getTheme()
        when (themeColor) {
            "Red" -> {
                theme.applyStyle(R.style.Base_Theme_Red, true)
                return R.color.primary
            }
            "Orange" -> {
                theme.applyStyle(R.style.Base_Theme_Orange, true)
                return R.color.orange
            }
            "Brown" -> {
                theme.applyStyle(R.style.Base_Theme_Brown, true)
                return R.color.brown
            }
        }
        return 0
    }
}
