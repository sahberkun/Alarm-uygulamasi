package com.example.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.util.Calendar
import android.os.Build
import android.util.Log

class SaveData(context: Context) {

    var context: Context = context
    var sharedRef:SharedPreferences=context.getSharedPreferences("myRef",Context.MODE_PRIVATE)

    fun SaveData(hours:Int,minutes:Int){
        var editor=sharedRef!!.edit()
        editor.putInt("hour",hours)
        editor.putInt("minute",minutes)
        editor.commit()
    }

    fun getHour():Int{
        return sharedRef.getInt("hour",0)
    } fun getMin():Int{
        return sharedRef.getInt("minute",0)
    }
    fun setAlarm(hours: Int, mins: Int){
        Log.d("MYTAG", "Alarmın şu saate çalacak $hours: $mins")
        val calendar = Calendar.getInstance()
        calendar.set(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            hours,
            mins,
            0
        )

        val alarmManager = context.getSystemService(Service.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, AlarmAlıcı::class.java).also {
            it.action = "com.example.alarm"
            it.putExtra("Hours", hours)
            it.putExtra("Minutes", mins)
        }

        var pendingIntent: PendingIntent? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)

        } else {
            pendingIntent =PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        alarmManager.set(AlarmManager.RTC, calendar.timeInMillis, pendingIntent)

    }
}