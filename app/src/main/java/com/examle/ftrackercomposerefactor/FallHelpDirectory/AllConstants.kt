package com.examle.ftrackercomposerefactor.FallHelpDirectory

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager


object AllConstants {
    val phoneNumbers = mutableListOf(1.2, 4.5, 3.2, 1.8)
    val timerNums = 15000
//https://developer.android.com/courses/android-basics-kotlin/course
internal fun level(context: Context): Int {
    val applicationContext: Context = context.applicationContext
    val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
    val intent = applicationContext.registerReceiver(null, filter) ?: return -1
    val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0)
    val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 1)
    return (level * 100.0 / scale).toInt()
}
}