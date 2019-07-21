package com.shaynek.hockey.common.util

import java.text.SimpleDateFormat
import java.util.*

private const val timeFormat = "hh:mm aa"

fun Date.getLocalTime(): String {
    val formatter = SimpleDateFormat(timeFormat, Locale.getDefault())
    return formatter.format(this)
}