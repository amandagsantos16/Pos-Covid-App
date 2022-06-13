package com.amanda.poscovid.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

private const val API_FORMAT = "yyyy/MM/dd"
private const val UI_FORMAT = "dd/MM/yyyy"
private const val DATA_FORMAT = "HH:mm"
const val FULL_API_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"

@SuppressLint("SimpleDateFormat")
fun Date.formatToUi(): String {
    return SimpleDateFormat(UI_FORMAT).format(this.time)
}

@SuppressLint("SimpleDateFormat")
fun Date.formatToApi(): String {
    return SimpleDateFormat(API_FORMAT).format(this.time)
}

@SuppressLint("SimpleDateFormat")
fun Date.formatDate(): String {
    return SimpleDateFormat(DATA_FORMAT).format(this.time)
}