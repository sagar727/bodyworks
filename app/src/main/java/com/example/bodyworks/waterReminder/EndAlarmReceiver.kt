package com.example.bodyworks.waterReminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class EndAlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val manager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent = PendingIntent.getBroadcast(context,0,Intent(context,StartAlarmReceiver::class.java),PendingIntent.FLAG_IMMUTABLE)
        manager.cancel(pendingIntent)
    }
}
