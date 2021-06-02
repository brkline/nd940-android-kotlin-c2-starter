package com.udacity.asteroidradar.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun getNow() : String {
        val calendar = Calendar.getInstance()
        val currentTime = calendar.time
        val dateFormat = SimpleDateFormat("YYYY-MM-dd", Locale.getDefault())
        return dateFormat.format(currentTime)
    }

    fun getOneWeekFromNow() : String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 7)
        val currentTime = calendar.time
        val dateFormat = SimpleDateFormat("YYYY-MM-dd", Locale.getDefault())
        return dateFormat.format(currentTime)
    }
}