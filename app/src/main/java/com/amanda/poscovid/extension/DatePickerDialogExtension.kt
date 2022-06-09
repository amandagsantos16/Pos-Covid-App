package com.amanda.poscovid.extension

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import java.util.*

fun showDataPickerDialog(context: Context, calendar: Calendar, listener: (DatePicker, Int, Int, Int) -> Unit) {
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH)
    val year = calendar.get(Calendar.YEAR)
    val datePickerDialog = DatePickerDialog(context, listener, year, month, day)
    datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
    datePickerDialog.show()
}