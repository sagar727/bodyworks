package com.example.bodyworks.views.waterReminder

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.bodyworks.R

class StartAlarmReceiver : BroadcastReceiver() {
    @SuppressLint("ScheduleExactAlarm")
    override fun onReceive(context: Context, intent: Intent) {
        val channelId = "water_reminder_channel"
        val notificationId = 25
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Water Reminder")
            .setContentText("Time to drink water! Stay Hydrated...")
            .build()

        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, notification)

        val intervalMillis = intent.getLongExtra("waterReminderInterval", 15)
        val nextAlarmTime = System.currentTimeMillis() + intervalMillis

        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val newPendingIntent = PendingIntent.getBroadcast(
            context, 11, intent, PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            nextAlarmTime,
            newPendingIntent
        )
    }
}
