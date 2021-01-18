package me.blackdroid.nab.ext

import java.text.SimpleDateFormat
import java.util.*

inline fun Int.toDateDisplay() : String {
    val simpleDateFormat = SimpleDateFormat("EEE, MMM d yyy")
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this.toLong()*1000
    return simpleDateFormat.format(calendar.time)
}

