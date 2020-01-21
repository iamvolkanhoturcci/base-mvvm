package com.volkanhotur.basemvvm.android.utils

import android.annotation.SuppressLint
import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author volkanhotur
 */
object DateUtils {

    fun findDate(dateFormat: String?): String? {
        try {
            @SuppressLint("SimpleDateFormat")
            val sdf = SimpleDateFormat(dateFormat, Locale.US)

            val today: String

            val calendar: Calendar = GregorianCalendar(Locale.US)

            today = sdf.format(calendar.time)

            return today
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }


    private fun isDateComponentEqualToDate(date1: Date?, date2: Date?): Boolean {
        val calendar = Calendar.getInstance()

        calendar.time = date1

        val year1 = calendar[Calendar.YEAR]

        val month1 = calendar[Calendar.MONTH]

        val day1 = calendar[Calendar.DATE]

        calendar.time = date2

        val year2 = calendar[Calendar.YEAR]

        val month2 = calendar[Calendar.MONTH]

        val day2 = calendar[Calendar.DATE]

        return year1 == year2 && month1 == month2 && day1 == day2
    }
}